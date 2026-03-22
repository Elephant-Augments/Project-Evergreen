package com.elephantaugments.projectevergreen.neoforge.data;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
import java.util.SortedSet;

public class RegistryReader {

    private final String registryPath;
    private final String registryName;

    public HolderLookup.RegistryLookup<Biome> BIOME_LOOKUP;
    public HolderGetter.Provider STRUCTURE_SET_LOOKUP;
    public HolderGetter.Provider STRUCTURE_LOOKUP;

    public RegistryReader(WritableRegistry<?> registry) {
        ProjectEvergreen.LOGGER.info("Worldgen Registry Path: " + registry.key().location().getPath());
        this.registryPath = registry.key().location().getPath();
        this.registryName = registryPath.substring(registryPath.lastIndexOf('/') + 1);
        updateDefaultWorldgenData(registry);
    }

//    public void updateLoadedWorldgenData(RegistryAccess registryAccess) {
//        switch (registryPath) {
//            case "worldgen/biome" -> {
//                ProjectEvergreen.LOGGER.info("Getting biome: " + registryAccess.registry(Registries.BIOME.registryKey()).get().size());
//                //this.BIOME_LOOKUP = registryAccess.asGetterLookup().lookup(Registries.BIOME).toString();
//                ProjectEvergreenNeoforge.BIOME_REGISTRY = this;
//                ProjectEvergreenNeoforge.initBiolith();
//                //WorldgenDataManager.loadPatchableBiomes(BIOME_LOOKUP);
//            }
//            case "worldgen/structure_set" -> {
//                this.STRUCTURE_SET_LOOKUP = registryAccess.asGetterLookup();
//                ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY = this;
//                //WorldgenDataManager.loadPatchableStructureSets(STRUCTURE_SET_LOOKUP);
//            }
//            case "worldgen/structure" -> {
//                this.STRUCTURE_LOOKUP = registryAccess.asGetterLookup();
//                ProjectEvergreenNeoforge.STRUCTURE_REGISTRY = this;
//                //WorldgenDataManager.loadPatchableStructures(STRUCTURE_LOOKUP);
//            }
//        }
//    }

    public void updateDefaultWorldgenData(WritableRegistry<?> registry) {
        switch (registryPath) {
            case "worldgen/biome" -> WorldgenDataManager.loadPatchableBiomes(Optional.empty());
            case "worldgen/structure_set" -> WorldgenDataManager.loadPatchableStructureSets(Optional.empty());
            case "worldgen/structure" -> WorldgenDataManager.loadPatchableStructures(Optional.empty());
        }
    }

    public <E> void readFromDynamicRegistry(ResourceKey<E> resourceKey, JsonObject json, SortedSet<String> loadedList) {
        String location = resourceKey.location().toString();
        logProgress(loadedList);
        loadedList.add(location);

        Optional<IPatchable> wdata;
        switch (registryPath) {
            case "worldgen/biome" -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_BIOMES.get(location));
            case "worldgen/structure_set" -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(location));
            case "worldgen/placed_feature", "biome_modifier" -> {
                wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_FEATURES.get(location));
                wdata.ifPresent(p -> {
                    PatchableFeature feature = WorldgenDataManager.PATCHABLE_FEATURES.get(location);
                    feature.setRegistry(registryPath);
                    WorldgenDataManager.setFeatureData(location, feature);
                });
            }
            case "worldgen/structure" -> {
                wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURES.get(location));
                wdata.ifPresent(p -> {
                    PatchableStructure structure = WorldgenDataManager.PATCHABLE_STRUCTURES.get(location);
                    JsonElement heightmap = json.get("project_start_to_heightmap");
                    String type = json.get("type").getAsString().toLowerCase();
                    String step = json.get("step").getAsString().toLowerCase();
                    structure.initJsonData(type, step, heightmap);
                    WorldgenDataManager.setStructureData(location, structure);
                });
            }
            default -> wdata = Optional.empty();
        };
        wdata.ifPresent((p) -> {
            p.setLoaded(true);
        });
    }

    //TODO: Add switch statement for other registries
    public <T> void readFromStaticRegistry(DefaultedRegistry<T> registry, SortedSet<String> loadedList) {
        for (ResourceLocation id : registry.keySet()) {
            logProgress(loadedList);
            loadedList.add(id.toString());

//            Optional<IPatchable> wdata;
//            switch (registry) {
//                case BuiltInRegistries.ENTITY_TYPE ->
//                default -> wdata = Optional.empty();
//            }

            EntityType<?> e = BuiltInRegistries.ENTITY_TYPE.get(id);
            Optional<IPatchable> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_ENTITIES.get(id.toString()));
            wdata.ifPresent((p) -> {
                p.setLoaded(true);
                PatchableEntity entity = WorldgenDataManager.PATCHABLE_ENTITIES.get(id.toString());
                entity.setMobCategory(e.getCategory().name());
            });
        }
    }

    private <T> void logProgress(SortedSet<String> loadedList) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Adding " + this.registryName + " to " + this.registryName + "s list: " + loadedList.size());
        }
    }
}
