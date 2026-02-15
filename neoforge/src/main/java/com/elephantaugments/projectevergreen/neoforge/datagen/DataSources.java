package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.elephantaugments.projectevergreen.neoforge.config.PEConfig;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.enderturret.patchedmod.Patched;
import net.enderturret.patchedmod.SingleDataSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class DataSources {

    public static final ResourceLocation PE_OBJECT = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, Constants.PE_OBJECT_KEY);
    public static final ResourceLocation PE_VALUE = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, Constants.PE_VALUE_KEY);
    public static final ResourceLocation CONFIG_VALUE = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, Constants.CONFIG_VALUE_KEY);

    public static void registerDataSources() {
        registerPEDataObject();
        registerConfigData();
    }

    private static void registerPEDataObject() {
        SingleDataSource source = (from, value) ->
            switch (value.getAsString()) {
                case Constants.PATCHABLE_BIOME_KEY -> getPatchableDataObject(value.getAsString(), from.getAsJsonObject());
                case Constants.PATCHABLE_STRUCTURE_SET_KEY -> getPatchableDataObject(value.getAsString(), from.getAsJsonObject());
                case Constants.PATCHABLE_STRUCTURE_KEY -> getPatchableDataObject(value.getAsString(), from.getAsJsonObject());
                //TODO case Constants.PATCHABLE_FEATURE_KEY -> getPatchableDataObject(from.getAsJsonObject());
                //TODO case Constants.PATCHABLE_ENTITY_KEY -> getPatchableDataObject(from.getAsJsonObject());
                case Constants.DYNAMIC_STRUCTURE_SET_KEY -> buildStructureSet(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(from.getAsString()));

                default -> throw new IllegalArgumentException("No Data Object Provided.");
            };
        Patched.registerDataSource(PE_OBJECT, source);
    }

    private static void registerConfigData() {
        SingleDataSource source = (from, value) ->
            switch (value.getAsString()) {
                case PEConfig.POPULATION_BIAS_KEY  -> DataSources.getBiomeRadius();
                case PEConfig.POPULATION_BIAS_OFFSET_KEY  -> DataSources.getJsonInt(PEConfig.populationBiasOffset);
                case PEConfig.SPACING_RARITY_KEY  -> DataSources.getSpread(value.getAsString(), from.getAsString());
                case PEConfig.SEPARATION_RARITY_KEY  -> DataSources.getSpread(value.getAsString(), from.getAsString());
                case PEConfig.ALLOWED_TERRAIN_HEIGHT_KEY  -> DataSources.getAllowedTerrainHeight(from.getAsString());

                case PEConfig.COLD_WATER_COLOR_KEY -> DataSources.getJsonInt(PEConfig.coldWaterColor);
                case PEConfig.TEMPERATE_WATER_COLOR_KEY -> DataSources.getJsonInt(PEConfig.temperateWaterColor);
                case PEConfig.WARM_WATER_COLOR_KEY -> DataSources.getJsonInt(PEConfig.warmWaterColor);

                case PEConfig.LOST_CITIES_FIXED_BIOME_KEY -> DataSources.getJsonBool(PEConfig.useLostCitiesFixedBiome);
                case PEConfig.LOST_CITIES_BIOME_KEY -> DataSources.getJsonString(PEConfig.lostCitiesBiome);
                case PEConfig.LOST_CITIES_LIQUID_KEY -> DataSources.getJsonString(PEConfig.lostCitiesLiquid);

                case PEConfig.CONTINENTS_SCALE_KEY -> DataSources.getJsonDoubleWithOffset(from.getAsDouble(), PEConfig.continentScale);
                case PEConfig.NON_CONTINENT_ISLAND_AMOUNT_KEY -> DataSources.getJsonDoubleWithOffset(from.getAsDouble(), PEConfig.nonContinentIslandAmount);
                case PEConfig.NON_CONTINENT_ISLAND_SCALE_KEY -> DataSources.getJsonDoubleWithOffset(from.getAsDouble(), PEConfig.nonContinentIslandScale);
                case PEConfig.SPAWN_ISLAND_SCALE_KEY -> DataSources.getJsonDoubleWithOffset(from.getAsDouble(), PEConfig.spawnIslandScale);

                default -> throw new IllegalArgumentException("No Config Value Provided.");
            };
        Patched.registerDataSource(CONFIG_VALUE, source);
    }

    private static JsonElement getPatchableDataObject(String data_key, JsonObject json) {
        String id = json.get(Constants.JsonProp.ID.jsonKey()).getAsString();
        Optional<IPatchable> wdata;
        switch (data_key) {
            case Constants.PATCHABLE_BIOME_KEY -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_BIOMES.get(id));
            case Constants.PATCHABLE_STRUCTURE_SET_KEY -> wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(id));
            case Constants.PATCHABLE_STRUCTURE_KEY -> {
                wdata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURES.get(id));
                wdata.ifPresent(p -> {
                    PatchableStructure structure = WorldgenDataManager.PATCHABLE_STRUCTURES.get(id);
                    JsonElement heightmap = json.get(Constants.JsonProp.HEIGHTMAP.jsonKey());
                    String type = json.get(Constants.JsonProp.TYPE.jsonKey()).getAsString().toLowerCase();
                    String step = json.get(Constants.JsonProp.STEP.jsonKey()).getAsString().toLowerCase();
                    structure.initJsonData(type, step, heightmap);
                });
            }
            default -> wdata = Optional.empty();
        }
        return wdata.isPresent()?
                wdata.get().toJson() :
                new JsonObject();
    }

    private static JsonElement buildStructureSet(PatchableStructureSet sset) {
        return sset.buildStructureSet();
    }

    public static JsonElement getJsonBool(boolean config_bool) {
        return ProjectEvergreen.GSON.toJsonTree(config_bool);
    }

    public static JsonElement getJsonInt(int config_int) {
        return ProjectEvergreen.GSON.toJsonTree(config_int);
    }

    public static JsonElement getJsonDouble(Double config_double) {
        return ProjectEvergreen.GSON.toJsonTree(config_double);
    }

    public static JsonElement getJsonDoubleWithOffset(Double original, Double config_offset) {
        return ProjectEvergreen.GSON.toJsonTree(original/config_offset);
    }

    public static JsonElement getJsonString(String config_value) {
        return ProjectEvergreen.GSON.toJsonTree(config_value);
    }

    public static JsonElement getBiomeRadius() {
        int radius = PEConfig.populationBias == 1 ? 1 : PEConfig.populationBiasOffset;
        return ProjectEvergreen.GSON.toJsonTree(radius);
    }

    private static JsonElement getAllowedTerrainHeight(String size) {
        return switch (size) {
            case "SMALL" -> ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightSmall);
            case "MEDIUM" -> ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightMedium);
            case "LARGE" -> ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightLarge);
            case "SPRAWLING" -> ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightSprawling);
            default -> ProjectEvergreen.GSON.toJsonTree(PEStructure.Size.MEDIUM.terrainHeight());
        };
    }

    private static JsonElement getSpread(String key, String id) {
        Optional<PatchableStructureSet> sset = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(id));
        Double rarity_offset = sset.get().getData().map(DataSources::getRarityOffset).orElse(1.0);

        int spread = sset.map(peStructureSet -> switch (key) {
            case PEConfig.SPACING_RARITY_KEY -> peStructureSet.getSpacing();
            case PEConfig.SEPARATION_RARITY_KEY -> peStructureSet.getSeparation();
            default -> throw new IllegalArgumentException("No Config Value Provided");
        }).orElse(Constants.DEFAULT_COMMON_SPACING);

        spread = !PEConfig.performanceFriendlyMode && sset.get().hasFlatStructures() ?
                Math.toIntExact(Math.round(spread * Constants.FLATNESS_SPREAD_OFFSET * rarity_offset)) :
                Math.toIntExact(Math.round(spread * rarity_offset));
        return ProjectEvergreen.GSON.toJsonTree(spread);
    }

    private static Double getRarityOffset(PEStructureSet sset) {
        return switch (sset) {
            case PEStructureSet.CIVILIZATION_EXTRA_RARE -> PEConfig.civilizationExtraRareOffset;
            case PEStructureSet.CIVILIZATION_RARE -> PEConfig.civilizationRareOffset;
            case PEStructureSet.CIVILIZATION_COMMON -> PEConfig.civilizationCommonOffset;
            case PEStructureSet.CIVILIZATION_DECO -> PEConfig.civilizationDecoOffset;
            case PEStructureSet.WILDERNESS_EXTRA_RARE -> PEConfig.wildernessExtraRareOffset;
            case PEStructureSet.WILDERNESS_RARE -> PEConfig.wildernessRareOffset;
            case PEStructureSet.WILDERNESS_COMMON -> PEConfig.wildernessCommonOffset;
            case PEStructureSet.WILDERNESS_DECO -> PEConfig.wildernessDecoOffset;
            case PEStructureSet.OCEAN_FLOATING_RARE -> PEConfig.oceanRareOffset;
            case PEStructureSet.OCEAN_UNDERWATER_RARE -> PEConfig.oceanRareOffset;
            case PEStructureSet.OCEAN_ALL_COMMON -> PEConfig.oceanCommonOffset;
            case PEStructureSet.UNDERGROUND_RARE -> PEConfig.undergroundRareOffset;
            case PEStructureSet.SKY_RARE -> PEConfig.skyRareOffset;
        };
    }

    public static JsonElement getStartHeight(int height) {
        JsonObject json = new JsonObject();
        json.addProperty("absolute", height);
        return ProjectEvergreen.GSON.toJsonTree(json);
    }


    /*public static JsonElement getPopulationBiasOffset() {
        return ProjectEvergreen.GSON.toJsonTree(PEConfig.populationBiasOffset);
    }

    public static JsonElement getAllowedTerrainHeightNarrow() {
        return ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightSmall);
    }

    public static JsonElement getAllowedTerrainHeightWide() {
        return ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightLarge);
    }

    public static JsonElement getAllowedTerrainHeightSprawling() {
        return ProjectEvergreen.GSON.toJsonTree(PEConfig.allowedTerrainHeightSprawling);
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
        Optional<String> biomeTag = PEConfig.structuresByBiome.entries().stream()
                        .filter(e -> structureID.equals(e.getValue()))
                        .map(Map.Entry::getKey)
                        .sorted(Comparator.reverseOrder())
                        .findFirst();
        return biomeTag.isEmpty() ? 
            ProjectEvergreen.GSON.toJsonTree(PERegion.NO_BIOMES.tagKey()) :
            ProjectEvergreen.GSON.toJsonTree(biomeTag.get());
    }

    private static JsonElement buildTagSet(List<JsonElement> set) {
        List<String> jsonSet = set.stream()
            .map(JsonElement::getAsString)
            .filter(TestConditions::isBiomeLoaded)
            .collect(Collectors.toList());
        return ProjectEvergreen.GSON.toJsonTree(jsonSet);
    }*/

    public static int randWeight(int min, int max) 
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
