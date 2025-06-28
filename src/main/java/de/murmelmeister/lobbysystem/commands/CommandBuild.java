package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public final class CommandBuild extends Commands {
    public CommandBuild(LobbySystem plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        MessageConfig messageConfig = getMessageConfig();
        List<UUID> buildMode = getPlugin().getBuildMode();

        if (!sender.hasPermission(messageConfig.getMessage(Messages.PERMISSION_BUILD_USE))) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_PERMISSION));
            return true;
        }

        if (args.length > 1) {
            sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_BUILD_SYNTAX));
            return true;
        }

        if (args.length == 0) {
            Player player = sender instanceof Player ? (Player) sender : null;

            if (player == null) {
                sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_CONSOLE));
                return true;
            }

            if (buildMode.contains(player.getUniqueId())) {
                buildMode.remove(player.getUniqueId());
                player.setGameMode(GameMode.ADVENTURE);
                sendMessage(player, messageConfig.getMessage(Messages.MESSAGE_BUILD_USE_DISABLED));
            } else {
                buildMode.add(player.getUniqueId());
                player.setGameMode(GameMode.CREATIVE);
                sendMessage(player, messageConfig.getMessage(Messages.MESSAGE_BUILD_USE_ENABLED));
            }
        } else {
            if (!(sender.hasPermission(messageConfig.getMessage(Messages.PERMISSION_BUILD_OTHER)))) {
                sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_NO_PERMISSION));
                return true;
            }

            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_PLAYER_IS_NOT_ONLINE).replace("[PLAYER]", args[0]));
                return true;
            }

            if (buildMode.contains(target.getUniqueId())) {
                buildMode.remove(target.getUniqueId());
                target.setGameMode(GameMode.ADVENTURE);
                sendMessage(target, messageConfig.getMessage(Messages.MESSAGE_BUILD_USE_DISABLED));
                sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_BUILD_OTHER_DISABLED).replace("[PLAYER]", target.getName()));
            } else {
                buildMode.add(target.getUniqueId());
                target.setGameMode(GameMode.CREATIVE);
                sendMessage(target, messageConfig.getMessage(Messages.MESSAGE_BUILD_USE_ENABLED));
                sendMessage(sender, messageConfig.getMessage(Messages.MESSAGE_BUILD_OTHER_ENABLED).replace("[PLAYER]", target.getName()));
            }
        }
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        return getTabComplete(sender, args);
    }
}
