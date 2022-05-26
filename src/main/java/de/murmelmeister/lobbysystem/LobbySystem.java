package de.murmelmeister.lobbysystem;

import de.murmelmeister.lobbysystem.commands.CommandManager;
import de.murmelmeister.lobbysystem.config.MessageConfig;
import de.murmelmeister.lobbysystem.listeners.Listeners;
import de.murmelmeister.lobbysystem.utils.ArrayListUtil;
import de.murmelmeister.lobbysystem.utils.LobbyItems;
import de.murmelmeister.lobbysystem.utils.LocationUtil;

import java.util.Objects;

public final class LobbySystem extends Main {

    private static LobbySystem instance;

    private Listeners listeners;
    private ArrayListUtil arrayListUtil;
    private LocationUtil locationUtil;
    private CommandManager commandManager;
    private MessageConfig messageConfig;
    private LobbyItems lobbyItems;

    @Override
    public void onDisable() {
        this.handleDisableMessage();
    }

    @Override
    public void onEnable() {
        init();
        getLobbyItems().rainbowLoop().runTaskTimer(this, 1, 1);
        this.handleEnableMessage();
    }

    @Override
    public void onLoad() {
        setInstance(this);
    }

    @Override
    public void init() {
        setArrayListUtil(new ArrayListUtil());
        setLocationUtil(new LocationUtil());
        setMessageConfig(new MessageConfig());
        setLobbyItems(new LobbyItems());
        setListeners(new Listeners());
        setCommandManager(new CommandManager());
        getListeners().registerListeners();
        getCommandManager().registerCommands();
    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public static void setInstance(LobbySystem instance) {
        LobbySystem.instance = instance;
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
}
