package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.patchable.*;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Size;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;


import java.util.*;
import java.util.stream.Collectors;

public final class WorldgenDataManager {

    public static LinkedHashMap<String, PatchableBiome> PATCHABLE_BIOMES = new PatchableBiomes().Data;
    public static LinkedHashMap<String, PatchableStructure> PATCHABLE_STRUCTURES = new PatchableStructures().Data;
    public static LinkedHashMap<String, PatchableStructureSet> PATCHABLE_STRUCTURE_SETS = new PatchableStructureSets().Data;
    public static LinkedHashMap<String, PatchableEntity> PATCHABLE_ENTITIES = new PatchableEntities().Data;
    public static LinkedHashMap<String, PatchableFeature> PATCHABLE_FEATURES = new PatchableFeatures().Data;
    public static PatchableProcessorLists PATCHABLE_PROCESSOR_LISTS = new PatchableProcessorLists();


    public static SortedSet<String> loadedBiomes = new TreeSet<>();
    public static SortedSet<String> loadedFeatures = new TreeSet<>();
    public static SortedSet<String> loadedProcessorLists= new TreeSet<>();
    public static SortedSet<String> loadedLootTables = new TreeSet<>();
    public static SortedSet<String> loadedEntities= new TreeSet<>();
    public static SortedSet<String> loadedTemplatePools = new TreeSet<>();
    public static SortedSet<String> loadedStructureSets = new TreeSet<>();
    public static SortedSet<String> loadedStructures = new TreeSet<>();

    public static void setBiomeData(String id, PatchableBiome biome) {
        PATCHABLE_BIOMES.replace(id, biome);
    }

    public static void setFeatureData(String id, PatchableFeature feature) {
        PATCHABLE_FEATURES.replace(id, feature);
    }
    public static void setEntityData(String id, PatchableEntity entity) {
        PATCHABLE_ENTITIES.replace(id, entity);
    }

    public static void setStructureSetData(String id, PatchableStructureSet structSet) {
        PATCHABLE_STRUCTURE_SETS.replace(id, structSet);
    }

    public static void setStructureData(String id, PatchableStructure structure) {
        PATCHABLE_STRUCTURES.replace(id, structure);
    }

    /*public static void loadWorldgenData(Optional<RegistryAccess> registryAccess) {
        registryAccess.ifPresentOrElse((access) -> {
            ProjectEvergreen.LOGGER.info("Loading dynamic worldgen data...");
            loadDynamicWorldgenData(access.asGetterLookup());
        },
        () -> {
            ProjectEvergreen.LOGGER.info("Loading default worldgen data...");
            loadDefaultWorldgenData();
        });
        ProjectEvergreen.LOGGER.info("Successfully mapped all worldgen data!");
    }*/

    public static void loadDynamicWorldgenData(RegistryAccess registryAccess) {
        loadPatchableEntities(registryAccess.asGetterLookup().lookup(Registries.ENTITY_TYPE));
        loadPatchableFeatures(registryAccess.asGetterLookup().lookup(Registries.PLACED_FEATURE));
        loadPatchableStructures(registryAccess.asGetterLookup().lookup(Registries.STRUCTURE));
        loadPatchableStructureSets(registryAccess.asGetterLookup().lookup(Registries.STRUCTURE_SET));
        loadPatchableBiomes(registryAccess.asGetterLookup().lookup(Registries.BIOME));
    }

    public static void loadDefaultWorldgenData() {
        loadPatchableEntities(Optional.empty());
        loadPatchableFeatures(Optional.empty());
        loadPatchableStructures(Optional.empty());
        loadPatchableStructureSets(Optional.empty());
        loadPatchableBiomes(Optional.empty());
    }

    /**
     * Will initialize all Patchable structure data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableStructures(Optional<HolderGetter<Structure>> registryLookup) {
        for (PEStructure struct_override : PEStructure.values()) {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(struct_override.location().toString())).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure override..." + t.id); }

                t.setData(struct_override);
            });
        }

        loadStructuresByDimension(registryLookup);
        loadStructuresByRegion(registryLookup);
        loadStructuresByRarity(registryLookup);
        loadStructuresByHeightmap();
        loadStructuresByFlag(registryLookup);
        loadStructuresBySize();
    }

    /**
     * Will initialize all Patchable structure set data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableStructureSets(Optional<HolderGetter<StructureSet>> registryLookup) {
        for (PEStructureSet sset : PEStructureSet.values()) {
            Optional.ofNullable(PATCHABLE_STRUCTURE_SETS.get(sset.location().toString())).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure set to itself..." + t.id); }
                t.setData(sset);
            });
        }

        PEStructureSet.Flag.DISABLED.initIDs(PEStructureSet.hasRedistributedStructures());
        PEStructureSet.Flag.DISABLED.initIDs(PEStructureSet.hasDisabledStructures());
        for (PEStructureSet.Flag flag : PEStructureSet.Flag.values()) {
            mapFlagToStructureSet(flag, registryLookup);
        }
    }

    /**
     * Will initialize all Patchable biome data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableBiomes(Optional<HolderGetter<Biome>> registryLookup) {
        for (PEBiome.Flag flag : PEBiome.Flag.values()) {
            mapFlagToBiome(flag, registryLookup);
        }
    }

    /**
     * Will initialize all Patchable entity data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableEntities(Optional<HolderGetter<EntityType<?>>> registryLookup) {
        loadMobsByBiomeClimate(registryLookup);
        loadMobsByFlag(registryLookup);
    }

    /**
     * Will initialize all Patchable placed_feature/biome_modifier data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableFeatures(Optional<HolderGetter<PlacedFeature>> registryLookup) {
        loadFeaturesByBiomeClimate(registryLookup);
        loadFeaturesByFlag(registryLookup);
    }

    public static void loadFeaturesByBiomeClimate(Optional<HolderGetter<PlacedFeature>> registryLookup) {
        for (PEBiome biome : PEBiome.values()) {
            mapBiomesToFeature(biome, registryLookup);
            mapBiomesToModifier(biome);
        }
    }

    public static void loadFeaturesByFlag(Optional<HolderGetter<PlacedFeature>> registryLookup) {
        for (PEFeature.Flag flag : PEFeature.Flag.values()) {
            mapFlagToFeature(flag, registryLookup);
        }
    }

    public static void loadMobsByBiomeClimate(Optional<HolderGetter<EntityType<?>>> registryLookup) {
        for (PEBiome biome : PEBiome.values()) {
            mapMobsToBiome(biome, registryLookup);
        }
    }

    public static void loadMobsByFlag(Optional<HolderGetter<EntityType<?>>> registryLookup) {
        for (PEMob.Flag flag : PEMob.Flag.values()) {
            mapFlagToMob(flag, registryLookup);
        }
    }

    public static void loadStructuresByDimension(Optional<HolderGetter<Structure>> registryLookup) {
        for (PEDimension dim : PEDimension.values()) {
            mapDimensionToStructure(dim, registryLookup);
        }
    }

    public static void loadStructuresByRegion(Optional<HolderGetter<Structure>> registryLookup) {
        for (PERegion region : PERegion.values()) {
            mapRegionToStructure(region, registryLookup);
        }
    }

    public static void loadStructuresByRarity(Optional<HolderGetter<Structure>> registryLookup) {
        for (PEStructureSet sset : PEStructureSet.values()) {
            mapRarityToStructure(sset, registryLookup);
        }
    }

    public static void loadStructuresByFlag(Optional<HolderGetter<Structure>> registryLookup) {
        PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS.initIDs(PEStructure.Heightmap.nonSurfaceStructures());
        PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK.initIDs(PEStructure.Flag.isBiasIgnored());
        for (PEStructure.Flag flag : PEStructure.Flag.values()) {
            mapFlagToStructure(flag, registryLookup);
        }

        PEStructure.Flag.IGNORED.initIDs(PEStructure.Flag.isIgnored());
        PEStructure.Flag.IGNORED_BIOME_REDISTRIBUTION.initIDs(PEStructure.Flag.IGNORED.defaultIDs());
        PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS.initIDs(PEStructure.Flag.IGNORED.defaultIDs());
        PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK.initIDs(PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS.defaultIDs());
        PEStructure.Flag.FLATNESS_CHECK_SPRAWLING.initIDs(PEStructure.Flag.isSprawlingFlat());
        PEStructure.Flag.FLATNESS_CHECK_LARGE.initIDs(PEStructure.Flag.isLargeFlat());
        PEStructure.Flag.FLATNESS_CHECK_SMALL.initIDs(PEStructure.Flag.isSmallFlat());
        //PEStructure.Flag.LATE_SPAWN_STEP.initIDs(PEStructure.Size.nonMassiveStructures());
        PEStructure.Flag.IS_CIVILIZATION.initIDs(PEStructure.Flag.isCivilization());
        PEStructure.Flag.IS_WILDERNESS.initIDs(PEStructure.Flag.isWilderness());
        for (PEStructure.Flag flag : PEStructure.Flag.values()) {
            mapFlagToStructure(flag, registryLookup);
        }
    }

    public static void loadStructuresByHeightmap() {
        PEStructure.Heightmap.OCEANSURFACE.initIDs(PEStructure.Heightmap.allOceanSurfaceStructures());
        PEStructure.Heightmap.GROUNDLEVEL.initIDs(PEStructure.Heightmap.allGroundLevelStructures());
        for (PEStructure.Heightmap heightmap : PEStructure.Heightmap.values()) {
            mapHeightmapToStructure(heightmap);
        }
    }

    private static void mapBiomesToFeature(PEBiome biome, Optional<HolderGetter<PlacedFeature>> registryLookup) {
        List<String> feature_ids = registryLookup
                .map(featureRegistryLookup ->
                        getTaggedData(featureRegistryLookup, biome.featureTag()))
                .orElseGet(biome::featureAdditions);
        feature_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_FEATURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping feature to biome climate..." + t.id); }
                if (!t.isModifier()) {
                    t.setBiomes(biome);
                }
            });
        });
    }

    private static void mapBiomesToModifier(PEBiome biome) {
//        List<String> feature_ids = registryLookup
//                .map(featureRegistryLookup ->
//                        getTaggedData(featureRegistryLookup, biome.featureTag()))
//                .orElseGet(biome::featureAdditions);
        biome.featureAdditions().forEach(s -> {
            Optional.ofNullable(PATCHABLE_FEATURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping biome modifier to biome climate..." + t.id); }
                if (t.isModifier()) {
                    t.setBiomes(biome);
                }
            });
        });
    }

    public static void mapFlagToFeature(PEFeature.Flag flag, Optional<HolderGetter<PlacedFeature>> registryLookup) {
        PEFeature.Flag.DISABLED.initIDs(PEFeature.Flag.disabledModifiers());
        //PEFeature.Flag.DISABLED.initIDs(PEBiome.redistributedFeatures());
        //PEFeature.Flag.DISABLED.initIDs(PEBiome.redistributedModifiers());
        List<String> features = registryLookup
                .map(featureRegistryLookup ->
                        getTaggedData(featureRegistryLookup, flag.tag()))
                .orElseGet(flag::defaultIDs);
        features.forEach(s -> {
            Optional.ofNullable(PATCHABLE_FEATURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping flag to feature..." + t.id); }
                t.appendFlag(flag);
            });
        });
    }

    private static void mapMobsToBiome(PEBiome biome, Optional<HolderGetter<EntityType<?>>> registryLookup) {
        HashSet<PatchableEntity> mobs = new HashSet<>();
        List<String> mob_ids = registryLookup
                .map(entityRegistryLookup ->
                        getTaggedData(entityRegistryLookup, biome.entityTag()))
                .orElseGet(biome::entityAdditions);
        ArrayList<PatchableBiome> biomes = biome.defaultBiomes().stream()
                .filter(e -> !(PATCHABLE_BIOMES.get(e) == null))
                .map(e -> PATCHABLE_BIOMES.get(e))
                .collect(Collectors.toCollection(ArrayList::new));

        mob_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_ENTITIES.get(s)).ifPresent(e -> {
                e.appendFlag(PEMob.Flag.IS_REDISTRIBUTED);
                mobs.add(e);
            });
        });
        biomes.forEach(b -> {
            if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping mob to biome climate..." + b.id); }
            b.setMobs(mobs);
        });
    }

    public static void mapFlagToMob(PEMob.Flag flag, Optional<HolderGetter<EntityType<?>>> registryLookup) {
        //PEMob.Flag.DISABLED.initIDs(PEMob.isRedistributed());
        PEMob.NON_WATER_CRITTER.initIDs(PEMob.allNonWaterMobs());
        PEMob.UNCIVILIZED_MONSTER.initIDs(PEMob.uncivilizedMonsters());
        List<String> mobs = registryLookup
                .map(entityRegistryLookup ->
                        getTaggedData(entityRegistryLookup, flag.tag()))
                .orElseGet(flag::defaultIDs);
        mobs.forEach(s -> {
            Optional.ofNullable(PATCHABLE_ENTITIES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping flag to mob..." + t.id); }
                t.appendFlag(flag);
            });
        });
    }

    public static void mapFlagToBiome(PEBiome.Flag flag, Optional<HolderGetter<Biome>> registryLookup) {
        List<String> biomes = registryLookup
                .map(biomeRegistryLookup ->
                        getTaggedData(biomeRegistryLookup, flag.tag()))
                .orElseGet(flag::defaultIDs);
        biomes.forEach(s -> {
            Optional.ofNullable(PATCHABLE_BIOMES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping flag to a biome..." + t.id); }
                t.appendFlag(flag);
            });
        });
    }

    public static void mapFlagToStructureSet(PEStructureSet.Flag flag, Optional<HolderGetter<StructureSet>> registryLookup) {
        List<String> structure_sets = registryLookup
                .map(ssetRegistryLookup ->
                        getTaggedData(ssetRegistryLookup, flag.tag()))
                .orElseGet(flag::defaultIDs);
        structure_sets.forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURE_SETS.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping flag to a structure set..." + t.id); }
                t.appendFlag(flag);
            });
        });
    }

    private static void mapDimensionToStructure(PEDimension dimension, Optional<HolderGetter<Structure>> registryLookup) {
        List<String> structure_ids = registryLookup
                .map(structureRegistryLookup ->
                        getTaggedData(structureRegistryLookup, dimension.structureTag()))
                .orElseGet(dimension::defaultStructures);
        structure_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure to a dimension..." + t.id); }
                t.setDimension(dimension);
            });
        });
    }

    private static void mapRegionToStructure(PERegion region, Optional<HolderGetter<Structure>> registryLookup) {
        List<String> structure_ids = registryLookup
                .map(structureRegistryLookup ->
                        getTaggedData(structureRegistryLookup, region.structureTag()))
                .orElseGet(region::defaultStructures);
        structure_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure to a region..." + t.id); }
                t.setRegion(region);
            });
        });
    }

    public static void mapRarityToStructure(PEStructureSet sset, Optional<HolderGetter<Structure>> registryLookup) {
        List<String> structure_ids = registryLookup
                .map(structureRegistryLookup ->
                        getTaggedData(structureRegistryLookup, sset.structureTag()))
                .orElseGet(sset::defaultStructures);
        structure_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure to a rarity set..." + t.id); }
                t.setStructureSet(sset);
            });
        });
    }

    public static void mapFlagToStructure(PEStructure.Flag flag, Optional<HolderGetter<Structure>> registryLookup) {
        List<String> structure_ids = registryLookup
                .map(structureRegistryLookup ->
                        getTaggedData(structureRegistryLookup, flag.tag()))
                .orElseGet(flag::defaultIDs);
        structure_ids.forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(s)).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping flag - " + flag.name() + " - to structure: " + t.id); }
                t.appendFlag(flag);
                //IS_DISABLED
                if(flag == PEStructure.Flag.DISABLED) { t.setRegion(PERegion.NO_BIOMES); }
            });
        });
    }

    private static void mapHeightmapToStructure(PEStructure.Heightmap heightmap) {
        heightmap.defaultIDs().forEach(s -> {
            Optional.ofNullable(PATCHABLE_STRUCTURES.get(s)).ifPresent(t -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Mapping heightmap - " + heightmap + " - to structure: " + t.id);
                }
                t.setHeightmap(heightmap);
            });
        });
    }

    public static void loadStructuresBySize() {

        Map<String, Size> sizeMap = Map.of(
            "DECO", Size.SMALL,
            "SMALL", Size.SMALL,
            "MEDIUM", Size.MEDIUM,
            "COMMON", Size.MEDIUM,
            "RARE", Size.LARGE,
            "LARGE", Size.LARGE,
            "SPRAWLING", Size.SPRAWLING
        );

        PATCHABLE_STRUCTURES.forEach((id, s) -> {
            Optional<PEStructure.Flag> flatnessFlag = s.getFlags().stream()
                    .filter(f -> f.name().contains("FLATNESS_CHECK"))
                    .findFirst();
            String sizeKey;
            if (flatnessFlag.isPresent()) {
                String flag = flatnessFlag.get().name();
                sizeKey = flag.substring(flag.lastIndexOf('_') + 1);
                s.setSize(sizeMap.get(sizeKey));
           /* } else if (s.getStructureSet().isPresent()) {
                String sset = s.getStructureSet().get().name();
                sizeKey = sset.substring(sset.lastIndexOf('_') + 1);
                s.setSize(sizeMap.get(sizeKey));*/
            } else {
                s.setSize(Size.SMALL);
            }
        });
    }

    private static <T> boolean isInTag(HolderLookup.RegistryLookup<T> registryLookup, ResourceKey<T> resourceKey, TagKey<T> tag) {
        return registryLookup.get(resourceKey).isPresent() && registryLookup.get(resourceKey).get().is(tag);
    }

    private static <T> List<String> getTaggedData(HolderGetter<T> registryLookup, TagKey<T> tag) {
        List<String> ids = new ArrayList<>();
        try {
            registryLookup.getOrThrow(tag).stream().map(Holder::unwrapKey).forEach(holder ->
            {
                holder.ifPresent((key) -> {
                    ids.add(key.location().toString());
                });
            });
        } catch (Exception e) {
            if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                ProjectEvergreen.LOGGER.error("Failed to load non-existent tag.");
            }
        }
        return ids;
    }
}
