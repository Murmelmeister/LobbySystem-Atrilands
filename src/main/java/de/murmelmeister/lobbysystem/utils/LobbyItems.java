package de.murmelmeister.lobbysystem.utils;

import de.murmelmeister.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;

public class LobbyItems {
    private final ArrayListUtil arrayListUtil = LobbySystem.getInstance().getArrayListUtil();

    public void setLobbyItems(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(20.0D);
        player.setFoodLevel(20);

        player.getInventory().setItem(4, createCompass());
    }

    private ItemStack createCompass() {
        ItemStack itemStack = new ItemStack(Material.COMPASS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§3Navigator");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private void setRainbowArmor(Player player, float hue) {
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
        org.bukkit.Color bootsColor = org.bukkit.Color.fromBGR(getRGB(hue).getBlue(), getRGB(hue).getGreen(), getRGB(hue).getRed());
        LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsMeta.setColor(bootsColor);
        bootsMeta.setDisplayName("§3Rainbow Boots");
        bootsMeta.setUnbreakable(true);
        bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        boots.setItemMeta(bootsMeta);
        player.getInventory().setBoots(boots);
    }

    public BukkitRunnable rainbowLoop() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                arrayListUtil.getHue().forEach((uuid, h) -> {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player != null && player.isOnline()) {
                        h = handleColor(h);
                        setRainbowArmor(player, h);
                        arrayListUtil.getHue().put(uuid, h);
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

}
