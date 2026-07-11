package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import oshi.jna.platform.windows.NtDll;

import java.util.*;

public class PatchableBiome extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/biome/";
    protected final String DTYPE = this.getClass().toString();
    public final String JSON_DATA_KEY = Constants.PATCHABLE_BIOME_KEY;

    private final List<PEBiome.Flag> flags = new ArrayList<PEBiome.Flag>();

    private List<String> region_tags;
    private List<String> biome_tags;
    private List<String> structures = new ArrayList<>();
    private List<String> features = new ArrayList<>();
    private HashSet<PatchableEntity> mobs = new HashSet<>();
    JsonObject spawners = new JsonObject();

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

    public void setStructures(List<String> structs) {
        this.structures = structs;
        updateData();
    }

    public void setFeatures(List<String> feature) {
        this.features = feature;
        updateData();
    }

    public void setMobs(HashSet<PatchableEntity> mobs) {
        this.mobs = mobs;
        updateData();
    }
    public void setSpawners(JsonObject spawners) {
        this.spawners = spawners;
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

    public void initJsonData(JsonElement spawn_overrides) {
        JsonObject spawners = new JsonObject();
        spawners.add("monster", spawn_overrides.getAsJsonObject().getAsJsonArray("monster"));
        spawners.add("creature", spawn_overrides.getAsJsonObject().getAsJsonArray("creature"));
        spawners.add("water_creature", spawn_overrides.getAsJsonObject().getAsJsonArray("water_creature"));
        spawners.add("ambient", spawn_overrides.getAsJsonObject().getAsJsonArray("ambient"));
        spawners.add("water_ambient", spawn_overrides.getAsJsonObject().getAsJsonArray("water_ambient"));
        spawners.add("underground_water_creature", spawn_overrides.getAsJsonObject().getAsJsonArray("underground_water_creature"));
        spawners.add("axolotls", spawn_overrides.getAsJsonObject().getAsJsonArray("axolotls"));
        spawners.add("misc", spawn_overrides.getAsJsonObject().getAsJsonArray("misc"));
        setSpawners(spawners);
    }

    public JsonElement buildSpawners() {
        if (this.mobs.isEmpty()) { return this.spawners; }

        List<PatchableEntity> monsters = this.mobs.stream()
                .filter(e -> PEMob.MONSTER.defaultMobs().contains(e.getId()))
                .toList();
        List<PatchableEntity> creatures = this.mobs.stream()
                .filter(e -> PEMob.LAND_CRITTER.defaultMobs().contains(e.getId()))
                .toList();
        List<PatchableEntity> water_creatures = this.mobs.stream()
                .filter(e -> PEMob.WATER_CRITTER.defaultMobs().contains(e.getId()))
                .toList();
        List<PatchableEntity> ambient = this.mobs.stream()
                .filter(e -> PEMob.AMBIENT_CRITTER.defaultMobs().contains(e.getId()))
                .toList();
        List<PatchableEntity> misc = this.mobs.stream()
                .filter(e -> PEMob.MISC.defaultMobs().contains(e.getId()))
                .toList();

        if(this.spawners.getAsJsonArray("ambient") != null) {
            this.spawners.getAsJsonArray("ambient").addAll(buildSpawnEntry(ambient));
        }
        if(this.spawners.getAsJsonArray("creature") != null) {
            this.spawners.getAsJsonArray("creature").addAll(buildSpawnEntry(creatures));
        }
        if(this.spawners.getAsJsonArray("misc") != null) {
            this.spawners.getAsJsonArray("misc").addAll(buildSpawnEntry(misc));
        }
        if(this.spawners.getAsJsonArray("monster") != null) {
            this.spawners.getAsJsonArray("monster").addAll(buildSpawnEntry(monsters));
        }
        if(this.spawners.getAsJsonArray("water_creature") != null) {
            this.spawners.getAsJsonArray("water_creature").addAll(buildSpawnEntry(water_creatures));
        }

        return ProjectEvergreen.GSON.toJsonTree(spawners);
    }

    private JsonArray buildSpawnEntry(List<PatchableEntity> entities) {
        JsonArray spawns = new JsonArray();
        if (entities.isEmpty()) { return spawns; }
        this.flags.add(PEBiome.Flag.HAS_SPAWN_OVERRIDES);

        entities.forEach(e -> {
            if (!e.isLoaded()) return;
            JsonObject json = new JsonObject();
            json.addProperty("type", e.getId());
            json.addProperty("maxCount", 1);
            json.addProperty("minCount", 1);
            json.addProperty("weight", 1);
            spawns.add(json);
        });
        return spawns;
    }

    private JsonObject buildSpawnCosts() {
        JsonObject spawnCosts = new JsonObject();
        if (this.mobs.isEmpty()) { return spawnCosts; }

        this.mobs.forEach(e -> {
            //if (!e.isLoaded()) return;

            JsonObject json = new JsonObject();
            if(e.getFlags().contains(PEMob.Flag.IS_COMMON_SPAWN)) {
                json.addProperty("charge", 0.7);
                json.addProperty("energy_budget", 0.15);
            } else if(e.getFlags().contains(PEMob.Flag.IS_RARE_SPAWN)) {
                json.addProperty("charge", 1.5);
                json.addProperty("energy_budget", 0.1);
            } else if(e.getFlags().contains(PEMob.Flag.IS_EXTRA_RARE_SPAWN)) {
                json.addProperty("charge", 2.0);
                json.addProperty("energy_budget", 0.05);
            } else if(e.getFlags().contains(PEMob.Flag.FIX_BROKEN_SPAWN)) {
                json.addProperty("charge", 3.0);
                json.addProperty("energy_budget", 0.006);
            } else {
                return;
            }
            spawnCosts.add(e.getId(), json);
        });
        return spawnCosts;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());
        json.add(Constants.JsonProp.SPAWNERS.jsonKey(), this.buildSpawners());
        //json.add(Constants.JsonProp.SPAWN_COSTS.jsonKey(), this.buildSpawnCosts());
        this.flags.forEach(flag -> {
            json.addProperty(flag.jsonKey(), true);
        });

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
