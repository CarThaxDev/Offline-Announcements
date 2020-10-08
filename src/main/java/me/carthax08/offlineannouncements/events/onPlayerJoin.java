package me.carthax08.offlineannouncements.events;

import com.sun.tools.jdi.Packet;
import me.carthax08.offlineannouncements.OfflineAnnouncements;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class onPlayerJoin implements Listener {
    OfflineAnnouncements plugin;

    public onPlayerJoin(OfflineAnnouncements plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        String message = plugin.getConfig().getString("info.message");
        Player player = event.getPlayer();
        if(!plugin.getConfig().getStringList("info.players").contains(player.getName()) && message != null){
            List<String> playerList = plugin.getConfig().getStringList("info.players");
            player.sendMessage(message);
            playerList.add(player.getName());
            plugin.getConfig().set("info.players", playerList);
            plugin.saveConfig();
            plugin.reloadConfig();
        }
    }
}
