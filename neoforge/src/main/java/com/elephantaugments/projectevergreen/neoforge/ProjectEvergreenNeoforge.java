package com.elephantaugments.projectevergreen.neoforge;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.elephantaugments.projectevergreen.neoforge.data.RegistryReader;
import com.elephantaugments.projectevergreen.neoforge.platform.NeoForgePlatformHelper;
import com.elephantaugments.projectevergreen.neoforge.world.PEBiomePlacement;

import com.elephantaugments.projectevergreen.common.command.WorldgenDataDumpCommand;
import com.elephantaugments.projectevergreen.neoforge.config.PEConfig;
import com.elephantaugments.projectevergreen.neoforge.datagen.DataSources;
import com.elephantaugments.projectevergreen.neoforge.datagen.TestConditions;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Optional;
import java.util.SortedSet;

@Mod(ProjectEvergreen.MODID)
public class ProjectEvergreenNeoforge {
    public static final String MODID = "project_evergreen";
    public static final NeoForgePlatformHelper PLATFORM = new NeoForgePlatformHelper();

    public static RegistryReader BIOME_REGISTRY;
    public static RegistryReader STRUCTURE_SET_REGISTRY;
    public static RegistryReader STRUCTURE_REGISTRY;
    public static RegistryReader ENTITY_REGISTRY;
    public static RegistryReader PLACED_FEATURE_REGISTRY;
    public static RegistryReader BIOME_MODIFIER_REGISTRY;

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
        modContainer.registerConfig(ModConfig.Type.COMMON, PEConfig.COMMON_CONFIG, MODID + "_common.toml");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ProjectEvergreen.initCompat();
        TestConditions.registerConditions();
        DataSources.registerDataSources();
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        //initBiolith();
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        BIOME_REGISTRY = null;
        STRUCTURE_SET_REGISTRY = null;
        STRUCTURE_REGISTRY = null;
        ENTITY_REGISTRY = null;
        BIOME_MODIFIER_REGISTRY = null;
        PLACED_FEATURE_REGISTRY = null;
    }

    @SubscribeEvent
    public void onTagsLoaded(TagsUpdatedEvent event) {
        SupportedMods.registryAccess = event.getRegistryAccess();
        initBiolith();
    }

    public static void initBiolith() {
        ProjectEvergreen.LOGGER.info("Grabbing biome values from biome registry reader... " + BIOME_REGISTRY.toString());
        if (PlatformHooks.PLATFORM_HELPER.isModLoaded("biolith")) {
            PEBiomePlacement.register();
        }
    }

    private void registerCommands(RegisterCommandsEvent event) {
        WorldgenDataDumpCommand.register(event.getDispatcher());
    }

    public static ResourceKey<BiomeModifier> createModifierKey(ResourceLocation location) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, location);
    }

//    private static void readRegistryData(RegistryAccess registryAccess) {
//        getLoadedWorldgenData(registryAccess.registry(Registries.BIOME), WorldgenDataManager.loadedBiomes);
//        getLoadedWorldgenData(registryAccess.registry(Registries.STRUCTURE_SET), WorldgenDataManager.loadedStructureSets);
//        getLoadedWorldgenData(registryAccess.registry(Registries.STRUCTURE), WorldgenDataManager.loadedStructures);
//    }
//
//    private static <E> void getLoadedWorldgenData(Optional<Registry<E>> reg, SortedSet<String> loadedList) {
//        reg.ifPresent((registry) -> {
//            String registryLocation = registry.key().location().getPath();
//            String registryName = registryLocation.substring(registryLocation.lastIndexOf('/') + 1);
//
//            registry.entrySet().forEach(t -> {
//                String id = t.getKey().location().toString();
//                logProgress(registryName, loadedList);
//                loadedList.add(id);
//
//                //ResourceLocation location = registry.getKey(t);
//                Optional<IPatchable> wdata;
//                wdata = switch (t.getValue()) {
//                    case Biome b -> Optional.ofNullable(WorldgenDataManager.PATCHABLE_BIOMES.get(id));
//                    case Structure s -> Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURES.get(id));
//                    case StructureSet ss -> Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(id));
//                    default -> Optional.empty();
//                };
//                wdata.ifPresent((p) -> {
//                    p.setLoaded(true);
//                });
//            });
//        });
//    }
//
//    private static <T> void logProgress(String reg_name, SortedSet<String> loadedList) {
//        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
//            ProjectEvergreen.LOGGER.info("Adding " + reg_name + " to " + reg_name + "s list: " + loadedList.size());
//        }
//    }
}
