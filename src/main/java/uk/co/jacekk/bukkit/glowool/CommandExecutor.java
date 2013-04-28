package uk.co.jacekk.bukkit.glowool;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import uk.co.jacekk.bukkit.baseplugin.command.BaseCommandExecutor;
import uk.co.jacekk.bukkit.baseplugin.command.CommandHandler;
import uk.co.jacekk.bukkit.baseplugin.command.CommandTabCompletion;
import uk.co.jacekk.bukkit.baseplugin.command.SubCommandHandler;

public class CommandExecutor extends BaseCommandExecutor<GloWool> {
	
	public CommandExecutor(GloWool plugin){
		super(plugin);
	}
	
	@CommandHandler(names = {"glowool"}, description = "Manage the lit blocks", usage = "[option]")
	@CommandTabCompletion({"count|reset|show"})
	public void glowool(CommandSender sender, String label, String[] args){
		sender.sendMessage(ChatColor.RED + "Usage: /" + label + " [option]");
		sender.sendMessage(ChatColor.RED + "Options:");
		
		sender.sendMessage(ChatColor.RED + "  count - Shows the number of lit blocks");
		sender.sendMessage(ChatColor.RED + "  reset - Unlight all blocks");
		sender.sendMessage(ChatColor.RED + "  relight - Resets all lit blocks");
	}
	
	@SubCommandHandler(parent = "glowool", name = "count")
	public void glowoolCount(CommandSender sender, String label, String[] args){
		if (!Permission.ADMIN_COUNT.has(sender)){
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command !");
			return;
		}
		
		sender.sendMessage(ChatColor.GREEN + "GloWool is currently lighting " + plugin.blockStore.getTotalBlocks() + " blocks.");
	}
	
	@SubCommandHandler(parent = "glowool", name = "reset")
	public void glowoolReset(CommandSender sender, String label, String[] args){
		if (!Permission.ADMIN_RESET.has(sender)){
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command !");
			return;
		}
		
		for (Block block : plugin.blockStore.getBlocks()){
			plugin.lighting.unLightBlock(block);
		}
		
		plugin.blockStore.removeAll();
		
		sender.sendMessage(ChatColor.GREEN + "All blocks have been unlit.");
	}
	
	@SubCommandHandler(parent = "glowool", name = "relight")
	public void glowoolRelight(CommandSender sender, String label, String[] args){
		if (!Permission.ADMIN_RELIGHT.has(sender)){
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command !");
			return;
		}
		
		for (Block block : plugin.blockStore.getBlocks()){
			plugin.lighting.reLightBlock(block);
		}
		
		sender.sendMessage(ChatColor.GREEN + "All blocks have been re-lit.");
	}
	
}
