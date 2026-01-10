package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.registry.WorldgenRegistryManager;
import com.elephantaugments.projectevergreen.common.util.BiomeUtil;
import com.elephantaugments.projectevergreen.common.util.StructureUtil;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.*;

public final class WorldgenDataProvider
{
    private static final List<String> EMPTY_LIST = new ArrayList<>();
    private static List<String> biomes = new ArrayList<>();
    private static List<String> structureSetData = new ArrayList<>();
    private static Map<String, StructureData> structureData = new TreeMap<>();

    private static final Comparator<String> alphabeticallComparator = (key1, key2) -> {
        boolean isKey1Minecraft = key1.startsWith("minecraft:");
        boolean isKey2Minecraft = key2.startsWith("minecraft:");

        if (isKey1Minecraft && !isKey2Minecraft) {
            return -1;
        } else if (!isKey1Minecraft && isKey2Minecraft) {
            return 1;
        } else {
            return key1.compareTo(key2);
        }
    };

    public static List<String> getBiomes() {
        return biomes;
    }

    public static List<String> getStructureSets() {
        return structureSetData;
    }

    public static Map<String, StructureData> getStructures() {
        return structureData;
    }

    public static void loadWorldgenData() {
        biomes = loadBiomes();
        structureData = loadStructures();
        structureSetData = loadStructureSets();
    }

    public static List<String> loadBiomes() {
        var biomeRegistry = WorldgenRegistryManager.getBiomeRegistry();

        if (biomeRegistry == null) {
            return Collections.emptyList();
        }

        List<String> biomes = new ArrayList<>();

        for (var biomeTag : biomeRegistry.listTags().toList()) {
            biomes.add('#' + biomeTag.unwrapKey().get().location().toString());
        }

        for (var biome : biomeRegistry.listElements().toList()) {
            biomes.add(biome.unwrapKey().get().location().toString());
        }

        ProjectEvergreen.LOGGER.info("Biomes successfully mapped: " + biomes.size());
        return biomes;
    }

    public static List<String> loadStructureSets() {
        var registryManager = WorldgenRegistryManager.getRegistryManager();

        if (registryManager == null) {
            return EMPTY_LIST;
        }

        var structureSetRegistry = registryManager.lookupOrThrow(Registries.STRUCTURE_SET);
        List<String> structureSets = new ArrayList<>();

        for (var structureSetReference : structureSetRegistry.listElements().toList()) {
            var structureSet = structureSetReference.value();
            ResourceLocation structureSetId = structureSetReference.key().location();
            String structureSetStringId = structureSetId.toString();

            structureSets.add(structureSetStringId);
        }

        ProjectEvergreen.LOGGER.info("Structure Sets successfully mapped: " + structureSets.size());
        return structureSets;
    }

    public static Map<String, StructureData> loadStructures() {
        var registryManager = WorldgenRegistryManager.getRegistryManager();

        if (registryManager == null) {
            return Collections.emptyMap();
        }

        var structureRegistry = registryManager.lookupOrThrow(Registries.STRUCTURE);
        var biomeRegistry = registryManager.lookupOrThrow(Registries.BIOME);
        Map<String, StructureData> structures = new TreeMap<>(alphabeticallComparator);

        for (var structureReference : structureRegistry.listElements().toList()) {
            var structure = structureReference.value();
            String structureId = structureReference.key().location().toString();
            var biomeStorage = structure.biomes().unwrap();
            var defaultBiomes = new ArrayList<String>();

            biomeStorage.mapLeft(biomeTagKey -> {
                biomeRegistry.get(biomeTagKey).ifPresent(biomes -> {
                    for (var biome : biomes) {
                        String biomeKey = biome.unwrapKey().get().location().toString();

                        if (defaultBiomes.contains(biomeKey)) {
                            continue;
                        }

                        defaultBiomes.add(biomeKey);
                    }
                });

                return null;
            });

            biomeStorage.mapRight(biomes -> {
                for (var biome : biomes) {
                    String biomeKey = biome.unwrapKey().get().location().toString();

                    if (defaultBiomes.contains(biomeKey)) {
                        continue;
                    }

                    defaultBiomes.add(biomeKey);
                }

                return null;
            });

            StructureData structureData = new StructureData(defaultBiomes, structure.step(), structure.terrainAdaptation());

            if (StructureUtil.isUndergroundStructure(structureData.getStep())) {

            }

            if (StructureUtil.isRawGenerationStructure(structureData.getStep())) {

            }

            structures.put(structureId, structureData);
        }

        ProjectEvergreen.LOGGER.info("Structures successfully mapped: " + structures.size());
        return structures;
    }
}
