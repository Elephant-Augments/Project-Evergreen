package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableBiomes;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableProcessorLists;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructureSets;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Size;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;


import java.util.*;
import java.util.stream.Stream;

public final class WorldgenDataManager {
    private static final List<String> EMPTY_LIST = new ArrayList<>();

    public static LinkedHashMap<String, PatchableBiome> PATCHABLE_BIOMES = new PatchableBiomes().Data;
    public static LinkedHashMap<String, PatchableStructure> PATCHABLE_STRUCTURES = new PatchableStructures().Data;
    public static LinkedHashMap<String, PatchableStructureSet> PATCHABLE_STRUCTURE_SETS = new PatchableStructureSets().Data;
    public static PatchableProcessorLists PATCHABLE_PROCESSOR_LISTS = new PatchableProcessorLists();

//    public static LinkedHashMap<String, PERegion_OLD> REGIONS_BY_TAG;
//    public static LinkedHashMap<String, PatchableBiome> BIOMES_BY_ID;
//    public static ArrayListMultimap<String, PatchableStructure> STRUCTURES_BY_REGION = ArrayListMultimap.create();
//
//    public static ArrayListMultimap<String, PatchableBiome> BIOMES_BY_TAG;
//    public static LinkedHashMap<String, PatchableStructureSet> STRUCTURE_SETS_BY_ID;
//    public static ArrayListMultimap<String, PatchableStructure> STRUCTURES_BY_TAG;
//    public static LinkedHashMap<String, PatchableStructure> STRUCTURES_BY_ID;
//    public static ArrayListMultimap<Size, PatchableStructure> STRUCTURES_BY_SIZE;
//    public static ArrayListMultimap<Dimension, PatchableStructure> STRUCTURES_BY_DIM;

    public static SortedSet<String> loadedBiomes = new TreeSet<>();
    public static SortedSet<String> loadedBiomeModifiers = new TreeSet<>();
    public static SortedSet<String> loadedProcessorLists= new TreeSet<>();
    public static SortedSet<String> loadedLootTables = new TreeSet<>();
    public static SortedSet<String> loadedEntities= new TreeSet<>();
    public static SortedSet<String> loadedTemplatePools = new TreeSet<>();
    public static SortedSet<String> loadedStructureSets = new TreeSet<>();
    public static SortedSet<String> loadedStructures = new TreeSet<>();
    public static SortedSet<String> undergroundStructures = new TreeSet<>();
    public static SortedSet<String> flatStructures = new TreeSet<>();

    public static void setBiomeData(String id, PatchableBiome biome) {
        PATCHABLE_BIOMES.replace(id, biome);
    }

    public static void setStructureSetData(String id, PatchableStructureSet structSet) {
        PATCHABLE_STRUCTURE_SETS.replace(id, structSet);
    }

    public static void setStructureData(String id, PatchableStructure structure) {
        PATCHABLE_STRUCTURES.replace(id, structure);
    }

    public static void loadDefaultWorldgenData() {
        loadPatchableStructures(Optional.empty());
        loadPatchableStructureSets(Optional.empty());
        loadPatchableBiomes(Optional.empty());

        ProjectEvergreen.LOGGER.info("Successfully mapped all worldgen data objects!");
    }

    public static void loadDynamicWorldgenData(HolderLookup.Provider registryLookup) {
        ProjectEvergreen.LOGGER.info("Loading dynamic worldgen data...");

        loadPatchableStructures(registryLookup.lookup(Registries.STRUCTURE));
        loadPatchableStructureSets(registryLookup.lookup(Registries.STRUCTURE_SET));
        loadPatchableBiomes(registryLookup.lookup(Registries.BIOME));

        ProjectEvergreen.LOGGER.info("Successfully mapped all worldgen data objects!");
    }

    /**
     * Will initialize all Patchable structure data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableStructures(Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        loadStructuresByDimension(registryLookup);
        loadStructuresByRegion(registryLookup);
        loadStructuresByRarity(registryLookup);
        loadStructuresByFlag(registryLookup);
        loadStructuresByHeightmap();
        loadStructuresBySize();
    }

    /**
     * Will initialize all Patchable structure set data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableStructureSets(Optional<HolderLookup.RegistryLookup<StructureSet>> registryLookup) {
        for (PEStructureSet sset : PEStructureSet.values()) {
            Optional.ofNullable(PATCHABLE_STRUCTURE_SETS.get(sset.location().toString())).ifPresent(t -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Mapping structure set to itself..." + t.id); }
                t.setData(sset);
            });
        }
        for (PEStructureSet.Flag flag : PEStructureSet.Flag.values()) {
            mapFlagToStructureSet(flag, registryLookup);
        }
    }

    public static void mapFlagToStructureSet(PEStructureSet.Flag flag, Optional<HolderLookup.RegistryLookup<StructureSet>> registryLookup) {
        PEStructureSet.Flag.DISABLED.initIDs(PEStructureSet.isRedistributed());
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

    /**
     * Will initialize all Patchable structure set data passed in via tags if a registry lookup is provided,
     * otherwise initializes our default data.
     * @param registryLookup - Optional registry lookup
     */
    public static void loadPatchableBiomes(Optional<HolderLookup.RegistryLookup<Biome>> registryLookup) {
        for (PEBiome.Flag flag : PEBiome.Flag.values()) {
            mapFlagToBiome(flag, registryLookup);
        }
    }

    public static void mapFlagToBiome(PEBiome.Flag flag, Optional<HolderLookup.RegistryLookup<Biome>> registryLookup) {
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

    public static void loadStructuresByDimension(Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        for (PEDimension dim : PEDimension.values()) {
            mapDimensionToStructure(dim, registryLookup);
        }
    }

    private static void mapDimensionToStructure(PEDimension dimension, Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
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

    public static void loadStructuresByRegion(Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        for (PERegion region : PERegion.values()) {
            mapRegionToStructure(region, registryLookup);
        }
    }

    private static void mapRegionToStructure(PERegion region, Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
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

    public static void loadStructuresByRarity(Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        for (PEStructureSet sset : PEStructureSet.values()) {
            mapRarityToStructure(sset, registryLookup);
        }
    }

    public static void mapRarityToStructure(PEStructureSet sset, Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
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

    public static void loadStructuresByFlag(Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        for (PEStructure.Flag flag : PEStructure.Flag.values()) {
            mapFlagToStructure(flag, registryLookup);
        }
    }

    public static void mapFlagToStructure(PEStructure.Flag flag, Optional<HolderLookup.RegistryLookup<Structure>> registryLookup) {
        PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK.initIDs(PEStructure.Flag.isBiasIgnored());
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
        //IS_IGNORED
        Stream<PatchableStructure> ignored = PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getDimension().isEmpty());
        ignored.forEach(s -> {
            PEStructure.Flag.IGNORED.appendIDs(s.id);
            s.appendFlag(PEStructure.Flag.IGNORED);
        });
    }

    public static void loadStructuresByHeightmap() {
        PEStructure.Heightmap.OCEANSURFACE.initIDs(PEStructure.Heightmap.allOceanSurfaceStructures());
        PEStructure.Heightmap.GROUNDLEVEL.initIDs(PEStructure.Heightmap.allGroundLevelStructures());
        for (PEStructure.Heightmap heightmap : PEStructure.Heightmap.values()) {
            mapHeightmapToStructure(heightmap);
        }
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
            "MASSIVE", Size.LARGE,
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
            } else if (s.getStructureSet().isPresent()) {
                String sset = s.getStructureSet().get().name();
                sizeKey = sset.substring(sset.lastIndexOf('_') + 1);
                s.setSize(sizeMap.get(sizeKey));
            } else {
                s.setSize(Size.MEDIUM);
            }
        });
    }

    private static <T> boolean isInTag(HolderLookup.RegistryLookup<T> registryLookup, ResourceKey<T> resourceKey, TagKey<T> tag) {
        return registryLookup.get(resourceKey).isPresent() && registryLookup.get(resourceKey).get().is(tag);
    }

    private static <T> List<String> getTaggedData(HolderLookup.RegistryLookup<T> registryLookup, TagKey<T> tag) {
         return registryLookup.get(tag).stream().map(t -> t.key().location().toString()).toList();
    }
}
