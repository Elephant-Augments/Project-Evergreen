package com.elephantaugments.projectevergreen.neoforge.data;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.IPatchable;
import com.elephantaugments.projectevergreen.common.api.PEStructure;
import com.elephantaugments.projectevergreen.common.api.PatchableStructure;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.SortedSet;

public class RegistryReader {

    private final String registryPath;
    private final String registryName;

    public HolderLookup.RegistryLookup<Biome> BIOME_LOOKUP;
    public HolderGetter.Provider STRUCTURE_SET_LOOKUP;
    public HolderGetter.Provider STRUCTURE_LOOKUP;

    public RegistryReader(WritableRegistry<?> registry) {
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

    public <E> void readFromRegistry(ResourceKey<E> resourceKey, JsonObject json, SortedSet<String> loadedList) {
        String location = resourceKey.location().toString();
        logProgress(loadedList);
        loadedList.add(location);

        Optional<IPatchable> wdata;
        switch (registryPath) {
            case "worldgen/biome" -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_BIOMES.get(location));
            case "worldgen/structure_set" -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(location));
            case "worldgen/structure" -> {
                wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURES.get(location));
                wdata.ifPresent(p -> {
                    PatchableStructure structure = WorldgenDataManager.PATCHABLE_STRUCTURES.get(location);
                    JsonElement heightmap = json.get("project_start_to_heightmap");
                    String type = json.get("type").getAsString().toLowerCase();
                    String step = json.get("step").getAsString().toLowerCase();
                    structure.setType(type);
                    structure.setStep(step);
                    if (heightmap != null && (heightmap.getAsString().toLowerCase().contains("ocean_floor"))) {
                        structure.setHeightmap(PEStructure.Heightmap.OCEANFLOOR);
                        PEStructure.Heightmap.OCEANFLOOR.appendIDs(location);
                    }
                    if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds"))) {
                        structure.setHeightmap(PEStructure.Heightmap.UNDERGROUND);
                        PEStructure.Heightmap.UNDERGROUND.appendIDs(location);
                    }
                    WorldgenDataManager.setStructureData(location, structure);
                });
            }
            default -> wdata = Optional.empty();
        };
        wdata.ifPresent((p) -> {
            p.setLoaded(true);
        });
    }

    private <T> void logProgress(SortedSet<String> loadedList) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Adding " + this.registryName + " to " + this.registryName + "s list: " + loadedList.size());
        }
    }
}
