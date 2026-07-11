package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFeatureCategories;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PatchableFeature extends IPatchable {

    public final String JSON_DATA_KEY = Constants.PATCHABLE_FEATURE_KEY;
    protected String REGISTRY_PATH;

    private final List<PEFeature.Flag> flags = new ArrayList<PEFeature.Flag>();
    private boolean is_modifier = false;
    private PEBiome biomes;

    public PatchableFeature(String ID) {
        super(ID);
        REGISTRY_PATH = DefaultFeatureCategories.placedFeatures.contains(ID) ?
                "worldgen/placed_feature/" :
                "neoforge/biome_modifier/";
        this.is_modifier = Objects.equals(REGISTRY_PATH, "neoforge/biome_modifier/");
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public boolean isModifier() {
        return is_modifier;
    }

    public void setRegistry(String registryPath) {
        this.REGISTRY_PATH = registryPath;
        this.is_modifier = Objects.equals(registryPath, "biome_modifier");
        this.updateData();
    }

    public void appendFlag(PEFeature.Flag flag) {
        this.flags.add(flag);
        updateData();
    }

    public Optional<PEBiome> getBiomes() {
        return Optional.ofNullable(this.biomes);
    }

    public void setBiomes(PEBiome biomes) {
        this.biomes = biomes;
        updateData();
    }

    @Override
    public void updateData() {
        WorldgenDataManager.setFeatureData(this.id, this);
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());
        json.addProperty(Constants.JsonProp.IS_LOADED.jsonKey(), this.is_loaded);
        json.addProperty(Constants.JsonProp.IS_MODIFIER.jsonKey(), this.is_modifier);
        getBiomes().ifPresent((biome) -> {
            json.addProperty(biome.jsonKey(), biome.tagKey());
        });
        this.flags.forEach(flag -> {
            json.addProperty(flag.jsonKey(), true);
        });

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
