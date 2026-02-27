package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import com.elephantaugments.projectevergreen.neoforge.data.RegistryReader;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;
import com.mojang.serialization.*;

import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RegistryDataLoader.class)
public class ReadRegistryMixin {

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
            if (ProjectEvergreenNeoforge.STRUCTURE_REGISTRY == null) { ProjectEvergreenNeoforge.STRUCTURE_REGISTRY = new RegistryReader(registry); }
            ProjectEvergreenNeoforge.STRUCTURE_REGISTRY.readFromRegistry(
                    resourceKey,
                    jsonElement.getAsJsonObject(),
                    WorldgenDataManager.loadedStructures
            );
        }
        //<----------------------BIOMES---------------------->
        if (regLocation == Registries.BIOME.location()) {
            if (ProjectEvergreenNeoforge.BIOME_REGISTRY == null) { ProjectEvergreenNeoforge.BIOME_REGISTRY = new RegistryReader(registry); }
            ProjectEvergreenNeoforge.BIOME_REGISTRY.readFromRegistry(
                    resourceKey,
                    jsonElement.getAsJsonObject(),
                    WorldgenDataManager.loadedBiomes
            );
        }
        //<-------------------STRUCTURE_SETS------------------->
        if (regLocation == Registries.STRUCTURE_SET.location()) {
            if (ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY == null) { ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY = new RegistryReader(registry); }
            ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY.readFromRegistry(
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
        ProjectEvergreenNeoforge.BIOME_REGISTRY.updateLoadedWorldgenData(registryAccess);
        ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY.updateLoadedWorldgenData(registryAccess);
        ProjectEvergreenNeoforge.STRUCTURE_REGISTRY.updateLoadedWorldgenData(registryAccess);
    }

    @Inject(at = @At("RETURN"), method = "load(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceProvider;Lnet/minecraft/core/RegistryAccess;Ljava/util/List;)Lnet/minecraft/core/RegistryAccess$Frozen;")
    private static <E> void projectEvergreen_updateFromNetwork(
            Map<ResourceKey<? extends Registry<?>>, List<RegistrySynchronization.PackedRegistryEntry>> elements,
            ResourceProvider resourceProvider,
            RegistryAccess registryAccess,
            List<RegistryDataLoader.RegistryData<?>> registryData,
            CallbackInfoReturnable<RegistryAccess.Frozen> cir)
    {
        ProjectEvergreenNeoforge.BIOME_REGISTRY.updateLoadedWorldgenData(registryAccess);
        ProjectEvergreenNeoforge.STRUCTURE_SET_REGISTRY.updateLoadedWorldgenData(registryAccess);
        ProjectEvergreenNeoforge.STRUCTURE_REGISTRY.updateLoadedWorldgenData(registryAccess);
    }*/
}
