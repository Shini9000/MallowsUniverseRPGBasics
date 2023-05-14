package net.mallowsuniverse.rpgbasics;

import net.mallowsuniverse.rpgbasics.commands.Feed;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGBasics extends JavaPlugin {
    public static RPGBasics THIS;


    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        Bukkit.getConsoleSender().sendMessage(
                "Mallows Universe RPG Basics starting up!"
        );
        Bukkit.getConsoleSender().sendMessage(
                "Version: " + pdfFile.getVersion()
        );
        saveDefaultConfig();
        //new Heal(this);
        new Feed(this);
        //new God(this);
        //new Flight(this);
        //new connectionMethod(this);
        Bukkit.getConsoleSender().sendMessage(
                "Mallows Universe RPG Basics started up!" + "Version: " + pdfFile.getVersion()
        );
        THIS = this;
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(
                "MURPGBasics shutting down."
        );
    }
}
