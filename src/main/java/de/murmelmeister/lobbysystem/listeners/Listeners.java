package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Economy;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.api.Locations;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Listeners implements Listener {
    private final LobbySystem plugin;

    private final MessageConfig messageConfig;
    private final Locations locations;
    private final Economy economy;
    private final LobbyItems lobbyItems;

    public Listeners(LobbySystem plugin) {
        this.plugin = plugin;
        this.messageConfig = plugin.getMessageConfig();
        this.locations = plugin.getLocations();
        this.economy = plugin.getEconomy();
        this.lobbyItems = plugin.getLobbyItems();
    }

    public static void registers(LobbySystem plugin) {
        addListener(plugin, new OtherListener(plugin));
        addListener(plugin, new DamageListener(plugin));
        addListener(plugin, new ConnectListener(plugin));
        addListener(plugin, new LobbyListener(plugin));
        addListener(plugin, new EconomyListener(plugin));
    }

    private static void addListener(LobbySystem plugin, Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(messageConfig.getPrefix() + message));
    }

    public LobbySystem getPlugin() {
        return plugin;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public Locations getLocationUtil() {
        return locations;
    }

    public Economy getEconomy() {
        return economy;
    }

    public LobbyItems getLobbyItems() {
        return lobbyItems;
    }
}
