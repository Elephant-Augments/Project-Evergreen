package com.elephantaugments.projectevergreen.neoforge;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.neoforge.platform.NeoForgePlatformHelper;
import com.elephantaugments.projectevergreen.neoforge.world.PEBiomePlacement;

import com.elephantaugments.projectevergreen.common.command.FormatStructureEntriesCommand;
import com.elephantaugments.projectevergreen.neoforge.config.ProjectEvergreenConfig;
import com.elephantaugments.projectevergreen.neoforge.datagen.DataSources;
import com.elephantaugments.projectevergreen.neoforge.datagen.TestConditions;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Optional;

@Mod(ProjectEvergreenNeoforge.MODID)
public class ProjectEvergreenNeoforge {
    public static final String MODID = "project_evergreen";
    public static final NeoForgePlatformHelper PLATFORM = new NeoForgePlatformHelper();

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

        if (SupportedMods.BIOLITH.isLoaded()) {
            PEBiomePlacement.register();
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        TestConditions.registerConditions();
        DataSources.registerDataSources();
    }

    /*@SubscribeEvent
    public void onServerLoad(AddReloadListenerEvent event) {
        WorldgenDataManager.loadDynamicWorldgenData(event.getServerResources().getRegistryLookup());
    }*/

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        //TODO: replace ReadRegistry mixin with a proper handler inside WorldgenDataManager. Needs to run before data load.
        WorldgenDataManager.loadDynamicWorldgenData(event.getServer().registryAccess());
    }

    private void registerCommands(RegisterCommandsEvent event) {
        FormatStructureEntriesCommand.register(event.getDispatcher());
    }
}
