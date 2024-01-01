package com.github.miyasoku.spawn.commands;

import com.github.miyasoku.spawn.Spawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandSetSpawn implements CommandExecutor {

    private final Spawn plugin;

    public CommandSetSpawn(Spawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("lobby.setspawn") || p.isOp()) {
                
                Location location = p.getLocation();

                plugin.getConfig().set("spawn", location);

                try {
                    plugin.saveConfig();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                p.sendMessage(ChatColor.GREEN + "スポーン地点の座標を " + ChatColor.YELLOW + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ", " + ChatColor.GREEN + " に設定しました！");

            } else {

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("no-permission"))));

            }
        } else {

            sender.sendMessage(ChatColor.RED + "ゲーム内からでしか使用することは出来ません！");

        }

        return true;
    }
}
