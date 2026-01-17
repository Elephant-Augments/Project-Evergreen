package com.elephantaugments.projectevergreen.common.data.defaults;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class DefaultStructureFixes {
    public static final String SAFE_STRUCTURE_TYPE = "safe_structure_type";
    public static final String FLATNESS_CHECK_WIDE = "flatness_check_wide";
    public static final String FLATNESS_CHECK_NARROW = "flatness_check_narrow";
    public static final String FLATNESS_CHECK_SPRAWLING = "flatness_check_sprawling";
    public static final String ADD_TERRAIN_ADAPTATION = "add_terrain_adaptation";
    public static final String ADJUST_Y_LEVEL = "adjust_y_level";
    
    public static ArrayListMultimap<String, String> parseStructures() {
		ArrayListMultimap<String, String> structuresByFix = ArrayListMultimap.create();
        safeStructureType.forEach(s -> structuresByFix.put(SAFE_STRUCTURE_TYPE, s));
        flatnessCheckWide.forEach(s -> structuresByFix.put(FLATNESS_CHECK_WIDE, s));
        flatnessCheckNarrow.forEach(s -> structuresByFix.put(FLATNESS_CHECK_NARROW, s));
        addTerrainAdaptation.forEach(s -> structuresByFix.put(ADD_TERRAIN_ADAPTATION, s));
        adjustYLevel.forEach(s -> structuresByFix.put(ADJUST_Y_LEVEL, s));
        return structuresByFix;
    }

    //CONDITIONAL_DISABLE
    //<--- Structure sets to disable individually --->
    public static final SortedSet<String> conditionalDisable = new TreeSet<>(Arrays.asList(
        "minecraft:villages",
        "minecraft:ocean_monuments",
        "minecraft:pillager_outposts",
		"minecraft:ruined_portals",
        "ati_structures:aboveground_large",
		"ati_structures:aboveground_small",
        "dungeons_arise:major_structures",
		"formationsoverworld:uncommon",
		"formationsoverworld:rare",
        "integrated_villages:air_villages",
		"integrated_villages:regular_villages",
        "idas:idas_rare",
        "idas:idas_underground_rare",
        "nova_structures:firewatch_towers",
		"philipsruins:ocean_fortress_main",
        "towns_and_towers:towers",
        "towns_and_towers:towns",
        "structory:ruin_quiet",
        "repurposed_structures:outposts_overworld",
        "repurposed_structures:monuments_overworld",
        "repurposed_structures:villages_overworld",
        "repurposed_structures:cities_overworld",
        "lios_outlandish_villages:spiral_tower_village",
		"lios_outlandish_villages:spiral_tower_village_sea",
        "betterdeserttemples:desert_temples",
        "betterjungletemples:jungle_temples",
		"trek:overworld/very_common",
		"trek:overworld/medium",
		"trek:overworld/very_rare",
		"trek:overworld/rare"
    ));

    //SAFE_STRUCTURE_TYPE
    //<--- Safe structure type used for structures that break when integrated_api:generic_structure is used --->
    public static final List<String> safeStructureType = ImmutableList.of(
        "beautify:botanist_house_savanna",
        "letsdoaddon-structures:mangrove_hut",
        "letsdoaddon-structures:swamp_well"
    );

    //FLATNESS_CHECK_SPRAWLING
    //<--- Flatness check used for villages and other sprawling structures --->
    public static final List<String> flatnessCheckSprawling = ImmutableList.of(
        "bosses_of_mass_destruction:lich_tower",
        "bosses_of_mass_destruction:gauntlet_arena",
        "betterarcheology:catacombs",
        "cataclysm:cursed_pyramid",
        "cataclysm:frosted_prison",
        "dungeons_arise:thornborn_towers",
        "dungeons_arise:desert_hall",
        "dungeons_arise:shiraz_palace",
        "idas:collectors_museum",
        "idas:tinkers_citadel",
        "idas:bazaar",
        "letsdoaddon-structures:illager_mine",
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

    //FLATNESS_CHECK_WIDE
    //<--- Flatness check used for large-massive sized structures --->
    public static final List<String> flatnessCheckWide = ImmutableList.of(
        "minecraft:trail_ruins",
        "additionalstructures:maya_temple",
        "archaeology_ruins:ruined_desert_pyramid",
		"archaeology_ruins:ruinedjungletemple",
        "ars_additions:arcane_library",
        "ars_nouveau:stalker_wilden_den",
        "idas:train_ruins",
        "idas:apothecary_abode",
        "idas:pillager_fortress",
        "integrated_minecraft:cyclops_lair",
        "betterarcheology:temple_jungle",
        "betterarcheology:stonehenge_grassy",
        "biomemakeover:mansion",
        "companions:companions_factory",
        "create_ltab:birch_structures",
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
        "imst:caravan",
        "legendary_monsters:ancient_stronghold",
        "legendary_monsters:abandoned_crypt",
        "medieval_buildings:fort",
        "mvs:red_tower",
        "mvs:houses/large_warped_tower",
        "mythsandlegends:old_camp",
        "mythsandlegends:ancient_vestiges",
        "mythsandlegends:graveyard",
        "structory:old_manor",
        "terramity:court_of_gnomes",
        "terramity:overgrown_facility",
        "terramity:snow_fort",
		"towns_and_towers:exclusives/pillager_outpost_rustic",
        "trek:overworld/rare/wooden_manor",
        "trek:overworld/rare/abandoned_castle_pillager",
        "wabi_sabi_structures:zen_chair_museum"
    );

    //FLATNESS_CHECK_NARROW
    //<--- Flatness check used for tiny-small sized structures --->
    public static final List<String> flatnessCheckNarrow = ImmutableList.of(
        "born_in_chaos_v1:dark_tower_plain",
        "born_in_chaos_v1:clown_caravan_plains",
        "born_in_chaos_v1:clown_caravan_savanna",
        "born_in_chaos_v1:clown_caravan_taiga",
        "create_structures_arise:createlosttrainstation",
        "create_structures_arise:createcushercrane",
        "eidolon:lab",
        "explorify:farmstead",
        "explorify:tavern",
        "explorify:campsite",
        "hexerei:owl_post_office",
        "idas:pillager_camp",
        "imst:sunflower_farm",
        "imst:mangrove_hut",
        "mvs:nature/paths",
        "mvs:houses/flower_hole",
        "mvs:nature/oak_tree",
        "mvs:nature/big_oak_tree",
        "mvs:nature/cherry_tree",
        "mvs:nature/jungle_palm_tree",
        "mvs:nature/jungle_tree",
        "mvs:nature/dark_oak_tree",
        "mvs:nature/spruce_tree",
        "species:paleontology_dig_site",
        "wabi_sabi_structures:abandoned_small_castle"
    );

    //ADD_TERRAIN_ADAPTATION
    //<--- Add beard_thin adaptation to structures with none --->
    public static final List<String> addTerrainAdaptation = ImmutableList.of(
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
		"mushroomquest:witch_cottage_redone",
        "mythsandlegends:ancient_vestiges",
		"mythsandlegends:graveyard",
		"mythsandlegends:old_camp",
        "species:libra",
		"species:paleontology_dig_site",
        "wabi_sabi_structures:frost_reactor_plant"
    );

    //ADJUST_Y_LEVEL
    //<--- Flatness check used for tiny-small sized structures --->
    public static final SortedSet<String> adjustYLevel = new TreeSet<>(Arrays.asList(
        
    ));
}
