package com.elephantaugments.projectevergreen.neoforge.world;

import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.RatioTargets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import static com.elephantaugments.projectevergreen.common.integration.SupportedMods.*;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.*;

public class PEBiomePlacement {

    public static void register() {

        //<------------------------------PLAINS ECOSYSTEM------------------------------>
        ResourceKey<Biome> temperateEdge = REGIONS_UNEXPLORED.getBiome("clover_plains")
                .orElseGet(() -> NATURES_SPIRIT.getBiome("lavender_fields")
                        .orElseGet(() -> Biomes.PLAINS));
        ResourceKey<Biome> plainsScatteredTrees = REGIONS_UNEXPLORED.getBiome("orchard")
                .orElseGet(() -> DREAMWOODS.getBiome("poplar_grove")
                        .orElseGet(() -> Biomes.BIRCH_FOREST));
        ResourceKey<Biome> plainsCultivatedFields = REGIONS_UNEXPLORED.getBiome("prairie")
                .orElseGet(() -> DREAMWOODS.getBiome("poplar_grove")
                        .orElseGet(() -> Biomes.SUNFLOWER_PLAINS));
        ResourceKey<Biome> plainsHighlandReplacement = DREAMWOODS.getBiome("lush_grassland")
                .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prairie")
                        .orElseGet(() -> WYTHERS.getBiome("highland_plains").get()));

        BiomePlacement.replaceOverworld(WYTHERS.getBiome("highland_plains").get(), plainsHighlandReplacement);
        transitionalBiome(Biomes.PLAINS, plainsHighlandReplacement, plainsScatteredTrees);
        clearingBiome(Biomes.PLAINS, plainsCultivatedFields, 0.2f);
        //patchySubBiome(Biomes.PLAINS, plainsCultivatedFields);


        //<------------------------------FOREST ECOSYSTEM------------------------------>
        ResourceKey<Biome> forestFloweryClearing = REGIONS_UNEXPLORED.getBiome("poppy_fields")
                .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("marsh")
                        .orElseGet(() -> Biomes.FOREST));
        ResourceKey<Biome> forestClearingEdge = REGIONS_UNEXPLORED.getBiome("marsh")
                .orElseGet(() -> Biomes.FOREST);
        ResourceKey<Biome> forestWoodyAlternate = REGIONS_UNEXPLORED.getBiome("old_growth_forest")
                .orElseGet(() -> DREAMWOODS.getBiome("spinney")
                        .orElseGet(() -> Biomes.FOREST));
        ResourceKey<Biome> deciduousAutumnalTransition = REGIONS_UNEXPLORED.getBiome("poppy_fields")
                .orElseGet(() -> DREAMWOODS.getBiome("golden_aspen_spinney")
                        .orElseGet(() -> Biomes.SUNFLOWER_PLAINS));

        patchySubBiome(Biomes.FOREST, forestWoodyAlternate);
        //clearingBiome(Biomes.FOREST, forestFloweryClearing, 0.2F);



        //WYTHERS
        if (PlatformHooks.PLATFORM_HELPER.isModLoaded("expanded_ecosphere"))
        {
            ResourceKey<Biome> gravellyRiver = WYTHERS.getBiome("gravelly_river").get();
            ResourceKey<Biome> gravellyBeach = WYTHERS.getBiome("gravelly_beach").get();
            ResourceKey<Biome> desertBeach = WYTHERS.getBiome("desert_beach").get();
            ResourceKey<Biome> forestEdge = WYTHERS.getBiome("forest_edge").get();
            ResourceKey<Biome> coolForest = WYTHERS.getBiome("cool_forest").get();
            ResourceKey<Biome> coolForestEdge = WYTHERS.getBiome("cool_forest_edge").get();
            ResourceKey<Biome> coolPlains = WYTHERS.getBiome("cool_plains").get();
            ResourceKey<Biome> coldPlains = WYTHERS.getBiome("cold_plains").get();
            ResourceKey<Biome> autumnalPlains = WYTHERS.getBiome("autumnal_plains").get();
            ResourceKey<Biome> autumnalForest = WYTHERS.getBiome("autumnal_forest").get();
            ResourceKey<Biome> autumnalForestEdge = WYTHERS.getBiome("autumnal_forest_edge").get();
            ResourceKey<Biome> pineBarrens = WYTHERS.getBiome("pine_barrens").get();
            ResourceKey<Biome> borealForestRed = WYTHERS.getBiome("boreal_forest_red").get();
            ResourceKey<Biome> borealForestYellow = WYTHERS.getBiome("boreal_forest_yellow").get();
            ResourceKey<Biome> crimsonTundra = WYTHERS.getBiome("crimson_tundra").get();
            ResourceKey<Biome> snowyTundra = WYTHERS.getBiome("snowy_tundra").get();
            ResourceKey<Biome> icyCrags = WYTHERS.getBiome("icy_crags").get();
            ResourceKey<Biome> calciteCoast = WYTHERS.getBiome("calcite_coast").get();
            ResourceKey<Biome> tundra = WYTHERS.getBiome("tundra").get();
            ResourceKey<Biome> fen = WYTHERS.getBiome("fen").get();
            ResourceKey<Biome> marsh = WYTHERS.getBiome("marsh").get();
            ResourceKey<Biome> gravellyBeachReplacement = REGIONS_UNEXPLORED.getBiome("gravel_beach")
                    .orElseGet(() -> DREAMWOODS.getBiome("gravel_beach")
                            .orElseGet(() -> gravellyBeach));
            ResourceKey<Biome> gravellyRiverReplacement = REGIONS_UNEXPLORED.getBiome("cold_river")
                            .orElseGet(() -> Biomes.RIVER);

            BiomePlacement.replaceOverworld(Biomes.BEACH, WYTHERS.getBiome("sand_dunes").get());
            BiomePlacement.replaceOverworld(Biomes.FROZEN_RIVER, WYTHERS.getBiome("icy_river").get());
            BiomePlacement.replaceOverworld(gravellyRiver, gravellyRiverReplacement);
            BiomePlacement.replaceOverworld(gravellyBeach, gravellyBeachReplacement);
            BiomePlacement.replaceOverworld(Biomes.RIVER, gravellyRiver);



            //<------------------------------FLOWER FOREST ECOSYSTEMS------------------------------>
            ResourceKey<Biome> harvestFields = WYTHERS.getBiome("harvest_fields").get();
            ResourceKey<Biome> autumnalFlowerForest = WYTHERS.getBiome("autumnal_flower_forest").get();
            ResourceKey<Biome> springFlowerForest = WYTHERS.getBiome("spring_flower_forest").get();
            ResourceKey<Biome> springFlowerFields = WYTHERS.getBiome("spring_flower_fields").get();
            ResourceKey<Biome> springFlowerThicket = NATURES_SPIRIT.getBiome("wisteria_forest")
                    .orElseGet(() -> DREAMWOODS.getBiome("blooming_grove")
                            .orElseGet(() -> Biomes.FLOWER_FOREST));
            ResourceKey<Biome> springFlowerFieldsReplacement = NATURES_SPIRIT.getBiome("floral_ridges")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("flower_fields")
                            .orElseGet(() -> DREAMWOODS.getBiome("blooming_grove")
                                    .orElseGet(() -> springFlowerFields)));
            ResourceKey<Biome> flowerFieldsTransition = REGIONS_UNEXPLORED.getBiome("clover_plains")
                    .orElseGet(() -> DREAMWOODS.getBiome("flowering_fields")
                            .orElseGet(() -> Biomes.FLOWER_FOREST));

            BiomePlacement.replaceOverworld(springFlowerFields, springFlowerFieldsReplacement);
            transitionalEdgeBiome(Biomes.FLOWER_FOREST, springFlowerForest, springFlowerFields);
            transitionalEdgeBiome(Biomes.SUNFLOWER_PLAINS, springFlowerFields, flowerFieldsTransition);
            clearingBiome(springFlowerForest, springFlowerThicket, 0.35F);
            clearingBiome(springFlowerFields, springFlowerThicket, 0.25F);


            //<------------------------------ISLAND ECOSYSTEMS------------------------------>
            ResourceKey<Biome> coldIsland = WYTHERS.getBiome("cold_island").get();
            ResourceKey<Biome> frozenIsland = WYTHERS.getBiome("frozen_island").get();
            ResourceKey<Biome> temperateIsland = WYTHERS.getBiome("temperate_island").get();
            ResourceKey<Biome> mediterraneanIsland = WYTHERS.getBiome("mediterranean_island").get();
            ResourceKey<Biome> mushroomIsland = WYTHERS.getBiome("mushroom_island").get();
            ResourceKey<Biome> tropicalIsland = WYTHERS.getBiome("tropical_island").get();
            ResourceKey<Biome> tropicalBeach = WYTHERS.getBiome("tropical_beach").get();
            ResourceKey<Biome> jungleIsland = WYTHERS.getBiome("jungle_island").get();
            ResourceKey<Biome> desertIsland = WYTHERS.getBiome("desert_island").get();
            ResourceKey<Biome> frigidIsland = WYTHERS.getBiome("frigid_island").get();
            ResourceKey<Biome> icyVolcano = WYTHERS.getBiome("icy_volcano").get();
            ResourceKey<Biome> volcano = WYTHERS.getBiome("volcano").get();

            ResourceKey<Biome> tropicalBeachReplacement = DREAMWOODS.getBiome("jungle_shore")
                            .orElseGet(() -> WYTHERS.getBiome("tropical_beach").get());
            ResourceKey<Biome> tropicalIslandReplacement = REGIONS_UNEXPLORED.getBiome("tropics")
                            .orElseGet(() -> tropicalIsland);
            ResourceKey<Biome> jungleIslandReplacement = DREAMWOODS.getBiome("roofed_tropical_forest")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("tropical_woods")
                        .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("sparse_rainforest")
                            .orElseGet(() -> desertIsland)));
            ResourceKey<Biome> desertIslandReplacement = NATURES_SPIRIT.getBiome("carnation_fields")
                            .orElseGet(() -> desertIsland);
            ResourceKey<Biome> icyVolcanoReplacement = REGIONS_UNEXPLORED.getBiome("icy_heights")
                            .orElseGet(() -> desertIsland);
            ResourceKey<Biome> coldIslandReplacement = NATURES_SPIRIT.getBiome("coniferous_covert")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("windswept_maple_forest")
                            .orElseGet(() -> WYTHERS.getBiome("cold_island").get()));
            ResourceKey<Biome> temperateIslandTransition = REGIONS_UNEXPLORED.getBiome("clover_plains")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("redwood_forest")
                            .orElseGet(() -> Biomes.FOREST));
            ResourceKey<Biome> temperateIslandThicket = REGIONS_UNEXPLORED.getBiome("redwoods")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("redwood_forest")
                            .orElseGet(() -> Biomes.FOREST));
            ResourceKey<Biome> temperateIslandThicketEdge = REGIONS_UNEXPLORED.getBiome("sparse_redwoods")
                    .orElseGet(() -> Biomes.PLAINS);
            ResourceKey<Biome> temperateVolcanoTransition = WYTHERS.getBiome("temperate_rainforest_crags")
                            .orElseGet(() -> temperateIsland);
            ResourceKey<Biome> mediterraneanDesertTransition = NATURES_SPIRIT.getBiome("xeric_plains")
                            .orElseGet(() -> mediterraneanIsland);
            ResourceKey<Biome> desertVolcanoTransition = NATURES_SPIRIT.getBiome("carnation_fields")
                    .orElseGet(() -> DREAMWOODS.getBiome("volcano_fields")
                            .orElseGet(() -> desertIsland));
            ResourceKey<Biome> tropicalVolcanoTransition = REGIONS_UNEXPLORED.getBiome("tropics")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("tropics")
                            .orElseGet(() -> temperateIsland));
            ResourceKey<Biome> mediterraneanIslandTransition = NATURES_SPIRIT.getBiome("shrubland")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prairie")
                            .orElseGet(() -> mediterraneanIsland));
            ResourceKey<Biome> mediterraneanIslandClearing = NATURES_SPIRIT.getBiome("carnation_fields")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("cypress_fields")
                            .orElseGet(() -> mediterraneanIsland));
            ResourceKey<Biome> mediterraneanIslandThicket = NATURES_SPIRIT.getBiome("cypress_fields")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("xeric_plains")
                            .orElseGet(() -> mediterraneanIsland));
            ResourceKey<Biome> desertTropicalTransition = REGIONS_UNEXPLORED.getBiome("dry_bushland")
                    .orElseGet(() -> DREAMWOODS.getBiome("jungle_bushland")
                            .orElseGet(() -> desertIsland));
            ResourceKey<Biome> mushroomFieldsTransition = DREAMWOODS.getBiome("fairy_garden")
                    .orElseGet(() -> DREAMWOODS.getBiome("jungle_bushland")
                            .orElseGet(() -> Biomes.MUSHROOM_FIELDS));
            ResourceKey<Biome> spookyFrigidClearing = PlatformHooks.PLATFORM_HELPER.isModLoaded("vanillabackport") ?
                    MINECRAFT.getBiome("pale_garden").get() :
                            frigidIsland;


            //BiomePlacement.replaceOverworld(icyVolcano, icyVolcanoReplacement);
            BiomePlacement.replaceOverworld(tropicalIsland, tropicalIslandReplacement);
            BiomePlacement.replaceOverworld(jungleIsland, jungleIslandReplacement);
            BiomePlacement.replaceOverworld(coldIsland, coldIslandReplacement, 0.5d);
            patchySubBiome(temperateIsland, temperateIslandThicket);
            edgeBiome(temperateIslandThicket, temperateIslandThicketEdge, BiomeParameterTargets.HUMIDITY);
            //patchySubBiome(mediterraneanIsland, mediterraneanIslandThicket);
            //clearingBiome(mediterraneanIsland, mediterraneanIslandClearing, 0.2f);
            landEdgeBiome(mediterraneanIsland, mediterraneanIslandTransition, BiomeParameterTargets.HUMIDITY);
            landEdgeBiome(temperateIsland, temperateIslandTransition, BiomeParameterTargets.HUMIDITY);
            transitionalBiome(temperateIsland, volcano, temperateVolcanoTransition);
            transitionalBiome(tropicalIslandReplacement, volcano, tropicalVolcanoTransition);
            transitionalBiome(desertIsland, volcano, desertVolcanoTransition);
            transitionalBiome(mediterraneanIsland, desertIsland, mediterraneanDesertTransition);
            transitionalEdgeBiome(desertIsland, jungleIsland, desertVolcanoTransition);
            transitionalEdgeBiome(tropicalIslandReplacement, desertIsland, desertTropicalTransition);
            //transitionalBiome(frigidIsland, icyVolcano, frigidVolcanoTransition);
            //clearingBiome(desertIsland, desertIslandReplacement, 0.5F);
            clearingBiome(frigidIsland, spookyFrigidClearing, 0.3F);


            //<------------------------------OCEAN ECOSYSTEM------------------------------>
            ResourceKey<Biome> frozenOcean = Biomes.FROZEN_OCEAN;
            ResourceKey<Biome> deepFrozenOcean = Biomes.DEEP_FROZEN_OCEAN;
            ResourceKey<Biome> icyOcean = WYTHERS.getBiome("icy_ocean").get();
            ResourceKey<Biome> iceCaps = WYTHERS.getBiome("ice_cap").get();
            ResourceKey<Biome> deepIcyOcean = WYTHERS.getBiome("deep_icy_ocean").get();
            ResourceKey<Biome> icyOceanAddition = DREAMWOODS.getBiome("ice_abyss")
                    .orElseGet(() -> deepIcyOcean);
            ResourceKey<Biome> frozenOceanAddition = REGIONS_UNEXPLORED.getBiome("hyacinth_deeps")
                    .orElseGet(() -> deepFrozenOcean);
            ResourceKey<Biome> warmOceanTransition = REGIONS_UNEXPLORED.getBiome("rocky_reef")
                    .orElseGet(() -> Biomes.WARM_OCEAN);
            ResourceKey<Biome> deepWarmOceanTransition = SPAWN.getBiome("deep_warm_ocean")
                    .orElseGet(() -> Biomes.WARM_OCEAN);

            BiomePlacement.replaceOverworld(deepFrozenOcean, deepIcyOcean);
            BiomePlacement.replaceOverworld(frozenOcean, icyOcean);
            BiomePlacement.replaceOverworld(icyOcean, frozenOcean);
            BiomePlacement.replaceOverworld(deepIcyOcean, deepFrozenOcean);
            clearingBiome(deepFrozenOcean, frozenOceanAddition, 0.3F);
            clearingBiome(deepIcyOcean, frozenOceanAddition, 0.3F);
            //clearingBiome(deepIcyOcean, iceCaps, 0.5F);
            //transitionalBiome(Biomes.WARM_OCEAN, WYTHERS.getBiome("desert_beach").get(), warmOceanTransition);
            transitionalBiome(Biomes.WARM_OCEAN, WYTHERS.getBiome("tropical_beach").get(), warmOceanTransition);
            transitionalBiome(Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN, deepWarmOceanTransition);
            transitionalBiome(Biomes.MUSHROOM_FIELDS, deepIcyOcean, Biomes.MUSHROOM_FIELDS);
            transitionalBiome(frigidIsland, Biomes.DEEP_FROZEN_OCEAN, Biomes.ICE_SPIKES);
            clearingBiome(Biomes.WARM_OCEAN, warmOceanTransition, 0.25F);


            //<------------------------------AUTUMNAL ECOSYSTEMS------------------------------>
            ResourceKey<Biome> autumnalFieldsReplacement = NATURES_SPIRIT.getBiome("prairie")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prairie")
                            .orElseGet(() -> autumnalPlains));
            ResourceKey<Biome> autumnBirchForestReplacement = NATURES_SPIRIT.getBiome("golden_wilds")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("prairie")
                            .orElseGet(() -> WYTHERS.getBiome("autumnal_birch_forest").get()));
            ResourceKey<Biome> autumnForestReplacement = NATURES_SPIRIT.getBiome("maple_woodlands")
                    .orElseGet(() -> DREAMWOODS.getBiome("seasonal_grove")
                            .orElseGet(() -> WYTHERS.getBiome("autumnal_forest").get()));
            ResourceKey<Biome> forestedHighlandsReplacement = REGIONS_UNEXPLORED.getBiome("pine_slopes")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("highland_fields")
                            .orElseGet(() -> WYTHERS.getBiome("forested_highlands").get()));
            ResourceKey<Biome> coolForestReplacement = NATURES_SPIRIT.getBiome("maple_woodlands")
                    .orElseGet(() -> DREAMWOODS.getBiome("seasonal_grove")
                            .orElseGet(() -> coolForest));
            ResourceKey<Biome> coolForestEdgeReplacement = NATURES_SPIRIT.getBiome("marigold_meadows")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("golden_wilds")
                            .orElseGet(() -> coolForestEdge));
            ResourceKey<Biome> coolPlainsReplacement = REGIONS_UNEXPLORED.getBiome("prairie")
                    .orElseGet(() -> DREAMWOODS.getBiome("prairie")
                            .orElseGet(() -> WYTHERS.getBiome("cool_plains").get()));
            ResourceKey<Biome> birchTaigaReplacement = NATURES_SPIRIT.getBiome("aspen_forest")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("pine_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("birch_taiga").get()));
            ResourceKey<Biome> autumnPlainsTransition = REGIONS_UNEXPLORED.getBiome("prairie")
                    .orElseGet(() -> Biomes.SUNFLOWER_PLAINS);
            ResourceKey<Biome> autumnMeadowTransition = DREAMWOODS.getBiome("poplar_slopes")
                    .orElseGet(() -> Biomes.SUNFLOWER_PLAINS);
            ResourceKey<Biome> mountainAutumnTransition = DREAMWOODS.getBiome("golden_aspen_spinney")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("aspen_forest")
                            .orElseGet(() -> WYTHERS.getBiome("birch_taiga").get()));
            ResourceKey<Biome> taigaAutumnTransition = DREAMWOODS.getBiome("autumnal_hills")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("aspen_forest")
                            .orElseGet(() -> autumnalPlains));
            ResourceKey<Biome> autumnalCragsReplacement = DREAMWOODS.getBiome("autumnal_hills")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("aspen_forest")
                            .orElseGet(() -> WYTHERS.getBiome("autumnal_crags").get()));
            ResourceKey<Biome> autumnalSwampReplacement = WYTHERS.getBiome("berry_bog").get();
            ResourceKey<Biome> coldAutumnalTransition = DREAMWOODS.getBiome("autumnal_hills")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("heather_fields")
                            .orElseGet(() -> WYTHERS.getBiome("autumnal_forest_edge").get()));

            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_plains").get(), autumnalFieldsReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_crags").get(), autumnalCragsReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_swamp").get(), autumnalSwampReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_birch_forest").get(), autumnBirchForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_forest_edge").get(), autumnalFieldsReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("autumnal_forest").get(), autumnForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("birch_taiga").get(), birchTaigaReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("forested_highlands").get(), forestedHighlandsReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cool_forest").get(), coolForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cool_forest_edge").get(), coolForestEdgeReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cool_plains").get(), coolPlainsReplacement);
            transitionalBiome(coolForestEdgeReplacement, forestEdge, deciduousAutumnalTransition);
            transitionalEdgeBiome(coolForestEdgeReplacement, coolForestReplacement, deciduousAutumnalTransition);
            transitionalBiome(coolPlainsReplacement, coolForestEdgeReplacement, autumnalFieldsReplacement);
            transitionalBiome(forestEdge, coolForestReplacement, deciduousAutumnalTransition);
            transitionalEdgeBiome(Biomes.FOREST, coolForestReplacement, deciduousAutumnalTransition);
            transitionalEdgeBiome(forestWoodyAlternate, coolForestReplacement, deciduousAutumnalTransition);
            transitionalEdgeBiome(forestedHighlandsReplacement, coolForestReplacement, mountainAutumnTransition);
            transitionalBiome(WYTHERS.getBiome("taiga_crags").get(), coolForestEdgeReplacement, taigaAutumnTransition);
            transitionalBiome(harvestFields, coolPlainsReplacement, Biomes.SUNFLOWER_PLAINS);
            transitionalBiome(harvestFields, autumnalFieldsReplacement, autumnPlainsTransition);
            transitionalBiome(Biomes.PLAINS, autumnalFieldsReplacement, autumnPlainsTransition);
            transitionalBiome(Biomes.MEADOW, autumnalFieldsReplacement, autumnMeadowTransition);
            transitionalBiome(Biomes.MEADOW, autumnForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.MEADOW, coolForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.MEADOW, coolForestEdgeReplacement, harvestFields);
            transitionalBiome(Biomes.BIRCH_FOREST, autumnForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.BIRCH_FOREST, coolForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.OLD_GROWTH_BIRCH_FOREST, autumnForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.OLD_GROWTH_BIRCH_FOREST, coolForestReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.BIRCH_FOREST, coolForestEdgeReplacement, coldAutumnalTransition);
            transitionalBiome(Biomes.OLD_GROWTH_BIRCH_FOREST, coolForestEdgeReplacement, coldAutumnalTransition);
            transitionalBiome(forestedHighlandsReplacement, coolForestReplacement, coldAutumnalTransition);
            transitionalBiome(forestedHighlandsReplacement, autumnForestReplacement, coldAutumnalTransition);


            //<------------------------------TUNDRA ECOSYSTEMS------------------------------>
            ResourceKey<Biome> tundraReplacement = DREAMWOODS.getBiome("tundra")
                            .orElseGet(() -> WYTHERS.getBiome("tundra").get());
            ResourceKey<Biome> coldStonyCanyonReplacement = REGIONS_UNEXPLORED.getBiome("pine_slopes")
                    .orElseGet(() -> DREAMWOODS.getBiome("autumnal_hills")
                            .orElseGet(() -> WYTHERS.getBiome("cool_stony_canyons").get()));
            ResourceKey<Biome> tundraMeadowTransition = NATURES_SPIRIT.getBiome("alpine_highlands")
                    .orElseGet(() -> DREAMWOODS.getBiome("flowering_fields")
                            .orElseGet(() -> WYTHERS.getBiome("tundra").get()));
            ResourceKey<Biome> tundraTreePatches = REGIONS_UNEXPLORED.getBiome("towering_cliffs")
                            .orElseGet(() -> WYTHERS.getBiome("tundra").get());
            ResourceKey<Biome> tundraFlowerPatches = DREAMWOODS.getBiome("flowering_fields")
                            .orElseGet(() -> WYTHERS.getBiome("tundra").get());
            ResourceKey<Biome> coldPlainsReplacement = NATURES_SPIRIT.getBiome("fir_forest")
                    .orElseGet(() -> DREAMWOODS.getBiome("moor")
                            .orElseGet(() -> coldPlains));

            //BiomePlacement.replaceOverworld(tundra, tundraReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cool_stony_canyons").get(), coldStonyCanyonReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cold_plains").get(), coldPlainsReplacement);
            clearingBiome(tundra, tundraTreePatches, 0.5F);
            transitionalEdgeBiome(tundra, Biomes.MEADOW, tundraMeadowTransition);
            transitionalBiome(autumnForestReplacement, coldPlains, coldAutumnalTransition);
            transitionalBiome(calciteCoast, autumnalFieldsReplacement, coolPlainsReplacement);
            transitionalBiome(coolForestReplacement, coldPlains, coldAutumnalTransition);
            transitionalBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS, fen, tundra);
            clearingBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS, tundraFlowerPatches, 0.25F);


            //<------------------------------CONIFEROUS ECOSYSTEMS------------------------------>
            ResourceKey<Biome> oldGrowthPineTaigaReplacement = REGIONS_UNEXPLORED.getBiome("blackwood_taiga")
                    .orElseGet(() -> DREAMWOODS.getBiome("temperate_hemlock_rainforest")
                            .orElseGet(() -> Biomes.OLD_GROWTH_PINE_TAIGA));
            ResourceKey<Biome> yellowBorealReplacement = REGIONS_UNEXPLORED.getBiome("golden_boreal_taiga")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("boreal_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("boreal_forest_yellow").get()));
            ResourceKey<Biome> larchTaigaReplacement = REGIONS_UNEXPLORED.getBiome("boreal_taiga")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("boreal_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("larch_taiga").get()));
            ResourceKey<Biome> ancientTaigaTransition = REGIONS_UNEXPLORED.getBiome("blackwood_taiga")
                    .orElseGet(() -> WYTHERS.getBiome("ancient_taiga").get());

            transitionalBiome(Biomes.TAIGA, Biomes.BIRCH_FOREST, coldPlainsReplacement);
            transitionalBiome(Biomes.TAIGA, Biomes.OLD_GROWTH_BIRCH_FOREST, coldPlainsReplacement);
            transitionalBiome(Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, ancientTaigaTransition);
            transitionalBiome(Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, ancientTaigaTransition);
            transitionalBiome(Biomes.OLD_GROWTH_PINE_TAIGA, WYTHERS.getBiome("larch_taiga").get(), ancientTaigaTransition);
            transitionalBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, WYTHERS.getBiome("larch_taiga").get(), ancientTaigaTransition);
            //transitionalBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, ancientTaigaTransition);


            //<------------------------------SNOWY ECOSYSTEMS------------------------------>
            ResourceKey<Biome> snowyThermalTaiga = WYTHERS.getBiome("snowy_thermal_taiga").get();
            ResourceKey<Biome> crimsonTundraReplacement = WYTHERS.getBiome("larch_taiga").get();
            ResourceKey<Biome> snowyBeachReplacement = DREAMWOODS.getBiome("frozen_shore")
                    .orElseGet(() -> Biomes.SNOWY_BEACH);
            ResourceKey<Biome> snowyPlainsReplacement = REGIONS_UNEXPLORED.getBiome("tundra")
                            .orElseGet(() -> Biomes.SNOWY_PLAINS);
            ResourceKey<Biome> snowyTundraReplacement = NATURES_SPIRIT.getBiome("tundra")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("tundra")
                            .orElseGet(() -> WYTHERS.getBiome("snowy_tundra").get()));
            ResourceKey<Biome> snowyTaigaReplacement = DREAMWOODS.getBiome("snowy_forest")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("boreal_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("snowy_thermal_taiga").get()));
            ResourceKey<Biome> snowyTundraTransition = NATURES_SPIRIT.getBiome("boreal_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("crimson_tundra").get());
            ResourceKey<Biome> snowyForestClearing = DREAMWOODS.getBiome("snowy_forest")
                    .orElseGet(() -> frozenIsland);
            ResourceKey<Biome> icyClearing = REGIONS_UNEXPLORED.getBiome("spires")
                    .orElseGet(() -> icyCrags);
            ResourceKey<Biome> frozenTundraTransition = REGIONS_UNEXPLORED.getBiome("tundra")
                        .orElseGet(() -> WYTHERS.getBiome("snowy_tundra").get());
            ResourceKey<Biome> snowyDeciduousTransition = REGIONS_UNEXPLORED.getBiome("pine_taiga")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("snowy_fir_forest")
                        .orElseGet(() -> Biomes.SNOWY_PLAINS));

            //BiomePlacement.replaceOverworld(Biomes.SNOWY_PLAINS, snowyPlainsReplacement);
            //BiomePlacement.replaceOverworld(Biomes.SNOWY_BEACH, snowyBeachReplacement);
            BiomePlacement.replaceOverworld(crimsonTundra, crimsonTundraReplacement);
            BiomePlacement.replaceOverworld(Biomes.SNOWY_PLAINS, snowyTaigaReplacement, 0.5d);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("snowy_tundra").get(), snowyTundraReplacement);
            transitionalEdgeBiome(tundra, coldPlains, crimsonTundraReplacement);
            transitionalEdgeBiome(Biomes.MEADOW, coldPlains, pineBarrens);
            transitionalEdgeBiome(Biomes.MEADOW, snowyTundraReplacement, snowyDeciduousTransition);
            transitionalEdgeBiome(snowyThermalTaiga, snowyTundraReplacement, snowyDeciduousTransition);
            transitionalEdgeBiome( coldStonyCanyonReplacement, snowyTundraReplacement, snowyDeciduousTransition);
            transitionalEdgeBiome(snowyTundraReplacement, Biomes.PLAINS, crimsonTundra);
            transitionalEdgeBiome(snowyTundraTransition, Biomes.PLAINS, crimsonTundra);
            transitionalEdgeBiome(Biomes.PLAINS, snowyTaigaReplacement, snowyDeciduousTransition);
            transitionalBiome(autumnalFieldsReplacement, Biomes.MEADOW, snowyDeciduousTransition);
            transitionalBiome(crimsonTundraReplacement, snowyTundra, snowyTundraTransition);
            transitionalBiome(tundraTreePatches, snowyTundra, snowyTundraTransition);
            transitionalBiome(tundra, snowyTundra, snowyTundraTransition);
            transitionalBiome(crimsonTundraReplacement, icyCrags, snowyTundraTransition);
            transitionalBiome(Biomes.SNOWY_TAIGA, borealForestRed, snowyTundraTransition);
            transitionalBiome(Biomes.SNOWY_TAIGA, borealForestYellow, snowyTundraTransition);
            transitionalBiome(Biomes.SNOWY_TAIGA, snowyTundraReplacement, frozenTundraTransition);
            transitionalEdgeBiome(Biomes.SNOWY_PLAINS, Biomes.PLAINS, snowyDeciduousTransition);
            transitionalEdgeBiome(snowyPlainsReplacement, Biomes.PLAINS, snowyDeciduousTransition);
            clearingBiome(Biomes.SNOWY_PLAINS, snowyThermalTaiga, 0.3F);
            clearingBiome(icyCrags, icyClearing, 0.35F);
            clearingBiome(frozenIsland, snowyForestClearing, 0.5F);


            //<------------------------------SWAMPY ECOSYSTEMS------------------------------>
            ResourceKey<Biome> birchSwamp = WYTHERS.getBiome("birch_swamp").get();
            ResourceKey<Biome> autumnalSwamp = WYTHERS.getBiome("autumnal_swamp").get();
            ResourceKey<Biome> waterlilySwamp = WYTHERS.getBiome("waterlily_swamp").get();
            ResourceKey<Biome> dripleafSwamp = WYTHERS.getBiome("dripleaf_swamp").get();
            ResourceKey<Biome> bambooSwamp = WYTHERS.getBiome("bamboo_swamp").get();
            ResourceKey<Biome> bambooJungleSwamp = WYTHERS.getBiome("bamboo_jungle_swamp").get();
            ResourceKey<Biome> bayou = WYTHERS.getBiome("bayou").get();
            ResourceKey<Biome> marshReplacement = NATURES_SPIRIT.getBiome("marsh")
                    .orElseGet(() -> WYTHERS.getBiome("marsh").get());
            ResourceKey<Biome> fenReplacement = REGIONS_UNEXPLORED.getBiome("fen")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("marsh")
                            .orElseGet(() -> WYTHERS.getBiome("fen").get()));
            ResourceKey<Biome> bayouReplacement = REGIONS_UNEXPLORED.getBiome("bayou")
                    .orElseGet(() -> DREAMWOODS.getBiome("bayou")
                            .orElseGet(() -> WYTHERS.getBiome("fen").get()));
            ResourceKey<Biome> dripleafSwampReplacement = REGIONS_UNEXPLORED.getBiome("old_growth_bayou")
                    .orElseGet(() -> DREAMWOODS.getBiome("morass")
                            .orElseGet(() -> WYTHERS.getBiome("dripleaf_swamp").get()));
            ResourceKey<Biome> bambooJungleSwampReplacement = NATURES_SPIRIT.getBiome("bamboo_wetlands")
                    .orElseGet(() -> DREAMWOODS.getBiome("bayou")
                            .orElseGet(() -> WYTHERS.getBiome("bamboo_jungle_swamp").get()));
            ResourceKey<Biome> birchWaterlilySwampTransition = NATURES_SPIRIT.getBiome("sugi_forest")
                    .orElseGet(() -> WYTHERS.getBiome("ancient_oak_swamp").get());

            BiomePlacement.replaceOverworld(bambooJungleSwamp, bambooJungleSwampReplacement);
            BiomePlacement.replaceOverworld(dripleafSwamp, dripleafSwampReplacement);
            BiomePlacement.replaceOverworld(bayou, bayouReplacement);
            //transitionalBiome(dripleafSwamp, WYTHERS.getBiome("tropical_forest").get(), bayouReplacement);
            transitionalBiome(waterlilySwamp, birchSwamp, birchWaterlilySwampTransition);
            transitionalBiome(waterlilySwamp, bambooSwamp, bambooSwamp);
            clearingBiome(Biomes.SWAMP, marshReplacement, 0.2F);


            //<------------------------------SPOOKY ECOSYSTEMS------------------------------>

            ResourceKey<Biome> forbiddenForestReplacement = DREAMWOODS.getBiome("autumnal_wood")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("blackwood_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("ancient_golden_beech_forest").get()));
            ResourceKey<Biome> spookyForestEdge = DREAMWOODS.getBiome("weeping_grove")
                    .orElseGet(() -> WYTHERS.getBiome("wistman_woods").get());
            ResourceKey<Biome> spookyForest = DREAMWOODS.getBiome("grim_woods")
                                .orElseGet(() -> spookyForestEdge);


            BiomePlacement.replaceOverworld(WYTHERS.getBiome("forbidden_forest").get(), forbiddenForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("marsh").get(), spookyForestEdge);
            transitionalBiome(coolForest, spookyForestEdge, spookyForest);
            transitionalBiome(coolForestEdge, spookyForestEdge, deciduousAutumnalTransition);
            transitionalBiome(coolPlains,  spookyForestEdge, Biomes.SWAMP);
            //transitionalBiome(autumnalPlains, spookyForestEdge, autumnalSpookyForestEdge);
            transitionalEdgeBiome(spookyForestEdge, Biomes.SWAMP, marshReplacement);
            clearingBiome(spookyForestEdge, spookyForest, 0.8F);



            //<------------------------------ORIENTAL ECOSYSTEMS------------------------------>
            ResourceKey<Biome> sakuraForest = WYTHERS.getBiome("sakura_forest").get();
            ResourceKey<Biome> wisteriaClearing = NATURES_SPIRIT.getBiome("wisteria_forest")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("wisteria_grove")
                            .orElseGet(() -> WYTHERS.getBiome("sakura_forest").get()));
            ResourceKey<Biome> bambooForest = REGIONS_UNEXPLORED.getBiome("bamboo_forest")
                    .orElseGet(() -> WYTHERS.getBiome("sparse_bamboo_jungle").get());
            ResourceKey<Biome> sakuraBambooTransition = NATURES_SPIRIT.getBiome("floral_ridges")
                            .orElseGet(() -> WYTHERS.getBiome("sparse_bamboo_jungle").get());
            ResourceKey<Biome> warmBirchReplacement = NATURES_SPIRIT.getBiome("blooming_sugi_forest")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("sugi_forest")
                            .orElseGet(() -> WYTHERS.getBiome("warm_birch_forest").get()));


            BiomePlacement.replaceOverworld(WYTHERS.getBiome("warm_birch_forest").get(), warmBirchReplacement);
            transitionalBiome(sakuraForest, bambooForest, sakuraBambooTransition);
            clearingBiome(sakuraForest, wisteriaClearing, 0.2F);



            //<------------------------------MAGICAL ECOSYSTEMS------------------------------>
            ResourceKey<Biome> phantasmalForest = WYTHERS.getBiome("phantasmal_forest").get();
            ResourceKey<Biome> phantasmalSwamp = WYTHERS.getBiome("phantasmal_swamp").get();
                    ResourceKey<Biome> ancientOakSwampReplacement = DREAMWOODS.getBiome("grim_woods")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("blackwood_taiga")
                            .orElseGet(() -> WYTHERS.getBiome("ancient_oak_swamp").get()));
            ResourceKey<Biome> phantasmalSwampReplacement = DREAMWOODS.getBiome("twilight_wetlands")
                            .orElseGet(() -> phantasmalSwamp);
            ResourceKey<Biome> ancientMossyForestReplacement = DREAMWOODS.getBiome("elder_woods")
                    .orElseGet(() -> WYTHERS.getBiome("ancient_moss_forest").get());
            ResourceKey<Biome> goldenBeechReplacement = DREAMWOODS.getBiome("elder_woods")
                    .orElseGet(() -> spookyForest);
            ResourceKey<Biome> copperBeechReplacement = DREAMWOODS.getBiome("fairy_garden")
                    .orElseGet(() -> spookyForest);
            ResourceKey<Biome> magicalTaigaTransition = REGIONS_UNEXPLORED.getBiome("blackwood_taiga")
                    .orElseGet(() -> ancientOakSwampReplacement);
            ResourceKey<Biome> magicalSpookyTransition = DREAMWOODS.getBiome("old_growth_swampland")
                    .orElseGet(() -> ancientOakSwampReplacement);

            BiomePlacement.replaceOverworld(phantasmalSwamp, phantasmalSwampReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("ancient_moss_forest").get(), ancientMossyForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("ancient_oak_swamp").get(), ancientOakSwampReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("ancient_golden_beech_forest").get(), goldenBeechReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("ancient_copper_beech_forest").get(), copperBeechReplacement);
            transitionalBiome(phantasmalForest, WYTHERS.getBiome("ancient_taiga").get(), magicalTaigaTransition);
            transitionalEdgeBiome(phantasmalSwampReplacement, WYTHERS.getBiome("old_growth_taiga_swamp").get(), magicalTaigaTransition);
            transitionalEdgeBiome(phantasmalSwampReplacement, ancientOakSwampReplacement, magicalTaigaTransition);
            transitionalEdgeBiome(phantasmalForest, ancientOakSwampReplacement, magicalTaigaTransition);
            transitionalBiome(forbiddenForestReplacement, ancientOakSwampReplacement, magicalSpookyTransition);
            transitionalBiome(forbiddenForestReplacement, Biomes.MEADOW, coldAutumnalTransition);
            transitionalBiome(forbiddenForestReplacement, Biomes.OLD_GROWTH_SPRUCE_TAIGA, coldAutumnalTransition);
            transitionalBiome(Biomes.DARK_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, forbiddenForestReplacement);
            transitionalBiome(Biomes.DARK_FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA, forbiddenForestReplacement);
            transitionalBiome(Biomes.DARK_FOREST, Biomes.OLD_GROWTH_PINE_TAIGA, forbiddenForestReplacement);



            //<------------------------------SUBTROPICAL ECOSYSTEMS------------------------------>
            ResourceKey<Biome> woodedSavanna = WYTHERS.getBiome("wooded_savanna").get();
            ResourceKey<Biome> lapachoPlains = WYTHERS.getBiome("lapacho_plains").get();
            ResourceKey<Biome> bambooJungleHighlandsReplacement = NATURES_SPIRIT.getBiome("sugi_forest")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("bamboo_forest")
                            .orElseGet(() -> WYTHERS.getBiome("bamboo_jungle_highlands").get()));
            ResourceKey<Biome> subtropicalGrasslandReplacement = DREAMWOODS.getBiome("lush_grassland")
                            .orElseGet(() -> WYTHERS.getBiome("subtropical_grassland").get());
            ResourceKey<Biome> jacarandaSavannaReplacement = NATURES_SPIRIT.getBiome("flowering_shrubland")
                    .orElseGet(() -> WYTHERS.getBiome("jacaranda_savanna").get());
            ResourceKey<Biome> savannaPlateauReplacement = REGIONS_UNEXPLORED.getBiome("baobab_savanna")
                    .orElseGet(() -> DREAMWOODS.getBiome("savanna_grove")
                            .orElseGet(() -> WYTHERS.getBiome("jacaranda_savanna").get()));
            ResourceKey<Biome> savannaThicket = DREAMWOODS.getBiome("savanna_grove")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prairie")
                            .orElseGet(() -> WYTHERS.getBiome("jacaranda_savanna").get()));
            ResourceKey<Biome> scrublandSavannaTransition = REGIONS_UNEXPLORED.getBiome("grassland")
                    .orElseGet(() -> DREAMWOODS.getBiome("lush_grassland")
                            .orElseGet(() -> WYTHERS.getBiome("scrubland").get()));
            ResourceKey<Biome> mountainSavannaTransition = REGIONS_UNEXPLORED.getBiome("arid_mountains")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("arid_highlands")
                            .orElseGet(() -> WYTHERS.getBiome("cool_stony_peaks").get()));
            ResourceKey<Biome> volcanoSavannaTransition = WYTHERS.getBiome("savanna_badlands").get();

            BiomePlacement.replaceOverworld(WYTHERS.getBiome("bamboo_jungle_highlands").get(), bambooJungleHighlandsReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("sparse_bamboo_jungle").get(), bambooForest);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("jacaranda_savanna").get(), jacarandaSavannaReplacement);
            BiomePlacement.replaceOverworld(Biomes.SAVANNA_PLATEAU, savannaPlateauReplacement, 0.5F);
            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("subtropical_grassland").get(), subtropicalGrasslandReplacement);
            transitionalBiome(Biomes.SAVANNA, WYTHERS.getBiome("volcanic_crater").get(), volcanoSavannaTransition);
            transitionalBiome(Biomes.SAVANNA, Biomes.PLAINS, WYTHERS.getBiome("subtropical_grassland").get());
            transitionalBiome(Biomes.SAVANNA_PLATEAU, WYTHERS.getBiome("volcanic_crater").get(), volcanoSavannaTransition);
            transitionalBiome(Biomes.SAVANNA_PLATEAU, WYTHERS.getBiome("cool_stony_peaks").get(), mountainSavannaTransition);
            //transitionalEdgeBiome(Biomes.SAVANNA, WYTHERS.getBiome("scrubland").get(), scrublandSavannaTransition);
            transitionalEdgeBiome(woodedSavanna, lapachoPlains, jacarandaSavannaReplacement);
            clearingBiome(lapachoPlains, jacarandaSavannaReplacement, 0.35F);
            clearingBiome(Biomes.SAVANNA, savannaThicket, 0.2F);


            //<------------------------------TROPICAL ECOSYSTEMS------------------------------>
            ResourceKey<Biome> tropicalGrassland = WYTHERS.getBiome("tropical_grassland").get();
            ResourceKey<Biome> tropicalForest = WYTHERS.getBiome("tropical_forest").get();
                    ResourceKey<Biome> rainforestReplacement = REGIONS_UNEXPLORED.getBiome("rainforest")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("tropical_woods")
                            .orElseGet(() -> WYTHERS.getBiome("tropical_rainforest").get()));
            ResourceKey<Biome> floodedRainforestReplacement = NATURES_SPIRIT.getBiome("tropical_basin")
                    .orElseGet(() -> DREAMWOODS.getBiome("flooded_rainforest")
                            .orElseGet(() -> WYTHERS.getBiome("flooded_rainforest").get()));
            ResourceKey<Biome> tropicalGrasslandReplacement = REGIONS_UNEXPLORED.getBiome("dry_bushland")
                    .orElseGet(() -> DREAMWOODS.getBiome("arid_plains")
                            .orElseGet(() -> WYTHERS.getBiome("tropical_grassland").get()));
            ResourceKey<Biome> tropicalForestReplacement = NATURES_SPIRIT.getBiome("sparse_tropical_woods")
                    .orElseGet(() -> DREAMWOODS.getBiome("savanna_grove")
                            .orElseGet(() -> WYTHERS.getBiome("tropical_forest").get()));
            ResourceKey<Biome> eucalyptusWoodlandReplacement = REGIONS_UNEXPLORED.getBiome("eucalyptus_forest")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("dusty_slopes")
                            .orElseGet(() -> WYTHERS.getBiome("eucalyptus_woodland").get()));
            ResourceKey<Biome> sparseEucaWoodlandReplacement = REGIONS_UNEXPLORED.getBiome("dry_bushland")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("oak_savanna")
                            .orElseGet(() -> WYTHERS.getBiome("sparse_eucalyptus_woodland").get()));
            ResourceKey<Biome> subtropicalSavannaTransition = DREAMWOODS.getBiome("arid_plains")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prairie")
                            .orElseGet(() -> WYTHERS.getBiome("subtropical_grassland").get()));
            ResourceKey<Biome> tropicalSavannaTransition = NATURES_SPIRIT.getBiome("shrubland")
                    .orElseGet(() -> DREAMWOODS.getBiome("lush_grassland")
                            .orElseGet(() -> WYTHERS.getBiome("subtropical_grassland").get()));

            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("scrub_forest").get(), WYTHERS.getBiome("tropical_grassland").get());
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("tropical_grassland").get(), tropicalGrasslandReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("tropical_forest").get(), tropicalForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("eucalyptus_woodland").get(), eucalyptusWoodlandReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("sparse_eucalyptus_woodland").get(), sparseEucaWoodlandReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("eucalyptus_salubris_woodland").get(), sparseEucaWoodlandReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("tropical_rainforest").get(), rainforestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("flooded_rainforest").get(), floodedRainforestReplacement);

            transitionalEdgeBiome(Biomes.SAVANNA, WYTHERS.getBiome("humid_tropical_grassland").get(), subtropicalSavannaTransition);
            transitionalEdgeBiome(tropicalForestReplacement, WYTHERS.getBiome("scrub_forest").get(), tropicalSavannaTransition);
            transitionalEdgeBiome(tropicalForestReplacement, WYTHERS.getBiome("humid_tropical_grassland").get(), tropicalSavannaTransition);
            transitionalBiome(tropicalForestReplacement, WYTHERS.getBiome("tropical_forest_canyon").get(), tropicalSavannaTransition);
            transitionalBiome(Biomes.SAVANNA_PLATEAU, tropicalForestReplacement, savannaThicket);
            transitionalBiome(Biomes.SAVANNA, tropicalForestReplacement, tropicalSavannaTransition);
            transitionalBiome(woodedSavanna, tropicalForestReplacement, tropicalSavannaTransition);


            //<------------------------------BADLANDS ECOSYSTEMS------------------------------>
            ResourceKey<Biome> scrublandReplacement = NATURES_SPIRIT.getBiome("stratified_desert")
                            .orElseGet(() -> WYTHERS.getBiome("scrubland").get());
            ResourceKey<Biome> outbackDesertReplacement = NATURES_SPIRIT.getBiome("stratified_desert")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("dusty_slopes")
                            .orElseGet(() -> WYTHERS.getBiome("outback_desert").get()));
            ResourceKey<Biome> ayersRockReplacement = NATURES_SPIRIT.getBiome("arid_highlands")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("arid_mountains")
                            .orElseGet(() -> WYTHERS.getBiome("ayers_rock").get()));
            ResourceKey<Biome> redDesertReplacement = NATURES_SPIRIT.getBiome("scorched_dunes")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("dusty_slopes")
                            .orElseGet(() -> WYTHERS.getBiome("outback_desert").get()));
            ResourceKey<Biome> badlandsDesertReplacement = NATURES_SPIRIT.getBiome("lively_dunes")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("arid_mountains")
                            .orElseGet(() -> WYTHERS.getBiome("badlands_desert").get()));
            ResourceKey<Biome> chaparralReplacement = NATURES_SPIRIT.getBiome("xeric_plains")
                            .orElseGet(() -> WYTHERS.getBiome("chaparral").get());
            ResourceKey<Biome> savannaBadlandsTransition = NATURES_SPIRIT.getBiome("arid_highlands")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("arid_mountains")
                            .orElseGet(() -> WYTHERS.getBiome("scrubland").get()));
            ResourceKey<Biome> desertBadlandsTransition = NATURES_SPIRIT.getBiome("stratified_desert")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("arid_mountains")
                            .orElseGet(() -> WYTHERS.getBiome("scrubland").get()));

            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("outback_desert").get(), outbackDesertReplacement);
            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("ayers_rock").get(), ayersRockReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("red_desert").get(), redDesertReplacement);
            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("chaparral").get(), chaparralReplacement);
            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("scrubland").get(), scrublandReplacement);
            //BiomePlacement.replaceOverworld(WYTHERS.getBiome("badlands_desert").get(), badlandsDesertReplacement);
            transitionalEdgeBiome(Biomes.BADLANDS, WYTHERS.getBiome("scrubland").get(), savannaBadlandsTransition);
            transitionalBiome(WYTHERS.getBiome("granite_canyon").get(), WYTHERS.getBiome("scrubland").get(), savannaBadlandsTransition);
            transitionalBiome(WYTHERS.getBiome("scrub_forest").get(), WYTHERS.getBiome("badlands_desert").get(), desertBadlandsTransition);
            transitionalBiome(WYTHERS.getBiome("scrubland").get(), Biomes.ERODED_BADLANDS, desertBadlandsTransition);


            //<-----------------------------DESERT ECOSYSTEMS------------------------------>
            ResourceKey<Biome> woodedDesert = WYTHERS.getBiome("wooded_desert").get();
            ResourceKey<Biome> desertBeachReplacement = SPAWN.getBiome("rocky_shore")
                            .orElseGet(() -> desertBeach);
            ResourceKey<Biome> tropicalRiverReplacement = REGIONS_UNEXPLORED.getBiome("tropical_river")
                    .orElseGet(() -> WYTHERS.getBiome("savanna_river")
                            .orElseGet(() -> Biomes.RIVER));
            ResourceKey<Biome> desertPinnaclesReplacement = REGIONS_UNEXPLORED.getBiome("saguaro_desert")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("lively_dunes")
                            .orElseGet(() -> WYTHERS.getBiome("desert_pinnacles").get()));
            ResourceKey<Biome> cactusDesertReplacement = REGIONS_UNEXPLORED.getBiome("joshua_desert")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("stratified_desert")
                            .orElseGet(() -> WYTHERS.getBiome("cactus_desert").get()));
            ResourceKey<Biome> dryTropicalGrasslandReplacement = NATURES_SPIRIT.getBiome("stratified_desert")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("drylands")
                            .orElseGet(() -> WYTHERS.getBiome("dry_tropical_grassland").get()));
            ResourceKey<Biome> dryTropicalForestReplacement = REGIONS_UNEXPLORED.getBiome("dry_bushland")
                    .orElseGet(() -> NATURES_SPIRIT.getBiome("wooded_drylands")
                            .orElseGet(() -> WYTHERS.getBiome("dry_tropical_forest").get()));
            ResourceKey<Biome> drySavannaReplacement = NATURES_SPIRIT.getBiome("arid_savanna")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("arid_mountains")
                            .orElseGet(() -> WYTHERS.getBiome("dry_savanna").get()));
            ResourceKey<Biome> danakilDesertReplacement = NATURES_SPIRIT.getBiome("blooming_dunes")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("tropics")
                            .orElseGet(() -> WYTHERS.getBiome("danakil_desert").get()));
            ResourceKey<Biome> desertBarrenAlternate = NATURES_SPIRIT.getBiome("stratified_desert")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("saguaro_desert")
                            .orElseGet(() -> Biomes.DESERT));
            ResourceKey<Biome> desertLivelyAlternate = REGIONS_UNEXPLORED.getBiome("saguaro_desert")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("saguaro_desert")
                            .orElseGet(() -> WYTHERS.getBiome("cactus_desert").get()));

            BiomePlacement.replaceOverworld(desertBeach, desertBeachReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("desert_pinnacles").get(), desertPinnaclesReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("cactus_desert").get(), cactusDesertReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("dry_tropical_grassland").get(), dryTropicalGrasslandReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("dry_tropical_forest").get(), dryTropicalForestReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("dry_savanna").get(), drySavannaReplacement);
            transitionalBiome(Biomes.DESERT, WYTHERS.getBiome("danakil_desert").get(), desertLivelyAlternate);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("danakil_desert").get(), danakilDesertReplacement);
            transitionalEdgeBiome(Biomes.DESERT, dryTropicalGrasslandReplacement, desertLivelyAlternate);
            transitionalEdgeBiome(Biomes.DESERT, dryTropicalForestReplacement, desertLivelyAlternate);
            transitionalBiome(Biomes.DESERT, Biomes.BADLANDS, dryTropicalGrasslandReplacement);
            transitionalBiome(Biomes.DESERT, tropicalForestReplacement, WYTHERS.getBiome("sandy_jungle").get());
            transitionalBiome(WYTHERS.getBiome("badlands_desert").get(), cactusDesertReplacement, desertBadlandsTransition);
            transitionalBiome(Biomes.DESERT, WYTHERS.getBiome("ayers_rock").get(), woodedDesert);
            edgeBiome(Biomes.DESERT, desertLivelyAlternate, BiomeParameterTargets.HUMIDITY);


            //<------------------------------CAVE ECOSYSTEM------------------------------>
            ResourceKey<Biome> underground = WYTHERS.getBiome("underground").get();
            ResourceKey<Biome> undergroundDeep = WYTHERS.getBiome("deep_underground").get();
            ResourceKey<Biome> rockyCavesReplacement = GALOSPHERE.getBiome("pink_salt_caves")
                            .orElseGet(() -> WYTHERS.getBiome("calcite_caverns").get());
            ResourceKey<Biome> lichenousCavesReplacement = GALOSPHERE.getBiome("lichen_caves")
                    .orElseGet(() -> QUARK.getBiome("glimmering_weald")
                            .orElseGet(() -> WYTHERS.getBiome("lichenous_caves").get()));
            ResourceKey<Biome> rareGemsCave = GALOSPHERE.getBiome("crystal_canyons")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("prismachasm")
                            .orElseGet(() -> WYTHERS.getBiome("lush_fungous_dripstone_caves").get()));
            ResourceKey<Biome> rareShroomyCave = DARKERDEPTHS.getBiome("glowshroom_forest")
                    .orElseGet(() -> QUARK.getBiome("glimmering_weald")
                            .orElseGet(() -> WYTHERS.getBiome("lush_shroom_caves").get()));
            ResourceKey<Biome> rareLushCave = REGIONS_UNEXPLORED.getBiome("ancient_delta")
                    .orElseGet(() -> DARKERDEPTHS.getBiome("glowshroom_forest")
                            .orElseGet(() -> WYTHERS.getBiome("mossy_dripstone_caves").get()));
            ResourceKey<Biome> lavaCaveClearing = REGIONS_UNEXPLORED.getBiome("inferno")
                    .orElseGet(() -> DARKERDEPTHS.getBiome("molten_cavern")
                            .orElseGet(() -> WYTHERS.getBiome("deep_underground").get()));
            ResourceKey<Biome> gemCaveClearing = REGIONS_UNEXPLORED.getBiome("redstone_caves")
                            .orElseGet(() -> WYTHERS.getBiome("deep_underground").get());
            ResourceKey<Biome> shroomCaveClearing = QUARK.getBiome("glimmering_weald")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("bioshroom_caves")
                            .orElseGet(() -> WYTHERS.getBiome("mushroom_caves").get()));
            ResourceKey<Biome> sandyCaveClearing = YUNGSCAVEBIOMES.getBiome("lost_caves")
                    .orElseGet(() -> UNDERGROUNDWORLDS.getBiome("underground_desert")
                            .orElseGet(() -> DARKERDEPTHS.getBiome("sandy_catacombs")
                                .orElseGet(() -> underground)));
            ResourceKey<Biome> icyCaveClearing = YUNGSCAVEBIOMES.getBiome("frosted_caves")
                    .orElseGet(() -> UNDERGROUNDWORLDS.getBiome("underground_tundra")
                            .orElseGet(() -> underground));
            ResourceKey<Biome> lushCaveClearing = UNDERGROUNDWORLDS.getBiome("underground_jungle")
                    .orElseGet(() -> REGIONS_UNEXPLORED.getBiome("ancient_delta")
                            .orElseGet(() -> underground));
            ResourceKey<Biome> aridCaveClearing = SPAWN.getBiome("ant_gardens")
                            .orElseGet(() -> underground);
            ResourceKey<Biome> spookyCaveClearing = UNDERGROUNDWORLDS.getBiome("spider_cave")
                            .orElseGet(() -> underground);

            BiomePlacement.replaceOverworld(WYTHERS.getBiome("lichenous_caves").get(), lichenousCavesReplacement);
            BiomePlacement.replaceOverworld(WYTHERS.getBiome("calcite_caverns").get(), rockyCavesReplacement);
            BiomePlacement.replaceOverworld(undergroundDeep, lavaCaveClearing, 0.4F);
            BiomePlacement.replaceOverworld(undergroundDeep, gemCaveClearing, 0.4F);
            transitionalBiome(Biomes.DEEP_DARK, underground, Biomes.DEEP_DARK);
            transitionalBiome(WYTHERS.getBiome("lush_fungous_dripstone_caves").get(), underground, rareGemsCave);
            transitionalBiome(WYTHERS.getBiome("calcite_caverns").get(), underground, spookyCaveClearing);
            transitionalBiome(Biomes.DESERT, underground, sandyCaveClearing);
            transitionalBiome(rareLushCave, underground, sandyCaveClearing);
            transitionalBiome(lichenousCavesReplacement, underground, sandyCaveClearing);
            transitionalBiome(Biomes.ICE_SPIKES, underground, icyCaveClearing);
            transitionalBiome(icyCrags, underground, icyCaveClearing);
            transitionalBiome(frigidIsland, underground, icyCaveClearing);
            transitionalBiome(icyOcean, underground, icyCaveClearing);
            transitionalBiome(deepIcyOcean, underground, icyCaveClearing);
            transitionalBiome(Biomes.DARK_FOREST, underground, rareLushCave);
            transitionalBiome(goldenBeechReplacement, underground, rareLushCave);
            transitionalBiome(ancientMossyForestReplacement, underground, rareLushCave);
            transitionalBiome(lichenousCavesReplacement, underground, rareLushCave);
            transitionalBiome(DREAMWOODS.getBiome("grim_woods").get(), underground, spookyCaveClearing);
            transitionalBiome(DREAMWOODS.getBiome("thaumic_grove").get(), underground, rareLushCave);
            transitionalBiome(WYTHERS.getBiome("lush_shroom_caves").get(), underground, rareShroomyCave);
            transitionalBiome(WYTHERS.getBiome("mushroom_caves").get(), underground, shroomCaveClearing);
            clearingBiome(undergroundDeep, gemCaveClearing, 0.5F);


            //<------------------------------END ECOSYSTEM------------------------------>
            ResourceKey<Biome> theEnd = Biomes.THE_END;
            ResourceKey<Biome> endBarrens = Biomes.END_BARRENS;
            ResourceKey<Biome> endMidlands = Biomes.END_MIDLANDS;
            ResourceKey<Biome> endHighlands = Biomes.END_HIGHLANDS;
            ResourceKey<Biome> endIslandClearing = UNUSUALEND.getBiome("warped_reef")
                            .orElseGet(() -> endBarrens);
            ResourceKey<Biome> endMidlandsTransition = UNUSUALEND.getBiome("gloopstone_midlands")
                            .orElseGet(() -> endMidlands);

            transitionalBiome(endMidlands, endMidlandsTransition, endMidlandsTransition);
            clearingBiome(theEnd, endIslandClearing, 0.3F);
        }
    }

    public static void clearingBiome(ResourceKey<Biome> firstBiome, ResourceKey<Biome> secondBiome, float size) {
        BiomePlacement.addSubOverworld(
            firstBiome,
            secondBiome,
            allOf(
                ratioMax(RatioTargets.CENTER, size),
                not(NEAR_BORDER)
            )
        );
    }

    public static void patchySubBiome(ResourceKey<Biome> firstBiome, ResourceKey<Biome> secondBiome) {
        BiomePlacement.addSubOverworld(
            firstBiome,
            secondBiome,
            allOf(
                deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .5F),
                not(NEAR_BORDER)
            )
        );
    }

    public static void transitionalBiome(ResourceKey<Biome> firstBiome, ResourceKey<Biome> secondBiome, ResourceKey<Biome> transitionalBiome) {
        BiomePlacement.addSubOverworld(
                firstBiome,
                transitionalBiome,
                allOf(neighbor(secondBiome), not(NEAR_INTERIOR))
        );

        BiomePlacement.addSubOverworld(
                secondBiome,
                transitionalBiome,
                allOf(neighbor(firstBiome), not(NEAR_INTERIOR))
        );

//        BiomePlacement.addSubOverworld(
//                secondBiome,
//                transitionalBiome,
//                allOf(alternate(transitionalBiome, firstBiome), not(NEAR_INTERIOR))
//        );
//
//        BiomePlacement.addSubOverworld(
//                firstBiome,
//                transitionalBiome,
//                allOf(alternate(transitionalBiome, secondBiome), not(NEAR_INTERIOR))
//        );
    }

    public static void transitionalEdgeBiome(ResourceKey<Biome> firstBiome, ResourceKey<Biome> secondBiome, ResourceKey<Biome> transitionalBiome) {
        BiomePlacement.addSubOverworld(
                firstBiome,
                transitionalBiome,
                allOf(
                    neighbor(secondBiome),
                    NEAR_BORDER,
                    not(NEAR_INTERIOR)
                )
        );

        BiomePlacement.addSubOverworld(
                secondBiome,
                transitionalBiome,
                allOf(
                    neighbor(firstBiome),
                    NEAR_BORDER,
                    not(NEAR_INTERIOR)
                )
        );

        BiomePlacement.addSubOverworld(
                secondBiome,
                transitionalBiome,
                allOf(alternate(transitionalBiome, firstBiome), NEAR_BORDER, not(NEAR_INTERIOR))
        );

        BiomePlacement.addSubOverworld(
                firstBiome,
                transitionalBiome,
                allOf(alternate(transitionalBiome, secondBiome), NEAR_BORDER, not(NEAR_INTERIOR))
        );
    }



    public static void edgeReplacement(ResourceKey<Biome> originalBiome, ResourceKey<Biome> borderBiome, ResourceKey<Biome> replacementBiome) {
        BiomePlacement.addSubOverworld(
            originalBiome,
            replacementBiome,
            allOf(neighbor(borderBiome))
        );
    }

    public static void edgeBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> edgeBiome, BiomeParameterTargets biomeParameterTarget) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                edgeBiome,
                allOf(
                    deviationMin(biomeParameterTarget, .05F),
                    anyOf(
                        allOf(NEAR_BORDER, not(NEAR_INTERIOR)),
                        BEACHSIDE,
                        OCEANSIDE,
                        allOf(NEAR_BORDER, neighbor(BiomeTags.IS_RIVER))
                    )
                )
        );
    }

    public static void landEdgeBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> edgeBiome, BiomeParameterTargets biomeParameterTarget) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                edgeBiome,
                allOf(
                    deviationMin(biomeParameterTarget, .05F),
                    allOf(
                        allOf(NEAR_BORDER, not(NEAR_INTERIOR)),
                        allOf(
                            not(OCEANSIDE),
                            not(allOf(NEAR_BORDER, neighbor(BiomeTags.IS_RIVER)))
                        )
                    )
                )
        );
    }

    public static void waterEdgeBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> edgeBiome, BiomeParameterTargets biomeParameterTarget) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                edgeBiome,
                allOf(
                    deviationMin(biomeParameterTarget, .05F),
                    allOf(
                        allOf(NEAR_BORDER, not(NEAR_INTERIOR)),
                        anyOf(
                            OCEANSIDE,
                            allOf(NEAR_BORDER, neighbor(BiomeTags.IS_RIVER))
                        )
                    )
                )
        );
    }
}
