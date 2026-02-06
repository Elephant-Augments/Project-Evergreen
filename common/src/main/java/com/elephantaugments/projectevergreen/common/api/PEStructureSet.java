package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructureSets;
import com.elephantaugments.projectevergreen.common.util.PETags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;

public enum PEStructureSet {
    CIVILIZATION_INLAND_SPRAWLING(
        DefaultStructureRarity.civilizationSprawling,
        Constants.DEFAULT_SPRAWLING_SPACING,
        Constants.DEFAULT_SPRAWLING_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_INLAND_MASSIVE(
        DefaultStructureRarity.civilizationMassive,
        Constants.DEFAULT_MASSIVE_SPACING,
        Constants.DEFAULT_MASSIVE_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_INLAND_MEDIUM(
        DefaultStructureRarity.civilizationMedium,
        Constants.DEFAULT_MEDIUM_SPACING,
        Constants.DEFAULT_MEDIUM_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    CIVILIZATION_INLAND_DECO(
        DefaultStructureRarity.civilizationDeco,
        Constants.DEFAULT_DECO_SPACING,
        Constants.DEFAULT_DECO_SEPARATION,
        Constants.CIVILIZATION_SPREAD_OFFSET
    ),
    WILDERNESS_INLAND_SPRAWLING(
        DefaultStructureRarity.wildernessSprawling,
        Constants.DEFAULT_SPRAWLING_SPACING,
        Constants.DEFAULT_SPRAWLING_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_INLAND_MASSIVE(
        DefaultStructureRarity.wildernessMassive,
        Constants.DEFAULT_MASSIVE_SPACING,
        Constants.DEFAULT_MASSIVE_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_INLAND_MEDIUM(
        DefaultStructureRarity.wildernessMedium,
        Constants.DEFAULT_MEDIUM_SPACING,
        Constants.DEFAULT_MEDIUM_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    WILDERNESS_INLAND_DECO(
        DefaultStructureRarity.wildernessDeco,
        Constants.DEFAULT_DECO_SPACING,
        Constants.DEFAULT_DECO_SEPARATION,
        Constants.WILDERNESS_SPREAD_OFFSET
    ),
    OCEAN_FLOATING_MASSIVE(
        DefaultStructureRarity.oceanFloatingMassive,
        Constants.DEFAULT_SPRAWLING_SPACING,
        Constants.DEFAULT_SPRAWLING_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    OCEAN_UNDERWATER_MASSIVE(
        DefaultStructureRarity.oceanUnderwaterMassive,
        Constants.DEFAULT_MASSIVE_SPACING,
        Constants.DEFAULT_MASSIVE_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    OCEAN_ALL_MEDIUM(
        DefaultStructureRarity.oceanAllMedium,
        Constants.DEFAULT_MEDIUM_SPACING,
        Constants.DEFAULT_MEDIUM_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    UNDERGROUND_SPRAWLING(
        DefaultStructureRarity.undergroundSprawling,
        Constants.DEFAULT_SPRAWLING_SPACING,
        Constants.DEFAULT_SPRAWLING_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    ),
    SKY_MASSIVE(
        DefaultStructureRarity.skyMassive,
        Constants.DEFAULT_SPRAWLING_SPACING,
        Constants.DEFAULT_SPRAWLING_SEPARATION,
        Constants.SPECIAL_SPREAD_OFFSET
    );

    private final String path;
    private final String tagPath;
    private final ResourceLocation location;
    private final ResourceLocation tagLocation;
    private TagKey<Structure> structureTag;
    private List<String> defaultSet;
    private int separation;
    private int spacing;
    private Double spreadOffset;

    PEStructureSet(List<String> defaultSet, int defaultSpacing, int defaultSeparation, Double offset) {
        path = name().toLowerCase();
        tagPath = "is_rarity/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        tagLocation = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, tagPath);
        this.structureTag = ProjectEvergreen.createTag(Registries.STRUCTURE, tagLocation);
        this.defaultSet = defaultSet;
        this.spacing = defaultSpacing;
        this.separation = defaultSeparation;
        this.spreadOffset = offset;
    }

    public ResourceLocation location() {
        return this.location;
    }

    public TagKey<Structure> structureTag() {
        return this.structureTag;
    }

    public TagKey<Structure> createStructureTag() {
        TagKey<Structure> tag = ProjectEvergreen.createTag(Registries.STRUCTURE, tagLocation);
        this.structureTag = tag;
        return tag;
    }

    public List<String> defaultStructures() {
        return this.defaultSet;
    }

    public int spacing() {
        return Math.toIntExact(Math.round(this.spacing * spreadOffset));
    }

    public int separation() {
        return Math.toIntExact(Math.round(this.separation * spreadOffset));
    }

    public enum Flag {
        PATCHABLE(new PatchableStructureSets().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledSets);

        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<StructureSet> tag;
        private List<String> defaultIDs;

        Flag(List<String> defaultIDs) {
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE_SET, location);
            this.defaultIDs = defaultIDs;
        }

        public String tagKey() {
            return this.tagKey;
        }

        public TagKey<StructureSet> tag() {
            return this.tag;
        }

        public TagKey<StructureSet> createTag() {
            TagKey<StructureSet> tag = ProjectEvergreen.createTag(Registries.STRUCTURE_SET, location);
            this.tag = tag;
            return tag;
        }

        public List<String> defaultIDs() {
            return defaultIDs;
        }
    }
}
