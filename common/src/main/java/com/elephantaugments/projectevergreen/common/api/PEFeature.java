package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFeatureCategories;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableEntities;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.*;
import java.util.stream.Collectors;

public enum PEFeature {
    PLACED_FEATURE(
        initPlacedFeatures()
    ),
    BIOME_MODIFIER(
        initBiomeModifiers()
    );

    private List<String> defaultIDs = new ArrayList<>();

    private final String jsonKey = this.name().toLowerCase();
    private final String jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey();
    private final String path;
    private final ResourceLocation location;
    private TagKey<PlacedFeature> tag;
    private String tagKey;

    PEFeature(List<String> defaultFeatures) {
        path = "is_category/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        this.tagKey = "#" + location;
        this.tag = this.name().equals("PLACED_FEATURE") ?
                ProjectEvergreen.createTag(Registries.PLACED_FEATURE, location) :
                null;
        initIDs(defaultFeatures);
    }

    public String tagKey() {
        return this.tagKey;
    }

    public TagKey<PlacedFeature> tag() {
        return this.tag;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public List<String> defaultIDs() {
        return this.defaultIDs;
    }

    public void initIDs(List<String> ids) {
        defaultIDs.addAll(ids);
    }

    public static List<String> initPlacedFeatures() {
        return WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(f -> !f.isModifier())
                .map(PatchableFeature::getId)
                .toList();
    }

    public static List<String> initBiomeModifiers() {
        return WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(PatchableFeature::isModifier)
                .map(PatchableFeature::getId)
                .toList();
    }

    public enum Flag {
        PATCHABLE(new PatchableFeatures().getIDs().stream().toList()),
        DISABLED(disabledFeatures());

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey;
        private final String jsonPath;
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<PlacedFeature> tag;

        Flag(List<String> defaultIDs) {
            jsonKey = name().toLowerCase();
            jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.PLACED_FEATURE, location);
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

        public TagKey<PlacedFeature> tag() {
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

        public static List<String> disabledFeatures() {
            return PEFeature.PLACED_FEATURE.defaultIDs().stream()
                    .filter(DefaultFlags.disabledFeatures::contains)
                    .toList();
        }

        public static List<String> disabledModifiers() {
            return PEFeature.BIOME_MODIFIER.defaultIDs().stream()
                    .filter(DefaultFlags.disabledFeatures::contains)
                    .toList();
        }
    }
}
