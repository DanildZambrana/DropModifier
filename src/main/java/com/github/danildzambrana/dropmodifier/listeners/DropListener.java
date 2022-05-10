package com.github.danildzambrana.dropmodifier.listeners;

import com.github.danildzambrana.dropmodifier.models.managers.WorldManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DropListener implements Listener {
    private WorldManager worldManager;

    @EventHandler
    public void onMobDrop(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player) {
            return;
        }

        worldManager.getWorld(entity.getLocation().getWorld().getName())
                .flatMap(pluginWorld -> pluginWorld.getEntityByType(entity.getType()))
                .ifPresent(pluginEntity -> event.getDrops()
                        .removeIf(itemStack -> pluginEntity.getRemovedItems().contains(itemStack.getType())));
    }

}
