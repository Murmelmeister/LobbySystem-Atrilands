package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Economy;
import de.murmelmeister.lobbysystem.api.Locations;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class LobbySystemReloadCommand extends Commands {
    public LobbySystemReloadCommand(LobbySystem plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        MessageConfig messageConfig = getMessageConfig();
        Locations locations = getLocations();
        Economy economy = getEconomy();
        if (!sender.hasPermission(messageConfig.getMessage(Messages.PERMISSION_LOBBY_SYSTEM_RELOAD))) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_PERMISSION));
            return true;
        }

        messageConfig.reload();
        locations.reload();
        economy.reload();
        sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_LOBBY_SYSTEM_RELOAD));

        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        return Collections.emptyList();
    }
}
