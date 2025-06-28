package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Economy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public final class EconomyListener extends Listeners {
    public EconomyListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Economy economy = getEconomy();
        UUID playerId = event.getPlayer().getUniqueId();
        if (!economy.hasAccount(playerId))
            economy.createAccount(playerId);
    }
}
