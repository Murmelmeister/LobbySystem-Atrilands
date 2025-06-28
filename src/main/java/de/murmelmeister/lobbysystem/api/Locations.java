package de.murmelmeister.lobbysystem.api;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.FileUtil;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Locations {
    private final Logger logger;
    private final Server server;
    private File file;
    private YamlConfiguration config;

    public Locations(Logger logger, Server server) {
        this.logger = logger;
        this.server = server;
        create();
    }

    public void reload() {
        create();
    }

    private void create() {
        this.file = FileUtil.createFile(logger, "./plugins/" + LobbySystem.class.getSimpleName() + "/", "locations.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            this.config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLocation(String name, Location location) {
        double x = location.getBlockX() + 0.5D;
        double y = location.getBlockY() + 0.25D;
        double z = location.getBlockZ() + 0.5D;
        double yaw = (Math.round(location.getYaw() / 45.0F) * 45);
        double pitch = (Math.round(location.getPitch() / 45.0F) * 45);
        String worldName = Objects.requireNonNull(location.getWorld()).getName();
        config.set("Locations." + name + ".X", x);
        config.set("Locations." + name + ".Y", y);
        config.set("Locations." + name + ".Z", z);
        config.set("Locations." + name + ".Yaw", yaw);
        config.set("Locations." + name + ".Pitch", pitch);
        config.set("Locations." + name + ".WorldName", worldName);
        save();
    }

    public Location getLocation(String name) {
        double x = config.getDouble("Locations." + name + ".X");
        double y = config.getDouble("Locations." + name + ".Y");
        double z = config.getDouble("Locations." + name + ".Z");
        double yaw = config.getDouble("Locations." + name + ".Yaw");
        double pitch = config.getDouble("Locations." + name + ".Pitch");
        String worldName = config.getString("Locations." + name + ".WorldName");
        assert worldName != null;
        Location location = new Location(server.getWorld(worldName), x, y, z);
        location.setYaw((float) yaw);
        location.setPitch((float) pitch);

        return location;
    }

    public void removeLocation(String name) {
        config.set("Locations." + name, null);
        save();
    }

    public boolean existsLocation(String name) {
        return (config.get("Locations." + name) != null);
    }

    public double getY(String name) {
        return config.getDouble("Locations." + name + ".Y");
    }

    public double getX(String name) {
        return config.getDouble("Locations." + name + ".X");
    }

    public double getZ(String name) {
        return config.getDouble("Locations." + name + ".Z");
    }
}
