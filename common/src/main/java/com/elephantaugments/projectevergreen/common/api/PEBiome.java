package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultBiomeTags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFeatureBiomes;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultFlags;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultMobBiomes;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.*;
import java.util.stream.Collectors;

public enum PEBiome {
    NO_BIOMES(
        List.of("project_evergreen:empty"),
        List.of("project_evergreen:empty"),
        List.of("project_evergreen:empty")
    ),
    DESERT_DUNES_ALIVE(
        DefaultBiomeTags.desertDunesAlive,
        DefaultMobBiomes.desertDunesAlive,
        DefaultFeatureBiomes.desertDunesAlive
    ),
    DESERT_DUNES_BARREN(
        DefaultBiomeTags.desertDunesBarren,
        DefaultMobBiomes.desertDunesBarren,
        DefaultFeatureBiomes.desertDunesBarren
    ),
    DESERT_RED_ALIVE(
        DefaultBiomeTags.desertRedAlive,
        DefaultMobBiomes.desertRedAlive,
        DefaultFeatureBiomes.desertRedAlive
    ),
    DESERT_RED_BARREN(
        DefaultBiomeTags.desertRedBarren,
        DefaultMobBiomes.desertRedBarren,
        DefaultFeatureBiomes.desertRedBarren
    ),
    FOREST_DENSE_CONIFEROUS_SNOW(
        DefaultBiomeTags.forestDenseConiferousSnow,
        DefaultMobBiomes.forestDenseConiferousSnow,
        DefaultFeatureBiomes.forestDenseConiferousSnow
    ),
    FOREST_DENSE_CONIFEROUS(
        DefaultBiomeTags.forestDenseConiferous,
        DefaultMobBiomes.forestDenseConiferous,
        DefaultFeatureBiomes.forestDenseConiferous
    ),
    FOREST_DENSE_DECIDUOUS(
        DefaultBiomeTags.forestDenseDeciduous,
        DefaultMobBiomes.forestDenseDeciduous,
        DefaultFeatureBiomes.forestDenseDeciduous
    ),
    FOREST_DENSE_TROPICAL(
        DefaultBiomeTags.forestDenseTropical,
        DefaultMobBiomes.forestDenseTropical,
        DefaultFeatureBiomes.forestDenseTropical
    ),
    FOREST_SPARSE_CONIFEROUS_SNOW(
        DefaultBiomeTags.forestSparseConiferousSnow,
        DefaultMobBiomes.forestSparseConiferousSnow,
        DefaultFeatureBiomes.forestSparseConiferousSnow
    ),
    FOREST_SPARSE_CONIFEROUS(
        DefaultBiomeTags.forestSparseConiferous,
        DefaultMobBiomes.forestSparseConiferous,
        DefaultFeatureBiomes.forestSparseConiferous
    ),
    FOREST_SPARSE_DECIDUOUS(
        DefaultBiomeTags.forestSparseDeciduous,
        DefaultMobBiomes.forestSparseDeciduous,
        DefaultFeatureBiomes.forestSparseDeciduous
    ),
    FOREST_SPARSE_TROPICAL(
        DefaultBiomeTags.forestSparseTropical,
        DefaultMobBiomes.forestSparseTropical,
        DefaultFeatureBiomes.forestSparseTropical
    ),
    MOUNTAINS_ALIVE(
        DefaultBiomeTags.mountainsAlive,
        DefaultMobBiomes.mountainsAlive,
        DefaultFeatureBiomes.mountainsAlive
    ),
    MOUNTAINS_BARREN(
        DefaultBiomeTags.mountainsBarren,
        DefaultMobBiomes.mountainsBarren,
        DefaultFeatureBiomes.mountainsBarren
    ),
    MOUNTAINS_COLD(
        DefaultBiomeTags.mountainsCold,
        DefaultMobBiomes.mountainsCold,
        DefaultFeatureBiomes.mountainsCold
    ),
    MOUNTAINS_HOT(
        DefaultBiomeTags.mountainsHot,
        DefaultMobBiomes.mountainsHot,
        DefaultFeatureBiomes.mountainsHot
    ),
    OCEAN_DEEP_FROZEN(
        DefaultBiomeTags.oceanDeepFrozen,
        DefaultMobBiomes.oceanDeepFrozen,
        DefaultFeatureBiomes.oceanDeepFrozen
    ),
    OCEAN_DEEP_TEMPERATE(
        DefaultBiomeTags.oceanDeepTemperate,
        DefaultMobBiomes.oceanDeepTemperate,
        DefaultFeatureBiomes.oceanDeepTemperate
    ),
    OCEAN_DEEP_WARM(
        DefaultBiomeTags.oceanDeepWarm,
        DefaultMobBiomes.oceanDeepWarm,
        DefaultFeatureBiomes.oceanDeepWarm
    ),
    OCEAN_DEEP_RARE(
        DefaultBiomeTags.oceanDeepRare,
        DefaultMobBiomes.oceanDeepRare,
        DefaultFeatureBiomes.oceanDeepRare
    ),
    OCEAN_SHALLOW_FROZEN(
        DefaultBiomeTags.oceanShallowFrozen,
        DefaultMobBiomes.oceanShallowFrozen,
        DefaultFeatureBiomes.oceanShallowFrozen
    ),
    OCEAN_SHALLOW_TEMPERATE(
        DefaultBiomeTags.oceanShallowTemperate,
        DefaultMobBiomes.oceanShallowTemperate,
        DefaultFeatureBiomes.oceanShallowTemperate
    ),
    OCEAN_SHALLOW_WARM(
        DefaultBiomeTags.oceanShallowWarm,
        DefaultMobBiomes.oceanShallowWarm,
        DefaultFeatureBiomes.oceanShallowWarm
    ),
    OCEAN_SHALLOW_RARE(
        DefaultBiomeTags.oceanShallowRare,
        DefaultMobBiomes.oceanShallowRare,
        DefaultFeatureBiomes.oceanShallowRare
    ),
    COASTAL_FROZEN(
        DefaultBiomeTags.coastalFrozen,
        DefaultMobBiomes.coastalFrozen,
        DefaultFeatureBiomes.coastalFrozen
    ),
    COASTAL_TEMPERATE(
        DefaultBiomeTags.coastalTemperate,
        DefaultMobBiomes.coastalTemperate,
        DefaultFeatureBiomes.coastalTemperate
    ),
    COASTAL_WARM(
        DefaultBiomeTags.coastalWarm,
        DefaultMobBiomes.coastalWarm,
        DefaultFeatureBiomes.coastalWarm
    ),
    COASTAL_ALIVE(
        DefaultBiomeTags.coastalAlive,
        DefaultMobBiomes.coastalAlive,
        DefaultFeatureBiomes.coastalAlive
    ),
    COASTAL_BARREN(
        DefaultBiomeTags.coastalBarren,
        DefaultMobBiomes.coastalBarren,
        DefaultFeatureBiomes.coastalBarren
    ),
    RIVER_FROZEN(
        DefaultBiomeTags.riverFrozen,
        DefaultMobBiomes.riverFrozen,
        DefaultFeatureBiomes.riverFrozen
    ),
    RIVER_TEMPERATE(
        DefaultBiomeTags.riverTemperate,
        DefaultMobBiomes.riverTemperate,
        DefaultFeatureBiomes.riverTemperate
    ),
    RIVER_WARM(
        DefaultBiomeTags.riverWarm,
        DefaultMobBiomes.riverWarm,
        DefaultFeatureBiomes.riverWarm
    ),
    PLAINS_GRASSY_ARID(
        DefaultBiomeTags.plainsGrassyArid,
        DefaultMobBiomes.plainsGrassyArid,
        DefaultFeatureBiomes.plainsGrassyArid
    ),
    PLAINS_GRASSY_SNOW(
        DefaultBiomeTags.plainsGrassySnow,
        DefaultMobBiomes.plainsGrassySnow,
        DefaultFeatureBiomes.plainsGrassySnow
    ),
    PLAINS_GRASSY_TEMPERATE(
        DefaultBiomeTags.plainsGrassyTemperate,
        DefaultMobBiomes.plainsGrassyTemperate,
        DefaultFeatureBiomes.plainsGrassyTemperate
    ),
    PLAINS_GRASSY_TROPICAL(
        DefaultBiomeTags.plainsGrassyTropical,
        DefaultMobBiomes.plainsGrassyTropical,
        DefaultFeatureBiomes.plainsGrassyTropical
    ),
    PLAINS_SHRUBBY_ARID(
        DefaultBiomeTags.plainsShrubbyArid,
        DefaultMobBiomes.plainsShrubbyArid,
        DefaultFeatureBiomes.plainsShrubbyArid
    ),
    PLAINS_SHRUBBY_SNOW(
        DefaultBiomeTags.plainsShrubbySnow,
        DefaultMobBiomes.plainsShrubbySnow,
        DefaultFeatureBiomes.plainsShrubbySnow
    ),
    PLAINS_SHRUBBY_TEMPERATE(
        DefaultBiomeTags.plainsShrubbyTemperate,
        DefaultMobBiomes.plainsShrubbyTemperate,
        DefaultFeatureBiomes.plainsShrubbyTemperate
    ),
    PLAINS_SHRUBBY_TROPICAL(
        DefaultBiomeTags.plainsShrubbyTropical,
        DefaultMobBiomes.plainsShrubbyTropical,
        DefaultFeatureBiomes.plainsShrubbyTropical
    ),
    SPECIAL_AUTUMNAL_FIELDS(
        DefaultBiomeTags.specialAutumnalFields,
        DefaultMobBiomes.specialAutumnalFields,
        DefaultFeatureBiomes.specialAutumnalFields
    ),
    SPECIAL_AUTUMNAL_FOREST(
        DefaultBiomeTags.specialAutumnalForest,
        DefaultMobBiomes.specialAutumnalForest,
        DefaultFeatureBiomes.specialAutumnalForest
    ),
    SPECIAL_CRAGGY_SNOW(
        DefaultBiomeTags.specialCraggySnow,
        DefaultMobBiomes.specialCraggySnow,
        DefaultFeatureBiomes.specialCraggySnow
    ),
    SPECIAL_CRAGGY_TEMPERATE(
        DefaultBiomeTags.specialCraggyTemperate,
        DefaultMobBiomes.specialCraggyTemperate,
        DefaultFeatureBiomes.specialCraggyTemperate
    ),
    SPECIAL_CRAGGY_WARM(
        DefaultBiomeTags.specialCraggyWarm,
        DefaultMobBiomes.specialCraggyWarm,
        DefaultFeatureBiomes.specialCraggyWarm
    ),
    SPECIAL_FLOWERY_FIELDS(
        DefaultBiomeTags.specialFloweryFields,
        DefaultMobBiomes.specialFloweryFields,
        DefaultFeatureBiomes.specialFloweryFields
    ),
    SPECIAL_FLOWERY_FOREST(
        DefaultBiomeTags.specialFloweryForest,
        DefaultMobBiomes.specialFloweryForest,
        DefaultFeatureBiomes.specialFloweryForest
    ),
    SPECIAL_MEDITERRANEAN_INVITING(
        DefaultBiomeTags.specialMediterraneanInviting,
        DefaultMobBiomes.specialMediterraneanInviting,
        DefaultFeatureBiomes.specialMediterraneanInviting
    ),
    SPECIAL_MEDITERRANEAN_UNINVITING(
        DefaultBiomeTags.specialMediterraneanUninviting,
        DefaultMobBiomes.specialMediterraneanUninviting,
        DefaultFeatureBiomes.specialMediterraneanUninviting
    ),
    SPECIAL_ORIENTAL_INVITING(
        DefaultBiomeTags.specialOrientalInviting,
        DefaultMobBiomes.specialOrientalInviting,
        DefaultFeatureBiomes.specialOrientalInviting
    ),
    SPECIAL_ORIENTAL_UNINVITING(
        DefaultBiomeTags.specialOrientalUninviting,
        DefaultMobBiomes.specialOrientalUninviting,
        DefaultFeatureBiomes.specialOrientalUninviting
    ),
    SPECIAL_ICY(
        DefaultBiomeTags.specialIcy,
        DefaultMobBiomes.specialIcy,
        DefaultFeatureBiomes.specialIcy
    ),
    SPECIAL_MAGICAL(
        DefaultBiomeTags.specialMagical,
        DefaultMobBiomes.specialMagical,
        DefaultFeatureBiomes.specialMagical
    ),
    SPECIAL_ROCKY(
        DefaultBiomeTags.specialRocky,
        DefaultMobBiomes.specialRocky,
        DefaultFeatureBiomes.specialRocky
    ),
    SPECIAL_RUINED(
        DefaultBiomeTags.specialRuined,
        DefaultMobBiomes.specialRuined,
        DefaultFeatureBiomes.specialRuined
    ),
    SPECIAL_SHROOMY(
        DefaultBiomeTags.specialShroomy,
        DefaultMobBiomes.specialShroomy,
        DefaultFeatureBiomes.specialShroomy
    ),
    SPECIAL_SPOOKY(
        DefaultBiomeTags.specialSpooky,
        DefaultMobBiomes.specialSpooky,
        DefaultFeatureBiomes.specialSpooky
    ),
    SPECIAL_SWAMPY_SNOW(
        DefaultBiomeTags.specialSwampySnow,
        DefaultMobBiomes.specialSwampySnow,
        DefaultFeatureBiomes.specialSwampySnow
    ),
    SPECIAL_SWAMPY_TEMPERATE(
        DefaultBiomeTags.specialSwampyTemperate,
        DefaultMobBiomes.specialSwampyTemperate,
        DefaultFeatureBiomes.specialSwampyTemperate
    ),
    SPECIAL_SWAMPY_WARM(
        DefaultBiomeTags.specialSwampyWarm,
        DefaultMobBiomes.specialSwampyWarm,
        DefaultFeatureBiomes.specialSwampyWarm
    ),
    CULTIVATED_FIELDS(
        DefaultBiomeTags.cultivatedFields,
        DefaultMobBiomes.plainsGrassyTemperate,
        DefaultFeatureBiomes.cultivatedFields
    ),
    BIRCH_FOREST(
        DefaultBiomeTags.birchForest,
        DefaultMobBiomes.forestSparseDeciduous,
        DefaultFeatureBiomes.birchForest
    ),
    CHERRY_FOREST(
        DefaultBiomeTags.cherryForest,
        DefaultMobBiomes.specialOrientalInviting,
        DefaultFeatureBiomes.cherryForest
    ),
    FROZEN_CAVES(
        DefaultBiomeTags.frozenCaves,
        DefaultMobBiomes.specialIcy,
        DefaultFeatureBiomes.frozenCaves
    ),
    SANDY_CAVES(
        DefaultBiomeTags.sandyCaves,
        DefaultMobBiomes.specialMagical,
        DefaultFeatureBiomes.sandyCaves
    ),
    ICY_VOLCANO(
        DefaultBiomeTags.icyVolcano,
        DefaultMobBiomes.specialIcy,
        DefaultFeatureBiomes.icyVolcano
    ),
    TROPICAL_VOLCANO(
        DefaultBiomeTags.tropicalVolcano,
        DefaultMobBiomes.mountainsHot,
        DefaultFeatureBiomes.tropicalVolcano
    ),
    TROPICAL_ISLAND(
        DefaultBiomeTags.tropicalIsland,
        DefaultMobBiomes.plainsShrubbyTropical,
        DefaultFeatureBiomes.tropicalCove
    ),
    VOLCANIC_CRATER(
        DefaultBiomeTags.volcanicCrater,
        DefaultMobBiomes.mountainsHot,
        DefaultFeatureBiomes.volcanicCrater
    ),
    RED_FLOWER_FIELDS(
        DefaultBiomeTags.redFlowerFields,
        DefaultMobBiomes.specialMediterraneanUninviting,
        DefaultFeatureBiomes.redFlowerFields
    ),
    PALE_GARDEN(
        DefaultBiomeTags.paleGarden,
        DefaultMobBiomes.specialSpooky,
        DefaultFeatureBiomes.paleGarden
    ),
    SOUL_VALLEY(
        DefaultBiomeTags.soulValley,
        ImmutableList.of(
            "born_in_chaos_v1:pumpkin_bomb",
            "born_in_chaos_v1:pumpkin_bruiser",
            "born_in_chaos_v1:pumpkin_dunce"
        ),
        DefaultFeatureBiomes.soulValley
    ),
    STARLIT_GRASSY(
        DefaultBiomeTags.starlitGrassy,
        ImmutableList.of(
            "born_in_chaos_v1:fallen_chaos_knight",
            "born_in_chaos_v1:fallen_chaos_knight",
            "born_in_chaos_v1:fallen_chaos_knight",
            "born_in_chaos_v1:nightmare_stalker",
            "born_in_chaos_v1:spiritof_chaos"
        ),
        DefaultFeatureBiomes.starlitGrassy
    ),
    STARLIT_SEA(
        DefaultBiomeTags.starlitSea,
        ImmutableList.of(
            "born_in_chaos_v1:corpse_fish",
            "born_in_chaos_v1:corpse_fish",
            "born_in_chaos_v1:corpse_fish"
        ),
        DefaultFeatureBiomes.starlitSea
    ),
    STARLIT_FOREST(
        DefaultBiomeTags.starlitForest,
        ImmutableList.of(
            "born_in_chaos_v1:scarlet_persecutor",
            "born_in_chaos_v1:scarlet_persecutor"
        ),
        DefaultFeatureBiomes.starlitForest
    ),
    STARLIT_DESERT(
        DefaultBiomeTags.starlitDesert,
        ImmutableList.of(
            "born_in_chaos_v1:dark_vortex",
            "born_in_chaos_v1:dark_vortex",
            "born_in_chaos_v1:dark_vortex",
            "born_in_chaos_v1:lifestealer"
        ),
        DefaultFeatureBiomes.starlitDesert
    ),
    STARLIT_SWAMP(
        DefaultBiomeTags.starlitSwamp,
        ImmutableList.of(
            "born_in_chaos_v1:mother_spider",
            "born_in_chaos_v1:baby_spider",
            "born_in_chaos_v1:baby_spider",
            "born_in_chaos_v1:baby_spider"
        ),
        DefaultFeatureBiomes.starlitSwamp
    ),
    STARLIT_PERMAFROST(
        DefaultBiomeTags.starlitPermafrost,
        ImmutableList.of(
            "born_in_chaos_v1:krampus_henchman"
        ),
        DefaultFeatureBiomes.starlitPermafrost
    ),
    STARLIT_LUSH_SEA(
        DefaultBiomeTags.starlitLushSea,
        Constants.EMPTY_LIST,
        DefaultFeatureBiomes.starlitLushSea
    ),
    STARLIT_ABYSS(
        DefaultBiomeTags.starlitAbyss,
        ImmutableList.of(
            "born_in_chaos_v1:glutton_fish"
        ),
        DefaultFeatureBiomes.starlitAbyss
    ),
    UNDERGROUND(
        DefaultBiomeTags.underground,
        DefaultMobBiomes.underground,
        DefaultFeatureBiomes.underground
    );

    private List<String> defaultBiomes = new ArrayList<>();
    private List<String> entityAdditions = new ArrayList<>();
    private List<String> featureAdditions = new ArrayList<>();

    private final String jsonKey = Constants.JsonProp.BIOME.jsonKey();
    private final String jsonPath = Constants.JsonProp.BIOME.jsonPath();
    private final String tagKey;
    private final String path;
    private final ResourceLocation location;
    private TagKey<Biome> tag;
    private TagKey<PlacedFeature> featureTag;
    private TagKey<EntityType<?>> entityTag;

    PEBiome(List<String> defaultBiomes,
            List<String> entityAdditions,
            List<String> featureAdditions
    ) {
        path = "is_climate/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        this.tagKey = "#" + location;
        this.tag = ProjectEvergreen.createTag(Registries.BIOME, location);
        this.featureTag = ProjectEvergreen.createTag(Registries.PLACED_FEATURE, location);
        this.entityTag = ProjectEvergreen.createTag(Registries.ENTITY_TYPE, location);
        this.defaultBiomes.addAll(defaultBiomes);
        this.entityAdditions.addAll(entityAdditions);
        this.featureAdditions.addAll(featureAdditions);
    }

    public ResourceLocation location() {
        return this.location;
    }

    public String jsonKey() {
        return jsonKey;
    }

    public String jsonPath() {
        return jsonPath;
    }

    public String tagKey() {
        return this.tagKey;
    }

    public TagKey<Biome> tag() {
        return this.tag;
    }

    public TagKey<PlacedFeature> featureTag() {
        return this.featureTag;
    }

    public TagKey<EntityType<?>> entityTag() {
        return this.entityTag;
    }

    public List<String> entityAdditions() {
        return entityAdditions;
    }

    public List<String> featureAdditions() {
        return featureAdditions;
    }

    public List<String> defaultBiomes() {
        return defaultBiomes;
    }

    public static List<String> allTemperate() {
        HashSet<String> tempBiomes = Arrays.stream(PEBiome.values())
                .filter(r -> r.name().contains("TEMPERATE") ||
                        r.name().contains("FLOWERY") ||
                        r.name().contains("MEDITERRANEAN") ||
                        r.name().contains("ORIENTAL") ||
                        r.name().contains("DECIDUOUS"))
                .map(PEBiome::defaultBiomes)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return tempBiomes.stream().toList();
    }

    public static List<String> allWarm() {
        HashSet<String> warmBiomes = Arrays.stream(PEBiome.values())
                .filter(r -> r.name().contains("WARM") ||
                        r.name().contains("TROPICAL") ||
                        r.name().contains("DESERT") ||
                        r.name().contains("ARID"))
                .map(PEBiome::defaultBiomes)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return warmBiomes.stream().toList();
    }

    public static List<String> redistributedFeatures() {
        HashSet<String> redistributedFeatures = Arrays.stream(PEBiome.values())
                .map(PEBiome::featureAdditions)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(f -> !f.isModifier() &&
                        redistributedFeatures.contains(f.id))
                .map(PatchableFeature::getId)
                .toList();
    }

    public static List<String> redistributedModifiers() {
        HashSet<String> redistributedFeatures = Arrays.stream(PEBiome.values())
                .map(PEBiome::featureAdditions)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));
        return WorldgenDataManager.PATCHABLE_FEATURES.values().stream()
                .filter(f -> f.isModifier() &&
                        redistributedFeatures.contains(f.id))
                .map(PatchableFeature::getId)
                .toList();
    }

    public enum Flag {
        PATCHABLE(new PatchableBiomes().getIDs().stream().toList()),
        HAS_SPAWN_OVERRIDES(Constants.EMPTY_LIST),
        REMOVE_SNOW(DefaultFlags.removeSnow),
        REMOVE_VEGETATION_COLOR(DefaultFlags.removeBiomeVegetationColor),
        IS_MEADOW_BLUE(DefaultFlags.addMeadowGrassColor),
        IS_MARSH_GREEN(DefaultFlags.addMarshVegetationColor),
        IS_BAYOU_BLUE(DefaultFlags.addBayouVegetationColor),
        IS_COOL_GREEN(DefaultFlags.addCoolPlainsVegetationColor),
        IS_PLAINS_GREEN(DefaultFlags.addPlainsVegetationColor),
        IS_STEPPE_BROWN(DefaultFlags.addSteppeGrassColor),
        IS_PRAIRIE_YELLOW(DefaultFlags.addPrairieGrassColor),
        IS_SAVANNA_BROWN(DefaultFlags.addSavannaVegetationColor),
        IS_TEMPERATE(PEBiome.allTemperate()),
        IS_WARM(PEBiome.allWarm());

        private final String jsonKey;
        private final String jsonPath;
        private final String path;
        private final ResourceLocation location;
        private String tagKey;
        private TagKey<Biome> tag;
        private List<String> defaultIDs;

        Flag(List<String> defaultIDs) {
            jsonKey = name().toLowerCase();
            jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
            path = name().contains("_") ? ("is_flagged/" + name().toLowerCase()) : name().toLowerCase();
            location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
            this.tagKey = "#" + location;
            this.tag = ProjectEvergreen.createTag(Registries.BIOME, location);
            this.defaultIDs = defaultIDs;
        }

        public String jsonKey() {
            return jsonKey;
        }

        public String jsonPath() {
            return jsonPath;
        }

        public String tagKey() {
            return this.tagKey;
        }

        public TagKey<Biome> tag() {
            return this.tag;
        }

        public List<String> defaultIDs() {
            return defaultIDs;
        }
    }
}
