package com.elephantaugments.projectevergreen.common;

import com.elephantaugments.projectevergreen.common.util.PETags;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class ProjectEvergreen {
    public static final String MODID = "project_evergreen";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void init() {
        PETags.initTags();
    }
}
