package de.murmelmeister.lobbysystem.commands;

import de.murmelmeister.lobbysystem.LobbySystem;
import org.bukkit.command.TabExecutor;

import java.util.Objects;

public class CommandManager {

    private final LobbySystem instance = LobbySystem.getInstance();

    public void registerCommands() {
        setCommand("build", new CommandBuild());
        setCommand("deathheight", new CommandDeathHeight());
        setCommand("lobbysystem", new CommandLobbySystem());
        setCommand("setdeathheight", new CommandSetDeathHeight());
        setCommand("setspawn", new CommandSetSpawn());
        setCommand("spawn", new CommandSpawn());
    }

    private void setCommand(String commandName, TabExecutor command) {
        Objects.requireNonNull(this.instance.getCommand(commandName)).setExecutor(command);
        Objects.requireNonNull(this.instance.getCommand(commandName)).setTabCompleter(command);
    }

}
