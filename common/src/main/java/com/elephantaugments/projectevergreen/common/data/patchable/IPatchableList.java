package com.elephantaugments.projectevergreen.common.data.patchable;

import java.util.*;
import java.util.stream.Collectors;

public abstract class IPatchableList {

    public Map<String, ?> Data;

    protected SortedSet<String> Paths;
    protected SortedSet<String> IDs;

    protected IPatchableList(SortedSet<String> set) {
        Paths = set;
    }

    protected IPatchableList(SortedSet<String> set, String regPath) {
        IDs = set;
        Paths = setPaths(regPath);
    }

    protected abstract void mapPatchableData(SortedSet<String> ids);

    public abstract void addPatchableData(String id);

    protected SortedSet<String> setPaths(String regPath) {
        return IDs.stream()
            .map(p -> getFullPath(regPath, p))
            .collect(Collectors.toCollection(TreeSet::new));
    };

    public SortedSet<String> getPaths() {
        return Paths;
    }

    public SortedSet<String> getIDs() {
        return IDs;
    }

    protected static String getFullPath(String regPath, String id) {
        return id.replace(":", ":" + regPath);
    }
}
