package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Size;
import com.elephantaugments.projectevergreen.common.api.PEStructure.Heightmap;
import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.platform.PlatformHooks;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private int weight = 1;
    private int biomeRadiusOffset = 1;
    private int flatnessCheckRadius = 1;
    private int allowedTerrainHeight = 10;

    private PEStructure data;

    public PatchableStructure(String ID) {
        super(ID);
        full_path = getFullPath(ID, REGISTRY_PATH);
    }

    public Optional<PEStructure> getData() {
        return Optional.ofNullable(this.data);
    }

    public void setData(PEStructure structure) {
        this.data = structure;
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
        boolean advancedModLoaded = (PlatformHooks.PLATFORM_HELPER.isModLoaded("integrated_api") ||
                PlatformHooks.PLATFORM_HELPER.isModLoaded("moogs_structures") ||
                PlatformHooks.PLATFORM_HELPER.isModLoaded("repurposed_structures"));
        boolean isSupportedType = (getType().isPresent() &&
                PEStructure.SupportedTypes.contains(type) &&
                !flags.contains(PEStructure.Flag.IGNORED_PLACEMENT_TWEAKS));
        boolean isIDA = (PlatformHooks.PLATFORM_HELPER.isModLoaded("integrated_dungeons_arise") &&
                getId().contains("dungeons_arise"));

        return advancedModLoaded && isSupportedType && !isIDA;
    }

    public boolean isFlat() {
        return getHeightmap().isPresent() &&
            (heightmap == Heightmap.GROUNDLEVEL) &&
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

    public boolean isDeepUnderground() {
        return PlatformHooks.PLATFORM_HELPER.isModLoaded("expanded_ecosphere") && isUnderground();
    }

    public boolean isInland() {
        return getHeightmap().isPresent() &&
            heightmap == Heightmap.GROUNDLEVEL;
    }

    public boolean isWaterBound() {
        return PERegion.allWaterStructures().contains(this.id) ||
                (getRegion().isPresent() && getRegion().get().name().contains("SWAMP"));
    }

    public boolean isWaterRestricted() {
        return isInland() && !isWaterBound();
    }


    public boolean isRadiusBound() {
        return isAdvancedType() && (!isSmall() || isWaterBound()) &&
                !(flags.contains(PEStructure.Flag.IGNORED_BIOME_RADIUS_CHECK));
    }

    public boolean hasPopulationBias(int populationBias) {
        return isRadiusBound() &&
            switch (populationBias) {
            case 0 -> isCivilization();
            case 1 -> !isUnderground();
            case 2 -> isWilderness();
            default -> throw new IllegalArgumentException("No Config Value Provided.");
        };
    }

    @Override
    public void updateData() {
        calculateWeight();
        calculateFlatness();
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

    public int getWeight() {
        return weight;
    }

    public void calculateWeight() {
        if (isMassive() &&
                (isFlat() || id.contains("village"))) {
            this.weight = 3;
        } else if (isFlat() && !isMassive()) {
            this.weight = 2;
        } else {
            this.weight = 1;
        }
    }

    public void calculateFlatness() {
        getSize().ifPresentOrElse(
            (size) -> {
                this.flatnessCheckRadius = size.flatnessRadius();
                this.allowedTerrainHeight = size.terrainHeight();
            }, () -> {});
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
                diffLevel = diffLevel - size.diffOffset();
            } else {
                diffLevel = diffLevel + size.diffOffset();
            }
        }
        //Flag overrides
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_FOUR)) { diffLevel = 4; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_FIVE)) { diffLevel = 5; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_SIX)) { diffLevel = 6; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_SEVEN)) { diffLevel = 7; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_EIGHT)) { diffLevel = 8; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_NINE)) { diffLevel = 9; }
        if (this.flags.contains(PEStructure.Flag.IS_DIFF_TEN)) { diffLevel = 10; }
        difficulty = diffLevel;
    }

    public JsonElement buildSpawnOverrides() {
        JsonObject overrides = new JsonObject();

        Optional<PEStructure> sdata = this.getData();
        if (sdata.isEmpty()) return overrides;

        List<String> monsters = sdata.get().defaultSpawns().stream()
                .filter(e -> PEMob.MONSTER.defaultMobs().contains(e))
                .toList();
        List<String> creatures = sdata.get().defaultSpawns().stream()
                .filter(e -> PEMob.LAND_CRITTER.defaultMobs().contains(e))
                .toList();
        List<String> water_creatures = sdata.get().defaultSpawns().stream()
                .filter(e -> PEMob.WATER_CRITTER.defaultMobs().contains(e))
                .toList();
        List<String> misc = sdata.get().defaultSpawns().stream()
                .filter(e -> PEMob.MISC.defaultMobs().contains(e))
                .toList();

        if (!monsters.isEmpty()) { overrides.add("monster", buildSpawnEntry(monsters)); }
        if (!creatures.isEmpty()) { overrides.add("creature", buildSpawnEntry(creatures)); }
        if (!water_creatures.isEmpty()) { overrides.add("water_creature", buildSpawnEntry(water_creatures)); }
        if (!misc.isEmpty()) { overrides.add("misc", buildSpawnEntry(misc)); }

        return ProjectEvergreen.GSON.toJsonTree(overrides);
    }

    private JsonObject buildSpawnEntry(List<String> entities) {
        if (PlatformHooks.PLATFORM_HELPER.isDevelopmentEnvironment()) {
            ProjectEvergreen.LOGGER.info("Building dynamic structure spawn override... ");
        }
        JsonObject json = new JsonObject();
        json.addProperty("bounding_box", "full");
        JsonArray spawns = new JsonArray();
        entities.forEach(id -> {
            Optional<PatchableEntity> edata = Optional.ofNullable(WorldgenDataManager.PATCHABLE_ENTITIES.get(id));
            edata.ifPresent((e) -> {
                if (!e.isLoaded()) return;
                this.flags.add(PEStructure.Flag.HAS_SPAWN_OVERRIDES);
                JsonObject entry = new JsonObject();
                entry.addProperty("type", e.getId());
                entry.addProperty("maxCount", 1);
                entry.addProperty("minCount", 1);
                entry.addProperty("weight", 1);
                spawns.add(entry);
            });
        });
        json.add("spawns", spawns);
        return json;
    }
    
    public void initJsonData(String type, String step, JsonElement heightmap) {
        setType(type);
        setStep(step);
        if (getHeightmap().isEmpty()) {
            if (heightmap != null) {
                if (isWaterBound()) {
                    if (heightmap.getAsString().toLowerCase().contains("ocean_floor")) {
                        setHeightmap(PEStructure.Heightmap.OCEANFLOOR);
                        PEStructure.Heightmap.OCEANFLOOR.appendIDs(id);
                    } else {
                        setHeightmap(PEStructure.Heightmap.OCEANSURFACE);
                        PEStructure.Heightmap.OCEANSURFACE.appendIDs(id);
                    }
                }
            }
            if ((step.equals("underground_structures") || step.equals("underground_decoration") || step.equals("strongholds"))) {
                setHeightmap(PEStructure.Heightmap.UNDERGROUND);
                PEStructure.Heightmap.UNDERGROUND.appendIDs(id);
            }
        }
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty(Constants.JsonProp.ID.jsonKey(), this.getId());
        json.addProperty(Constants.JsonProp.DIFFICULTY.jsonKey(), this.difficulty);

        getSize().ifPresent((size) -> {
            json.addProperty(size.jsonKey(), size.name());
        });
        getHeightmap().ifPresent((heightmap) -> {
            json.addProperty(heightmap.jsonKey(), heightmap.name());
        });
        getDimension().ifPresent((dim) -> {
            json.addProperty(dim.jsonKey(), dim.biomeTagKey());
        });
        getRegion().ifPresent((region) -> {
            json.addProperty(region.jsonKey(), region.tagKey());
        });
        getStructureSet().ifPresent((sset) -> {
            json.addProperty(sset.jsonKey(), sset.location().toString());
        });
        getData().ifPresent((sdata) -> {
            json.addProperty(Constants.JsonProp.REGION.jsonKey(), sdata.biomeTag());
        });

        json.addProperty(Constants.JsonProp.IS_LOADED.jsonKey(), this.is_loaded);
        json.addProperty(Constants.JsonProp.IS_ADVANCED_TYPE.jsonKey(), this.isAdvancedType());
        json.addProperty(Constants.JsonProp.IS_FLAT.jsonKey(), this.isFlat());
        json.addProperty(Constants.JsonProp.IS_MASSIVE.jsonKey(), this.isMassive());
        json.addProperty(Constants.JsonProp.IS_INLAND.jsonKey(), this.isInland());
        json.addProperty(Constants.JsonProp.IS_RADIUS_BOUND.jsonKey(), this.isRadiusBound());
        json.addProperty(Constants.JsonProp.IS_WATER_BOUND.jsonKey(), this.isWaterBound());
        json.addProperty(Constants.JsonProp.IS_WATER_RESTRICTED.jsonKey(), this.isWaterRestricted());
        json.add(Constants.JsonProp.SPAWN_OVERRIDES.jsonKey(), this.buildSpawnOverrides());
        this.flags.forEach(flag -> {
            json.addProperty(flag.jsonKey(), true);
        });

        return ProjectEvergreen.GSON.toJsonTree(json);
    }
}
