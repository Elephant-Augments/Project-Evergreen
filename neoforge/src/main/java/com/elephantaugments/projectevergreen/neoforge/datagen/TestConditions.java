package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.neoforge.config.ProjectEvergreenConfig;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultBlacklist;
import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureFixes;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import net.enderturret.patched.patch.PatchContext;
import net.enderturret.patchedmod.Patched;
import net.enderturret.patchedmod.util.PatchUtil;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestConditions {

    public static List<String> ALL_FLAT_STRUCTURES;
    public static List<String> ALL_DECO_STRUCTURES;
    public static List<String> ALL_MASSIVE_STRUCTURES;
    public static List<String> ALL_WATER_STRUCTURES;
    public static List<String> ALL_UNDERGROUND_STRUCTURES;
    public static List<String> ALL_CIVILIZATION_STRUCTURES;
    public static List<String> ALL_WILDERNESS_STRUCTURES;
    public static List<String> ALL_IGNORED_BIAS_STRUCTURES;

    public static void registerConditions() {
        ProjectEvergreen.LOGGER.info("Registering Project Evergreen Test Conditions");
        ALL_UNDERGROUND_STRUCTURES = filterUndergroundStructures();
        ALL_WATER_STRUCTURES = filterWaterStructures();
        ALL_MASSIVE_STRUCTURES = filterMassiveStructures();
        ALL_DECO_STRUCTURES = filterDecoStructures();
        ProjectEvergreen.LOGGER.info("Checking all Deco structures... " + ALL_DECO_STRUCTURES.size());
        ALL_FLAT_STRUCTURES = filterFlatStructures();
        ALL_CIVILIZATION_STRUCTURES = filterCivilizationStructures();
        ProjectEvergreen.LOGGER.info("Checking all Civilization structures... " + ALL_CIVILIZATION_STRUCTURES.size());
        ALL_WILDERNESS_STRUCTURES = filterWildernessStructures();
        ProjectEvergreen.LOGGER.info("Checking all Wilderness structures... " + ALL_WILDERNESS_STRUCTURES.size());
        ALL_IGNORED_BIAS_STRUCTURES = filterBiasIgnoredStructures();
        
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "structure_registered"), TestConditions::structureRegistered);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, Constants.IS_BIOME_REGISTERED_TEST), TestConditions::biomeRegistered);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "performance_mode"), TestConditions::performanceMode);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "structure_biome_redistribution"), TestConditions::structureBiomeRedistribution);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "structure_rarity_redistribution"), TestConditions::structureRarityRedistribution);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "flatness_check_ignored"), TestConditions::flatnessCheckIgnored);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "flat_check_narrow"), TestConditions::flatCheckNarrow);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "flat_check_wide"), TestConditions::flatCheckWide);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, DefaultStructureFixes.FLATNESS_CHECK_SPRAWLING), TestConditions::flatCheckSprawling);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, Constants.ENHANCED_TERRAIN_ADAPTATION), TestConditions::enhancedTerrainAdaptation);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, DefaultBlacklist.IGNORE_BIOME_RADIUS), TestConditions::biomeRadiusIgnored);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, DefaultBlacklist.IGNORE_BIOME_REDISTRIBUTION), TestConditions::biomeRedistributionIgnored);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "population_bias_check"), TestConditions::populationBiasCheck);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "safe_structure_type"), TestConditions::safeStructureType);
        Patched.registerTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "advanced_structure_type"), TestConditions::advancedStructureType);

		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "water_structure"), TestConditions::waterStructure);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "deco_structure"), TestConditions::decoStructure);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "massive_structure"), TestConditions::massiveStructure);
        Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "underground_structure"), TestConditions::undergroundStructure);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "inland_structure"), TestConditions::inlandStructure);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "civilization_structure"), TestConditions::civilizationStructure);
		Patched.registerSimpleTestCondition(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "wilderness_structure"), TestConditions::wildernessStructure);
	}
    
    private static boolean performanceMode(JsonElement value) {
		return ProjectEvergreenConfig.performanceFriendlyMode == value.getAsBoolean();
	}
    
    private static boolean structureBiomeRedistribution(JsonElement value) {
        return ProjectEvergreenConfig.structureBiomeRedistribution == value.getAsBoolean();
	}
    
    private static boolean structureRarityRedistribution(JsonElement value) {
		return ProjectEvergreenConfig.structureRarityRedistribution == value.getAsBoolean();
	}

    private static boolean decoStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.MASSIVE_STRUCTURE_TEST, "value", value);
        return isDecoStructure(id.toString());
	}

    private static boolean massiveStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.MASSIVE_STRUCTURE_TEST, "value", value);
        return isMassiveStructure(id.toString());
	}

    private static boolean flatnessCheckIgnored(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(ProjectEvergreen.MODID + ":flatness_check_ignored", "value", value);
        return isFlatnessCheckIgnored(id.toString());
	}
    
    private static boolean undergroundStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(ProjectEvergreen.MODID + ":underground_structure", "value", value);
        return isUndergroundStructure(id.toString());
	}

    private static boolean waterStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.WATER_STRUCTURE_TEST, "value", value);
        return isWaterStructure(id.toString());
	}

    private static boolean civilizationStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.WATER_STRUCTURE_TEST, "value", value);
        return isCivilizationStructure(id.toString());
	}

    private static boolean wildernessStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.WATER_STRUCTURE_TEST, "value", value);
        return isWildernessStructure(id.toString());
	}

    private static boolean inlandStructure(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.INLAND_STRUCTURE_TEST, "value", value);
		//final ResourceLocation spawn_step = PatchUtil.assertIsResourceLocation(Constants.INLAND_STRUCTURE_TEST, "target", target);
        return isInlandStructure(id.toString());
	}

    private static boolean flatCheckNarrow(JsonElement value) {
        final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.FLAT_NARROW_TEST, "value", value);
        return ((isFlatStructure(id.toString()) && !isMassiveStructure(id.toString())) ||
                DefaultStructureFixes.flatnessCheckNarrow.contains(id.toString()));
	}

    private static boolean flatCheckWide(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.FLAT_WIDE_TEST, "value", value);
        return ((isFlatStructure(id.toString()) && isMassiveStructure(id.toString())) ||
                DefaultStructureFixes.flatnessCheckWide.contains(id.toString()));
	}

    private static boolean flatCheckSprawling(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.FLAT_WIDE_TEST, "value", value);
        return isSprawlingStructure(id.toString());
	}

    private static boolean enhancedTerrainAdaptation(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.TERRAIN_ADAPTATION_TEST, "value", value);
        return ProjectEvergreenConfig.addTerrainAdaptation.indexOf(id.toString()) > 0;
	}

    private static boolean biomeRedistributionIgnored(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(DefaultBlacklist.IGNORE_BIOME_REDISTRIBUTION, "value", value);
        return isBiomeRedistributionIgnored(id.toString());
	}

    private static boolean biomeRadiusIgnored(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(DefaultBlacklist.IGNORE_BIOME_RADIUS, "value", value);
        return isBiasIgnored(id.toString());
	}

    private static boolean populationBiasCheck(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.POPULATION_BIAS_TEST, "value", value);
        return hasPopulationBias_Structure(id.toString()) && !isBiasIgnored(id.toString());
	}

    private static boolean safeStructureType(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.SAFE_STRUCTURE_TYPE, "value", value);
        return isSafeStructureType(id.toString());
	}

    private static boolean advancedStructureType(JsonElement root, JsonElement target, JsonElement value, PatchContext context) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(Constants.ADVANCED_STRUCTURE_TYPE, "value", value);
        final ResourceLocation type = PatchUtil.assertIsResourceLocation(Constants.ADVANCED_STRUCTURE_TYPE, "target", target);
        return  flatStructureType(type.toString()) && 
                !isUndergroundStructure(id.toString()) &&
                !isStructureTypeIgnored(id.toString());
	}

    private static boolean biomeRegistered(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(ProjectEvergreen.MODID + ":" + Constants.IS_BIOME_REGISTERED_TEST, "value", value);
        return isBiomeLoaded(id.toString());
	}

    private static boolean structureRegistered(JsonElement value) {
		final ResourceLocation id = PatchUtil.assertIsResourceLocation(ProjectEvergreen.MODID + ":structure_registered", "value", value);
        return isStructureLoaded(id.toString());
	}

    public static boolean isDecoStructure(String id) {
        return ALL_DECO_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isMassiveStructure(String id) {
        return ALL_MASSIVE_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isSprawlingStructure(String id) {
        return DefaultStructureFixes.flatnessCheckSprawling.contains(id);
    }

    public static boolean isFlatStructure(String id) {
        return (ALL_FLAT_STRUCTURES.indexOf(id) > 0);
    }

    public static boolean isSafeStructureType(String id) {
        return DefaultStructureFixes.safeStructureType.indexOf(id) > 0;
    }

    public static boolean isUndergroundStructure(String id) {
        return ALL_UNDERGROUND_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isWaterStructure(String id) {
        return ALL_WATER_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isCivilizationStructure(String id) {
        return ALL_CIVILIZATION_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isWildernessStructure(String id) {
        return ALL_WILDERNESS_STRUCTURES.indexOf(id) > 0;
    }

    public static boolean isInlandStructure(String id) {
        return !isWaterStructure(id) && !isUndergroundStructure(id);
    }

    public static boolean isBiomeLoaded(String id) {
        //ProjectEvergreen.LOGGER.info("Checking loaded biomes... " + Constants.loadedBiomes.values().size());

        return Constants.loadedBiomes.stream().anyMatch(id::equals);
    }

    public static boolean isStructureLoaded(String id) {
        //ProjectEvergreen.LOGGER.info("Checking loaded structures... " + Constants.loadedStructures.values().size());

        return Constants.loadedStructures.stream().anyMatch(id::equals);
    }

    public static boolean isIgnored(String id) {
        return ProjectEvergreenConfig.structuresByFix.entries().stream()
            .filter(e -> Constants.getNamespace(id).equals(e.getValue()) || id.equals(e.getValue()))
            .map(Map.Entry::getKey)
            .anyMatch(e -> e.equals(DefaultBlacklist.IGNORE_MOD) || e.equals(DefaultBlacklist.IGNORE_STRUCTURE));
    }

    public static boolean isStructureTypeIgnored(String id) {
        return ProjectEvergreenConfig.structuresByFix.entries().stream()
            .filter(e -> id.equals(e.getValue()))
            .map(Map.Entry::getKey)
            .anyMatch(e -> e.equals(DefaultBlacklist.IGNORE_STRUCTURE_TYPE));
    }

    public static boolean isBiomeRedistributionIgnored(String id) {
        return ProjectEvergreenConfig.structuresByFix.entries().stream()
            .filter(e -> id.equals(e.getValue()))
            .map(Map.Entry::getKey)
            .anyMatch(e -> e.equals(DefaultBlacklist.IGNORE_BIOME_REDISTRIBUTION));
    }

    public static boolean isBiasIgnored(String id) {
        return (ALL_IGNORED_BIAS_STRUCTURES.indexOf(id) > 0) || (DefaultBlacklist.ignoreBiomeRadius.indexOf(id) > 0);
    }

    public static boolean isTerrainAdaptationIgnored(String id) {
        return ProjectEvergreenConfig.structuresByFix.entries().stream()
            .filter(e -> id.equals(e.getValue()))
            .map(Map.Entry::getKey)
            .anyMatch(e -> e.equals(DefaultBlacklist.IGNORE_TERRAIN_ADAPTATION));
    }

    public static boolean isFlatnessCheckIgnored(String id) {
        return ProjectEvergreenConfig.structuresByFix.entries().stream()
            .filter(e -> id.equals(e.getValue()))
            .map(Map.Entry::getKey)
            .anyMatch(e -> e.equals(DefaultBlacklist.IGNORE_FLATNESS_CHECK));
    }

    public static boolean hasPopulationBias_Structure(String id) {
        return switch (ProjectEvergreenConfig.populationBias) {
            case 0 -> isCivilizationStructure(id);
            case 1 -> !isUndergroundStructure(id);
            case 2 -> isWildernessStructure(id);
            default -> throw new IllegalArgumentException("No Config Value Provided.");
        };
    }

    public static boolean hasPopulationBias_StructureSet(String id) {
        return switch (ProjectEvergreenConfig.populationBias) {
            case 0 -> ProjectEvergreenConfig.structuresByRarity.keys().stream().filter(e -> (e.contains("massive") & e.contains("civilization"))).anyMatch(e -> e.equals(id));
            case 1 -> false;
            case 2 -> ProjectEvergreenConfig.structuresByRarity.keys().stream().filter(e -> (e.contains("massive") & e.contains("wilderness"))).anyMatch(e -> e.equals(id));
            default -> throw new IllegalArgumentException("No Config Value Provided.");
        };
    }

    public static boolean flatStructureType(String type) {
        List<String> compatibleStructureTypes = ImmutableList.of(
            "minecraft:jigsaw",
            "integrated_api:generic_structure",
            "betterarcheology:betterarcheology_structures",
            "repurposed_structures:generic_jigsaw_structure",
            "moogs_structures:moogs_structures_generic_jigsaw_structure",
            "mvs:mvs_generic_jigsaw_structure",
            "structure_gel:extended_jigsaw",
            "cataclysm:cataclysm_jigsaw",
            "mostructures:generic",
            "hexerei:witch_hut"
        );
        //ProjectEvergreen.LOGGER.info("Checking structure type... " + type + ", " + compatibleStructureTypes.contains(type));
        return compatibleStructureTypes.contains(type);   
    }

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

    public static List<String> filterUndergroundStructures() {
        return ProjectEvergreenConfig.structuresByBiome.entries().stream()
            .filter(e -> (e.getKey().contains("underground")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterFlatStructures() {
        return Constants.defaultFlatStructures.stream()
            .filter(e -> !isIgnored(e))
            .filter(e-> !isWaterStructure(e) &&
                                !isDecoStructure(e) &&
                                !isUndergroundStructure(e) &&
                                !isFlatnessCheckIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterDecoStructures() {
        return ProjectEvergreenConfig.structuresByRarity.entries().stream()
            .filter(e -> (e.getKey().contains("_deco")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterMassiveStructures() {
        return ProjectEvergreenConfig.structuresByRarity.entries().stream()
            .filter(e -> (e.getKey().contains("massive")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterWaterStructures() {
        return ProjectEvergreenConfig.structuresByBiome.entries().stream()
            .filter(e -> (e.getKey().contains("ocean")) || (e.getKey().contains("river")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterCivilizationStructures() {
        return ProjectEvergreenConfig.structuresByBiome.entries().stream()
            .filter(e -> (e.getKey().contains("civilization")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterWildernessStructures() {
        return ProjectEvergreenConfig.structuresByBiome.entries().stream()
            .filter(e -> (e.getKey().contains("wilderness")) || ((e.getKey().contains("special")) && !(e.getKey().contains("civilization"))))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }

    public static List<String> filterBiasIgnoredStructures() {
        return ProjectEvergreenConfig.structuresByBiome.entries().stream()
            .filter(e-> (e.getKey().contains("mountainous") || 
                        e.getKey().contains("craggy") ||
                        e.getKey().contains("river") ||
                        e.getKey().contains("rare") ||
                        e.getKey().contains("oriental")))
            .map(Map.Entry::getValue)
            .filter(e -> !isIgnored(e))
            .collect(Collectors.toList());
    }
}
