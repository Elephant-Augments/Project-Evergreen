package com.elephantaugments.projectevergreen.neoforge.world;

import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import static com.terraformersmc.biolith.api.biome.sub.CriterionBuilder.*;

public class PEBiomePlacement {

    public static void register() {

        BiomePlacement.replaceOverworld(
            Biomes.PLAINS,
            SupportedMods.NATURES_SPIRIT.getBiome("blooming_highlands")
        );

        BiomePlacement.addSubOverworld(
                SupportedMods.WYTHERS.getBiome("cold_island"),
                Biomes.BADLANDS,
                deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .5F)
        );

        BiomePlacement.addSubOverworld(
                SupportedMods.WYTHERS.getBiome("temperate_island"),
                SupportedMods.NATURES_SPIRIT.getBiome("redwood_forest"),
                deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .5F)
        );

        transitionalBiome(SupportedMods.WYTHERS.getBiome("temperate_island"), SupportedMods.WYTHERS.getBiome("volcano"), SupportedMods.REGIONS_UNEXPLORED.getBiome("ashen_woodland"));
        clearingBiome(SupportedMods.WYTHERS.getBiome("temperate_island"), SupportedMods.NATURES_SPIRIT.getBiome("redwood_forest"));
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

    public static void clearingBiome(ResourceKey<Biome> interiorBiome, ResourceKey<Biome> clearingBiome) {
        BiomePlacement.addSubOverworld(
                interiorBiome,
                clearingBiome,
                allOf(deviationMin(BiomeParameterTargets.PEAKS_VALLEYS, .05F), NEAR_INTERIOR, not(NEAR_BORDER))
        );
    }
}
