package com.elephantaugments.projectevergreen.common.integration;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
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
    QUARK,
    WYTHERS,
    DREAMWOODS,
    BIOMESWEVEGONE,
    REGIONS_UNEXPLORED,
    NATURES_SPIRIT,
    DARKERDEPTHS,
    AETHER,
    THE_AFTERDARK,
    LOSTCITIES,
    INTEGRATED_API,
    MOOGS_STRUCTURES,
    SUPPLEMENTARIES,
    BLOCK_FACTORYS_BOSSES;

    public static RegistryAccess registryAccess;

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
        return ResourceLocation.read(namespace + ":" + path).result();
    }

    public Optional<ResourceKey<Biome>> getBiome(String path) {
        ResourceKey<Registry<Biome>> BIOME_REGISTRY = Biomes.PLAINS.registryKey();
        return registryAccess.lookup(BIOME_REGISTRY).flatMap(r -> r.get(ResourceKey.create(BIOME_REGISTRY, location(path)))).flatMap(Holder.Reference::unwrapKey);
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
