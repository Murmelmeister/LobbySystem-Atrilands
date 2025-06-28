package de.murmelmeister.lobbysystem;

import de.murmelmeister.lobbysystem.api.EconomyAPI;
import de.murmelmeister.lobbysystem.commands.CommandManager;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.listeners.Listeners;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.utils.LocationUtil;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.*;

public final class LobbySystem extends JavaPlugin {
    private MessageConfig messageConfig;
    private LocationUtil locationUtil;
    private EconomyAPI economyAPI;
    private LobbyItems lobbyItems;
    private Listeners listeners;
    private CommandManager commandManager;

    private final List<UUID> buildMode = new ArrayList<>();
    private final Map<UUID, Float> rainbowHue = new HashMap<>();

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }

    @Override
    public void onEnable() {
        final Logger logger = getSLF4JLogger();
        this.messageConfig = new MessageConfig(logger);
        this.locationUtil = new LocationUtil();
        this.economyAPI = new EconomyAPI();
        this.lobbyItems = new LobbyItems();
        this.listeners = new Listeners(this);
        this.commandManager = new CommandManager();

        Listeners.registers(this);
        commandManager.registerCommands();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
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

    public LocationUtil getLocationUtil() {
        return locationUtil;
    }

    public void setLocationUtil(LocationUtil locationUtil) {
        this.locationUtil = locationUtil;
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

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public List<UUID> getBuildMode() {
        return buildMode;
    }

    public Map<UUID, Float> getRainbowHue() {
        return rainbowHue;
    }
}
