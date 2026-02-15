package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.neoforge.config.PEConfig;
import com.google.gson.JsonElement;
import net.enderturret.patchedmod.Patched;
import net.enderturret.patchedmod.util.PatchUtil;
import net.minecraft.resources.ResourceLocation;

public class TestConditions {

    public static void registerConditions() {
        ProjectEvergreen.LOGGER.info("Registering Project Evergreen Test Conditions");

        //TODO: Replace with inherited methods inside IPatchable, maybe?
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.PERFORMANCE_MODE_KEY), TestConditions::performanceMode);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.STRUCTURE_BIOME_REDISTRIBUTION_KEY), TestConditions::structureBiomeRedistribution);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.STRUCTURE_RARITY_REDISTRIBUTION_KEY), TestConditions::structureRarityRedistribution);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.COLD_CLIMATE_WATER_NORMALIZATION_KEY), TestConditions::coldClimateWaterNormalization);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.TEMPERATE_CLIMATE_WATER_NORMALIZATION_KEY), TestConditions::temperateClimateWaterNormalization);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.WARM_CLIMATE_WATER_NORMALIZATION_KEY), TestConditions::warmClimateWaterNormalization);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.POPULATION_BIAS_KEY), TestConditions::populationBiasCheck);
	}

    private static boolean performanceMode(JsonElement value) {
		return PEConfig.performanceFriendlyMode == value.getAsBoolean();
	}
    
    private static boolean structureBiomeRedistribution(JsonElement value) {
        return PEConfig.structureBiomeRedistribution == value.getAsBoolean();
	}
    
    private static boolean structureRarityRedistribution(JsonElement value) {
		return PEConfig.structureRarityRedistribution == value.getAsBoolean();
	}

    private static boolean coldClimateWaterNormalization(JsonElement value) {
		return PEConfig.coldBiomeColorNormalization == value.getAsBoolean();
	}

    private static boolean temperateClimateWaterNormalization(JsonElement value) {
		return PEConfig.temperateBiomeColorNormalization == value.getAsBoolean();
	}

    private static boolean warmClimateWaterNormalization(JsonElement value) {
		return PEConfig.warmBiomeColorNormalization == value.getAsBoolean();
	}

    //TODO: Replace with local Patchable Structure method
    private static boolean populationBiasCheck(JsonElement value) {
        final ResourceLocation id = PatchUtil.assertIsResourceLocation(PEConfig.POPULATION_BIAS_TEST, "value", value);
        return WorldgenDataManager.PATCHABLE_STRUCTURES.get(id.toString()).hasPopulationBias(PEConfig.populationBias);
    }
}
