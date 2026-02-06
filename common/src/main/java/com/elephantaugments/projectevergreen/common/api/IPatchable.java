package com.elephantaugments.projectevergreen.common.api;

import com.google.gson.JsonObject;

public abstract class IPatchable {

    public String id;
    public String full_path;
    public Integer difficulty;
    public boolean is_ignored;

    public IPatchable(String ID) {
        id = ID;
    }

    public IPatchable(String ID, String fullPath) {
        id = ID;
        full_path = fullPath;
    }

    public IPatchable(String ID, Integer diffLevel) {
        id = ID;
        difficulty = diffLevel;
    }

    public IPatchable(String ID, String fullPath, Integer diffLevel) {
        id = ID;
        full_path = fullPath;
        difficulty = diffLevel;
    }

    public String getId() {
        return this.id;
    }

    public abstract void updateData();

    public abstract JsonObject toJson();

    public String getFullPath(String id, String regPath) {
        return id.replace(":", ":" + regPath);
    }
}
