package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.google.gson.JsonObject;

public class PatchableEntity extends IPatchable {

    public final String JSON_DATA_KEY = Constants.PATCHABLE_ENTITY_KEY;

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
