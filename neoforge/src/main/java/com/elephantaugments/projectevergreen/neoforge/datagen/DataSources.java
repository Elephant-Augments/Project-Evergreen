package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.*;
import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import com.elephantaugments.projectevergreen.neoforge.config.ProjectEvergreenConfig;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.enderturret.patchedmod.Patched;
import net.enderturret.patchedmod.SingleDataSource;
import net.minecraft.resources.ResourceLocation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataSources {

    public static void registerDataSources() {
        //Registers a new data source to pull config values into patch files
        SingleDataSource source = (from, value) -> {
            return switch (value.getAsString()) {
                case "get_biome" -> DataSources.getBiomeTag(from.getAsString());
                case Constants.IGNORE_STRUCTURE_TYPE -> DataSources.getIgnoreStructureType(from.getAsString());
                case DefaultFlags.FLATNESS_CHECK_NARROW -> DataSources.getFlatnessCheckNarrow(from.getAsString());
                case DefaultFlags.FLATNESS_CHECK_WIDE -> DataSources.getFlatnessCheckWide(from.getAsString());
                case Constants.ALLOWED_TERRAIN_HEIGHT_NARROW -> DataSources.getAllowedTerrainHeightNarrow();
                case Constants.ALLOWED_TERRAIN_HEIGHT_WIDE -> DataSources.getAllowedTerrainHeightWide();
                case Constants.ALLOWED_TERRAIN_HEIGHT_SPRAWLING -> DataSources.getAllowedTerrainHeightSprawling();

                case Constants.POPULATION_BIAS -> DataSources.getBiomeRadius();
                case Constants.POPULATION_BIAS_OFFSET -> DataSources.getPopulationBiasOffset();
                case Constants.CIVILIZATION_MASSIVE_RARITY -> DataSources.getSpreadWithOffset(from.getAsInt(), ProjectEvergreenConfig.civilizationMassiveRarity, DefaultStructureRarity.CIVILIZATION_MASSIVE);
                case Constants.CIVILIZATION_MEDIUM_RARITY -> DataSources.getSpreadWithOffset(from.getAsInt(), ProjectEvergreenConfig.civilizationMediumRarity, DefaultStructureRarity.CIVILIZATION_MEDIUM);
                case Constants.CIVILIZATION_DECORATIVE_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.civilizationDecorativeRarity);
                case Constants.WILDERNESS_MASSIVE_RARITY -> DataSources.getSpreadWithOffset(from.getAsInt(), ProjectEvergreenConfig.wildernessMassiveRarity, DefaultStructureRarity.WILDERNESS_MASSIVE);
                case Constants.WILDERNESS_MEDIUM_RARITY -> DataSources.getSpreadWithOffset(from.getAsInt(), ProjectEvergreenConfig.wildernessMediumRarity, DefaultStructureRarity.WILDERNESS_MEDIUM);
                case Constants.WILDERNESS_DECORATIVE_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.wildernessDecorativeRarity);
                case Constants.UNDERGROUND_MASSIVE_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.undergroundMassiveRarity);
                case Constants.OCEAN_MASSIVE_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.oceanMassiveRarity);
                case Constants.OCEAN_MEDIUM_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.oceanMediumRarity);
                case Constants.SKY_MASSIVE_RARITY -> DataSources.getSpread(from.getAsInt(), ProjectEvergreenConfig.skyMassiveRarity);

                case Constants.BIOME_TAG -> DataSources.buildTagSet(from.getAsJsonArray().asList());
                case Constants.CIVILIZATION_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.civilizationMassive);
                case Constants.CIVILIZATION_MEDIUM -> DataSources.buildStructureSet(ProjectEvergreenConfig.civilizationMedium);
                case Constants.CIVILIZATION_DECO -> DataSources.buildStructureSet(ProjectEvergreenConfig.civilizationDeco);
                case Constants.WILDERNESS_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.wildernessMassive);
                case Constants.WILDERNESS_MEDIUM -> DataSources.buildStructureSet(ProjectEvergreenConfig.wildernessMedium);
                case Constants.WILDERNESS_DECO -> DataSources.buildStructureSet(ProjectEvergreenConfig.wildernessDeco);
                case Constants.OCEAN_FLOATING_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.oceanFloatingMassive);
                case Constants.OCEAN_UNDERWATER_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.oceanUnderwaterMassive);
                case Constants.OCEAN_ALL_MEDIUM -> DataSources.buildStructureSet(ProjectEvergreenConfig.oceanAllMedium);
                case Constants.UNDERGROUND_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.undergroundMassive);
                case Constants.SKY_MASSIVE -> DataSources.buildStructureSet(ProjectEvergreenConfig.skyMassive);

                default -> throw new IllegalArgumentException("No Config Value Provided.");
            };
        };
        Patched.registerDataSource(ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "config_value"), source);
    }

    private static JsonElement getSpread(Integer spread, Double rarity) {
        return ProjectEvergreen.GSON.toJsonTree(Math.ceil(spread * rarity));
    }

    private static JsonElement getSpreadWithOffset(Integer spread, Double rarity, String id) {
        return  ((ProjectEvergreenNeoforge.PLATFORM.isModLoaded("integrated_api") || ProjectEvergreenNeoforge.PLATFORM.isModLoaded("repurposed_structures")) &&
                (!ProjectEvergreenConfig.performanceFriendlyMode || TestConditions.hasPopulationBias_StructureSet(id))) ? 
            ProjectEvergreen.GSON.toJsonTree(Math.ceil(spread * Constants.FLATNESS_SPREAD_OFFSET * rarity)) :
            ProjectEvergreen.GSON.toJsonTree(Math.ceil(spread * rarity));
    }

    public static JsonElement getBiomeRadius() {
        int radius = ProjectEvergreenConfig.populationBias == 1 ? 1 : 3;
        return ProjectEvergreen.GSON.toJsonTree(radius);
    }

    public static JsonElement getPopulationBiasOffset() {
        return ProjectEvergreen.GSON.toJsonTree(ProjectEvergreenConfig.populationBiasOffset);
    }

    public static JsonElement getAllowedTerrainHeightNarrow() {
        return ProjectEvergreen.GSON.toJsonTree(ProjectEvergreenConfig.allowedTerrainHeightNarrow);
    }

    public static JsonElement getAllowedTerrainHeightWide() {
        return ProjectEvergreen.GSON.toJsonTree(ProjectEvergreenConfig.allowedTerrainHeightWide);
    }

    public static JsonElement getAllowedTerrainHeightSprawling() {
        return ProjectEvergreen.GSON.toJsonTree(ProjectEvergreenConfig.allowedTerrainHeightSprawling);
    }

    private static JsonElement getIgnoreStructureType(String structureID) {
        return ProjectEvergreen.GSON.toJsonTree(TestConditions.isStructureTypeIgnored(structureID));
    }

    private static JsonElement getFlatnessCheckNarrow(String structureID) {
        //ProjectEvergreen.LOGGER.info("Checking all flat structures... " + TestConditions.filterFlatStructures().size());
        boolean isFlatNarrow = (TestConditions.isFlatStructure(structureID) && !(TestConditions.isMassiveStructure(structureID))) ||
            DefaultFlags.flatnessCheckMedium.contains(structureID);
        return ProjectEvergreen.GSON.toJsonTree(isFlatNarrow);
    }

    private static JsonElement getFlatnessCheckWide(String structureID) {
        boolean isFlatWide = (TestConditions.isFlatStructure(structureID) && TestConditions.isMassiveStructure(structureID)) ||
            DefaultFlags.flatnessCheckLarge.contains(structureID);
        return ProjectEvergreen.GSON.toJsonTree(isFlatWide);
    }

    private static JsonElement getBiomeTag(String structureID) {
        Optional<String> biomeTag = ProjectEvergreenConfig.structuresByBiome.entries().stream()
                        .filter(e -> structureID.equals(e.getValue()))
                        .map(Map.Entry::getKey)
                        .sorted(Comparator.reverseOrder())
                        .findFirst();
        return biomeTag.isEmpty() ? 
            ProjectEvergreen.GSON.toJsonTree(DefaultRegions.NO_BIOMES) :
            ProjectEvergreen.GSON.toJsonTree(biomeTag.get());
    }

    private static JsonElement buildTagSet(List<JsonElement> set) {
        List<String> jsonSet = set.stream()
            .map(JsonElement::getAsString)
            .filter(TestConditions::isBiomeLoaded)
            .collect(Collectors.toList());
        return ProjectEvergreen.GSON.toJsonTree(jsonSet);
    }

    private static JsonElement buildStructureSet(List<? extends String> set) {
        List<JsonObject> jsonSet = set.stream()
            .filter(e -> TestConditions.isStructureLoaded(e) && !DefaultBlacklist.structureIDs.contains(e))
            .map(DataSources::buildStructureEntry)
            .collect(Collectors.toList());
        return ProjectEvergreen.GSON.toJsonTree(jsonSet);
    }

    private static JsonObject buildStructureEntry(String id) {
        JsonObject json = new JsonObject();
        json.addProperty("structure", id);
        if (TestConditions.isFlatStructure(id) && TestConditions.isSprawlingStructure(id)) {
            json.addProperty("weight", 3);
        } else if (TestConditions.isFlatStructure(id) && !TestConditions.isSprawlingStructure(id)) {
            json.addProperty("weight", 2);
        } else {
            json.addProperty("weight", 1);
        }
        return json;
    }

    public static int randWeight(int min, int max) 
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
