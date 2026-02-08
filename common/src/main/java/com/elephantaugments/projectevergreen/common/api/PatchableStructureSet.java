package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRarity;
import com.google.common.collect.ArrayListMultimap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatchableStructureSet extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/structure_set/";
    public final String JSON_DATA_KEY = Constants.PATCHABLE_STRUCTURE_SET_KEY;

    private final List<PEStructureSet.Flag> flags = new ArrayList<PEStructureSet.Flag>();

    private int separation;
    private int spacing;
    private int salt;
    private ArrayList<PatchableStructure> structures  = new ArrayList<PatchableStructure>();
    private PEStructureSet data;
    private boolean is_loaded = false;

    public PatchableStructureSet(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
        salt = getSalt();
        //this.structures = getStructures();
    }

    public PatchableStructureSet(String ID, int spacing, int separation) {
        this(ID);
        this.spacing = spacing;
        this.separation = separation;
    }

    /*public boolean hasPopulationBias(int populationBias) {
        return switch (populationBias) {
            case 0 -> ProjectEvergreenConfig.structuresByRarity.keys().stream().filter(e -> (e.contains("massive") & e.contains("civilization"))).anyMatch(e -> e.equals(id));
            case 1 -> false;
            case 2 -> ProjectEvergreenConfig.structuresByRarity.keys().stream().filter(e -> (e.contains("massive") & e.contains("wilderness"))).anyMatch(e -> e.equals(id));
            default -> throw new IllegalArgumentException("No Config Value Provided.");
        };
    }*/

    @Override
    public void updateData() {
        updateStructureData();
        WorldgenDataManager.setStructureSetData(this.id, this);
    }

    /*public ArrayList<PatchableStructure> getStructures() {
        if (this.structures == null) {
            ArrayListMultimap<String, PatchableStructure> structuresByRarity =
                    DefaultStructureRarity.mapStructuresByRarity(WorldgenDataManager.getPatchableStructures());
            this.structures.addAll(structuresByRarity.get(this.id));
            updateStructureData();
        }
        return this.structures;
    }*/

    /*public void appendStructures(String id) {
        ArrayListMultimap<String, PatchableStructure> structuresByRarity =
                DefaultStructureRarity.mapStructuresByRarity(WorldgenDataManager.getPatchableStructures());
        this.structures.addAll(structuresByRarity.get(id));
        updateData();
    }*/

    public void setData(PEStructureSet set) {
        this.data = set;
    }

    public Optional<PEStructureSet> getData() {
        return Optional.ofNullable(this.data);
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

    public void updateStructureData() {
        this.structures.forEach(structure -> {
            if (structure != null) {
                structure.setStructureSet(this.data);
            }
        });
    }

    public List<PEStructureSet.Flag> getFlags() {
        return this.flags;
    }

    public void appendFlag(PEStructureSet.Flag flag) {
        this.flags.add(flag);
        updateData();
    }

    public boolean isLoaded() {
        return this.is_loaded;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
