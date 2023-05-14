package net.mallowsuniverse.rpgbasics.commands;

import net.mallowsuniverse.rpgbasics.RPGBasics;
import net.mallowsuniverse.rpgbasics.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Feed implements CommandExecutor {
    private RPGBasics plugin;

    public Feed(RPGBasics plugin) {
        this.plugin = plugin;
        plugin.getCommand("feed").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                int maxHunger = 20;
                if (p.hasPermission("mubasics.command.feed.self") || p.hasPermission("mubasics.command.feed.*") || p.hasPermission("mu.command.*")) {
                    if (p.getFoodLevel() < maxHunger) {
                        p.setFoodLevel(maxHunger);
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedSelf")));
                    } else {
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedSelf_Full")));
                    }
                } else {
                    p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.LackPermission")));
                }
            } else {
                sender.sendMessage(Utils.color("error contact admin"));
            }
        } else if (Bukkit.getPlayerExact(args[0]) != null) {
            Player p = (Player)sender;
            Player tp = Bukkit.getPlayerExact(args[0]);
            int maxHunger = 20;
            if (p.hasPermission("mubasics.command.feed.other") || p.hasPermission("mubasics.command.feed.*") || p.hasPermission("mu.command.*")) {
                if (tp.getFoodLevel() < maxHunger) {
                    if (Objects.equals(args[0], p.getDisplayName())) {
                        if (p.hasPermission("mubasics.command.feed.self") || p.hasPermission("mubasics.command.feed.*") || p.hasPermission("mu.command.*")) {
                            if (p.getFoodLevel() < maxHunger) {
                                p.setFoodLevel(maxHunger);
                                p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedSelf")));
                            } else {
                                p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedSelf_Full")));
                            }
                        } else {
                            p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.LackPermission")));
                        }
                    } else {
                        tp.setHealth(maxHunger);
                        tp.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.BeenFed").replace("playerName", p.getDisplayName())));
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedOthers").replace("playerName", tp.getDisplayName())));
                    }
                } else {
                    p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Feed.FeedOthers_Full").replace("playerName", tp.getDisplayName())));
                }
            } else {
                p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.LackPermission")));
            }
        } else {
            Player p = (Player)sender;
            p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.OfflinePlayer")));
        }
        return true;
    }
}