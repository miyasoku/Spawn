package com.github.miyasoku.spawn;

import com.github.miyasoku.spawn.commands.CommandSetSpawn;
import com.github.miyasoku.spawn.commands.CommandSpawn;
import com.github.miyasoku.spawn.listeners.Listeners;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Spawn extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Loaded Successfully!");

        config = this.getConfig();
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("spawn").setExecutor(new CommandSpawn(this));
        getCommand("setspawn").setExecutor(new CommandSetSpawn(this));

        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Unloaded Successfully");

        super.onDisable();
    }
}
