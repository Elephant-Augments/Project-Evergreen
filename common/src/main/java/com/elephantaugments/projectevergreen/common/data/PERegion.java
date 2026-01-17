package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.data.defaults.DefaultBiomeTags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultRegions;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRegions;
import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PERegion extends IWorldgenData {

    String[] biome_tags = new String[]{};
    ArrayList<PEBiome> biomes;
    ArrayList<PEStructureSet> structureSets;
    ArrayList<PEStructure> structures;
    ArrayList<PEFeature> features;
    ArrayList<PEMob> mobs;

    public PERegion(String ID) {
        super(ID);
        this.difficulty = calculateDifficulty();
    }

    @Override
    public void updateData() {
        updateStructureData();
        updateBiomeData();
    }

    public PERegion(String ID, String[] biomeTags)
    {
        this(ID);
        this.biome_tags = biomeTags;
        this.biomes = getBiomes();
        this.structures = getStructures();
    }

    /**
     * Retrieves the list of Biome objects associated with this region, or instantiates them if null.
     * @return A list of Biome objects.
     */
    public ArrayList<PEBiome> getBiomes() {
        if (this.biomes == null) {
            this.biomes = new ArrayList<PEBiome>();
            ArrayListMultimap<String, PEBiome> biomeIDsByTag = DefaultBiomeTags.mapBiomesToTags(WorldgenDataManager.getBiomeData());
            Arrays.stream(this.biome_tags).toList().forEach(tag -> {
                this.biomes.addAll(biomeIDsByTag.get(tag));
            });
            updateBiomeData();
        }
        return this.biomes;
    }

    /**
     * Retrieves the list of Structure Set objects associated with this region, or instantiates them if null.
     * @param structureSets - An array of structure set IDs that belong to this region.
     * @return A list of Structure Set objects.
     */
    /*private ArrayList<PEStructureSet> getRegionStructuresSets(String[] structureSets) {
        if (this.structureSets == null) {
            this.structureSets = new ArrayList<PEStructureSet>();
            ArrayListMultimap<String, PEBiome> biomeIDsByTag = DefaultBiomeTags.mapBiomesToTags(WorldgenDataManager.getBiomeData());
            Arrays.stream(tags).toList().forEach(tag -> {
                this.structureSets.addAll(biomeIDsByTag.get(tag));
            });
        }
        return this.structureSets;
    }*/

    /**
     * Retrieves the list of Structure objects associated with this region, or instantiates them if null.
     * @return A list of Structure objects.
     */
    public ArrayList<PEStructure> getStructures() {
        if (this.structures == null) {
            this.structures = new ArrayList<PEStructure>();
            ArrayListMultimap<String, PEStructure> structuresByRegion = DefaultStructureRegions.mapStructuresToRegion(WorldgenDataManager.getStructureData());
            this.structures.addAll(structuresByRegion.get(this.id));
            updateStructureData();
        }
        return this.structures;
    }

    public void updateBiomeData() {
        this.biomes.forEach(biome -> {
            if (biome != null) {
                biome.difficulty = this.difficulty;
                biome.setStructures(this.structures);
            }
        });
    }

    public void updateStructureData() {
        this.structures.forEach(structure -> {
            if (structure != null) {
                //Each structure is guaranteed to only appear in one region
                structure.setRegion(this);
            }
        });
    }

    public Integer calculateDifficulty() {
        int diffLevel = 3;
        if(DefaultRegions.safeRegions.contains(this.id)) {
            diffLevel = diffLevel - 2;
        } else if (DefaultRegions.challengingRegions.contains(this.id)) {
            diffLevel = diffLevel + 2;
        }
        return diffLevel;
    }
}
