package de.murmelmeister.lobbysystem.config;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.FileUtil;
import de.murmelmeister.lobbysystem.utils.Messages;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

public class MessageConfig {
    private final Logger logger;
    private File file;
    private YamlConfiguration config;

    public MessageConfig(Logger logger) {
        this.logger = logger;
        load();
    }

    public void reload() {
        create();
    }

    private void create() {
        this.file = FileUtil.createFile(logger, "./plugins/" + LobbySystem.class.getSimpleName() + "/", "message.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    private void load() {
        create();
        for (Messages messages : Messages.VALUES)
            if (get(messages) == null)
                set(messages);
        save();
    }

    private void save() {
        try {
            this.config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void set(Messages messages) {
        config.set(messages.getPath(), messages.getFallback());
    }

    private Object get(Messages messages) {
        return config.get(messages.getPath());
    }

    public String getMessage(Messages messages) {
        return config.getString(messages.getPath());
    }

    public String getPrefix() {
        return getMessage(Messages.PREFIX);
    }
}
