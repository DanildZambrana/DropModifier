package com.github.danildzambrana.dropmodifier.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Contains the world registered in config.yml
 */
public class PluginWorld {
    private String worldName;
    private Set<PluginEntity> entities = new HashSet<>();

    public PluginWorld(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldName() {
        return worldName;
    }

    public PluginWorld setWorldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    public Set<PluginEntity> getEntities() {
        return entities;
    }

    public PluginWorld setEntities(Set<PluginEntity> entities) {
        this.entities = entities;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PluginWorld that)) {
            return false;
        }
        return getWorldName().equals(that.getWorldName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorldName());
    }

    @Override
    public String toString() {
        return "PluginWorld{" +
                "worldName='" + worldName + '\'' +
                ", entities=" + entities +
                '}';
    }
}
