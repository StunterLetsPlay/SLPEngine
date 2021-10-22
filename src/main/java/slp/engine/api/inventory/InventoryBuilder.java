package slp.engine.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import slp.engine.system.SLPEngine;
import slp.engine.system.SLPHexColor;
import slp.engine.system.SLPLogger;

import java.util.ArrayList;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class InventoryBuilder {

    private Inventory inv;

    /**
     * @param inv Inventory you want to load Data from
     * @return current InventoryBuilder
     */
    public static InventoryBuilder fromInventory(Inventory inv){
        InventoryBuilder builder = new InventoryBuilder();
        builder.setInventory(inv);
        return builder;
    }

    /**
     * @param type Inventory Type
     * @param name Displayed Inventory Name
     * @return Current InventoryBuilder
     */
    public static InventoryBuilder buildNewInventory(InventoryType type, String name){
        InventoryBuilder builder = new InventoryBuilder();
        builder.setInventory(Bukkit.createInventory(null, type, name));
        return builder;
    }

    /**
     * Max amount of Rows an Inventory can normally have
     */
    public static int MAX_SIZE = 6;

    /**
     * @param size Amount of Rows your Inventory can have (NOTE! Can't be higher than MAX_SIZE)
     * @param name Displayed Inventory Name
     * @return Current InventoryBuilder
     */
    public static InventoryBuilder buildNewInventory(int size, String name){
        if (size > MAX_SIZE){
            SLPLogger.createLog(SLPEngine.pr, "Inventory Size can't be bigger than " + SLPHexColor.MAGENTA + MAX_SIZE + "§r!").logError();
            size = MAX_SIZE;
        }

        InventoryBuilder builder = new InventoryBuilder();
        builder.setInventory(Bukkit.createInventory(null, size * 9, name));
        return builder;
    }

    public InventoryBuilder setInventory(Inventory inv){
        this.inv = inv;
        return this;
    }

    /**
     * Adds an Item on the next free Slot
     *
     * @param item Item you want to add
     * @return Current InventoryBuilder
     */
    public InventoryBuilder addItem(ItemStack item){
        inv.addItem(item);
        return this;
    }

    /**
     * @param slot Slot you want to set the Item to
     * @param item Item you want to set
     * @return Current InventoryBuilder
     */
    public InventoryBuilder setItem(int slot, ItemStack item){
        inv.setItem(slot, item);
        return this;
    }

    /**
     * @param item Item you want to Fill
     * @param replace If the Item should replace current Item on Slot
     * @return Current InventoryBuilder
     */
    public InventoryBuilder fillInventory(ItemStack item, boolean replace){
        for (int i = 0; i < inv.getSize(); i++){
            if (!replace) {
                if (inv.getItem(i) == null) {
                    inv.setItem(i, item);
                }
            } else {
                inv.setItem(i, item);
            }
        }

        return this;
    }

    /**
     * @param item Item you want to Fill
     * @param replace If the Item should replace current Item on Slot
     * @param start First Slot
     * @param end Last Slot
     * @return Current InventoryBuilder
     */
    public InventoryBuilder fillInventorySlots(ItemStack item, int start, int end, boolean replace){
        for (int i = start; i <= end; i++){
            if (!replace) {
                if (inv.getItem(i) == null) {
                    inv.setItem(i, item);
                }
            } else {
                inv.setItem(i, item);
            }
        }

        return this;
    }

    /**
     * Clears the Inventory
     *
     * @return Current InventoryBuilder
     */
    public InventoryBuilder clearItems(){
        inv.clear();
        return this;
    }

    private boolean nonClickable;

    /**
     * Set the Inventory to cancel all Click Events
     * (Custom Click Events will still work)
     *
     * @return Current InventoryBuilder
     */
    public InventoryBuilder setNonClickable(){
        nonClickable = true;
        return this;
    }

    /**
     * @return Returns the finished Inventory
     */
    public Inventory build(){
        if (nonClickable)
            nonClickableList.add(inv);

        return inv;
    }

    private static ArrayList<Inventory> nonClickableList = new ArrayList<>();

    public static boolean isNonClickable(Inventory inv){
        return nonClickableList.contains(inv);
    }

    public static void clearNonClickableInventory(Inventory inv){
        nonClickableList.remove(inv);
    }

}
