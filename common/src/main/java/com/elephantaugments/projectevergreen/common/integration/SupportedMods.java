package com.elephantaugments.projectevergreen.common.integration;

import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.Optional;

public enum SupportedMods {
    MINECRAFT,
    PATCHED,
    BIOLITH,
    LITHOSTITCHED,
    WYTHERS,
    REGIONS_UNEXPLORED,
    NATURES_SPIRIT,
    AETHER,
    THE_AFTERDARK,
    LOSTCITIES,
    INTEGRATED_API,
    MOOGS_STRUCTURES;

    private final String namespace;

    SupportedMods() {
        namespace = name().toLowerCase();
    }

    public boolean isLoaded() {
        return PlatformHooks.PLATFORM_HELPER.isModLoaded(namespace);
    }

    public ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }

    public Optional<ResourceLocation> tryLocation(String path) {
        return Optional.ofNullable(ResourceLocation.tryBuild(namespace, path));
    }

    public Optional<ResourceKey<Biome>> getBiome(String id) {
        ResourceKey<Registry<Biome>> BIOME_REGISTRY = Biomes.PLAINS.registryKey();
        Optional<ResourceLocation> location = tryLocation(id);
        return location.isPresent() ?
                Optional.of(ResourceKey.create(BIOME_REGISTRY, location(id))) :
                Optional.empty();
    }

    public Block getBlock(String id) {
        return BuiltInRegistries.BLOCK.get(location(id));
    }

    public Item getItem(String id) {
        return BuiltInRegistries.ITEM.get(location(id));
    }

    public Fluid getFluid(String id) {
        return BuiltInRegistries.FLUID.get(location(id));
    }
}
