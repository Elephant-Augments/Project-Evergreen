package com.elephantaugments.projectevergreen.neoforge.config;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.*;
import com.google.common.collect.ArrayListMultimap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = ProjectEvergreen.MODID)
public class ProjectEvergreenConfig {

    public static ModConfigSpec COMMON_CONFIG;
    
    public static List<String> EMPTY_LIST = new ArrayList<>();
    //public static final SortedSet<String> ALL_STRUCTURES = Constants.parseStructures();
    //public static final SortedSet<String> ALL_STRUCTURE_SETS = Constants.parseStructureSets();
    
    //private static final String CATEGORY_FEATURES = "features";
    private static ModConfigSpec.BooleanValue STRUCTURE_BIOME_REDISTRIBUTION;
    private static ModConfigSpec.BooleanValue STRUCTURE_RARITY_REDISTRIBUTION;
    private static ModConfigSpec.BooleanValue PERFORMANCE_FRIENDLY_MODE;
    
    private static final String CATEGORY_FIXES = "fixes";
    private static ModConfigSpec.ConfigValue<List<? extends String>> IGNORE_MOD;
    private static ModConfigSpec.ConfigValue<List<? extends String>> IGNORE_STRUCTURE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> IGNORE_STRUCTURE_TYPE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> IGNORE_BIOME_REDISTRIBUTION;
    private static ModConfigSpec.ConfigValue<List<? extends String>> IGNORE_FLATNESS_CHECK;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FLATNESS_CHECK_SPRAWLING;
    private static ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_SPRAWLING;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FLATNESS_CHECK_WIDE;
    private static ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_WIDE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> FLATNESS_CHECK_NARROW;
    private static ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_NARROW;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ADD_TERRAIN_ADAPTATION;

    private static final String CATEGORY_RARITY = "rarity";
    private static ModConfigSpec.IntValue POPULATION_BIAS;
    private static ModConfigSpec.IntValue POPULATION_BIAS_OFFSET;
    private static ModConfigSpec.DoubleValue CIVILIZATION_MASSIVE_RARITY;
    private static ModConfigSpec.DoubleValue CIVILIZATION_MEDIUM_RARITY;
    private static ModConfigSpec.DoubleValue CIVILIZATION_DECORATIVE_RARITY;
    private static ModConfigSpec.DoubleValue WILDERNESS_MASSIVE_RARITY;
    private static ModConfigSpec.DoubleValue WILDERNESS_MEDIUM_RARITY;
    private static ModConfigSpec.DoubleValue WILDERNESS_DECORATIVE_RARITY;
    private static ModConfigSpec.DoubleValue UNDERGROUND_MASSIVE_RARITY;
    private static ModConfigSpec.DoubleValue OCEAN_MASSIVE_RARITY;
    private static ModConfigSpec.DoubleValue OCEAN_MEDIUM_RARITY;
    private static ModConfigSpec.DoubleValue SKY_MASSIVE_RARITY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_MASSIVE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_MEDIUM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_DECO;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_MASSIVE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_MEDIUM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_DECO;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_FLOATING_MASSIVE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_UNDERWATER_MASSIVE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_ALL_MEDIUM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> UNDERGROUND_MASSIVE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SKY_MASSIVE;

    private static final String CATEGORY_BIOMES = "biomes";
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALL_CIVILIZATION;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALL_WILDERNESS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALL_OCEAN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> ALL_RIVERS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_CONIFEROUS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_DECIDUOUS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_ARID;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_DESERT;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_DESERT_RED;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_TROPICAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_SNOW;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_FIELDS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_TEMPERATE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_SPECIAL_AUTUMNAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_SPECIAL_COASTAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_SPECIAL_FLOWERY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> CIVILIZATION_SPECIAL_ORIENTAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_CONIFEROUS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_DECIDUOUS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_ARID;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_DESERT;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_DESERT_RED;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_TROPICAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_SNOW;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_FIELDS;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_TEMPERATE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_SPECIAL_AUTUMNAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_SPECIAL_COASTAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> WILDERNESS_SPECIAL_ORIENTAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_BARREN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_CRAGGY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_ICY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_MAGICAL;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_MEDITERRANEAN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_MOUNTAINOUS_COLD;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_MOUNTAINOUS_HOT;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_RUINED;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_SHROOMY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_SPOOKY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_SWAMPY;
    private static ModConfigSpec.ConfigValue<List<? extends String>> SPECIAL_SWAMPY_WARM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_RARE_DEEP;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_RARE_SHALLOW;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_WARM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_WARM_DEEP;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_DEEP;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_SHALLOW;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_DEEP_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> OCEAN_NOT_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_OR_COAST_NOT_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_OR_COAST_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_NOT_FROZEN;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_TEMPERATE;
    private static ModConfigSpec.ConfigValue<List<? extends String>> RIVER_WARM;
    private static ModConfigSpec.ConfigValue<List<? extends String>> UNDERGROUND_LAND;
    
    public static boolean performanceFriendlyMode;
    public static List<? extends String> addTerrainAdaptation;
    public static List<? extends String> flatnessCheckSprawling;
    public static int allowedTerrainHeightSprawling;
    public static List<? extends String> flatnessCheckWide;
    public static int allowedTerrainHeightWide;
    public static List<? extends String> flatnessCheckNarrow;
    public static int allowedTerrainHeightNarrow;
    public static List<? extends String> ignoreMod;
    public static List<? extends String> ignoreStructure;
    public static List<? extends String> ignoreStructureType;
    public static List<? extends String> ignoreBiomeRedistribution;
    public static List<? extends String> ignoreFlatnessCheck;
    public static ArrayListMultimap<String, String> structuresByFix;

    public static int populationBias;
    public static int populationBiasOffset;
    public static boolean structureRarityRedistribution;
    public static double civilizationMassiveRarity;
    public static double civilizationMediumRarity;
    public static double civilizationDecorativeRarity;
    public static double wildernessMassiveRarity;
    public static double wildernessMediumRarity;
    public static double wildernessDecorativeRarity;
    public static double undergroundMassiveRarity;
    public static double oceanMassiveRarity;
    public static double oceanMediumRarity;
    public static double skyMassiveRarity;
    public static List<? extends String> civilizationMassive;
    public static List<? extends String> civilizationMedium;
    public static List<? extends String> civilizationDeco;
    public static List<? extends String> wildernessMassive;
    public static List<? extends String> wildernessMedium;
    public static List<? extends String> wildernessDeco;
    public static List<? extends String> oceanFloatingMassive;
    public static List<? extends String> oceanUnderwaterMassive;
    public static List<? extends String> oceanAllMedium;
    public static List<? extends String> undergroundMassive;
    public static List<? extends String> skyMassive;
    public static ArrayListMultimap<String, String> structuresByRarity;

    public static boolean structureBiomeRedistribution;
    public static List<? extends String> allCivilization;
    public static List<? extends String> allWilderness;
    public static List<? extends String> allOcean;
    public static List<? extends String> allRivers;
    public static List<? extends String> civilizationConiferous;
    public static List<? extends String> civilizationDeciduous;
    public static List<? extends String> civilizationArid;
    public static List<? extends String> civilizationDesert;
    public static List<? extends String> civilizationDesertRed;
    public static List<? extends String> civilizationTropical;
    public static List<? extends String> civilizationSnow;
    public static List<? extends String> civilizationFields; 
    public static List<? extends String> civilizationTemperate;
    public static List<? extends String> civilizationSpecialAutumnal; 
    public static List<? extends String> civilizationSpecialCoastal;
    public static List<? extends String> civilizationSpecialOriental; 
    public static List<? extends String> wildernessConiferous;
    public static List<? extends String> wildernessDeciduous; 
    public static List<? extends String> wildernessArid; 
    public static List<? extends String> wildernessDesert;
    public static List<? extends String> wildernessDesertRed; 
    public static List<? extends String> wildernessTropical; 
    public static List<? extends String> wildernessSnow;
    public static List<? extends String> wildernessFields; 
    public static List<? extends String> wildernessTemperate;
    public static List<? extends String> wildernessSpecialAutumnal; 
    public static List<? extends String> wildernessSpecialCoastal; 
    public static List<? extends String> wildernessSpecialOriental;
    public static List<? extends String> specialBarren; 
    public static List<? extends String> specialCraggy; 
    public static List<? extends String> civilizationSpecialFlowery; 
    public static List<? extends String> specialIcy; 
    public static List<? extends String> specialMagical; 
    public static List<? extends String> specialMediterranean; 
    public static List<? extends String> specialMountainousCold; 
    public static List<? extends String> specialMountainousHot; 
    public static List<? extends String> specialRuined; 
    public static List<? extends String> specialShroomy; 
    public static List<? extends String> specialSpooky; 
    public static List<? extends String> specialSwampy; 
    public static List<? extends String> specialSwampyWarm; 
    public static List<? extends String> oceanRareDeep; 
    public static List<? extends String> oceanRareShallow; 
    public static List<? extends String> oceanWarm; 
    public static List<? extends String> oceanWarmDeep; 
    public static List<? extends String> oceanDeep; 
    public static List<? extends String> oceanShallow; 
    public static List<? extends String> oceanFrozen; 
    public static List<? extends String> oceanDeepFrozen; 
    public static List<? extends String> oceanNotFrozen; 
    public static List<? extends String> riverOrCoastNotFrozen; 
    public static List<? extends String> riverOrCoastFrozen; 
    public static List<? extends String> riverFrozen;
    public static List<? extends String> riverNotFrozen;
    public static List<? extends String> riverTemperate;
    public static List<? extends String> riverWarm;
    public static List<? extends String> undergroundLand;
    public static ArrayListMultimap<String, String> structuresByBiome;
    

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        try {
            ProjectEvergreen.LOGGER.info("Loading Project Evergreen config...");

            performanceFriendlyMode = PERFORMANCE_FRIENDLY_MODE.get();
            ignoreMod = getConfigList(DefaultBlacklist.modIDs, IGNORE_MOD.get());
            ignoreStructure = getConfigList(DefaultBlacklist.structureIDs, IGNORE_STRUCTURE.get());
            ignoreStructureType = getConfigList(DefaultBlacklist.ignoreStructureType, IGNORE_STRUCTURE_TYPE.get());
            ignoreBiomeRedistribution = getConfigList(DefaultBlacklist.ignoreBiomeRedistribution, IGNORE_BIOME_REDISTRIBUTION.get());
            ignoreFlatnessCheck = getConfigList(DefaultBlacklist.ignoreFlatnessCheck, IGNORE_FLATNESS_CHECK.get());
            flatnessCheckSprawling = getConfigList(DefaultFlags.flatnessCheckSprawling, FLATNESS_CHECK_SPRAWLING.get());
            allowedTerrainHeightSprawling = ALLOWED_TERRAIN_HEIGHT_SPRAWLING.get();
            flatnessCheckWide = getConfigList(DefaultFlags.flatnessCheckLarge, FLATNESS_CHECK_WIDE.get());
            allowedTerrainHeightWide = ALLOWED_TERRAIN_HEIGHT_WIDE.get();
            flatnessCheckNarrow = getConfigList(DefaultFlags.flatnessCheckMedium, FLATNESS_CHECK_NARROW.get());
            allowedTerrainHeightNarrow = ALLOWED_TERRAIN_HEIGHT_NARROW.get();
            addTerrainAdaptation = getConfigList(DefaultFlags.adjustedTerrainAdaptation, ADD_TERRAIN_ADAPTATION.get());
            structuresByFix = parseStructureFixes();

            structureRarityRedistribution = STRUCTURE_RARITY_REDISTRIBUTION.get();
            populationBias = POPULATION_BIAS.get();
            populationBiasOffset = POPULATION_BIAS_OFFSET.get();
            civilizationMassiveRarity = CIVILIZATION_MASSIVE_RARITY.get();
            civilizationMediumRarity = CIVILIZATION_MEDIUM_RARITY.get();
            civilizationDecorativeRarity = CIVILIZATION_DECORATIVE_RARITY.get();
            wildernessMassiveRarity = WILDERNESS_MASSIVE_RARITY.get();
            wildernessMediumRarity = WILDERNESS_MEDIUM_RARITY.get();
            wildernessDecorativeRarity = WILDERNESS_DECORATIVE_RARITY.get();
            undergroundMassiveRarity = UNDERGROUND_MASSIVE_RARITY.get();
            oceanMassiveRarity = OCEAN_MASSIVE_RARITY.get();
            oceanMediumRarity = OCEAN_MEDIUM_RARITY.get();
            skyMassiveRarity = SKY_MASSIVE_RARITY.get();
            civilizationMassive = getConfigList(DefaultStructureRarity.civilizationMassive, CIVILIZATION_MASSIVE.get());
            civilizationMedium = getConfigList(DefaultStructureRarity.civilizationMedium, CIVILIZATION_MEDIUM.get());
            civilizationDeco = getConfigList(DefaultStructureRarity.civilizationDeco, CIVILIZATION_DECO.get());
            wildernessMassive = getConfigList(DefaultStructureRarity.wildernessMassive, WILDERNESS_MASSIVE.get());
            wildernessMedium = getConfigList(DefaultStructureRarity.wildernessMedium, WILDERNESS_MEDIUM.get());
            wildernessDeco = getConfigList(DefaultStructureRarity.wildernessDeco, WILDERNESS_DECO.get());
            oceanFloatingMassive = getConfigList(DefaultStructureRarity.oceanFloatingMassive, OCEAN_FLOATING_MASSIVE.get());
            oceanUnderwaterMassive = getConfigList(DefaultStructureRarity.oceanUnderwaterMassive, OCEAN_UNDERWATER_MASSIVE.get());
            oceanAllMedium = getConfigList(DefaultStructureRarity.oceanAllMedium, OCEAN_ALL_MEDIUM.get());
            undergroundMassive = getConfigList(DefaultStructureRarity.undergroundSprawling, UNDERGROUND_MASSIVE.get());
            skyMassive = getConfigList(DefaultStructureRarity.skyMassive, SKY_MASSIVE.get());
            structuresByRarity = parseStructureRarities();

            structureBiomeRedistribution = STRUCTURE_BIOME_REDISTRIBUTION.get();
            allCivilization = getConfigList(DefaultStructureRegions.allCivilization, ALL_CIVILIZATION.get());
            allWilderness = getConfigList(DefaultStructureRegions.allWilderness, ALL_WILDERNESS.get());
            allOcean = getConfigList(DefaultStructureRegions.allOcean, ALL_OCEAN.get());
            allRivers = getConfigList(DefaultStructureRegions.allRivers, ALL_RIVERS.get());
            civilizationConiferous = getConfigList(DefaultStructureRegions.civilizationConiferous, CIVILIZATION_CONIFEROUS.get());
            civilizationDeciduous = getConfigList(DefaultStructureRegions.civilizationDeciduous, CIVILIZATION_DECIDUOUS.get());
            civilizationArid = getConfigList(DefaultStructureRegions.civilizationArid, CIVILIZATION_ARID.get());
            civilizationDesert = getConfigList(DefaultStructureRegions.civilizationDesert, CIVILIZATION_DESERT.get());
            civilizationDesertRed = getConfigList(DefaultStructureRegions.civilizationDesertRed, CIVILIZATION_DESERT_RED.get());
            civilizationTropical = getConfigList(DefaultStructureRegions.civilizationTropical, CIVILIZATION_TROPICAL.get());
            civilizationSnow = getConfigList(DefaultStructureRegions.civilizationSnow, CIVILIZATION_SNOW.get());
            civilizationFields = getConfigList(DefaultStructureRegions.civilizationFields, CIVILIZATION_FIELDS.get());
            civilizationTemperate = getConfigList(DefaultStructureRegions.civilizationTemperate, CIVILIZATION_TEMPERATE.get());
            civilizationSpecialAutumnal = getConfigList(DefaultStructureRegions.civilizationSpecialAutumnal, CIVILIZATION_SPECIAL_AUTUMNAL.get());
            civilizationSpecialCoastal = getConfigList(DefaultStructureRegions.civilizationSpecialCoastal, CIVILIZATION_SPECIAL_COASTAL.get());
            civilizationSpecialFlowery = getConfigList(DefaultStructureRegions.civilizationSpecialFlowery, CIVILIZATION_SPECIAL_FLOWERY.get());
            civilizationSpecialOriental = getConfigList(DefaultStructureRegions.civilizationSpecialOriental, CIVILIZATION_SPECIAL_ORIENTAL.get());
            wildernessConiferous = getConfigList(DefaultStructureRegions.wildernessConiferous, WILDERNESS_CONIFEROUS.get());
            wildernessDeciduous = getConfigList(DefaultStructureRegions.wildernessDeciduous, WILDERNESS_DECIDUOUS.get());
            wildernessArid = getConfigList(DefaultStructureRegions.wildernessArid, WILDERNESS_ARID.get());
            wildernessDesert = getConfigList(DefaultStructureRegions.wildernessDesert, WILDERNESS_DESERT.get());
            wildernessDesertRed = getConfigList(DefaultStructureRegions.wildernessDesertRed, WILDERNESS_DESERT_RED.get());
            wildernessTropical = getConfigList(DefaultStructureRegions.wildernessTropical, WILDERNESS_TROPICAL.get());
            wildernessSnow = getConfigList(DefaultStructureRegions.wildernessSnow, WILDERNESS_SNOW.get());
            wildernessFields = getConfigList(DefaultStructureRegions.wildernessFields, WILDERNESS_FIELDS.get());
            wildernessTemperate = getConfigList(DefaultStructureRegions.wildernessTemperate, WILDERNESS_TEMPERATE.get());
            wildernessSpecialAutumnal = getConfigList(DefaultStructureRegions.wildernessSpecialAutumnal, WILDERNESS_SPECIAL_AUTUMNAL.get());
            wildernessSpecialCoastal = getConfigList(DefaultStructureRegions.wildernessSpecialCoastal, WILDERNESS_SPECIAL_COASTAL.get());
            wildernessSpecialOriental = getConfigList(DefaultStructureRegions.wildernessSpecialOriental, WILDERNESS_SPECIAL_ORIENTAL.get());
            specialBarren = getConfigList(DefaultStructureRegions.specialRocky, SPECIAL_BARREN.get());
            specialCraggy = getConfigList(DefaultStructureRegions.specialCraggy, SPECIAL_CRAGGY.get());
            specialIcy = getConfigList(DefaultStructureRegions.specialIcy, SPECIAL_ICY.get());
            specialMagical = getConfigList(DefaultStructureRegions.specialMagical, SPECIAL_MAGICAL.get());
            specialMediterranean = getConfigList(DefaultStructureRegions.wildernessSpecialMediterranean, SPECIAL_MEDITERRANEAN.get());
            specialMountainousCold = getConfigList(DefaultStructureRegions.specialMountainousCold, SPECIAL_MOUNTAINOUS_COLD.get());
            specialMountainousHot = getConfigList(DefaultStructureRegions.specialMountainousHot, SPECIAL_MOUNTAINOUS_HOT.get());
            specialRuined = getConfigList(DefaultStructureRegions.specialRuined, SPECIAL_RUINED.get());
            specialShroomy = getConfigList(DefaultStructureRegions.specialShroomy, SPECIAL_SHROOMY.get());
            specialSpooky = getConfigList(DefaultStructureRegions.specialSpooky, SPECIAL_SPOOKY.get());
            specialSwampy = getConfigList(DefaultStructureRegions.specialSwampy, SPECIAL_SWAMPY.get());
            specialSwampyWarm = getConfigList(DefaultStructureRegions.specialSwampyWarm, SPECIAL_SWAMPY_WARM.get());
            oceanRareDeep = getConfigList(DefaultStructureRegions.oceanRareDeep, OCEAN_RARE_DEEP.get());
            oceanRareShallow = getConfigList(DefaultStructureRegions.oceanRareShallow, OCEAN_RARE_SHALLOW.get());
            oceanWarm = getConfigList(DefaultStructureRegions.oceanWarm, OCEAN_WARM.get());
            oceanWarmDeep = getConfigList(DefaultStructureRegions.oceanDeepWarm, OCEAN_WARM_DEEP.get());
            oceanDeep = getConfigList(DefaultStructureRegions.oceanDeep, OCEAN_DEEP.get());
            oceanShallow = getConfigList(DefaultStructureRegions.oceanShallow, OCEAN_SHALLOW.get());
            oceanFrozen = getConfigList(DefaultStructureRegions.oceanFrozen, OCEAN_FROZEN.get());
            oceanDeepFrozen = getConfigList(DefaultStructureRegions.oceanDeepFrozen, OCEAN_DEEP_FROZEN.get());
            oceanNotFrozen = getConfigList(DefaultStructureRegions.oceanNotFrozen, OCEAN_NOT_FROZEN.get());
            riverOrCoastNotFrozen = getConfigList(DefaultStructureRegions.riverOrCoastNotFrozen, RIVER_OR_COAST_NOT_FROZEN.get());
            riverOrCoastFrozen = getConfigList(DefaultStructureRegions.riverOrCoastFrozen, RIVER_OR_COAST_FROZEN.get());
            riverFrozen = getConfigList(DefaultStructureRegions.riverFrozen, RIVER_FROZEN.get());
            riverNotFrozen = getConfigList(DefaultStructureRegions.riverNotFrozen, RIVER_NOT_FROZEN.get());
            riverTemperate = getConfigList(DefaultStructureRegions.riverTemperate, RIVER_TEMPERATE.get());
            riverWarm = getConfigList(DefaultStructureRegions.riverWarm, RIVER_WARM.get());
            undergroundLand = getConfigList(DefaultStructureRegions.allUndergroundLand, UNDERGROUND_LAND.get());
            structuresByBiome = parseStructureBiomes();

            ProjectEvergreen.LOGGER.info("Finished loading Project Evergreen config.");
        } catch (Exception e) {
            ProjectEvergreen.LOGGER.error("Failed to load Project Evergreen config.");
        }
    }

    public static List<String> getConfigList(List<String> defaults, List<? extends String> configList) {
        List<String> fullList = new ArrayList<String>(configList);
        fullList.addAll(defaults);
        return fullList;
    }

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.comment("Toggle Features");
        STRUCTURE_BIOME_REDISTRIBUTION = COMMON_BUILDER
            .comment("# Enable/Disable structure redistribution between Civilization/Wilderness biomes.")
            .define(Constants.STRUCTURE_BIOME_TOGGLE, true);
        STRUCTURE_RARITY_REDISTRIBUTION = COMMON_BUILDER
            .comment("# Enable/Disable rarity distribution based on structure size.\n(e.g Massive structures are rare, decorative structures more common.)")
            .define(Constants.STRUCTURE_RARITY_TOGGLE, true);
        PERFORMANCE_FRIENDLY_MODE = COMMON_BUILDER
            .comment("# Disables extra structure placement checks that may cause chunk lag.\n(e.g Disables terrain flatness, biome radius, and no-spawn-in-water checks.)")
            .define(Constants.PERFORMANCE_CONFIG_VALUE, false);
        POPULATION_BIAS = COMMON_BUILDER
            .comment("# Determines the natural spread of Wilderness/Civilization structures throughout the Overworld.\n# (0 = more Wilderness, 1 = balanced Wilderness/Civilization, 2 = more Civilization)")
            .defineInRange(Constants.POPULATION_BIAS, 1, 0, 2);
        POPULATION_BIAS_OFFSET = COMMON_BUILDER
            .comment("# The degree to which non-biased structures should be isolated.\n# (2 = slightly rare, 5 = extremely rare)")
            .defineInRange(Constants.POPULATION_BIAS_OFFSET, 3, 2, 5);


        COMMON_BUILDER.comment("Blacklist & Other Fixes").push(CATEGORY_FIXES);
        IGNORE_MOD = COMMON_BUILDER
            .comment("# Ignore all Project Evergreen alterations for all structures from the following mod IDs. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(DefaultBlacklist.IGNORE_MOD, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        IGNORE_STRUCTURE = COMMON_BUILDER
            .comment("# Ignore all Project Evergreen alterations for the following structure IDs. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(DefaultBlacklist.IGNORE_STRUCTURE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        IGNORE_BIOME_REDISTRIBUTION = COMMON_BUILDER
            .comment("# Allows structures to spawn via their original modded biome parameters.")
            .defineListAllowEmpty(DefaultBlacklist.IGNORE_BIOME_REDISTRIBUTION, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        IGNORE_STRUCTURE_TYPE = COMMON_BUILDER
            .comment("# Project Evergreen will not update the structure type of the following structure IDs.",
                "Can fix most issues related to missing blocks/structure parts on spawn.")
            .defineListAllowEmpty(DefaultBlacklist.IGNORE_STRUCTURE_TYPE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        IGNORE_FLATNESS_CHECK = COMMON_BUILDER
            .comment("# Project Evergreen will not apply a flatness check to the following structure IDs.",
                "Can fix most issues related to broken water/underground structure spawns.")
            .defineListAllowEmpty(DefaultBlacklist.IGNORE_FLATNESS_CHECK, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        FLATNESS_CHECK_NARROW = COMMON_BUILDER
            .comment("# A list of small-medium structures to force-spawn on flat terrain. Use a comma-separated list, newlines accepted.",
             "Only applied if Performance Mode is disabled.")
            .defineListAllowEmpty(DefaultFlags.FLATNESS_CHECK_NARROW, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALLOWED_TERRAIN_HEIGHT_NARROW = COMMON_BUILDER
            .comment("# The average elevation (in blocks) at which flat_narrow structures can be allowed to spawn.\n# (6 = extremely flat, 30 = vanilla parity)")
            .defineInRange(Constants.ALLOWED_TERRAIN_HEIGHT_NARROW, 8, 6, 30);
        FLATNESS_CHECK_WIDE = COMMON_BUILDER
            .comment("# A list of large-massive structures to force-spawn on flat terrain. Use a comma-separated list, newlines accepted.",
             "Only applied if Performance Mode is disabled.")
            .defineListAllowEmpty(DefaultFlags.FLATNESS_CHECK_WIDE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALLOWED_TERRAIN_HEIGHT_WIDE = COMMON_BUILDER
            .comment("# The average elevation (in blocks) at which flat_wide structures can be allowed to spawn.\n# (8 = extremely flat, 40 = vanilla parity)")
            .defineInRange(Constants.ALLOWED_TERRAIN_HEIGHT_WIDE, 12, 8, 40);
        FLATNESS_CHECK_SPRAWLING = COMMON_BUILDER
            .comment("# A list of sprawling structures (village-sized) to force-spawn on flat terrain. Use a comma-separated list, newlines accepted.",
             "Only applied if Performance Mode is disabled.")
            .defineListAllowEmpty(DefaultFlags.FLATNESS_CHECK_SPRAWLING, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALLOWED_TERRAIN_HEIGHT_SPRAWLING = COMMON_BUILDER
            .comment("# The average elevation (in blocks) at which flat_sprawling structures can be allowed to spawn.\n# (12 = extremely flat, 50 = vanilla parity)")
            .defineInRange(Constants.ALLOWED_TERRAIN_HEIGHT_SPRAWLING, 18, 12, 50);
        ADD_TERRAIN_ADAPTATION = COMMON_BUILDER
            .comment("# Adds terrain padding beneath structure spawns.")
            .defineListAllowEmpty(DefaultFlags.ADD_TERRAIN_ADAPTATION, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        COMMON_BUILDER.pop();


        //Rarity Redistribution
        COMMON_BUILDER.comment("Rarity Redistribution",
            "Use this feature to add custom compatibility for unsupported mods. Default values cannot be changed.",
            "Use a comma-separated list, newlines accepted.").push(CATEGORY_RARITY);
        CIVILIZATION_MASSIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for villages and other massive Civilization structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.CIVILIZATION_MASSIVE_RARITY, 1.0, 0.2, 2.0);
        CIVILIZATION_MEDIUM_RARITY = COMMON_BUILDER
            .comment("# Rarity value for medium to large Civilization structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.CIVILIZATION_MEDIUM_RARITY, 1.0, 0.2, 2.0);
        CIVILIZATION_DECORATIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for small decorative structures found in Civilization biomes.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.CIVILIZATION_DECORATIVE_RARITY, 1.0, 0.2, 2.0);
        WILDERNESS_MASSIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for boss arenas and other massive Wilderness structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.WILDERNESS_MASSIVE_RARITY, 1.0, 0.2, 2.0);
        WILDERNESS_MEDIUM_RARITY = COMMON_BUILDER
            .comment("# Rarity value for medium to large Wilderness structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.WILDERNESS_MEDIUM_RARITY, 1.0, 0.2, 2.0);
        WILDERNESS_DECORATIVE_RARITY= COMMON_BUILDER
            .comment("# Rarity value for small decorative structures found in some Wilderness biomes.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.WILDERNESS_DECORATIVE_RARITY, 1.0, 0.2, 2.0);
        UNDERGROUND_MASSIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for massive Underground structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.UNDERGROUND_MASSIVE_RARITY, 1.0, 0.2, 2.0);
        OCEAN_MASSIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for massive Ocean structures, both underwater and on the surface.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.OCEAN_MASSIVE_RARITY, 1.0, 0.2, 2.0);
        OCEAN_MEDIUM_RARITY = COMMON_BUILDER
            .comment("# Rarity value for medium to large Ocean structures, both underwater and on the surface.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.OCEAN_MEDIUM_RARITY, 1.0, 0.2, 2.0);
        SKY_MASSIVE_RARITY = COMMON_BUILDER
            .comment("# Rarity value for massive floating Sky structures.\n# (0.2 = most common, 2.0 = most rare)")
            .defineInRange(Constants.SKY_MASSIVE_RARITY, 1.0, 0.2, 2.0);
        CIVILIZATION_MASSIVE = COMMON_BUILDER
            .comment("Villages and other massive-sized Civilization structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_MEDIUM = COMMON_BUILDER
            .comment("Medium to large structures found in Civilization biomes with an average spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_MEDIUM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_DECO = COMMON_BUILDER
            .comment("Small decorative structures found in Civilization biomes with a very common spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_DECO, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_MASSIVE = COMMON_BUILDER
            .comment("Bosses and other massive-sized Wilderness structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_MEDIUM = COMMON_BUILDER
            .comment("Medium to large structures found in Wilderness biomes with an average spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_MEDIUM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_DECO = COMMON_BUILDER
            .comment("Small decorative structures found in some Wilderness biomes with a very common spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_DECO, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_FLOATING_MASSIVE = COMMON_BUILDER
            .comment("Massive floating Ocean structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_FLOATING_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_UNDERWATER_MASSIVE = COMMON_BUILDER
            .comment("Massive underwater Ocean structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_UNDERWATER_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_ALL_MEDIUM = COMMON_BUILDER
            .comment("Underwater/Floating Ocean structures with an average spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_ALL_MEDIUM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        UNDERGROUND_MASSIVE = COMMON_BUILDER
            .comment("Massive Underground structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.UNDERGROUND_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SKY_MASSIVE = COMMON_BUILDER
            .comment("Massive floating Sky structures with a rarer spread. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SKY_MASSIVE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        COMMON_BUILDER.pop();


        //Biome Redistribution
        COMMON_BUILDER.comment("# Biome Redistribution",
            "Use this feature to add custom compatibility for unsupported mods. Default values cannot be changed.",
            "Only applied if " + Constants.STRUCTURE_BIOME_TOGGLE + " is enabled.").push(CATEGORY_BIOMES);
        ALL_CIVILIZATION = COMMON_BUILDER
            .comment("Structure can appear in any Civilization biome. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.ALL_CIVILIZATION, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALL_WILDERNESS = COMMON_BUILDER
            .comment("Structure can appear in any Wilderness biome. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.ALL_WILDERNESS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALL_OCEAN = COMMON_BUILDER
            .comment("Structure can appear in any ocean biome. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.ALL_OCEAN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        ALL_RIVERS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.ALL_RIVERS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_CONIFEROUS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_CONIFEROUS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_DECIDUOUS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_DECIDUOUS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_ARID = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_ARID, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_DESERT = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_DESERT, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_DESERT_RED = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_DESERT_RED, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_TROPICAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_TROPICAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_SNOW = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_SNOW, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_FIELDS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_FIELDS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_TEMPERATE = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_TEMPERATE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_SPECIAL_AUTUMNAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_SPECIAL_AUTUMNAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_SPECIAL_COASTAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_SPECIAL_COASTAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_SPECIAL_ORIENTAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_SPECIAL_ORIENTAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_CONIFEROUS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_CONIFEROUS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_DECIDUOUS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_DECIDUOUS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_ARID = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_ARID, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_DESERT = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_DESERT, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_DESERT_RED = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_DESERT_RED, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_TROPICAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_TROPICAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_SNOW = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_SNOW, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_FIELDS = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_FIELDS, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_TEMPERATE = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_TEMPERATE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_SPECIAL_AUTUMNAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_SPECIAL_AUTUMNAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_SPECIAL_COASTAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_SPECIAL_COASTAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        WILDERNESS_SPECIAL_ORIENTAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.WILDERNESS_SPECIAL_ORIENTAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_BARREN = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_BARREN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_CRAGGY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_CRAGGY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        CIVILIZATION_SPECIAL_FLOWERY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.CIVILIZATION_SPECIAL_FLOWERY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_ICY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_ICY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_MAGICAL = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_MAGICAL, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_MEDITERRANEAN = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_MEDITERRANEAN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_MOUNTAINOUS_COLD = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_MOUNTAINOUS_COLD, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_MOUNTAINOUS_HOT = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_MOUNTAINOUS_HOT, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_RUINED = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_RUINED, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_SHROOMY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_SHROOMY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_SPOOKY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_SPOOKY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_SWAMPY = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_SWAMPY, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        SPECIAL_SWAMPY_WARM = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.SPECIAL_SWAMPY_WARM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_RARE_DEEP = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_RARE_DEEP, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_RARE_SHALLOW = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_RARE_SHALLOW, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_WARM = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_WARM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_WARM_DEEP = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_WARM_DEEP, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_DEEP = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_DEEP, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_SHALLOW = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_SHALLOW, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_DEEP_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_DEEP_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        OCEAN_NOT_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.OCEAN_NOT_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_OR_COAST_NOT_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any non-frozen river or coastline. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_OR_COAST_NOT_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_OR_COAST_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any frozen river or coastline. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_OR_COAST_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any frozen river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_NOT_FROZEN = COMMON_BUILDER
            .comment("Structure can appear in any non-frozen river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_NOT_FROZEN, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_TEMPERATE = COMMON_BUILDER
            .comment("Structure can appear in any temperate (normal/cold) river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_TEMPERATE, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        RIVER_WARM = COMMON_BUILDER
            .comment("Structure can appear in any arid/tropical river. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.RIVER_WARM, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        UNDERGROUND_LAND = COMMON_BUILDER
            .comment("Underground structures that generate exclusively beneath land. Use a comma-separated list, newlines accepted.")
            .defineListAllowEmpty(Constants.UNDERGROUND_LAND, EMPTY_LIST, () -> "", ProjectEvergreenConfig::validateListItem);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static boolean validateListItem(final Object obj) {
        return obj instanceof String;
    }

    public static ArrayListMultimap<String, String> parseStructureBiomes() {
		ArrayListMultimap<String, String> structuresByBiome = ArrayListMultimap.create();
        allCivilization.forEach(s -> structuresByBiome.put(DefaultRegions.ALL_CIVILIZATION, s));
        allWilderness.forEach(s -> structuresByBiome.put(DefaultRegions.ALL_WILDERNESS, s));
        allOcean.forEach(s -> structuresByBiome.put(DefaultRegions.ALL_OCEAN, s));
        allRivers.forEach(s -> structuresByBiome.put(DefaultRegions.ALL_RIVERS, s));
        civilizationConiferous.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_CONIFEROUS, s));
        civilizationDeciduous.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_DECIDUOUS, s));
        civilizationArid.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_ARID, s));
        civilizationDesert.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_DESERT, s));
        civilizationDesertRed.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_DESERT_RED, s));
        civilizationTropical.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_TROPICAL, s));
        civilizationSnow.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_SNOW, s));
        civilizationFields.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_FIELDS, s));
        civilizationTemperate.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_TEMPERATE, s));
        civilizationSpecialAutumnal.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_SPECIAL_AUTUMNAL, s));
        civilizationSpecialCoastal.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_SPECIAL_COASTAL, s));
        civilizationSpecialOriental.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_SPECIAL_ORIENTAL, s));
        wildernessConiferous.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_CONIFEROUS, s));
        wildernessDeciduous.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_DECIDUOUS, s));
        wildernessArid.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_ARID, s));
        wildernessDesert.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_DESERT, s));
        wildernessDesertRed.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_DESERT_RED, s));
        wildernessTropical.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_TROPICAL, s));
        wildernessSnow.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_SNOW, s));
        wildernessFields.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_FIELDS, s));
        wildernessTemperate.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_TEMPERATE, s));
        wildernessSpecialAutumnal.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_SPECIAL_AUTUMNAL, s));
        wildernessSpecialCoastal.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_SPECIAL_COASTAL, s));
        wildernessSpecialOriental.forEach(s -> structuresByBiome.put(DefaultRegions.WILDERNESS_SPECIAL_ORIENTAL, s));
        specialBarren.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_BARREN, s));
        specialCraggy.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_CRAGGY, s));
        civilizationSpecialFlowery.forEach(s -> structuresByBiome.put(DefaultRegions.CIVILIZATION_SPECIAL_FLOWERY, s));
        specialIcy.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_ICY, s));
        specialMagical.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_MAGICAL, s));
        specialMediterranean.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_MEDITERRANEAN, s));
        specialMountainousCold.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_MOUNTAINOUS_COLD, s));
        specialMountainousHot.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_MOUNTAINOUS_HOT, s));
        specialRuined.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_RUINED, s));
        specialShroomy.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_SHROOMY, s));
        specialSpooky.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_SPOOKY, s));
        specialSwampy.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_SWAMPY, s));
        specialSwampyWarm.forEach(s -> structuresByBiome.put(DefaultRegions.SPECIAL_SWAMPY_WARM, s));
        oceanRareDeep.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_RARE_DEEP, s));
        oceanRareShallow.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_RARE_SHALLOW, s));
        oceanWarm.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_WARM, s));
        oceanWarmDeep.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_DEEP_WARM, s));
        oceanDeep.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_DEEP, s));
        oceanShallow.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_SHALLOW, s));
        oceanFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_FROZEN, s));
        oceanDeepFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_DEEP_FROZEN, s));
        oceanNotFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.OCEAN_NOT_FROZEN, s));
        riverOrCoastNotFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_OR_COAST_NOT_FROZEN, s));
        riverOrCoastFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_OR_COAST_FROZEN, s));
        riverFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_FROZEN, s));
        riverNotFrozen.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_NOT_FROZEN, s));
        riverTemperate.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_TEMPERATE, s));
        riverWarm.forEach(s -> structuresByBiome.put(DefaultRegions.RIVER_WARM, s));
        undergroundLand.forEach(s -> structuresByBiome.put(DefaultRegions.ALL_UNDERGROUND_LAND, s));
        return structuresByBiome;
    }

    public static ArrayListMultimap<String, String> parseStructureRarities() {
		ArrayListMultimap<String, String> structuresByRarity = ArrayListMultimap.create();
        civilizationMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.CIVILIZATION_MASSIVE, s));
        civilizationMedium.forEach(s -> structuresByRarity.put(DefaultStructureRarity.CIVILIZATION_MEDIUM, s));
        civilizationDeco.forEach(s -> structuresByRarity.put(DefaultStructureRarity.CIVILIZATION_DECO, s));
        wildernessMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.WILDERNESS_MASSIVE, s));
        wildernessMedium.forEach(s -> structuresByRarity.put(DefaultStructureRarity.WILDERNESS_MEDIUM, s));
        wildernessDeco.forEach(s -> structuresByRarity.put(DefaultStructureRarity.WILDERNESS_DECO, s));
        oceanFloatingMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.OCEAN_FLOATING_MASSIVE, s));
        oceanUnderwaterMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.OCEAN_UNDERWATER_MASSIVE, s));
        oceanAllMedium.forEach(s -> structuresByRarity.put(DefaultStructureRarity.OCEAN_ALL_MEDIUM, s));
        undergroundMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.UNDERGROUND_SPRAWLING, s));
        skyMassive.forEach(s -> structuresByRarity.put(DefaultStructureRarity.SKY_MASSIVE, s));
        return structuresByRarity;
    }

    public static ArrayListMultimap<String, String> parseStructureFixes() {
		ArrayListMultimap<String, String> structuresByFix = ArrayListMultimap.create();
        ignoreMod.forEach(s -> structuresByFix.put(DefaultBlacklist.IGNORE_MOD, s));
        ignoreStructure.forEach(s -> structuresByFix.put(DefaultBlacklist.IGNORE_STRUCTURE, s));
        ignoreStructureType.forEach(s -> structuresByFix.put(DefaultBlacklist.IGNORE_STRUCTURE_TYPE, s));
        ignoreBiomeRedistribution.forEach(s -> structuresByFix.put(DefaultBlacklist.IGNORE_BIOME_REDISTRIBUTION, s));
        ignoreFlatnessCheck.forEach(s -> structuresByFix.put(DefaultBlacklist.IGNORE_FLATNESS_CHECK, s));
        flatnessCheckNarrow.forEach(s -> structuresByFix.put(DefaultFlags.FLATNESS_CHECK_NARROW, s));
        flatnessCheckWide.forEach(s -> structuresByFix.put(DefaultFlags.FLATNESS_CHECK_WIDE, s));
        addTerrainAdaptation.forEach(s -> structuresByFix.put(DefaultFlags.ADD_TERRAIN_ADAPTATION, s));
        DefaultFlags.safeStructureType.forEach(s -> structuresByFix.put(DefaultFlags.SAFE_STRUCTURE_TYPE, s));
        return structuresByFix;
    }
}
