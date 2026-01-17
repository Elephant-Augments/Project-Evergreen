package com.elephantaugments.projectevergreen.neoforge;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import org.slf4j.Logger;

import com.elephantaugments.projectevergreen.common.command.FormatStructureEntriesCommand;
import com.elephantaugments.projectevergreen.neoforge.config.ProjectEvergreenConfig;
import com.elephantaugments.projectevergreen.neoforge.datagen.DataSources;
import com.elephantaugments.projectevergreen.neoforge.datagen.TestConditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ProjectEvergreenNeoforge.MODID)
public class ProjectEvergreenNeoforge {
    public static final String MODID = "project_evergreen";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ProjectEvergreenNeoforge(IEventBus modEventBus, ModContainer modContainer) {

        ProjectEvergreen.init();
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ProjectEvergreen) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(this::registerCommands);


        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, ProjectEvergreenConfig.COMMON_CONFIG, MODID + "_common.toml");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        TestConditions.registerConditions();
        DataSources.registerDataSources();
        //WorldgenDataProvider.loadWorldgenData();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void registerCommands(RegisterCommandsEvent event) {
        FormatStructureEntriesCommand.register(event.getDispatcher());
    }
}
