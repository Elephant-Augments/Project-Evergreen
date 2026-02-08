package com.elephantaugments.projectevergreen.common.api;

import com.elephantaugments.projectevergreen.common.Constants;
import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.defaults.DefaultBiomeTags;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableBiomes;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public enum PEBiome {
    NO_BIOMES(List.of("project_evergreen:empty")),
    DESERT_DUNES_ALIVE(DefaultBiomeTags.desertDunesAlive),
    DESERT_DUNES_BARREN(DefaultBiomeTags.desertDunesBarren),
    DESERT_RED_ALIVE(DefaultBiomeTags.desertRedAlive),
    DESERT_RED_BARREN(DefaultBiomeTags.desertRedBarren),
    FOREST_DENSE_CONIFEROUS_SNOW(DefaultBiomeTags.forestDenseConiferousSnow),
    FOREST_DENSE_CONIFEROUS(DefaultBiomeTags.forestDenseConiferous),
    FOREST_DENSE_DECIDUOUS(DefaultBiomeTags.forestDenseDeciduous),
    FOREST_DENSE_TROPICAL(DefaultBiomeTags.forestDenseTropical),
    FOREST_SPARSE_CONIFEROUS_SNOW(DefaultBiomeTags.forestSparseConiferousSnow),
    FOREST_SPARSE_CONIFEROUS(DefaultBiomeTags.forestSparseConiferous),
    FOREST_SPARSE_DECIDUOUS(DefaultBiomeTags.forestSparseDeciduous),
    FOREST_SPARSE_TROPICAL(DefaultBiomeTags.forestSparseTropical),
    MOUNTAINS_ALIVE(DefaultBiomeTags.mountainsAlive),
    MOUNTAINS_BARREN(DefaultBiomeTags.mountainsBarren),
    MOUNTAINS_COLD(DefaultBiomeTags.mountainsCold),
    MOUNTAINS_HOT(DefaultBiomeTags.mountainsHot),
    OCEAN_DEEP_FROZEN(DefaultBiomeTags.oceanDeepFrozen),
    OCEAN_DEEP_TEMPERATE(DefaultBiomeTags.oceanDeepTemperate),
    OCEAN_DEEP_WARM(DefaultBiomeTags.oceanDeepWarm),
    OCEAN_DEEP_RARE(DefaultBiomeTags.oceanDeepRare),
    OCEAN_SHALLOW_FROZEN(DefaultBiomeTags.oceanShallowFrozen),
    OCEAN_SHALLOW_TEMPERATE(DefaultBiomeTags.oceanShallowTemperate),
    OCEAN_SHALLOW_WARM(DefaultBiomeTags.oceanShallowWarm),
    OCEAN_SHALLOW_RARE(DefaultBiomeTags.oceanShallowRare),
    COASTAL_FROZEN(DefaultBiomeTags.coastalFrozen),
    COASTAL_TEMPERATE(DefaultBiomeTags.coastalTemperate),
    COASTAL_WARM(DefaultBiomeTags.coastalWarm),
    COASTAL_ALIVE(DefaultBiomeTags.coastalAlive),
    COASTAL_BARREN(DefaultBiomeTags.coastalBarren),
    RIVER_FROZEN(DefaultBiomeTags.riverFrozen),
    RIVER_TEMPERATE(DefaultBiomeTags.riverTemperate),
    RIVER_WARM(DefaultBiomeTags.riverWarm),
    PLAINS_GRASSY_ARID(DefaultBiomeTags.plainsGrassyArid),
    PLAINS_GRASSY_SNOW(DefaultBiomeTags.plainsGrassySnow),
    PLAINS_GRASSY_TEMPERATE(DefaultBiomeTags.plainsGrassyTemperate),
    PLAINS_GRASSY_TROPICAL(DefaultBiomeTags.plainsGrassyTropical),
    PLAINS_SHRUBBY_ARID(DefaultBiomeTags.plainsShrubbyArid),
    PLAINS_SHRUBBY_SNOW(DefaultBiomeTags.plainsShrubbySnow),
    PLAINS_SHRUBBY_TEMPERATE(DefaultBiomeTags.plainsShrubbyTemperate),
    PLAINS_SHRUBBY_TROPICAL(DefaultBiomeTags.plainsShrubbyTropical),
    SPECIAL_AUTUMNAL_FIELDS(DefaultBiomeTags.specialAutumnalFields),
    SPECIAL_AUTUMNAL_FOREST(DefaultBiomeTags.specialAutumnalForest),
    SPECIAL_CRAGGY_SNOW(DefaultBiomeTags.specialCraggySnow),
    SPECIAL_CRAGGY_TEMPERATE(DefaultBiomeTags.specialCraggyTemperate),
    SPECIAL_CRAGGY_WARM(DefaultBiomeTags.specialCraggyWarm),
    SPECIAL_FLOWERY_FIELDS(DefaultBiomeTags.specialFloweryFields),
    SPECIAL_FLOWERY_FOREST(DefaultBiomeTags.specialFloweryForest),
    SPECIAL_MEDITERRANEAN_INVITING(DefaultBiomeTags.specialMediterraneanInviting),
    SPECIAL_MEDITERRANEAN_UNINVITING(DefaultBiomeTags.specialMediterraneanUninviting),
    SPECIAL_ORIENTAL_INVITING(DefaultBiomeTags.specialOrientalInviting),
    SPECIAL_ORIENTAL_UNINVITING(DefaultBiomeTags.specialOrientalUninviting),
    SPECIAL_ICY(DefaultBiomeTags.specialIcy),
    SPECIAL_MAGICAL(DefaultBiomeTags.specialMagical),
    SPECIAL_ROCKY(DefaultBiomeTags.specialRocky),
    SPECIAL_RUINED(DefaultBiomeTags.specialRuined),
    SPECIAL_SHROOMY(DefaultBiomeTags.specialShroomy),
    SPECIAL_SPOOKY(DefaultBiomeTags.specialSpooky),
    SPECIAL_SWAMPY_SNOW(DefaultBiomeTags.specialSwampySnow),
    SPECIAL_SWAMPY_TEMPERATE(DefaultBiomeTags.specialSwampyTemperate),
    SPECIAL_SWAMPY_WARM(DefaultBiomeTags.specialSwampyWarm);

    private final String jsonKey = "biome";
    private final String jsonPath = "/" + Constants.PROPERTIES_KEY + "/" + jsonKey;
    private final String tagKey;
    private final String path;
    private final ResourceLocation location;
    private TagKey<Biome> tag;
    private List<String> defaultSet;

    PEBiome(List<String> defaultSet) {
        path = "is_climate/" + name().toLowerCase();
        location = ResourceLocation.fromNamespaceAndPath(ProjectEvergreen.MODID, path);
        this.tag = ProjectEvergreen.createTag(Registries.BIOME, location);
        this.tagKey = "#" + location;
        this.defaultSet = defaultSet;
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

    public List<String> defaultTags() {
        return defaultSet;
    }

    public enum Flag {
        PATCHABLE(new PatchableBiomes().getIDs().stream().toList());

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
