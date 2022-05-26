package de.murmelmeister.lobbysystem.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class ListenerConnect extends Listeners {

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
        Player player = event.getPlayer();

        this.lobbyItems.setLobbyItems(player);

        if (player.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.LobbyItem.Boots")))) {
            this.arrayListUtil.getHue().put(player.getUniqueId(), 0.0f);
        }

        // When the spawn does not exist
        try {
            player.teleport(this.locationUtil.getLocation("Spawn"));
        } catch (NullPointerException | IllegalArgumentException e) {
            setSendMessage(player, "#777777--- #ff0000Setup #777777---");
            setSendMessage(player, "#999999Setze bitte den Spawn. #777777(#ff0000/setspawn#777777)");
        }

        // When the death height does not exist
        if (!(this.locationUtil.heightIsExisting())) {
            setSendMessage(player, "#777777--- #ff0000Setup #777777---");
            setSendMessage(player, "#999999Setze bitte die todes Höhe. #777777(#ff0000/setdeathheight#777777)");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // When the player disconnect
        this.arrayListUtil.getBuildmode().remove(player.getUniqueId());

        event.quitMessage(null);
    }
}
