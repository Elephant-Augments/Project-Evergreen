package com.elephantaugments.projectevergreen.common.data;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StructureData implements IStructureData {
    private final List<String> defaultBiomes;
    private final GenerationStep.Decoration defaultStep;
    private final TerrainAdjustment defaultTerrainAdaptation;

    private boolean isDisabled = IS_DISABLED_DEFAULT_VALUE;
    private List<String> biomes;
    private GenerationStep.Decoration step;
    private TerrainAdjustment terrainAdaptation;

    public StructureData(
            List<String> biomes,
            GenerationStep.Decoration step,
            TerrainAdjustment terrainAdaptation
    ) {
        this.defaultBiomes = biomes;
        this.biomes = biomes;
        this.defaultStep = step;
        this.step = step;
        this.defaultTerrainAdaptation = terrainAdaptation;
        this.terrainAdaptation = terrainAdaptation;
    }

    public boolean isUsingDefaultValues() {
        var biomes = new ArrayList<>(this.biomes);
        var defaultBiomes = new ArrayList<>(this.defaultBiomes);

        Collections.sort(biomes);
        Collections.sort(defaultBiomes);

        return this.isDisabled == IS_DISABLED_DEFAULT_VALUE
                && this.step == this.defaultStep
                && this.terrainAdaptation == this.defaultTerrainAdaptation
                && biomes.equals(defaultBiomes);
    }

    public boolean isDisabled() {
        return this.isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public List<String> getDefaultBiomes() {
        return this.defaultBiomes;
    }

    public List<String> getBiomes() {
        return this.biomes;
    }

    public void setBiomes(List<String> biomes) {
        this.biomes = biomes;
    }

    public GenerationStep.Decoration getDefaultStep() {
        return this.defaultStep;
    }

    public GenerationStep.Decoration getStep() {
        return this.step;
    }

    public void setStep(GenerationStep.Decoration step) {
        this.step = step;
    }

    public TerrainAdjustment getDefaultTerrainAdaptation() {
        return defaultTerrainAdaptation;
    }

    public TerrainAdjustment getTerrainAdaptation() {
        return terrainAdaptation;
    }

    public void setTerrainAdaptation(TerrainAdjustment terrainAdaptation) {
        this.terrainAdaptation = terrainAdaptation;
    }
}
