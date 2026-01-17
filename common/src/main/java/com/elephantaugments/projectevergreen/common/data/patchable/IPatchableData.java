package com.elephantaugments.projectevergreen.common.data.patchable;

import com.elephantaugments.projectevergreen.common.data.IWorldgenData;
import com.elephantaugments.projectevergreen.common.data.PEBiome;
import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class IPatchableData {

    protected SortedSet<String> Paths;
    protected SortedSet<String> IDs;

    protected IPatchableData(SortedSet<String> set) {
        Paths = set;
    }

    protected IPatchableData(SortedSet<String> set, String regPath) {
        Paths = set;
        IDs = setPaths(regPath);
    }

    protected abstract Map<String, ?> mapPatchableData();

    protected SortedSet<String> setPaths(String regPath) {
        return Paths.stream()
            .map(p -> getFullPath(regPath, p))
            .collect(Collectors.toCollection(TreeSet::new));
    };

    public SortedSet<String> getPaths() {
        return Paths;
    }

    public SortedSet<String> getIDs() {
        return IDs;
    }

    /*protected SortedSet<String> isValidPath(SortedSet<String> path_set, boolean also_check_ids) {
        return path_set.stream()
            .filter(e -> !(DefaultBlacklist.modIDs.contains(Constants.getNamespace(e)) ||
                    DefaultBlacklist.structureIDs.contains(Constants.getLocation(e))))
            .map(Constants::getLocation)
            .collect(Collectors.toCollection(TreeSet::new));
    };

    protected ArrayList<WorldgenData> mapPatchableData(String regPath) {
        ArrayList<WorldgenData> data = new ArrayList<WorldgenData>();
        Paths.forEach(d -> {
            WorldgenData biome = new WorldgenData(d, getFullPath(regPath, d));
            data.add(biome);
        });
        return data;
    }*/

    protected static String getFullPath(String regPath, String id) {
        return id.replace(":", ":" + regPath);
    }
}
