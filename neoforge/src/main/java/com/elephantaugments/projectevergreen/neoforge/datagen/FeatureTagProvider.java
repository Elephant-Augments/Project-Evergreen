package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEBiome;
import com.elephantaugments.projectevergreen.common.api.PEFeature;
import com.elephantaugments.projectevergreen.common.api.PERegion;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

public class FeatureTagProvider extends TagsProvider<PlacedFeature> {

    public FeatureTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.PLACED_FEATURE, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //IS_CATEGORY
        for (PEFeature category : PEFeature.values()) {
            setCategoryTag(category);
        }
        //IS_CLIMATE
        for (PEBiome biome : PEBiome.values()) {
            setBiomeTag(biome);
        }
        //IS_FLAGGED
        for (PEFeature.Flag flag : PEFeature.Flag.values()) {
            setFlagTag(flag);
        }
    }

    private void setCategoryTag(PEFeature category) {
        Optional.ofNullable(category.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating feature category tag... " + tag.location());
                }
                appendIDOnce(tag, category.defaultIDs());
            }
        );
    }

    private void setBiomeTag(PEBiome biome) {
        Optional.ofNullable(biome.featureTag()).ifPresent(
                (tag) -> {
                    if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                        ProjectEvergreen.LOGGER.info("Populating feature biome tag... " + tag.location());
                    }
                    appendIDOnce(tag, biome.featureAdditions());
                }
        );
    }

    private void setFlagTag(PEFeature.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating feature flag tag... " + tag.location()); }
                appendIDOnce(tag, flag.defaultIDs());
            }
        );
    }

    private void appendIDOnce(TagKey<PlacedFeature> tag, List<String> ids) {
        TreeSet<String> mobs = new TreeSet<>(ids);
        mobs.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptional(s);
                });
    }
}
