package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandSetSpawn extends Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.SetSpawn"))))) {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
            return true;
        }

        Player player = sender instanceof Player ? (Player) sender : null;

        if (player == null) {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoConsole")));
            return true;
        }

        this.locationUtil.setLocation("Spawn", player.getLocation());
        setSendMessage(player, HexColor.format(this.messageConfig.getConfig().getString("Message.SetSpawn")));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
