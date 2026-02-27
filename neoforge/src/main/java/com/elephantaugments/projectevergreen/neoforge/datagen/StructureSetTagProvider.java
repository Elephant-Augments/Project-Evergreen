package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEStructureSet;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.text.Collator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;

public class StructureSetTagProvider extends TagsProvider<StructureSet> {

    Collator collator = Collator.getInstance();

    public StructureSetTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.STRUCTURE_SET, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        //IS_FLAGGED
        for (PEStructureSet.Flag flag : PEStructureSet.Flag.values()) {
            setFlagTag(flag);
        }
    }

    private void setFlagTag(PEStructureSet.Flag flag) {
        Optional.ofNullable(flag.tag()).ifPresent(
            (tag) -> {
                if(PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) { ProjectEvergreen.LOGGER.info("Populating structure set tag... " + tag.location()); }
                appendIDOnce(tag, flag.defaultIDs());
            }
        );
    }

    private void appendIDOnce(TagKey<StructureSet> tag, List<String> ids) {
        TreeSet<String> structures = new TreeSet<>(ids);
        structures.stream().map(ResourceLocation::parse)
                .forEach(s -> {
                    tag(tag).addOptional(s);
                });
    }
}
