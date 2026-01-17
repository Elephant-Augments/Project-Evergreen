package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.data.defaults.DefaultBiomeTags;
import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PEBiome extends IWorldgenData {

    protected final String REGISTRY_PATH = "worldgen/biome/";

    private List<String> region_tags;
    private List<String> biome_tags;
    private ArrayList<PEStructure> structures;
    private ArrayList<PEFeature> features;
    private ArrayList<PEMob> mobs;

    public PEBiome(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public PEBiome(String ID, List<String> tags) {
        this(ID);
        setBiomeTags(tags);
    }

    public void setBiomeTags(List<String> tags) {
        this.biome_tags = tags;
        updateData();
    }

    public void setStructures(ArrayList<PEStructure> structs) {
        this.structures = structs;
        updateData();
    }

    public List<String> getBiomeTags() {
        return this.biome_tags;
    }

    @Override
    public void updateData() {
        WorldgenDataManager.setBiomeData(this.id, this);
    }
}
