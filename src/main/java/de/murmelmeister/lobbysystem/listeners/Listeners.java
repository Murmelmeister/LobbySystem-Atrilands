package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.ArrayListUtil;
import de.murmelmeister.lobbysystem.utils.HexColor;
import de.murmelmeister.lobbysystem.utils.LocationUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Listeners implements Listener {

    protected LobbySystem instance = LobbySystem.getInstance();

    protected ArrayListUtil arrayListUtil = this.instance.getArrayListUtil();
    protected LocationUtil locationUtil = this.instance.getLocationUtil();

    public void registerListeners() {
        setListener(new ListenerOthers());
        setListener(new ListenerDamage());
        setListener(new ListenerConnect());
    }

    private void setListener(Listener listener) {
        this.instance.getServer().getPluginManager().registerEvents(listener, this.instance);
    }

    protected void setSendMessage(CommandSender sender, String message) {
        sender.sendMessage(this.instance.getMessageConfig().getConfig().getString("Prefix") + HexColor.format(message));
    }

}
