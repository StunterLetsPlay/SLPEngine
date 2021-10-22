package slp.engine.api;

import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class ItemBuilder {

    private ItemStack item;

    /**
     * @param item The ItemStack you want to fetch Data from
     * @return Returns the ItemBuilder with the Data of the fetched ItemStack
     */
    public static ItemBuilder fromItemStack(ItemStack item) {
        ItemBuilder builder = new ItemBuilder();
        builder.setItem(item);
        return builder;
    }

    /**
     * @param item Set the currently edited ItemStack to another ItemStack
     */
    private void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * @param material The Material you want your new ItemStack to be
     * @return Returns the ItemBuilder with the Data of a new ItemStack
     */
    public static ItemBuilder buildNewItem(Material material){
        ItemBuilder builder = new ItemBuilder();
        builder.setItem(new ItemStack(material));
        return builder;
    }

    /**
     * @param material The Material you want your new ItemStack to be
     * @param amount The Amount of the ItemStack
     * @return Returns the ItemBuilder with the Data of a new ItemStack
     */
    public static ItemBuilder buildNewItem(Material material, int amount){
        ItemBuilder builder = new ItemBuilder();
        builder.setItem(new ItemStack(material, amount));
        return builder;
    }


    /**
     * @param name The Display Name you want your Item to have
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setDisplayName(String name) {
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(name);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param lore The Lore you want your ItemStack to have
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> loreList = new ArrayList<>();

        for (String snippet : lore) {
            loreList.add(snippet.replace("\n", " "));
        }

        Objects.requireNonNull(meta).setLore(loreList);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param lore The Lore you want to add to your current Lore
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addToLore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> loreList = meta != null && meta.getLore() != null ? new ArrayList<>(meta.getLore()) : new ArrayList<>();

        for (String snippet : lore) {
            loreList.add(snippet.replace("\n", " "));
        }

        Objects.requireNonNull(meta).setLore(loreList);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param enchantment The Enchantment you want to add
     * @param level The Level the Enchantment should have
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).addEnchant(enchantment, level, true);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param enchantment The Enchantment you want to remove
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        ItemMeta meta = item.getItemMeta();
        if (Objects.requireNonNull(meta).hasEnchant(enchantment)) {
            meta.removeEnchant(enchantment);
        }
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param var If the Item should be unbreakable or not
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setUnbreakable(boolean var) {
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setUnbreakable(var);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param itemFlags The Item Flag you want to add
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).addItemFlags(itemFlags);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param id The Custom Model Data your Item should have (Good for Resource Packs)
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setCustomModel(int id) {
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).setCustomModelData(id);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param pattern The Banner Pattern you want to add to your Item
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addBannerPattern(Pattern pattern) {
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        Objects.requireNonNull(meta).addPattern(pattern);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param i The Slot you want to change the Pattern
     * @param pattern The Banner Pattern you want to set to your Item
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setBannerPattern(int i, Pattern pattern) {
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setPattern(i, pattern);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param pageContent Adds a new Page with the Text you specify
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addBookPage(String... pageContent) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        Objects.requireNonNull(meta).addPage(pageContent);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param author The Author of the Book
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setBookAuthor(String author) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setAuthor(author);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param title The Title of the Book
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setBookTitle(String title) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setTitle(title);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param generaton The Generation of the Book (If it's a Copy, the Original, ...)
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setBookGeneraton(BookMeta.Generation generaton) {
        BookMeta meta = (BookMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setGeneration(generaton);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param power The Lifetime of a Firework Rocket
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setFireworkPower(int power) {
        FireworkMeta meta = (FireworkMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setPower(power);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param effect Adds a Firework Effect
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addFireworkEffect(FireworkEffect effect) {
        FireworkMeta meta = (FireworkMeta) item.getItemMeta();
        Objects.requireNonNull(meta).addEffect(effect);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param color Sets the Color of the Leather Armor Piece
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setLeatherArmorColor(Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setColor(color);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param effect Adds a Potion Effect to the Potion
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder addCustomPotionEffect(PotionEffect effect) {
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        Objects.requireNonNull(meta).addCustomEffect(effect, true);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param color Sets the Color of the Potion
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setPotionColor(Color color) {
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setColor(color);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @param player The Player Name the Skull should be matched to
     * @return Returns the ItemBuilder with the Data of the current ItemStack
     */
    public ItemBuilder setSkullOwner(OfflinePlayer player) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setOwningPlayer(player);
        item.setItemMeta(meta);

        return this;
    }

    /**
     * @return Finishes Building the Item and returns as a ItemStack
     */
    public ItemStack build() {
        return this.item;
    }

}
