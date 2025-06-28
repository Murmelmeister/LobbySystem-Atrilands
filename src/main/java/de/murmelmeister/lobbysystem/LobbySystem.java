package de.murmelmeister.lobbysystem;

import de.murmelmeister.lobbysystem.api.EconomyAPI;
import de.murmelmeister.lobbysystem.commands.Commands;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.listeners.Listeners;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.api.Locations;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.*;

public final class LobbySystem extends JavaPlugin {
    private MessageConfig messageConfig;
    private Locations locations;
    private EconomyAPI economyAPI;
    private LobbyItems lobbyItems;
    private Listeners listeners;

    private final List<UUID> buildMode = new ArrayList<>();
    private final Map<UUID, Float> rainbowHue = new HashMap<>();

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }

    @Override
    public void onEnable() {
        final Logger logger = getSLF4JLogger();
        final Server server = getServer();
        this.messageConfig = new MessageConfig(logger);
        this.locations = new Locations(logger, server);
        this.economyAPI = new EconomyAPI();
        this.lobbyItems = new LobbyItems();
        this.listeners = new Listeners(this);

        Listeners.registers(this);
        Commands.registers(this);

        server.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        lobbyItems.rainbowLoop().runTaskTimer(this, 1, 1);
    }

    public static LobbySystem getInstance() {
        return getPlugin(LobbySystem.class);
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public void setMessageConfig(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public EconomyAPI getEconomyAPI() {
        return economyAPI;
    }

    public void setEconomyAPI(EconomyAPI economyAPI) {
        this.economyAPI = economyAPI;
    }

    public LobbyItems getLobbyItems() {
        return lobbyItems;
    }

    public void setLobbyItems(LobbyItems lobbyItems) {
        this.lobbyItems = lobbyItems;
    }

    public Listeners getListeners() {
        return listeners;
    }

    public void setListeners(Listeners listeners) {
        this.listeners = listeners;
    }

    public List<UUID> getBuildMode() {
        return buildMode;
    }

    public Map<UUID, Float> getRainbowHue() {
        return rainbowHue;
    }
}
