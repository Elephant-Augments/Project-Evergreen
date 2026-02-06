package com.elephantaugments.projectevergreen.common.data.defaults;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.api.PERegion_OLD;
import com.google.common.collect.ImmutableList;

import java.util.LinkedHashMap;
import java.util.List;

public class DefaultRegions {

	public static final String NO_BIOMES = "#project_evergreen:zno_biomes";
	public static final String OVERWORLD_BIOMES = "#minecraft:is_overworld";
	public static final String AETHER_BIOMES = "#aether:is_aether";
	public static final String NETHER_BIOMES = "#minecraft:is_nether";
	public static final String AFTERDARK_BIOMES = "#the_afterdark:afterdark_biomes";
	public static final String END_BIOMES = "#minecraft:is_end";

	public static final String ALL_OCEAN = "#project_evergreen:is_region/all_ocean";
	public static final String ALL_RIVERS = "#project_evergreen:is_region/all_rivers";
	public static final String ALL_CIVILIZATION = "#project_evergreen:is_region/all_civilization";
	public static final String ALL_WILDERNESS = "#project_evergreen:is_region/all_wilderness";
	public static final String ALL_UNDERGROUND_LAND = "#project_evergreen:is_region/all_aunderground_land";
	public static final String CIVILIZATION_TEMPERATE = "#project_evergreen:is_region/civilization_temperate";
	public static final String CIVILIZATION_FIELDS = "#project_evergreen:is_region/civilization_fields";
	public static final String CIVILIZATION_CONIFEROUS = "#project_evergreen:is_region/civilization_coniferous";
	public static final String CIVILIZATION_DECIDUOUS = "#project_evergreen:is_region/civilization_deciduous";
	public static final String CIVILIZATION_ARID = "#project_evergreen:is_region/civilization_arid";
	public static final String CIVILIZATION_DESERT = "#project_evergreen:is_region/civilization_desert";
	public static final String CIVILIZATION_DESERT_RED = "#project_evergreen:is_region/civilization_desert_red";
	public static final String CIVILIZATION_TROPICAL = "#project_evergreen:is_region/civilization_tropical";
	public static final String CIVILIZATION_SNOW = "#project_evergreen:is_region/civilization_snow";
	public static final String CIVILIZATION_SPECIAL_AUTUMNAL = "#project_evergreen:is_region/civilization_special_autumnal";
	public static final String CIVILIZATION_SPECIAL_COASTAL = "#project_evergreen:is_region/civilization_special_coastal";
	public static final String CIVILIZATION_SPECIAL_FLOWERY = "#project_evergreen:is_region/civilization_special_flowery";
	public static final String CIVILIZATION_SPECIAL_ORIENTAL = "#project_evergreen:is_region/civilization_special_oriental";
	public static final String WILDERNESS_TEMPERATE = "#project_evergreen:is_region/wilderness_temperate";
	public static final String WILDERNESS_FIELDS = "#project_evergreen:is_region/wilderness_fields";
	public static final String WILDERNESS_CONIFEROUS = "#project_evergreen:is_region/wilderness_coniferous";
	public static final String WILDERNESS_DECIDUOUS = "#project_evergreen:is_region/wilderness_deciduous";
	public static final String WILDERNESS_ARID = "#project_evergreen:is_region/wilderness_arid";
	public static final String WILDERNESS_DESERT = "#project_evergreen:is_region/wilderness_desert";
	public static final String WILDERNESS_DESERT_RED = "#project_evergreen:is_region/wilderness_desert_red";
	public static final String WILDERNESS_TROPICAL = "#project_evergreen:is_region/wilderness_tropical";
	public static final String WILDERNESS_SNOW = "#project_evergreen:is_region/wilderness_snow";
	public static final String WILDERNESS_SPECIAL_AUTUMNAL = "#project_evergreen:is_region/wilderness_special_autumnal";
	public static final String WILDERNESS_SPECIAL_COASTAL = "#project_evergreen:is_region/wilderness_special_coastal";
	public static final String WILDERNESS_SPECIAL_ORIENTAL = "#project_evergreen:is_region/wilderness_special_oriental";
	public static final String OCEAN_DEEP = "#project_evergreen:is_region/all_ocean_deep";
	public static final String ALL_COASTAL = "#project_evergreen:is_region/all_coast";
	public static final String COASTAL_FROZEN = "#project_evergreen:coastal_frozen";
	public static final String COASTAL_TEMPERATE = "#project_evergreen:coastal_temperate";
	public static final String COASTAL_WARM = "#project_evergreen:coastal_warm";
	public static final String OCEAN_SHALLOW = "#project_evergreen:is_region/all_ocean_shallow";
	public static final String OCEAN_NOT_FROZEN = "#project_evergreen:is_region/ocean_not_frozen";
	public static final String OCEAN_FROZEN = "#project_evergreen:is_region/ocean_frozen";
	public static final String OCEAN_TEMPERATE = "#project_evergreen:is_region/ocean_temperate";
	public static final String OCEAN_WARM = "#project_evergreen:is_region/ocean_warm";
	public static final String OCEAN_DEEP_FROZEN = "#project_evergreen:ocean_deep_frozen";
	public static final String OCEAN_DEEP_TEMPERATE = "#project_evergreen:ocean_deep_temperate";
	public static final String OCEAN_DEEP_WARM = "#project_evergreen:ocean_deep_warm";
	public static final String OCEAN_SHALLOW_FROZEN = "#project_evergreen:ocean_shallow_frozen";
	public static final String OCEAN_SHALLOW_TEMPERATE = "#project_evergreen:ocean_shallow_temperate";
	public static final String OCEAN_SHALLOW_WARM = "#project_evergreen:ocean_shallow_warm";
	public static final String OCEAN_RARE_SHALLOW = "#project_evergreen:ocean_rare_shallow";
	public static final String OCEAN_RARE_DEEP = "#project_evergreen:ocean_rare_deep";
	public static final String RIVER_OR_COAST_NOT_FROZEN = "#project_evergreen:is_region/rivers_or_coasts_not_frozen";
	public static final String RIVER_OR_COAST_FROZEN = "#project_evergreen:is_region/rivers_or_coasts_frozen";
	public static final String RIVER_NOT_FROZEN = "#project_evergreen:is_region/river_not_frozen";
	public static final String RIVER_FROZEN = "#project_evergreen:river_frozen";
	public static final String RIVER_TEMPERATE = "#project_evergreen:river_temperate";
	public static final String RIVER_WARM = "#project_evergreen:river_warm";
	public static final String SPECIAL_BARREN = "#project_evergreen:is_region/special_barren";
	public static final String SPECIAL_CRAGGY = "#project_evergreen:is_region/special_craggy";
	public static final String SPECIAL_ICY = "#project_evergreen:is_region/special_icy";
	public static final String SPECIAL_MAGICAL = "#project_evergreen:is_region/special_magical";
	public static final String SPECIAL_MEDITERRANEAN = "#project_evergreen:is_region/special_mediterranean";
	public static final String SPECIAL_MOUNTAINOUS_COLD = "#project_evergreen:is_region/special_mountainous_cold";
	public static final String SPECIAL_MOUNTAINOUS_HOT = "#project_evergreen:is_region/special_mountainous_hot";
	public static final String SPECIAL_RUINED = "#project_evergreen:is_region/special_ruined";
	public static final String SPECIAL_SHROOMY = "#project_evergreen:is_region/special_shroomy";
	public static final String SPECIAL_SPOOKY = "#project_evergreen:is_region/special_spooky";
	public static final String SPECIAL_SWAMPY = "#project_evergreen:is_region/special_swampy";
	public static final String SPECIAL_SWAMPY_WARM = "#project_evergreen:special_swampy_warm";

	/*public static final PERegion_OLD noBiomes = ProjectEvergreen.PERegion.NO_BIOMES.initDefault(new String[]{});
	public static final PERegion_OLD allUndergroundLand = ProjectEvergreen.PERegion.ALL_UNDERGROUND_LAND.initDefault(
		new String[] {
			"#project_evergreen:is_region/all_civilization",
			"#project_evergreen:is_region/all_wilderness",
			"#project_evergreen:all_special",
			"#project_evergreen:all_underground"
		}
	);
	public static final PERegion_OLD allOcean = ProjectEvergreen.PERegion.ALL_OCEAN.initDefault(
		new String[] {
			"#project_evergreen:is_region/all_ocean_deep",
			"#project_evergreen:is_region/all_ocean_shallow"
		}
	);
	public static final PERegion_OLD allCoastal = ProjectEvergreen.PERegion.ALL_COASTAL.initDefault(
		new String[] {
			"#project_evergreen:coastal_alive",
			"#project_evergreen:coastal_barren",
			"#project_evergreen:coastal_frozen",
			"#project_evergreen:coastal_temperate",
			"#project_evergreen:coastal_warm"
		}
	);
	public static final PERegion_OLD allRivers = ProjectEvergreen.PERegion.ALL_RIVERS.initDefault(
		new String[] {
			"#project_evergreen:river_frozen",
			"#project_evergreen:river_not_frozen"
		}
	);
	public static final PERegion_OLD allCivilization = ProjectEvergreen.PERegion.ALL_CIVILIZATION.initDefault(
		new String[] {
			"#project_evergreen:is_region/civilization_snow",
			"#project_evergreen:is_region/civilization_arid",
			"#project_evergreen:is_region/civilization_desert",
			"#project_evergreen:is_region/civilization_temperate",
			"#project_evergreen:is_region/civilization_coniferous",
			"#project_evergreen:is_region/civilization_deciduous",
			"#project_evergreen:is_region/civilization_fields",
			"#project_evergreen:is_region/civilization_tropical",
			"#project_evergreen:is_region/civilization_special_autumnal",
			"#project_evergreen:is_region/civilization_special_oriental",
			"#project_evergreen:special_mediterranean_inviting",
			"#project_evergreen:all_special_flowery"
		}
	);
	public static final PERegion_OLD allWilderness = ProjectEvergreen.PERegion.ALL_WILDERNESS.initDefault(
		new String[] {
			"#project_evergreen:is_region/wilderness_snow",
			"#project_evergreen:is_region/wilderness_arid",
			"#project_evergreen:is_region/wilderness_desert",
			"#project_evergreen:is_region/wilderness_temperate",
			"#project_evergreen:is_region/wilderness_deciduous",
			"#project_evergreen:is_region/wilderness_coniferous",
			"#project_evergreen:is_region/wilderness_fields",
			"#project_evergreen:is_region/wilderness_tropical",
			"#project_evergreen:is_region/wilderness_special_autumnal",
			"#project_evergreen:is_region/wilderness_special_oriental",
			"#project_evergreen:special_mediterranean_uninviting",
			"#project_evergreen:all_special_craggy"
		}
	);
	public static final PERegion_OLD civilizationTemperate = ProjectEvergreen.PERegion.CIVILIZATION_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:plains_grassy_temperate",
			"#project_evergreen:forest_sparse_coniferous",
			"#project_evergreen:forest_sparse_deciduous",
			"#project_evergreen:all_special_flowery",
			"#project_evergreen:special_autumnal_fields",
			"#project_evergreen:special_mediterranean_inviting"
		}
	);
	public static final PERegion_OLD civilizationFields = ProjectEvergreen.PERegion.CIVILIZATION_FIELDS.initDefault(
		new String[] {
			"#project_evergreen:plains_grassy_temperate",
			"#project_evergreen:special_flowery_fields",
			"#project_evergreen:special_autumnal_fields"
		}
	);
	public static final PERegion_OLD civilizationConiferous = ProjectEvergreen.PERegion.CIVILIZATION_CONIFEROUS.initDefault(
		new String[] {
			"#project_evergreen:forest_sparse_coniferous_snow",
			"#project_evergreen:forest_sparse_coniferous"
		}
	);
	public static final PERegion_OLD civilizationDeciduous = ProjectEvergreen.PERegion.CIVILIZATION_DECIDUOUS.initDefault(
		new String[] {
			"#project_evergreen:forest_sparse_deciduous",
			"#project_evergreen:special_flowery_forest"
		}
	);
	public static final PERegion_OLD civilizationArid = ProjectEvergreen.PERegion.CIVILIZATION_ARID.initDefault(
		new String[] {
			"#project_evergreen:plains_grassy_arid",
			"#project_evergreen:special_mediterranean_inviting"
		}
	);
	public static final PERegion_OLD civilizationDesert = ProjectEvergreen.PERegion.CIVILIZATION_DESERT.initDefault(
		new String[] {
			"#project_evergreen:desert_dunes_alive"
		}
	);
	public static final PERegion_OLD civilizationDesertRed = ProjectEvergreen.PERegion.CIVILIZATION_DESERT_RED.initDefault(
		new String[] {
			"#project_evergreen:desert_red_alive"
		}
	);
	public static final PERegion_OLD civilizationTropical = ProjectEvergreen.PERegion.CIVILIZATION_TROPICAL.initDefault(
		new String[] {
			"#project_evergreen:plains_grassy_tropical",
			"#project_evergreen:forest_sparse_tropical"
		}
	);
	public static final PERegion_OLD civilizationSnow = ProjectEvergreen.PERegion.CIVILIZATION_SNOW.initDefault(
		new String[] {
			"#project_evergreen:plains_grassy_snow",
			"#project_evergreen:forest_sparse_coniferous_snow"
		}
	);
	public static final PERegion_OLD civilizationSpecialAutumnal = ProjectEvergreen.PERegion.CIVILIZATION_SPECIAL_AUTUMNAL.initDefault(
		new String[] {
			"#project_evergreen:special_autumnal_fields"
		}
	);
	public static final PERegion_OLD civilizationSpecialCoastal = ProjectEvergreen.PERegion.CIVILIZATION_SPECIAL_COASTAL.initDefault(
		new String[] {
			"#project_evergreen:coastal_alive"
		}
	);
	public static final PERegion_OLD civilizationSpecialFlowery = ProjectEvergreen.PERegion.CIVILIZATION_SPECIAL_FLOWERY.initDefault(
		new String[] {
			"#project_evergreen:special_flowery_fields",
			"#project_evergreen:special_flowery_forest"
		}
	);
	public static final PERegion_OLD civilizationSpecialOriental = ProjectEvergreen.PERegion.CIVILIZATION_SPECIAL_ORIENTAL.initDefault(
		new String[] {
			"#project_evergreen:special_oriental_inviting"
		}
	);
	public static final PERegion_OLD wildernessTemperate = ProjectEvergreen.PERegion.WILDERNESS_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:plains_shrubby_temperate",
			"#project_evergreen:forest_dense_coniferous",
			"#project_evergreen:forest_dense_deciduous",
			"#project_evergreen:special_craggy_temperate",
			"#project_evergreen:plains_shrubby_arid",
			"#project_evergreen:special_craggy_warm",
			"#project_evergreen:special_mediterranean_uninviting"
		}
	);
	public static final PERegion_OLD wildernessFields = ProjectEvergreen.PERegion.WILDERNESS_FIELDS.initDefault(
		new String[] {
			"#project_evergreen:plains_shrubby_snow",
			"#project_evergreen:plains_shrubby_temperate",
			"#project_evergreen:plains_shrubby_arid"
		}
	);
	public static final PERegion_OLD wildernessConiferous = ProjectEvergreen.PERegion.WILDERNESS_CONIFEROUS.initDefault(
		new String[] {
			"#project_evergreen:forest_dense_coniferous"
		}
	);
	public static final PERegion_OLD wildernessDeciduous = ProjectEvergreen.PERegion.WILDERNESS_DECIDUOUS.initDefault(
		new String[] {
			"#project_evergreen:forest_dense_deciduous"
		}
	);
	public static final PERegion_OLD wildernessArid = ProjectEvergreen.PERegion.WILDERNESS_ARID.initDefault(
		new String[] {
			"#project_evergreen:plains_shrubby_arid",
			"#project_evergreen:special_craggy_warm",
			"#project_evergreen:special_mediterranean_uninviting"
		}
	);
	public static final PERegion_OLD wildernessDesert = ProjectEvergreen.PERegion.WILDERNESS_DESERT.initDefault(
		new String[] {
			"#project_evergreen:desert_dunes_barren"
		}
	);
	public static final PERegion_OLD wildernessDesertRed = ProjectEvergreen.PERegion.WILDERNESS_DESERT_RED.initDefault(
		new String[] {
			"#project_evergreen:desert_red_barren"
		}
	);
	public static final PERegion_OLD wildernessTropical = ProjectEvergreen.PERegion.WILDERNESS_TROPICAL.initDefault(
		new String[] {
			"#project_evergreen:forest_dense_tropical"
		}
	);
	public static final PERegion_OLD wildernessSnow = ProjectEvergreen.PERegion.WILDERNESS_SNOW.initDefault(
		new String[] {
			"#project_evergreen:plains_shrubby_snow",
			"#project_evergreen:forest_dense_coniferous_snow",
			"#project_evergreen:special_craggy_cold"
		}
	);
	public static final PERegion_OLD wildernessSpecialAutumnal = ProjectEvergreen.PERegion.WILDERNESS_SPECIAL_AUTUMNAL.initDefault(
		new String[] {
			"#project_evergreen:special_autumnal_forest"
		}
	);
	public static final PERegion_OLD wildernessSpecialCoastal = ProjectEvergreen.PERegion.WILDERNESS_SPECIAL_COASTAL.initDefault(
		new String[] {
			"#project_evergreen:coastal_barren"
		}
	);
	public static final PERegion_OLD wildernessSpecialOriental = ProjectEvergreen.PERegion.WILDERNESS_SPECIAL_ORIENTAL.initDefault(
		new String[] {
			"#project_evergreen:special_oriental_uninviting"
		}
	);
	public static final PERegion_OLD coastalFrozen = ProjectEvergreen.PERegion.COASTAL_FROZEN.initDefault(
		new String[] {
				"#project_evergreen:coastal_frozen"
		}
	);
	public static final PERegion_OLD coastalTemperate = ProjectEvergreen.PERegion.COASTAL_TEMPERATE.initDefault(
		new String[] {
				"#project_evergreen:coastal_temperate"
		}
	);
	public static final PERegion_OLD coastalWarm = ProjectEvergreen.PERegion.COASTAL_WARM.initDefault(
		new String[] {
			"#project_evergreen:coastal_warm"
		}
	);
	public static final PERegion_OLD oceanDeep = ProjectEvergreen.PERegion.OCEAN_DEEP.initDefault(
		new String[] {
			"#project_evergreen:ocean_deep_frozen",
			"#project_evergreen:ocean_deep_temperate",
			"#project_evergreen:ocean_deep_warm"
		}
	);
	public static final PERegion_OLD oceanShallow = ProjectEvergreen.PERegion.OCEAN_SHALLOW.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_frozen",
			"#project_evergreen:ocean_shallow_temperate",
			"#project_evergreen:ocean_shallow_warm"
		}
	);
	public static final PERegion_OLD oceanNotFrozen = ProjectEvergreen.PERegion.OCEAN_NOT_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:ocean_deep_warm",
			"#project_evergreen:ocean_deep_temperate",
			"#project_evergreen:ocean_shallow_temperate",
			"#project_evergreen:ocean_shallow_warm"
		}
	);
	public static final PERegion_OLD oceanFrozen = ProjectEvergreen.PERegion.OCEAN_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_frozen",
			"#project_evergreen:ocean_deep_frozen"
		}
	);
	public static final PERegion_OLD oceanTemperate = ProjectEvergreen.PERegion.OCEAN_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_temperate",
			"#project_evergreen:ocean_deep_temperate"
		}
	);
	public static final PERegion_OLD oceanWarm = ProjectEvergreen.PERegion.OCEAN_WARM.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_warm",
			"#project_evergreen:ocean_deep_warm"
		}
	);
	public static final PERegion_OLD oceanDeepFrozen = ProjectEvergreen.PERegion.OCEAN_DEEP_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:ocean_deep_frozen"
		}
	);
	public static final PERegion_OLD oceanDeepTemperate = ProjectEvergreen.PERegion.OCEAN_DEEP_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:ocean_deep_temperate"
		}
	);
	public static final PERegion_OLD oceanDeepWarm = ProjectEvergreen.PERegion.OCEAN_DEEP_WARM.initDefault(
		new String[] {
			"#project_evergreen:ocean_deep_warm"
		}
	);
	public static final PERegion_OLD oceanShallowFrozen = ProjectEvergreen.PERegion.OCEAN_SHALLOW_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_frozen"
		}
	);
	public static final PERegion_OLD oceanShallowTemperate = ProjectEvergreen.PERegion.OCEAN_SHALLOW_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_temperate"
		}
	);
	public static final PERegion_OLD oceanShallowWarm = ProjectEvergreen.PERegion.OCEAN_SHALLOW_WARM.initDefault(
		new String[] {
			"#project_evergreen:ocean_shallow_warm"
		}
	);
	public static final PERegion_OLD oceanRareShallow = ProjectEvergreen.PERegion.OCEAN_RARE_SHALLOW.initDefault(
		new String[] {
			"#project_evergreen:ocean_rare_shallow"
		}
	);
	public static final PERegion_OLD oceanRareDeep = ProjectEvergreen.PERegion.OCEAN_RARE_DEEP.initDefault(
		new String[] {
			"#project_evergreen:ocean_rare_deep"
		}
	);
	public static final PERegion_OLD riverOrCoastNotFrozen = ProjectEvergreen.PERegion.RIVER_OR_COAST_NOT_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:river_temperate",
			"#project_evergreen:river_warm",
			"#project_evergreen:coastal_temperate",
			"#project_evergreen:coastal_warm"
		}
	);
	public static final PERegion_OLD riverOrCoastFrozen = ProjectEvergreen.PERegion.RIVER_OR_COAST_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:river_frozen",
			"#project_evergreen:coastal_frozen"
		}
	);
	public static final PERegion_OLD riverNotFrozen = ProjectEvergreen.PERegion.RIVER_NOT_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:river_frozen",
			"#project_evergreen:river_not_frozen"
		}
	);
	public static final PERegion_OLD riverFrozen = ProjectEvergreen.PERegion.RIVER_FROZEN.initDefault(
		new String[] {
			"#project_evergreen:river_frozen"
		}
	);
	public static final PERegion_OLD riverTemperate = ProjectEvergreen.PERegion.RIVER_TEMPERATE.initDefault(
		new String[] {
			"#project_evergreen:river_temperate"
		}
	);
	public static final PERegion_OLD riverWarm = ProjectEvergreen.PERegion.RIVER_WARM.initDefault(
		new String[] {
			"#project_evergreen:river_warm"
		}
	);
	public static final PERegion_OLD specialBarren = ProjectEvergreen.PERegion.SPECIAL_BARREN.initDefault(
		new String[] {
			"#project_evergreen:special_ruined",
			"#project_evergreen:special_rocky"
		}
	);
	public static final PERegion_OLD specialCraggy = ProjectEvergreen.PERegion.SPECIAL_CRAGGY.initDefault(
		new String[] {
			"#project_evergreen:special_craggy_temperate",
			"#project_evergreen:special_craggy_warm",
			"#project_evergreen:special_rocky",
			"#project_evergreen:mountains_barren"
		}
	);
	public static final PERegion_OLD specialIcy = ProjectEvergreen.PERegion.SPECIAL_ICY.initDefault(
		new String[] {
			"#project_evergreen:special_icy"
		}
	);
	public static final PERegion_OLD specialMagical = ProjectEvergreen.PERegion.SPECIAL_MAGICAL.initDefault(
		new String[] {
			"#project_evergreen:special_magical"
		}
	);
	public static final PERegion_OLD specialMediterranean = ProjectEvergreen.PERegion.SPECIAL_MEDITERRANEAN.initDefault(
		new String[] {
			"#project_evergreen:special_mediterranean_uninviting",
			"#project_evergreen:special_mediterranean_inviting"
		}
	);
	public static final PERegion_OLD specialMountainousCold = ProjectEvergreen.PERegion.SPECIAL_MOUNTAINOUS_COLD.initDefault(
		new String[] {
			"#project_evergreen:mountains_cold",
			"#project_evergreen:special_craggy_cold"
		}
	);
	public static final PERegion_OLD specialMountainousHot = ProjectEvergreen.PERegion.SPECIAL_MOUNTAINOUS_HOT.initDefault(
		new String[] {
			"#project_evergreen:mountains_hot"
		}
	);
	public static final PERegion_OLD specialRuined = ProjectEvergreen.PERegion.SPECIAL_RUINED.initDefault(
		new String[] {
			"#project_evergreen:special_ruined"
		}
	);
	public static final PERegion_OLD specialShroomy = ProjectEvergreen.PERegion.SPECIAL_SHROOMY.initDefault(
		new String[] {
			"#project_evergreen:special_shroomy"
		}
	);
	public static final PERegion_OLD specialSpooky = ProjectEvergreen.PERegion.SPECIAL_SPOOKY.initDefault(
		new String[] {
			"#project_evergreen:special_spooky",
			"#project_evergreen:special_autumnal_forest"
		}
	);
	public static final PERegion_OLD specialSwampy = ProjectEvergreen.PERegion.SPECIAL_SWAMPY.initDefault(
		new String[] {
			"#project_evergreen:special_swampy_cold",
			"#project_evergreen:special_swampy_temperate",
			"#project_evergreen:special_swampy_warm"
		}
	);
	public static final PERegion_OLD specialSwampyWarm = ProjectEvergreen.PERegion.SPECIAL_SWAMPY_WARM.initDefault(
		new String[] {
			"#project_evergreen:special_swampy_warm"
		}
	);


	private static void addRegionToMap(LinkedHashMap<String, PERegion_OLD> map, PERegion_OLD region) {
		region.updateData();
		map.put(region.id, region);
	}

	public static final List<String> safeRegions = ImmutableList.of(
		ALL_RIVERS,
		ALL_COASTAL,
		ALL_CIVILIZATION,
		CIVILIZATION_ARID,
		CIVILIZATION_DESERT,
		CIVILIZATION_DESERT_RED,
		CIVILIZATION_CONIFEROUS,
		CIVILIZATION_DECIDUOUS,
		CIVILIZATION_FIELDS,
		CIVILIZATION_SNOW,
		CIVILIZATION_TEMPERATE,
		CIVILIZATION_TROPICAL,
		CIVILIZATION_SPECIAL_AUTUMNAL,
		CIVILIZATION_SPECIAL_COASTAL,
		CIVILIZATION_SPECIAL_FLOWERY,
		CIVILIZATION_SPECIAL_ORIENTAL,
		RIVER_OR_COAST_NOT_FROZEN,
		RIVER_NOT_FROZEN,
		RIVER_TEMPERATE,
		RIVER_WARM,
		COASTAL_TEMPERATE,
		COASTAL_WARM,
		OCEAN_NOT_FROZEN,
		OCEAN_TEMPERATE,
		OCEAN_WARM,
		OCEAN_SHALLOW,
		OCEAN_SHALLOW_TEMPERATE,
		OCEAN_SHALLOW_WARM
	);

	public static final List<String> dangerousRegions = ImmutableList.of(
		ALL_OCEAN,
		ALL_WILDERNESS,
		WILDERNESS_ARID,
		WILDERNESS_CONIFEROUS,
		WILDERNESS_DECIDUOUS,
		WILDERNESS_FIELDS,
		WILDERNESS_TEMPERATE,
		WILDERNESS_SPECIAL_AUTUMNAL,
		WILDERNESS_SPECIAL_COASTAL,
		WILDERNESS_SPECIAL_ORIENTAL,
		SPECIAL_BARREN,
		SPECIAL_CRAGGY,
		SPECIAL_SHROOMY,
		SPECIAL_MEDITERRANEAN,
		SPECIAL_SWAMPY,
		OCEAN_DEEP,
		OCEAN_DEEP_WARM,
		OCEAN_DEEP_TEMPERATE,
		OCEAN_RARE_SHALLOW,
		OCEAN_FROZEN,
		COASTAL_FROZEN,
		RIVER_OR_COAST_FROZEN,
		RIVER_FROZEN
	);

	public static final List<String> challengingRegions = ImmutableList.of(
		ALL_UNDERGROUND_LAND,
		WILDERNESS_DESERT,
		WILDERNESS_DESERT_RED,
		WILDERNESS_SNOW,
		WILDERNESS_TROPICAL,
		SPECIAL_MOUNTAINOUS_COLD,
		SPECIAL_MOUNTAINOUS_HOT,
		SPECIAL_MAGICAL,
		SPECIAL_ICY,
		SPECIAL_RUINED,
		SPECIAL_SPOOKY,
		SPECIAL_SWAMPY_WARM,
		OCEAN_DEEP_FROZEN,
		OCEAN_RARE_DEEP
	);*/
}
