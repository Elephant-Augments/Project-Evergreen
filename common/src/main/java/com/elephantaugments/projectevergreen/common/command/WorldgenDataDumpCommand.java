package com.elephantaugments.projectevergreen.common.command;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static net.minecraft.commands.Commands.literal;

public class WorldgenDataDumpCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
            literal("dump_worldgen_data")
                .requires(cs -> cs.hasPermission(2))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("Dumping formatted entries..."), false);
                    String biomesFile = new SimpleDateFormat("'biomes_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String featuresFile = new SimpleDateFormat("'features_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String processorListsFile = new SimpleDateFormat("'processor_lists_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String entitiesFile = new SimpleDateFormat("'entities_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structuresFile = new SimpleDateFormat("'structures_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structureSetsFile = new SimpleDateFormat("'structure_sets_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structuresByHeightmapFile = new SimpleDateFormat("'structures_by_heightmap_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String mobsByCategoryFile = new SimpleDateFormat("'mobs_by_category_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String featuresByCategoryFile = new SimpleDateFormat("'features_by_category_'yy_MM_dd_HH_mm'.txt'").format(new Date());

                    try {
                        dumpIDs(structuresFile, WorldgenDataManager.loadedStructures);
                        dumpIDs(structureSetsFile, WorldgenDataManager.loadedStructureSets);
                        dumpIDs(processorListsFile, WorldgenDataManager.loadedProcessorLists);
                        dumpIDs(entitiesFile, WorldgenDataManager.loadedEntities);
                        dumpIDs(featuresFile, WorldgenDataManager.loadedFeatures);
                        dumpIDs(biomesFile, WorldgenDataManager.loadedBiomes);

                        categorizeStructures(structuresByHeightmapFile);
                        categorizeFeatures(featuresByCategoryFile);
                        categorizeMobs(mobsByCategoryFile);

                        context.getSource().sendSuccess(() -> {
                            boolean isDedicatedServer = context.getSource().getServer().isDedicatedServer();
                            String firstFile = Paths.get("logs/" + ProjectEvergreen.MODID, structuresFile).toString();

                            String message = "Files created at: `" + firstFile + "`";
                            if (isDedicatedServer) {
                                message += "\n(check the server's logs folder)";
                            }

                            return Component.literal(message);
                        }, false);
                        return 1;
                    } catch (IOException e) {
                        context.getSource().sendSuccess(() -> Component.literal("Failed to dump formatted entries, check logs for error"), false);
                        ProjectEvergreen.LOGGER.error("Failed to dump formatted entries\n", e);
                        return 0;
                    }
                })
        );

        dispatcher.register(
            literal("dump_item_data")
                .requires(cs -> cs.hasPermission(2))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("Dumping formatted entries..."), false);
                    String itemsFile = new SimpleDateFormat("'items_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    SortedSet<String> itemIDs = new TreeSet<>();

                    try {
                        for (ResourceLocation id : BuiltInRegistries.ITEM.keySet()) {
                            if (!id.toString().contains("everycomp:")) {
                                itemIDs.add(id.toString());
                            }
                        }
                        dumpIDs(itemsFile, itemIDs);

                        context.getSource().sendSuccess(() -> {
                            boolean isDedicatedServer = context.getSource().getServer().isDedicatedServer();
                            String firstFile = Paths.get("logs/" + ProjectEvergreen.MODID, itemsFile).toString();

                            String message = "Files created at: `" + firstFile + "`";
                            if (isDedicatedServer) {
                                message += "\n(check the server's logs folder)";
                            }

                            return Component.literal(message);
                        }, false);
                        return 1;
                    } catch (IOException e) {
                        context.getSource().sendSuccess(() -> Component.literal("Failed to dump formatted entries, check logs for error"), false);
                        ProjectEvergreen.LOGGER.error("Failed to dump formatted entries\n", e);
                        return 0;
                    }
                })
        );
    }

    private static void dumpIDs(String fileName, SortedSet<String> idSet) throws IOException {
        Path dumpPath = Path.of("logs/" + ProjectEvergreen.MODID);
        StringBuilder dump = new StringBuilder();
        idSet.forEach(e -> {
            dump.append("\t\"").append(e).append("\",\n");
        });
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void dumpIDs(String fileName, ArrayList<String> idSet) throws IOException {
        Path dumpPath = Path.of("logs/" + ProjectEvergreen.MODID);
        StringBuilder dump = new StringBuilder();
        idSet.forEach(e -> {
            dump.append("\t\"").append(e).append("\",\n");
        });
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void categorizeStructures(String fileName) throws IOException {
        ArrayList<String> structuresByHeightmap = new ArrayList<>();
        structuresByHeightmap.add("GROUND_LEVEL\n");
        structuresByHeightmap.addAll(WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getHeightmap().isPresent() &&
                        s.getHeightmap().get() == PEStructure.Heightmap.GROUNDLEVEL)
                .map(PatchableStructure::getId).toList());
        structuresByHeightmap.add("UNDERGROUND\n");
        structuresByHeightmap.addAll(WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getHeightmap().isPresent() &&
                        s.getHeightmap().get() == PEStructure.Heightmap.UNDERGROUND)
                .map(PatchableStructure::getId).toList());
        structuresByHeightmap.add("OCEAN_SURFACE\n");
        structuresByHeightmap.addAll(WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getHeightmap().isPresent() &&
                        s.getHeightmap().get() == PEStructure.Heightmap.OCEANSURFACE)
                .map(PatchableStructure::getId).toList());
        structuresByHeightmap.add("OCEAN_FLOOR\n");
        structuresByHeightmap.addAll(WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getHeightmap().isPresent() &&
                        s.getHeightmap().get() == PEStructure.Heightmap.OCEANFLOOR)
                .map(PatchableStructure::getId).toList());
        dumpIDs(fileName, structuresByHeightmap);
    }

    private static void categorizeMobs(String fileName) throws IOException {
        ArrayList<String> mobsByCategory = new ArrayList<>();
        mobsByCategory.add("MONSTERS\n");
        mobsByCategory.addAll(WorldgenDataManager.PATCHABLE_ENTITIES.values().stream()
                .filter(m -> m.category().isPresent() && m.category().get().equals("MONSTER"))
                .map(PatchableEntity::getId).toList());
        mobsByCategory.add("CRITTERS\n");
        mobsByCategory.addAll(WorldgenDataManager.PATCHABLE_ENTITIES.values().stream()
                .filter(m -> m.category().isPresent() && m.category().get().equals("CREATURE"))
                .map(PatchableEntity::getId).toList());
        mobsByCategory.add("AMBIENT_CREATURES\n");
        mobsByCategory.addAll(WorldgenDataManager.PATCHABLE_ENTITIES.values().stream()
                .filter(m -> m.category().isPresent() &&
                        (m.category().get().equals("AMBIENT") ||
                                m.category().get().equals("AXOLOTLS") ||
                                m.category().get().equals("WATER_AMBIENT")))
                .map(PatchableEntity::getId).toList());
        mobsByCategory.add("WATER_CREATURES\n");
        mobsByCategory.addAll(WorldgenDataManager.PATCHABLE_ENTITIES.values().stream()
                .filter(m -> m.category().isPresent() &&
                        (m.category().get().equals("WATER_CREATURE") ||
                                m.category().get().equals("UNDERGROUND_WATER_CREATURE") ||
                                m.category().get().equals("WATER_AMBIENT")))
                .map(PatchableEntity::getId).toList());
        dumpIDs(fileName, mobsByCategory);
    }

    private static void categorizeFeatures(String fileName) throws IOException {
        ArrayList<String> featuresByCategory = new ArrayList<>();
        featuresByCategory.add("PLACED_FEATURES\n");
        featuresByCategory.addAll(WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(m -> !m.isModifier())
                .map(PatchableFeature::getId).toList());
        featuresByCategory.add("BIOME_MODIFIERS\n");
        featuresByCategory.addAll(WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(PatchableFeature::isModifier)
                .map(PatchableFeature::getId).toList());
        dumpIDs(fileName, featuresByCategory);
    }
}
