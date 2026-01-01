package com.elephantaugments.projectevergreen.datagen;

import com.elephantaugments.projectevergreen.ProjectEvergreen;
import net.enderturret.patchedmod.data.PatchProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ProjectEvergreen.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PatchProvider structurePatcher = new StructurePatchProvider(generator, PackOutput.Target.DATA_PACK);
        
        generator.addProvider(event.includeServer(), structurePatcher);
    }
    
}
