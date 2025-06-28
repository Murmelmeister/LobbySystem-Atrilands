package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Locations;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class SpawnCommand extends Commands {
    public SpawnCommand(LobbySystem plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        MessageConfig messageConfig = getMessageConfig();
        Locations locations = getLocations();
        if (!sender.hasPermission(messageConfig.getMessage(Messages.PERMISSION_SPAWN_GET))) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_PERMISSION));
            return true;
        }

        Player player = sender instanceof Player ? (Player) sender : null;

        if (player == null) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_CONSOLE));
            return true;
        }

        player.teleport(locations.getLocation("Spawn"));
        sendMessage(player, messageConfig.getMessage(Messages.MESSAGE_SPAWN_GET));
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        return Collections.emptyList();
    }

}
