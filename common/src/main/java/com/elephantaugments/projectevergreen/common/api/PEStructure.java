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

public enum PEStructure {
    PE_STRUCTURE;

    public final static List<String> SupportedTypes = ImmutableList.of(
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

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE,
        SPRAWLING;

        public String jsonKey() {
            return "size";
        }
        public String jsonPath() {
            return "/" + Constants.PROPERTIES_KEY + "/" + jsonKey();
        }

        public Integer diffOffset() {
            Map<Size, Integer> diffMap = Map.of(
                Size.SMALL, Constants.SMALL_DIFFICULTY_OFFSET,
                Size.MEDIUM, Constants.MEDIUM_DIFFICULTY_OFFSET,
                Size.LARGE, Constants.LARGE_DIFFICULTY_OFFSET,
                Size.SPRAWLING, Constants.SPRAWLING_DIFFICULTY_OFFSET
            );
            return diffMap.get(this);
        }
    }

    public enum Heightmap {
        OCEANFLOOR(DefaultStructureHeightmaps.oceanfloor),
        OCEANSURFACE(Constants.EMPTY_LIST),
        AIRBORN(DefaultStructureHeightmaps.airborn),
        UNDERGROUND(DefaultStructureHeightmaps.underground),
        GROUNDLEVEL(Constants.EMPTY_LIST);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey = "heightmap";
        private final String jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
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

        public static List<String> allGroundLevelStructures() {
            HashSet<String> notAboveground = Arrays.stream(Heightmap.values())
                    .filter(h -> h != Heightmap.GROUNDLEVEL)
                    .map(Heightmap::defaultIDs)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(HashSet::new));
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
        private final String jsonKey = "difficulty";
        private final String jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
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
        FLATNESS_CHECK_SPRAWLING(DefaultFlags.flatnessCheckSprawling);

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
    }
}
