package me.carthax08.offlineannouncements;

import me.carthax08.offlineannouncements.commands.MainCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import me.carthax08.offlineannouncements.events.onPlayerJoin;


public final class OfflineAnnouncements extends JavaPlugin {

    String version = "BETA 2.0";
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.RESET + "[OfflineAnnouncements] Loading Plugin, Please wait...");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OfflineAnnouncements] VERSION" + version + " IS PRESENT");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OfflineAnnouncements] The plugin is loaded, enjoy!");
        getCommand("offlineannouncements").setExecutor(new MainCommand(this));
        getServer().getPluginManager().registerEvents(new onPlayerJoin(this), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[OfflineAnnouncements] The plugin has shut down.");
    }
}
