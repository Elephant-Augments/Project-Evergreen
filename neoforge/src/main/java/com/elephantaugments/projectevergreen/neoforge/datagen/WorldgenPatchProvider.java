package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.patchable.*;
import com.elephantaugments.projectevergreen.neoforge.config.PEConfig;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
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

    //<-----------------------------------------------------BIOMES----------------------------------------------------->
        ProjectEvergreen.LOGGER.info("Patching all Biomes...");
        WorldgenDataManager.PATCHABLE_BIOMES.forEach((id, biome) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(biome.full_path);

            patch(id(fromMod, atPath))
                .compound()
                    .compound()
                        //The first data object holds our default values, added during development when data is generated.
                        //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                        .add("/" + Constants.PROPERTIES_KEY, biome.toJson())
                        .compound()
                            .copy(Constants.JsonProp.SPAWNERS.jsonPath(), "/spawners")
                        .end()
                        .paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_BIOME_KEY))
                    .end()
                //SPAWN_NORMALIZATION_PATCH
                    .compound()
                        .test(PEConfig.MOB_REDISTRIBUTION_TEST, true)
                        .test(PEBiome.Flag.HAS_SPAWN_OVERRIDES.jsonPath(), true, false)
                        //TODO: Fix overspawning mobs
                        .move("/spawners", Constants.JsonProp.SPAWNERS.jsonPath())
                        //.move("/spawn_costs", Constants.JsonProp.SPAWN_COSTS.jsonPath())
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
                //REMOVE_SNOW_ACCUMULATION_PATCH
                    .compound()
                        .test(PEBiome.Flag.REMOVE_SNOW.jsonPath(), true, false)
                        .add("/temperature", 0.5)
                    .end()
                //REMOVE_VEGETATION_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.REMOVE_VEGETATION_COLOR.jsonPath(), true, false)
                        .remove("/effects/foliage_color")
                        .remove("/effects/grass_color")
                    .end()
                //ADD_MEADOW_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_MEADOW_BLUE.jsonPath(), true, false)
                        .add("/effects/foliage_color", 7374422)
                        .remove("/effects/grass_color")
                        .add("/temperature", 0.5)
                        .add("/downfall", 0.8)
                    .end()
                //ADD_PRAIRIE_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_PRAIRIE_YELLOW.jsonPath(), true, false)
                        .add("/effects/grass_color", 15259000)
                    .end()
                //ADD_STEPPE_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_STEPPE_BROWN.jsonPath(), true, false)
                        .add("/effects/grass_color", -5067675)
                    .end()
                //ADD_MARSH_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_MARSH_GREEN.jsonPath(), true, false)
                        .add("/effects/grass_color", 7574355)
                        .add("/effects/foliage_color", 4347179)
                    .end()
                //ADD_BAYOU_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_BAYOU_BLUE.jsonPath(), true, false)
                        .add("/effects/grass_color", 7574355)
                        .add("/effects/foliage_color", 7441446)
                        .add("/temperature", 1.2)
                        .add("/downfall", 0.2)
                    .end()
                //ADD_COOL_PLAINS_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_COOL_GREEN.jsonPath(), true, false)
                        .remove("/effects/grass_color")
                        .remove("/effects/foliage_color")
                        .add("/temperature", 0.65)
                        .add("/downfall", 0.3)
                        //.add("/temperature", 0.7)
                        //.add("/downfall", 0)
                    .end()
                //ADD_PLAINS_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_PLAINS_GREEN.jsonPath(), true, false)
                        .add("/temperature", 1.2)
                        .add("/downfall", 0.2)
                        .remove("/effects/grass_color")
                        .remove("/effects/foliage_color")
                    .end()
                //ADD_SAVANNA_COLORING_PATCH
                    .compound()
                        .test(PEBiome.Flag.IS_SAVANNA_BROWN.jsonPath(), true, false)
                        .add("/effects/grass_color", 15259000)
                        .remove("/effects/foliage_color")
                        .add("/temperature", 2)
                        .add("/downfall", 0)
                    .end()
                .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_BIOMES.size() + " Biome Tags");


    //<-----------------------------------------------PROCESSOR LISTS------------------------------------------------>
        ProjectEvergreen.LOGGER.info("Patching all Processor Lists...");
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


    //<-----------------------------------------------LOOT TABLES------------------------------------------------>
    /*This doesn't plug into our custom data system yet, just an array of string ids.
        ProjectEvergreen.LOGGER.info("Patching all Loot Tables...");
        PatchableLootTables.defaultSupported.forEach((id) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(id.replace(":", ":" + PatchableLootTables.REGISTRY_PATH));

            patch(id(fromMod, atPath))
                .replace("/pools", ProjectEvergreen.GSON.toJsonTree(new ArrayList<>()));
        });
        ProjectEvergreen.LOGGER.info("Patched " + PROCESSOR_LISTS.getPaths().size() + " Processor Lists");
     */

    //<------------------------------------------------STRUCTURE SETS------------------------------------------------>
        ProjectEvergreen.LOGGER.info("Patching all Structure Sets...");
        WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.forEach((id, structureSet) -> {
            String fromMod = Constants.getNamespace(id);
            String atPath = Constants.getPath(structureSet.full_path);

            //This flag accounts for both individual + grouped structure sets that need to be disabled
            if (structureSet.getFlags().contains(PEStructureSet.Flag.DISABLED)) {
            //CONDITIONAL_DISABLE_PATCH
                patch(id(fromMod, atPath))
                    .compound()
                        .test(PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_TEST, true)
                        .replace("/structures", ProjectEvergreen.GSON.toJsonTree(new ArrayList<>()))
                    .end();
            } else {
            //UNIQUE_SALT_PATCH & FLATNESS_OFFSET
                patch(id(fromMod, atPath))
                    .compound()
                        .replace("/placement/salt", structureSet.getSalt())
                        .test(PEConfig.PERFORMANCE_MODE_TEST, false)
                        .compound()
                            .test(Constants.JsonProp.IS_FLAT.jsonPath(), true, false)
                            .paste("/placement/spacing", DataSources.CONFIG_VALUE, "/placement/spacing", JsonParser.parseString(PEConfig.FLATNESS_OFFSET_KEY))
                            .paste("/placement/spread", DataSources.CONFIG_VALUE, "/placement/spread", JsonParser.parseString(PEConfig.FLATNESS_OFFSET_KEY))
                        .end()
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


    //<---------------------------------------------------STRUCTURES--------------------------------------------------->
        ProjectEvergreen.LOGGER.info("Patching all Structures...");
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
                    //FORCE_DEEP_DARK
                    .compound()
                        .test(PEStructure.Flag.IS_DEEP_DARK.jsonPath(), true, false)
                        .add("/biomes", "minecraft:deep_dark")
                    .end()
                    //FORCE_VOLCANIC_CRATER
                    .compound()
                        .test(PEStructure.Flag.IS_VOLCANIC_CRATER.jsonPath(), true, false)
                        .add("/biomes", PEBiome.VOLCANIC_CRATER.tagKey())
                    .end()
                    //FORCE_CULTIVATED_FIELDS
                    .compound()
                        .test(PEStructure.Flag.IS_CULTIVATED_FIELDS.jsonPath(), true, false)
                        .add("/biomes", PEBiome.CULTIVATED_FIELDS.tagKey())
                    .end()
                    //FORCE_TROPICAL_ISLAND
                    .compound()
                        .test(PEStructure.Flag.IS_TROPICAL_ISLAND.jsonPath(), true, false)
                        .add("/biomes", PEBiome.TROPICAL_ISLAND.tagKey())
                    .end()
                    //FORCE_BIRCH_SPAWN
                    .compound()
                        .test(PEStructure.Flag.IS_BIRCH_FOREST.jsonPath(), true, false)
                        .add("/biomes", PEBiome.BIRCH_FOREST.tagKey())
                    .end()
                    //FORCE_CHERRY_SPAWN
                    .compound()
                        .test(PEStructure.Flag.IS_CHERRY_FOREST.jsonPath(), true, false)
                        .add("/biomes", PEBiome.CHERRY_FOREST.tagKey())
                    .end()
                .end()
            //MOB_SPAWN_OVERRIDES
                .compound()
                    .test(PEConfig.MOB_REDISTRIBUTION_TEST, true)
                    .test(PEStructure.Flag.HAS_SPAWN_OVERRIDES.jsonPath(), true, false)
                    .move("/spawn_overrides", Constants.JsonProp.SPAWN_OVERRIDES.jsonPath())
                .end()
            //BEARD_THIN_ADAPTATION
                .compound()
                    .test(PEStructure.Flag.ADD_BEARD_THIN_ADAPTATION.jsonPath(), true, false)
                    .add("/terrain_adaptation", "beard_thin")
                .end()
            //BURY_ADAPTATION
                .compound()
                    .test(PEStructure.Flag.ADD_BURY_ADAPTATION.jsonPath(), true, false)
                    .add("/terrain_adaptation", "bury")
                .end()
            //OCEANFLOOR_HEIGHTMAP_FIX
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_OCEAN_HEIGHTMAP.jsonPath(), true, false)
                    .add("/project_start_to_heightmap", "OCEAN_FLOOR_WG")
                    .add("/start_height", DataSources.getStartHeight(-3))
                .end()
            //ADD_WATERLOGGING
                .compound()
                    .test(PEStructure.Flag.ADD_WATERLOGGING.jsonPath(), true, false)
                    .remove("/liquid_settings")
                .end()
            //REMOVE_WATERLOGGING
                .compound()
                    .test(PEStructure.Flag.REMOVE_WATERLOGGING.jsonPath(), true, false)
                    .add("/liquid_settings", "ignore_waterlogging")
                .end()
            //ADJUST_UNDERGROUND_Y_LEVEL_SHALLOW
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_UNDERGROUND_Y_LEVEL_SHALLOW.jsonPath(), true, false)
                    .add("/start_height", PEStructure.Heightmap.buildScaledStartHeight(
                            Constants.UNDERGROUND_Y_MIN_SHALLOW, Constants.UNDERGROUND_Y_MAX_SHALLOW))
                .end()
            //ADJUST_UNDERGROUND_Y_LEVEL_DEEP
                .compound()
                    .test(PEStructure.Flag.ADJUSTED_UNDERGROUND_Y_LEVEL_DEEP.jsonPath(), true, false)
                    .add("/start_height", PEStructure.Heightmap.buildScaledStartHeight(
                            Constants.UNDERGROUND_Y_MIN_DEEP, Constants.UNDERGROUND_Y_MAX_DEEP))
                    .test("/project_start_to_heightmap", null, false)
                    .remove("/project_start_to_heightmap")
                .end()
            //INTEGRATED_VILLAGE_PATCH
                .compound()
                    .test(PEStructure.Flag.HAS_VILLAGE_FIX.jsonPath(), true, false)
                    .add("/terrain_adaptation", "beard_thin")
                    .test("/enhanced_terrain_adaptation", null, false)
                    .remove("/enhanced_terrain_adaptation")
                    .remove("/valid_biome_radius_check")
                    .remove("/use_bounding_box_hack")
                .end()
            //IS_EARLY_SPAWN_STEP
                .compound()
                    .test(PEStructure.Flag.EARLY_SPAWN_STEP.jsonPath(), true, false)
                    .add("/step", Constants.SpawnSteps.UNDERGROUND_STRUCTURES.name().toLowerCase())
                .end()
            //IS_LATE_SPAWN_STEP
                .compound()
                    .test(PEStructure.Flag.LATE_SPAWN_STEP.jsonPath(), true, false)
                    .add("/step", Constants.SpawnSteps.FLUID_SPRINGS.name().toLowerCase())
                .end()
                .compound()
                    .test(PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS.jsonPath(), null, true)
                //ADVANCED_STRUCTURE_TYPE_PATCH
                    .compound()
                        .test(Constants.JsonProp.IS_ADVANCED_TYPE.jsonPath(), true, false)
                        .remove("/terrain_check")
                        .compound()
                            .test("patched:mod_loaded", "repurposed_structures")
                            .add("/type", "repurposed_structures:generic_jigsaw_structure")
                        .end()
                        .compound()
                            .test("patched:mod_loaded", "moogs_structures")
                            .add("/type", "moogs_structures:moogs_structures_generic_jigsaw_structure")
                        .end()
                    .end()
                //SPAWN_STEP_NORMALIZATION
                    .compound()
                        .test(Constants.JsonProp.HEIGHTMAP.jsonPath(), "UNDERGROUND", false)
                        .add("/step", Constants.SpawnSteps.UNDERGROUND_STRUCTURES.name().toLowerCase())
                    .end()
                    .compound()
                        .test(Constants.JsonProp.HEIGHTMAP.jsonPath(), "GROUNDLEVEL", false)
                        .add("/step", Constants.SpawnSteps.SURFACE_STRUCTURES.name().toLowerCase())
                    .end()
                //CANNOT_SPAWN_IN_LIQUID_PATCH
                    .compound()
                        .test(Constants.JsonProp.IS_WATER_RESTRICTED.jsonPath(), true, false)
                        .add("/cannot_spawn_in_liquid", true)
                    .end()
                    .test(PEConfig.PERFORMANCE_MODE_TEST, false)
                //FLATNESS_CHECK_PATCH
                    .compound()
                        .test(Constants.JsonProp.IS_FLAT.jsonPath(), true, false)
                        .add("/terrain_height_radius_check", structure.getSize().get().flatnessRadius())
                        .paste("/allowed_terrain_height_range", DataSources.CONFIG_VALUE, structure.getSize().get().jsonPath(), JsonParser.parseString(PEConfig.ALLOWED_TERRAIN_HEIGHT_KEY))
                    .end()
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
                            .test(Constants.JsonProp.IS_MASSIVE.jsonPath(), true, false)
                            .test(Constants.JsonProp.IS_WATER_BOUND.jsonPath(), true, false)
                            .add("/valid_biome_radius_check", 3)
                        .end()
                        .compound()
                            .test(PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_TEST, true)
                            .test(PEConfig.POPULATION_BIAS_TEST, id)
                            .test("/valid_biome_radius_check", null, true)
                            .paste("/valid_biome_radius_check", DataSources.CONFIG_VALUE, JsonParser.parseString(PEConfig.POPULATION_BIAS_KEY))
                        .end()
                    .end()
                .end()
            .end();
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURES.size() + " Structures");

        //<---------------------------------------------------FEATURES--------------------------------------------------->
        ProjectEvergreen.LOGGER.info("Patching all Features...");
        WorldgenDataManager.PATCHABLE_FEATURES.forEach((id, feature) -> {
                    String fromMod = Constants.getNamespace(id);
                    String atPath = Constants.getPath(feature.full_path);

                    if(!DefaultFlags.ignoredFeatures.contains(id) && feature.isModifier()) {
                        patch(id(fromMod, atPath))
                        .compound()
                            .compound()
                                //The first data object holds our default values, added during development when data is generated.
                                //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
                                .add("/" + Constants.PROPERTIES_KEY, feature.toJson())
                                //.paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_FEATURE_KEY))
                            .end()
                        //DISABLED
                            .compound()
                                .test(PEFeature.Flag.DISABLED.jsonPath(), true, false)
                                .add("/type", "neoforge:none")
                            .end()
                        //BIOME_REDISTRIBUTION
                            .compound()
//                                .compound()
//                                    .test(Constants.JsonProp.DIMENSION.jsonPath(), null, false)
//                                    .copy("/biomes", Constants.JsonProp.DIMENSION.jsonPath())
//                                .end()
//                                .compound()
//                                    .test(Constants.JsonProp.REGION.jsonPath(), null, false)
//                                    .copy("/biomes", structure.getRegion().orElse(PERegion.NO_BIOMES).jsonPath())
//                                .end()
                                .compound()
                                    .test(Constants.JsonProp.BIOME.jsonPath(), null, false)
                                    .copy("/biomes", feature.getBiomes().orElse(PEBiome.NO_BIOMES).jsonPath())
                                .end()
                            .end()
                        .end();
                    } else {

                    }
        });
        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_STRUCTURES.size() + " Structures");

//        //<---------------------------------------------------MOBS--------------------------------------------------->
            //This is a global catch-all for certain buggy spawns that don't play well with the biome spawner code.
            //In particular this fixes crazy mob spawns at the borders between two biomes with different spawn costs.
            //TODO: Add isLoaded() check on removed entities
            JsonArray common_costs = new JsonArray();
            DefaultFlags.commonSpawn.forEach(common_costs::add);
            patch(id(ProjectEvergreen.MODID, "neoforge/biome_modifier/overworld_common_spawn_costs"))
                .compound()
                    .test(PEConfig.MOB_REDISTRIBUTION_TEST, true)
                    .add("/entity_types", common_costs)
                .end();
            JsonArray rare_costs = new JsonArray();
            DefaultFlags.rareSpawn.forEach(rare_costs::add);
            patch(id(ProjectEvergreen.MODID, "neoforge/biome_modifier/overworld_rare_spawn_costs"))
                .compound()
                    .test(PEConfig.MOB_REDISTRIBUTION_TEST, true)
                    .add("/entity_types", rare_costs)
                .end();
            JsonArray extra_rare_costs = new JsonArray();
            DefaultFlags.extraRareSpawn.forEach(extra_rare_costs::add);
            patch(id(ProjectEvergreen.MODID, "neoforge/biome_modifier/overworld_extra_rare_spawn_costs"))
                .compound()
                    .test(PEConfig.MOB_REDISTRIBUTION_TEST, true)
                    .add("/entity_types", extra_rare_costs)
                .end();

//        ProjectEvergreen.LOGGER.info("Patching all Mobs...");
//        WorldgenDataManager.PATCHABLE_ENTITIES.forEach((id, entity) -> {
//                    String fromMod = Constants.getNamespace(id);
//                    String atPath = Constants.getPath(entity.full_path);
//
//                    //if(!DefaultFlags.ignoredEntities.contains(id)) {
//                        patch(id(fromMod, atPath))
//                        .compound()
//                            .compound()
//                                //The first data object holds our default values, added during development when data is generated.
//                                //It's overwritten by a second object that is dynamically updated from the registry/configs at runtime.
//                                .add("/" + Constants.PROPERTIES_KEY, entity.toJson())
//                                //.paste("/" + Constants.PROPERTIES_KEY, DataSources.PE_OBJECT, "/" + Constants.PROPERTIES_KEY, JsonParser.parseString(Constants.PATCHABLE_FEATURE_KEY))
//                            .end()
//                        //DISABLED
//                            .compound()
//                                .test(PEFeature.Flag.DISABLED.jsonPath(), true, false)
//                                .add("/type", "neoforge:none")
//                            .end()
//                        //BIOME_REDISTRIBUTION
//                            .compound()
////                                .compound()
////                                    .test(Constants.JsonProp.DIMENSION.jsonPath(), null, false)
////                                    .copy("/biomes", Constants.JsonProp.DIMENSION.jsonPath())
////                                .end()
////                                .compound()
////                                    .test(Constants.JsonProp.REGION.jsonPath(), null, false)
////                                    .copy("/biomes", structure.getRegion().orElse(PERegion.NO_BIOMES).jsonPath())
////                                .end()
//                                .compound()
//                                    .test(Constants.JsonProp.BIOME.jsonPath(), null, false)
//                                    .copy("/biomes", entity.getBiomes().orElse(PEBiome.NO_BIOMES).jsonPath())
//                                .end()
//                            .end()
//                        .end();
//                    //}
//        });
//        ProjectEvergreen.LOGGER.info("Patched " + WorldgenDataManager.PATCHABLE_ENTITIES.size() + " Entities");
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
            "kattersstructures",
            "mostructures",
            "nordic_structures",
            "trek",
            "u_sea",
            "u_desert"
        );
        return modsWithBrokenSigns.contains(mod_id);
    }
}
