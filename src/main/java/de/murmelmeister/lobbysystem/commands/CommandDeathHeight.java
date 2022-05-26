package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandDeathHeight extends Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.DeathHeight"))))) {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
            return true;
        }

        setSendMessage(sender, HexColor.format(Objects.requireNonNull(this.messageConfig.getConfig().getString("Message.DeathHeight")).replace("[HEIGHT]", this.locationUtil.getDeathHeight() + "")));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
