package de.murmelmeister.lobbysystem.utils;

import de.murmelmeister.lobbysystem.LobbySystem;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

public final class LobbyItems {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final Server server;
    private final Map<UUID, Float> rainbowHue;

    private final NamespacedKey navigatorKey;
    private final NamespacedKey rainbowBootsKey;

    public LobbyItems(LobbySystem plugin) {
        this.server = plugin.getServer();
        this.rainbowHue = plugin.getRainbowHue();
        this.navigatorKey = new NamespacedKey(plugin, "navigator");
        this.rainbowBootsKey = new NamespacedKey(plugin, "rainbow_boots");
    }

    public void setLobbyItems(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        player.setHealth(20.0D);
        player.setFoodLevel(20);

        player.getInventory().setItem(4, createCompass());
    }

    private ItemStack createCompass() {
        ItemStack itemStack = new ItemStack(Material.COMPASS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(navigatorKey, PersistentDataType.STRING, "navigator");
        itemMeta.displayName(miniMessage.deserialize("<#6600bb>Navigator"));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private void setRainbowArmor(Player player, float hue) {
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        org.bukkit.Color bootsColor = org.bukkit.Color.fromBGR(getRGB(hue).getBlue(), getRGB(hue).getGreen(), getRGB(hue).getRed());
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        PersistentDataContainer container = bootsMeta.getPersistentDataContainer();
        container.set(rainbowBootsKey, PersistentDataType.STRING, "rainbow_boots");

        bootsMeta.setColor(bootsColor);
        bootsMeta.displayName(miniMessage.deserialize("<rainbow>Rainbow Boots"));
        bootsMeta.setUnbreakable(true);
        bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        boots.setItemMeta(bootsMeta);

        player.getInventory().setBoots(boots);
    }

    public BukkitRunnable rainbowLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                rainbowHue.forEach((uuid, h) -> {
                    Player player = server.getPlayer(uuid);
                    if (player != null && player.isOnline()) {
                        h = handleColor(h);
                        setRainbowArmor(player, h);
                        rainbowHue.put(uuid, h);
                    }
                });
            }
        };
    }

    private Color getRGB(float hue) {
        return Color.getHSBColor(hue, 1f, 1f);
    }

    private float handleColor(float hue) {
        hue += (float) 0.0045;
        if (hue > 1.0f)
            hue = 0.0f;
        return hue;
    }

    public NamespacedKey getNavigatorKey() {
        return navigatorKey;
    }

    public NamespacedKey getRainbowBootsKey() {
        return rainbowBootsKey;
    }
}
