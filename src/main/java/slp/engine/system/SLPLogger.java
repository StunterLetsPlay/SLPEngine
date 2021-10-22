package slp.engine.system;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class SLPLogger {

    private ArrayList<Player> targets;

    private String prefix;
    private String msg;

    /**
     * @param prefix The Prefix of your Plugin
     * @param msg The Message it writes
     * @return Builder of the Logger
     */
    public static SLPLogger createLog(String prefix, String msg) {
        SLPLogger logger = new SLPLogger();
        logger.setPrefix(prefix);
        logger.setMsg(msg);
        return logger;
    }

    /**
     * @param prefix The Prefix of your Plugin
     * @return Builder of the Logger
     */
    public SLPLogger setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * @param msg The Message it writes
     * @return Builder of the Logger
     */
    public SLPLogger setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * @param list The List of Players that should receive the Log
     * @return Builder of the Logger
     */
    public SLPLogger setPlayers(ArrayList<Player> list) {
        targets = list;
        return this;
    }

    /**
     * @param target The Player that should receive the Log
     * @return Builder of the Logger
     */
    public SLPLogger setPlayer(Player target) {
        targets = new ArrayList<>(Collections.singleton(target));
        return this;
    }

    /**
     * @apiNote If no Players have been set, it will set the Target Players to every OPPED Player on the Server
     */
    private void checkPlayers() {
        if (targets == null){
            targets = new ArrayList<>();

            Bukkit.getOnlinePlayers().forEach(player -> {
                if (player.isOp())
                    targets.add(player);
            });
        }
    }

    public void logError() {
        checkPlayers();
        targets.forEach(p -> p.sendMessage((prefix + SLPHexColor.RED + "§lERROR!"
                + "\n"
                + prefix + SLPHexColor.DARK_RED + msg).replace("§r", SLPHexColor.DARK_RED)));
    }

    public void logWarning() {
        checkPlayers();
        targets.forEach(p -> p.sendMessage((prefix + SLPHexColor.YELLOW + "§oWARNING"
                + "\n"
                + prefix + SLPHexColor.ORANGE + msg).replace("§r", SLPHexColor.ORANGE)));
    }

    public void logDebug() {
        checkPlayers();
        targets.forEach(p -> p.sendMessage((prefix + SLPHexColor.CYAN + "DEBUG"
                + "\n"
                + prefix + SLPHexColor.BLUE + msg).replace("§r", SLPHexColor.BLUE)));
    }
    
}
