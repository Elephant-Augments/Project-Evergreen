package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructureSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.*;
import java.util.stream.Collectors;

public enum PEStructureSet {
    CIVILIZATION_EXTRA_RARE(
        DefaultStructureRarity.civilizationExtraRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_RARE(
        DefaultStructureRarity.civilizationRare,
        Constants.DEFAULT_RARE_SPACING,
        Constants.DEFAULT_RARE_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_COMMON(
        DefaultStructureRarity.civilizationCommon,
        Constants.DEFAULT_COMMON_SPACING,
        Constants.DEFAULT_COMMON_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_DECO(
        DefaultStructureRarity.civilizationDeco,
        Constants.DEFAULT_DECO_SPACING,
        Constants.DEFAULT_DECO_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    WILDERNESS_EXTRA_RARE(
        DefaultStructureRarity.wildernessExtraRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_RARE(
        DefaultStructureRarity.wildernessRare,
        Constants.DEFAULT_RARE_SPACING,
        Constants.DEFAULT_RARE_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_COMMON(
        DefaultStructureRarity.wildernessCommon,
        Constants.DEFAULT_COMMON_SPACING,
        Constants.DEFAULT_COMMON_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_DECO(
        DefaultStructureRarity.wildernessDeco,
        Constants.DEFAULT_DECO_SPACING,
        Constants.DEFAULT_DECO_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    OCEAN_FLOATING_RARE(
        DefaultStructureRarity.oceanFloatingRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    OCEAN_UNDERWATER_RARE(
        DefaultStructureRarity.oceanUnderwaterRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    OCEAN_ALL_COMMON(
        DefaultStructureRarity.oceanAllCommon,
        Constants.DEFAULT_RARE_SPACING,
        Constants.DEFAULT_RARE_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    UNDERGROUND_RARE(
        DefaultStructureRarity.undergroundRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    SKY_RARE(
        DefaultStructureRarity.skyRare,
        Constants.DEFAULT_EXTRA_RARE_SPACING,
        Constants.DEFAULT_EXTRA_RARE_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    );

    private List<String> defaultSet = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.STRUCTURE_SET.jsonKey();
    private final String jsonPath = Constants.JsonProp.STRUCTURE_SET.jsonPath();
    private final String path;
    private final String tagPath;
    private final ResourceLocation location;
    private final ResourceLocation tagLocation;
    private TagKey<Structure> structureTag;
    private int separation;
    private int spacing;
    private Double spreadOffset;

    PEStructureSet(List<String> defaultSet, int defaultSpacing, int defaultSeparation, Double offset) {
        path = name().toLowerCase();
        tagPath = "is_rarity/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        tagLocation = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, tagPath);
        this.structureTag = ProjectEvergreen.createTag(Registries.STRUCTURE, tagLocation);
        this.spacing = defaultSpacing;
        this.separation = defaultSeparation;
        this.spreadOffset = offset;
        initIDs(defaultSet);
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

    public TagKey<Structure> structureTag() {
        return this.structureTag;
    }

    public List<String> defaultStructures() {
        return this.defaultSet;
    }

    public void initIDs(List<String> ids) {
        defaultSet.addAll(ids);
    }

    public int spacing() {
        return Math.toIntExact(Math.round(this.spacing * spreadOffset));
    }

    public int separation() {
        return Math.toIntExact(Math.round(this.separation * spreadOffset));
    }

    public static List<String> hasRedistributedStructures() {
        HashSet<String> redistributedStructures = Arrays.stream(PEStructureSet.values())
                .map(PEStructureSet::defaultStructures)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.keySet().stream()
                .filter(redistributedStructures::contains)
                .toList();
    }

    //TODO: Load SSets with their default structure IDs, disable them here and make a note in dev console if other structures disabled without being redistributed
    public static List<String> hasDisabledStructures() {
        List<String> disabledStructures = WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getFlags().contains(PEStructure.Flag.DISABLED))
                .map(PatchableStructure::getId)
                .toList();
        return WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.keySet().stream()
                .filter(disabledStructures::contains)
                .toList();
    }

    public enum Flag {
        PATCHABLE(new PatchableStructureSets().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledSets);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey;
        private final String jsonPath;
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<StructureSet> tag;

        Flag(List<String> defaultIDs) {
            jsonKey = name().toLowerCase();
            jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE_SET, location);
            initIDs(defaultIDs);
        }

        public String jsonKey() {
            return jsonKey;
        }

        public String jsonPath() {
            return jsonPath;
        }

        public String tagKey() {
            return this.tagKey;
        }

        public TagKey<StructureSet> tag() {
            return this.tag;
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
    }
}
