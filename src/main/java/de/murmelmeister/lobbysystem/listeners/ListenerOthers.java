package de.murmelmeister.lobbysystem.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ListenerOthers extends Listeners {

    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(!(event.getEntity() instanceof Player));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleExplodeDamage(BlockExplodeEvent event) {
        event.setCancelled(true);
        event.setYield(0f);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void handleXpBar(PlayerLevelChangeEvent event) {
        event.getPlayer().setLevel(2022);
        event.getPlayer().setExp(0.25F);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleBlockBreak(BlockBreakEvent event) {
        event.setExpToDrop(0);
        event.setCancelled(!this.arrayListUtil.getBuildmode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!this.arrayListUtil.getBuildmode().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (this.arrayListUtil.getBuildmode().contains(player.getUniqueId())) {
            event.setCancelled(false);
            return;
        }

        try {
            if (player.getLocation().getBlockY() <= this.locationUtil.getDeathHeight()) {
                player.teleport(this.locationUtil.getLocation("Spawn"));
            }
        } catch (NullPointerException ignored) {
            setSendMessage(player.getServer().getConsoleSender(), "#ff0000Error NullPointerException [ListenerOthers.java:81]");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerDeath(PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // Build mode can use the build mode inventory or settings inventory (It is a bug)
        if (this.arrayListUtil.getBuildmode().contains(player.getUniqueId())) {
            event.setCancelled(!event.getView().getPlayer().equals(player));
        } else {
            event.setCancelled(event.getView().getPlayer().equals(player));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleFarmLandEvent(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        Block block = event.getPlayer().getLocation().getBlock();

        if(block.getType().equals(Material.FARMLAND) || block.getType().equals(Material.WHEAT)) {
            if(player.isJumping()) {
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }
}
