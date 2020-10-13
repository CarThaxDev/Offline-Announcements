package me.carthax08.offlineannouncements.events;

import me.carthax08.offlineannouncements.OfflineAnnouncements;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Objects;

public class onPlayerJoin implements Listener{
    OfflineAnnouncements plugin;

    public onPlayerJoin(OfflineAnnouncements plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        String message = plugin.getConfig().getString("info.message");
        Player player = event.getPlayer();
        if(!plugin.getConfig().getStringList("info.players").contains(player.getName()) && message != null) {
            List<String> playerList = plugin.getConfig().getStringList("info.players");
            player.sendMessage(message);
            playerList.add(player.getName());
            plugin.getConfig().set("info.players", playerList);
            plugin.saveConfig();
            plugin.reloadConfig();
            System.out.println("[OfflineAnnouncements]" + player.getName() + "has seen global message '" + message + "'");
            try {
                if (!Objects.requireNonNull(plugin.getConfig().getString("players." + player.getName() + ".message")).isEmpty() && !plugin.getConfig().getBoolean("players." + player.getName() + ".hasSeenMessage")) {
                    player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("players." + player.getName() + ".message")));
                    plugin.getConfig().set("players." + player.getName() + ".hasBeenSeen", true);
                    System.out.println("[OfflineAnnouncements]" + player.getName() + "has seen personal message '" + plugin.getConfig().getString("players." + player.getName() + ".message") + "'");
                }
            }
        catch(NullPointerException e){
                System.out.println("Player" + player.getName() + "does not have a personal message.");
        }
        }
    }
}
