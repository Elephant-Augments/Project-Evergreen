package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.data.patchable.*;
import com.elephantaugments.projectevergreen.neoforge.config.PEConfig;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonParser;
import net.enderturret.patchedmod.data.PatchProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput.Target;

import javax.xml.crypto.Data;
import java.util.*;

public class WorldgenPatchProvider extends PatchProvider {

    protected WorldgenPatchProvider(DataGenerator generator, Target target) {
        super(generator, target, ProjectEvergreen.MODID);
    }

    @Override
    public void registerPatches() {

        ProjectEvergreen.LOGGER.info("Patching all Biomes with new features...");
        WorldgenDataManager.PATCHABLE_BIOMES.forEach((id, biome) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(biome.full_path);

            patch(id(fromMod, atPath))
                .compound()
                    .compound()
                        //The first data object holds our default values, added during development when data is generated.
                        //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                        .add("/" + Constants.PROPERTIES_KEY, biome.toJson())
                    .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_BIOME_KEY))
                    .end()
                //WATER_COLOR_NORMALIZATION_PATCH
                    .compound()
                        .test(PEConfig.TEMPERATE_CLIMATE_WATER_NORMALIZATION_TEST, true)
                        .test(PEBiome.Flag.IS_TEMPERATE.jsonPath(), null, false)
                        .paste("/effects/water_color", DataSources.CONFIG_VALUE, JsonParser.parseString(PEConfig.TEMPERATE_WATER_COLOR_KEY))
                    .end()
                    .compound()
                        .test(PEConfig.WARM_CLIMATE_WATER_NORMALIZATION_TEST, true)
                        .test(PEBiome.Flag.IS_WARM.jsonPath(), null, false)
                        .paste("/effects/water_color", DataSources.CONFIG_VALUE, JsonParser.parseString(PEConfig.TEMPERATE_WATER_COLOR_KEY))
                    .end()
                .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_BIOMES.size() + " Biome Tags");


        ProjectEvergreen.LOGGER.info("Patching all Processor Lists with fixes...");
        PatchableProcessorLists PROCESSOR_LISTS = WorldgenDataManager.PATCHABLE_PROCESSOR_LISTS;
        PROCESSOR_LISTS.getPaths().forEach((path) -> {
            String fromMod = Constants.getNamespace(path);
            String atPath = Constants.getPath(path);

            if (needsSignFix(fromMod)) {
                patch(id(fromMod, atPath))
                    .include("add_sign_fix");
            }
        });
        ProjectEvergreen.LOGGER.info("Patched " + PROCESSOR_LISTS.getPaths().size() + " Processor Lists");


        ProjectEvergreen.LOGGER.info("Patching all Structure Sets with new structures...");
        WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.forEach((id, structureSet) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(structureSet.full_path);

            //This flag accounts for both individual + grouped structure sets that need to be disabled
            if (structureSet.getFlags().contains(PEStructureSet.Flag.DISABLED)) {
            //CONDITIONAL_DISABLE_PATCH
                patch(id(fromMod, atPath))
                    .compound()
                        .test(PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_TEST, true)
                        .replace("/structures", ProjectEvergreen.GSON.toJsonTree(new ArrayList()))
                    .end();
            } else {
            //UNIQUE_SALT_PATCH
                patch(id(fromMod, atPath))
                    .compound()
                        .replace("/placement/salt", structureSet.getSalt())
                    .end();
            }
            if (fromMod.contentEquals(ProjectEvergreen.MODID)) {
                patch(id(fromMod, atPath))
                //GET_PE_DATA_OBJECT
                .compound()
                    .compound()
                        //The first data object holds our default values, added during development when data is generated.
                        //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                        .add("/" + Constants.PROPERTIES_KEY, structureSet.toJson())
                        .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_STRUCTURE_SET_KEY))
                    .end()
                //UNIQUE_SALT_PATCH
                    .replace("/placement/salt", structureSet.getSalt())
                //RARITY_REDISTRIBUTION_PATCH
                    .test(PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_TEST, true)
                    .compound()
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, Constants.JsonProp.ID.jsonPath(), JsonParser.parseString(PEConfig.SPACING_RARITY_KEY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, Constants.JsonProp.ID.jsonPath(), JsonParser.parseString(PEConfig.SEPARATION_RARITY_KEY))
                        .paste("/structures", DataSources.PE_OBJECT, Constants.JsonProp.ID.jsonPath(), JsonParser.parseString(Constants.DYNAMIC_STRUCTURE_SET_KEY))
                    .end()
                .end();
            }
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.size() + " Structure Sets");


        ProjectEvergreen.LOGGER.info("Patching all Structures into new biomes...");
        WorldgenDataManager.PATCHABLE_STRUCTURES.forEach((id, structure) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(structure.full_path);

            patch(id(fromMod, atPath))
            .compound()
            //GET_PE_DATA_OBJECT
                .compound()
                    //The first data object holds our default values, added during development when data is generated.
                    //It's appended with values pulled directly from the json files by Patched at runtime.
                    .add("/" + Constants.PROPERTIES_KEY, structure.toJson())
                    .compound()
                        .copy(Constants.JsonProp.TYPE.jsonPath(), "/type")
                        .copy(Constants.JsonProp.STEP.jsonPath(), "/step")
                        .test("/project_start_to_heightmap", null, false)
                        .copy(Constants.JsonProp.HEIGHTMAP.jsonPath(), "/project_start_to_heightmap")
                    .end()
                    .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_STRUCTURE_KEY))
                .end()
            //BIOME_REDISTRIBUTION_PATCH
                .compound()
                    .test(PEConfig.STRUCTURE_BIOME_REDISTRIBUTION_TEST, true)
                    .test(PEStructure.Flag.IGNORED_BIOME_REDISTRIBUTION.jsonPath(), null, true)
                    .compound()
                        .test(Constants.JsonProp.DIMENSION.jsonPath(), null, false)
                        .copy("/biomes", Constants.JsonProp.DIMENSION.jsonPath())
                    .end()
                    .compound()
                        .test(Constants.JsonProp.REGION.jsonPath(), null, false)
                        .copy("/biomes", structure.getRegion().orElse(PERegion.NO_BIOMES).jsonPath())
                    .end()
                .end()
            //TERRAIN_ADAPTATION_FIX
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_TERRAIN_ADAPTATION.jsonPath(), true, false)
                    .add("/terrain_adaptation", "beard_thin")
                .end()
            //OCEANFLOOR_HEIGHTMAP_FIX
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_OCEAN_HEIGHTMAP.jsonPath(), true, false)
                    .add("/project_start_to_heightmap", "OCEAN_FLOOR_WG")
                    .add("/start_height", DataSources.getStartHeight(-3))
                .end()
                .test(PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS.jsonPath(), null, true)
            //ADVANCED_STRUCTURE_TYPE_PATCH
                .compound()
                    .test(Constants.JsonProp.IS_ADVANCED_TYPE.jsonPath(), true, false)
                    .compound()
                        .test("patched:mod_loaded", "repurposed_structures")
                        .add("/type", "repurposed_structures:generic_jigsaw_structure")
                    .end()
                    .compound()
                        .test("patched:mod_loaded", "mvs")
                        .add("/type", "moogs_structures:moogs_structures_generic_jigsaw_structure")
                    .end()
                    .compound()
                        .test("patched:mod_loaded", "integrated_api")
                        .add("/type", "integrated_api:generic_structure")
                    .end()
                .end()
            //CANNOT_SPAWN_IN_LIQUID_PATCH
                .compound()
                    .test(Constants.JsonProp.IS_WATER_RESTRICTED.jsonPath(), true, false)
                    .add("/cannot_spawn_in_liquid", true)
                .end()
                .test(PEConfig.PERFORMANCE_MODE_TEST, false)
            //BIOME_RADIUS_PATCH
                .compound()
                    .test(PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK.jsonPath(), null, true)
                    .test(Constants.JsonProp.IS_RADIUS_BOUND.jsonPath(), true, false)
                    .add("/valid_biome_radius_check", 1)
                    .compound()
                        .test(Constants.JsonProp.SIZE.jsonPath(), PEStructure.Size.SPRAWLING.name(), false)
                        .add("/valid_biome_radius_check", 2)
                    .end()
                    .compound()
                        .test(PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_TEST, true)
                        .test(PEConfig.POPULATION_BIAS_TEST, id)
                        .paste("/valid_biome_radius_check", DataSources.CONFIG_VALUE, JsonParser.parseString(PEConfig.POPULATION_BIAS_KEY))
                    .end()
                .end()
            //FLATNESS_CHECK_PATCH
                .compound()
                    .test(Constants.JsonProp.IS_FLAT.jsonPath(), true, false)
                    .add("/terrain_height_radius_check", structure.getSize().get().flatnessRadius())
                    .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, structure.getSize().get().jsonPath(), JsonParser.parseString(PEConfig.ALLOWED_TERRAIN_HEIGHT_KEY))
                .end()
            .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURES.size() + " Structures");
    }

    /*public static JsonElement getBiomeTag(String structureID) {
        Optional<String> biomeTag = WorldgenDataManager.PATCHABLE_STRUCTURES.entries().stream()
                        .filter(e -> structureID.equals(e.getValue().id))
                        .map(Map.Entry::getKey)
                        .sorted(Comparator.reverseOrder())
                        .findFirst();
        return biomeTag.isEmpty() ? 
            ProjectEvergreen.GSON.toJsonTree(DefaultRegions.NO_BIOMES) :
            ProjectEvergreen.GSON.toJsonTree(biomeTag.get());
    }*/

    public static boolean needsSignFix(String mod_id) {
        List<String> modsWithBrokenSigns = ImmutableList.of(
                "minecraft",
                "create_pillagers_arise",
                "mostructures",
                "nordic_structures",
                "u_sea",
                "u_desert"
        );
        return modsWithBrokenSigns.contains(mod_id);
    }
}
