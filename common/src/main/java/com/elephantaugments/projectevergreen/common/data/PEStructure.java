package com.elephantaugments.projectevergreen.common.data;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureDimensions.Dimension;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity.Size;

import java.util.regex.Pattern;

public class PEStructure extends IWorldgenData {

    protected final String REGISTRY_PATH = "worldgen/structure/";

    private String type;
    private String step;
    private Size size;
    private Dimension dimension;
    private PEStructureSet structureSet;
    private PERegion region;

    public PEStructure(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    /**
     * @return PEStructureSet - Can be null.
     */
    public PERegion getRegion() {
        return this.region;
    }

    public void setRegion(PERegion region) {
        this.region = region;
        updateData();
    }

    public void setStructureSet(PEStructureSet structureSet) {
        this.structureSet = structureSet;
        updateData();
    }

    /**
     * @return PEStructureSet - Can be null.
     */
    public PEStructureSet getStructureSet() {
        return this.structureSet;
    }

    @Override
    public void updateData() {
        calculateDifficulty();
        WorldgenDataManager.setStructureData(this.id, this);
    }

    public void calculateDifficulty() {
        int diffLevel;
        //The region will always be set for Overworld structures
        if (region != null) {
            diffLevel = region.difficulty;
        } else {
            diffLevel = 4;
            if (this.dimension != null) {
                switch (this.dimension) {
                    case NETHER:
                        diffLevel = diffLevel + 1;
                        break;
                    case AFTERDARK:
                        diffLevel = diffLevel + 2;
                        break;
                    case END:
                        diffLevel = diffLevel + 3;
                        break;
                    case LOSTCITIES:
                        diffLevel = diffLevel + 4;
                        break;
                    default:
                        break;
                }
            }
        }
        if (size != null) {
            switch(size) {
                case DECO:
                    diffLevel = diffLevel - 1;
                    break;
                case MEDIUM:
                    break;
                case MASSIVE:
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

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        updateData();
    }
}
