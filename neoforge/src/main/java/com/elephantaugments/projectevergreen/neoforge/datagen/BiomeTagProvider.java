package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
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
        tag(PEBiome.UNDERGROUND.tag()).addOptionalTag(ResourceLocation.parse(Constants.CAVE_TAG));
        //IS_REGION
        for (PERegion region : PERegion.values()) {
            setRegionTag(region);
        }
        //IS_DIFFICULTY
        for (PERegion region : PERegion.values()) {
            setDangerTag(region);
        }
        //IS_FLAGGED
        for (PEBiome.Flag flag : PEBiome.Flag.values()) {
            setFlagTag(flag);
        }
        setEternalStarlightTag();
    }

    private void setEternalStarlightTag() {
        ResourceLocation dimensionTag = ResourceLocation.fromNamespaceAndPath(SupportedMods.ETERNAL_STARLIGHT.name().toLowerCase(), "is_" + SupportedMods.ETERNAL_STARLIGHT.name().toLowerCase());
        TagKey<Biome> eternalStarlightBiomes = ProjectEvergreen.createTag(Registries.BIOME, dimensionTag);
        appendIDOnce(eternalStarlightBiomes, ImmutableList.of(
            "eternal_starlight:crystallized_desert",
            "eternal_starlight:dark_swamp",
            "eternal_starlight:ether_river",
            "eternal_starlight:lush_shallow_sea",
            "eternal_starlight:scarlet_forest",
            "eternal_starlight:shimmer_river",
            "eternal_starlight:spiral_kelp_forest",
            "eternal_starlight:starlight_dense_forest",
            "eternal_starlight:starlight_forest",
            "eternal_starlight:starlight_permafrost_forest",
            "eternal_starlight:starlit_sea",
            "eternal_starlight:the_abyss",
            "eternal_starlight:torreya_forest",
            "eternal_starlight:warm_shore"
        ));
    }

    private void setBiomeTag(PEBiome biome) {
        Optional.ofNullable(biome.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating biome tag... " + tag.location()); }
                appendIDOnce(tag, biome.defaultBiomes());
            }
        );
    }

    private void setDangerTag(PERegion region) {
        TagKey<Biome> safeTag = ProjectEvergreen.createTag(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "is_danger_level/" + PERegion.DangerLevel.SAFE.name().toLowerCase()));
        TagKey<Biome> neutralTag = ProjectEvergreen.createTag(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "is_danger_level/" + PERegion.DangerLevel.NEUTRAL.name().toLowerCase()));
        TagKey<Biome> dangerousTag = ProjectEvergreen.createTag(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, "is_danger_level/" + PERegion.DangerLevel.DANGEROUS.name().toLowerCase()));
        Map<PERegion.DangerLevel, TagKey<Biome>> tagMap = Map.of(
            PERegion.DangerLevel.SAFE, safeTag,
            PERegion.DangerLevel.NEUTRAL, neutralTag,
            PERegion.DangerLevel.DANGEROUS, dangerousTag
        );
        Optional.ofNullable(region.dangerLevel()).ifPresent(
            (danger) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating regional danger tag... " + region.name()); }
                appendTagOnce(tagMap.get(danger), region.defaultBiomes());
            }
        );
    }

    private void setRegionTag(PERegion region) {
        Optional.ofNullable(region.biomeTag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating region tag... " + tag.location()); }
                appendTagOnce(tag, region.defaultBiomes());
            }
        );
    }

    private void setFlagTag(PEBiome.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating biome flag tag... " + tag.location()); }
                appendIDOnce(tag, flag.defaultIDs());
            }
        );
    }

    private void appendIDOnce(TagKey<Biome> tag, List<String> ids) {
        TreeSet<String> structures = new TreeSet<>(ids);
        structures.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptional(s);
                });
    }

    private void appendTagOnce(TagKey<Biome> tag, List<String> ids) {
        TreeSet<String> structures = new TreeSet<>(ids);
        structures.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptionalTag(s);
                });
    }
}
