package me.carthax08.offlineannouncements.commands;

import me.carthax08.offlineannouncements.OfflineAnnouncements;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PersonalMessageCommand implements CommandExecutor {
    OfflineAnnouncements plugin;

    public PersonalMessageCommand(OfflineAnnouncements plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 1 && player.hasPermission("offline.sendpm")){
                Player player1 = Bukkit.getPlayer(args[0]);
                if (player1 == null){
                    player.sendMessage("The player you've specified does not exist.");
                }
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    message.append(args[i]).append(" ");
                }
                plugin.getConfig().set("players." + args[0] + ".message", message.toString());
                plugin.getConfig().set("players." + args[0] + ".hasBeenSeen", false);
                player.sendMessage("Message successfully queued.");
            } else if(args.length == 0){
                player.sendMessage("Please specify a player and a message.");
            } else if(args.length == 1){
                if(!args[0].equalsIgnoreCase("help")) {
                    player.sendMessage("Please specify a message.");
                }else{
                    player.sendMessage("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                    player.sendMessage("┃ OfflineAnnouncements Version 1.0 by CarThax08 ┃");
                    player.sendMessage("┃      /personalmessage [player] [message]      ┃");
                    player.sendMessage("┃   Normal command but to a specific player     ┃");
                    player.sendMessage("┗╺━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                }
            } else{
                player.sendMessage("An unknown error has occurred. Please let CarThax08 know on the GitHub. Include what you did to cause this error and if you were able to repeat it.");
            }
        }
        return true;
    }
}
