package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class StructureTagProvider extends StructureTagsProvider {

    Collator collator = Collator.getInstance();
    public static Map<Integer, TagKey<Structure>> diffMap = Map.of(
        1, PEStructure.Difficulty.DIFFICULTY_LEVEL_1.tag(),
        2, PEStructure.Difficulty.DIFFICULTY_LEVEL_2.tag(),
        3, PEStructure.Difficulty.DIFFICULTY_LEVEL_3.tag(),
        4, PEStructure.Difficulty.DIFFICULTY_LEVEL_4.tag(),
        5, PEStructure.Difficulty.DIFFICULTY_LEVEL_5.tag(),
        6, PEStructure.Difficulty.DIFFICULTY_LEVEL_6.tag(),
        7, PEStructure.Difficulty.DIFFICULTY_LEVEL_7.tag(),
        8, PEStructure.Difficulty.DIFFICULTY_LEVEL_8.tag(),
        9, PEStructure.Difficulty.DIFFICULTY_LEVEL_9.tag(),
        10, PEStructure.Difficulty.DIFFICULTY_LEVEL_10.tag()
    );

    public StructureTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ProjectEvergreen.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //IS_DIMENSION
        for (PEDimension dim : PEDimension.values()) {
            setDimensionTag(dim);
        }
        //IS_REGION
        for (PERegion region : PERegion.values()) {
            setRegionTag(region);
        }
        //IS_RARITY
        for (PEStructureSet sset : PEStructureSet.values()) {
            setRarityTag(sset);
        }
        //IS_FLAGGED
        for (PEStructure.Flag flag : PEStructure.Flag.values()) {
            setFlagTag(flag);
        }
        //IS_DIFFICULTY
        for (PEStructure.Difficulty diff : PEStructure.Difficulty.values()) {
            setDifficultyTag(diff);
        }
    }

    private void setDimensionTag(PEDimension dimension) {
        Optional.ofNullable(dimension.structureTag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure dimension tag... " + tag.location());
                }
                List<ResourceLocation> regional_structures = dimension.defaultStructures().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                regional_structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setRegionTag(PERegion region) {
        Optional.ofNullable(region.structureTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure region tag... " + tag.location());
                }
                List<ResourceLocation> structures = region.defaultStructures().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setRarityTag(PEStructureSet sset) {
        Optional.ofNullable(sset.structureTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure rarity tag... " + tag.location());
                }
                List<ResourceLocation> structures = sset.defaultStructures().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setFlagTag(PEStructure.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure flag tag... " + tag.location());
                }
                List<ResourceLocation> structures = flag.defaultIDs().stream()
                        .sorted((a, b) -> collator.compare(a.split(":")[0], b.split(":")[0]))
                        .map(ResourceLocation::parse)
                        .toList();
                structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }

    private void setDifficultyTag(PEStructure.Difficulty diff) {
        Optional.ofNullable(diff.tag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure difficulty tag... " + tag.location());
                }
                List<ResourceLocation> structures = WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                        .filter(s -> s.getDifficulty().isPresent() && s.getDifficulty().get().equals(diff.number()))
                        .sorted((a, b) -> collator.compare(a.id.split(":")[0], b.id.split(":")[0]))
                        .map(s -> ResourceLocation.parse(s.id))
                        .toList();
                structures.forEach(s -> {
                    tag(tag).addOptional(s);
                });
            }
        );
    }
}
