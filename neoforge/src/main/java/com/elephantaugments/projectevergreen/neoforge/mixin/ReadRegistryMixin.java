package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;
import com.mojang.serialization.*;

import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mixin(RegistryDataLoader.class)
public class ReadRegistryMixin {

    @Unique
    private static RegistryReader project_Evergreen$biomeReader;
    @Unique
    private static RegistryReader project_Evergreen$structureSetReader;
    @Unique
    private static RegistryReader project_Evergreen$structureReader;

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"),
            method = "loadElementFromResource")
    private static <E> void projectEvergreen_readRegistry(
        WritableRegistry<E> registry,
        Decoder<E> codec,
        RegistryOps<JsonElement> ops,
        ResourceKey<E> resourceKey,
        Resource resource,
        RegistrationInfo registrationInfo,
        CallbackInfo ci,
        @Local JsonElement jsonElement)
    {

        ResourceLocation regLocation = registry.key().location();

        //<---------------------STRUCTURES--------------------->
        if (regLocation == Registries.STRUCTURE.location()) {
            if (project_Evergreen$structureReader == null) { project_Evergreen$structureReader = new RegistryReader(registry); }
            project_Evergreen$structureReader.readFromRegistry(
                    resourceKey,
                    jsonElement.getAsJsonObject(),
                    WorldgenDataManager.loadedStructures
            );
        }
        //<----------------------BIOMES---------------------->
        if (regLocation == Registries.BIOME.location()) {
            if (project_Evergreen$biomeReader == null) { project_Evergreen$biomeReader = new RegistryReader(registry); }
            project_Evergreen$biomeReader.readFromRegistry(
                    resourceKey,
                    jsonElement.getAsJsonObject(),
                    WorldgenDataManager.loadedBiomes
            );
        }
        //<-------------------STRUCTURE_SETS------------------->
        if (regLocation == Registries.STRUCTURE_SET.location()) {
            if (project_Evergreen$structureSetReader == null) { project_Evergreen$structureSetReader = new RegistryReader(registry); }
            project_Evergreen$structureSetReader.readFromRegistry(
                    resourceKey,
                    jsonElement.getAsJsonObject(),
                    WorldgenDataManager.loadedStructureSets
            );
        }
//
//        //<------------------BIOME_MODIFIERS------------------>
//        if (registryLocation.contains("biome_modifier\b")) {
//            logProgress(registryName, WorldgenDataManager.loadedBiomeModifiers);
//            /*Optional<PatchableFeature> pfeature = Optional.ofNullable(WorldgenDataManager.PATCHABLE_FEATURES.get(location));
//            pfeature.ifPresentOrElse((p) -> {
//                p.setLoaded(true);
//            }, () -> {});*/
//
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            String type = jsonObject.get("type").getAsString();
//
//            if(Constants.supportedModifierTypes.contains(type)) {
//                WorldgenDataManager.loadedBiomeModifiers.add(location);
//            }
//        }
//
//        //<---------------------ENTITIES--------------------->
//        if (registryLocation.contains("entity_type\b")) {
//            logProgress(registryName, WorldgenDataManager.loadedEntities);
//            WorldgenDataManager.loadedEntities.add(location);
//            /*Optional<PatchableEntity> pmob = Optional.ofNullable(WorldgenDataManager.PATCHABLE_FEATURES.get(location));
//            pmob.ifPresentOrElse((p) -> {
//                p.setLoaded(true);
//            }, () -> {});*/
//        }
//
//        //<-------------------PROCESSOR_LISTS------------------->
//        if (registryLocation.equals("worldgen/processor_list")) {
//            logProgress(registryName, WorldgenDataManager.loadedProcessorLists);
//            WorldgenDataManager.loadedProcessorLists.add(location);
//            /*Optional<PatchableProcessorList> pproc_list = Optional.ofNullable(WorldgenDataManager.PATCHABLE_PROCESSOR_LISTS.get(location));
//            pproc_list.ifPresentOrElse((p) -> {
//                p.setLoaded(true);
//            }, () -> {});*/
//        }
//
//        //<--------------------TEMPLATE_POOLS-------------------->
//        if (registryLocation.equals("worldgen/template_pool")) {
//            logProgress(registryName, WorldgenDataManager.loadedTemplatePools);
//            WorldgenDataManager.loadedTemplatePools.add(location);
//            /*Optional<PatchableTemplatePool> ptemp_pool = Optional.ofNullable(WorldgenDataManager.PATCHABLE_TEMPLATE_POOLS.get(location));
//            ptemp_pool.ifPresentOrElse((p) -> {
//                p.setLoaded(true);
//            }, () -> {});*/
//        }
    }

    /*@Inject(at = @At("RETURN"), method = "load(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/core/RegistryAccess;Ljava/util/List;)Lnet/minecraft/core/RegistryAccess$Frozen;")
    private static <E> void projectEvergreen_updatefromResources(
            ResourceManager resourceManager,
            RegistryAccess registryAccess,
            List<RegistryDataLoader.RegistryData<?>> registryData,
            CallbackInfoReturnable<RegistryAccess.Frozen> cir)
    {
        project_Evergreen$biomeReader.updateDefaultWorldgenData();
        project_Evergreen$structureSetReader.updateDefaultWorldgenData();
        project_Evergreen$structureReader.updateDefaultWorldgenData();
        ProjectEvergreen.LOGGER.info("Here's how many ocean surface structures are loaded2: " + PEStructure.Heightmap.OCEANSURFACE.defaultIDs().size());
    }

    @Inject(at = @At("RETURN"), method = "load(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceProvider;Lnet/minecraft/core/RegistryAccess;Ljava/util/List;)Lnet/minecraft/core/RegistryAccess$Frozen;")
    private static <E> void projectEvergreen_updateFromNetwork(
            Map<ResourceKey<? extends Registry<?>>, List<RegistrySynchronization.PackedRegistryEntry>> elements,
            ResourceProvider resourceProvider,
            RegistryAccess registryAccess,
            List<RegistryDataLoader.RegistryData<?>> registryData,
            CallbackInfoReturnable<RegistryAccess.Frozen> cir)
    {
        project_Evergreen$biomeReader.updateDefaultWorldgenData();
        project_Evergreen$structureSetReader.updateDefaultWorldgenData();
        project_Evergreen$structureReader.updateDefaultWorldgenData();
        ProjectEvergreen.LOGGER.info("Here's how many ocean surface structures are loaded3: " + PEStructure.Heightmap.OCEANSURFACE.defaultIDs().size());
    }*/
}
