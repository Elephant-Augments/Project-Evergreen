package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureHeightmaps;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRegions;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;

public enum PERegion {
    NO_BIOMES(
        Constants.EMPTY_LIST,
        List.of(PEBiome.NO_BIOMES),
        DangerLevel.SAFE
    ),
    ALL_UNDERGROUND_LAND(
        DefaultStructureHeightmaps.underground,
        List.of(PEBiome.NO_BIOMES),
        DangerLevel.NEUTRAL
    ),
    ALL_OCEAN(
        DefaultStructureRegions.allOcean,
        List.of(
            PEBiome.OCEAN_DEEP_FROZEN,
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_DEEP_WARM,
            PEBiome.OCEAN_DEEP_RARE,
            PEBiome.OCEAN_SHALLOW_FROZEN,
            PEBiome.OCEAN_SHALLOW_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_WARM,
            PEBiome.OCEAN_SHALLOW_RARE
        ),
        DangerLevel.NEUTRAL
    ),
    ALL_COASTAL(
        DefaultStructureRegions.allCoastal,
        List.of(
            PEBiome.COASTAL_ALIVE,
            PEBiome.COASTAL_BARREN,
            PEBiome.COASTAL_FROZEN,
            PEBiome.COASTAL_TEMPERATE,
            PEBiome.COASTAL_WARM
        ),
        DangerLevel.SAFE
    ),
    ALL_RIVERS(
        DefaultStructureRegions.allRivers,
        List.of(
            PEBiome.RIVER_FROZEN,
            PEBiome.RIVER_TEMPERATE,
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    ALL_CIVILIZATION(
        DefaultStructureRegions.allCivilization,
        List.of(
            PEBiome.PLAINS_GRASSY_TEMPERATE,
            PEBiome.FOREST_SPARSE_CONIFEROUS,
            PEBiome.FOREST_SPARSE_DECIDUOUS,
            PEBiome.SPECIAL_AUTUMNAL_FIELDS,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING,
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_FLOWERY_FOREST,
            PEBiome.PLAINS_GRASSY_SNOW,
            PEBiome.FOREST_SPARSE_CONIFEROUS_SNOW,
            PEBiome.PLAINS_GRASSY_ARID,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING,
            PEBiome.DESERT_DUNES_ALIVE,
            PEBiome.DESERT_RED_ALIVE,
            PEBiome.PLAINS_GRASSY_TROPICAL,
            PEBiome.FOREST_SPARSE_TROPICAL,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING,
            PEBiome.SPECIAL_ORIENTAL_INVITING
        ),
        DangerLevel.SAFE
    ),
    ALL_WILDERNESS(
        DefaultStructureRegions.allWilderness,
        List.of(
            PEBiome.FOREST_DENSE_CONIFEROUS,
            PEBiome.FOREST_DENSE_DECIDUOUS,
            PEBiome.FOREST_DENSE_TROPICAL,
            PEBiome.DESERT_DUNES_BARREN,
            PEBiome.DESERT_RED_BARREN,
            PEBiome.PLAINS_SHRUBBY_ARID,
            PEBiome.PLAINS_SHRUBBY_TEMPERATE,
            PEBiome.PLAINS_SHRUBBY_TROPICAL,
            PEBiome.PLAINS_SHRUBBY_SNOW,
            PEBiome.FOREST_DENSE_CONIFEROUS_SNOW,
            PEBiome.SPECIAL_AUTUMNAL_FOREST,
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING,
            PEBiome.SPECIAL_ORIENTAL_UNINVITING,
            PEBiome.SPECIAL_CRAGGY_SNOW,
            PEBiome.SPECIAL_CRAGGY_TEMPERATE,
            PEBiome.SPECIAL_CRAGGY_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    ALL_SPECIAL(
        Constants.EMPTY_LIST,
        List.of(
            PEBiome.SPECIAL_CRAGGY_TEMPERATE,
            PEBiome.SPECIAL_CRAGGY_WARM,
            PEBiome.SPECIAL_CRAGGY_SNOW,
            PEBiome.SPECIAL_ICY,
            PEBiome.SPECIAL_MAGICAL,
            PEBiome.MOUNTAINS_ALIVE,
            PEBiome.MOUNTAINS_BARREN,
            PEBiome.MOUNTAINS_COLD,
            PEBiome.MOUNTAINS_HOT,
            PEBiome.SPECIAL_ROCKY,
            PEBiome.SPECIAL_RUINED,
            PEBiome.SPECIAL_SHROOMY,
            PEBiome.SPECIAL_SPOOKY,
            PEBiome.SPECIAL_SWAMPY_SNOW,
            PEBiome.SPECIAL_SWAMPY_TEMPERATE,
            PEBiome.SPECIAL_SWAMPY_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    CIVILIZATION_TEMPERATE(
        DefaultStructureRegions.civilizationTemperate,
        List.of(
            PEBiome.PLAINS_GRASSY_TEMPERATE,
            PEBiome.FOREST_SPARSE_CONIFEROUS,
            PEBiome.FOREST_SPARSE_DECIDUOUS,
            PEBiome.SPECIAL_AUTUMNAL_FIELDS,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING,
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_FLOWERY_FOREST
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_FIELDS(
        DefaultStructureRegions.civilizationFields,
        List.of(
            PEBiome.PLAINS_GRASSY_TEMPERATE,
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_AUTUMNAL_FIELDS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_CONIFEROUS(
        DefaultStructureRegions.civilizationConiferous,
        List.of(
            PEBiome.FOREST_SPARSE_CONIFEROUS_SNOW,
            PEBiome.FOREST_SPARSE_CONIFEROUS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DECIDUOUS(
        DefaultStructureRegions.civilizationDeciduous,
        List.of(
            PEBiome.FOREST_SPARSE_DECIDUOUS,
            PEBiome.SPECIAL_FLOWERY_FOREST
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_ARID(
        DefaultStructureRegions.civilizationArid,
        List.of(
            PEBiome.PLAINS_GRASSY_ARID,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DESERT(
        DefaultStructureRegions.civilizationDesert,
        List.of(
            PEBiome.DESERT_DUNES_ALIVE
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DESERT_RED(
        DefaultStructureRegions.civilizationDesertRed,
        List.of(
            PEBiome.DESERT_RED_ALIVE
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_TROPICAL(
        DefaultStructureRegions.civilizationTropical,
        List.of(
            PEBiome.PLAINS_GRASSY_TROPICAL,
            PEBiome.FOREST_SPARSE_TROPICAL
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SNOW(
        DefaultStructureRegions.civilizationSnow,
        List.of(
            PEBiome.PLAINS_GRASSY_SNOW,
            PEBiome.FOREST_SPARSE_CONIFEROUS_SNOW
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_AUTUMNAL(
        DefaultStructureRegions.civilizationSpecialAutumnal,
        List.of(
            PEBiome.SPECIAL_AUTUMNAL_FIELDS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_FLOWERY(
        DefaultStructureRegions.civilizationSpecialFlowery,
        List.of(
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_FLOWERY_FOREST
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_MEDITERRANEAN(
        DefaultStructureRegions.civilizationSpecialMediterranean,
        List.of(
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_ORIENTAL(
        DefaultStructureRegions.civilizationSpecialOriental,
        List.of(
            PEBiome.SPECIAL_ORIENTAL_INVITING
        ),
        DangerLevel.SAFE
    ),
    WILDERNESS_TEMPERATE(
        DefaultStructureRegions.wildernessTemperate,
        List.of(
            PEBiome.PLAINS_SHRUBBY_TEMPERATE,
            PEBiome.FOREST_DENSE_CONIFEROUS,
            PEBiome.FOREST_DENSE_DECIDUOUS,
            PEBiome.SPECIAL_AUTUMNAL_FOREST,
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING,
            PEBiome.SPECIAL_CRAGGY_TEMPERATE
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_FIELDS(
        DefaultStructureRegions.wildernessFields,
        List.of(
            PEBiome.PLAINS_SHRUBBY_SNOW,
            PEBiome.PLAINS_SHRUBBY_TEMPERATE,
            PEBiome.PLAINS_SHRUBBY_ARID
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_CONIFEROUS(
        DefaultStructureRegions.wildernessConiferous,
        List.of(
            PEBiome.FOREST_DENSE_CONIFEROUS
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_DECIDUOUS(
        DefaultStructureRegions.wildernessDeciduous,
        List.of(
            PEBiome.FOREST_DENSE_DECIDUOUS
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_ARID(
        DefaultStructureRegions.wildernessArid,
        List.of(
            PEBiome.PLAINS_SHRUBBY_ARID,
            PEBiome.SPECIAL_CRAGGY_WARM,
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_DESERT(
        DefaultStructureRegions.wildernessDesert,
        List.of(
            PEBiome.DESERT_DUNES_BARREN
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_DESERT_RED(
        DefaultStructureRegions.wildernessDesertRed,
        List.of(
            PEBiome.DESERT_RED_BARREN
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_TROPICAL(
        DefaultStructureRegions.wildernessTropical,
        List.of(
            PEBiome.FOREST_DENSE_TROPICAL,
            PEBiome.PLAINS_SHRUBBY_TROPICAL
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_SNOW(
        DefaultStructureRegions.wildernessSnow,
        List.of(
            PEBiome.PLAINS_SHRUBBY_SNOW,
            PEBiome.FOREST_DENSE_CONIFEROUS_SNOW,
            PEBiome.SPECIAL_CRAGGY_SNOW
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_SPECIAL_AUTUMNAL(
        DefaultStructureRegions.wildernessSpecialAutumnal,
        List.of(
            PEBiome.SPECIAL_AUTUMNAL_FOREST
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_SPECIAL_MEDITERRANEAN(
        DefaultStructureRegions.wildernessSpecialMediterranean,
        List.of(
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_SPECIAL_ORIENTAL(
        DefaultStructureRegions.wildernessSpecialOriental,
        List.of(
            PEBiome.SPECIAL_ORIENTAL_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    COASTAL_FROZEN(
        DefaultStructureRegions.coastalFrozen,
        List.of(
            PEBiome.COASTAL_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    COASTAL_TEMPERATE(
        DefaultStructureRegions.coastalTemperate,
        List.of(
            PEBiome.COASTAL_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    COASTAL_WARM(
        DefaultStructureRegions.coastalWarm,
        List.of(
            PEBiome.COASTAL_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_DEEP(
        DefaultStructureRegions.oceanDeep,
        List.of(
            PEBiome.OCEAN_DEEP_FROZEN,
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_DEEP_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_SHALLOW(
        DefaultStructureRegions.oceanShallow,
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN,
            PEBiome.OCEAN_SHALLOW_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_NOT_FROZEN(
        DefaultStructureRegions.oceanNotFrozen,
        List.of(
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_DEEP_WARM,
            PEBiome.OCEAN_SHALLOW_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_FROZEN(
        DefaultStructureRegions.oceanFrozen,
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN,
            PEBiome.OCEAN_DEEP_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_TEMPERATE(
        DefaultStructureRegions.oceanTemperate,
        List.of(
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    OCEAN_WARM(
        DefaultStructureRegions.oceanWarm,
        List.of(
            PEBiome.OCEAN_DEEP_WARM,
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_DEEP_FROZEN(
        DefaultStructureRegions.oceanDeepFrozen,
        List.of(
            PEBiome.OCEAN_DEEP_FROZEN
        ),
        DangerLevel.DANGEROUS
    ),
    OCEAN_DEEP_TEMPERATE(
        DefaultStructureRegions.oceanDeepTemperate,
        List.of(
                PEBiome.OCEAN_DEEP_TEMPERATE
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_DEEP_WARM(
        DefaultStructureRegions.oceanDeepWarm,
        List.of(
            PEBiome.OCEAN_DEEP_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_RARE_DEEP(
        DefaultStructureRegions.oceanRareDeep,
        List.of(
            PEBiome.OCEAN_DEEP_RARE
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_SHALLOW_FROZEN(
        DefaultStructureRegions.oceanShallowFrozen,
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_SHALLOW_TEMPERATE(
        DefaultStructureRegions.oceanShallowTemperate,
        List.of(
            PEBiome.OCEAN_SHALLOW_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    OCEAN_SHALLOW_WARM(
        DefaultStructureRegions.oceanShallowWarm,
        List.of(
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_RARE_SHALLOW(
        DefaultStructureRegions.oceanRareShallow,
        List.of(
            PEBiome.OCEAN_SHALLOW_RARE
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_OR_COAST_NOT_FROZEN(
        DefaultStructureRegions.riverOrCoastNotFrozen,
        List.of(
            PEBiome.RIVER_TEMPERATE,
            PEBiome.RIVER_WARM,
            PEBiome.COASTAL_TEMPERATE,
            PEBiome.COASTAL_WARM
        ),
        DangerLevel.SAFE
    ),
    RIVER_OR_COAST_FROZEN(
        DefaultStructureRegions.riverOrCoastFrozen,
        List.of(
            PEBiome.RIVER_FROZEN,
            PEBiome.COASTAL_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_NOT_FROZEN(
        DefaultStructureRegions.riverNotFrozen,
        List.of(
            PEBiome.RIVER_TEMPERATE,
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    RIVER_FROZEN(
        DefaultStructureRegions.riverFrozen,
        List.of(
            PEBiome.RIVER_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_TEMPERATE(
        DefaultStructureRegions.riverTemperate,
        List.of(
            PEBiome.RIVER_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    RIVER_WARM(
        DefaultStructureRegions.riverWarm,
        List.of(
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    SPECIAL_ROCKY(
        DefaultStructureRegions.specialRocky,
        List.of(
            PEBiome.MOUNTAINS_BARREN,
            PEBiome.SPECIAL_ROCKY
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_CRAGGY(
        DefaultStructureRegions.specialCraggy,
        List.of(
            PEBiome.SPECIAL_CRAGGY_TEMPERATE,
            PEBiome.SPECIAL_CRAGGY_WARM,
            PEBiome.SPECIAL_CRAGGY_SNOW,
            PEBiome.MOUNTAINS_ALIVE,
            PEBiome.SPECIAL_ROCKY
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_ICY(
        DefaultStructureRegions.specialIcy,
        List.of(
            PEBiome.SPECIAL_ICY
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MAGICAL(
        DefaultStructureRegions.specialMagical,
        List.of(
            PEBiome.SPECIAL_MAGICAL
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MOUNTAINOUS_COLD(
        DefaultStructureRegions.specialMountainousCold,
        List.of(
            PEBiome.MOUNTAINS_COLD,
            PEBiome.SPECIAL_CRAGGY_SNOW
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MOUNTAINOUS_HOT(
        DefaultStructureRegions.specialMountainousHot,
        List.of(
            PEBiome.MOUNTAINS_HOT
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_RUINED(
        DefaultStructureRegions.specialRuined,
        List.of(
            PEBiome.SPECIAL_RUINED
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_SHROOMY(
        DefaultStructureRegions.specialShroomy,
        List.of(
            PEBiome.SPECIAL_SHROOMY
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_SPOOKY(
        DefaultStructureRegions.specialSpooky,
        List.of(
            PEBiome.SPECIAL_SPOOKY,
            PEBiome.SPECIAL_AUTUMNAL_FOREST
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_SWAMPY(
        DefaultStructureRegions.specialSwampy,
        List.of(
            PEBiome.SPECIAL_SWAMPY_SNOW,
            PEBiome.SPECIAL_SWAMPY_TEMPERATE,
            PEBiome.SPECIAL_SWAMPY_WARM
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_SWAMPY_WARM(
        DefaultStructureRegions.specialSwampyWarm,
        List.of(
            PEBiome.SPECIAL_SWAMPY_WARM
        ),
        DangerLevel.DANGEROUS
    );

    private List<String> defaultStructures = new ArrayList<>();
    private List<PEBiome> defaultBiomes = new ArrayList<>();

    private final String jsonKey = "region";
    private final String jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
    private final String path;
    private final ResourceLocation location;
    private final String tagKey;
    private TagKey<Biome> biomeTag;
    private TagKey<Structure> structureTag;
    private DangerLevel dangerLevel;

    public enum DangerLevel {
        SAFE,
        NEUTRAL,
        DANGEROUS
    }

    PERegion(List<String> defaultStructures, List<PEBiome> defaultBiomes, DangerLevel dangerLevel) {
        path = "is_region/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        tagKey = "#" + location;
        this.defaultBiomes.addAll(defaultBiomes);
        this.defaultStructures.addAll(defaultStructures);
        this.biomeTag = ProjectEvergreen.createTag(Registries.BIOME, location);
        this.structureTag = ProjectEvergreen.createTag(Registries.STRUCTURE, location);
        this.dangerLevel = dangerLevel;
    }

    public String path() {
        return this.path;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public String tagKey() {
        return tagKey;
    }

    public TagKey<Biome> biomeTag() {
        return this.biomeTag;
    }

    public TagKey<Structure> structureTag() {
        return this.structureTag;
    }

    public List<String> defaultBiomes() {
        return defaultBiomes.stream().map(b -> b.location().toString()).toList();
    }

    public List<String> defaultStructures() {
        return defaultStructures;
    }

    public void appendStructures(String id) {
        defaultStructures.add(id);
    }

    public int difficulty() {
        Map<DangerLevel, Integer> diffOffset = Map.of(
                DangerLevel.SAFE, Constants.SAFE_DIFFICULTY_OFFSET,
                DangerLevel.NEUTRAL, Constants.NEUTRAL_DIFFICULTY_OFFSET,
                DangerLevel.DANGEROUS, Constants.DANGEROUS_DIFFICULTY_OFFSET
        );
        return Constants.OVERWORLD_DIFFICULTY + diffOffset.get(dangerLevel);
    }

    public static List<String> allOverworldStructures() {
        HashSet<String> overworldStructures = Arrays.stream(PERegion.values())
                .map(PERegion::defaultStructures)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return overworldStructures.stream().toList();
    }

    public static List<String> allWaterStructures() {
        HashSet<String> waterStructures = Arrays.stream(PERegion.values())
                .filter(r -> r.name().contains("OCEAN") || r.name().contains("RIVER"))
                .map(PERegion::defaultStructures)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return waterStructures.stream().toList();
    }
}
