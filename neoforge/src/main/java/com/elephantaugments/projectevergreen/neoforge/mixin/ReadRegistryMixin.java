package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.*;

import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.Resource;

import com.elephantaugments.projectevergreen.neoforge.datagen.TestConditions;
import com.elephantaugments.projectevergreen.common.Constants;

import java.util.SortedSet;

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
        String registryLocation = registry.key().location().getPath();
        String registryName = registryLocation.substring(registryLocation.lastIndexOf('/') + 1);
        String location = resourceKey.location().toString();
        String namespace = resourceKey.location().getNamespace();
        String path = registryLocation + "/" + resourceKey.location().getPath();
        
        
        if (registryLocation.contains("biome_modifier\b")) {
            logProgress(registryName, WorldgenDataManager.loadedBiomeModifiers);

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            if(Constants.supportedModifierTypes.contains(type)) {
                WorldgenDataManager.loadedBiomeModifiers.add(location);
            }
        }
        if (registryLocation.contains("entity_type\b")) {
            logProgress(registryName, WorldgenDataManager.loadedEntities);
            WorldgenDataManager.loadedEntities.add(location);
        }
        if (registryLocation.equals("worldgen/biome")) {
            logProgress(registryName, WorldgenDataManager.loadedBiomes);
            WorldgenDataManager.loadedBiomes.add(location);
        }
        if (registryLocation.equals("worldgen/structure_set")) {
            logProgress(registryName, WorldgenDataManager.loadedStructureSets);
            WorldgenDataManager.loadedStructureSets.add(location);
        }
        if (registryLocation.equals("worldgen/processor_list")) {
            logProgress(registryName, WorldgenDataManager.loadedProcessorLists);
            WorldgenDataManager.loadedProcessorLists.add(location);
        }
        if (registryLocation.equals("worldgen/template_pool")) {
            logProgress(registryName, WorldgenDataManager.loadedTemplatePools);
            WorldgenDataManager.loadedTemplatePools.add(location);
        }
        if (registryLocation.equals("worldgen/structure")) {
            logProgress(registryName, WorldgenDataManager.loadedStructures);
            WorldgenDataManager.loadedStructures.add(location);
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            String step = jsonObject.get("step").getAsString();
            if (TestConditions.flatStructureType(type)) {
                WorldgenDataManager.flatStructures.add(location);
            }
            if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds"))) {
                WorldgenDataManager.undergroundStructures.add(location);
            }
        }
    }

    private static <T> void logProgress(String reg_name, SortedSet<String> loadedList) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Adding " + reg_name + " to " + reg_name + "s list: " + loadedList.size());
        }
    }
}
