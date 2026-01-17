package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRegions;
import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;

public class PEStructureSet extends IWorldgenData {

    protected final String REGISTRY_PATH = "worldgen/structure_set/";

    private int separation;
    private int spacing;
    private int salt;
    private ArrayList<PEStructure> structures  = new ArrayList<PEStructure>();;

    public PEStructureSet(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
        salt = getSalt();
        this.structures = getStructures();
    }

    public PEStructureSet(String ID, int spacing, int separation) {
        this(ID);
        this.spacing = spacing;
        this.separation = separation;
    }

    /**
     * Retrieves the list of Structure objects associated with this structure set, or instantiates them if null.
     * @return A list of Structure objects.
     */
    public ArrayList<PEStructure> getStructures() {
        if (this.structures == null) {
            ArrayListMultimap<String, PEStructure> structuresByRarity = DefaultStructureRarity.mapStructuresByRarity(WorldgenDataManager.getStructureData());
            this.structures.addAll(structuresByRarity.get(this.id));
            updateStructureData();
        }
        return this.structures;
    }

    /**
     * Retrieves structures from another structure set to append them.
     * @param id - A structure set id.
     */
    public void appendStructures(String id) {
        ArrayListMultimap<String, PEStructure> structuresByRarity = DefaultStructureRarity.mapStructuresByRarity(WorldgenDataManager.getStructureData());
        this.structures.addAll(structuresByRarity.get(id));
        updateData();
    }

    public int getSpacing() {
        return this.spacing;
    }

    public int getSeparation() {
        return this.separation;
    }

    public int getSalt() {
        if (salt == 0) {
            this.salt = Math.abs(this.id.hashCode()) % 2_147_483_647;
        }
        return this.salt;
    }

    @Override
    public void updateData() {
        updateStructureData();
        WorldgenDataManager.setStructureSetData(this.id, this);
    }

    public void updateStructureData() {
        this.structures.forEach(structure -> {
            if (structure != null) {
                structure.setStructureSet(this);
            }
        });
    }
}
