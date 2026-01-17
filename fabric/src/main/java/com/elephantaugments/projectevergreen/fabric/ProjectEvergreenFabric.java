package com.elephantaugments.projectevergreen.fabric;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import net.fabricmc.api.ModInitializer;

public class ProjectEvergreenFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        ProjectEvergreen.LOGGER.info("Hello Fabric world!");
        ProjectEvergreen.init();
    }
}
