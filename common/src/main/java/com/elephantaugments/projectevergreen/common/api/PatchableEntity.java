package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatchableEntity extends IPatchable {

    public final String JSON_DATA_KEY = Constants.PATCHABLE_ENTITY_KEY;

    private final List<PEMob.Flag> flags = new ArrayList<PEMob.Flag>();
    private String mobCategory;
    private List<PEBiome> biomes;

    public PatchableEntity(String ID) {
        super(ID);
    }

    public List<PEMob.Flag> getFlags() {
        return this.flags;
    }

    public void appendFlag(PEMob.Flag flag) {
        this.flags.add(flag);
        updateData();
    }

    public Optional<String> category() {
        return Optional.ofNullable(this.mobCategory);
    }

    public void setMobCategory(String category) {
        this.mobCategory = category;
    }

    public Optional<List<PEBiome>> getBiomes() {
        return Optional.ofNullable(this.biomes);
    }

    public void appendBiome(PEBiome biome) {
        this.biomes.add(biome);
        updateData();
    }

    @Override
    public void updateData() {
        WorldgenDataManager.setEntityData(this.id, this);
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());
        json.addProperty(Constants.JsonProp.IS_LOADED.jsonKey(), this.is_loaded);
        this.flags.forEach(flag -> {
            json.addProperty(flag.jsonKey(), true);
        });

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
