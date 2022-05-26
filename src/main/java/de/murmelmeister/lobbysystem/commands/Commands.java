package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.ArrayListUtil;
import de.murmelmeister.lobbysystem.utils.HexColor;
import de.murmelmeister.lobbysystem.utils.LocationUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Commands implements TabExecutor {

    protected LobbySystem instance = LobbySystem.getInstance();

    protected ArrayListUtil arrayListUtil = this.instance.getArrayListUtil();
    protected LocationUtil locationUtil = this.instance.getLocationUtil();
    protected MessageConfig messageConfig = this.instance.getMessageConfig();

    protected void setSendMessage(CommandSender sender, String message) {
        sender.sendMessage(this.instance.getMessageConfig().getConfig().getString("Prefix") + HexColor.format(message));
    }

    @NotNull
    protected List<String> getTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {

            String lastWord = args[args.length - 1];

            Player senderPlayer = sender instanceof Player ? (Player) sender : null;

            for (Player all : sender.getServer().getOnlinePlayers()) {

                String name = all.getName();

                if ((senderPlayer == null || senderPlayer.canSee(all)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
                    tabComplete.add(name);
                }
            }
            tabComplete.sort(String.CASE_INSENSITIVE_ORDER);
        }

        return tabComplete;
    }

}
