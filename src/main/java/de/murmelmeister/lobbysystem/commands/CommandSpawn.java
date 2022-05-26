package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandSpawn extends Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.Spawn.Use"))))) {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
            return true;
        }

        if (args.length == 0) {

            Player player = sender instanceof Player ? (Player) sender : null;

            if (player == null) {
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoConsole")));
                return true;
            }

            player.teleport(this.locationUtil.getLocation("Spawn"));
            setSendMessage(player, HexColor.format(this.messageConfig.getConfig().getString("Message.Spawn.Teleport")));

        } else if (args.length == 1) {

            if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.Spawn.Other"))))) {
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
                return true;
            }

            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.PlayerIsNotOnline")).replace("[PLAYER]", args[0])));
                return true;
            }

            target.teleport(this.locationUtil.getLocation("Spawn"));
            setSendMessage(target, HexColor.format(this.messageConfig.getConfig().getString("Message.Spawn.Teleport")));
            setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.Spawn.TeleportOther")).replace("[PLAYER]", args[0])));

        } else {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.Spawn.Syntax")));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return getTabComplete(sender, args);
    }

}
