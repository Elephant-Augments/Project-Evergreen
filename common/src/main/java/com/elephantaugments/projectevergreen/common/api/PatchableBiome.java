package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PatchableBiome extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/biome/";
    public final String JSON_DATA_KEY = Constants.PATCHABLE_BIOME_KEY;

    private final List<PEBiome.Flag> flags = new ArrayList<PEBiome.Flag>();

    private List<String> region_tags;
    private List<String> biome_tags;
    private ArrayList<PatchableStructure> structures;
    private ArrayList<PatchableFeature> features;
    private ArrayList<PatchableEntity> mobs;

    public PatchableBiome(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public PatchableBiome(String ID, List<String> tags) {
        this(ID);
        setBiomeTags(tags);
    }

    public void setBiomeTags(List<String> tags) {
        this.biome_tags = tags;
        updateData();
    }

    public void setStructures(ArrayList<PatchableStructure> structs) {
        this.structures = structs;
        updateData();
    }

    public List<PEBiome.Flag> getFlags() {
        return this.flags;
    }

    public void appendFlag(PEBiome.Flag flag) {
        this.flags.add(flag);
    }

    public List<String> getBiomeTags() {
        return this.biome_tags;
    }

    @Override
    public void updateData() {
        WorldgenDataManager.setBiomeData(this.id, this);
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
