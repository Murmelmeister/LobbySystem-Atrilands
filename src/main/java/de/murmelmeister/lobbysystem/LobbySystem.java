package de.murmelmeister.lobbysystem;

import de.murmelmeister.lobbysystem.api.EconomyAPI;
import de.murmelmeister.lobbysystem.commands.CommandManager;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.listeners.Listeners;
import de.murmelmeister.lobbysystem.utils.ArrayListUtil;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.utils.LocationUtil;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public final class LobbySystem extends JavaPlugin {
    private MessageConfig messageConfig;
    private Listeners listeners;
    private ArrayListUtil arrayListUtil;
    private LocationUtil locationUtil;
    private CommandManager commandManager;
    private LobbyItems lobbyItems;
    private EconomyAPI economyAPI;

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }

    @Override
    public void onEnable() {
        final Logger logger = getSLF4JLogger();
        this.messageConfig = new MessageConfig(logger);
        this.arrayListUtil = new ArrayListUtil();
        this.locationUtil = new LocationUtil();
        this.economyAPI = new EconomyAPI();
        this.lobbyItems = new LobbyItems();
        this.listeners = new Listeners();
        this.commandManager = new CommandManager();

        listeners.registerListeners();
        commandManager.registerCommands();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        lobbyItems.rainbowLoop().runTaskTimer(this, 1, 1);
    }

    public static LobbySystem getInstance() {
        return getPlugin(LobbySystem.class);
    }

    public Listeners getListeners() {
        return listeners;
    }

    public void setListeners(Listeners listeners) {
        this.listeners = listeners;
    }

    public ArrayListUtil getArrayListUtil() {
        return arrayListUtil;
    }

    public void setArrayListUtil(ArrayListUtil arrayListUtil) {
        this.arrayListUtil = arrayListUtil;
    }

    public LocationUtil getLocationUtil() {
        return locationUtil;
    }

    public void setLocationUtil(LocationUtil locationUtil) {
        this.locationUtil = locationUtil;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public void setMessageConfig(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    public LobbyItems getLobbyItems() {
        return lobbyItems;
    }

    public void setLobbyItems(LobbyItems lobbyItems) {
        this.lobbyItems = lobbyItems;
    }

    public EconomyAPI getEconomyAPI() {
        return economyAPI;
    }

    public void setEconomyAPI(EconomyAPI economyAPI) {
        this.economyAPI = economyAPI;
    }
}
