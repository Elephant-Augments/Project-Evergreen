package com.elephantaugments.projectevergreen.common.platform;

import java.nio.file.Path;

public interface PlatformHelper
{
    public String getPlatformName();

    public boolean isDevelopmentEnvironment();

    boolean isModLoaded(String modId);

    String getModVersion();
}
