package com.github.danildzambrana.dropmodifier.models;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Contains the configured entity in config.yml
 */
public class PluginEntity {
    private EntityType type;
    private Set<Material> removedItems = new HashSet<>();

    public PluginEntity(EntityType type) {
        this.type = type;
    }

    public PluginEntity(EntityType type, Set<Material> removedItems) {
        this.type = type;
        this.removedItems = removedItems;
    }

    public EntityType getType() {
        return type;
    }

    public PluginEntity setType(EntityType type) {
        this.type = type;
        return this;
    }

    public Set<Material> getRemovedItems() {
        return removedItems;
    }

    public PluginEntity setRemovedItems(Set<Material> removedItems) {
        this.removedItems = removedItems;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PluginEntity that)) {
            return false;
        }
        return getType() == that.getType() && Objects.equals(getRemovedItems(), that.getRemovedItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getRemovedItems());
    }

    @Override
    public String toString() {
        return "PluginEntity{" +
                "type=" + type +
                ", removedItems=" + removedItems +
                '}';
    }
}
