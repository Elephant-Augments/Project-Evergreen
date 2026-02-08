package com.elephantaugments.projectevergreen.common.api;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class IPatchable {

    private final List<?> flags = new ArrayList<>();

    public String id;
    public String full_path;
    protected Integer difficulty;
    protected boolean is_loaded = false;

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

    public String getFullPath(String id, String regPath) {
        return id.replace(":", ":" + regPath);
    }

    public Optional<Integer> getDifficulty() {
        return Optional.ofNullable(this.difficulty);
    }

    public boolean isLoaded() {
        return this.is_loaded;
    }

    public void setLoaded(boolean loaded) {
        this.is_loaded = loaded;
    }

    public abstract void updateData();

    public abstract JsonElement toJson();
}
