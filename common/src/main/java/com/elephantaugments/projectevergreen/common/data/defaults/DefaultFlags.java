package com.elephantaugments.projectevergreen.common.data.defaults;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.EntityPredicate;

import java.util.List;

public class DefaultFlags {

//<-----------------------------------------------------ENTITIES----------------------------------------------------->
//<------------------------------------------------------------------------------------------------------------------>

    //DISABLED
    //<--- Entities to disable individually --->
    public static final List<String> disabledMobs = ImmutableList.of(
		"minecraft:squid",
		"hybrid-aquatic:manta_ray"
	);

//<-----------------------------------------------------FEATURES----------------------------------------------------->
//<------------------------------------------------------------------------------------------------------------------>

	//IGNORED
	//<--- Ignored features, PE will not apply patches to these features in any way to avoid duplication --->
	public static final List<String> ignoredFeatures = ImmutableList.of(
		"lostcities:lostcities",
		"lostcities:lostcity_spheres",
		"projectvibrantjourneys:bark_mushroom"
	);

    //DISABLED
    //<--- Biome features to disable individually --->
    public static final List<String> disabledFeatures = ImmutableList.of(
		"culturaldelights:add_corn",
		"culturaldelights:add_cucumber",
		"dreamwoods:vanilla_biomes_features/bamboo_jungle_trees",
		"dreamwoods:vanilla_biomes_features/birch_forest_trees",
		"dreamwoods:vanilla_biomes_features/cherry_grove_trees",
		"dreamwoods:vanilla_biomes_features/dark_forest_trees",
		"dreamwoods:vanilla_biomes_features/desert_trees",
		"dreamwoods:vanilla_biomes_features/flower_forest_trees",
		"dreamwoods:vanilla_biomes_features/forest_trees",
		"dreamwoods:vanilla_biomes_features/grove_trees",
		"dreamwoods:vanilla_biomes_features/jungle_trees",
		"dreamwoods:vanilla_biomes_features/mangrove_swamp_trees",
		"dreamwoods:vanilla_biomes_features/meadow_trees",
		"dreamwoods:vanilla_biomes_features/mushroom_fields_trees",
		"dreamwoods:vanilla_biomes_features/old_growth_birch_forest_trees",
		"dreamwoods:vanilla_biomes_features/old_growth_pine_taiga_trees",
		"dreamwoods:vanilla_biomes_features/old_growth_spruce_taiga_trees",
		"dreamwoods:vanilla_biomes_features/plains_trees",
		"dreamwoods:vanilla_biomes_features/river_trees",
		"dreamwoods:vanilla_biomes_features/savanna_plateau_trees",
		"dreamwoods:vanilla_biomes_features/savanna_trees",
		"dreamwoods:vanilla_biomes_features/snowy_plains_trees",
		"dreamwoods:vanilla_biomes_features/snowy_taiga_trees",
		"dreamwoods:vanilla_biomes_features/sparse_jungle_trees",
		"dreamwoods:vanilla_biomes_features/sunflower_plains_trees",
		"dreamwoods:vanilla_biomes_features/swamp_trees",
		"dreamwoods:vanilla_biomes_features/taiga_trees",
		"dreamwoods:vanilla_biomes_features/windswept_forest_trees",
		"dreamwoods:vanilla_biomes_features/windswept_gravelly_hills_trees",
		"dreamwoods:vanilla_biomes_features/windswept_hills_trees",
		"dreamwoods:vanilla_biomes_features/windswept_savanna_trees",
		"dreamwoods:vanilla_biomes_features/wooded_badlands_trees",
		"extradelight:apple_tree",
		"extradelight:patch_wild_coffee",
		"extradelight:patch_wild_garlic",
		"extradelight:patch_wild_peanut",
		"extradelight:patch_wild_soybean",
		"extradelight:apple",
		"farmerspizzeria:basil_4",
		"herbalbrews:add_coffee_patch",
		"herbalbrews:coffee_patch_chance",
		"hybrid-aquatic:basking_shark_arctic_oceans",
		"hybrid-aquatic:basking_shark_cold_oceans",
		"hybrid-aquatic:basking_shark_deep_temperate_oceans",
		"hybrid-aquatic:crayfish_rivers",
		"hybrid-aquatic:fiddler_crab_mangroves",
		"hybrid-aquatic:fiddler_crab_marshes",
		"hybrid-aquatic:fiddler_crab_swamp",
		"hybrid-aquatic:great_white_shark_deep_temperate_oceans",
		"hybrid-aquatic:great_white_shark_deep_tropical_oceans",
		"hybrid-aquatic:hammerhead_shark_shallow_tropical_oceans",
		"hybrid-aquatic:hammerhead_shark_temperate_oceans",
		"hybrid-aquatic:hermit_crab_sandy_beaches",
		"hybrid-aquatic:hermit_crab_shallow_tropical_oceans",
		"hybrid-aquatic:herring_shallow_cold_oceans",
		"hybrid-aquatic:herring_shallow_temperate_oceans",
		"hybrid-aquatic:lions_mane_jellyfish_arctic_oceans",
		"hybrid-aquatic:lions_mane_jellyfish_cold_oceans",
		"hybrid-aquatic:lobster_reef",
		"hybrid-aquatic:lobster_shallow_tropical_oceans",
		"hybrid-aquatic:ocean_sunfish_deep_temperate_oceans",
		"hybrid-aquatic:ocean_sunfish_deep_tropical_oceans",
		//"hybrid-aquatic:tuna_deep_temperate_oceans",
		//"hybrid-aquatic:tuna_deep_tropical_oceans",
		"hybrid-aquatic:yeti_crab_deep_arctic_oceans",
		"hybrid-aquatic:yeti_crab_deep_cold_oceans",
		"koopascritters:agazzis_cichlid_biome_modifier",
		"koopascritters:asian_leopard_cat_biome_modifier",
		"koopascritters:attenboroughs_echidna_biome_modifier",
		"koopascritters:binturong_biome_modifier",
		"koopascritters:bonnet_head_shark_biome_modifier",
		"koopascritters:bowmouth_guitarfish_biome_modifier",
		"koopascritters:burrowing_owl_biome_modifier",
		"koopascritters:bush_dog_biome_modifier",
		"koopascritters:california_quail_biome_modifier",
		"koopascritters:candy_cane_snail_biome_modifier",
		"koopascritters:cape_buffalo_biome_modifier",
		"koopascritters:chacoan_peccary_biome_modifier",
		"koopascritters:chinstrap_penguin_biome_modifier",
		"koopascritters:chuckwalla_biome_modifier",
		"koopascritters:clown_loach_biome_modifier",
		"koopascritters:dwarf_sperm_whale_biome_modifier",
		"koopascritters:emerald_toucanet_biome_modifier",
		"koopascritters:fire_salamander_biome_modifier",
		"koopascritters:fly_river_turtle_biome_modifier",
		"koopascritters:gentoo_penguin_biome_modifier",
		"koopascritters:giant_muntjac_biome_modifier",
		"koopascritters:goblin_shark_biome_modifier",
		"koopascritters:headlight_beetle_biome_modifier",
		"koopascritters:helmeted_guineafowl_biome_modifier",
		"koopascritters:hyrax_biome_modifier",
		"koopascritters:impalla_biome_modifier",
		"koopascritters:klipspringer_biome_modifier",
		"koopascritters:kopje_fig_sapling",
		"koopascritters:kopje_fig_tree_1_feature",
		"koopascritters:kopje_fig_tree_2_feature",
		"koopascritters:termite_mound_1_feature",
		"koopascritters:termite_mound_2_feature",
		"koopascritters:marabou_stork_biome_modifier",
		"koopascritters:marsupial_mole_biome_modifier",
		"koopascritters:mexican_rosy_boa_biome_modifier",
		"koopascritters:northern_rock_hopper_penguin_biome_modifier",
		"koopascritters:oceanic_manta_ray_biome_modifier",
		"koopascritters:matschies_tree_kangaroo_biome_modifier",
		"koopascritters:ocellate_river_ray_biome_modifier",
		"koopascritters:pallas_cat_biome_modifier",
		"koopascritters:pancake_tortoise_biome_modifier",
		"koopascritters:pink_land_iguana_biome_modifier",
		"koopascritters:pink_velvet_worm_biome_modifier",
		"koopascritters:psychedelic_jelly_biome_modifier",
		"koopascritters:rock_ptarmigan_biome_modifier",
		"koopascritters:roseate_spoonbill_biome_modifier",
		"koopascritters:south_island_takahe_biome_modifier",
		"koopascritters:southern_patas_monkey_biome_modifier",
		"koopascritters:tibetan_fox_biome_modifier",
		"koopascritters:tricolor_hognose_snake_biome_modifier",
		"koopascritters:uakari_monkey_biome_modifier",
		"koopascritters:upside_down_jellyfish_biome_modifier",
		"koopascritters:yangtze_giant_softshell_turtle_biome_modifier",
		"koopascritters:yellow_eye_penguin_biome_modifier",
		"ramadandelight:add_tree_palm",
		"ramadandelight:add_wild_chickpea",
		"ramadandelight:add_wild_parsley",
		"ramadandelight:date_palm_tree",
		"ramadandelight:patch_wild_chickpea",
		"ramadandelight:patch_wild_parsley",
		"rusticdelight:add_wild_cotton",
		"rusticdelight:wild_cotton_placed",
		"snowyspirit:ginger",
		"snowyspirit:ginger_dense",
		"snowyspirit:wild_ginger",
		"snowyspirit:wild_ginger_dense",
		"ubesdelight:wild_garlic",
		"ubesdelight:wild_ginger",
		"ubesdelight:patch_wild_garlic",
		"ubesdelight:patch_wild_ginger",
		"veggiesdelight:patch_wild_bellpeppers",
		"veggiesdelight:wild_bellpeppers"
	);

	//IS_COMMON_SPAWN
	//<--- Common spawns, as determined by the spawn cost system --->
	public static final List<String> commonSpawn = ImmutableList.of(
		"crittersandcompanions:koi_fish",
		"envelope:pigeon",
		"hybrid-aquatic:yeti_crab",
		"koopascritters:agazzis_cichlid",
		"koopascritters:bush_dog",
		"koopascritters:candy_cane_snail",
		"koopascritters:california_quail",
		"koopascritters:clown_loach",
		"koopascritters:peacock_bass",
		"mythsandlegends:condemned"
	);

	//IS_RARE_SPAWN
	//<--- Rare spawns, as determined by the spawn cost system --->
	public static final List<String> rareSpawn = ImmutableList.of(
		"crittersandcompanions:dragonfly",
		"crittersandcompanions:leaf_insect",
		"hybrid-aquatic:crayfish",
		"hybrid-aquatic:fiddler_crab",
		"hybrid-aquatic:sea_urchin",
		"hybrid-aquatic:mackerel",
		"hybrid-aquatic:herring",
		"koopascritters:asian_leopard_cat",
		"koopascritters:attenboroughs_echidna",
		"koopascritters:binturong",
		"koopascritters:bonnet_head_shark",
		"koopascritters:bowmouth_guitarfish",
		"koopascritters:burrowing_owl",
		"koopascritters:cape_buffalo",
		"koopascritters:chacoan_peccary",
		"koopascritters:chinstrap_penguin",
		"koopascritters:chuckwalla",
		"koopascritters:emerald_toucanet",
		"koopascritters:fire_salamander",
		"koopascritters:fly_river_turtle",
		"koopascritters:gentoo_penguin",
		"koopascritters:giant_muntjac",
		"koopascritters:goblin_shark",
		"koopascritters:headlight_beetle",
		"koopascritters:helmeted_guineafowl",
		"koopascritters:hyrax",
		"koopascritters:impalla",
		"koopascritters:indian_bullfrog",
		"koopascritters:klipspringer",
		"koopascritters:marabou_stork",
		"koopascritters:marsupial_mole",
		"koopascritters:matschies_tree_kangaroo",
		"koopascritters:mexican_rosy_boa",
		"koopascritters:mountain_viscacha",
		"koopascritters:northern_rock_hopper_penguin",
		"koopascritters:oceanic_manta_ray",
		"koopascritters:ocellate_river_ray",
		"koopascritters:pancake_tortoise",
		"koopascritters:pallas_cat",
		"koopascritters:pink_land_iguana",
		"koopascritters:pink_velvet_worm",
		"koopascritters:psychedelic_jelly",
		"koopascritters:rock_ptarmigan",
		"koopascritters:roseate_spoonbill",
		"koopascritters:serval",
		"koopascritters:southern_patas_monkey",
		"koopascritters:south_island_takahe",
		"koopascritters:sumatran_rhino",
		"koopascritters:tibetan_fox",
		"koopascritters:tricolor_hognose_snake",
		"koopascritters:uakari_monkey",
		"koopascritters:upside_down_jellyfish",
		"koopascritters:yellow_eye_penguin",
		"quark:shiba"
	);

	//IS_EXTRA_RARE_SPAWN
	//<--- Extra rare spawns, as determined by the spawn cost system --->
	public static final List<String> extraRareSpawn = ImmutableList.of(
		"hybrid-aquatic:basking_shark",
		"hybrid-aquatic:great_white_shark",
		"hybrid-aquatic:hammerhead_shark",
		"hybrid-aquatic:lions_mane_jellyfish",
		"hybrid-aquatic:ocean_sunfish",
		"hybrid-aquatic:tuna",
		"koopascritters:dwarf_sperm_whale",
		"koopascritters:yangtze_giant_softshell_turtle"
	);

	//IS_BROKEN_SPAWN
	//<--- Incredibly limited spawns to account for certain hard-coded custom mobs that break the spawner system (hybrid aquatic) --->
	public static final List<String> brokenSpawnFix = ImmutableList.of(

	);


//<--------------------------------------------------STRUCTURE SETS-------------------------------------------------->
//<------------------------------------------------------------------------------------------------------------------>

    //DISABLED
    //<--- Structure sets to disable individually --->
    public static final List<String> disabledSets = ImmutableList.of(
        "minecraft:villages",
        "minecraft:ocean_monuments",
        "minecraft:pillager_outposts",
		"minecraft:ruined_portals",
		"minecraft:woodland_mansions",
		"ancientstructures:german_buildings",
        "ati_structures:aboveground_large",
		"ati_structures:aboveground_small",
		"create_ltab:water_pre",
        "dungeons_arise:major_structures",
        "dungeons_arise_seven_seas:minor_structures",
		"explorify:taverns",
		"explorify:watchtowers",
		"formationsoverworld:uncommon",
		"formationsoverworld:rare",
		"hollowmarch:desert",
		"hollowmarch:ocean",
		"hollowmarch:air",
        "integrated_villages:air_villages",
		"integrated_villages:regular_villages",
        "idas:idas_rare",
        "idas:idas_underground_rare",
		"illagerstructures:ice",
		"illagerstructures:pirates",
		"illagerstructures:ruins",
		"jvs:ocean",
		"jvs:cold_ocean",
		"kattersstructures:sky",
		"kattersstructures:ocean",
		"kattersstructures:villages",
		"kattersstructures:villagers",
		"kattersstructures:grassland",
		"loot_n_explore:dungeons",
		"loot_n_explore:overworld_inns",
		"loot_n_explore:frostmonarch_temple",
		"netherman:gast_chamber",
		"netherman:mansion_nether",
		"netherman:minor_points",
        "nova_structures:firewatch_towers",
        "nova_structures:villages_swamp",
        "nova_structures:villages_jungle",
        "nova_structures:villages_birch",
        "nova_structures:swamp_structure",
		"philipsruins:ocean_fortress_main",
		"takesapillage:pillager_structure",
        "towns_and_towers:towers",
        "towns_and_towers:towns",
        "structory:ruin_quiet",
		"supplementaries:galleons",
        "repurposed_structures:outposts_overworld",
        "repurposed_structures:monuments_overworld",
        "repurposed_structures:villages_overworld",
        "repurposed_structures:cities_overworld",
        "lios_outlandish_villages:spiral_tower_village",
		"lios_outlandish_villages:spiral_tower_village_sea",
        "betterdeserttemples:desert_temples",
        "betterjungletemples:jungle_temples",
		"totw_modded:overworld",
		"trek:overworld/very_common",
		"trek:overworld/medium",
		"trek:overworld/very_rare",
		"trek:overworld/rare",
		"wythers:villages",
		"wythers:features"
    );


//<----------------------------------------------------STRUCTURES---------------------------------------------------->
//<------------------------------------------------------------------------------------------------------------------>

	//DISABLED
    //<--- Structure sets to disable individually --->
    public static final List<String> disabledStructures = ImmutableList.of(
		"archaeology_ruins:ruined_ocean_monument",
		"ati_structures:lighthouse",
		"born_in_chaos_v1:dark_tower_forest",
		"combat_structures_update:ruinedstronghold",
		"kattersstructures:villager_ocean",
		"mvs:well/well",
		"mvs:well",
		"qrafty:oilrig",
		"trek:overworld/very_rare/coves",
		"trek:overworld/rare/island_village_1",
		"trek:overworld/rare/tower_island"
    );

	//IGNORED
	//<--- Ignored structures, PE will not alter these structures in any way --->
	public static final List<String> ignoredStructureIDs = ImmutableList.of(
		"ati_structures:wither_cavern",
		"goety:final_terminal",
		"idas:necromancers_spire",
		"idas:foxhound_den",
		"idas:animal_den/foxhound_den",
		"imst:terralith/yurt",
		"irons_spellbooks:ancient_battleground",
		"kattersstructures:red_coral",
		"kattersstructures:deep_blue_labs",
		"kattersstructures:deep_blue_lab_ruin",
		"kattersstructures:deep_blue_city",
		"legendary_monsters:ancient_tower_remains",
		"legendary_monsters:lava_eater_spawn",
		"legendary_monsters:shulker_tower",
		"legendary_monsters:skeletosaurus_nest",
		"legendary_monsters:soul_fortress_remains",
		"legendary_monsters:warped_fungussus_nest",
		"qrafty:overworld_leak",
		"terramity:chthonian_breach",
		"terramity:chthonic_cathedral",
		"terramity:chthonic_dungeon",
		"totw_modded:aether",
		"totw_modded:everbright",
		"totw_modded:everdawn",
		"totw_modded:glacio",
		"totw_modded:mars",
		"totw_modded:mercury",
		"totw_modded:moon",
		"totw_modded:ratlantis",
		"totw_modded:tw_canopy",
		"totw_modded:tw_mangrove",
		"totw_modded:venus",
		"traveloptics:echo_shrine",
		"traveloptics:void_cathedral",
		"wythers:banyan_sparse_jungle",
		"wythers:baobab_dry_tropical_forest",
		"wythers:baobab_savanna",
		"wythers:baobab_tropical_forest",
		"wythers:el_dorado",
		"wythers:elephant_graveyard_fossil",
		"wythers:giant_fallen_log"
	);

	//IGNORED_PLACEMENT_TWEAKS
	//<--- Selectively ignores biome redistribution while still applying rarity redistribution & advanced placement --->
	public static final List<String> ignoreBiomeRedistribution = ImmutableList.of(
		"minecraft:end_city",
		"minecraft:bastion_remnant",
		"minecraft:fortress",
		"minecraft:nether_city",
		"minecraft:nether_fossil",
		"minecraft:ruined_portal_nether",
		"block_factorys_bosses:underworld_arena",
		"bosses_of_mass_destruction:gauntlet_arena",
		"combat_structures_update:netherlargewarpedtree_1",
		"combat_structures_update:netherlargewarpedtree_2",
		"combat_structures_update:netherlargewarpedtree_3",
		"combat_structures_update:netherlargewarpedtree_4",
		"combat_structures_update:netherlargewarpedtreehouse",
		"farmers_structures:quark_glimer",
		"hopo:portal/overworld/portal_deepslate",
		"hopo:portal/overworld/portal_dripstone",
		"hopo:portal/overworld/portal_lush",
		"idas:desert_camp/desert_camp_bygwindswept",
		"idas:lumber_camp/lumber_camp_bopmahogany",
		"idas:lumber_camp/lumber_camp_bopredwood",
		"idas:lumber_camp/lumber_camp_bygmahogany",
		"idas:lumber_camp/lumber_camp_bygredwood",
		"integrated_villages:pirate_village",
		"mvs:log_pile/dark_oak_log_pile",
		"mvs:dark_oak_log_pile",
		"nova_structures:firewatch_tower_dark_oak",
		"nova_structures:firewatch_tower_forest",
		"nova_structures:firewatch_tower_jungle",
		"nova_structures:firewatch_tower_mangrove",
		"nova_structures:firewatch_tower_swamp",
		"letsdoaddon-structures:warped_vinery",
		"nova_structures:nether_skeleton_tower_warped",
		"nova_structures:skeleton_camp_warped",
		"structory_towers:nether/warped_outpost",
		"repurposed_structures:pyramid_dark_forest",
		"threateningly_mobs:distortion_village",
		"trek:overworld/medium/cold_red_trade",
		"trek:overworld/medium/dark_oak_trade",
		"trek:overworld/medium/small_red_trade",
		"trek:overworld/rare/island_village_1",
		"trek:overworld/rare/sleepy_island",
		"trek:overworld/rare/tower_island",
		"trek:overworld/rare/villager_island",
		"trek:overworld/very_rare/floating_farm_large",
		"windswept:chestnut_weathered_house",
		"windswept:grove_weathered_house",
		"wabi_sabi_structures:basalt_castle",
		"wabi_sabi_structures:basalt_factory_ruin",
		"wabi_sabi_structures:hellish_orphanage",
		"wabi_sabi_structures:obsidian_egg",
		"wabi_sabi_structures:strider_stable",
		"wabi_sabi_structures:warped_ender_claw",
		"wabi_sabi_structures:lumpy_tower"
	);

	//IGNORED_PLACEMENT_TWEAKS
	//<--- Ignores structure type update and all advanced placement tweaks - spawn-step normalization, flatness, biome radius, cannot-spawn-in-liquid --->
	public static final List<String> ignorePlacementTweaks = ImmutableList.of(
		"minecraft:village_desert",
		"minecraft:village_plains",
		"minecraft:village_savanna",
		"minecraft:village_snowy",
		"minecraft:village_taiga",
		"natures_spirit:village_adobe",
		"natures_spirit:village_coconut",
		"natures_spirit:village_cypress",
		"natures_spirit:village_wisteria",
		"archaeology_ruins:ocean_ruin",
		"alekiships:unfinished_sloop_birch",
		"alekiships:unfinished_sloop_cherry",
		"alekiships:unfinished_sloop_dark_oak",
		"alekiships:unfinished_sloop_oak",
		"alekiships:unfinished_sloop_spruce",
		"create_structures_arise:obsidiantemple",
		"fdbosses:geburah_arena",
		"fdbosses:chesed_arena",
		"fdbosses:malkuth_arena",
		"hopo:portal/overworld/portal_ruined",
		"idas:abandoned_lighthouse",
		"idas:abandoned_vineyard",
		"idas:abandonedhouse",
		"idas:ancient_mines",
		"idas:ancient_portal/ancient_portal",
		"idas:ancient_portal/nether_ancient_portal",
		"idas:ancient_statue/ancient_statue_desert",
		"idas:ancient_statue/ancient_statue_jungle",
		"idas:ancient_statue/ancient_statue_plains",
		"idas:animal_den/forest_den",
		"idas:animal_den/foxhound_den",
		"idas:animal_den/polar_bear_den",
		"idas:apothecary_abode",
		"idas:ars_nouveau/archmages_tower",
		"idas:bazaar",
		"idas:bearclaw_inn",
		"idas:beekeepers_house",
		"idas:botanist",
		"idas:brickhouse",
		"idas:castle",
		"idas:collectors_museum",
		"idas:cottage",
		"idas:desert_camp/desert_camp",
		"idas:desert_camp/desert_camp_bygwindswept",
		"idas:desert_camp/desert_camp_orange",
		"idas:desert_camp/desert_camp_red",
		"idas:desert_market/desert_market",
		"idas:desert_market/desert_market_orange",
		"idas:desert_market/desert_market_red",
		"idas:desert_pyramid",
		"idas:desert_ruins",
		"idas:dig_site/dig_site",
		"idas:dig_site/dig_site_desert",
		"idas:enchantingtower",
		"idas:farmhouse",
		"idas:fishermans_lodge",
		"idas:frozen_crypt",
		"idas:haunted_manor",
		"idas:hermits_hollow",
		"idas:hunters_cabin",
		"idas:iceandfire/dread_citadel",
		"idas:iceandfire/sirens_cove",
		"idas:labyrinth",
		"idas:lumber_camp/lumber_camp_acacia",
		"idas:lumber_camp/lumber_camp_birch",
		"idas:lumber_camp/lumber_camp_bopmahogany",
		"idas:lumber_camp/lumber_camp_bopredwood",
		"idas:lumber_camp/lumber_camp_bygmahogany",
		"idas:lumber_camp/lumber_camp_bygredwood",
		"idas:lumber_camp/lumber_camp_dark_oak",
		"idas:lumber_camp/lumber_camp_jungle",
		"idas:lumber_camp/lumber_camp_oak",
		"idas:lumber_camp/lumber_camp_spruce",
		"idas:mason_house",
		"idas:necromancers_spire",
		"idas:nether_pump_camp",
		"idas:nexus",
		"idas:pillager_camp",
		"idas:pillager_fortress",
		"idas:redhorn_guild",
		"idas:ruined_church",
		"idas:ruined_fort",
		"idas:ruined_well",
		"idas:ruins_of_the_deep",
		"idas:snifferhenge",
		"idas:sunken_ship/sunken_ship",
		"idas:sunken_ship/sunken_ship_coral",
		"idas:sunken_ship/sunken_ship_ruins",
		"idas:the_log",
		"idas:tinkers_citadel",
		"idas:tinkers_workshop",
		"idas:train_ruins",
		"idas:tree_of_wisdom",
		"idas:treetop_tavern",
		"idas:tudor_pub",
		"idas:underground_camp/underground_camp",
		"idas:underground_camp/underground_camp_deep",
		"idas:wacky_wares",
		"idas:washing_camp",
		"idas:windswept_shrine",
		"idas:winter_wagon",
		"idas:witches_treestump",
		"idas:wizard_tower",
		"integrated_villages:airship_village",
		"integrated_villages:cabin_village",
		"integrated_villages:clockwork_village",
		"integrated_villages:kutcha_village",
		"integrated_villages:marketstead_village",
		"integrated_villages:mediterranean_village",
		"integrated_villages:mossy_mounds",
		"integrated_villages:oasis_village",
		"integrated_villages:pirate_village",
		"integrated_villages:quark/minka_village",
		"integrated_villages:sunken_village",
		"integrated_villages:tavern_village",
		"kattersstructures:village_birch",
		"kattersstructures:village_cherry",
		"kattersstructures:village_jungle",
		"kattersstructures:village_mesa",
		"kattersstructures:village_ocean",
		"kattersstructures:village_sky",
		"kattersstructures:village_swamp",
		"structory_towers:ocean_pillar",
		"supplementaries:road_sign",
		"towns_and_towers:exclusives/pillager_outpost_classic",
		"towns_and_towers:exclusives/pillager_outpost_iberian",
		"towns_and_towers:exclusives/pillager_outpost_mediterranean",
		"towns_and_towers:exclusives/pillager_outpost_oriental",
		"towns_and_towers:exclusives/pillager_outpost_rustic",
		"towns_and_towers:exclusives/pillager_outpost_swedish",
		"towns_and_towers:exclusives/pillager_outpost_tudor",
		"towns_and_towers:exclusives/village_classic",
		"towns_and_towers:exclusives/village_iberian",
		"towns_and_towers:exclusives/village_mediterranean",
		"towns_and_towers:exclusives/village_nilotic",
		"towns_and_towers:exclusives/village_piglin",
		"towns_and_towers:exclusives/village_rustic",
		"towns_and_towers:exclusives/village_swedish",
		"towns_and_towers:exclusives/village_tudor",
		"towns_and_towers:exclusives/village_wandering_trader_camp",
		"towns_and_towers:pillager_outpost_badlands",
		"towns_and_towers:pillager_outpost_birch_forest",
		"towns_and_towers:pillager_outpost_desert",
		"towns_and_towers:pillager_outpost_flower_forest",
		"towns_and_towers:pillager_outpost_forest",
		"towns_and_towers:pillager_outpost_grove",
		"towns_and_towers:pillager_outpost_jungle",
		"towns_and_towers:pillager_outpost_meadow",
		"towns_and_towers:pillager_outpost_mushroom_fields",
		"towns_and_towers:pillager_outpost_ocean",
		"towns_and_towers:pillager_outpost_old_growth_taiga",
		"towns_and_towers:pillager_outpost_savanna",
		"towns_and_towers:pillager_outpost_savanna_plateau",
		"towns_and_towers:pillager_outpost_snowy_beach",
		"towns_and_towers:pillager_outpost_snowy_plains",
		"towns_and_towers:pillager_outpost_snowy_slopes",
		"towns_and_towers:pillager_outpost_snowy_taiga",
		"towns_and_towers:pillager_outpost_sparse_jungle",
		"towns_and_towers:pillager_outpost_sunflower_plains",
		"towns_and_towers:pillager_outpost_swamp",
		"towns_and_towers:pillager_outpost_taiga",
		"towns_and_towers:pillager_outpost_wooded_badlands",
		"towns_and_towers:village_badlands",
		"towns_and_towers:village_beach",
		"towns_and_towers:village_birch_forest",
		"towns_and_towers:village_flower_forest",
		"towns_and_towers:village_forest",
		"towns_and_towers:village_grove",
		"towns_and_towers:village_jungle",
		"towns_and_towers:village_meadow",
		"towns_and_towers:village_mushroom_fields",
		"towns_and_towers:village_ocean",
		"towns_and_towers:village_old_growth_taiga",
		"towns_and_towers:village_savanna_plateau",
		"towns_and_towers:village_snowy_slopes",
		"towns_and_towers:village_snowy_taiga",
		"towns_and_towers:village_sparse_jungle",
		"towns_and_towers:village_sunflower_plains",
		"towns_and_towers:village_swamp",
		"towns_and_towers:village_wooded_badlands",
		"trek:village/cherry",
		"trek:village/desert",
		"trek:village/mushroom",
		"trek:village/plains",
		"trek:village/savanna",
		"trek:village/snowy",
		"trek:village/swamp_vanilla",
		"trek:village/taiga",
		"betterarcheology:light_temple",
		"cataclysm:acropolis",
		"windswept:village_frozen",
		"dungeons_arise:small_blimp",
		"unusual_prehistory:mesozoic_fossil",
		"unusual_prehistory:paleozoic_fossil",
		"unusual_prehistory:petrified_tree",
		"repurposed_structures:village_ocean"
	);

	//IGNORE_BIOME_RADIUS_CHECK
	//<--- Ignore's population bias adjustments --->
	public static final List<String> ignoreBiomeRadiusCheck = ImmutableList.of(
		"ati_structures:old_fort",
		"bosses_of_mass_destruction:lich_tower",
		"block_factorys_bosses:yeti_hideout",
		"block_factorys_bosses:sandworm_nest",
		"eternal_starlight:portal_ruins_jungle",
		"eternal_starlight:portal_ruins_forest",
		"illagerinvasion:firecaller_hut",
		"illagerinvasion:illager_fort",
		"illagerinvasion:illusioner_tower",
		"loot_n_explore:glaze_tower",
		"medieval_buildings:fort",
		"medieval_buildings:tower",
		"mvs:small_pillager_tower",
		"mythsandlegends:graveyard",
		"piglet_structures:jungle_small_tower",
		"piglet_structures:plains_stony_tower",
		"qrafty:tower",
		"qrafty:birk_fort",
		"qrafty:fort",
		"repurposed_structures:ruins_land_cold",
		"repurposed_structures:ruins_land_hot",
		"repurposed_structures:ruins_land_icy",
		"repurposed_structures:ruins_land_warm",
		"structory:jungle_ruin",
		"structory:northern_ruin",
		"structory:ruin_grassy",
		"structory:swamp_ruin",
		"structory:dense_forest_ruin",
		"structory:taiga_ruin_surface",
		"structory_towers:ancient_temple",
		"structory_towers:end/end_tower",
		"structory_towers:engineer_tower",
		"structory_towers:farmer_outpost",
		"structory_towers:foraging_outpost",
		"structory_towers:great_toadstool",
		"structory_towers:lighthouse",
		"structory_towers:mirage_outpost",
		"structory_towers:nether/fortress_tower",
		"structory_towers:nether/strange_outpost",
		"structory_towers:nether/warped_outpost",
		"structory_towers:nomad_outpost",
		"structory_towers:ocean_pillar",
		"structory_towers:overgrown_mangrove",
		"structory_towers:pillager_lookout",
		"structory_towers:quarter_outpost",
		"structory_towers:sacred_relic_temple",
		"structory_towers:small_firetower",
		"structory_towers:taiga_outpost",
		"structory_towers:warped_greatsword",
		"structory_towers:wizard_tower",
		"trek:overworld/medium/ice_fort",
		"trek:overworld/medium/ruine_bateaux",
		"trek:overworld/medium/ruine_cabane",
		"trek:overworld/medium/ruine_tour",
		"u_desert:badlands/ambient/geyser",
		"u_desert:desert/archeology/ruin/house",
		"u_desert:desert/archeology/ruin/pit",
		"u_desert:desert/archeology/ruin/well",
		"u_desert:desert/adventure/pillager_outpost/abandoned",
		"u_desert:desert/adventure/pillager_outpost/standard",
		"wabi_sabi_structures:old_fort",
		"wabi_sabi_structures:vigilante_fort",
		"wabi_sabi_structures:concrete_mill_ruins_desert",
		"wabi_sabi_structures:concrete_mill_ruins_shore",
		"wabi_sabi_structures:lumpy_tower"
	);

    //FLATNESS_CHECK_SPRAWLING
    //<--- Flatness check used for villages and other sprawling (~5x5 chunk) structures --->
    public static final List<String> flatnessCheckSprawling = ImmutableList.of(
		"ati_structures:ati_stoneworks",
		"ati_structures:quarry",
        "betterarcheology:catacombs",
        "cataclysm:cursed_pyramid",
        "cataclysm:frosted_prison",
		"create_pillagers_arise:createpillagervillage",
		"dungeons_arise:illager_campsite",
		"dungeons_arise:merchant_campsite",
        "dungeons_arise:thornborn_towers",
        "dungeons_arise:desert_hall",
        "dungeons_arise:shiraz_palace",
		"dungeons_arise:mushroom_mines",
		"epic:witch_hut",
		"integrated_minecraft:ruined_fortress",
		"integrated_minecraft:scarlet_citadel",
		"idas:iceandfire/dread_citadel",
        "idas:collectors_museum",
        "idas:tinkers_citadel",
        "idas:bazaar",
		"kattersstructures:village_mesa",
        "letsdoaddon-structures:illager_mine",
		"qrafty:mushroom_village",
		"qrafty:mangrove_village",
		"nova_structures:ruin_town",
		"nova_structures:illager_manor",
		"nova_structures:lone_citadel",
        "takesapillage:bastille",
        "repurposed_structures:mansion_birch",
		"repurposed_structures:mansion_desert",
		"repurposed_structures:mansion_jungle",
		"repurposed_structures:mansion_mangrove",
		"repurposed_structures:mansion_oak",
		"repurposed_structures:mansion_savanna",
		"repurposed_structures:mansion_snowy",
		"repurposed_structures:mansion_taiga"
    );

    //FLATNESS_CHECK_MASSIVE
    //<--- Flatness check used for large-massive sized (~3x3 chunk) structures --->
    public static final List<String> flatnessCheckLarge = ImmutableList.of(
        "minecraft:trail_ruins",
        "additionalstructures:maya_temple",
		"ancientstructures:ruined_german_village",
		"ancientstructures:ruined_japanese_village",
        "archaeology_ruins:ruined_desert_pyramid",
		"archaeology_ruins:ruinedjungletemple",
        "ars_additions:arcane_library",
        "ars_nouveau:stalker_wilden_den",
		"ati_structures:arachnid_dwelling",
		"ati_structures:nomadic_camp",
        "idas:train_ruins",
        "idas:apothecary_abode",
        "idas:pillager_fortress",
        "integrated_minecraft:cyclops_lair",
        "betterarcheology:temple_jungle",
        "betterarcheology:stonehenge_grassy",
        "biomemakeover:mansion",
        "companions:companions_factory",
		"create_pillagers_arise:cathedral_create",
        "create_ltab:birch_structures",
		"create_ltab:railroad",
		"create_ltab:quarry",
        "dungeons_arise:bathhouse",
        "dungeons_arise:greenwood_pub",
        "dungeons_plus:snowy_temple",
        "feur_extension_fossil:skull_camp",
        "feur_extension_fossil:rex_camp",
        "feur_extension_fossil:fossil_arc",
        "feur_extension_fossil:fossil_dragon",
        "feur_extension_fossil:fossil_chest",
        "feur_extension_jungle:protector",
        "feur_extension_jungle:pyramid",
        "feur_extension_jungle:tree_ancestral",
        "feur_extension_jungle:tree_ancestral_house",
        "feur_extension_jungle:tree_double",
        "feur_extension_jungle:tree_giant",
        "feur_extension_jungle:tree_old",
        "feur_extension_jungle:tree_spline",
        "feur_extension_jungle:tree_stump",
        "goety:dark_manor",
		"hollowmarch:palm_haven",
		"hollowmarch:phantome_nest",
		"hollowmarch:pirates_fall",
		"hollowmarch:sultans_bazaar",
        "imst:caravan",
		"illagerstructures:illager_fort",
		"illagerstructures:monastery",
		"illagerstructures:smeltery",
		"kattersstructures:graveyard",
		"kattersstructures:windmill",
        "legendary_monsters:ancient_stronghold",
        "legendary_monsters:abandoned_crypt",
        "medieval_buildings:fort",
        "mvs:red_tower",
		"mvs:cathedral",
        "mvs:houses/large_warped_tower",
        "mythsandlegends:old_camp",
        "mythsandlegends:ancient_vestiges",
		"nova_structures:tavern_swamp",
        "structory:old_manor",
        "terramity:court_of_gnomes",
        "terramity:overgrown_facility",
        "terramity:snow_fort",
		"towns_and_towers:exclusives/pillager_outpost_rustic",
		"trek:village/mushroom",
        "trek:overworld/rare/wooden_manor",
        "trek:overworld/rare/abandoned_castle_pillager",
		"trek:overworld/rare/villager_castle"
    );

    //FLATNESS_CHECK_MEDIUM
    //<--- Flatness check used for medium-sized (~2x2 chunk) structures --->
    public static final List<String> flatnessCheckMedium = ImmutableList.of(
		"ati_structures:tavern",
		"ati_structures:ancient_temple",
		"ati_structures:desert_outpost",
		"ati_structures:fortified_temple",
		"ati_structures:granite_fort",
		"ati_structures:manor",
		"ati_structures:old_fort",
		"ati_structures:old_residence",
		"ati_structures:marble_chateau",
		"ati_structures:sinking_temple",
		"ati_structures:woodland_keep",
		"ati_structures:ruined_castle",
		"ati_structures:castillo",
		"ati_structures:dojo",
		"born_in_chaos_v1:dark_tower_plain",
		"born_in_chaos_v1:clown_caravan_plains",
		"born_in_chaos_v1:clown_caravan_savanna",
		"born_in_chaos_v1:clown_caravan_taiga",
		"block_factorys_bosses:sandworm_nest",
		"create_ltab:oak_house",
        "create_structures_arise:createlosttrainstation",
        "create_structures_arise:createcushercrane",
		//"create_structures_arise:obsidiantemple",
		"create_rustic_structures:rustic_barn",
        "eidolon:lab",
        "explorify:farmstead",
        "explorify:tavern",
        "explorify:campsite",
		"feur_extension_desert:oasis_big",
		"feur_extension_desert:oasis_small",
        "hexerei:owl_post_office",
        "idas:pillager_camp",
		"illagerinvasion:firecaller_hut",
		"illagerinvasion:illager_fort",
		"illagerinvasion:illusioner_tower",
		"illagerstructures:ruined_church",
        "imst:sunflower_farm",
        "imst:mangrove_hut",
		"jvs:froglord_lair",
		"jvs:illager_raid_camp",
		"mtr:badlands_temple",
        "mvs:nature/paths",
        "mvs:houses/flower_hole",
        "mvs:nature/oak_tree",
        "mvs:nature/big_oak_tree",
        "mvs:nature/cherry_tree",
        "mvs:nature/jungle_palm_tree",
        "mvs:nature/jungle_tree",
        "mvs:nature/dark_oak_tree",
        "mvs:nature/spruce_tree",
		"mvs:paths",
        "mvs:flower_hole",
        "mvs:oak_tree",
        "mvs:big_oak_tree",
        "mvs:cherry_tree",
        "mvs:jungle_palm_tree",
        "mvs:jungle_tree",
        "mvs:dark_oak_tree",
        "mvs:spruce_tree",
		"mythsandlegends:graveyard",
		"rpgstructures:mage_guild",
        "species:paleontology_dig_site",
		"trek:overworld/medium/oasis",
		"trek:overworld/rare/portal_sword",
		"trek:overworld/rare/abandoned_castle_pillager",
		"trek:overworld/medium/haunted_house",
		"trek:overworld/rare/pyramide_of_anubis",
		"trek:overworld/rare/wooden_manor",
		"trek:overworld/rare/villager_fortress",
        "wabi_sabi_structures:abandoned_small_castle",
		"wabi_sabi_structures:zen_chair_museum"
    );

    //FLATNESS_CHECK_Small
    //<--- Flatness check used for tiny-small sized (~1x1 chunk) structures --->
    public static final List<String> flatnessCheckSmall = ImmutableList.of(
		"bosses_of_mass_destruction:lich_tower",
		"block_factorys_bosses:yeti_hideout",
		"eternal_starlight:portal_ruins_jungle",
		"eternal_starlight:portal_ruins_forest",
		"qrafty:birk_fort"
    );

    //ADD_BEARD_THIN_ADAPTATION
    //<--- Add beard_thin adaptation to structures with none --->
    public static final List<String> addBeardThinAdaptation = ImmutableList.of(
		"ati_structures:ancient_temple",
		"ati_structures:ancient_vessel",
		"ati_structures:arachnid_dwelling",
		"ati_structures:ati_stoneworks",
		"ati_structures:castillo",
		"ati_structures:catalonian_castle",
		"ati_structures:dark_keep",
		"ati_structures:dark_tower",
		"ati_structures:deepslate_keep",
		"ati_structures:desert_outpost",
		"ati_structures:dojo",
		"ati_structures:fortified_temple",
		"ati_structures:gnome_hut",
		"ati_structures:granite_fort",
		"ati_structures:haunted_ruin",
		"ati_structures:herobrine_stronghold",
		"ati_structures:illager_homestead",
		"ati_structures:illager_tower",
		"ati_structures:jungle_grotto",
		"ati_structures:jungle_settlement",
		"ati_structures:manor",
		"ati_structures:marble_chateau",
		"ati_structures:monastery_tower",
		"ati_structures:mosque",
		"ati_structures:mud_tower",
		"ati_structures:nomadic_camp",
		"ati_structures:old_fort",
		"ati_structures:old_home",
		"ati_structures:old_residence",
		"ati_structures:overgrown_outpost",
		"ati_structures:quarry",
		"ati_structures:rotten_log",
		"ati_structures:rotting_temple",
		"ati_structures:ruined_castle",
		"ati_structures:small_keep",
		"ati_structures:spider_nest",
		"ati_structures:steam_house",
		"ati_structures:storage_house",
		"ati_structures:storage_shack",
		"ati_structures:stray_ruins",
		"ati_structures:tavern",
		"ati_structures:villager_inn",
		"ati_structures:wither_keep",
		"ati_structures:woodland_keep",
        "create_structures_arise:create_ruined_castle",
		"create_structures_arise:obsidiantemple",
		"imst:train_station",
		"mushroomquest:witch_cottage_redone",
        "mythsandlegends:ancient_vestiges",
		"mythsandlegends:graveyard",
		"mythsandlegends:old_camp",
        "species:libra",
		"species:paleontology_dig_site",
		"trek:overworld/medium/oasis",
		"trek:overworld/medium/spruce_cottage",
        "wabi_sabi_structures:frost_reactor_plant"
    );

    //ADD_BURY_ADAPTATION
    //<--- Add bury adaptation to structures --->
    public static final List<String> addBuryAdaptation = ImmutableList.of(

    );

    //ADD_WATERLOGGING
    //<--- Remove ignore_waterlogging from structures, making them fill with water --->
    public static final List<String> addWaterlogging = ImmutableList.of(
		"create_structures_arise:obsidiantemple"
    );

    //REMOVE_WATERLOGGING
    //<--- Add ignore_waterlogging to structures, making them immune to waterlogging --->
    public static final List<String> removeWaterlogging = ImmutableList.of(
		"illagerstructures:submarine"
    );

    //ADJUSTED_Y_LEVEL_SHALLOW
    //<--- Offset the start_height of underground structures that peak out above-ground --->
    public static final List<String> adjustedYLevelShallow = ImmutableList.of(
		"alexscaves:underground_cabin",
		"ati_structures:rotting_temple_underground",
		"bettermineshafts:mineshaft_acacia",
		"bettermineshafts:mineshaft_desert",
		"bettermineshafts:mineshaft_dripstone",
		"bettermineshafts:mineshaft_ice",
		"bettermineshafts:mineshaft_jungle",
		"bettermineshafts:mineshaft_lush",
		"bettermineshafts:mineshaft_mesa",
		"bettermineshafts:mineshaft_mushroom",
		"bettermineshafts:mineshaft_oak",
		"bettermineshafts:mineshaft_overgrown",
		"bettermineshafts:mineshaft_red_desert",
		"bettermineshafts:mineshaft_spruce",
		"bettermineshafts:mineshaft_spruce_snowy",
		"create_structures_arise:createmonsterroom",
		"kattersstructures:village_underground"
    );

    //ADJUSTED_Y_LEVEL_DEEP
    //<--- Offset the start_height of underground structures to spawn near bedrock --->
    public static final List<String> adjustedYLevelDeep = ImmutableList.of(
		"ati_structures:warden_monument",
		"dungeons_arise:plague_asylum",
		"dungeons_arise:scorched_mines",
		"dungeons_arise:mining_system",
		"hopo:mineshaft/acacia_mineshaft",
		"hopo:mineshaft/bamboo_mineshaft",
		"hopo:mineshaft/biomes/dripstone_mineshaft",
		"hopo:mineshaft/biomes/lush_mineshaft",
		"hopo:mineshaft/birch_mineshaft",
		"hopo:mineshaft/cherry_mineshaft",
		"hopo:mineshaft/dark_oak_mineshaft",
		"hopo:mineshaft/deepslate_mineshaft",
		"hopo:mineshaft/jungle_mineshaft",
		"hopo:mineshaft/mangrove_mineshaft",
		"hopo:mineshaft/mud_mineshaft",
		"hopo:mineshaft/oak_mineshaft",
		"hopo:mineshaft/spruce_mineshaft",
		"hopo:mineshaft/stone_mineshaft",
		"hopo:portal/overworld/portal_deepslate",
		"hopo:portal/overworld/portal_dripstone",
		"hopo:portal/overworld/portal_lush",
		"mtr:stronghold"
    );

    //IS_Early_SPAWN_STEP
    //<--- Change the spawn step to "underground_structures" to place it earlier than most other structures --->
    public static final List<String> earlySpawnStep = ImmutableList.of(
		"create_structures_arise:obsidiantemple",
		"hopo:underwater/underwater_city"
    );

    //IS_LATE_SPAWN_STEP
    //<--- Change the spawn step to "fluid_springs" to place it later than all other structures --->
    public static final List<String> lateSpawnStep = ImmutableList.of(
		"kattersstructures:village_birch",
		"kattersstructures:village_cherry",
		"kattersstructures:village_jungle",
		"kattersstructures:village_mesa",
		"kattersstructures:village_ocean",
		"kattersstructures:village_sky",
		"kattersstructures:village_swamp",
		"kattersstructures:village_underground"
    );

    //HAS_VILLAGE_FIX
    //<--- Flag specific to integrated villages - fixes start height and terrain adaptation --->
    public static final List<String> hasVillageFix = ImmutableList.of(
		"integrated_villages:cabin_village",
		"integrated_villages:clockwork_village",
		"integrated_villages:kutcha_village",
		"integrated_villages:marketstead_village",
		"integrated_villages:mediterranean_village",
		"integrated_villages:mossy_mounds",
		"integrated_villages:oasis_village",
		"integrated_villages:quark/minka_village",
		"integrated_villages:tavern_village",
		"dungeons_arise:merchant_campsite",
		"dungeons_arise:illager_campsite"
    );

	//IS_RARE_FROZEN_CAVES
	//<--- Forces structures to spawn exclusively in frozen cave biomes --->
	public static final List<String> forceFrozenCaves = ImmutableList.of(
		"loot_n_explore:frostmonarch_temple",
		"loot_n_explore:glacial_tomb"
	);

	//IS_SANDY_CAVES
	//<--- Forces structures to spawn exclusively in sandy cave biomes --->
	public static final List<String> forceSandyCaves = ImmutableList.of(
		"bosses_of_mass_destruction:void_blossom"
	);

	//IS_VOLCANIC_CRATER
	//<--- Forces structures to spawn exclusively in volcanic craters --->
	public static final List<String> forceVolcanicCrater = ImmutableList.of(
		"hopo:portal/overworld/portal_ruined"
	);

	//IS_DEEP_DARK
	//<--- Forces structures to spawn exclusively in the deep dark --->
	public static final List<String> forceDeepDark = ImmutableList.of(
		"minecraft:ancient_city",
		"ati_structures:sculk_deposit",
		"ati_structures:warden_monument",
		"philipsruins:sculk_dungeon",
		"wabi_sabi_structures:forgotten_remnants"
	);

	//IS_CULTIVATED_FIELDS
	//<--- Forces structures to spawn exclusively in the deep dark --->
	public static final List<String> forceCultivatedFields = ImmutableList.of(

	);

	//IS_CULTIVATED_FIELDS
	//<--- Forces structures to spawn exclusively in the deep dark --->
	public static final List<String> forceTropicalIsland = ImmutableList.of(
		"natures_spirit:village_coconut"
	);

	//IS_BIRCH_FOREST
	//<--- Forces structures to spawn exclusively in full birch forests --->
	public static final List<String> forceBirchForest = ImmutableList.of(
		"additionalstructures:birch_log",
		"ati_structures:marble_chateau",
		"combat_structures_update:birch_cottage",
		"create_ltab:birch_structures",
		"explorify:supply_cache/birch",
		"imst:lumberjack/birch_hut",
		"imst:birch_hut",
		"kattersstructures:birch_tree",
		"luistercorp:fallen_log_birch",
		"mvs:dead_tree_birch",
		"nova_structures:well_birch",
		"nova_structures:tavern_birch",
		"nova_structures:firewatch_tower_birch",
		"nova_structures:remnant_birch_graveyard",
		"nova_structures:village_birch",
		"qrafty:birk_fort",
		"repurposed_structures:village_birch",
		"towns_and_towers:village_birch_forest",
		"trek:overworld/medium/birch_fort",
		"wabi_sabi_structures:tall_birch_house"
	);

	//IS_CHERRY_FOREST
	//<--- Forces structures to spawn exclusively in forests with vanilla cherry trees --->
	public static final List<String> forceCherryForest = ImmutableList.of(
		"create_ltab:cherry_house",
		"hopo:mineshaft/cherry_mineshaft",
		"kattersstructures:cherry_tree",
		"kattersstructures:village_cherry",
		"luistercorp:fallen_log_cherry",
		"mvs:lantern/small_cherry_lantern",
		"mvs:nature/cherry_tree",
		"mvs:dead_tree/cherry",
		"mvs:small_cherry_lantern",
		"mvs:cherry_tree",
		"mvs:cherry",
		"mvs:dead_tree_cherry",
		"nova_structures:firewatch_tower_cherry",
		"nova_structures:tavern_cherry",
		"repurposed_structures:village_cherry",
		"taxtg:giant_cherryblossom_tree",
		"taxtg:giant_cherryblossom_tree_1"
	);

	//IS_DIFFICULTY_FOUR
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultyFour = ImmutableList.of(
		"archaeology_ruins:ruined_desert_pyramid",
		"archaeology_ruins:ruinedjungletemple",
		"archeological:ancient_vault_desert_ruins",
		"archeological:ancient_vault_frozen_ruins","archeological:ancient_vault_wasteland_ruins",
		"archeological:desert_ruins_large",
		"archeological:frozen_ruins_large",
		"archeological:wasteland_ruins_large",
		"ati_structures:old_fort",
		"betterarcheology:temple_jungle",
		"illagerinvasion:illusioner_tower",
		"illagerinvasion:illager_fort",
		"nova_structures:creeping_crypt",
		"repurposed_structures:mansion_desert",
		"repurposed_structures:mansion_jungle",
		"repurposed_structures:mansion_snowy",
		"trek:overworld/medium/haunted_house"
	);

	//IS_DIFFICULTY_FIVE
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultyFive = ImmutableList.of(
		"bosses_of_mass_destruction:lich_tower",
		"create_structures_arise:obsidiantemple",
		"dungeons_arise:illager_fort",
		"mythsandlegends:graveyard"
	);

	//IS_DIFFICULTY_SIX
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultySix = ImmutableList.of(
		"minecraft:mansion",
		"bosses_of_mass_destruction:void_blossom",
		"bosses_of_mass_destruction:gauntlet_arena",
		"block_factorys_bosses:sandworm_nest",
		"block_factorys_bosses:yeti_hideout",
		"cataclysm:cursed_pyramid",
		"cataclysm:frosted_prison",
		"dungeons_arise:shiraz_palace",
		"nova_structures:lone_citadel"
	);

	//IS_DIFFICULTY_SEVEN
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultySeven = ImmutableList.of(
		"minecraft:ancient_city",
		"block_factorys_bosses:dragon_tower",
		"bosses_of_mass_destruction:obsidilith_arena",
		"cataclysm:burning_arena",
		"cataclysm:ruined_citadel",
		"cataclysm:soul_black_smith",
		"eternal_starlight:golem_forge",
		"eternal_starlight:cursed_garden",
		"integrated_stronghold:stronghold"
	);

	//IS_DIFFICULTY_EIGHT
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultyEight = ImmutableList.of(
		"fdbosses:chesed_arena",
		"fdbosses:malkuth_arena",
		"withershrine:shrines"
	);

	//IS_DIFFICULTY_NINE
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultyNine = ImmutableList.of(

	);

	//IS_DIFFICULTY_TEN
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> forceDifficultyTen = ImmutableList.of(

	);

	//IS_BOSS_STRUCTURE
	//<--- Override built-in difficulty calculation for individual structures --->
	public static final List<String> bossStructures = ImmutableList.of(
		"block_factorys_bosses:sandworm_nest",
		"block_factorys_bosses:yeti_hideout",
		"block_factorys_bosses:dragon_tower",
		"block_factorys_bosses:underworld_arena",
		"bosses_of_mass_destruction:lich_tower",
		"bosses_of_mass_destruction:obsidilith_arena",
		"bosses_of_mass_destruction:gauntlet_arena",
		"bosses_of_mass_destruction:void_blossom",
		"eternal_starlight:golem_forge",
		"eternal_starlight:cursed_garden",
		"fdbosses:chesed_arena",
		"fdbosses:malkuth_arena",
		"mythsandlegends:graveyard",
		"withershrine:shrines"
	);


//<------------------------------------------------------BIOMES------------------------------------------------------>
//<------------------------------------------------------------------------------------------------------------------>
    //REMOVE_SNOW
    //<--- Simply removes any foliage/grass color overrides to leave coloration up to the climate values --->
    public static final List<String> removeSnow = ImmutableList.of(
		"wythers:jade_highlands"
    );

	//REMOVE_VEGETATION_COLOR
    //<--- Simply removes any foliage/grass color overrides to leave coloration up to the climate values --->
    public static final List<String> removeBiomeVegetationColor = ImmutableList.of(
		"dreamwoods:blooming_grove",
		"dreamwoods:poplar_grove",
		"regions_unexplored:shrubland",
		"regions_unexplored:dry_bushland",
		"regions_unexplored:tropical_river",
		"wythers:spring_flower_fields"
    );

    //ADD_PRAIRIE_GRASS_COLOR
    //<--- Simply replaces any grass color overrides with a light dry Prairie grass coloring --->
    public static final List<String> addPrairieGrassColor = ImmutableList.of(
		"natures_spirit:heather_fields",
		"natures_spirit:boreal_taiga",
		"wythers:berry_bog"
    );

    //ADD_STEPPE_GRASS_COLOR
    //<--- Simply replaces any grass color overrides with a brown Steppe grass coloring --->
    public static final List<String> addSteppeGrassColor = ImmutableList.of(
		"dreamwoods:tundra",
		"regions_unexplored:pumpkin_fields"
    );

	//ADD_MEADOW_VEGETATION_COLOR
	//<--- Simply replaces any grass/foliage color overrides and sets the temperate/downfall values to match vanilla Meadow --->
	public static final List<String> addMeadowGrassColor = ImmutableList.of(
		//"natures_spirit:alpine_clearings",
		"natures_spirit:alpine_highlands",
		"regions_unexplored:pine_taiga",
		"regions_unexplored:towering_cliffs",
		"regions_unexplored:highland_fields",
		"dreamwoods:flowering_fields"
	);

	//ADD_MARSH_VEGETATION_COLOR
	//<--- Simply replaces any grass/foliage color overrides with a dark green marsh coloring --->
	public static final List<String> addMarshVegetationColor = ImmutableList.of(
		"dreamwoods:weeping_grove"
		//"dreamwoods:morass"
	);

	//ADD_BAYOU_VEGETATION_COLOR
	//<--- Simply replaces any grass/foliage color overrides with a dark blue-green bayou coloring --->
	public static final List<String> addBayouVegetationColor = ImmutableList.of(
		"regions_unexplored:bayou",
		"regions_unexplored:old_growth_bayou"
	);

	//ADD_COOL_PLAINS_VEGETATION_COLOR
	//<--- Simply replaces any grass/foliage color overrides with a muted yellow-green coloring --->
	public static final List<String> addCoolPlainsVegetationColor = ImmutableList.of(
		"dreamwoods:prairie",
		"dreamwoods:lush_grassland",
		"regions_unexplored:grassland",
		//"regions_unexplored:prairie",
		"dreamwoods:autumnal_hills"
	);

	//ADD_PLAINS_VEGETATION_COLOR
	//<--- Simply replaces any grass/foliage color overrides with a muted green coloring --->
	public static final List<String> addPlainsVegetationColor = ImmutableList.of(
		"dreamwoods:poplar_slopes",
		"natures_spirit:floral_ridges",
		"regions_unexplored:flower_fields",
		"regions_unexplored:bamboo_forest"
	);

    //ADD_SAVANNA_VEGETATION_COLOR
    //<--- Simply replaces any grass/foliage color overrides with a yellow-brown savanna coloring --->
    public static final List<String> addSavannaVegetationColor = ImmutableList.of(
		"minecraft:badlands",
		"minecraft:eroded_badlands",
		"minecraft:wooded_badlands",
		"wythers:badlands_desert"
    );
}
