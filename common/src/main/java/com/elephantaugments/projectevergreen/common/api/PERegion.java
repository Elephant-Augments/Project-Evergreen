package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureHeightmaps;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultStructureRegions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.*;
import java.util.stream.Collectors;

public enum PERegion {
    NO_BIOMES(
        Constants.EMPTY_LIST,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(PEBiome.NO_BIOMES),
        DangerLevel.SAFE
    ),
    UNDERGROUND_LAND(
        DefaultStructureHeightmaps.underground,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(PEBiome.UNDERGROUND),
        DangerLevel.DANGEROUS
    ),
    ALL_OCEAN(
        DefaultStructureRegions.allOcean,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.NON_WATER_CRITTER.defaultMobs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.UNCIVILIZED_MONSTER.defaultMobs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.NON_WATER_CRITTER.defaultMobs(),
        List.of(
            PEBiome.RIVER_FROZEN,
            PEBiome.RIVER_TEMPERATE,
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    ALL_CIVILIZATION(
        DefaultStructureRegions.allCivilization,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.UNCIVILIZED_MONSTER.defaultMobs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_GRASSY_TEMPERATE,
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_AUTUMNAL_FIELDS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_CONIFEROUS(
        DefaultStructureRegions.civilizationConiferous,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.FOREST_SPARSE_CONIFEROUS_SNOW,
            PEBiome.FOREST_SPARSE_CONIFEROUS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DECIDUOUS(
        DefaultStructureRegions.civilizationDeciduous,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.FOREST_SPARSE_DECIDUOUS,
            PEBiome.SPECIAL_FLOWERY_FOREST
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_ARID(
        DefaultStructureRegions.civilizationArid,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_GRASSY_ARID,
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DESERT(
        DefaultStructureRegions.civilizationDesert,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.DESERT_DUNES_ALIVE
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_DESERT_RED(
        DefaultStructureRegions.civilizationDesertRed,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.DESERT_RED_ALIVE
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_TROPICAL(
        DefaultStructureRegions.civilizationTropical,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_GRASSY_TROPICAL,
            PEBiome.FOREST_SPARSE_TROPICAL
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SNOW(
        DefaultStructureRegions.civilizationSnow,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_GRASSY_SNOW,
            PEBiome.FOREST_SPARSE_CONIFEROUS_SNOW
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_AUTUMNAL(
        DefaultStructureRegions.civilizationSpecialAutumnal,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_AUTUMNAL_FIELDS
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_FLOWERY(
        DefaultStructureRegions.civilizationSpecialFlowery,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_FLOWERY_FIELDS,
            PEBiome.SPECIAL_FLOWERY_FOREST
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_MEDITERRANEAN(
        DefaultStructureRegions.civilizationSpecialMediterranean,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_MEDITERRANEAN_INVITING
        ),
        DangerLevel.SAFE
    ),
    CIVILIZATION_SPECIAL_ORIENTAL(
        DefaultStructureRegions.civilizationSpecialOriental,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_ORIENTAL_INVITING
        ),
        DangerLevel.SAFE
    ),
    WILDERNESS_TEMPERATE(
        DefaultStructureRegions.wildernessTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_SHRUBBY_SNOW,
            PEBiome.PLAINS_SHRUBBY_TEMPERATE,
            PEBiome.PLAINS_SHRUBBY_ARID
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_CONIFEROUS(
        DefaultStructureRegions.wildernessConiferous,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.FOREST_DENSE_CONIFEROUS
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_DECIDUOUS(
        DefaultStructureRegions.wildernessDeciduous,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.FOREST_DENSE_DECIDUOUS
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_ARID(
        DefaultStructureRegions.wildernessArid,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_SHRUBBY_ARID,
            PEBiome.SPECIAL_CRAGGY_WARM,
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_DESERT(
        DefaultStructureRegions.wildernessDesert,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.DESERT_DUNES_BARREN
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_DESERT_RED(
        DefaultStructureRegions.wildernessDesertRed,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.DESERT_RED_BARREN
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_TROPICAL(
        DefaultStructureRegions.wildernessTropical,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.FOREST_DENSE_TROPICAL,
            PEBiome.PLAINS_SHRUBBY_TROPICAL
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_SNOW(
        DefaultStructureRegions.wildernessSnow,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.PLAINS_SHRUBBY_SNOW,
            PEBiome.FOREST_DENSE_CONIFEROUS_SNOW,
            PEBiome.SPECIAL_CRAGGY_SNOW,
            PEBiome.SPECIAL_SWAMPY_SNOW
        ),
        DangerLevel.DANGEROUS
    ),
    WILDERNESS_SPECIAL_AUTUMNAL(
        DefaultStructureRegions.wildernessSpecialAutumnal,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_AUTUMNAL_FOREST
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_SPECIAL_MEDITERRANEAN(
        DefaultStructureRegions.wildernessSpecialMediterranean,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_MEDITERRANEAN_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    WILDERNESS_SPECIAL_ORIENTAL(
        DefaultStructureRegions.wildernessSpecialOriental,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_ORIENTAL_UNINVITING
        ),
        DangerLevel.NEUTRAL
    ),
    COASTAL_FROZEN(
        DefaultStructureRegions.coastalFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.COASTAL_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    COASTAL_TEMPERATE(
        DefaultStructureRegions.coastalTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.COASTAL_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    COASTAL_WARM(
        DefaultStructureRegions.coastalWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.COASTAL_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_DEEP(
        DefaultStructureRegions.oceanDeep,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_FROZEN,
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_DEEP_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_SHALLOW(
        DefaultStructureRegions.oceanShallow,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN,
            PEBiome.OCEAN_SHALLOW_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_NOT_FROZEN(
        DefaultStructureRegions.oceanNotFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN,
            PEBiome.OCEAN_DEEP_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_TEMPERATE(
        DefaultStructureRegions.oceanTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_TEMPERATE,
            PEBiome.OCEAN_SHALLOW_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    OCEAN_WARM(
        DefaultStructureRegions.oceanWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_WARM,
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_DEEP_FROZEN(
        DefaultStructureRegions.oceanDeepFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_FROZEN
        ),
        DangerLevel.DANGEROUS
    ),
    OCEAN_DEEP_TEMPERATE(
        DefaultStructureRegions.oceanDeepTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
                PEBiome.OCEAN_DEEP_TEMPERATE
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_DEEP_WARM(
        DefaultStructureRegions.oceanDeepWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_RARE_DEEP(
        DefaultStructureRegions.oceanRareDeep,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_DEEP_RARE
        ),
        DangerLevel.DANGEROUS
    ),
    OCEAN_SHALLOW_FROZEN(
        DefaultStructureRegions.oceanShallowFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    OCEAN_SHALLOW_TEMPERATE(
        DefaultStructureRegions.oceanShallowTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    OCEAN_SHALLOW_WARM(
        DefaultStructureRegions.oceanShallowWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_WARM
        ),
        DangerLevel.SAFE
    ),
    OCEAN_RARE_SHALLOW(
        DefaultStructureRegions.oceanRareShallow,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.OCEAN_SHALLOW_RARE
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_OR_COAST_NOT_FROZEN(
        DefaultStructureRegions.riverOrCoastNotFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.RIVER_FROZEN,
            PEBiome.COASTAL_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_NOT_FROZEN(
        DefaultStructureRegions.riverNotFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.RIVER_TEMPERATE,
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    RIVER_FROZEN(
        DefaultStructureRegions.riverFrozen,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.RIVER_FROZEN
        ),
        DangerLevel.NEUTRAL
    ),
    RIVER_TEMPERATE(
        DefaultStructureRegions.riverTemperate,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.RIVER_TEMPERATE
        ),
        DangerLevel.SAFE
    ),
    RIVER_WARM(
        DefaultStructureRegions.riverWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.RIVER_WARM
        ),
        DangerLevel.SAFE
    ),
    SPECIAL_ROCKY(
        DefaultStructureRegions.specialRocky,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.MOUNTAINS_BARREN,
            PEBiome.SPECIAL_ROCKY
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_CRAGGY(
        DefaultStructureRegions.specialCraggy,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
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
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_ICY
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MAGICAL(
        DefaultStructureRegions.specialMagical,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_MAGICAL
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MOUNTAINOUS_COLD(
        DefaultStructureRegions.specialMountainousCold,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.MOUNTAINS_COLD,
            PEBiome.SPECIAL_CRAGGY_SNOW
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_MOUNTAINOUS_HOT(
        DefaultStructureRegions.specialMountainousHot,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.MOUNTAINS_HOT
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_RUINED(
        DefaultStructureRegions.specialRuined,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_RUINED
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_SHROOMY(
        DefaultStructureRegions.specialShroomy,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_SHROOMY
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_SPOOKY(
        DefaultStructureRegions.specialSpooky,
            PEFeature.Flag.DISABLED.tag(),
        PEMob.LAND_CRITTER.defaultMobs(),
        List.of(
            PEBiome.SPECIAL_SPOOKY,
            PEBiome.SPECIAL_AUTUMNAL_FOREST
        ),
        DangerLevel.DANGEROUS
    ),
    SPECIAL_SWAMPY(
        DefaultStructureRegions.specialSwampy,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_SWAMPY_TEMPERATE,
            PEBiome.SPECIAL_SWAMPY_WARM
        ),
        DangerLevel.NEUTRAL
    ),
    SPECIAL_SWAMPY_WARM(
        DefaultStructureRegions.specialSwampyWarm,
        PEFeature.Flag.DISABLED.tag(),
        PEMob.Flag.DISABLED.defaultIDs(),
        List.of(
            PEBiome.SPECIAL_SWAMPY_WARM
        ),
        DangerLevel.DANGEROUS
    );

    private List<String> defaultStructures = new ArrayList<>();
    private List<String> mobRemovals = new ArrayList<>();
    private List<PEBiome> defaultBiomes = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.REGION.jsonKey();
    private final String jsonPath = Constants.JsonProp.REGION.jsonPath();
    private final String path;
    private final ResourceLocation location;
    private final String tagKey;
    private TagKey<Biome> biomeTag;
    private TagKey<Structure> structureTag;
    private TagKey<PlacedFeature> featureRemovalTag;
    private TagKey<EntityType<?>> mobRemovalTag;
    private DangerLevel dangerLevel;

    public enum DangerLevel {
        SAFE,
        NEUTRAL,
        DANGEROUS
    }

    PERegion(List<String> defaultStructures,
             TagKey<PlacedFeature> featureRemovalTag,
             List<String> defaultMobRemovals,
             List<PEBiome> defaultBiomes,
             DangerLevel dangerLevel
    ) {
        path = "is_region/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        tagKey = "#" + location;
        this.defaultBiomes.addAll(defaultBiomes);
        this.defaultStructures.addAll(defaultStructures);
        this.mobRemovals.addAll(defaultMobRemovals);
        this.mobRemovalTag = this.getMobRemovals();
        this.featureRemovalTag = featureRemovalTag;
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

    public DangerLevel dangerLevel() {
        return this.dangerLevel;
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

    public TagKey<PlacedFeature> featureTag() {
        return this.featureRemovalTag;
    }

    public TagKey<EntityType<?>> mobRemovalTag() {
        return this.mobRemovalTag;
    }

    public List<String> defaultBiomes() {
        return defaultBiomes.stream().map(b -> b.location().toString()).toList();
    }

    public List<String> defaultStructures() {
        return defaultStructures;
    }

    public List<String> mobRemovals() {
        return this.mobRemovals;
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

    //Here we are accounting for both the default blanket removals (defined above)
    //and all redistributed mods *except* those that have been moved into this biome region.
    public TagKey<EntityType<?>> getMobRemovals() {
        List<String> allRedistributedMobs = new ArrayList<>(PEMob.isRedistributed());
        this.defaultBiomes.forEach(b -> {
            allRedistributedMobs.removeAll(PEMob.isRedistributed().stream()
                .filter(e -> b.entityAdditions().contains(e))
                .toList());
        });
        this.mobRemovals.addAll(allRedistributedMobs);
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID,
                "is_redistributed/" + name().toLowerCase() + "_removals");
        return ProjectEvergreen.createTag(Registries.ENTITY_TYPE, location);
    }
}
