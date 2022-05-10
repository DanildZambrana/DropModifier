package com.github.danildzambrana.dropmodifier.models.managers;

import com.github.danildzambrana.dropmodifier.models.PluginEntity;
import com.github.danildzambrana.dropmodifier.models.PluginWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public class WorldManager {
    private final Logger logger;
    private final FileConfiguration configuration;
    private final Set<PluginWorld> worlds = new HashSet<>();

    public WorldManager(Logger logger, FileConfiguration configuration) {
        this.logger = logger;
        this.configuration = configuration;
    }

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


    /**
     * Find all worlds in config and load them.
     */
    private void loadWorlds() {
        ConfigurationSection section = configuration.getConfigurationSection("");
        if (section == null) {
            return;
        }

        for (String worldName : section.getKeys(false)) {
            World world = Bukkit.getWorld(worldName);

            if (world == null) {
                logger.warning("Couldn't find world with name '"+ worldName + "', Skip...");
                continue;
            }


            PluginWorld pluginWorld = new PluginWorld(worldName, getEntities(worldName));

            this.addWorld(pluginWorld);
        }
    }

    /**
     * Find all entities added in the provided path.
     * @param path the path to find the entities.
     * @return found entities.
     */
    private Set<PluginEntity> getEntities(String path) {
        Set<PluginEntity> entities = new HashSet<>();

        ConfigurationSection section = configuration.getConfigurationSection(path);
        if (section == null) {
            return entities;
        }

        for (String entityName : section.getKeys(false)) {
            try {
                EntityType entityType = EntityType.valueOf(entityName);
                PluginEntity entity = new PluginEntity(entityType, getDeniedItems(path + "." + entityName));

                entities.add(entity);
            } catch (IllegalArgumentException e) {
                logger.warning("The entity with name: '" + entityName + "' not exist, skip...");
            }
        }

        return entities;
    }

    /**
     * Find all materials denied placed in provided path.
     * @param path the path to find the materials.
     * @return found items.
     */
    private Set<Material> getDeniedItems(String path) {
        Set<Material> materials = new HashSet<>();

        for (String itemDeniedName : configuration.getStringList(path)) {
            try {
                Material material = Material.valueOf(itemDeniedName);
                materials.add(material);
            } catch (IllegalArgumentException e) {
                logger.warning("The item with name '" + itemDeniedName + "' not exist, skip...");
            }
        }

        return materials;
    }
}
