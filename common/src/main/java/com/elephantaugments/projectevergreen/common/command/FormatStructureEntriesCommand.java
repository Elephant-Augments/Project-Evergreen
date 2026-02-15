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
            literal("dump_worldgen_data")
                .requires(cs -> cs.hasPermission(2))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.literal("Dumping formatted entries..."), false);
                    String biomesFile = new SimpleDateFormat("'biomes_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String biomeModifiersFile = new SimpleDateFormat("'biome_modifiers_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String processorListsFile = new SimpleDateFormat("'processor_lists_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String entitiesFile = new SimpleDateFormat("'entities_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structuresFile = new SimpleDateFormat("'structures_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    String structureSetsFile = new SimpleDateFormat("'structure_sets_'yy_MM_dd_HH_mm'.txt'").format(new Date());
                    try {
                        dumpIDs(structuresFile, WorldgenDataManager.loadedStructures);
                        dumpIDs(structureSetsFile, WorldgenDataManager.loadedStructureSets);
                        dumpIDs(processorListsFile, WorldgenDataManager.loadedProcessorLists);
                        dumpIDs(entitiesFile, WorldgenDataManager.loadedEntities);
                        dumpIDs(biomeModifiersFile, WorldgenDataManager.loadedBiomeModifiers);
                        dumpIDs(biomesFile, WorldgenDataManager.loadedBiomes);
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
}
