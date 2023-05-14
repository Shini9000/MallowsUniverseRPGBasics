package net.mallowsuniverse.rpgbasics.commands;

import net.mallowsuniverse.rpgbasics.RPGBasics;
import net.mallowsuniverse.rpgbasics.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Heal implements CommandExecutor {
    private RPGBasics plugin;

    public Heal(RPGBasics plugin) {
        this.plugin = plugin;
        plugin.getCommand("heal").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player)sender;
                double maxHealth = 20.0D;
                if (p.hasPermission("mubasics.command.heal.self") || p.hasPermission("mubasics.command.heal.*") || p.hasPermission("mu.command.*")) {
                    if (p.getHealth() < maxHealth) {
                        p.setHealth(maxHealth);
                        p.spawnParticle(Particle.HEART, p.getLocation().add(1.0D, 1.0D, 0.0D), 1);
                        p.spawnParticle(Particle.HEART, p.getLocation().add(0.0D, 1.0D, 1.0D), 1);
                        p.spawnParticle(Particle.HEART, p.getLocation().subtract(1.0D, -1.0D, 0.0D), 1);
                        p.spawnParticle(Particle.HEART, p.getLocation().subtract(0.0D, -1.0D, 1.0D), 1);
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealSelf")));
                    } else {
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealSelf_Full")));
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
            double maxHealth = 20.0D;
            if (p.hasPermission("mubasics.command.heal.other") || p.hasPermission("mubasics.command.heal.*") || p.hasPermission("mu.command.*")) {
                if (tp.getHealth() < maxHealth) {
                    if (Objects.equals(args[0], p.getDisplayName())) {
                        if (p.hasPermission("mubasics.command.heal.self") || p.hasPermission("mubasics.command.heal.*") || p.hasPermission("mu.command.*")) {
                            if (p.getHealth() < maxHealth) {
                                p.setHealth(maxHealth);
                                p.spawnParticle(Particle.HEART, p.getLocation().add(1.0D, 1.0D, 0.0D), 1);
                                p.spawnParticle(Particle.HEART, p.getLocation().add(0.0D, 1.0D, 1.0D), 1);
                                p.spawnParticle(Particle.HEART, p.getLocation().subtract(1.0D, -1.0D, 0.0D), 1);
                                p.spawnParticle(Particle.HEART, p.getLocation().subtract(0.0D, -1.0D, 1.0D), 1);
                                p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealSelf")));
                            } else {
                                p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealSelf_Full")));
                            }
                        } else {
                            p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.LackPermission")));
                        }
                    } else {
                        tp.setHealth(maxHealth);
                        tp.spawnParticle(Particle.HEART, p.getLocation().add(1.0D, 1.0D, 0.0D), 1);
                        tp.spawnParticle(Particle.HEART, p.getLocation().subtract(1.0D, -1.0D, 0.0D), 1);
                        tp.spawnParticle(Particle.HEART, p.getLocation().add(0.0D, 1.0D, 1.0D), 1);
                        tp.spawnParticle(Particle.HEART, p.getLocation().subtract(0.0D, -1.0D, 1.0D), 1);
                        tp.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.BeenHealed").replace("playerName", p.getDisplayName())));
                        p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealOthers").replace("playerName", tp.getDisplayName())));
                    }
                } else {
                    p.sendMessage(Utils.color(this.plugin.getConfig().getString("Commands.Heal.HealOthers_Full").replace("playerName", tp.getDisplayName())));
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
