package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureDimensions;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;

public enum PEDimension {
    IS_OVERWORLD(
        SupportedMods.MINECRAFT.name(),
        PERegion.allOverworldStructures(),
        -1
    ),
    IS_NETHER(
        SupportedMods.MINECRAFT.name(),
        DefaultStructureDimensions.netherStructures,
        Constants.NETHER_DIFFICULTY_OFFSET
    ),
    IS_END(
        SupportedMods.MINECRAFT.name(),
        DefaultStructureDimensions.endStructures,
        Constants.END_DIFFICULTY_OFFSET
    ),
    IS_AETHER(
        SupportedMods.AETHER.name(),
        DefaultStructureDimensions.aetherStructures,
        Constants.AETHER_DIFFICULTY_OFFSET
    ),
    AFTERDARK_BIOMES(
        SupportedMods.THE_AFTERDARK.name(),
        DefaultStructureDimensions.afterdarkStructures,
        Constants.AFTERDARK_DIFFICULTY_OFFSET
    ),
    IS_LOSTCITIES(
        ProjectEvergreen.MODID,
        DefaultStructureDimensions.lostCitiesStructures,
        Constants.LOSTCITIES_DIFFICULTY_OFFSET
    );

    private List<String> defaultStructures = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.DIMENSION.jsonKey();
    private final String jsonPath = Constants.JsonProp.DIMENSION.jsonPath();
    private final String path;
    private final ResourceLocation location;
    private final String biomeTagKey;
    private TagKey<Structure> structureTag;
    private int diffOffset;

    PEDimension(String namespace, List<String> defaultStructures, int diffOffset) {
        path = "is_dimension/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        biomeTagKey = "#" + namespace.toLowerCase() + ":" + name().toLowerCase();
        this.structureTag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
        this.diffOffset = diffOffset;
        initStructures(defaultStructures);
    }

    public TagKey<Structure> structureTag() {
        return this.structureTag;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public String biomeTagKey() {
        return this.biomeTagKey;
    }

    public List<String> defaultStructures() {
        return this.defaultStructures;
    }

    public void initStructures(List<String> ids) {
        defaultStructures.addAll(ids);
    }

    public int difficulty() {
        return Constants.OTHERWORLD_DIFFICULTY + diffOffset;
    }

    public static List<String> allOtherworldStructures() {
        HashSet<String> otherworldStructures = Arrays.stream(PEDimension.values())
                .filter(d -> d != PEDimension.IS_OVERWORLD)
                .map(PEDimension::defaultStructures)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return otherworldStructures.stream().toList();
    }
}
