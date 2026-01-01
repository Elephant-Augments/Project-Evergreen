package com.elephantaugments.projectevergreen.mixin;

import java.io.Reader;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.*;

import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.Resource;

import com.elephantaugments.projectevergreen.datagen.TestConditions;
import com.elephantaugments.projectevergreen.input.Constants;

@Mixin(RegistryDataLoader.class)
public class ReadRegistryMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), 
            method = "loadElementFromResource", locals = LocalCapture.CAPTURE_FAILHARD)
    private static <E> void projectEvergreen_readRegistry(
        WritableRegistry<E> registry,
        Decoder<E> codec,
        RegistryOps<JsonElement> ops,
        ResourceKey<E> resourceKey,
        Resource resource,
        RegistrationInfo registrationInfo,
        CallbackInfo ci, 
        Decoder<Optional<E>> decoder,
        Reader reader, 
        JsonElement jsonElement) 
    {
        String registryLocation = registry.key().location().getPath();
        String location = resourceKey.location().toString();
        String namespace = resourceKey.location().getNamespace();
        String path = registryLocation + "/" + resourceKey.location().getPath();
        
        
        if (registryLocation.equals("forge/biome_modifier")) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            if(Constants.supportedModifierTypes.contains(type)) {
                Constants.loadedBiomeModifiers.put(namespace, path);
            }
        }
        if (registryLocation.equals("worldgen/biome")) {
            Constants.loadedBiomes.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/structure_set")) {
            Constants.loadedStructureSets.put(namespace, path);
        }
        if (registryLocation.equals("worldgen/structure")) {
            Constants.loadedStructures.put(namespace, path);
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            String step = jsonObject.get("step").getAsString();
            if (TestConditions.flatStructureType(type) && !TestConditions.isIgnored(location)) {
                //ProjectEvergreen.LOGGER.info("Adding flat structure type... " + type);
                Constants.flatStructures.add(location);
            };
            if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds")) &&
                !TestConditions.isIgnored(location)) {
                Constants.undergroundStructures.add(location);
            };
        }
    }
}
