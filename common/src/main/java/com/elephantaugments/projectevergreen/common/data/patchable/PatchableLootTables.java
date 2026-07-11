package com.elephantaugments.projectevergreen.common.data.patchable;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class PatchableLootTables extends IPatchableList {

    //public LinkedHashMap<String, ?> Data = new LinkedHashMap<>();

    public static final String REGISTRY_PATH = "loot_table/";

    public PatchableLootTables() {
        super(defaultSupported, REGISTRY_PATH);
    }

    @Override
    protected void mapPatchableData(SortedSet<String> ids) {

    }

    @Override
    public void addPatchableData(String id) {

    }

    public static final SortedSet<String> defaultSupported = new TreeSet<>(Arrays.asList(
        "artifacts:entities/mimic",
		"aquaculture:box/neptunes_bounty_junk",
		"aquaculture:box/neptunes_bounty",
		"aquaculture:box/lockbox",
		"aquaculture:box/treasure_chest",
		"aquaculture:box/box",
		"dungeons_arise:chests/fishing_hut/fishing_hut_barrels",
		"dungeons_arise:chests/illager_fort/illager_fort_treasure",
		"farmersdelight:chests/fd_village_butcher",
		"farmersdelight:chests/fd_bastion_treasure",
		"farmersdelight:chests/fd_end_city_treasure",
		"farmersdelight:chests/fd_village_snowy_house",
		"farmersdelight:chests/fd_simple_dungeon",
		"farmersdelight:chests/fd_ruined_portal",
		"farmersdelight:chests/fd_shipwreck_supply",
		"farmersdelight:chests/fd_village_taiga_house",
		"farmersdelight:chests/fd_bastion_hoglin_stable",
		"farmersdelight:chests/fd_village_savanna_house",
		"farmersdelight:chests/fd_abandoned_mineshaft",
		"farmersdelight:chests/fd_pillager_outpost",
		"farmersdelight:chests/fd_village_plains_house",
		"farmersdelight:chests/fd_village_desert_house",
		"kaleidoscope_cookery:chest/village_chest",
		"kaleidoscope_cookery:chest/village_hide_chest",
		"minecraft:chests/illager_mansion/generic",
		"minecraft:chests/trial_chambers/intersection",
		"minecraft:pots/trial_chambers/corridor",
		"minecraft:chests/underwater_ruin_small",
		"minecraft:chests/dye",
		"minecraft:chests/mineral",
		"minecraft:chests/igloo_chest",
		"minecraft:chests/ancient_city",
		"minecraft:chests/illager_mansion/ravager_chest",
		"minecraft:chests/illager_mansion/pillager_chest",
		"minecraft:chests/illager_mansion/witch_chest",
		"minecraft:chests/end_city_treasure",
		"minecraft:chests/trial_chambers/reward_rare",
		"minecraft:chests/abandoned_mineshaft",
		"minecraft:chests/stronghold_corridor",
		"minecraft:chests/buried_treasure",
		"minecraft:chests/random",
		"minecraft:chests/nether_fortress/fort_inside_generic",
		"minecraft:chests/bastion_bridge",
		"minecraft:chests/trial_chambers/supply",
		"minecraft:chests/woodland_mansion",
		"minecraft:chests/illager_mansion/wool",
		"minecraft:chests/firewell_d",
		"minecraft:chests/farm_drop",
		"minecraft:chests/trial_chambers/corridor",
		"minecraft:chests/ancient_city_ice_box",
		"minecraft:chests/simple_dungeon",
		"minecraft:chests/trial_chambers/reward_ominous_rare",
		"minecraft:chests/shipwreck_supply",
		"minecraft:chests/trial_chambers/intersection_barrel",
		"minecraft:chests/shipwreck_treasure",
		"minecraft:chests/trash",
		"minecraft:chests/nether_fortress/fort_inside",
		"minecraft:chests/spawn_bonus_chest",
		"minecraft:chests/stone",
		"minecraft:chests/bastion_other",
		"minecraft:chests/illager_mansion/map_chest",
		"minecraft:chests/trial_chambers/reward",
		"minecraft:chests/jungle_temple_treasure",
		"minecraft:chests/trial_chambers/reward_ominous_common",
		"minecraft:chests/pillager_outpost",
		"minecraft:chests/illager_mansion/library_chest",
		"minecraft:chests/bastion_treasure",
		"minecraft:chests/shipwreck_map",
		"minecraft:chests/trial_chambers/reward_ominous_unique",
		"minecraft:chests/nether_bridge",
		"minecraft:chests/trial_chambers/reward_unique",
		"minecraft:chests/illager_mansion/kitchen",
		"minecraft:chests/bastion_hoglin_stable",
		"minecraft:chests/trial_chambers/reward_ominous",
		"minecraft:chests/illager_mansion/evoker_chest",
		"minecraft:chests/illager_mansion/smithing_room",
		"minecraft:chests/chest_level_1",
		"minecraft:chests/chest_level_2",
		"minecraft:chests/chest_level_3",
		"minecraft:chests/stronghold_library",
		"minecraft:chests/illager_mansion/ancient_city_raid_chest",
		"minecraft:chests/basic_chest",
		"minecraft:chests/stronghold_crossing",
		"minecraft:chests/shater",
		"minecraft:chests/wood",
		"minecraft:chests/desert_pyramid",
		"minecraft:chests/underwater_ruin_big",
		"minecraft:chests/illager_mansion/secret_room",
		"minecraft:chests/illager_mansion/vindicator_chest",
		"minecraft:chests/trial_chambers/reward_common",
		"minecraft:chests/jungle_temple_dispenser",
		"supplementaries:loot/urn_loot/uncommon",
		"supplementaries:loot/urn_loot/rare",
		"supplementaries:loot/urn_loot/common",
		"supplementaries:loot/urn_loot/epic",
		"structory:outcast/boat/loot",
		"veggiesdelight:chests/vd_wagon_plains",
		"veggiesdelight:chests/vd_wagon_savanna",
		"veggiesdelight:chests/vd_wagon_desert",
		"veggiesdelight:chests/vd_wagon_taiga",
		"veggiesdelight:chests/vd_wagon_snowy",
		"wizards:chests/village_wizard"
    ));
}
