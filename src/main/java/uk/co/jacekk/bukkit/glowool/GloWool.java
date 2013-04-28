package uk.co.jacekk.bukkit.glowool;

import java.io.File;

import uk.co.jacekk.bukkit.baseplugin.BasePlugin;
import uk.co.jacekk.bukkit.baseplugin.config.PluginConfig;
import uk.co.jacekk.bukkit.glowool.storage.BlockStore;

public class GloWool extends BasePlugin {
	
	protected BlockStore blockStore;
	protected LightingHelper lighting;
	
	@Override
	public void onEnable(){
		super.onEnable(true);
		
		this.config = new PluginConfig(new File(this.baseDirPath + File.separator + "config.yml"), Config.class, this.log);
		
		this.blockStore = new BlockStore(this);
		this.lighting = new LightingHelper(this);
		
		this.blockStore.loadBlocksFromFile();
		
		this.commandManager.registerCommandExecutor(new CommandExecutor(this));
		this.pluginManager.registerEvents(new BlockListener(this), this);
		this.scheduler.scheduleSyncRepeatingTask(this, new LightingCheckTask(this), 0, this.config.getInt(Config.RELIGHT_FREQUENCY) * 20);
		
		this.log.info("Enabled, " + this.blockStore.getTotalBlocks() + " blocks loaded from disk.");
	}
	
	@Override
	public void onDisable(){
		this.log.info("Writing " + this.blockStore.getTotalBlocks() + " blocks to disk.");
		
		this.blockStore.writeBlocksToFile();
		
		this.log.info("Done.");
	}
	
}
