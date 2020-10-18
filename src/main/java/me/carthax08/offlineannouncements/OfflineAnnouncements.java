package me.carthax08.offlineannouncements;

import me.carthax08.offlineannouncements.commands.MainCommand;
import me.carthax08.offlineannouncements.commands.PersonalMessageCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import me.carthax08.offlineannouncements.events.onPlayerJoin;


public final class OfflineAnnouncements extends JavaPlugin {

    String version = "RELEASE 1.0-BETA 3";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.RESET + "[OfflineAnnouncements] Loading Plugin, Please wait...");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OfflineAnnouncements] Plugin version " + version + " is present.");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OfflineAnnouncements] Please check the GitHub for a newer version.");
        getCommand("offlineannouncements").setExecutor(new MainCommand(this));
        getCommand("personalmessage").setExecutor(new PersonalMessageCommand(this));
        getServer().getPluginManager().registerEvents(new onPlayerJoin(this), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OfflineAnnouncements] The plugin is loaded, enjoy!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[OfflineAnnouncements] The plugin has shut down.");
    }
}
