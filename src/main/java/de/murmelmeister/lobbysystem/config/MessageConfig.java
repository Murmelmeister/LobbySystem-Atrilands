package de.murmelmeister.lobbysystem.config;

import de.murmelmeister.lobbysystem.utils.LobbyItems;
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
        this.file = new File("./plugins/" + LobbyItems.class.getSimpleName() + "/", "message.yml");
        final File parent = file.getParentFile();
        if (!parent.exists()) {
            boolean success = parent.mkdirs();
            if (!success) logger.warn("Could not create directory: {}", parent.getAbsolutePath());
        }

        if (!file.exists()) {
            try {
                boolean success = file.createNewFile();
                if (!success) logger.warn("Could not create file: {}", file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
