package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
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
        //ProjectEvergreen.LOGGER.info("Initializing Project Evergreen registry mixin.");
        String registryLocation = registry.key().location().getPath();
        String location = resourceKey.location().toString();
        String namespace = resourceKey.location().getNamespace();
        String path = registryLocation + "/" + resourceKey.location().getPath();
        
        
        if (registryLocation.equals("neoforge/biome_modifier")) {
            ProjectEvergreen.LOGGER.info("Adding biome modifier to loaded modifiers list... " + Constants.loadedBiomeModifiers.size());
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            if(Constants.supportedModifierTypes.contains(type)) {
                Constants.loadedBiomeModifiers.put(namespace, path);
            }
        }
        if (registryLocation.equals("worldgen/biome")) {
            //ProjectEvergreen.LOGGER.info("Adding biome to loaded biomes list... " + Constants.loadedBiomes.size());
            Constants.loadedBiomes.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/structure_set")) {
            //ProjectEvergreen.LOGGER.info("Adding structure set to loaded structure sets list... " + Constants.loadedStructureSets.size());
            Constants.loadedStructureSets.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/processor_list")) {
            //ProjectEvergreen.LOGGER.info("Adding processor list to loaded processor lists... " + Constants.loadedProcessorLists.size());
            Constants.loadedProcessorLists.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/template_pool")) {
            //ProjectEvergreen.LOGGER.info("Adding template pool to loaded template pools list... " + Constants.loadedTemplatePools.size());
            Constants.loadedTemplatePools.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/structure")) {
            //ProjectEvergreen.LOGGER.info("Adding structure to loaded structure list... " + Constants.loadedStructures.size());
            Constants.loadedStructures.put(namespace, path);
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            String step = jsonObject.get("step").getAsString();
            if (TestConditions.flatStructureType(type)) {
                Constants.flatStructures.add(location);
            };
            if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds"))) {
                Constants.undergroundStructures.add(location);
            };
        }
    }
}
