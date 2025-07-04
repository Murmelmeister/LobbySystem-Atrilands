package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.api.Locations;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ConnectListener extends Listeners {
    public ConnectListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        MessageConfig messageConfig = getMessageConfig();
        Locations locations = getLocationUtil();
        LobbyItems lobbyItems = getLobbyItems();
        Map<UUID, Float> rainbowHue = getPlugin().getRainbowHue();

        event.joinMessage(null);
        Player player = event.getPlayer();

        lobbyItems.setLobbyItems(player);

        // new TestScoreboard(player);

        if (player.hasPermission(messageConfig.getMessage(Messages.PERMISSION_LOBBY_ITEMS_BOOTS)))
            rainbowHue.put(player.getUniqueId(), 0.0f);

        // When the spawn does not exist
        if (locations.existsLocation("Spawn"))
            player.teleport(locations.getLocation("Spawn"));
        else sendMessage(player, messageConfig.getMessage(Messages.MESSAGE_SPAWN_NOT_SET));

        // When the death height does not exist
        if (!locations.existsLocation("DeathHeight"))
            sendMessage(player, messageConfig.getMessage(Messages.MESSAGE_DEATH_HEIGHT_NOT_SET));
    }

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent event) {
        List<UUID> buildMode = getPlugin().getBuildMode();
        Player player = event.getPlayer();

        // When the player disconnect
        buildMode.remove(player.getUniqueId());

        event.quitMessage(null);
    }
}
