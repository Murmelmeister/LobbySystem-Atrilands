package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Economy;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.api.Locations;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Commands implements TabExecutor {
    private final LobbySystem plugin;

    private final MessageConfig messageConfig;
    private final Locations locations;
    private final Economy economy;

    public Commands(LobbySystem plugin) {
        this.plugin = plugin;
        this.locations = plugin.getLocations();
        this.messageConfig = plugin.getMessageConfig();
        this.economy = plugin.getEconomy();
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(messageConfig.getPrefix() + message));
    }

    @NotNull
    public List<String> getTabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {
            String lastWord = args[args.length - 1];
            Player senderPlayer = sender instanceof Player ? (Player) sender : null;

            for (Player all : sender.getServer().getOnlinePlayers()) {
                String name = all.getName();
                if ((senderPlayer == null || senderPlayer.canSee(all)) && StringUtil.startsWithIgnoreCase(name, lastWord))
                    tabComplete.add(name);
            }
            tabComplete.sort(String.CASE_INSENSITIVE_ORDER);
        }

        return tabComplete;
    }

    public LobbySystem getPlugin() {
        return plugin;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public Locations getLocations() {
        return locations;
    }

    public Economy getEconomy() {
        return economy;
    }

    public static void registers(LobbySystem plugin) {
        addCommand(plugin, "build", new BuildCommand(plugin));
        addCommand(plugin, "lobbysystemreload", new LobbySystemReloadCommand(plugin));
        addCommand(plugin, "deathheight", new DeathHeightCommand(plugin));
        addCommand(plugin, "setdeathheight", new SetDeathHeightCommand(plugin));
        addCommand(plugin, "setspawn", new SetSpawnCommand(plugin));
        addCommand(plugin, "spawn", new SpawnCommand(plugin));
    }

    private static void addCommand(LobbySystem plugin, String commandName, TabExecutor executor) {
        PluginCommand command = plugin.getCommand(commandName);
        if (command == null) {
            plugin.getLogger().warning("Command '" + commandName + "' not found in plugin.yml.");
            return;
        }
        command.setExecutor(executor);
    }
}
