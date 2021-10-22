package slp.engine.system;

import net.md_5.bungee.api.ChatColor;

import java.util.Arrays;

/**
 * @version 1.0
 * @since 22.10.2021
 */
public class SLPHexColor {

    public static String WHITE = convertToHexString("#ffffff");
    public static String GRAY = convertToHexString("#9e9e9e");
    public static String CYAN = convertToHexString("#3dd8ff");
    public static String BLUE = convertToHexString("#37a3ff");
    public static String MAGENTA = convertToHexString("#ff00ff");
    public static String PURPLE = convertToHexString("#9900ff");
    public static String DARK_RED = convertToHexString("#aa0000");
    public static String RED = convertToHexString("#ff0000");
    public static String ORANGE = convertToHexString("#ff6600");
    public static String YELLOW = convertToHexString("#ffff00");
    public static String TURQUOISE = convertToHexString("#00ffcc");
    public static String GREEN = convertToHexString("#00ff00");
    public static String DARK_GREEN = convertToHexString("#00aa00");

    /*
        Reusing method from old API
     */
    /**
     * @param msg The Message you want to convert Hex Strings to actual Color
     * @return The color-coded Message
     */
    public static String convertToHexString(String msg) {
        String newMsg = msg;
        for (int i = 0; i < msg.length(); i++) {
            Character character = msg.charAt(i);

            if (!character.equals('#'))
                continue;

            int size = 7;
            char[] chars = new char[7];

            boolean cancel = false;

            if (msg.length() >= i + size) {
                msg.getChars(i, i + size, chars, 0);
            } else {
                cancel = true;
            }

            StringBuilder chunk = new StringBuilder();
            Character[] acceptable = new Character[]{'#', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (Character c : chars) {
                if (Arrays.asList(acceptable).contains(c)) {
                    chunk.append(c);
                } else {
                    cancel = true;
                }
            }

            if (!cancel) {
                newMsg = newMsg.replace(chunk.toString(), ChatColor.of(chunk.toString()).toString());
            }
        }

        return newMsg;
    }
    
}
