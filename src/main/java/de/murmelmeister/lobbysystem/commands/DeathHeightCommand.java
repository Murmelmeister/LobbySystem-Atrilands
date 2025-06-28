package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Locations;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class DeathHeightCommand extends Commands {
    public DeathHeightCommand(LobbySystem plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        MessageConfig messageConfig = getMessageConfig();
        Locations locations = getLocations();
        if (!(sender.hasPermission(messageConfig.getMessage(Messages.PERMISSION_DEATH_HEIGHT_GET)))) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_PERMISSION));
            return true;
        }

        sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_DEATH_HEIGHT_GET)
                .replace("[HEIGHT]", locations.getY("DeathHeight") + ""));
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        return Collections.emptyList();
    }
}
