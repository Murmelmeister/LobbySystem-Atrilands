package de.murmelmeister.lobbysystem.utils;

import de.murmelmeister.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LocationUtil {

    private final LobbySystem instance = LobbySystem.getInstance();

    private final File folder = new File("plugins//LobbySystem//");
    private File file;
    private YamlConfiguration config;

    public void createFile() {
        if (!this.folder.exists()) {
            boolean aBoolean = this.folder.mkdirs();
            if(!(aBoolean)) this.instance.getSLF4JLogger().warn("Der 'Folder' kann kein zweites mal erstellt werden.");
        }

        this.file = new File(this.getFolder(), "location.yml");

        if (!file.exists()) {
            try {
                boolean aBoolean = file.createNewFile();
                if(!(aBoolean)) this.instance.getSLF4JLogger().warn("Die File 'location.yml' kann kein zweites mal erstellt werden.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveLocations() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getSpawnY() {
        createFile();
        return this.getConfig().getDouble("Locations.Spawn.Y");
    }

    public double getSpawnX() {
        createFile();
        return this.getConfig().getDouble("Locations.Spawn.X");
    }

    public double getSpawnZ() {
        createFile();
        return this.getConfig().getDouble("Locations.Spawn.Z");
    }

    public double getDeathHeight() {
        createFile();
        return this.getConfig().getDouble("Locations.Height");
    }

    public void setLocation(String name, Location location) {
        createFile();
        double x = location.getBlockX() + 0.5D;
        double y = location.getBlockY();
        double z = location.getBlockZ() + 0.5D;
        double yaw = (Math.round(location.getYaw() / 45.0F) * 45);
        double pitch = (Math.round(location.getPitch() / 45.0F) * 45);
        String worldName = Objects.requireNonNull(location.getWorld()).getName();
        this.getConfig().set("Locations." + name + ".X", x);
        this.getConfig().set("Locations." + name + ".Y", y);
        this.getConfig().set("Locations." + name + ".Z", z);
        this.getConfig().set("Locations." + name + ".Yaw", yaw);
        this.getConfig().set("Locations." + name + ".Pitch", pitch);
        this.getConfig().set("Locations." + name + ".worldName", worldName);
        saveLocations();
    }

    public void setDeathHeight(String name, double deathHeight) {
        createFile();
        this.getConfig().set("Locations." + name, deathHeight);
        saveLocations();
    }

    public void removeLocation(String name) {
        createFile();
        this.getConfig().set("Locations." + name, null);
        saveLocations();
    }

    public boolean locationIsExisting(String name) {
        createFile();
        return (this.getConfig().get("Locations." + name) != null);
    }

    public boolean heightIsExisting() {
        createFile();
        return (this.getConfig().get("Locations.Height") != null);
    }

    public Location getLocation(String name) {
        createFile();
        double x = this.getConfig().getDouble("Locations." + name + ".X");
        double y = this.getConfig().getDouble("Locations." + name + ".Y");
        double z = this.getConfig().getDouble("Locations." + name + ".Z");
        double yaw = this.getConfig().getDouble("Locations." + name + ".Yaw");
        double pitch = this.getConfig().getDouble("Locations." + name + ".Pitch");
        String worldName = this.getConfig().getString("Locations." + name + ".worldName");
        assert worldName != null;
        Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
        location.setYaw((float) yaw);
        location.setPitch((float) pitch);

        return location;
    }

    public File getFolder() {
        return folder;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

}
