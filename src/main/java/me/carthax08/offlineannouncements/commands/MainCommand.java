package me.carthax08.offlineannouncements.commands;

import me.carthax08.offlineannouncements.OfflineAnnouncements;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class MainCommand implements CommandExecutor {
    OfflineAnnouncements plugin;
    public MainCommand(OfflineAnnouncements plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender);
            boolean canRun = player.hasPermission("offline.send") || player.isOp();
            if(args.length == 0){
                player.sendMessage("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                player.sendMessage("┃ OfflineAnnouncements Version 1.0 by CarTHax08 ┃");
                player.sendMessage("┃                                               ┃");
                player.sendMessage("┃ /offlineannouncements - Prints this page      ┃");
                player.sendMessage("┃ /offlineannouncements help - Prints this page ┃");
                player.sendMessage("┃ /offlineannouncements send <Message> - sets a ┃");
                player.sendMessage("┃        message to be sent once a player joins ┃");
                player.sendMessage("┗╺━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            } else if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                    player.sendMessage("┃ OfflineAnnouncements Version 1.0 by CarTHax08 ┃");
                    player.sendMessage("┃                                               ┃");
                    player.sendMessage("┃ /offlineannouncements - Prints this page      ┃");
                    player.sendMessage("┃ /offlineannouncements help - Prints this page ┃");
                    player.sendMessage("┃ /offlineannouncements send <Message> - sets a ┃");
                    player.sendMessage("┃        message to be sent once a player joins ┃");
                    player.sendMessage("┗╺━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                }else if(args[0].equalsIgnoreCase("send") && player.hasPermission("offline.send")){
                    player.sendMessage("You need to provide a message!");
                } else{
                    player.sendMessage("Unknown Argument Provided");
                }
            }else {
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                    player.sendMessage("┃ OfflineAnnouncements Version 1.0 by CarTHax08 ┃");
                    player.sendMessage("┃                                               ┃");
                    player.sendMessage("┃ /offlineannouncements - Prints this page      ┃");
                    player.sendMessage("┃ /offlineannouncements help - Prints this page ┃");
                    player.sendMessage("┃ /offlineannouncements send <Message> - sets a ┃");
                    player.sendMessage("┃        message to be sent once a player joins ┃");
                    player.sendMessage("┗╺━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                } else if(args[0].equalsIgnoreCase("send") && canRun){
                    String message = "";
                    for (int i = 1; i < args.length; i++) {
                        message = message + " " + args[i];
                    }
                    player.sendMessage("Message successfully queued.");
                    plugin.getConfig().set("info.message", message);
                    plugin.getConfig().set("info.players", Collections.emptyList());
                    plugin.saveConfig();
                    plugin.reloadConfig();
                }else{
                    player.sendMessage("Unknown Argument Provided.");
                }
            }
        }else{
            System.out.println(ChatColor.RED + "[OfflineAnnoucenments] You must be a player to run this command!");
        }
        return true;
    }
}
