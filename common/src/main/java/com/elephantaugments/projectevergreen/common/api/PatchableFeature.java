package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.google.gson.JsonObject;

public class PatchableFeature extends IPatchable {

    protected final String REGISTRY_PATH = "neoforge/biome_modifier";
    public final String JSON_DATA_KEY = Constants.PATCHABLE_FEATURE_KEY;

    public PatchableFeature(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    @Override
    public void updateData() {

    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
