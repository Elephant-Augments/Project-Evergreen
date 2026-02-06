package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
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
        ProjectEvergreen.LOGGER.info("Initializing Project Evergreen registry mixin.");
        String registryLocation = registry.key().location().getPath();
        String location = resourceKey.location().toString();
        String namespace = resourceKey.location().getNamespace();
        String path = registryLocation + "/" + resourceKey.location().getPath();
        
        
        if (registryLocation.contains("biome_modifier\b")) {
            ProjectEvergreen.LOGGER.info("Adding biome modifier to loaded modifiers list... " + WorldgenDataManager.loadedBiomeModifiers.size());

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            if(Constants.supportedModifierTypes.contains(type)) {
                WorldgenDataManager.loadedBiomeModifiers.add(location);
            }
        }
        if (registryLocation.contains("entity_type\b")) {
            ProjectEvergreen.LOGGER.info("Adding entity to loaded entity list... " + WorldgenDataManager.loadedEntities.size());
            WorldgenDataManager.loadedEntities.add(location);
        }
        if (registryLocation.equals("worldgen/biome")) {
            //ProjectEvergreen.LOGGER.info("Adding biome to loaded biomes list... " + Constants.loadedBiomes.size());
            WorldgenDataManager.loadedBiomes.add(location);
        }
        if (registryLocation.equals("worldgen/structure_set")) {
            //ProjectEvergreen.LOGGER.info("Adding structure set to loaded structure sets list... " + Constants.loadedStructureSets.size());
            WorldgenDataManager.loadedStructureSets.add(location);
        }
        if (registryLocation.equals("worldgen/processor_list")) {
            //ProjectEvergreen.LOGGER.info("Adding processor list to loaded processor lists... " + Constants.loadedProcessorLists.size());
            WorldgenDataManager.loadedProcessorLists.add(location);
        }
        if (registryLocation.equals("worldgen/template_pool")) {
            //ProjectEvergreen.LOGGER.info("Adding template pool to loaded template pools list... " + Constants.loadedTemplatePools.size());
            WorldgenDataManager.loadedTemplatePools.add(location);
        }
        if (registryLocation.equals("worldgen/structure")) {
            //ProjectEvergreen.LOGGER.info("Adding structure to loaded structure list... " + Constants.loadedStructures.size());
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
}
