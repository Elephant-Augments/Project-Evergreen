package com.elephantaugments.projectevergreen.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class Constants {

	public enum JsonProp {
		DTYPE,
		ID,
		TYPE,
		STEP,
		SIZE,
		BIOME,
		REGION,
		DIMENSION,
		STRUCTURE_SET,
		HEIGHTMAP,
		DIFFICULTY,
		SPAWN_OVERRIDES,
		SPAWNERS,
		SPAWN_COSTS,
		IS_LOADED,
		IS_MODIFIER,
		IS_ADVANCED_TYPE,
		IS_FLAT,
		IS_INLAND,
		IS_OCEAN,
		IS_WATER_BOUND,
		IS_WATER_RESTRICTED,
		IS_RADIUS_BOUND,
		IS_MASSIVE;

		public String jsonKey() {
			return this.name().toLowerCase();
		}

		public String jsonPath() {
			return "/" + PROPERTIES_KEY + "/" + jsonKey();
		}
	}

	public enum SpawnSteps {
		RAW_GENERATION,
		LAKES,
		LOCAL_MODIFICATIONS,
		UNDERGROUND_STRUCTURES,
		SURFACE_STRUCTURES,
		STRONGHOLDS,
		UNDERGROUND_ORES,
		UNDERGROUND_DECORATION,
		FLUID_SPRINGS,
		VEGETAL_DECORATION,
		TOP_LAYER_MODIFICATION
	}

	public static final String PROPERTIES_KEY = "temp_properties";
	public static final String PE_OBJECT_KEY = "pe_object";
	public static final String PE_VALUE_KEY = "pe_value";
	public static final String CONFIG_VALUE_KEY = "config_value";

	public static final String PATCHABLE_BIOME_KEY = "patchable_biome";
	public static final String PATCHABLE_STRUCTURE_SET_KEY = "patchable_structure_set";
	public static final String PATCHABLE_STRUCTURE_KEY = "patchable_structure";
	public static final String PATCHABLE_FEATURE_KEY = "patchable_feature";
	public static final String PATCHABLE_ENTITY_KEY = "patchable_entity";
	public static final String DYNAMIC_STRUCTURE_SET_KEY = "structure_set_builder";
	public static final String DYNAMIC_BIOME_SPAWNS_KEY = "biome_spawn_builder";

	public static final String STRUCTURE_TYPE_KEY = "type";
	public static final String STRUCTURE_STEP_KEY = "step";
	public static final String STRUCTURE_HEIGHTMAP_KEY = "heightmap";

	public static final List<String> EMPTY_LIST = new ArrayList<>();

	public static final int OVERWORLD_DIFFICULTY = 3;
	public static final int OTHERWORLD_DIFFICULTY = 4;

	public static final int AETHER_DIFFICULTY_OFFSET = 1;
	public static final int NETHER_DIFFICULTY_OFFSET = 2;
	public static final int END_DIFFICULTY_OFFSET = 2;
	public static final int AFTERDARK_DIFFICULTY_OFFSET = 2;
	public static final int ETERNAL_STARLIGHT_DIFFICULTY_OFFSET = 3;
	public static final int LOSTCITIES_DIFFICULTY_OFFSET = 4;

	public static final int SAFE_DIFFICULTY_OFFSET = -2;
	public static final int NEUTRAL_DIFFICULTY_OFFSET = 0;
	public static final int DANGEROUS_DIFFICULTY_OFFSET = 2;

	public static final int SMALL_DIFFICULTY_OFFSET = -1;
	public static final int MEDIUM_DIFFICULTY_OFFSET = 0;
	public static final int LARGE_DIFFICULTY_OFFSET = 1;
	public static final int SPRAWLING_DIFFICULTY_OFFSET = 2;

	public static final int DEFAULT_TERRAIN_HEIGHT_SMALL = 10;
	public static final int DEFAULT_TERRAIN_HEIGHT_MEDIUM = 16;
	public static final int DEFAULT_TERRAIN_HEIGHT_LARGE = 24;
	public static final int DEFAULT_TERRAIN_HEIGHT_SPRAWLING = 28;

	public static final int DEFAULT_DECO_SPACING = 12;
	public static final int DEFAULT_DECO_SEPARATION = 6;
	public static final int DEFAULT_COMMON_SPACING = 38;
	public static final int DEFAULT_COMMON_SEPARATION = 32;
	public static final int DEFAULT_RARE_SPACING = 52;
	public static final int DEFAULT_RARE_SEPARATION = 44;
	public static final int DEFAULT_EXTRA_RARE_SPACING = 68;
	public static final int DEFAULT_EXTRA_RARE_SEPARATION = 58;
	public static final Double CIVILIZATION_SPREAD_OFFSET = 0.9;
	public static final Double WILDERNESS_SPREAD_OFFSET = 1.05;
	public static final Double SPECIAL_SPREAD_OFFSET = 1.35;
	public static final Double FLATNESS_SPREAD_OFFSET = 0.5;

	public static final int DEFAULT_COLD_WATER_COLOR = 3570859;
	public static final int DEFAULT_TEMPERATE_WATER_COLOR = 3570859;
	public static final int DEFAULT_WARM_WATER_COLOR = 3570859;

	public static final int UNDERGROUND_Y_MAX_SHALLOW = -11;
	public static final int UNDERGROUND_Y_MIN_SHALLOW = -32;
	public static final int UNDERGROUND_Y_MAX_DEEP = -32;
	public static final int UNDERGROUND_Y_MIN_DEEP = -64;

	public static final String CAVE_TAG = "c:caves";
	public static final String BIOME_TAG_PATH = "tags/worldgen/biome/";
	public static final String STRUCTURE_TAG_PATH = "tags/worldgen/structure/";
	public static final String STRUCTURE_SET_TAG_PATH = "tags/worldgen/structure_set/";
	public static final String ENTITY_TAG_PATH = "tags/entity_type/";

	public static String getNamespace(String location) {
		return location.split(":")[0];
	}

	public static String getPath(String location) {
		return location.split(":")[1];
	}

	public static JsonElement neoforge_1_21_1_disable() {
		JsonArray array = new JsonArray();
		JsonObject disabled = new JsonObject();
		disabled.addProperty("type", "neoforge:false");
		array.add(disabled);
		return ProjectEvergreen.GSON.toJsonTree(array);
	}
}
