package com.elephantaugments.projectevergreen.neoforge.mixin;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

import com.elephantaugments.projectevergreen.common.Constants;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

@Mixin(RegistryDataLoader.class)
public class ReadRegistryMixin {

    @Unique
    private static boolean FIRST_LOAD = true;

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

        boolean firstBiome = true;
        boolean firstStructure = true;
        boolean firstSSet = true;

        //<----------------------BIOMES---------------------->
        if (registryLocation.equals("worldgen/biome")) {
            logProgress(registryName, WorldgenDataManager.loadedBiomes);
            WorldgenDataManager.loadedBiomes.add(location);
            Optional<PatchableBiome> pbiome = Optional.ofNullable(WorldgenDataManager.PATCHABLE_BIOMES.get(location));
            pbiome.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});
            //HolderLookup.RegistryLookup<Biome> lookup = (HolderLookup.RegistryLookup<Biome>) registry.asLookup();
            //WorldgenDataManager.loadPatchableBiomes(Optional.of(lookup));
        }

        //<-------------------STRUCTURE_SETS------------------->
        if (registryLocation.equals("worldgen/structure_set")) {
            logProgress(registryName, WorldgenDataManager.loadedStructureSets);
            WorldgenDataManager.loadedStructureSets.add(location);
            Optional<PatchableStructureSet> psset = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURE_SETS.get(location));
            psset.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});
            //HolderLookup.RegistryLookup<StructureSet> lookup = (HolderLookup.RegistryLookup<StructureSet>) registry.asLookup();
            //WorldgenDataManager.loadPatchableStructureSets(Optional.of(lookup));
        }

        //<---------------------STRUCTURES--------------------->
        if (registryLocation.equals("worldgen/structure")) {
            logProgress(registryName, WorldgenDataManager.loadedStructures);
            WorldgenDataManager.loadedStructures.add(location);
            
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement heightmap = jsonObject.get("project_start_to_heightmap");
            String type = jsonObject.get("type").getAsString().toLowerCase();
            String step = jsonObject.get("step").getAsString().toLowerCase();
            Optional<PatchableStructure> pstructure = Optional.ofNullable(WorldgenDataManager.PATCHABLE_STRUCTURES.get(location));
            pstructure.ifPresentOrElse((p) -> {
                p.setLoaded(true);
                p.setType(type);
                p.setStep(step);
            }, () -> {});

            if  (heightmap != null &&
                (heightmap.getAsString().toLowerCase().equals("ocean_floor") ||
                heightmap.getAsString().toLowerCase().equals("ocean_floor_wg")))
            {
                PEStructure.Heightmap.OCEANFLOOR.appendIDs(location);
            }
            if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds"))) {
                PEStructure.Heightmap.UNDERGROUND.appendIDs(location);
            }
            //TODO: Replace with proper handler in WorldgenDataManager
            if (FIRST_LOAD) {
                HolderLookup.RegistryLookup<Structure> lookup = (HolderLookup.RegistryLookup<Structure>) registry.asLookup();
                WorldgenDataManager.loadPatchableStructures(Optional.of(lookup));
                FIRST_LOAD = false;
            }
        }

        //<------------------BIOME_MODIFIERS------------------>
        if (registryLocation.contains("biome_modifier\b")) {
            logProgress(registryName, WorldgenDataManager.loadedBiomeModifiers);
            /*Optional<PatchableFeature> pfeature = Optional.ofNullable(WorldgenDataManager.PATCHABLE_FEATURES.get(location));
            pfeature.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});*/

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            if(Constants.supportedModifierTypes.contains(type)) {
                WorldgenDataManager.loadedBiomeModifiers.add(location);
            }
        }

        //<---------------------ENTITIES--------------------->
        if (registryLocation.contains("entity_type\b")) {
            logProgress(registryName, WorldgenDataManager.loadedEntities);
            WorldgenDataManager.loadedEntities.add(location);
            /*Optional<PatchableEntity> pmob = Optional.ofNullable(WorldgenDataManager.PATCHABLE_FEATURES.get(location));
            pmob.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});*/
        }

        //<-------------------PROCESSOR_LISTS------------------->
        if (registryLocation.equals("worldgen/processor_list")) {
            logProgress(registryName, WorldgenDataManager.loadedProcessorLists);
            WorldgenDataManager.loadedProcessorLists.add(location);
            /*Optional<PatchableProcessorList> pproc_list = Optional.ofNullable(WorldgenDataManager.PATCHABLE_PROCESSOR_LISTS.get(location));
            pproc_list.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});*/
        }

        //<--------------------TEMPLATE_POOLS-------------------->
        if (registryLocation.equals("worldgen/template_pool")) {
            logProgress(registryName, WorldgenDataManager.loadedTemplatePools);
            WorldgenDataManager.loadedTemplatePools.add(location);
            /*Optional<PatchableTemplatePool> ptemp_pool = Optional.ofNullable(WorldgenDataManager.PATCHABLE_TEMPLATE_POOLS.get(location));
            ptemp_pool.ifPresentOrElse((p) -> {
                p.setLoaded(true);
            }, () -> {});*/
        }
    }

    @Unique
    private static <T> void logProgress(String reg_name, SortedSet<String> loadedList) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Adding " + reg_name + " to " + reg_name + "s list: " + loadedList.size());
        }
    }
}
