package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEStructureSet;
import com.elephantaugments.projectevergreen.common.api.PatchableBiome;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.data.defaults.*;
import com.elephantaugments.projectevergreen.common.data.patchable.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.enderturret.patchedmod.data.PatchProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput.Target;

import java.util.*;
import java.util.stream.Collectors;

public class StructurePatchProvider extends PatchProvider {

    protected StructurePatchProvider(DataGenerator generator, Target target) {
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
                .paste("/values", id(ProjectEvergreen.MODID, "config_value"), "/temp_tags", JsonParser.parseString(Constants.BIOME_TAG))
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

            if (fromMod.contentEquals(ProjectEvergreen.MODID)) {
                patch(id(fromMod, atPath))
                    .compound()
                    .add("/" + Constants.ID_TAG, id)
                    .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_MASSIVE.name(), false)
                        .add("/placement/spacing", 65)
                        .add("/placement/separation", 55)
                    .end()
                    .compound()
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
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_MASSIVE.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.CIVILIZATION_MASSIVE))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_MEDIUM.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_MEDIUM_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_MEDIUM_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.CIVILIZATION_MEDIUM))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.CIVILIZATION_INLAND_DECO.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.CIVILIZATION_DECORATIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.CIVILIZATION_DECORATIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.CIVILIZATION_DECO))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_MASSIVE.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.WILDERNESS_MASSIVE))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_MEDIUM.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_MEDIUM_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_MEDIUM_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.WILDERNESS_MEDIUM))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.WILDERNESS_INLAND_DECO.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.WILDERNESS_DECORATIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.WILDERNESS_DECORATIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.WILDERNESS_DECO))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.UNDERGROUND_SPRAWLING.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.UNDERGROUND_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.UNDERGROUND_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.UNDERGROUND_MASSIVE))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_FLOATING_MASSIVE.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.OCEAN_FLOATING_MASSIVE))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_UNDERWATER_MASSIVE.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.OCEAN_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.OCEAN_UNDERWATER_MASSIVE))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.OCEAN_ALL_MEDIUM.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.OCEAN_MEDIUM_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.OCEAN_MEDIUM_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.OCEAN_ALL_MEDIUM))
                    .end()
                    .compound()
                        .test("/" + Constants.ID_TAG, PEStructureSet.SKY_MASSIVE.name(), false)
                        .paste("/placement/spacing", id(ProjectEvergreen.MODID, "config_value"), "/placement/spacing", JsonParser.parseString(Constants.SKY_MASSIVE_RARITY))
                        .paste("/placement/separation", id(ProjectEvergreen.MODID, "config_value"), "/placement/separation", JsonParser.parseString(Constants.SKY_MASSIVE_RARITY))
                        .paste("/structures", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.SKY_MASSIVE))
                    .end()
                    .end();
            }

            //This is to account for both individual + grouped structure sets
            //if (STRUCTURES_BY_RARITY.values().contains(id) || DefaultStructureFixes.conditionalDisable.contains(id)) {
            patch(id(fromMod, atPath))
                    .compound()
                    .add("/" + Constants.ID_TAG, id)
                    .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                    .replace("/structures", ProjectEvergreen.GSON.toJsonTree(new ArrayList()))
                    .end();
            //}
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.size() + " Structure Sets");


        ProjectEvergreen.LOGGER.info("Patching all Structures into new biomes...");
        WorldgenDataManager.PATCHABLE_STRUCTURES.forEach((id, structure) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(structure.full_path);

            patch(id(fromMod, atPath))
                .compound()
                .add("/" + Constants.ID_TAG, id)
                .compound()
                    .test(Constants.STRUCTURE_BIOME_REDISTRIBUTION, true)
                    .test(Constants.IGNORE_BIOME_REDISTRIBUTION_TEST, null, id, true)
                    .paste("/biomes", id(ProjectEvergreen.MODID, "config_value"), "/" + Constants.ID_TAG, JsonParser.parseString("get_biome"))
                    //.add("/biomes", getBiomeTag(id)) //Used to check needed mod support
                .end()
                .compound()
                    .test(Constants.ADVANCED_STRUCTURE_TYPE, "/type", id, false)
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
                /*.compound()
                    .test(Constants.SAFE_STRUCTURE_TYPE, id)
                    .include("structures/fixes/safe_structure_type")
                .end()*/
                .compound()
                    .test(Constants.INLAND_STRUCTURE_TEST, id)
                    .add("/cannot_spawn_in_liquid", true)
                .end()
                .compound()
                    .test(Constants.TERRAIN_ADAPTATION_TEST, id)
                    .add("/terrain_adaptation", "beard_thin")
                .end()
                .compound()
                    .test(Constants.WATER_STRUCTURE_TEST, id)
                    .test(Constants.MASSIVE_STRUCTURE_TEST, id)
                    .add("/valid_biome_radius_check", 2)
                .end()
                .compound()
                    .test(Constants.MASSIVE_STRUCTURE_TEST, id)
                    .test(Constants.IGNORE_BIOME_RADIUS_TEST, null, id, true)
                    .add("/valid_biome_radius_check", 1)
                .end()
                .compound()
                    .test(Constants.STRUCTURE_RARITY_REDISTRIBUTION, true)
                    .test(Constants.POPULATION_BIAS_TEST, id)
                    .paste("/valid_biome_radius_check", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.POPULATION_BIAS))
                .end()
                .compound()
                    .test(Constants.PERFORMANCE_MODE_CONFIG, false)
                    .test(Constants.FLAT_NARROW_TEST, id)
                    .add("/terrain_height_radius_check", 1)
                    .paste("/allowed_terrain_height_range", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_NARROW))
                .end()
                .compound()
                    .test(Constants.PERFORMANCE_MODE_CONFIG, false)
                    .test(Constants.FLAT_WIDE_TEST, id)
                    .add("/terrain_height_radius_check", 2)
                    .paste("/allowed_terrain_height_range", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_WIDE))
                .end()
                .compound()
                    .test(Constants.PERFORMANCE_MODE_CONFIG, false)
                    .test(Constants.IGNORE_BIOME_RADIUS_TEST, null, id, true)
                    .test(Constants.FLAT_SPRAWLING_TEST, id)
                    .add("/terrain_height_radius_check", 4)
                    .paste("/allowed_terrain_height_range", id(ProjectEvergreen.MODID, "config_value"), JsonParser.parseString(Constants.ALLOWED_TERRAIN_HEIGHT_SPRAWLING))
                .end()
                .end();
            /*if (id.contentEquals("ars_additions:nexus_tower")) {
                patch(id(fromMod, atPath))
                .compound()
                    .add("/biomes", ProjectEvergreen.GSON.toJsonTree("#project_evergreen:all_biomes"))
                    .include("structures/fixes/normalize_structure_type")
                    .include("structures/fixes/integrated_api_terrain_restriction_narrow")
                    .add("/cannot_spawn_in_liquid", ProjectEvergreen.GSON.toJsonTree(true))
                .end();
            }*/
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
