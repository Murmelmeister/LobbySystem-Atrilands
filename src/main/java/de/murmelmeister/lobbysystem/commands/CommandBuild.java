package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandBuild extends Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.Build.Use"))))) {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
            return true;
        }

        if (args.length == 0) {

            Player player = sender instanceof Player ? (Player) sender : null;

            if (player == null) {
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoConsole")));
                return true;
            }

            if (this.arrayListUtil.getBuildmode().contains(player.getUniqueId())) {
                this.arrayListUtil.getBuildmode().remove(player.getUniqueId());
                player.setGameMode(GameMode.ADVENTURE);
                setSendMessage(player, HexColor.format(this.messageConfig.getConfig().getString("Message.Build.OffBuildmode")));
            } else {
                this.arrayListUtil.getBuildmode().add(player.getUniqueId());
                player.setGameMode(GameMode.CREATIVE);
                setSendMessage(player, HexColor.format(this.messageConfig.getConfig().getString("Message.Build.OnBuildmode")));
            }

        } else if (args.length == 1) {

            if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.Build.Other"))))) {
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
                return true;
            }

            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.PlayerIsNotOnline")).replace("[PLAYER]", args[0])));
                return true;
            }

            if (this.arrayListUtil.getBuildmode().contains(target.getUniqueId())) {
                this.arrayListUtil.getBuildmode().remove(target.getUniqueId());
                target.setGameMode(GameMode.ADVENTURE);
                setSendMessage(target, HexColor.format(this.messageConfig.getConfig().getString("Message.Build.OffBuildmode")));
                setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.Build.OffBuildmodeOther")).replace("[PLAYER]", target.getName())));
            } else {
                this.arrayListUtil.getBuildmode().add(target.getUniqueId());
                target.setGameMode(GameMode.CREATIVE);
                setSendMessage(target, HexColor.format(this.messageConfig.getConfig().getString("Message.Build.OnBuildmode")));
                setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.Build.OnBuildmodeOther")).replace("[PLAYER]", target.getName())));
            }

        } else {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.Build.Syntax")));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return getTabComplete(sender, args);
    }
}
