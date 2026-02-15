package com.elephantaugments.projectevergreen.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.slf4j.Logger;

import java.text.Collator;

public class ProjectEvergreen {
    public static final String MODID = "project_evergreen";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Collator COLLATOR = Collator.getInstance();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void init() {}

    public static <T> TagKey<T> createTag(ResourceKey<Registry<T>> registry, ResourceLocation location) {
        return TagKey.create(registry, location);
    }
}
