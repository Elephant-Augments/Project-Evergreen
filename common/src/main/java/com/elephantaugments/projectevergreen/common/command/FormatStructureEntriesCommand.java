package com.elephantaugments.projectevergreen.common.command;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructureSets;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;

import static net.minecraft.commands.Commands.literal;

public class FormatStructureEntriesCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
            literal("dumpformattedentries")
                .requires(cs -> cs.hasPermission(2))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("Dumping formatted entries..."), false);
                    String biomesFile = new SimpleDateFormat("'biomes_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String biomeModifiersFile = new SimpleDateFormat("'biome_modifiers_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String processorListsFile = new SimpleDateFormat("'processor_lists_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String lootPoolsFile = new SimpleDateFormat("'loot_pools_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structuresFile = new SimpleDateFormat("'structures_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structureSetsFile = new SimpleDateFormat("'structure_sets_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String undergroundStructuresFile = new SimpleDateFormat("'underground_structure_dump_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String flatStructuresFile = new SimpleDateFormat("'flat_structure_dump'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    try {
                        dumpIDs(structuresFile, WorldgenDataManager.loadedStructures);
                        dumpIDs(structureSetsFile, WorldgenDataManager.loadedStructureSets);
                        dumpIDs(processorListsFile, WorldgenDataManager.loadedProcessorLists);
                        dumpIDs(lootPoolsFile, WorldgenDataManager.loadedLootTables);
                        dumpIDs(biomeModifiersFile, WorldgenDataManager.loadedBiomeModifiers);
                        dumpIDs(biomesFile, WorldgenDataManager.loadedBiomes);
                        dumpIDs(undergroundStructuresFile, WorldgenDataManager.undergroundStructures);
                        dumpIDs(flatStructuresFile, WorldgenDataManager.flatStructures);
                        context.getSource().sendSuccess(() -> {
                            boolean isDedicatedServer = context.getSource().getServer().isDedicatedServer();
                            String firstFile = Paths.get(ProjectEvergreen.MODID, structuresFile).toString();

                            String message = "Files created at: `" + firstFile + "`";
                            if (isDedicatedServer) {
                                message += "\n(check the root folder of the server)";
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
        Path dumpPath = Path.of(ProjectEvergreen.MODID);
        StringBuilder dump = new StringBuilder();
        idSet.forEach(e -> {
            dump.append("\t\"").append(e).append("\",\n");
        });
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    /*private static void formatBiomeEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedBiomes.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void formatBiomeModifierEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedBiomeModifiers.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void formatProcessorListEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedProcessorLists.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void formatLootPoolEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedTemplatePools.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void formatStructureEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedStructures.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        PatchableStructures.oldDefaultSupported.forEach(p -> dump.append(formatPatchString(p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }
    
    private static void formatStructureSetEntries(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID + "/format");
        StringBuilder dump = new StringBuilder();
        Constants.loadedStructureSets.forEach((n, p) -> dump.append(formatPatchStringFromMap(n, p)));
        PatchableStructureSets.oldDefaultSupported.forEach(p -> dump.append(formatPatchString(p)));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }

    private static void dumpStructures(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID);
        StringBuilder dump = new StringBuilder();
        Constants.loadedStructures.forEach((n, p) -> dump.append("\"").append(Constants.getLocation(n + ":" + p)).append("\",\n"));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }
    
    private static void dumpStructureSets(String fileName) throws IOException {
        Path dumpPath = Path.of(ProjectEvergreen.MODID);
        StringBuilder dump = new StringBuilder();
        Constants.loadedStructureSets.forEach((n, p) -> dump.append("\"").append(Constants.getLocation(n + ":" + p)).append("\",\n"));
        Files.createDirectories(dumpPath);
        Files.writeString(dumpPath.resolve(fileName), dump.toString());
    }*/

    /*public static String formatStructureSetPatch(String id) {
        StringBuilder patch = new StringBuilder();
        patch.append("\t[\n")
            .append("\t\t{\n")
            .append("\t\t\t\"op\": \"test\",\n")
            .append("\t\t\t\"type\": \"" + ProjectEvergreen.MODID + ":structure_registered\",\n")
            .append("\t\t\t\"value\": \"" + id + "\"\n")
            .append("\t\t},\n")
            .append("\t\t{\n")
            .append("\t\t\t\"op\": \"add\",\n")
            .append("\t\t\t\"path\": \"/structures/-\",\n")
            .append("\t\t\t\"value\": {\n")
            .append("\t\t\t\t\"structure\": \"" + id + "\",\n")
            .append("\t\t\t\t\"weight\": 1\n")
            .append("\t\t\t}\n")
            .append("\t\t}\n")
            .append("\t]");
        return patch.toString();
    }*/

    public static String formatPatchString(String path) {
        return "\t\t\"" + path + "\",\n";
    }

    public static String formatPatchStringFromMap(String namespace, String path) {
        return "\t\t\"" + namespace + ":" + path + "\",\n";
    }
}
