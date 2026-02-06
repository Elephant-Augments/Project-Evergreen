package com.elephantaugments.projectevergreen.common.api;

import com.google.gson.JsonObject;

public class PatchableEntity extends IPatchable {

    public PatchableEntity(String ID) {
        super(ID);
    }

    @Override
    public void updateData() {

    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
