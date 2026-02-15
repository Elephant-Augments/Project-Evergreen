package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.kinds.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatchableStructureSet extends IPatchable {

    protected final String REGISTRY_PATH = "worldgen/structure_set/";
    public final String JSON_DATA_KEY = Constants.PATCHABLE_STRUCTURE_SET_KEY;

    private final List<PEStructureSet.Flag> flags = new ArrayList<PEStructureSet.Flag>();
    private final ArrayList<PatchableStructure> structures = new ArrayList<PatchableStructure>();

    private int separation;
    private int spacing;
    private int salt;
    private PEStructureSet data;

    public PatchableStructureSet(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
        salt = getSalt();
    }

    public PatchableStructureSet(String ID, int spacing, int separation) {
        this(ID);
        this.spacing = spacing;
        this.separation = separation;
    }

    @Override
    public void updateData() {
        updateStructureData();
        WorldgenDataManager.setStructureSetData(this.id, this);
    }

    public List<PatchableStructure> getStructures() {
        List<PatchableStructure> child_structures = WorldgenDataManager.PATCHABLE_STRUCTURES.values().stream()
                .filter(s -> s.getStructureSet().isPresent())
                .filter(s -> s.getStructureSet().get() == this.data)
                .sorted((a, b) -> ProjectEvergreen.COLLATOR.compare(a.getId().split(":")[0], b.getId().split(":")[0]))
                .toList();
        if (this.structures.isEmpty()) { this.structures.addAll(child_structures); }
        return this.structures;
    }

    public void setData(PEStructureSet set) {
        this.data = set;
        this.spacing = set.spacing();
        this.separation = set.separation();
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

    public boolean hasFlatStructures() {
        return (PlatformHooks.PLATFORM_HELPER.isModLoaded("integrated_api") ||
                PlatformHooks.PLATFORM_HELPER.isModLoaded("moogs_structures") ||
                PlatformHooks.PLATFORM_HELPER.isModLoaded("repurposed_structures")) &&
                (this.getId().contains("civilization") || this.getId().contains("wilderness"));
    }

    public boolean hasPopulationBias(int populationBias) {
        Optional<PEStructureSet> sset = getData();
        return sset.map(peStructureSet -> switch (populationBias) {
            case 0 -> (peStructureSet == PEStructureSet.CIVILIZATION_RARE) || (peStructureSet == PEStructureSet.CIVILIZATION_EXTRA_RARE);
            case 1 -> false;
            case 2 -> (peStructureSet == PEStructureSet.WILDERNESS_RARE) || (peStructureSet == PEStructureSet.WILDERNESS_EXTRA_RARE);
            default -> throw new IllegalArgumentException("Population Bias Must Be 0, 1, or 2.");
        }).orElse(false);
    }

    public JsonElement buildStructureSet() {
        List<JsonObject> jsonSet = this.getStructures().stream()
                .filter(s -> s.isLoaded() && !s.getFlags().contains(PEStructure.Flag.DISABLED))
                .map(this::buildStructureEntry)
                .collect(Collectors.toList());
        return ProjectEvergreen.GSON.toJsonTree(jsonSet);
    }

    private JsonObject buildStructureEntry(PatchableStructure structure) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Building dynamic structure entry: " + structure.getId());
        }
        JsonObject json = new JsonObject();
        json.addProperty("structure", structure.getId());
        json.addProperty("weight", structure.getWeight());
        return json;
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();

        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
