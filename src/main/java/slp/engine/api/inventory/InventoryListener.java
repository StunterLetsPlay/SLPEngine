package slp.engine.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.CraftingInventory;
import slp.engine.system.SLPEngine;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Bukkit.getScheduler().runTask(SLPEngine.getInstance(), () -> {
            if (e.getPlayer().getOpenInventory().getTopInventory() instanceof CraftingInventory) {
                InventoryBuilder.clearNonClickableInventory(e.getInventory());
            }
        });
    }

    @EventHandler
    public void onNonClickableInventoryClick(InventoryClickEvent e) {
        if (InventoryBuilder.isNonClickable(e.getClickedInventory()))
            e.setCancelled(true);
    }

}
