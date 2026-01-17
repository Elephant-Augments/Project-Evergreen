package com.elephantaugments.projectevergreen.common.data;

public abstract class IWorldgenData {

    public String id;
    public String full_path;
    public Integer difficulty;
    public boolean is_ignored;

    public IWorldgenData(String ID) {
        id = ID;
    }

    public IWorldgenData(String ID, String fullPath) {
        id = ID;
        full_path = fullPath;
    }

    public IWorldgenData(String ID, Integer diffLevel) {
        id = ID;
        difficulty = diffLevel;
    }

    public IWorldgenData(String ID, String fullPath, Integer diffLevel) {
        id = ID;
        full_path = fullPath;
        difficulty = diffLevel;
    }

    public String getId() {
        return this.id;
    }

    public abstract void updateData();

    public String getFullPath(String id, String regPath) {
        return id.replace(":", ":" + regPath);
    }
}
