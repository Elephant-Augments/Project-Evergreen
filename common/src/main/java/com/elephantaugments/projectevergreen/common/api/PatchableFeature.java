package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Objects;

public class PatchableFeature extends IPatchable {

    public final String JSON_DATA_KEY = Constants.PATCHABLE_FEATURE_KEY;
    protected String REGISTRY_PATH;

    private boolean is_modifier = false;

    public PatchableFeature(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public boolean isModifier() {
        return is_modifier;
    }

    public void setRegistry(String registryPath) {
        this.REGISTRY_PATH = registryPath;
        this.is_modifier = Objects.equals(registryPath, "biome_modifier");
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

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
