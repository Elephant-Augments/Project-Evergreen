package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.api.PEStructure.Size;
import com.elephantaugments.projectevergreen.common.Constants;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatchableStructure extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/structure/";

    private final List<PEStructure.Flag> flags = new ArrayList<PEStructure.Flag>();

    private String type;
    private String step;
    private Size size;
    private PEDimension dimension;
    private PEStructureSet structureSet;
    private PERegion region;

    public PatchableStructure(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    @Override
    public void updateData() {
        calculateDifficulty();
        WorldgenDataManager.setStructureData(this.id, this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        updateData();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
        updateData();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
        updateData();
    }

    public Optional<PEDimension> getDimension() {
        return Optional.ofNullable(this.dimension);
    }

    public void setDimension(PEDimension dimension) {
        this.dimension = dimension;
        //updateData();
    }

    public Optional<PERegion> getRegion() {
        return Optional.ofNullable(this.region);
    }

    public void setRegion(PERegion region) {
        this.region = region;
        //updateData();
    }

    public Optional<PEStructureSet> getStructureSet() {
        return Optional.ofNullable(this.structureSet);
    }

    public void setStructureSet(PEStructureSet structureSet) {
        this.structureSet = structureSet;
        //updateData();
    }

    public List<PEStructure.Flag> getFlags() {
        return this.flags;
    }

    public void appendFlag(PEStructure.Flag flag) {
        this.flags.add(flag);
        updateData();
    }

    public void calculateDifficulty() {
        int diffLevel;
        //The region will always be set for Overworld structures
        if (region != null) {
            diffLevel = region.difficulty();
        } else {
            diffLevel = Constants.OTHERWORLD_DIFFICULTY;
            if (this.dimension != null) {
                diffLevel = dimension.difficulty();
            }
        }
        if (size != null) {
            switch(size) {
                case SMALL:
                    diffLevel = diffLevel - 1;
                    break;
                case MEDIUM:
                    break;
                case LARGE:
                    diffLevel = diffLevel + 1;
                    break;
                case SPRAWLING:
                    //TODO: Create a proper regex for village match
                    if (!(this.id.contains("_village\b") || this.id.contains("village_"))) {
                        diffLevel = diffLevel + 2;
                    }
                    break;
                default:
                    break;
            }
        }
        difficulty = diffLevel;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
