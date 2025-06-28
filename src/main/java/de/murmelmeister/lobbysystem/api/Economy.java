package de.murmelmeister.lobbysystem.api;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.FileUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Economy {
    private final Logger logger;
    private File file;
    private YamlConfiguration config;

    public Economy(Logger logger) {
        this.logger = logger;
        create();
    }

    public void reload() {
        create();
    }

    private void create() {
        this.file = FileUtil.createFile(logger, "./plugins/" + LobbySystem.class.getSimpleName() + "/", "economy.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            this.config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getDefaultMoney() {
        return 0.0D;
    }

    public boolean hasAccount(UUID uuid) {
        return this.config.get("Economy." + uuid.toString() + ".Money") != null;
    }

    public void createAccount(UUID uuid) {
        this.config.set("Economy." + uuid.toString() + ".Money", getDefaultMoney());
        save();
    }

    public double getMoney(UUID uuid) {
        return this.config.getDouble("Economy." + uuid.toString() + ".Money");
    }

    public void setMoney(UUID uuid, double amount) {
        this.config.set("Economy." + uuid.toString() + ".Money", amount);
        save();
    }

    public void addMoney(UUID uuid, double amount) {
        double money = getMoney(uuid) + amount;
        this.config.set("Economy." + uuid + ".Money", money);
        save();
    }

    public void removeMoney(UUID uuid, double amount) {
        double money = getMoney(uuid) - amount;
        this.config.set("Economy." + uuid + ".Money", money);
        save();
    }

    public void resetMoney(UUID uuid) {
        this.config.set("Economy." + uuid.toString() + ".Money", getDefaultMoney());
        save();
    }

    public boolean hasEnoughMoney(UUID uuid, double amount) {
        return getMoney(uuid) >= amount;
    }
}
