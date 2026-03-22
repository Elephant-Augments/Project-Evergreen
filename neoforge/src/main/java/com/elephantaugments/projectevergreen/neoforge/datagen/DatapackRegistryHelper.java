package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.neoforge.ProjectEvergreenNeoforge;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;

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
}
