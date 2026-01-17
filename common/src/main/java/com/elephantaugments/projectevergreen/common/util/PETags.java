package com.elephantaugments.projectevergreen.common.util;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultRegions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class PETags {
    private static final String PREFIX = "#" + ProjectEvergreen.MODID + ":";

    public static void initTags() {}

    public static class Biomes {
        public static final TagKey<Biome> CIVILIZATION_TEMPERATE = createTag(DefaultRegions.CIVILIZATION_TEMPERATE.replace(PREFIX, ""));

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, name));
        }
    }

    public static class Structures {
        public static final TagKey<Structure> DIFFICULTY_LEVEL_0 = createTag("difficulty_level_0");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_1 = createTag("difficulty_level_1");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_2 = createTag("difficulty_level_2");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_3 = createTag("difficulty_level_3");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_4 = createTag("difficulty_level_4");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_5 = createTag("difficulty_level_5");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_6 = createTag("difficulty_level_6");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_7 = createTag("difficulty_level_7");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_8 = createTag("difficulty_level_8");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_9 = createTag("difficulty_level_9");
        public static final TagKey<Structure> DIFFICULTY_LEVEL_10 = createTag("difficulty_level_10");

        private static TagKey<Structure> createTag(String name) {
            return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, name));
        }
    }

}
