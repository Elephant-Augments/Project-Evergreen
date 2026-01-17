package com.elephantaugments.projectevergreen.common.data.defaults;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.PERegion;
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

	/**
	 * Instantiates all of PE's custom Region objects and maps them to an easily accessible Multimap.
	 * @return A Multimap of Region objects mapped by their biome tag set.
	 */
	public static LinkedHashMap<String, PERegion> mapRegionsToTags() {
		LinkedHashMap<String, PERegion> regionsByTag = new LinkedHashMap<>();

		//ALL_UNDERGROUND
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_UNDERGROUND_LAND,
			new String[] {
				"#project_evergreen:has_structures/all_civilization",
				"#project_evergreen:has_structures/all_wilderness",
				"#project_evergreen:all_special",
				"#project_evergreen:all_underground"
			}
		));

		//ALL_CIVILIZATION
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_CIVILIZATION,
			new String[] {
				"#project_evergreen:has_structures/civilization_snow",
				"#project_evergreen:has_structures/civilization_arid",
				"#project_evergreen:has_structures/civilization_desert",
				"#project_evergreen:has_structures/civilization_temperate",
				"#project_evergreen:has_structures/civilization_coniferous",
				"#project_evergreen:has_structures/civilization_deciduous",
				"#project_evergreen:has_structures/civilization_fields",
				"#project_evergreen:has_structures/civilization_tropical",
				"#project_evergreen:has_structures/civilization_special_autumnal",
				"#project_evergreen:has_structures/civilization_special_oriental",
				"#project_evergreen:special_mediterranean_inviting",
				"#project_evergreen:all_special_flowery"
			}
		));

		//CIVILIZATION_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_TEMPERATE,
			new String[] {
				"#project_evergreen:plains_grassy_temperate",
				"#project_evergreen:forest_sparse_coniferous",
				"#project_evergreen:forest_sparse_deciduous",
				"#project_evergreen:all_special_flowery",
				"#project_evergreen:special_autumnal_fields",
				"#project_evergreen:special_mediterranean_inviting"
			}
		));

		//CIVILIZATION_ARID
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_ARID,
			new String[] {
				"#project_evergreen:plains_grassy_arid",
				"#project_evergreen:special_mediterranean_inviting"
			}
		));

		//CIVILIZATION_DESERT
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_DESERT,
			new String[] {
				"#project_evergreen:desert_dunes_alive"
			}
		));

		//CIVILIZATION_DESERT_RED
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_DESERT_RED,
			new String[] {
				"#project_evergreen:desert_red_alive"
			}
		));

		//CIVILIZATION_TROPICAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_TROPICAL,
			new String[] {
				"#project_evergreen:plains_grassy_tropical",
				"#project_evergreen:forest_sparse_tropical"
			}
		));

		//CIVILIZATION_SNOW
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_SNOW,
			new String[] {
				"#project_evergreen:plains_grassy_snow",
				"#project_evergreen:forest_sparse_coniferous_snow"
			}
		));

		//CIVILIZATION_CONIFEROUS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_CONIFEROUS,
			new String[] {
				"#project_evergreen:forest_sparse_coniferous_snow",
				"#project_evergreen:forest_sparse_coniferous"
			}
		));

		//CIVILIZATION_DECIDUOUS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_DECIDUOUS,
			new String[] {
				"#project_evergreen:forest_sparse_deciduous",
				"#project_evergreen:special_flowery_forest"
			}
		));

		//CIVILIZATION_FIELDS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_FIELDS,
			new String[] {
				"#project_evergreen:plains_grassy_temperate",
				"#project_evergreen:special_flowery_fields",
				"#project_evergreen:special_autumnal_fields"
			}
		));

		//CIVILIZATION_SPECIAL_AUTUMNAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_SPECIAL_AUTUMNAL,
			new String[] {
				"#project_evergreen:special_autumnal_fields"
			}
		));

		//CIVILIZATION_SPECIAL_COASTAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_SPECIAL_COASTAL,
			new String[] {
				"#project_evergreen:coastal_alive"
			}
		));

		//CIVILIZATION_SPECIAL_FLOWERY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_SPECIAL_FLOWERY,
			new String[] {
				"#project_evergreen:special_flowery_fields",
				"#project_evergreen:special_flowery_forest"
			}
		));

		//CIVILIZATION_SPECIAL_ORIENTAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.CIVILIZATION_SPECIAL_ORIENTAL,
			new String[] {
				"#project_evergreen:special_oriental_inviting"
			}
		));

		//ALL_WILDERNESS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_WILDERNESS,
			new String[] {
				"#project_evergreen:has_structures/wilderness_snow",
				"#project_evergreen:has_structures/wilderness_arid",
				"#project_evergreen:has_structures/wilderness_desert",
				"#project_evergreen:has_structures/wilderness_temperate",
				"#project_evergreen:has_structures/wilderness_deciduous",
				"#project_evergreen:has_structures/wilderness_coniferous",
				"#project_evergreen:has_structures/wilderness_fields",
				"#project_evergreen:has_structures/wilderness_tropical",
				"#project_evergreen:has_structures/wilderness_special_autumnal",
				"#project_evergreen:has_structures/wilderness_special_oriental",
				"#project_evergreen:special_mediterranean_uninviting",
				"#project_evergreen:all_special_craggy"
			}
		));

		//WILDERNESS_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_TEMPERATE,
			new String[] {
				"#project_evergreen:plains_shrubby_temperate",
				"#project_evergreen:forest_dense_coniferous",
				"#project_evergreen:forest_dense_deciduous",
				"#project_evergreen:special_craggy_temperate",
				"#project_evergreen:plains_shrubby_arid",
				"#project_evergreen:special_craggy_warm",
				"#project_evergreen:special_mediterranean_uninviting"
			}
		));

		//WILDERNESS_ARID
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_ARID,
			new String[] {
				"#project_evergreen:plains_shrubby_arid",
				"#project_evergreen:special_craggy_warm",
				"#project_evergreen:special_mediterranean_uninviting"
			}
		));

		//WILDERNESS_DESERT
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_DESERT,
			new String[] {
				"#project_evergreen:desert_dunes_barren"
			}
		));

		//WILDERNESS_DESERT_RED
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_DESERT_RED,
			new String[] {
				"#project_evergreen:desert_red_barren"
			}
		));

		//WILDERNESS_TROPICAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_TROPICAL,
			new String[] {
				"#project_evergreen:forest_dense_tropical"
			}
		));

		//WILDERNESS_SNOW
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_SNOW,
			new String[] {
				"#project_evergreen:plains_shrubby_snow",
				"#project_evergreen:forest_dense_coniferous_snow",
				"#project_evergreen:special_craggy_cold"
			}
		));

		//WILDERNESS_CONIFEROUS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_CONIFEROUS,
			new String[] {
				"#project_evergreen:forest_dense_coniferous"
			}
		));

		//WILDERNESS_DECIDUOUS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_DECIDUOUS,
			new String[] {
				"#project_evergreen:forest_dense_deciduous"
			}
		));

		//WILDERNESS_FIELDS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_FIELDS,
			new String[] {
				"#project_evergreen:plains_shrubby_snow",
				"#project_evergreen:plains_shrubby_temperate",
				"#project_evergreen:plains_shrubby_arid"
			}
		));

		//WILDERNESS_SPECIAL_AUTUMNAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_SPECIAL_AUTUMNAL,
			new String[] {
				"#project_evergreen:special_autumnal_forest"
			}
		));

		//WILDERNESS_SPECIAL_COASTAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_SPECIAL_COASTAL,
			new String[] {
				"#project_evergreen:coastal_barren"
			}
		));

		//WILDERNESS_SPECIAL_ORIENTAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.WILDERNESS_SPECIAL_ORIENTAL,
			new String[] {
				"#project_evergreen:special_oriental_uninviting"
			}
		));

		//SPECIAL_BARREN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_BARREN,
			new String[] {
				"#project_evergreen:special_ruined",
				"#project_evergreen:special_rocky"
			}
		));

		//SPECIAL_CRAGGY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_CRAGGY,
			new String[] {
				"#project_evergreen:special_craggy_temperate",
				"#project_evergreen:special_craggy_warm",
				"#project_evergreen:special_rocky",
				"#project_evergreen:mountains_barren"
			}
		));

		//SPECIAL_ICY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_ICY,
			new String[] {
				"#project_evergreen:special_icy"
			}
		));

		//SPECIAL_MAGICAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_MAGICAL,
			new String[] {
				"#project_evergreen:special_magical"
			}
		));

		//SPECIAL_MEDITERRANEAN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_MEDITERRANEAN,
			new String[] {
				"#project_evergreen:special_mediterranean_uninviting",
				"#project_evergreen:special_mediterranean_inviting"
			}
		));

		//SPECIAL_MOUNTAINOUS_COLD
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_MOUNTAINOUS_COLD,
			new String[] {
				"#project_evergreen:mountains_cold",
				"#project_evergreen:special_craggy_cold"
			}
		));

		//SPECIAL_MOUNTAINOUS_HOT
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_MOUNTAINOUS_HOT,
			new String[] {
				"#project_evergreen:mountains_hot"
			}
		));

		//SPECIAL_RUINED
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_RUINED,
			new String[] {
				"#project_evergreen:special_ruined"
			}
		));

		//SPECIAL_SHROOMY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_SHROOMY,
			new String[] {
				"#project_evergreen:special_shroomy"
			}
		));

		//SPECIAL_SPOOKY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_SPOOKY,
			new String[] {
				"#project_evergreen:special_spooky",
				"#project_evergreen:special_autumnal_forest"
			}
		));

		//SPECIAL_SWAMPY
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_SWAMPY,
			new String[] {
				"#project_evergreen:special_swampy_cold",
				"#project_evergreen:special_swampy_temperate",
				"#project_evergreen:special_swampy_warm"
			}
		));

		//SPECIAL_SWAMPY_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.SPECIAL_SWAMPY_WARM,
			new String[] {
				"#project_evergreen:special_swampy_warm"
			}
		));

		//ALL_COASTAL
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_COASTAL,
			new String[] {
				"#project_evergreen:coastal_alive",
				"#project_evergreen:coastal_barren",
				"#project_evergreen:coastal_frozen",
				"#project_evergreen:coastal_temperate",
				"#project_evergreen:coastal_warm"
			}
		));

		//COASTAL_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.COASTAL_FROZEN,
			new String[] {
				"#project_evergreen:coastal_frozen"
			}
		));

		//COASTAL_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.COASTAL_TEMPERATE,
			new String[] {
				"#project_evergreen:coastal_temperate"
			}
		));

		//COASTAL_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.COASTAL_WARM,
			new String[] {
				"#project_evergreen:coastal_warm"
			}
		));

		//RIVER_OR_COAST_NOT_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.RIVER_OR_COAST_NOT_FROZEN,
			new String[] {
				"#project_evergreen:river_temperate",
				"#project_evergreen:river_warm",
				"#project_evergreen:coastal_temperate",
				"#project_evergreen:coastal_warm"
			}
		));

		//RIVER_OR_COAST_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.RIVER_OR_COAST_FROZEN,
			new String[] {
				"#project_evergreen:river_frozen",
				"#project_evergreen:coastal_frozen"
			}
		));

		//ALL_RIVERS
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_RIVERS,
			new String[] {
				"#project_evergreen:river_frozen",
				"#project_evergreen:river_not_frozen"
			}
		));

		//RIVER_NOT_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_RIVERS,
			new String[] {
				"#project_evergreen:river_frozen",
				"#project_evergreen:river_not_frozen"
			}
		));

		//RIVER_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.RIVER_FROZEN,
			new String[] {
				"#project_evergreen:river_frozen"
			}
		));

		//RIVER_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.RIVER_TEMPERATE,
			new String[] {
				"#project_evergreen:river_temperate"
			}
		));

		//RIVER_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.RIVER_WARM,
			new String[] {
				"#project_evergreen:river_warm"
			}
		));

		//ALL_OCEAN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.ALL_OCEAN,
			new String[] {
				"#project_evergreen:has_structures/all_ocean_deep",
				"#project_evergreen:has_structures/all_ocean_shallow"
			}
		));

		//OCEAN_DEEP
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_DEEP,
			new String[] {
				"#project_evergreen:ocean_deep_frozen",
				"#project_evergreen:ocean_deep_temperate",
				"#project_evergreen:ocean_deep_warm"
			}
		));

		//OCEAN_SHALLOW
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_SHALLOW,
			new String[] {
				"#project_evergreen:ocean_shallow_frozen",
				"#project_evergreen:ocean_shallow_temperate",
				"#project_evergreen:ocean_shallow_warm"
			}
		));

		//OCEAN_NOT_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_NOT_FROZEN,
			new String[] {
				"#project_evergreen:ocean_deep_warm",
				"#project_evergreen:ocean_deep_temperate",
				"#project_evergreen:ocean_shallow_temperate",
				"#project_evergreen:ocean_shallow_warm"
			}
		));

		//OCEAN_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_FROZEN,
			new String[] {
				"#project_evergreen:ocean_shallow_frozen",
				"#project_evergreen:ocean_deep_frozen"
			}
		));

		//OCEAN_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_TEMPERATE,
			new String[] {
				"#project_evergreen:ocean_shallow_temperate",
				"#project_evergreen:ocean_deep_temperate"
			}
		));

		//OCEAN_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_WARM,
			new String[] {
				"#project_evergreen:ocean_shallow_warm",
				"#project_evergreen:ocean_deep_warm"
			}
		));

		//OCEAN_DEEP_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_DEEP_FROZEN,
			new String[] {
				"#project_evergreen:ocean_deep_frozen"
			}
		));

		//OCEAN_DEEP_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_DEEP_TEMPERATE,
			new String[] {
				"#project_evergreen:ocean_deep_temperate"
			}
		));

		//OCEAN_DEEP_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_DEEP_WARM,
			new String[] {
				"#project_evergreen:ocean_deep_warm"
			}
		));

		//OCEAN_SHALLOW_FROZEN
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_SHALLOW_FROZEN,
			new String[] {
				"#project_evergreen:ocean_shallow_frozen"
			}
		));

		//OCEAN_SHALLOW_TEMPERATE
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_SHALLOW_TEMPERATE,
			new String[] {
				"#project_evergreen:ocean_shallow_temperate"
			}
		));

		//OCEAN_SHALLOW_WARM
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_SHALLOW_WARM,
			new String[] {
				"#project_evergreen:ocean_shallow_warm"
			}
		));

		//OCEAN_RARE_DEEP
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_RARE_DEEP,
			new String[] {
				"#project_evergreen:ocean_rare_deep"
			}
		));

		//OCEAN_RARE_SHALLOW
		addRegionToMap(regionsByTag, new PERegion(
			DefaultRegions.OCEAN_RARE_SHALLOW,
			new String[] {
				"#project_evergreen:ocean_rare_shallow"
			}
		));

		ProjectEvergreen.LOGGER.info("Successfully mapped " + regionsByTag.size() + " regions.");
		return regionsByTag;
	}

	private static void addRegionToMap(LinkedHashMap<String, PERegion> map, PERegion region) {
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
	);
}
