package com.elephantaugments.projectevergreen.neoforge.platform;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.platform.PlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public final class NeoForgePlatformHelper implements PlatformHelper
{
    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public String getModVersion() {
        return ModList.get().getModContainerById(ProjectEvergreen.MODID).map(modContainer -> modContainer.getModInfo().getVersion().toString()).orElse(null);
    }
}