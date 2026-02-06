package com.elephantaugments.projectevergreen.common.api;

import com.google.gson.JsonObject;

public class PatchableFeature extends IPatchable {

    protected final String REGISTRY_PATH = "neoforge/biome_modifier";

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
