package de.murmelmeister.lobbysystem.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.utils.LocationUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

import java.util.List;
import java.util.UUID;

public class ListenerOthers extends Listeners {
    public ListenerOthers(LobbySystem plugin) {
        super(plugin);
    }

    @EventHandler
    public void handlePlayerFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handlePlayerDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void handlePlayerPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(!(event.getEntity() instanceof Player));
    }

    @EventHandler
    public void handleExplodeDamage(BlockExplodeEvent event) {
        event.setCancelled(true);
        event.setYield(0f);
    }

    @EventHandler
    public void handleXpBar(PlayerLevelChangeEvent event) {
        event.getPlayer().setLevel(2022);
        event.getPlayer().setExp(0.25F);
    }

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        List<UUID> buildMode = getPlugin().getBuildMode();
        event.setExpToDrop(0);
        event.setCancelled(!buildMode.contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        List<UUID> buildMode = getPlugin().getBuildMode();
        event.setCancelled(!buildMode.contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void handlePlayerMove(PlayerMoveEvent event) {
        LocationUtil locationUtil = getLocationUtil();
        List<UUID> buildMode = getPlugin().getBuildMode();
        Player player = event.getPlayer();

        if (buildMode.contains(player.getUniqueId())) {
            event.setCancelled(false);
            return;
        }

        try {
            if (player.getLocation().getBlockY() <= locationUtil.getDeathHeight())
                player.teleport(locationUtil.getLocation("Spawn"));
        } catch (NullPointerException ignored) {
            throw new NullPointerException("LocationUtil is not initialized or 'DeathHeight' is not set in the config.");
        }
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    @EventHandler
    public void handlePlayerInventoryClick(InventoryClickEvent event) {
        List<UUID> buildMode = getPlugin().getBuildMode();
        Player player = (Player) event.getWhoClicked();

        // Build mode can use the build mode inventory or settings inventory (It is a bug)
        if (buildMode.contains(player.getUniqueId()))
            event.setCancelled(!event.getView().getPlayer().equals(player));
        else
            event.setCancelled(event.getView().getPlayer().equals(player));
    }

    @EventHandler
    public void handleFarmLandEvent(PlayerJumpEvent event) {
        Block block = event.getPlayer().getLocation().getBlock();

        if (block.getType().equals(Material.FARMLAND) || block.getType().equals(Material.WHEAT))
            event.setCancelled(true);
    }
}
