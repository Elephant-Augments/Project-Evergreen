package com.elephantaugments.projectevergreen.neoforge.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.neoforged.neoforge.common.world.ModifiableStructureInfo;
import net.neoforged.neoforge.common.world.StructureModifier;
import net.neoforged.neoforge.common.world.StructureSettingsBuilder;

public record AddLimitedSpawnsStructureModifier(
        HolderSet<Structure> structures,
        List<SpawnerData> spawners,
        int limit,
        float centerFactor) implements StructureModifier {

    public static final MapCodec<AddLimitedSpawnsStructureModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            RegistryCodecs.homogeneousList(Registries.STRUCTURE, Structure.DIRECT_CODEC)
                    .fieldOf("structures")
                    .forGetter(AddLimitedSpawnsStructureModifier::structures),
            Codec.either(SpawnerData.CODEC.listOf(), SpawnerData.CODEC)
                    .xmap(
                            either -> either.map(Function.identity(), List::of),
                            list -> list.size() == 1 ? Either.right(list.getFirst()) : Either.left(list))
                    .fieldOf("spawners")
                    .forGetter(AddLimitedSpawnsStructureModifier::spawners),
            Codec.intRange(1, Integer.MAX_VALUE)
                    .fieldOf("limit")
                    .forGetter(AddLimitedSpawnsStructureModifier::limit),
            Codec.floatRange(0.0F, 1.0F)
                    .optionalFieldOf("center_factor", 0.5F)
                    .forGetter(AddLimitedSpawnsStructureModifier::centerFactor)
    ).apply(instance, AddLimitedSpawnsStructureModifier::new));

    public Set<EntityType<?>> spawnerTypes() {
        Set<EntityType<?>> types = new HashSet<>();
        for (SpawnerData spawner : this.spawners) {
            types.add(spawner.type);
        }
        return types;
    }

    @Override
    public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (phase != Phase.ADD || !this.structures.contains(structure)) {
            return;
        }

        StructureSettingsBuilder settingsBuilder = builder.getStructureSettings();
        Set<EntityType<?>> entityTypes = spawnerTypes();

        for (SpawnerData spawner : this.spawners) {
            EntityType<?> type = spawner.type;
            var overrides = settingsBuilder.getOrAddSpawnOverrides(type.getCategory());
            overrides.setBoundingBox(StructureSpawnOverride.BoundingBoxType.STRUCTURE);
            overrides.addSpawn(spawner);
        }

        structure.unwrapKey().ifPresent(key ->
                LimitedSpawnRules.register(key, this.limit, this.centerFactor, entityTypes));
    }

    @Override
    public MapCodec<? extends StructureModifier> codec() {
        return StructureModifierSerializers.ADD_LIMITED_SPAWNS.get();
    }
}
