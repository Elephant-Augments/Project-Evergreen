package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureHeightmaps;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PEStructure {
    PE_STRUCTURE;

    public final static List<String> SupportedTypes = ImmutableList.of(
        "minecraft:jigsaw",
        "integrated_api:generic_structure",
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
    }

    public enum Heightmap {
        OCEANFLOOR(DefaultStructureHeightmaps.oceanfloor),
        OCEANSURFACE(Constants.EMPTY_LIST),
        AIRBORN(DefaultStructureHeightmaps.airborn),
        UNDERGROUND(DefaultStructureHeightmaps.underground),
        GROUNDLEVEL(Constants.EMPTY_LIST);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey = Constants.JsonProp.HEIGHTMAP.jsonKey();
        private final String jsonPath = Constants.JsonProp.HEIGHTMAP.jsonPath();
        private final String path;
        private final ResourceLocation location;

        Heightmap(List<String> defaultIDs) {
            path = name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
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

        public static List<String> nonGroundLevelStructures() {
            HashSet<String> notAboveground = Arrays.stream(Heightmap.values())
                    .filter(h -> h != Heightmap.GROUNDLEVEL)
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            return notAboveground.stream().toList();
        }

        public static List<String> allGroundLevelStructures() {
            List<String> notAboveground = nonGroundLevelStructures();
            return WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                    .filter(s -> !notAboveground.contains(s.id))
                    .map(PatchableStructure::getId)
                    .toList();
        }

        public static List<String> allOceanSurfaceStructures() {
            HashSet<String> isOceanFloor = Arrays.stream(Heightmap.values())
                    .filter(h -> h == Heightmap.OCEANFLOOR)
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
            return PERegion.allWaterStructures().stream()
                    .filter(s -> !isOceanFloor.contains(s))
                    .toList();
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
    }

    public enum Flag {
        PATCHABLE(new PatchableStructures().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledStructures),
        IGNORED(DefaultFlags.ignoredStructureIDs),
        IGNORED_BIOME_REDISTRIBUTION(DefaultFlags.ignoreBiomeRedistribution),
        IGNORED_BIOME_RADIUS_CHECK(DefaultFlags.ignoreBiomeRadiusCheck),
        IGNORED_PLACEMENT_TWEAKS(DefaultFlags.ignoreStructureType),
        ADJUSTED_TERRAIN_ADAPTATION(DefaultFlags.adjustedTerrainAdaptation),
        ADJUSTED_OCEAN_HEIGHTMAP(DefaultStructureHeightmaps.oceanfloor),
        ADJUSTED_UNDERGROUND_Y_LEVEL(DefaultFlags.adjustedYLevel),
        FLATNESS_CHECK_SMALL(DefaultFlags.flatnessCheckSmall),
        FLATNESS_CHECK_MEDIUM(DefaultFlags.flatnessCheckMedium),
        FLATNESS_CHECK_LARGE(DefaultFlags.flatnessCheckLarge),
        FLATNESS_CHECK_SPRAWLING(DefaultFlags.flatnessCheckSprawling),
        IS_BIRCH_FOREST(DefaultFlags.forceBirchForest),
        IS_CHERRY_FOREST(DefaultFlags.forceCherryForest);

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
    }
}
