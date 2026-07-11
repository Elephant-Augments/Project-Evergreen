package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureHeightmaps;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;

public enum PEStructure {
    PIRATE_VILLAGE(
        SupportedMods.INTEGRATED_VILLAGES.name(),
        PEBiome.TROPICAL_ISLAND.tagKey(),
        Constants.EMPTY_LIST
    ),
    WHALE(
        SupportedMods.SKY_WHALE_SHIP.name(),
        PEBiome.TROPICAL_ISLAND.tagKey(),
        ImmutableList.of(
            "minecraft:villager"
        )
    ),
    DRAGON_TOWER(
        SupportedMods.BLOCK_FACTORYS_BOSSES.name(),
        PEBiome.VOLCANIC_CRATER.tagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:missioner",
            "born_in_chaos_v1:fallen_chaos_knight"
        )
    ),
    LICH_TOWER(
        SupportedMods.BOSSES_OF_MASS_DESTRUCTION.name(),
        PEBiome.SPECIAL_ICY.tagKey(),
        Constants.EMPTY_LIST
    ),
    YETI_HIDEOUT(
        SupportedMods.BLOCK_FACTORYS_BOSSES.name(),
        PEBiome.ICY_VOLCANO.tagKey(),
        Constants.EMPTY_LIST
    ),
    UNDERWORLD_ARENA(
        SupportedMods.BLOCK_FACTORYS_BOSSES.name(),
        PEDimension.IS_NETHER.biomeTagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:missioner",
            "born_in_chaos_v1:fallen_chaos_knight"
        )
    ),
    INFERNAL_PUMPKIN(
        SupportedMods.BORN_IN_CHAOS_V1.name(),
        PEBiome.SOUL_VALLEY.tagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:missioner",
            "born_in_chaos_v1:missioner"
        )
    ),
    OBSIDIANTEMPLE(
        SupportedMods.CREATE_STRUCTURES_ARISE.name(),
        PEBiome.OCEAN_DEEP_FROZEN.tagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:corpse_fish",
            "born_in_chaos_v1:corpse_fish",
            "born_in_chaos_v1:corpse_fish",
            "born_in_chaos_v1:glutton_fish"
        )
    ),
    MANSION(
        SupportedMods.MINECRAFT.name(),
        PEBiome.FOREST_DENSE_CONIFEROUS.tagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:missioner"
        )
    ),
    GRAVEYARD(
        SupportedMods.MYTHSANDLEGENDS.name(),
        PEBiome.RED_FLOWER_FIELDS.tagKey(),
        Constants.EMPTY_LIST
    ),
    END_VILLAGER_OUTPOST(
        SupportedMods.END_VILLAGER_OUTPOST.name(),
        PEBiome.STARLIT_PERMAFROST.tagKey(),
        Constants.EMPTY_LIST
    ),
    PORTAL_RUINS_FOREST(
        SupportedMods.ETERNAL_STARLIGHT.name(),
        PEBiome.TROPICAL_VOLCANO.tagKey(),
        Constants.EMPTY_LIST
    ),
    OBSIDILITH_ARENA(
        SupportedMods.BOSSES_OF_MASS_DESTRUCTION.name(),
        PEBiome.STARLIT_SWAMP.tagKey(),
        Constants.EMPTY_LIST
    ),
    GOLEM_FORGE(
        SupportedMods.ETERNAL_STARLIGHT.name(),
        PEBiome.STARLIT_PERMAFROST.tagKey(),
        Constants.EMPTY_LIST
    ),
    CHESED_ARENA(
        SupportedMods.FDBOSSES.name(),
        PEBiome.STARLIT_DESERT.tagKey(),
        Constants.EMPTY_LIST
    ),
    MALKUTH_ARENA(
        SupportedMods.FDBOSSES.name(),
        PEBiome.STARLIT_LUSH_SEA.tagKey(),
        Constants.EMPTY_LIST
    ),
    SHRINES(
        SupportedMods.WITHERSHRINE.name(),
        PEBiome.STARLIT_PERMAFROST.tagKey(),
        ImmutableList.of(
            "born_in_chaos_v1:missioner"
        )
    ),
    STRONGHOLD(
        SupportedMods.INTEGRATED_STRONGHOLD.name(),
        PEBiome.STARLIT_PERMAFROST.tagKey(),
        Constants.EMPTY_LIST
    );

    private List<String> defaultSpawns = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.STRUCTURE_SET.jsonKey();
    private final String jsonPath = Constants.JsonProp.STRUCTURE_SET.jsonPath();
    private final ResourceLocation location;
    private final String biomeTag;

    PEStructure(String namespace,
                String biomeTag,
                List<String> spawnOverrides
    ) {
        String path = name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(namespace.toLowerCase(), path);
        this.biomeTag = biomeTag;
        initIDs(spawnOverrides);
    }

    public ResourceLocation location() {
        return this.location;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public String biomeTag() {
        return this.biomeTag;
    }

    public List<String> defaultSpawns() {
        return this.defaultSpawns;
    }

    public void initIDs(List<String> ids) {
        defaultSpawns.addAll(ids);
    }

    public final static List<String> SupportedTypes = ImmutableList.of(
        "minecraft:jigsaw",
        "integrated_api:generic_structure",
        "friendsandfoes:iceologer_cabin_structure",
        "betterarcheology:betterarcheology_structures",
        "repurposed_structures:generic_jigsaw_structure",
        "moogs_structures:moogs_structures_generic_jigsaw_structure",
        "mvs:mvs_generic_jigsaw_structure",
        "takesapillage:pillager_structure",
        "structure_gel:extended_jigsaw",
        "cataclysm:cataclysm_jigsaw",
        "mostructures:generic",
        "hexerei:witch_hut"
    );

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE,
        SPRAWLING;

        public String jsonKey() {
            return Constants.JsonProp.SIZE.jsonKey();
        }
        public String jsonPath() {
            return Constants.JsonProp.SIZE.jsonPath();
        }

        public int flatnessRadius() {
            return switch (this) {
                case SMALL -> 1;
                case MEDIUM -> 2;
                case LARGE -> 3;
                case SPRAWLING -> 5;
            };
        }
        public int terrainHeight() {
            return switch (this) {
                case SMALL -> Constants.DEFAULT_TERRAIN_HEIGHT_SMALL;
                case MEDIUM -> Constants.DEFAULT_TERRAIN_HEIGHT_MEDIUM;
                case LARGE -> Constants.DEFAULT_TERRAIN_HEIGHT_LARGE;
                case SPRAWLING -> Constants.DEFAULT_TERRAIN_HEIGHT_SPRAWLING;
            };
        }
        public Integer diffOffset() {
            return switch (this) {
                case SMALL -> Constants.SMALL_DIFFICULTY_OFFSET;
                case MEDIUM -> Constants.MEDIUM_DIFFICULTY_OFFSET;
                case LARGE -> Constants.LARGE_DIFFICULTY_OFFSET;
                case SPRAWLING -> Constants.SPRAWLING_DIFFICULTY_OFFSET;
            };
        }
        public static List<String> nonMassiveStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> !s.isMassive())
                    .map(PatchableStructure::getId)
                    .toList();
        }
    }

    public enum Heightmap {
        OCEANFLOOR(DefaultStructureHeightmaps.oceanfloor),
        OCEANSURFACE(Constants.EMPTY_LIST),
        AIRBORN(DefaultStructureHeightmaps.airborn),
        UNDERGROUND(DefaultStructureHeightmaps.underground),
        GROUNDLEVEL(DefaultStructureHeightmaps.groundlevel);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey = Constants.JsonProp.HEIGHTMAP.jsonKey();
        private final String jsonPath = Constants.JsonProp.HEIGHTMAP.jsonPath();
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Structure> tag;

        Heightmap(List<String> defaultIDs) {
            path = "is_heightmap/" + name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.defaultIDs.addAll(defaultIDs);
        }

        public String jsonKey() {
            return jsonKey;
        }

        public String jsonPath() {
            return jsonPath;
        }

        public List<String> defaultIDs() {
            return defaultIDs;
        }

        public void appendIDs(String id) {
            defaultIDs.add(id);
        }

        public void initIDs(List<String> ids) {
            defaultIDs.addAll(ids);
        }

        public TagKey<Structure> tag() {
            return this.tag;
        }

        public String spawnStep() {
            return switch (this) {
                case GROUNDLEVEL, OCEANSURFACE, OCEANFLOOR, AIRBORN -> "surface_structures";
                case UNDERGROUND -> "underground_structures";
            };
        }
        public String projectToHeightmap() {
            return switch (this) {
                case GROUNDLEVEL, OCEANSURFACE, AIRBORN -> "WORLD_SURFACE_WG";
                case OCEANFLOOR -> "OCEAN_FLOOR_WG";
                case UNDERGROUND -> null;
            };
        }

        public static List<String> nonFlatStructures() {
            HashSet<String> notAboveground = Arrays.stream(Heightmap.values())
                    .filter(h -> h != Heightmap.GROUNDLEVEL)
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            return notAboveground.stream().toList();
        }

        public static List<String> allGroundLevelStructures() {
            List<String> notAboveground = nonFlatStructures();
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> !notAboveground.contains(s.id))
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> nonSurfaceStructures() {
            HashSet<String> notOnSurface = Arrays.stream(Heightmap.values())
                    .filter(h -> (h == Heightmap.UNDERGROUND) || (h == Heightmap.AIRBORN))
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            return notOnSurface.stream().toList();
        }

        public static List<String> allOceanSurfaceStructures() {
            HashSet<String> isOceanFloor = Arrays.stream(Heightmap.values())
                    .filter(h -> h == Heightmap.OCEANFLOOR)
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            return PERegion.allWaterStructures().stream()
                    .filter(s -> !isOceanFloor.contains(s) &&
                        !Heightmap.AIRBORN.defaultIDs().contains(s))
                    .toList();
        }

        public static JsonObject buildScaledStartHeight(int min, int max) {
            JsonObject start_height = new JsonObject();
            start_height.addProperty("type", "minecraft:uniform");

            JsonObject min_inclusive = new JsonObject();
            min_inclusive.addProperty("absolute", min);
            JsonObject max_inclusive = new JsonObject();
            max_inclusive.addProperty("absolute", max);

            start_height.add("min_inclusive", min_inclusive);
            start_height.add("max_inclusive", max_inclusive);
            return start_height;
        }

        public static JsonObject buildAbsoluteStartHeight(int constant) {
            JsonObject start_height = new JsonObject();
            start_height.addProperty("absolute", constant);
            return start_height;
        }
    }

    public enum Difficulty {
        DIFFICULTY_LEVEL_0,
        DIFFICULTY_LEVEL_1,
        DIFFICULTY_LEVEL_2,
        DIFFICULTY_LEVEL_3,
        DIFFICULTY_LEVEL_4,
        DIFFICULTY_LEVEL_5,
        DIFFICULTY_LEVEL_6,
        DIFFICULTY_LEVEL_7,
        DIFFICULTY_LEVEL_8,
        DIFFICULTY_LEVEL_9,
        DIFFICULTY_LEVEL_10;

        private int number;
        private final String jsonKey = Constants.JsonProp.DIFFICULTY.jsonKey();
        private final String jsonPath = Constants.JsonProp.DIFFICULTY.jsonPath();
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Structure> tag;

        Difficulty() {
            path = "is_difficulty/" + name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.number = Integer.parseInt(name().toLowerCase().substring(name().toLowerCase().lastIndexOf('_') + 1));
        }

        public Integer number() {
            return this.number;
        }

        public String tagKey() {
            return this.tagKey;
        }

        public String jsonKey() {
            return jsonKey;
        }

        public String jsonPath() {
            return jsonPath;
        }

        public TagKey<Structure> tag() {
            return this.tag;
        }

        public static List<String> allDungeonLevelOneStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.isWilderness() && (
                    s.getDifficulty().isPresent() && (
                        (s.getDifficulty().get() == 0) ||
                        (s.getDifficulty().get() == 1) ||
                        (s.getDifficulty().get() == 2)
                )))
                .map(PatchableStructure::getId)
                .toList();
        }

        public static List<String> allDungeonLevelTwoStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.isWilderness() && (
                    s.getDifficulty().isPresent() && (
                        (s.getDifficulty().get() == 3) ||
                        (s.getDifficulty().get() == 4)
                )))
                .map(PatchableStructure::getId)
                .toList();
        }

        public static List<String> allDungeonLevelThreeStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.isWilderness() && (
                    s.getDifficulty().isPresent() && (
                        (s.getDifficulty().get() == 5) ||
                        (s.getDifficulty().get() == 6)
                )))
                .map(PatchableStructure::getId)
                .toList();
        }

        public static List<String> allDungeonLevelFourStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.isWilderness() && (
                    s.getDifficulty().isPresent() && (
                        (s.getDifficulty().get() == 7) ||
                        (s.getDifficulty().get() == 8)
                )))
                .map(PatchableStructure::getId)
                .toList();
        }

        public static List<String> allDungeonLevelFiveStructures() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.isWilderness() && (
                    s.getDifficulty().isPresent() && (
                        (s.getDifficulty().get() == 9) ||
                        (s.getDifficulty().get() == 10)
                )))
                .map(PatchableStructure::getId)
                .toList();
        }
    }

    public enum Flag {
        PATCHABLE(new PatchableStructures().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledStructures),
        IGNORED(DefaultFlags.ignoredStructureIDs),
        IGNORED_BIOME_REDISTRIBUTION(DefaultFlags.ignoreBiomeRedistribution),
        IGNORED_BIOME_RADIUS_CHECK(DefaultFlags.ignoreBiomeRadiusCheck),
        IGNORED_PLACEMENT_TWEAKS(DefaultFlags.ignorePlacementTweaks),
        ADD_BEARD_THIN_ADAPTATION(DefaultFlags.addBeardThinAdaptation),
        ADD_BURY_ADAPTATION(DefaultFlags.addBuryAdaptation),
        ADD_WATERLOGGING(DefaultFlags.addWaterlogging),
        REMOVE_WATERLOGGING(DefaultFlags.removeWaterlogging),
        ADJUSTED_OCEAN_HEIGHTMAP(DefaultStructureHeightmaps.oceanfloor),
        ADJUSTED_UNDERGROUND_Y_LEVEL_SHALLOW(DefaultFlags.adjustedYLevelShallow),
        ADJUSTED_UNDERGROUND_Y_LEVEL_DEEP(DefaultFlags.adjustedYLevelDeep),
        EARLY_SPAWN_STEP(DefaultFlags.earlySpawnStep),
        LATE_SPAWN_STEP(DefaultFlags.lateSpawnStep),
        FLATNESS_CHECK_SMALL(DefaultFlags.flatnessCheckSmall),
        FLATNESS_CHECK_MEDIUM(DefaultFlags.flatnessCheckMedium),
        FLATNESS_CHECK_LARGE(DefaultFlags.flatnessCheckLarge),
        FLATNESS_CHECK_SPRAWLING(DefaultFlags.flatnessCheckSprawling),
        IS_FROZEN_CAVES(DefaultFlags.forceFrozenCaves),
        IS_SANDY_CAVES(DefaultFlags.forceSandyCaves),
        IS_DEEP_DARK(DefaultFlags.forceDeepDark),
        IS_VOLCANIC_CRATER(DefaultFlags.forceVolcanicCrater),
        IS_BIRCH_FOREST(DefaultFlags.forceBirchForest),
        IS_CULTIVATED_FIELDS(DefaultFlags.forceCultivatedFields),
        IS_TROPICAL_ISLAND(DefaultFlags.forceTropicalIsland),
        IS_CHERRY_FOREST(DefaultFlags.forceCherryForest),
        IS_DIFF_FOUR(DefaultFlags.forceDifficultyFour),
        IS_DIFF_FIVE(DefaultFlags.forceDifficultyFive),
        IS_DIFF_SIX(DefaultFlags.forceDifficultySix),
        IS_DIFF_SEVEN(DefaultFlags.forceDifficultySeven),
        IS_DIFF_EIGHT(DefaultFlags.forceDifficultyEight),
        IS_DIFF_NINE(DefaultFlags.forceDifficultyNine),
        IS_DIFF_TEN(DefaultFlags.forceDifficultyTen),
        IS_BOSS_STRUCTURE(DefaultFlags.bossStructures),
        HAS_VILLAGE_FIX(DefaultFlags.hasVillageFix),
        HAS_SPAWN_OVERRIDES(Constants.EMPTY_LIST),
        IS_CIVILIZATION(Constants.EMPTY_LIST),
        IS_WILDERNESS(Constants.EMPTY_LIST);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey;
        private final String jsonPath;
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Structure> tag;

        Flag(List<String> defaultIDs) {
            jsonKey = name().toLowerCase();
            jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.defaultIDs.addAll(defaultIDs);
        }

        public TagKey<Structure> tag() {
            return this.tag;
        }

        public String jsonKey() {
            return jsonKey;
        }

        public String jsonPath() {
            return jsonPath;
        }

        public List<String> defaultIDs() {
            return defaultIDs;
        }

        public void appendIDs(String id) {
            defaultIDs.add(id);
        }

        public void initIDs(List<String> ids) {
            defaultIDs.addAll(ids);
        }

        public static List<String> isBiasIgnored() {
            HashSet<String> spawnsInRareOrNarrowRegion = Arrays.stream(PERegion.values())
                .filter(r -> (r.name().contains("MOUNTAINOUS") ||
                        r.name().contains("CRAGGY") ||
                        r.name().contains("COASTAL") ||
                        r.name().contains("RIVER") ||
                        r.name().contains("RARE") ||
                        r.name().contains("SPOOKY") ||
                        r.name().contains("ORIENTAL")))
                .map(PERegion::defaultStructures)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
            return spawnsInRareOrNarrowRegion.stream().toList();
        }

        //Fix Ignored Biome Redistribution structures counting (this doesn't work)
        public static List<String> isIgnored() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> s.getDimension().isEmpty() &&
                            !(s.getFlags().contains(Flag.DISABLED)) &&
                            s.getFlags().size() <= 1)
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> isSprawlingFlat() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> s.getFlags().contains(Flag.FLATNESS_CHECK_SPRAWLING))
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> isLargeFlat() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> s.getFlags().contains(Flag.FLATNESS_CHECK_LARGE) ||
                            (s.getStructureSet().isPresent() && s.getStructureSet().get().name().contains("RARE")))
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> isSmallFlat() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> s.getFlags().contains(Flag.FLATNESS_CHECK_SMALL) ||
                            (s.getStructureSet().isPresent() && s.getStructureSet().get().name().contains("DECO")))
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> isCivilization() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(PatchableStructure::isCivilization)
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> isWilderness() {
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> s.isWilderness() || s.isSpecial())
                    .map(PatchableStructure::getId)
                    .toList();
        }
    }
}
