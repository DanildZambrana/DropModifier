package com.github.danildzambrana.dropmodifier.models;

import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
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

    public PluginWorld(String worldName, Set<PluginEntity> entities) {
        this.worldName = worldName;
        this.entities = entities;
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

    /**
     * Get entity by provided type.
     * @param type the type to find.
     * @return {@link Optional} instance with result.
     */
    public Optional<PluginEntity> getEntityByType(EntityType type) {
        return entities.stream().filter(pluginEntity -> pluginEntity.getType() == type).findFirst();
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
