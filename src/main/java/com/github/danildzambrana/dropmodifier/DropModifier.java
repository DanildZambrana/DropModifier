package com.github.danildzambrana.dropmodifier;

import com.github.danildzambrana.dropmodifier.listeners.DropListener;
import com.github.danildzambrana.dropmodifier.models.managers.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DropModifier extends JavaPlugin {

    @Override
    public void onEnable() {
        FileManager config = new FileManager(this, "config.yml", "config.yml").load(false);
        WorldManager worldManager = new WorldManager(getLogger(), config.getBukkitFile());

        getLogger().info("Loading entities!");
        worldManager.loadWorlds();
        getLogger().info("Configuration info loaded!");

        Bukkit.getPluginManager().registerEvents(new DropListener(worldManager), this);
    }
}
