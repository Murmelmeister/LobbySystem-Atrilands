package de.murmelmeister.lobbysystem.listeners;

import de.murmelmeister.lobbysystem.LobbySystem;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LobbyListener extends Listeners {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public LobbyListener(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() != Material.COMPASS &&
            item.getItemMeta().displayName() != miniMessage.deserialize("<dark_aqua>Navigator"))
            return;
        if (!event.getAction().isRightClick()) return;

        Inventory inventory = player.getServer().createInventory(null, 9, miniMessage.deserialize("<red>Navigator"));

        inventory.setItem(0, createItem());
        inventory.setItem(1, createItem());
        inventory.setItem(2, createItem());
        inventory.setItem(3, createItemTest1());
        inventory.setItem(4, createItem());
        inventory.setItem(5, createItem());
        inventory.setItem(6, createItem());

        player.openInventory(inventory);
        player.setMetadata("Navigator", new FixedMetadataValue(getPlugin(), inventory));
    }

    @EventHandler
    public void handleInventoryGameModes(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!player.hasMetadata("Navigator")) return;
        if (!player.getMetadata("Navigator").get(0).asString().equals("Navigator")) return;

        event.setCancelled(true);

        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null) return;
        if (currentItem.getType() != Material.GRASS_BLOCK &&
            currentItem.getItemMeta().displayName() != miniMessage.deserialize("<red>Test1")) return;

        player.closeInventory();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF("Survival-1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sendMessage(player, "<#999999>Connect to Survival-1...");

        player.sendPluginMessage(getPlugin(), "BungeeCord", b.toByteArray());
    }

    @EventHandler
    public void handleInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("Navigator"))
            player.removeMetadata("Navigator", getPlugin());
    }

    private ItemStack createItem() {
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(miniMessage.deserialize("<red>"));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack createItemTest1() {
        ItemStack itemStack = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(miniMessage.deserialize("<red>Test1"));

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
