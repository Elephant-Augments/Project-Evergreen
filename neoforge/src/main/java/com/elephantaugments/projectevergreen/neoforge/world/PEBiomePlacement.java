package com.elephantaugments.projectevergreen.neoforge.world;

import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.*;

public class PEBiomePlacement {

    public static void register() {

        //<------------------------------PLAINS ECOSYSTEM------------------------------>
        ResourceKey<Biome> plainsScatteredTrees = SupportedMods.REGIONS_UNEXPLORED.getBiome("orchard")
                        .orElseGet(() -> Biomes.BIRCH_FOREST);
        ResourceKey<Biome> plainsCultivatedFields = SupportedMods.REGIONS_UNEXPLORED.getBiome("barley_fields")
                .orElseGet(() -> SupportedMods.NATURES_SPIRIT.getBiome("lavender_fields")
                        .orElseGet(() -> Biomes.SUNFLOWER_PLAINS));
        centralSubBiome(Biomes.PLAINS, plainsScatteredTrees);
        clearingBiome(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS);

        //<------------------------------FOREST ECOSYSTEM------------------------------>
        ResourceKey<Biome> forestMarshClearing = SupportedMods.NATURES_SPIRIT.getBiome("marsh")
                .orElseGet(() -> SupportedMods.REGIONS_UNEXPLORED.getBiome("marsh")
                        .orElseGet(() -> Biomes.FOREST));
        ResourceKey<Biome> forestClearingEdge = SupportedMods.REGIONS_UNEXPLORED.getBiome("fen")
                .orElseGet(() -> Biomes.FOREST);
        ResourceKey<Biome> forestWoodyAlternate = SupportedMods.REGIONS_UNEXPLORED.getBiome("deciduous_forest")
                .orElseGet(() -> Biomes.FOREST);
        clearingBiome(Biomes.FOREST, forestMarshClearing);
        centralSubBiome(Biomes.FOREST, forestWoodyAlternate);
        transitionalBiome(forestMarshClearing, Biomes.FOREST, forestClearingEdge);


        //WYTHERS
        if (ProjectEvergreenNeoforge.PLATFORM.isModLoaded(SupportedMods.WYTHERS.name()))
        {
            //<------------------------------TEMPERATE ISLAND ECOSYSTEM------------------------------>
            ResourceKey<Biome> temperateIslandThicket = SupportedMods.REGIONS_UNEXPLORED.getBiome("redwoods")
                    .orElseGet(() -> SupportedMods.NATURES_SPIRIT.getBiome("redwood_forest")
                            .orElseGet(() -> Biomes.FOREST));

            centralSubBiome(SupportedMods.WYTHERS.getBiome("temperate_island").get(), temperateIslandThicket);

            //<------------------------------GRAVEL BEACH ECOSYSTEM------------------------------>
            ResourceKey<Biome> gravelBeachTransition = SupportedMods.REGIONS_UNEXPLORED.getBiome("gravel_beach")
                    .orElseGet(() -> SupportedMods.WYTHERS.getBiome("forest_edge")
                            .orElseGet(() -> Biomes.FOREST));
            transitionalBiome(Biomes.FOREST, SupportedMods.WYTHERS.getBiome("gravelly_beach").get(), gravelBeachTransition);

        }


        /*BiomePlacement.replaceOverworld(
            Biomes.PLAINS,
            SupportedMods.NATURES_SPIRIT.getBiome("blooming_highlands")
        );

        BiomePlacement.addSubOverworld(
                Biomes.FOREST,
                SupportedMods.NATURES_SPIRIT.getBiome("marsh"),
                deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .5F)
        );

        transitionalBiome(SupportedMods.WYTHERS.getBiome("temperate_island"), SupportedMods.WYTHERS.getBiome("volcano"), SupportedMods.REGIONS_UNEXPLORED.getBiome("ashen_woodland"));
        clearingBiome(SupportedMods.WYTHERS.getBiome("temperate_island"), SupportedMods.NATURES_SPIRIT.getBiome("redwood_forest"));
         */
    }

    public static void clearingBiome(ResourceKey<Biome> firstBiome, ResourceKey<Biome> secondBiome) {
        BiomePlacement.addSubOverworld(
            firstBiome,
            secondBiome,
            deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .5F)
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

        BiomePlacement.addSubOverworld(
                secondBiome,
                transitionalBiome,
                allOf(alternate(transitionalBiome, firstBiome), not(NEAR_INTERIOR))
        );

        BiomePlacement.addSubOverworld(
                firstBiome,
                transitionalBiome,
                allOf(alternate(transitionalBiome, secondBiome), not(NEAR_INTERIOR))
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

    public static void centralSubBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> clearingBiome) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                clearingBiome,
                allOf(deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .05F), NEAR_INTERIOR, not(NEAR_BORDER))
        );
    }
}
