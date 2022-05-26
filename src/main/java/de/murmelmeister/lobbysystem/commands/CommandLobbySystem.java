package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandLobbySystem extends Commands {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {

            if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.LobbySystem.Use"))))) {
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
                return true;
            }

            setSendMessage(sender, " ");
            setSendMessage(sender, "#999999Das Plugin wurde von #00ff00" + this.instance.getAuthor() + " #999999programmiert.");
            setSendMessage(sender, "#999999Version: #0000ff" + this.instance.getVersion());
            setSendMessage(sender, " ");

        } else if (args.length == 1) {

            if (args[0].equalsIgnoreCase("reload")) {

                if (!(sender.hasPermission(Objects.requireNonNull(this.messageConfig.getConfig().getString("Permission.LobbySystem.Reload"))))) {
                    setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.NoPermission")));
                    return true;
                }

                this.messageConfig.createFile();
                setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.LobbySystem.ReloadMessage")));
            }

        } else {
            setSendMessage(sender, HexColor.format(this.messageConfig.getConfig().getString("Message.LobbySystem.Syntax")));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        ArrayList<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {

            String[] commands = new String[]{"reload"};

            String lastWord = args[args.length - 1];

            for (String cmd : commands) {

                if (StringUtil.startsWithIgnoreCase(cmd, lastWord)) {
                    tabComplete.add(cmd);
                }

                tabComplete.sort(String.CASE_INSENSITIVE_ORDER);
            }
        }

        return tabComplete;
    }
}
