package com.github.danildzambrana.dropmodifier.models.managers;

import com.github.danildzambrana.dropmodifier.models.PluginWorld;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WorldManager {
    private final Set<PluginWorld> worlds = new HashSet<>();

    /**
     * Add world to {@link WorldManager#worlds}
     * @param world the world to add.
     */
    public void addWorld(PluginWorld world) {
        this.worlds.add(world);
    }

    /**
     * Remove world from {@link WorldManager#worlds}
     * @param worldName the name of world to remove.
     */
    public void removeWorld(String worldName) {
        this.worlds.removeIf(pluginWorld -> pluginWorld.getWorldName().equalsIgnoreCase(worldName));
    }

    /**
     * Find world in {@link WorldManager#worlds}.
     * @param worldName the world to find.
     * @return {@link Optional} instance with result.
     */
    public Optional<PluginWorld> getWorld(String worldName) {
        return this.worlds.stream()
                .filter(pluginWorld -> pluginWorld.getWorldName().equalsIgnoreCase(worldName))
                .findFirst();
    }
}
