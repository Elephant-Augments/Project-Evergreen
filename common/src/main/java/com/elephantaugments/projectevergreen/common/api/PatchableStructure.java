package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Size;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Heightmap;
import com.elephantaugments.projectevergreen.common.Constants;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatchableStructure extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/structure/";
    public final String JSON_DATA_KEY = Constants.PATCHABLE_STRUCTURE_KEY;

    private final List<PEStructure.Flag> flags = new ArrayList<PEStructure.Flag>();

    private String type;
    private String step;
    private Size size;
    private Heightmap heightmap;
    private PEDimension dimension;
    private PEStructureSet structureSet;
    private PERegion region;
    private int biomeRadiusOffset = 1;

    public PatchableStructure(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public boolean isCivilization() {
        return getRegion().isPresent() &&
                region.name().contains("CIVILIZATION");
    }

    public boolean isWilderness() {
        return getRegion().isPresent() &&
                region.name().contains("WILDERNESS");
    }

    public boolean isSpecial() {
        return getRegion().isPresent() &&
            region.name().contains("SPECIAL") &&
                !(isCivilization() || isWilderness());
    }

    public boolean isAdvancedType() {
        return getType().isPresent() &&
            PEStructure.SupportedTypes.contains(type) &&
            !flags.contains(PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS);
    }

    public boolean isFlat() {
        return getHeightmap().isPresent() &&
            heightmap == Heightmap.GROUNDLEVEL &&
            isAdvancedType();
    }

    public boolean isSmall() {
        return getSize().isPresent() && size == Size.SMALL;
    }

    public boolean isMedium() {
        return getSize().isPresent() && size == Size.MEDIUM;
    }

    public boolean isLarge() {
        return getSize().isPresent() && size == Size.LARGE;
    }

    public boolean isMassive() {
        return getSize().isPresent() &&
                (size == Size.LARGE || size == Size.SPRAWLING);
    }

    public boolean isSprawling() {
        return getSize().isPresent() && size == Size.SPRAWLING;
    }

    public boolean isUnderground() {
        return getHeightmap().isPresent() &&
            heightmap == Heightmap.UNDERGROUND;
    }

    public boolean isInland() {
        return getHeightmap().isPresent() &&
            heightmap == Heightmap.GROUNDLEVEL;
    }

    public boolean isWaterBound() {
        return getHeightmap().isPresent() &&
            (heightmap == Heightmap.OCEANSURFACE || heightmap == Heightmap.OCEANFLOOR);
    }

    public boolean isRadiusBound() {
        return !isUnderground() &&
            !(flags.contains(PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK) &&
            (getSize().isPresent() && size != Size.SMALL));
    }

    public boolean hasPopulationBias(int populationBias) {
        return switch (populationBias) {
            case 0 -> isCivilization();
            case 1 -> !isUnderground();
            case 2 -> isWilderness();
            default -> throw new IllegalArgumentException("No Config Value Provided.");
        };
    }

    @Override
    public void updateData() {
        calculateDifficulty();
        WorldgenDataManager.setStructureData(this.id, this);
    }

    public int getBiomeRadiusOffset() {
        return biomeRadiusOffset;
    }

    public void setBiomeRadiusOffset(int biomeRadiusOffset) {
        this.biomeRadiusOffset = biomeRadiusOffset;
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
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

    public Optional<Heightmap> getHeightmap() {
        return Optional.ofNullable(this.heightmap);
    }

    public void setHeightmap(Heightmap heightmap) {
        this.heightmap = heightmap;
    }

    public Optional<Size> getSize() {
        return Optional.ofNullable(this.size);
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
        updateData();
    }

    public Optional<PERegion> getRegion() {
        return Optional.ofNullable(this.region);
    }

    public void setRegion(PERegion region) {
        this.region = region;
        updateData();
    }

    public Optional<PEStructureSet> getStructureSet() {
        return Optional.ofNullable(this.structureSet);
    }

    public void setStructureSet(PEStructureSet structureSet) {
        this.structureSet = structureSet;
        updateData();
    }

    public List<PEStructure.Flag> getFlags() {
        return this.flags;
    }

    public void appendFlag(PEStructure.Flag flag) {
        this.flags.add(flag);
    }

    public void calculateDifficulty() {
        int diffLevel = Constants.OVERWORLD_DIFFICULTY;
        Optional<PEDimension> dim = getDimension();
        Optional<PERegion> reg = getRegion();
        if (dim.isPresent()) {
            diffLevel = dimension.difficulty();
        }
        if (reg.isPresent()) {
            diffLevel = region.difficulty();
        }
        if (getSize().isPresent()) {
            if (size == Size.SPRAWLING &&
                    (reg.isPresent() && region.name().contains("CIVILIZATION"))) {
                return;
            } else {
                diffLevel = diffLevel + size.diffOffset();
            }
        }
        difficulty = diffLevel;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());

        getSize().ifPresentOrElse((size) -> {
            json.addProperty(size.jsonKey(), size.name().toLowerCase());
        }, () -> {});
        getDimension().ifPresentOrElse((dim) -> {
            json.addProperty(dim.jsonKey(), dim.biomeTagKey());
        }, () -> {});
        getRegion().ifPresentOrElse((region) -> {
            json.addProperty(region.jsonKey(), region.tagKey());
        }, () -> {});
        getStructureSet().ifPresentOrElse((sset) -> {
            json.addProperty(sset.jsonKey(), sset.location().toString());
        }, () -> {});

        json.addProperty(Constants.JsonProp.IS_LOADED.jsonKey(), this.is_loaded);
        json.addProperty(Constants.JsonProp.IS_ADVANCED_TYPE.jsonKey(), this.isAdvancedType());
        json.addProperty(Constants.JsonProp.IS_FLAT.jsonKey(), this.isFlat());
        json.addProperty(Constants.JsonProp.IS_INLAND.jsonKey(), this.isInland());
        json.addProperty(Constants.JsonProp.IS_WATER_BOUND.jsonKey(), this.isWaterBound());
        json.addProperty(Constants.JsonProp.IS_RADIUS_BOUND.jsonKey(), this.isRadiusBound());
        json.addProperty(Constants.JsonProp.IS_SMALL.jsonKey(), this.isSmall());
        json.addProperty(Constants.JsonProp.IS_MEDIUM.jsonKey(), this.isMedium());
        json.addProperty(Constants.JsonProp.IS_LARGE.jsonKey(), this.isLarge());
        json.addProperty(Constants.JsonProp.IS_MASSIVE.jsonKey(), this.isMassive());
        json.addProperty(Constants.JsonProp.IS_SPRAWLING.jsonKey(), this.isSprawling());
        this.flags.forEach(flag -> {
            json.addProperty(flag.jsonKey(), true);
        });
        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
