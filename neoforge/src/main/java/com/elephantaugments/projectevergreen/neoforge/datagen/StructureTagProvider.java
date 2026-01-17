package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.elephantaugments.projectevergreen.common.util.PETags;
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
import java.util.concurrent.CompletableFuture;

public class StructureTagProvider extends StructureTagsProvider {

    Collator collator = Collator.getInstance();
    public static Map<Integer, TagKey<Structure>> diffMap = Map.of(
        1, PETags.Structures.DIFFICULTY_LEVEL_1,
        2, PETags.Structures.DIFFICULTY_LEVEL_2,
        3, PETags.Structures.DIFFICULTY_LEVEL_3,
        4, PETags.Structures.DIFFICULTY_LEVEL_4,
        5, PETags.Structures.DIFFICULTY_LEVEL_5,
        6, PETags.Structures.DIFFICULTY_LEVEL_6,
        7, PETags.Structures.DIFFICULTY_LEVEL_7,
        8, PETags.Structures.DIFFICULTY_LEVEL_8,
        9, PETags.Structures.DIFFICULTY_LEVEL_9,
        10, PETags.Structures.DIFFICULTY_LEVEL_10
    );

    public StructureTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, ProjectEvergreen.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        setDifficultyTag(0);
        setDifficultyTag(1);
        setDifficultyTag(2);
        setDifficultyTag(3);
        setDifficultyTag(4);
        setDifficultyTag(5);
        setDifficultyTag(6);
        setDifficultyTag(7);
        setDifficultyTag(8);
        setDifficultyTag(9);
        setDifficultyTag(10);
    }

    private void setDifficultyTag(int diffLevel) {
        List<ResourceLocation> diffStructures = WorldgenDataManager.STRUCTURES_BY_ID.values().stream()
                .filter(s -> s.difficulty == diffLevel)
                .sorted((a, b) -> collator.compare(a.id.split(":")[0], b.id.split(":")[0]))
                .map(s -> ResourceLocation.parse(s.id))
                .toList();
        TagKey<Structure> tag = diffMap.get(diffLevel) != null ? diffMap.get(diffLevel) : PETags.Structures.DIFFICULTY_LEVEL_0;
        diffStructures.forEach(s -> {
            tag(tag).addOptional(s);
        });
    }
}
