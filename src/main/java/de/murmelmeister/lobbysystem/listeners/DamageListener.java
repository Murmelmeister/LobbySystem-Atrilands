package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public final class DamageListener extends Listeners {
    public DamageListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(true);
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

}
