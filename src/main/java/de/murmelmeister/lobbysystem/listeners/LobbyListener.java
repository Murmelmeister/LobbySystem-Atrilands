package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LobbyListener extends Listeners {
    public LobbyListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) return;
        if (event.getAction().isRightClick()) {
            if (event.getItem().getType().equals(Material.COMPASS)) {
                if (event.getItem().getItemMeta().getDisplayName().equals("§3Navigator")) {
                    Inventory inventory = LobbySystem.getInstance().getServer().createInventory(null, 9, "§cNavigator");

                    inventory.setItem(0, createItem());
                    inventory.setItem(1, createItem());
                    inventory.setItem(2, createItem());
                    inventory.setItem(3, createItemTest1());
                    inventory.setItem(4, createItem());
                    inventory.setItem(5, createItem());
                    inventory.setItem(6, createItem());

                    player.openInventory(inventory);
                }
            }
        }
    }

    @EventHandler
    public void handleInventoryGameModes(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;
        if (!(event.getView().title().equals(Component.text("§cNavigator")))) return;
        event.setCancelled(true);
        if (event.getCurrentItem().getType().equals(Material.GRASS_BLOCK)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTest1")) {
                player.closeInventory();
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                try {
                    out.writeUTF("Connect");
                    out.writeUTF("Survival-1");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                sendMessage(player, "#999999Connect to Survival-1...");

                player.sendPluginMessage(getPlugin(), "BungeeCord", b.toByteArray());
            }
        }
    }

    private ItemStack createItem() {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§c");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack createItemTest1() {
        ItemStack itemStack = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§cTest1");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
