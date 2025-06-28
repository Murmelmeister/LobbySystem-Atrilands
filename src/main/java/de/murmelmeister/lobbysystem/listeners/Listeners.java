package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.EconomyAPI;
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
    private final EconomyAPI economyAPI;
    private final LobbyItems lobbyItems;

    public Listeners(LobbySystem plugin) {
        this.plugin = plugin;
        this.messageConfig = plugin.getMessageConfig();
        this.locations = plugin.getLocations();
        this.economyAPI = plugin.getEconomyAPI();
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

    public EconomyAPI getEconomyAPI() {
        return economyAPI;
    }

    public LobbyItems getLobbyItems() {
        return lobbyItems;
    }
}
