package com.elephantaugments.projectevergreen.neoforge.api;

import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class LimitedSpawnHandler {
    private LimitedSpawnHandler() {}

    public static void rebuildFromRegistry(MinecraftServer server) {
        LimitedSpawnRules.clear();
        server.registryAccess().registry(NeoForgeRegistries.Keys.STRUCTURE_MODIFIERS).ifPresent(registry -> {
            for (var modifier : registry) {
                if (!(modifier instanceof AddLimitedSpawnsStructureModifier limited)) {
                    continue;
                }
                Set<EntityType<?>> types = limited.spawners().stream()
                        .map(spawner -> spawner.type)
                        .collect(Collectors.toUnmodifiableSet());
                for (Holder<Structure> structure : limited.structures()) {
                    structure.unwrapKey().ifPresent(key ->
                            LimitedSpawnRules.register(key, limited.limit(), limited.centerFactor(), types));
                }
            }
        });
    }

    public static void onPositionCheck(MobSpawnEvent.PositionCheck event) {
        if (event.getSpawnType() != MobSpawnType.NATURAL) {
            return;
        }
        if (LimitedSpawnRules.isEmpty()) {
            return;
        }

        EntityType<?> type = event.getEntity().getType();
        if (!LimitedSpawnRules.tracks(type)) {
            return;
        }

        ServerLevel level = event.getLevel().getLevel();
        BlockPos pos = BlockPos.containing(event.getX(), event.getY(), event.getZ());

        for (var entry : LimitedSpawnRules.all().entrySet()) {
            ResourceKey<Structure> structureKey = entry.getKey();
            LimitedSpawnRules.Rule rule = entry.getValue();
            if (!rule.appliesTo(type)) {
                continue;
            }

            StructureStart start = level.structureManager().getStructureWithPieceAt(
                    pos, holder -> holder.is(structureKey));
            if (start == null || !start.isValid()) {
                continue;
            }

            BoundingBox box = start.getBoundingBox();
            if (!isNearCenter(pos, box, rule.centerFactor())) {
                event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
                return;
            }

            AABB aabb = AABB.of(box);
            int living = level.getEntities(type, aabb, Entity::isAlive).size();
            if (living >= rule.limit()) {
                event.setResult(MobSpawnEvent.PositionCheck.Result.FAIL);
                return;
            }

            return;
        }
    }

    private static boolean isNearCenter(BlockPos pos, BoundingBox box, float centerFactor) {
        BlockPos center = box.getCenter();
        int halfX = Math.max(box.getXSpan() / 2, 1);
        int halfZ = Math.max(box.getZSpan() / 2, 1);
        int maxOffsetX = Math.max(1, Math.round(halfX * centerFactor));
        int maxOffsetZ = Math.max(1, Math.round(halfZ * centerFactor));
        return Math.abs(pos.getX() - center.getX()) <= maxOffsetX
                && Math.abs(pos.getZ() - center.getZ()) <= maxOffsetZ;
    }
}
