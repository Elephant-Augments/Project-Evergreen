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
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.RPGJEWELRY_SINGLE_VILLAGER_KEY), TestConditions::rpgJewelrySingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.RPGWIZARDS_SINGLE_VILLAGER_KEY), TestConditions::rpgWizardsSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.RPGWARRIORS_SINGLE_VILLAGER_KEY), TestConditions::rpgWarriorsSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.RPGPRIESTS_SINGLE_VILLAGER_KEY), TestConditions::rpgPriestsSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.RPGARCHERS_SINGLE_VILLAGER_KEY), TestConditions::rpgArchersSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.PNEUMATICCRAFT_SINGLE_VILLAGER_KEY), TestConditions::pneumaticcraftSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.LOOTNEXPLORE_SINGLE_VILLAGER_KEY), TestConditions::lootnExploreSingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.BEAUTIFY_SINGLE_VILLAGER_KEY), TestConditions::beautifySingleVillager);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.FARMERSDELIGHT_VILLAGE_COMPOST_KEY), TestConditions::farmersdelightVillageCompostLimit);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, PEConfig.VEGGIESDELIGHT_VILLAGE_DEPOT_KEY), TestConditions::veggiesdelightVillageDepotLimit);
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

    private static boolean rpgJewelrySingleVillager(JsonElement value) {
		return PEConfig.rpgJewelrySingleVillager == value.getAsBoolean();
	}

    private static boolean rpgWizardsSingleVillager(JsonElement value) {
		return PEConfig.rpgWizardsSingleVillager == value.getAsBoolean();
	}

    private static boolean rpgWarriorsSingleVillager(JsonElement value) {
		return PEConfig.rpgWarriorsSingleVillager == value.getAsBoolean();
	}

    private static boolean rpgPriestsSingleVillager(JsonElement value) {
		return PEConfig.rpgPriestsSingleVillager == value.getAsBoolean();
	}

    private static boolean rpgArchersSingleVillager(JsonElement value) {
		return PEConfig.rpgArchersSingleVillager == value.getAsBoolean();
	}

    private static boolean pneumaticcraftSingleVillager(JsonElement value) {
		return PEConfig.pneumaticcraftSingleVillager == value.getAsBoolean();
	}

    private static boolean lootnExploreSingleVillager(JsonElement value) {
		return PEConfig.lootnExploreSingleVillager == value.getAsBoolean();
	}

    private static boolean beautifySingleVillager(JsonElement value) {
		return PEConfig.beautifySingleVillager == value.getAsBoolean();
	}

    private static boolean farmersdelightVillageCompostLimit(JsonElement value) {
		return PEConfig.farmersDelightVillageCompostLimit == value.getAsBoolean();
	}

    private static boolean veggiesdelightVillageDepotLimit(JsonElement value) {
		return PEConfig.veggiesDelightVillageDepotLimit == value.getAsBoolean();
	}

    //TODO: Replace with local Patchable Structure method
    private static boolean populationBiasCheck(JsonElement value) {
        final ResourceLocation id = PatchUtil.assertIsResourceLocation(PEConfig.POPULATION_BIAS_TEST, "value", value);
        return WorldgenDataManager.PATCHABLE_STRUCTURES.get(id.toString()).hasPopulationBias(PEConfig.populationBias);
    }
}
