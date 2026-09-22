package com.elephantaugments.projectevergreen.neoforge.api;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.mojang.serialization.MapCodec;

import net.neoforged.neoforge.common.world.StructureModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class StructureModifierSerializers {
    public static final DeferredRegister<MapCodec<? extends StructureModifier>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, ProjectEvergreen.MODID);

    public static final DeferredHolder<MapCodec<? extends StructureModifier>, MapCodec<AddLimitedSpawnsStructureModifier>> ADD_LIMITED_SPAWNS =
            REGISTER.register("add_limited_spawns", () -> AddLimitedSpawnsStructureModifier.CODEC);

    private StructureModifierSerializers() {}
}
