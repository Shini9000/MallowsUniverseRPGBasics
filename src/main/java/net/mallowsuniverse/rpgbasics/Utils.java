package net.mallowsuniverse.rpgbasics;

import org.bukkit.ChatColor;

public class Utils {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String decolor(String s) {
        return ChatColor.stripColor(color(s));
    }
}
