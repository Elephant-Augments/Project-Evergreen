package com.elephantaugments.projectevergreen.common.data.defaults;

import com.elephantaugments.projectevergreen.common.ProjectEvergreen;
import com.elephantaugments.projectevergreen.common.data.PEStructure;
import com.elephantaugments.projectevergreen.common.data.WorldgenDataManager;
import com.elephantaugments.projectevergreen.common.data.patchable.PatchableStructures;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DefaultStructureDimensions {

	public enum Dimension {
		OVERWORLD, AETHER, NETHER, AFTERDARK, END, LOSTCITIES;
	}

    public static final List<String> OUTER_DIMENSION_TAGS = ImmutableList.of(
        DefaultRegions.NETHER_BIOMES,
        DefaultRegions.END_BIOMES,
        DefaultRegions.AETHER_BIOMES,
        DefaultRegions.AFTERDARK_BIOMES
    );

    public static ArrayListMultimap<Dimension, PEStructure> mapStructuresToDimensions(PatchableStructures structureData) {
        ArrayListMultimap<Dimension, PEStructure> structuresByDimension = ArrayListMultimap.create();

        List<String> overworldStructures = new ArrayList<>();
        for (Map.Entry<String, PEStructure> e : DefaultStructureRegions.mapStructuresToRegion(structureData).entries()) {
            if (e.getValue() != null && !OUTER_DIMENSION_TAGS.contains(e.getKey())) {
                PEStructure value = e.getValue();
                String id = value.getId();
                overworldStructures.add(id);
            }
        }

        overworldStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.OVERWORLD, structureData.Data.get(s)));
        aetherStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.AETHER, structureData.Data.get(s)));
        netherStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.NETHER, structureData.Data.get(s)));
        afterdarkStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.AFTERDARK, structureData.Data.get(s)));
        endStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.END, structureData.Data.get(s)));
        lostCitiesStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.LOSTCITIES, structureData.Data.get(s)));

        structuresByDimension.values().removeIf(Objects::isNull);
        return structuresByDimension;
    }

    /*public static ArrayListMultimap<Dimension, PEStructure> mapStructuresToOuterDimensions(PatchableStructures structureData) {
		ArrayListMultimap<Dimension, PEStructure> structuresByDimension = ArrayListMultimap.create();

        aetherStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.AETHER, structureData.Data.get(s)));
        netherStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.NETHER, structureData.Data.get(s)));
        afterdarkStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.AFTERDARK, structureData.Data.get(s)));
        endStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.END, structureData.Data.get(s)));
        lostCitiesStructures.forEach(s -> addStructToMap(structuresByDimension, Dimension.LOSTCITIES, structureData.Data.get(s)));
        return structuresByDimension;
    }*/

    private static void addStructToMap(ArrayListMultimap<Dimension, PEStructure> structuresByDimension, Dimension dim, PEStructure struct) {
        struct.setDimension(dim);
        structuresByDimension.put(dim, struct);
        struct.updateData();
    }

    //AETHER
    public static final List<String> aetherStructures = ImmutableList.of(

    );

    //NETHER
    public static final List<String> netherStructures = ImmutableList.of(

    );

    //AFTERDARK
    public static final List<String> afterdarkStructures = ImmutableList.of(

    );

    //END
    public static final List<String> endStructures = ImmutableList.of(

    );

    //LOST_CITIES
    public static final List<String> lostCitiesStructures = ImmutableList.of(

    );
}
