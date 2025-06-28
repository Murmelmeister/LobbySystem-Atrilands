package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.EconomyAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class EconomyListener extends Listeners {
    public EconomyListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        EconomyAPI economyAPI = getEconomyAPI();
        UUID playerId = event.getPlayer().getUniqueId();
        if (!(economyAPI.hasAccount(playerId)))
            economyAPI.createAccount(playerId);
    }

}
