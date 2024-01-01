package com.github.miyasoku.spawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class CommandSpawn implements CommandExecutor {

    private final Plugin plugin;

    public CommandSpawn(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "ゲーム内でからしか使用することは出来ません！");
            return true;
        }

        Location location = plugin.getConfig().getLocation("spawn");

        if (location == null) {
            player.sendMessage(ChatColor.RED + "スポーンポイントが設定されていません。");
            return true;
        }

        player.setFallDistance(0);
        player.teleport(location);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("teleported-message"))));
        return true;
    }
}
