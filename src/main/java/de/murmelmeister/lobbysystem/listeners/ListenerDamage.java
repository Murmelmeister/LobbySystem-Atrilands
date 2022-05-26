package de.murmelmeister.lobbysystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ListenerDamage extends Listeners {

    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            event.setCancelled(event.getCause().equals(EntityDamageEvent.DamageCause.FALL));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

}
