package de.murmelmeister.lobbysystem;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Main extends JavaPlugin {

    private String prefix;

    public abstract void onDisable();

    public abstract void onEnable();

    public abstract void onLoad();

    public abstract void init();

    public void handleDisableMessage() {
        this.getServer().getConsoleSender().sendMessage("§3" + this.getPluginName() + " §8» §7Plugin is§c disabled§7! §7Version: §e" + this.getVersion() + " §7by §b" + this.getAuthor());
    }

    public void handleEnableMessage() {
        this.getServer().getConsoleSender().sendMessage("§3" + this.getPluginName() + " §8» §7Plugin is§a enabled§7! §7Version: §e" + this.getVersion() + " §7by §b" + this.getAuthor());
    }

    public String getAuthor() {
        return "Murmelmeister";
    }

    public String getVersion() {
        return "a1.0.0";
    }

    public String getPluginName() {
        return "LobbySystem";
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
