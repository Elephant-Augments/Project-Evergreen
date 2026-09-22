package com.elephantaugments.projectevergreen.neoforge.api;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class LimitedSpawnRules {
    private static final ConcurrentHashMap<ResourceKey<Structure>, Rule> RULES = new ConcurrentHashMap<>();
    private static final Set<EntityType<?>> TRACKED_TYPES = ConcurrentHashMap.newKeySet();

    private LimitedSpawnRules() {}

    public static void register(ResourceKey<Structure> structure, int limit, float centerFactor, Set<EntityType<?>> entityTypes) {
        Rule rule = new Rule(limit, centerFactor, Set.copyOf(entityTypes));
        RULES.put(structure, rule);
        TRACKED_TYPES.addAll(entityTypes);
    }

    public static Rule get(ResourceKey<Structure> structure) {
        return RULES.get(structure);
    }

    public static Map<ResourceKey<Structure>, Rule> all() {
        return Collections.unmodifiableMap(RULES);
    }

    public static boolean isEmpty() {
        return RULES.isEmpty();
    }

    public static boolean tracks(EntityType<?> type) {
        return TRACKED_TYPES.contains(type);
    }

    public static void clear() {
        RULES.clear();
        TRACKED_TYPES.clear();
    }

    public record Rule(int limit, float centerFactor, Set<EntityType<?>> entityTypes) {
        public boolean appliesTo(EntityType<?> type) {
            return entityTypes.contains(type);
        }
    }
}
