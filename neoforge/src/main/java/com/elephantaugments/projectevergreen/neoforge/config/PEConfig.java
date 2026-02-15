package com.elephantaugments.projectevergreen.neoforge.config;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEStructure;
import com.elephantaugments.projectevergreen.common.api.PEStructureSet;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = ProjectEvergreen.MODID)
public class PEConfig {

    public static final String PERFORMANCE_MODE_KEY = "performance_friendly_mode";
    public static final String PERFORMANCE_MODE_TEST = ProjectEvergreen.MODID + ":" + PERFORMANCE_MODE_KEY;
    public static final String STRUCTURE_BIOME_REDISTRIBUTION_KEY = "structure_biome_redistribution";
    public static final String STRUCTURE_BIOME_REDISTRIBUTION_TEST = ProjectEvergreen.MODID + ":" + STRUCTURE_BIOME_REDISTRIBUTION_KEY;
    public static final String STRUCTURE_RARITY_REDISTRIBUTION_KEY = "structure_rarity_redistribution";
    public static final String STRUCTURE_RARITY_REDISTRIBUTION_TEST = ProjectEvergreen.MODID + ":" + STRUCTURE_RARITY_REDISTRIBUTION_KEY;

    public static final String POPULATION_BIAS_KEY = "population_bias";
    public static final String POPULATION_BIAS_TEST = ProjectEvergreen.MODID + ":" + POPULATION_BIAS_KEY;
    public static final String POPULATION_BIAS_OFFSET_KEY = "population_bias_offset";
    public static final String SPACING_RARITY_KEY = "spacing_rarity_offset";
    public static final String SEPARATION_RARITY_KEY = "separation_rarity_offset";
    public static final String CIVILIZATION_EXTRA_RARE_OFFSET_KEY = "civilization_inland_sprawling_rarity";
    public static final String CIVILIZATION_RARE_OFFSET_KEY = PEStructureSet.CIVILIZATION_RARE.name().toLowerCase() + "_offset";
    public static final String CIVILIZATION_COMMON_OFFSET_KEY = PEStructureSet.CIVILIZATION_COMMON.name().toLowerCase() + "_offset";
    public static final String CIVILIZATION_DECORATION_OFFSET_KEY = PEStructureSet.CIVILIZATION_DECO.name().toLowerCase() + "_offset";
    public static final String WILDERNESS_EXTRA_RARE_OFFSET_KEY = "wilderness_inland_sprawling_rarity";
    public static final String WILDERNESS_RARE_OFFSET_KEY = PEStructureSet.WILDERNESS_RARE.name().toLowerCase() + "_offset";
    public static final String WILDERNESS_COMMON_OFFSET_KEY = PEStructureSet.WILDERNESS_COMMON.name().toLowerCase() + "_offset";
    public static final String WILDERNESS_DECORATION_OFFSET_KEY = PEStructureSet.WILDERNESS_DECO.name().toLowerCase() + "_offset";
    public static final String OCEAN_RARE_OFFSET_KEY = "ocean_rare_offset";
    public static final String OCEAN_COMMON_OFFSET_KEY = PEStructureSet.OCEAN_ALL_COMMON.name().toLowerCase() + "_offset";
    public static final String UNDERGROUND_RARE_OFFSET_KEY = PEStructureSet.UNDERGROUND_RARE.name().toLowerCase() + "_offset";
    public static final String SKY_RARE_OFFSET_KEY = PEStructureSet.SKY_RARE.name().toLowerCase() + "_offset";

    public static final String COLD_CLIMATE_WATER_NORMALIZATION_KEY = "cold_climate_color_normalization";
    public static final String COLD_CLIMATE_WATER_NORMALIZATION_TEST = ProjectEvergreen.MODID + ":" + COLD_CLIMATE_WATER_NORMALIZATION_KEY;
    public static final String COLD_WATER_COLOR_KEY = "cold_climate_water_color";
    public static final String TEMPERATE_CLIMATE_WATER_NORMALIZATION_KEY = "temperate_climate_color_normalization";
    public static final String TEMPERATE_CLIMATE_WATER_NORMALIZATION_TEST = ProjectEvergreen.MODID + ":" + TEMPERATE_CLIMATE_WATER_NORMALIZATION_KEY;
    public static final String TEMPERATE_WATER_COLOR_KEY = "temperate_climate_water_color";
    public static final String WARM_CLIMATE_WATER_NORMALIZATION_KEY = "warm_climate_color_normalization";
    public static final String WARM_CLIMATE_WATER_NORMALIZATION_TEST = ProjectEvergreen.MODID + ":" + WARM_CLIMATE_WATER_NORMALIZATION_KEY;
    public static final String WARM_WATER_COLOR_KEY = "warm_climate_water_color";
    public static final String ALLOWED_TERRAIN_HEIGHT_KEY = "flatness_allowed_elevation";
    public static final String ALLOWED_TERRAIN_HEIGHT_SMALL_KEY = "small_flatness_allowed_elevation";
    public static final String ALLOWED_TERRAIN_HEIGHT_MEDIUM_KEY = "medium_flatness_allowed_elevation";
    public static final String ALLOWED_TERRAIN_HEIGHT_LARGE_KEY = "large_flatness_allowed_elevation";
    public static final String ALLOWED_TERRAIN_HEIGHT_SPRAWLING_KEY = "sprawling_flatness_allowed_elevation";

    public static final String CONTINENTS_SCALE_KEY = "continents_scale";
    public static final String NON_CONTINENT_ISLAND_SCALE_KEY = "non_continent_island_scale";
    public static final String NON_CONTINENT_ISLAND_AMOUNT_KEY = "non_continent_island_amount";
    public static final String SPAWN_ISLAND_SCALE_KEY = "spawn_island_scale";

    public static final String DEFAULT_LOST_CITIES_BIOME = "minecraft:forest";
    public static final String DEFAULT_LOST_CITIES_LIQUID = "minecraft:water";
    public static final String LOST_CITIES_FIXED_BIOME_KEY = "use_fixed_biome";
    public static final String LOST_CITIES_BIOME_KEY = "lost_cities_biome";
    public static final String LOST_CITIES_LIQUID_KEY = "lost_cities_liquid";


    public static ModConfigSpec COMMON_CONFIG;
    
    private static final String CATEGORY_FEATURES = "features";
    private static final ModConfigSpec.BooleanValue PERFORMANCE_FRIENDLY_MODE;
    private static final ModConfigSpec.BooleanValue STRUCTURE_BIOME_REDISTRIBUTION;
    private static final ModConfigSpec.BooleanValue STRUCTURE_RARITY_REDISTRIBUTION;
    
    private static final String CATEGORY_TWEAKS = "tweaks";
    private static final ModConfigSpec.BooleanValue COLD_CLIMATE_WATER_NORMALIZATION;
    private static final ModConfigSpec.IntValue COLD_WATER_COLOR;
    private static final ModConfigSpec.BooleanValue TEMPERATE_CLIMATE_WATER_NORMALIZATION;
    private static final ModConfigSpec.IntValue TEMPERATE_WATER_COLOR;
    private static final ModConfigSpec.BooleanValue WARM_CLIMATE_WATER_NORMALIZATION;
    private static final ModConfigSpec.IntValue WARM_WATER_COLOR;
    private static final ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_SPRAWLING;
    private static final ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_LARGE;
    private static final ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_MEDIUM;
    private static final ModConfigSpec.IntValue ALLOWED_TERRAIN_HEIGHT_SMALL;

    private static final String CATEGORY_RARITY = "rarity";
    private static final ModConfigSpec.IntValue POPULATION_BIAS;
    private static final ModConfigSpec.IntValue POPULATION_BIAS_OFFSET;
    private static final ModConfigSpec.DoubleValue CIVILIZATION_EXTRA_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue CIVILIZATION_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue CIVILIZATION_COMMON_OFFSET;
    private static final ModConfigSpec.DoubleValue CIVILIZATION_DECORATION_OFFSET;
    private static final ModConfigSpec.DoubleValue WILDERNESS_EXTRA_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue WILDERNESS_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue WILDERNESS_COMMON_OFFSET;
    private static final ModConfigSpec.DoubleValue WILDERNESS_DECORATION_OFFSET;
    private static final ModConfigSpec.DoubleValue UNDERGROUND_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue OCEAN_RARE_OFFSET;
    private static final ModConfigSpec.DoubleValue OCEAN_COMMON_OFFSET;
    private static final ModConfigSpec.DoubleValue SKY_RARE_OFFSET;

    private static final String CATEGORY_CONTINENTS = "continents";
    private static final ModConfigSpec.ConfigValue<Double> CONTINENTS_SCALE;
    private static final ModConfigSpec.ConfigValue<Double> NON_CONTINENT_ISLAND_SCALE;
    private static final ModConfigSpec.ConfigValue<Double> NON_CONTINENT_ISLAND_AMOUNT;
    private static final ModConfigSpec.ConfigValue<Double> SPAWN_ISLAND_SCALE;

    private static final String CATEGORY_LOST_CITIES = "lost_cities";
    private static final ModConfigSpec.ConfigValue<Boolean> LOST_CITIES_FIXED_BIOME;
    private static final ModConfigSpec.ConfigValue<String> LOST_CITIES_BIOME;
    private static final ModConfigSpec.ConfigValue<String> LOST_CITIES_LIQUID;
    
    public static boolean performanceFriendlyMode;
    public static boolean structureBiomeRedistribution;
    public static boolean structureRarityRedistribution;

    public static int populationBias;
    public static int populationBiasOffset;
    public static int allowedTerrainHeightSprawling;
    public static int allowedTerrainHeightLarge;
    public static int allowedTerrainHeightMedium;
    public static int allowedTerrainHeightSmall;

    public static boolean coldBiomeColorNormalization;
    public static int coldWaterColor;
    public static boolean temperateBiomeColorNormalization;
    public static int temperateWaterColor;
    public static boolean warmBiomeColorNormalization;
    public static int warmWaterColor;

    public static double civilizationExtraRareOffset;
    public static double civilizationRareOffset;
    public static double civilizationCommonOffset;
    public static double civilizationDecoOffset;
    public static double wildernessExtraRareOffset;
    public static double wildernessRareOffset;
    public static double wildernessCommonOffset;
    public static double wildernessDecoOffset;
    public static double undergroundRareOffset;
    public static double oceanRareOffset;
    public static double oceanCommonOffset;
    public static double skyRareOffset;

    public static double continentScale;
    public static double nonContinentIslandScale;
    public static double nonContinentIslandAmount;
    public static double spawnIslandScale;

    public static boolean useLostCitiesFixedBiome;
    public static String lostCitiesBiome;
    public static String lostCitiesLiquid;


    

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        try {
            ProjectEvergreen.LOGGER.info("Loading Project Evergreen config...");

            performanceFriendlyMode = PERFORMANCE_FRIENDLY_MODE.get();
            structureBiomeRedistribution = STRUCTURE_BIOME_REDISTRIBUTION.get();
            structureRarityRedistribution = STRUCTURE_RARITY_REDISTRIBUTION.get();

            allowedTerrainHeightSprawling = ALLOWED_TERRAIN_HEIGHT_SPRAWLING.get();
            allowedTerrainHeightLarge = ALLOWED_TERRAIN_HEIGHT_LARGE.get();
            allowedTerrainHeightMedium = ALLOWED_TERRAIN_HEIGHT_MEDIUM.get();
            allowedTerrainHeightSmall = ALLOWED_TERRAIN_HEIGHT_SMALL.get();

            coldBiomeColorNormalization = COLD_CLIMATE_WATER_NORMALIZATION.get();
            coldWaterColor = COLD_WATER_COLOR.getAsInt();
            temperateBiomeColorNormalization = TEMPERATE_CLIMATE_WATER_NORMALIZATION.get();
            temperateWaterColor = TEMPERATE_WATER_COLOR.getAsInt();
            warmBiomeColorNormalization = WARM_CLIMATE_WATER_NORMALIZATION.get();
            warmWaterColor = WARM_WATER_COLOR.getAsInt();

            populationBias = POPULATION_BIAS.get();
            populationBiasOffset = POPULATION_BIAS_OFFSET.get();
            civilizationExtraRareOffset = CIVILIZATION_EXTRA_RARE_OFFSET.get();
            civilizationRareOffset = CIVILIZATION_RARE_OFFSET.get();
            civilizationCommonOffset = CIVILIZATION_COMMON_OFFSET.get();
            civilizationDecoOffset = CIVILIZATION_DECORATION_OFFSET.get();
            wildernessExtraRareOffset = WILDERNESS_EXTRA_RARE_OFFSET.get();
            wildernessRareOffset = WILDERNESS_RARE_OFFSET.get();
            wildernessCommonOffset = WILDERNESS_COMMON_OFFSET.get();
            wildernessDecoOffset = WILDERNESS_DECORATION_OFFSET.get();
            undergroundRareOffset = UNDERGROUND_RARE_OFFSET.get();
            oceanRareOffset = OCEAN_RARE_OFFSET.get();
            oceanCommonOffset = OCEAN_COMMON_OFFSET.get();
            skyRareOffset = SKY_RARE_OFFSET.get();

            continentScale = CONTINENTS_SCALE.get();
            nonContinentIslandScale = NON_CONTINENT_ISLAND_SCALE.get();
            nonContinentIslandAmount = NON_CONTINENT_ISLAND_AMOUNT.get();
            spawnIslandScale = SPAWN_ISLAND_SCALE.get();

            useLostCitiesFixedBiome = LOST_CITIES_FIXED_BIOME.get();
            lostCitiesBiome = LOST_CITIES_BIOME.get();
            lostCitiesLiquid = LOST_CITIES_LIQUID.get();

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
            .define(STRUCTURE_BIOME_REDISTRIBUTION_KEY, true);
        STRUCTURE_RARITY_REDISTRIBUTION = COMMON_BUILDER
            .comment("# Enable/Disable rarity distribution based on structure size.\n(e.g Massive structures are rare, decorative structures more common.)")
            .define(STRUCTURE_RARITY_REDISTRIBUTION_KEY, true);
        PERFORMANCE_FRIENDLY_MODE = COMMON_BUILDER
            .comment("# Disables extra structure placement checks that may cause chunk lag.\n(e.g Disables terrain flatness, biome radius, and no-spawn-in-water checks.)")
            .define(PERFORMANCE_MODE_KEY, false);


        COMMON_BUILDER.comment("Rarity Spread").push(CATEGORY_RARITY);
        POPULATION_BIAS = COMMON_BUILDER
                .comment("# Determines the natural spread of Wilderness/Civilization structures throughout the Overworld.\n# (0 = more Wilderness, 1 = balanced Wilderness/Civilization, 2 = more Civilization)")
                .defineInRange(POPULATION_BIAS_KEY, 1, 0, 2);
        POPULATION_BIAS_OFFSET = COMMON_BUILDER
                .comment("# The degree to which non-biased structures should be isolated.\n# (2 = slightly rare, 5 = extremely rare)")
                .defineInRange(POPULATION_BIAS_OFFSET_KEY, 3, 2, 5);
        CIVILIZATION_EXTRA_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for villages and other extra-rare Civilization structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(CIVILIZATION_EXTRA_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        CIVILIZATION_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for large Civilization structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(CIVILIZATION_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        CIVILIZATION_COMMON_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for medium Civilization structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(CIVILIZATION_COMMON_OFFSET_KEY, 1.0, 0.2, 2.0);
        CIVILIZATION_DECORATION_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for small decorative structures found in Civilization biomes.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(CIVILIZATION_DECORATION_OFFSET_KEY, 1.0, 0.2, 2.0);
        WILDERNESS_EXTRA_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for boss arenas and other extra-rare Wilderness structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(WILDERNESS_EXTRA_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        WILDERNESS_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for large Wilderness structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(WILDERNESS_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        WILDERNESS_COMMON_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for medium Wilderness structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(WILDERNESS_COMMON_OFFSET_KEY, 1.0, 0.2, 2.0);
        WILDERNESS_DECORATION_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for small decorative structures found in some Wilderness biomes.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(WILDERNESS_DECORATION_OFFSET_KEY, 1.0, 0.2, 2.0);
        UNDERGROUND_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for massive Underground structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(UNDERGROUND_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        OCEAN_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for massive Ocean structures, both underwater and on the surface.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(OCEAN_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        OCEAN_COMMON_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for medium to large Ocean structures, both underwater and on the surface.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(OCEAN_COMMON_OFFSET_KEY, 1.0, 0.2, 2.0);
        SKY_RARE_OFFSET = COMMON_BUILDER
                .comment("# Rarity offset for massive floating Sky structures.\n# (0.2 = most common, 2.0 = most rare)")
                .defineInRange(SKY_RARE_OFFSET_KEY, 1.0, 0.2, 2.0);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("Tweaks & Fixes").push(CATEGORY_TWEAKS);
        COLD_CLIMATE_WATER_NORMALIZATION = COMMON_BUILDER
                .comment("## If true, all cold climate biomes will adopt the same water color value (below)")
                .define(COLD_CLIMATE_WATER_NORMALIZATION_KEY, false);
        COLD_WATER_COLOR = COMMON_BUILDER
                .defineInRange(COLD_WATER_COLOR_KEY, Constants.DEFAULT_COLD_WATER_COLOR, 0, 16777215);
        TEMPERATE_CLIMATE_WATER_NORMALIZATION = COMMON_BUILDER
                .comment("## If true, all temperate climate biomes will adopt the same water color value (below)")
                .define(TEMPERATE_CLIMATE_WATER_NORMALIZATION_KEY, false);
        TEMPERATE_WATER_COLOR = COMMON_BUILDER
                .defineInRange(TEMPERATE_WATER_COLOR_KEY, Constants.DEFAULT_TEMPERATE_WATER_COLOR, 0, 16777215);
        WARM_CLIMATE_WATER_NORMALIZATION = COMMON_BUILDER
                .comment("## If true, all warm climate biomes will adopt the same water color value (below)")
                .define(WARM_CLIMATE_WATER_NORMALIZATION_KEY, false);
        WARM_WATER_COLOR = COMMON_BUILDER
                .defineInRange(WARM_WATER_COLOR_KEY, Constants.DEFAULT_WARM_WATER_COLOR, 0, 16777215);

        ALLOWED_TERRAIN_HEIGHT_SPRAWLING = COMMON_BUILDER
                .comment("# The average elevation (in blocks) at which flat_sprawling (~5x5 chunk) structures can be allowed to spawn.\n# (12 = extremely flat, 60 = vanilla parity)")
                .defineInRange(ALLOWED_TERRAIN_HEIGHT_SPRAWLING_KEY, PEStructure.Size.SPRAWLING.terrainHeight(), 12, 60);
        ALLOWED_TERRAIN_HEIGHT_LARGE = COMMON_BUILDER
                .comment("# The average elevation (in blocks) at which flat_large (~3x3 chunk) structures can be allowed to spawn.\n# (10 = extremely flat, 50 = vanilla parity)")
                .defineInRange(ALLOWED_TERRAIN_HEIGHT_LARGE_KEY, PEStructure.Size.LARGE.terrainHeight(), 10, 50);
        ALLOWED_TERRAIN_HEIGHT_MEDIUM = COMMON_BUILDER
                .comment("# The average elevation (in blocks) at which flat_medium (~2x2 chunk) structures can be allowed to spawn.\n# (8 = extremely flat, 40 = vanilla parity)")
                .defineInRange(ALLOWED_TERRAIN_HEIGHT_MEDIUM_KEY, PEStructure.Size.MEDIUM.terrainHeight(), 8, 40);
        ALLOWED_TERRAIN_HEIGHT_SMALL = COMMON_BUILDER
                .comment("# The average elevation (in blocks) at which flat_small (~1x1 chunk) structures can be allowed to spawn.\n# (6 = extremely flat, 30 = vanilla parity)")
                .defineInRange(ALLOWED_TERRAIN_HEIGHT_SMALL_KEY, PEStructure.Size.SMALL.terrainHeight(), 6, 30);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("Continents Tweaks").push(CATEGORY_CONTINENTS)
                .comment("\n## WARNING: Will create chunk borders if changed on an existing world.");
        CONTINENTS_SCALE = COMMON_BUILDER
                .comment("## <--------------- Overall continents size --------------->")
                .defineInRange(CONTINENTS_SCALE_KEY, 1.0, 0.25, 4.0);
        NON_CONTINENT_ISLAND_AMOUNT = COMMON_BUILDER
                .defineInRange(NON_CONTINENT_ISLAND_AMOUNT_KEY, 1.0, 0.25, 2.0);
        NON_CONTINENT_ISLAND_SCALE = COMMON_BUILDER
                .defineInRange(NON_CONTINENT_ISLAND_SCALE_KEY, 1.0, 0.25, 4.0);
        SPAWN_ISLAND_SCALE = COMMON_BUILDER
                .defineInRange(SPAWN_ISLAND_SCALE_KEY, 1.0, 0.01, 4.0);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.comment("Lost Cities Tweaks").push(CATEGORY_LOST_CITIES);
        LOST_CITIES_FIXED_BIOME = COMMON_BUILDER
                .comment("## <-------- Use a fixed biome (below) for the Lost Cities dimension -------->")
                .define(LOST_CITIES_FIXED_BIOME_KEY, false);
        LOST_CITIES_BIOME = COMMON_BUILDER
                .comment("# The ID of the biome used to generate the Lost Cities dimension.")
                .define(LOST_CITIES_BIOME_KEY, DEFAULT_LOST_CITIES_BIOME);
        LOST_CITIES_LIQUID = COMMON_BUILDER
                .comment("# The ID of the liquid generated in oceans/lakes within the Lost Cities dimension.")
                .define(LOST_CITIES_LIQUID_KEY, DEFAULT_LOST_CITIES_LIQUID);

        /*COMMON_BUILDER.comment("Blacklist & Other Fixes").push(CATEGORY_FIXES);
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
        */
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static boolean validateListItem(final Object obj) {
        return obj instanceof String;
    }
}
