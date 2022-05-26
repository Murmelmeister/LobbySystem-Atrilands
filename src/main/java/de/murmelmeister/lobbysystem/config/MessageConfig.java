package de.murmelmeister.lobbysystem.config;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.HexColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {

    private final LobbySystem instance = LobbySystem.getInstance();

    private File folder;
    private File file;
    private YamlConfiguration config;

    public MessageConfig() {
        createFile();
        saveFile();
    }

    public void createFile() {
        setFolder(new File("plugins//LobbySystem//"));
        if (!(getFolder().exists())) {
            boolean aBoolean = getFolder().mkdirs();
            if (!(aBoolean)) this.instance.getSLF4JLogger().warn("Der 'Folder' kann kein zweites mal erstellt werden.");
        }

        setFile(new File(getFolder(), "message.yml"));
        if (!(getFile().exists())) {
            try {
                boolean aBoolean = getFile().createNewFile();
                if (!(aBoolean))
                    this.instance.getSLF4JLogger().warn("Die File 'message.yml' kann kein zweites mal erstellt werden.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setConfig(YamlConfiguration.loadConfiguration(getFile()));
            createConfig();
            return;
        }
        setConfig(YamlConfiguration.loadConfiguration(getFile()));
        loadConfig();
    }

    public void saveFile() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createConfig() {
        this.instance.setPrefix(HexColor.format("#999999[#4200ffLobbySystem#999999] §r"));
        getConfig().set("Prefix", this.instance.getPrefix());
        getConfig().set("Permission.Build.Use", "lobbysystem.command.build.use");
        getConfig().set("Permission.Build.Other", "lobbysystem.command.build.other");
        getConfig().set("Permission.DeathHeight", "lobbysystem.command.deathheiht");
        getConfig().set("Permission.SetDeathHeight", "lobbysystem.command.setdeathheiht");
        getConfig().set("Permission.SetSpawn", "lobbysystem.command.setspawn");
        getConfig().set("Permission.Spawn.Use", "lobbysystem.command.spawn.use");
        getConfig().set("Permission.Spawn.Other", "lobbysystem.command.spawn.other");
        getConfig().set("Permission.LobbySystem.Use", "lobbysystem.command.lobbysystem.use");
        getConfig().set("Permission.LobbySystem.Reload", "lobbysystem.command.lobbysystem.reload");
        getConfig().set("Permission.LobbyItem.Boots", "lobbysystem.lobbyitem.boots");
        getConfig().set("Message.NoPermission", HexColor.format("#ff0000Dazu hast du keine Rechte."));
        getConfig().set("Message.NoConsole", HexColor.format("#ff0000Dieser Command funktioniert nicht in der Console."));
        getConfig().set("Message.PlayerIsNotOnline", HexColor.format("#999999Der Spieler #ffb500[PLAYER] #999999ist nicht auf diesem Server #ff0000online#999999."));
        getConfig().set("Message.LobbySystem.ReloadMessage", HexColor.format("#00ff00Die Konfigurationen wurden neugeladen."));
        getConfig().set("Message.LobbySystem.Syntax", HexColor.format("#999999Syntax: #ff0000/lobbysystem [reload]"));
        getConfig().set("Message.Build.OnBuildmode", HexColor.format("#999999Du bist im #00ff00Buildmode#999999."));
        getConfig().set("Message.Build.OffBuildmode", HexColor.format("#999999Du bist nicht mehr im #ff0000Buildmode#999999."));
        getConfig().set("Message.Build.OnBuildmodeOther", HexColor.format("#999999Du hast #ffb500[PLAYER] #999999im #00ff00Buildmode #999999gesetzt."));
        getConfig().set("Message.Build.OffBuildmodeOther", HexColor.format("#999999Du hast #ffb500[PLAYER] #999999aus dem #ff0000Buildmode #999999gesetzt."));
        getConfig().set("Message.Build.Syntax", HexColor.format("#999999Syntax: #ff0000/build [player]"));
        getConfig().set("Message.SetDeathHeight", HexColor.format("#999999Du hast die #00ff00todes Höhe #999999gesetzt. #777777(#00ff00Höhe#777777: #00ff00[HEIGHT]#777777)"));
        getConfig().set("Message.DeathHeight", HexColor.format("#999999Die #00ff00todes Höhe #999999ist auf #00ff00[HEIGHT]#999999."));
        getConfig().set("Message.SetSpawn", HexColor.format("#999999Du hast den #00ff00Spawn #999999gesetzt."));
        getConfig().set("Message.Spawn.Teleport", HexColor.format("#999999Du hast dich zum #00ff00Spawn #999999teleportiert."));
        getConfig().set("Message.Spawn.TeleportOther", HexColor.format("#999999Du hast #ffb500[PLAYER] #999999zum #00ff00Spawn #999999teleportiert."));
        getConfig().set("Message.Spawn.Syntax", HexColor.format("#999999Syntax: #ff0000/spawn [player]"));
    }

    private void loadConfig() {
        HexColor.format(getConfig().getString("Prefix"));
        getConfig().getString("Permission.Build.Use");
        getConfig().getString("Permission.Build.Other");
        getConfig().getString("Permission.DeathHeight");
        getConfig().getString("Permission.SetDeathHeight");
        getConfig().getString("Permission.SetSpawn");
        getConfig().getString("Permission.Spawn.Use");
        getConfig().getString("Permission.Spawn.Other");
        getConfig().getString("Permission.LobbySystem.Use");
        getConfig().getString("Permission.LobbySystem.Reload");
        getConfig().getString("Permission.LobbyItem.Boots");
        HexColor.format(getConfig().getString("Message.NoPermission"));
        HexColor.format(getConfig().getString("Message.NoConsole"));
        HexColor.format(getConfig().getString("Message.PlayerIsNotOnline"));
        HexColor.format(getConfig().getString("Message.LobbySystem.ReloadMessage"));
        HexColor.format(getConfig().getString("Message.LobbySystem.Syntax"));
        HexColor.format(getConfig().getString("Message.Build.OnBuildmode"));
        HexColor.format(getConfig().getString("Message.Build.OffBuildmode"));
        HexColor.format(getConfig().getString("Message.Build.OnBuildmodeOther"));
        HexColor.format(getConfig().getString("Message.Build.OffBuildmodeOther"));
        HexColor.format(getConfig().getString("Message.Build.Syntax"));
        HexColor.format(getConfig().getString("Message.SetDeathHeight"));
        HexColor.format(getConfig().getString("Message.DeathHeight"));
        HexColor.format(getConfig().getString("Message.SetSpawn"));
        HexColor.format(getConfig().getString("Message.Spawn.Teleport"));
        HexColor.format(getConfig().getString("Message.Spawn.TeleportOther"));
        HexColor.format(getConfig().getString("Message.Spawn.Syntax"));
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }
}
