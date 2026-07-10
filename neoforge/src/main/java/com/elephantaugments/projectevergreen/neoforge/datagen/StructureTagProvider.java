package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.*;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRegions;
import com.elephantaugments.projectevergreen.common.integration.SupportedMods;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.*;
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
        //IS_HEIGHTMAP
        for (PEStructure.Heightmap hm : PEStructure.Heightmap.values()) {
            setHeightmapTag(hm);
        }
        //setSupplementariesRoadSignTag();
        //setDungeonDifficultyTags();
        setThiefProtectedTag();
    }

    private void setDungeonDifficultyTags() {
        TagKey<Structure> dungeonLevelOne = ProjectEvergreen.createTag(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SupportedMods.DUNGEON_DIFFICULTY.name().toLowerCase(), "level_1"));
        List<String> dungeonLevelOneStructures = PEStructure.Difficulty.allDungeonLevelOneStructures();
        appendIDOnce(dungeonLevelOne, dungeonLevelOneStructures);

        TagKey<Structure> dungeonLevelTwo = ProjectEvergreen.createTag(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SupportedMods.DUNGEON_DIFFICULTY.name().toLowerCase(), "level_2"));
        List<String> dungeonLevelTwoStructures = PEStructure.Difficulty.allDungeonLevelTwoStructures();
        appendIDOnce(dungeonLevelTwo, dungeonLevelTwoStructures);

        TagKey<Structure> dungeonLevelThree = ProjectEvergreen.createTag(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SupportedMods.DUNGEON_DIFFICULTY.name().toLowerCase(), "level_3"));
        List<String> dungeonLevelThreeStructures = PEStructure.Difficulty.allDungeonLevelThreeStructures();
        appendIDOnce(dungeonLevelThree, dungeonLevelThreeStructures);

        TagKey<Structure> dungeonLevelFour = ProjectEvergreen.createTag(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SupportedMods.DUNGEON_DIFFICULTY.name().toLowerCase(), "level_4"));
        List<String> dungeonLevelFourStructures = PEStructure.Difficulty.allDungeonLevelFourStructures();
        appendIDOnce(dungeonLevelFour, dungeonLevelFourStructures);

        TagKey<Structure> dungeonLevelFive = ProjectEvergreen.createTag(Registries.STRUCTURE,
                ResourceLocation.fromNamespaceAndPath(SupportedMods.DUNGEON_DIFFICULTY.name().toLowerCase(), "level_5"));
        List<String> dungeonLevelFiveStructures = PEStructure.Difficulty.allDungeonLevelFiveStructures();
        appendIDOnce(dungeonLevelFive, dungeonLevelFiveStructures);
    }
    private void setThiefProtectedTag() {
        ResourceLocation protectedTag = ResourceLocation.fromNamespaceAndPath(SupportedMods.THIEF.name().toLowerCase(), "protected");
        TagKey<Structure> villages = ProjectEvergreen.createTag(Registries.STRUCTURE, protectedTag);
        appendIDOnce(villages, DefaultStructureRarity.civilizationRare);
    }

    private void setSupplementariesRoadSignTag() {
        ResourceLocation roadSignTag = ResourceLocation.fromNamespaceAndPath(SupportedMods.SUPPLEMENTARIES.name().toLowerCase(), "road_sign_destinations");
        TagKey<Structure> villages = ProjectEvergreen.createTag(Registries.STRUCTURE, roadSignTag);
        appendIDOnce(villages, DefaultStructureRarity.civilizationRare);
    }

    private void setDimensionTag(PEDimension dimension) {
        Optional.ofNullable(dimension.structureTag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure dimension tag... " + tag.location());
                }
                appendIDOnce(tag, dimension.defaultStructures());
            }
        );
    }

    private void setRegionTag(PERegion region) {
        Optional.ofNullable(region.structureTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure region tag... " + tag.location());
                }
                appendIDOnce(tag, region.defaultStructures());
            }
        );
    }

    private void setRarityTag(PEStructureSet sset) {
        Optional.ofNullable(sset.structureTag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure rarity tag... " + tag.location());
                }
                appendIDOnce(tag, sset.defaultStructures());
            }
        );
    }

    private void setFlagTag(PEStructure.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure flag tag... " + tag.location());
                }
                appendIDOnce(tag, flag.defaultIDs());
            }
        );
    }

    private void setHeightmapTag(PEStructure.Heightmap heightmap) {
        Optional.ofNullable(heightmap.tag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure heightmap tag... " + tag.location());
                }
                appendIDOnce(tag, heightmap.defaultIDs());
            }
        );
    }

    private void setDifficultyTag(PEStructure.Difficulty diff) {
        Optional.ofNullable(diff.tag()).ifPresent(
            (tag) -> {
                if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
                    ProjectEvergreen.LOGGER.info("Populating structure difficulty tag... " + tag.location());
                }
                appendIDOnce(tag, WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                        .filter(s -> s.getDifficulty().isPresent() && s.getDifficulty().get().equals(diff.number()))
                        .map(PatchableStructure::getId)
                        .toList());
            }
        );
    }

    private void appendIDOnce(TagKey<Structure> tag, List<String> ids) {
        TreeSet<String> structures = new TreeSet<>(ids);
        structures.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptional(s);
                });
    }
}
