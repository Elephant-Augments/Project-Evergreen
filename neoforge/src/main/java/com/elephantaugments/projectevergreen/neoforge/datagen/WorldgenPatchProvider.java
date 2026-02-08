package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.data.patchable.*;
import com.google.gson.JsonParser;
import net.enderturret.patchedmod.data.PatchProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput.Target;

import java.util.*;

public class WorldgenPatchProvider extends PatchProvider {

    protected WorldgenPatchProvider(DataGenerator generator, Target target) {
        super(generator, target, ProjectEvergreen.MODID);
    }

    @Override
    public void registerPatches() {

        /*ProjectEvergreen.LOGGER.info("Patching all Biomes into their tags...");
        WorldgenDataManager.BIOMES_BY_TAG.keySet().forEach((tag) -> {
            String fromMod = ProjectEvergreen.MODID;
            String atPath = "tags/worldgen/biome/" + Constants.getPath(tag);
            List<? extends String> tagSet = WorldgenDataManager.BIOMES_BY_TAG.get(tag).stream().map(PatchableBiome::getId).collect(Collectors.toList());

            patch(id(fromMod, atPath))
                .compound()
                .add("/temp_tags", ProjectEvergreen.GSON.toJsonTree(tagSet))
                .paste("/values", DataSources.CONFIG_VALUE, "/temp_tags", JsonParser.parseString(Constants.BIOME_TAG))
                .remove("/temp_tags")
                .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.BIOMES_BY_TAG.size() + " Biome Tags");*/


        ProjectEvergreen.LOGGER.info("Patching all Processor Lists with fixes...");
        PatchableProcessorLists PROCESSOR_LISTS = WorldgenDataManager.PATCHABLE_PROCESSOR_LISTS;
        PROCESSOR_LISTS.getPaths().forEach((path) -> {
            String fromMod = Constants.getNamespace(path);
            String atPath = Constants.getPath(path);

            if (TestConditions.needsSignFix(fromMod)) {
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
                patch(id(fromMod, atPath))
                    .compound()
                        .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                        .replace("/structures", ProjectEvergreen.GSON.toJsonTree(new ArrayList()))
                    .end();
            }
            if (fromMod.contentEquals(ProjectEvergreen.MODID)) {
                patch(id(fromMod, atPath))
                //GET_PE_DATA_OBJECT
                    .compound()
                        //The first data object holds our default values, added during development when data is generated.
                        //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                        .add("/" + Constants.PROPERTIES_KEY, structureSet.toJson())
                        .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, Constants.JsonProp.ID.jsonPath(), JsonParser.parseString(structureSet.JSON_DATA_KEY))
                    .end()
                    .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                    .compound()
                        .add("/placement/spacing", structureSet.getSpacing())
                        .add("/placement/separation", structureSet.getSeparation())
                    .end()
                    /*.compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_MEDIUM.name(), false)
                        .add("/placement/spacing", 50)
                        .add("/placement/separation", 45)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_DECO.name(), false)
                        .add("/placement/spacing", 16)
                        .add("/placement/separation", 6)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_MASSIVE.name(), false)
                        .add("/placement/spacing", 60)
                        .add("/placement/separation", 50)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_MEDIUM.name(), false)
                        .add("/placement/spacing", 45)
                        .add("/placement/separation", 40)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_DECO.name(), false)
                        .add("/placement/spacing", 18)
                        .add("/placement/separation", 14)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.UNDERGROUND_SPRAWLING.name(), false)
                        .add("/placement/spacing", 32)
                        .add("/placement/separation", 24)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_FLOATING_MASSIVE.name(), false)
                        .add("/placement/spacing", 75)
                        .add("/placement/separation", 56)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_UNDERWATER_MASSIVE.name(), false)
                        .add("/placement/spacing", 65)
                        .add("/placement/separation", 45)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_ALL_MEDIUM.name(), false)
                        .add("/placement/spacing", 50)
                        .add("/placement/separation", 45)
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.SKY_MASSIVE.name(), false)
                        .add("/placement/spacing", 90)
                        .add("/placement/separation", 80)
                    .end()*/
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.CIVILIZATION_INLAND_MASSIVE.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.CIVILIZATION_MASSIVE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.CIVILIZATION_INLAND_MEDIUM.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_MEDIUM_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_MEDIUM_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.CIVILIZATION_MEDIUM))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.CIVILIZATION_INLAND_DECO.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_DECORATIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_DECORATIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.CIVILIZATION_DECO))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.WILDERNESS_INLAND_MASSIVE.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.WILDERNESS_MASSIVE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.WILDERNESS_INLAND_MEDIUM.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_MEDIUM_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_MEDIUM_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.WILDERNESS_MEDIUM))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.WILDERNESS_INLAND_DECO.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_DECORATIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_DECORATIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.WILDERNESS_DECO))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.UNDERGROUND_SPRAWLING.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.UNDERGROUND_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.UNDERGROUND_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.UNDERGROUND_MASSIVE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.OCEAN_FLOATING_MASSIVE.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.OCEAN_FLOATING_MASSIVE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.OCEAN_UNDERWATER_MASSIVE.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.OCEAN_UNDERWATER_MASSIVE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.OCEAN_ALL_MEDIUM.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MEDIUM_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.OCEAN_MEDIUM_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.OCEAN_ALL_MEDIUM))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.ID.jsonPath(), PEStructureSet.SKY_MASSIVE.name(), false)
                        .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(Constants.SKY_MASSIVE_RARITY))
                        .paste("/placement/separation", DataSources.CONFIG_VALUE, "/placement/separation", JsonParser.parseString(Constants.SKY_MASSIVE_RARITY))
                        .paste("/structures", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.SKY_MASSIVE))
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
                    //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                    .add("/" + Constants.PROPERTIES_KEY, structure.toJson())
                    .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, Constants.JsonProp.ID.jsonPath(), JsonParser.parseString(structure.JSON_DATA_KEY))
                .end()
            //BIOME_REDISTRIBUTION_PATCH
                .compound()
                    .test(Constants.STRUCTURE_BIOME_REDISTRIBUTION, true)
                    .test(PEStructure.Flag.IGNORED_BIOME_REDISTRIBUTION.jsonPath(), true, false)
                    .copy("/biomes", structure.getRegion().orElse(PERegion.NO_BIOMES).jsonPath())
                    //.paste("/biomes", DataSources.PE_VALUE, "/" + Constants.ID_TAG, JsonParser.parseString("get_biome"))
                    //.add("/biomes", getBiomeTag(id)) //Used to check needed mod support
                .end()
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
                    .test(Constants.JsonProp.IS_INLAND.jsonPath(), true, false)
                    .add("/cannot_spawn_in_liquid", true)
                .end()
            //ADD_TERRAIN_ADAPTATION_PATCH
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_TERRAIN_ADAPTATION.jsonPath(), true, false)
                    .add("/terrain_adaptation", "beard_thin")
                .end()
            //BIOME_RADIUS_PATCH
                .compound()
                    .test(Constants.PERFORMANCE_MODE_CONFIG, false)
                    .compound()
                        .test(Constants.JsonProp.IS_WATER_BOUND.jsonPath(), true, false)
                        .test(Constants.JsonProp.IS_MASSIVE.jsonPath(), true, false)
                        .add("/valid_biome_radius_check", 2)
                    .end()
                    .compound()
                        .test(Constants.JsonProp.IS_MASSIVE.jsonPath(), true, false)
                        .test(Constants.JsonProp.IS_RADIUS_BOUND.jsonPath(), true, false)
                        .add("/valid_biome_radius_check", 2)
                    .end()
                    .compound()
                        .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                        .test(Constants.POPULATION_BIAS_TEST, id)
                        .paste("/valid_biome_radius_check", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.POPULATION_BIAS))
                    .end()
                .end()
            //OCEANFLOOR_HEIGHTMAP_FIX
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_OCEAN_HEIGHTMAP.jsonPath(), true, false)
                    .add("/project_start_to_heightmap", "OCEAN_FLOOR_WG")
                .end()
            //FLATNESS_CHECK_PATCH
                .compound()
                    .test(Constants.PERFORMANCE_MODE_CONFIG, false)
                    .compound()
                        .test(Constants.JsonProp.IS_SMALL.jsonPath(), true, false)
                        .add("/terrain_height_radius_check", 1)
                        .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_NARROW))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.IS_MEDIUM.jsonPath(), true, false)
                        .add("/terrain_height_radius_check", 2)
                        .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_WIDE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.IS_LARGE.jsonPath(), true, false)
                        .add("/terrain_height_radius_check", 3)
                        .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_WIDE))
                    .end()
                    .compound()
                        .test(Constants.JsonProp.IS_SPRAWLING.jsonPath(), true, false)
                        .add("/terrain_height_radius_check", 5)
                        .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_SPRAWLING))
                    .end()
                .end()
            .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURES.size() + " Structures");


        /*ProjectEvergreen.LOGGER.info("Patching all Biomes with new features...");
        BIOMES.Data.forEach((id, biome) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(biome.full_path);

            patch(id(fromMod, atPath))
                .compound()
                    .test("/effects/water_color", null, false)
                    .replace("/effects/water_color", ProjectEvergreen.GSON.toJsonTree(3570859))
                .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + STRUCTURES.size() + " Structures");*/
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
}
