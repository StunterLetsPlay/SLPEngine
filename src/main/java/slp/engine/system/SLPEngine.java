package slp.engine.system;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import slp.engine.api.inventory.InventoryListener;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class SLPEngine extends JavaPlugin {

    public static SLPEngine instance;
    public static String pr = SLPHexColor.BLUE + "§l" + "SLPEngine §r>> ";

    @Override
    public void onEnable() {
        instance = this;

        sendConsoleLoadMessage(pr, this.getDescription().getVersion());

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(pr + "§4Disabled!");
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
    }

    public void registerCommands() {

    }

    public static SLPEngine getInstance() {
        return instance;
    }

    public void sendConsoleLoadMessage(String pr, String version){
        Bukkit.getConsoleSender().sendMessage(pr + "§bEnabled!");
        Bukkit.getConsoleSender().sendMessage(pr + "§fVersion: §b" + version);
        Bukkit.getConsoleSender().sendMessage(pr + "§fCoded by: §b" + "StunterLetsPlay");
        Bukkit.getConsoleSender().sendMessage(pr + "§b§l" + "https://twitter.com/StunterLetsPlay");
    }
}