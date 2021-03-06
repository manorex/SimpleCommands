package com.trulis.SimpleCommands;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleCommands extends JavaPlugin{

	public final Logger logger = Logger.getLogger("Minecraft");
	public static SimpleCommands plugin;

	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " Has Been Disabled!");
	}

	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " Has Been Enabled!");
		
		
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
	}


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		PluginDescriptionFile pdfFile = this.getDescription();
		if(commandLabel.equalsIgnoreCase("sc")){
			if(args.length == 0){
				/*sender.sendMessage("Simple Commands Plugin v1.2.1 by trulis");*/
				sender.sendMessage(pdfFile.getName() + " Version " + pdfFile.getVersion() + " by " + pdfFile.getAuthors());
				sender.sendMessage(ChatColor.AQUA + "/sc help - shows you the commands of Simple Commands");
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					/*sender.sendMessage("Simple Commands Plugin v1.2.1 by trulis");*/
					sender.sendMessage(pdfFile.getName() + " Version " + pdfFile.getVersion() + " by " + pdfFile.getAuthors());
					sender.sendMessage(ChatColor.AQUA + "/sc help - shows you the commands of Simple Commands");
					sender.sendMessage(ChatColor.AQUA + "/sc heal - fills your hp bar");
					sender.sendMessage(ChatColor.AQUA + "/sc feed - fills your food bar");
					sender.sendMessage(ChatColor.AQUA + "/sc kill - kills you");
					sender.sendMessage(ChatColor.AQUA + "/sctp <player> <target> - teleports a player to another player");
				}else if(args[0].equalsIgnoreCase("heal")){
					if(sender instanceof Player){
						if(sender.isOp() || sender.hasPermission("simplecommand.heal") || sender.hasPermission("simplecommand.*")){
							((Player) sender).setHealth(20.0);
							sender.sendMessage(ChatColor.DARK_RED + "You have healed yourself!");
						}else{
							sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use that command!");
						}
					}else{
						sender.sendMessage("Only players can use this command!");
					}
				}else if(args[0].equalsIgnoreCase("feed")){
					if(sender instanceof Player){
						if(sender.isOp() || sender.hasPermission("simplecommand.feed") || sender.hasPermission("simplecommand.*")){
							((Player) sender).setFoodLevel(20);
							sender.sendMessage(ChatColor.DARK_RED + "You have filled your hunger bar!");
						}else{
							sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use that command!");
						}
					}else{
						sender.sendMessage("Only players can use this command!");
					}
				}else if(args[0].equalsIgnoreCase("kill")){
					if(sender instanceof Player){
						if(sender.isOp() || sender.hasPermission("simplecommand.kill") || sender.hasPermission("simplecommand.*")){
							((Player) sender).setHealth(20.0);
							sender.sendMessage(ChatColor.DARK_RED + "You have suicided!");
						}else{
							sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use that command!");
						}
					}else{
						sender.sendMessage("Only players can use this command!");
					}
				}
			}
		}else if(commandLabel.equalsIgnoreCase("sctp")){
			if(sender instanceof Player){
				if(sender.isOp() || sender.hasPermission("simplecommand.tp") || sender.hasPermission("simplecommand.*")){
					if(args.length == 0){
						sender.sendMessage(ChatColor.DARK_RED + "Usage: /sctp <player> <target>");
					}else if(args.length == 1){
						Player targetPlayer = ((Player) sender).getServer().getPlayer(args[0]);
						Location targetPlayerLocation = targetPlayer.getLocation();
						((Player) sender).teleport(targetPlayerLocation);
					}else if(args.length == 2){
						Player targetPlayer = ((Player) sender).getServer().getPlayer(args[0]);
						Player targetPlayer1 = ((Player) sender).getServer().getPlayer(args[1]);
						Location targetPlayer1Location = targetPlayer1.getLocation();
						targetPlayer.teleport(targetPlayer1Location);
						targetPlayer.sendMessage("You have been teleported to" + ((Player) sender).getDisplayName());
					}
				}else{
					sender.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use that command!");
				}
			}else{
				sender.sendMessage("Only players can use this command!");
			}
		}
		return false;
	}
}