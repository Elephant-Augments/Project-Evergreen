package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.*;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureDimensions.Dimension;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity.Size;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableBiomes;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableProcessorLists;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructureSets;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.google.common.collect.ArrayListMultimap;

import java.util.*;

public final class WorldgenDataManager {
    private static final List<String> EMPTY_LIST = new ArrayList<>();

    private static PatchableBiomes biomeData = new PatchableBiomes();
    private static PatchableStructures structureData = new PatchableStructures();
    private static PatchableStructureSets structureSetData = new PatchableStructureSets();
    private static PatchableProcessorLists processorData = new PatchableProcessorLists();

    public static LinkedHashMap<String, PERegion> REGIONS_BY_TAG;
    public static LinkedHashMap<String, PEBiome> BIOMES_BY_ID;
    public static ArrayListMultimap<String, PEBiome> BIOMES_BY_TAG;
    public static LinkedHashMap<String, PEStructureSet> STRUCTURE_SETS_BY_ID;
    public static ArrayListMultimap<String, PEStructure> STRUCTURES_BY_TAG;
    public static LinkedHashMap<String, PEStructure> STRUCTURES_BY_ID;
    public static ArrayListMultimap<Size, PEStructure> STRUCTURES_BY_SIZE;
    public static ArrayListMultimap<Dimension, PEStructure> STRUCTURES_BY_DIM;


    public static PatchableBiomes getBiomeData() {
        return biomeData;
    }

    public static void setBiomeData(String id, PEBiome biome) {
        biomeData.Data.replace(id, biome);
    }

    public static PatchableStructureSets getStructureSetData() {
        return structureSetData;
    }

    public static void setStructureSetData(String id, PEStructureSet structSet) {
        structureSetData.Data.replace(id, structSet);
    }

    public static PatchableStructures getStructureData() {
        return structureData;
    }

    public static void setStructureData(String id, PEStructure structure) {
        structureData.Data.replace(id, structure);
    }

    public static PatchableProcessorLists getProcessorData() {
        return processorData;
    }

    public static void loadDefaultWorldgenData() {
        loadDefaultSubData();
        REGIONS_BY_TAG = DefaultRegions.mapRegionsToTags();
        STRUCTURE_SETS_BY_ID = DefaultStructureRarity.mapStructureSetByID();

        ProjectEvergreen.LOGGER.info("Successfully mapped all worldgen data objects! " +
                STRUCTURES_BY_ID.values().stream().filter(s -> s.getSize() == Size.SPRAWLING).findFirst().get().id);
    }

    public static void loadDefaultSubData() {
        STRUCTURES_BY_TAG = DefaultStructureRegions.mapStructuresToRegion(structureData);
        STRUCTURES_BY_SIZE = DefaultStructureRarity.mapStructuresBySize(structureData);
        STRUCTURES_BY_DIM = DefaultStructureDimensions.mapStructuresToDimensions(structureData);
        STRUCTURES_BY_ID = new LinkedHashMap<>(structureData.Data);
        BIOMES_BY_TAG = DefaultBiomeTags.mapBiomesToTags(biomeData);
        BIOMES_BY_ID = DefaultBiomeTags.buildMapEntries(biomeData, BIOMES_BY_TAG);
    }
}
