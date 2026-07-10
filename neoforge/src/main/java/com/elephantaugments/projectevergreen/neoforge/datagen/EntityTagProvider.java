package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEBiome;
import com.elephantaugments.projectevergreen.common.api.PEDimension;
import com.elephantaugments.projectevergreen.common.api.PEMob;
import com.elephantaugments.projectevergreen.common.api.PERegion;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

public class EntityTagProvider extends EntityTypeTagsProvider {

    public EntityTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ProjectEvergreen.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //IS_CATEGORY
        for (PEMob category : PEMob.values()) {
            setCategoryTag(category);
        }
        //IS_CLIMATE
        for (PEBiome biome : PEBiome.values()) {
            setBiomeTag(biome);
        }
        //IS_REDISTRIBUTED
        for (PERegion region : PERegion.values()) {
            setRedistributedTag(region);
        }
        //IS_FLAGGED
        for (PEMob.Flag flag : PEMob.Flag.values()) {
            setFlagTag(flag);
        }
    }

    private void setCategoryTag(PEMob category) {
        Optional.ofNullable(category.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating mob category tag... " + tag.location());
                }
                appendIDOnce(tag, category.defaultMobs());
            }
        );
    }

    private void setBiomeTag(PEBiome biome) {
        Optional.ofNullable(biome.entityTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating mob biome tag... " + tag.location());
                }
                appendIDOnce(tag, biome.entityAdditions());
            }
        );
    }

    private void setRedistributedTag(PERegion region) {
        Optional.ofNullable(region.mobRemovalTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating mob redistribution tag... " + tag.location());
                }
                appendIDOnce(tag, region.mobRemovals());
            }
        );
    }

    private void setFlagTag(PEMob.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating mob tag... " + tag.location()); }
                appendIDOnce(tag, flag.defaultIDs());
            }
        );
    }

    private void appendIDOnce(TagKey<EntityType<?>> tag, List<String> ids) {
        TreeSet<String> mobs = new TreeSet<>(ids);
        mobs.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptional(s);
                });
    }
}
