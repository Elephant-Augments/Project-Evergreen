package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

import java.util.Collections;
import java.util.Set;

public class DatapackRegistryHelper {

    public static void generateSpawnRemovalModifier (
            BootstrapContext<BiomeModifier> bootstrap,
            ResourceLocation modifierLocation,
            TagKey<Biome> biomeTag,
            TagKey<EntityType<?>> mobTag)
    {
        HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);
        HolderGetter<EntityType<?>> entities = bootstrap.lookup(Registries.ENTITY_TYPE);

        // Register the biome modifiers.
        bootstrap.register(ProjectEvergreenNeoforge.createModifierKey(modifierLocation),
                new BiomeModifiers.RemoveSpawnsBiomeModifier(
                        biomes.getOrThrow(biomeTag),
                        entities.getOrThrow(mobTag)
                )
        );
    }

    public static void generateFeatureRemovalModifier (
            BootstrapContext<BiomeModifier> bootstrap,
            ResourceLocation modifierLocation,
            TagKey<Biome> biomeTag,
            TagKey<PlacedFeature> featureTag)
    {
        HolderGetter<Biome> biomes = bootstrap.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> features = bootstrap.lookup(Registries.PLACED_FEATURE);

        // Register the biome modifiers.
        bootstrap.register(ProjectEvergreenNeoforge.createModifierKey(modifierLocation),
                new BiomeModifiers.RemoveFeaturesBiomeModifier(
                        biomes.getOrThrow(biomeTag),
                        features.getOrThrow(featureTag),
                        Collections.emptySet()
                )
        );
    }
}
