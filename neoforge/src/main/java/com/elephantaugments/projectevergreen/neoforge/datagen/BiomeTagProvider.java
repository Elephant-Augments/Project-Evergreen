package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.elephantaugments.projectevergreen.neoforge.platform.NeoForgePlatformHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BiomeTagProvider extends BiomeTagsProvider {

    Collator collator = Collator.getInstance();

    public BiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ProjectEvergreen.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (PEBiome biome : PEBiome.values()) {
            setBiomeTag(biome);
        }
        //IS_REGION
        for (PERegion region : PERegion.values()) {
            setRegionTag(region);
        }
        //IS_FLAGGED
        for (PEBiome.Flag flag : PEBiome.Flag.values()) {
            setFlagTag(flag);
        }
    }

    private void setBiomeTag(PEBiome biome) {
        Optional.ofNullable(biome.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating biome tag... " + tag.location()); }
                List<ResourceLocation> biomes = biome.defaultTags().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                biomes.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setRegionTag(PERegion region) {
        Optional.ofNullable(region.biomeTag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating region tag... " + tag.location()); }
                List<ResourceLocation> regional_structures = region.defaultStructures().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                regional_structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setFlagTag(PEBiome.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating biome flag tag... " + tag.location()); }
                List<ResourceLocation> regional_structures = flag.defaultIDs().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                regional_structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }
}
