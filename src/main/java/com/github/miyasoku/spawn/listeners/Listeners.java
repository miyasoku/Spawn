package com.github.miyasoku.spawn.listeners;

import com.github.miyasoku.spawn.Spawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Listeners implements Listener {

    private final Spawn plugin;

    public Listeners(Spawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (!e.getPlayer().hasPlayedBefore()) {

            Location location = plugin.getConfig().getLocation("spawn");

            if (location != null) {

                p.teleport(location);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("teleported message"))));

            } else {

                p.sendMessage(ChatColor.RED + "スポーンポイントが設定されていません。");

            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        Location loc = e.getPlayer().getLocation();

        if (loc.getY() <= -50 ) {

            Location location = plugin.getConfig().getLocation("spawn");

            if (location != null) {

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        p.setFallDistance(0);
                        p.teleport(location);
                        this.cancel();

                    }
                }.runTaskTimer(plugin, 1, 20L);

            } else {
                p.sendMessage(ChatColor.RED + "スポーンポイントが設定されていません。");
            }
        }
    }

}
