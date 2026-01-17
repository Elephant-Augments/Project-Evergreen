package com.elephantaugments.projectevergreen.common.data;

public class PEFeature extends IWorldgenData {

    protected final String REGISTRY_PATH = "neoforge/biome_modifier";

    public PEFeature(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    @Override
    public void updateData() {

    }
}
