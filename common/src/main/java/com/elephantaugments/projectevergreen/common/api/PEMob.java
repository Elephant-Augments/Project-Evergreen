package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultMobCategories;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableEntities;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.*;
import java.util.stream.Collectors;

public enum PEMob {
    MONSTER(
        DefaultMobCategories.hostileMonsters,
        3
    ),
    UNCIVILIZED_MONSTER(
        Constants.EMPTY_LIST,
        3
    ),
    LAND_CRITTER(
        DefaultMobCategories.friendlyLandCritters,
        2
    ),
    WATER_CRITTER(
        DefaultMobCategories.waterCritters,
        2
    ),
    NON_WATER_CRITTER(
        Constants.EMPTY_LIST,
        2
    ),
    AMBIENT_CRITTER(
        DefaultMobCategories.ambientCritters,
        1
    );

    private List<String> defaultMobs = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.DIMENSION.jsonKey();
    private final String jsonPath = Constants.JsonProp.DIMENSION.jsonPath();
    private final String path;
    private final ResourceLocation location;
    private TagKey<EntityType<?>> tag;
    private String tagKey;
    private int diffOffset;

    PEMob(List<String> defaultEntities, int diffOffset) {
        path = "is_category/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        this.tagKey = "#" + location;
        this.tag = ProjectEvergreen.createTag(Registries.ENTITY_TYPE, location);
        this.diffOffset = diffOffset;
        initIDs(defaultEntities);
    }

    public String tagKey() {
        return this.tagKey;
    }

    public TagKey<EntityType<?>> tag() {
        return this.tag;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public List<String> defaultMobs() {
        return this.defaultMobs;
    }

    public void initIDs(List<String> ids) {
        defaultMobs.addAll(ids);
    }

    public int difficulty() {
        return diffOffset;
    }

    public static List<String> allMobs() {
        HashSet<String> allMobs = Arrays.stream(PEMob.values())
                .map(PEMob::defaultMobs)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return allMobs.stream().toList();
    }

    public static List<String> uncivilizedMonsters() {
        var monsters = PEMob.MONSTER.defaultMobs();
        monsters.removeAll(VanillaMonsters);
        return monsters;
    }

    public static List<String> allNonWaterMobs() {
        HashSet<String> nonWaterMobs = Arrays.stream(PEMob.values())
                .filter(m -> m != PEMob.WATER_CRITTER)
                .map(PEMob::defaultMobs)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return nonWaterMobs.stream().toList();
    }

    public enum Flag {
        PATCHABLE(new PatchableEntities().getIDs().stream().toList()),
        DISABLED(DefaultFlags.disabledMobs);

        private List<String> defaultIDs = new ArrayList<>();

        private final String jsonKey;
        private final String jsonPath;
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<EntityType<?>> tag;

        Flag(List<String> defaultIDs) {
            jsonKey = name().toLowerCase();
            jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.ENTITY_TYPE, location);
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

        public TagKey<EntityType<?>> tag() {
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

    public final static List<String> VanillaMonsters = ImmutableList.of(
        "minecraft:creeper",
        "minecraft:skeleton",
        "minecraft:slime",
        "minecraft:spider",
        "minecraft:zombie",
        "minecraft:zombie_villager",
        "born_in_chaos_v1:zombie_fisherman",
        "born_in_chaos_v1:zombie_lumberjack",
        "born_in_chaos_v1:decaying_zombie"
    );
}
