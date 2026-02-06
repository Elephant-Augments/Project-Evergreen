package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.elephantaugments.projectevergreen.common.util.PETags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.List;

public enum PEStructure {
    PE_STRUCTURE;

    public enum Size {
        SMALL, MEDIUM, LARGE, SPRAWLING;
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

        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Structure> tag;

        Difficulty() {
            path = "is_difficulty/" + name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
        }

        public String tagKey() {
            return this.tagKey;
        }

        public TagKey<Structure> tag() {
            return this.tag;
        }

        public TagKey<Structure> createTag() {
            TagKey<Structure> tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.tag = tag;
            return tag;
        }
    }

    public enum Flag {
        PATCHABLE(new PatchableStructures().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledStructures),
        IGNORED(DefaultFlags.ignoredStructureIDs),
        IGNORED_BIOME_REDISTRIBUTION(DefaultFlags.ignoreBiomeRedistribution),
        IGNORED_PLACEMENT_TWEAKS(DefaultFlags.ignoreStructureType),
        ADJUSTED_TERRAIN_ADAPTATION(DefaultFlags.adjustedTerrainAdaptation),
        ADJUSTED_OCEAN_HEIGHTMAP(DefaultFlags.adjustedOceanHeightmap),
        ADJUSTED_UNDERGROUND_Y_LEVEL(DefaultFlags.adjustedYLevel),
        FLATNESS_CHECK_SMALL(DefaultFlags.flatnessCheckSmall),
        FLATNESS_CHECK_MEDIUM(DefaultFlags.flatnessCheckMedium),
        FLATNESS_CHECK_LARGE(DefaultFlags.flatnessCheckLarge),
        FLATNESS_CHECK_SPRAWLING(DefaultFlags.flatnessCheckSprawling);

        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Structure> tag;
        private List<String> defaultIDs;

        Flag(List<String> defaultIDs) {
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.defaultIDs = defaultIDs;
        }

        public TagKey<Structure> tag() {
            return this.tag;
        }

        public TagKey<Structure> createTag() {
            TagKey<Structure> tag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
            this.tag = tag;
            return tag;
        }

        public List<String> defaultIDs() {
            return defaultIDs;
        }
    }
}
