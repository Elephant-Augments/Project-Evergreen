package com.elephantaugments.projectevergreen.neoforge.datagen;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PERegion;
import com.elephantaugments.projectevergreen.common.api.WorldgenDataManager;
import net.enderturret.patchedmod.data.PatchProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ProjectEvergreen.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        ProjectEvergreen.LOGGER.info("Preparing for data generation...");
        WorldgenDataManager.loadDefaultWorldgenData();

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        PatchProvider structurePatcher = new WorldgenPatchProvider(generator, PackOutput.Target.DATA_PACK);
        generator.addProvider(event.includeServer(), structurePatcher);

        BiomeTagProvider biomeTagsProvider = new BiomeTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), biomeTagsProvider);

        StructureSetTagProvider ssetTagsProvider = new StructureSetTagProvider(packOutput, lookupProvider);
        generator.addProvider(event.includeServer(), ssetTagsProvider);

        StructureTagProvider structureTagsProvider = new StructureTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), structureTagsProvider);

        EntityTagProvider mobTagsProvider = new EntityTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), mobTagsProvider);

        event.createDatapackRegistryObjects(
            // Our registry set builder to generate the data from.
            new RegistrySetBuilder()
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, bootstrap -> {
                    for (PERegion region : PERegion.values()) {
                        DatapackRegistryHelper.generateSpawnRemovalModifier(bootstrap,
                                region.modifierLocation(),
                                region.biomeTag(),
                                region.mobTag()
                        );
                    }
                })
        );
    }
}
