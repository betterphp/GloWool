package uk.co.jacekk.bukkit.glowool;

import java.util.Arrays;

import uk.co.jacekk.bukkit.baseplugin.config.PluginConfigKey;

public class Config {
	
	public static final PluginConfigKey RELIGHT_FREQUENCY	= new PluginConfigKey("relight-freq", 10);
	public static final PluginConfigKey WORLDS				= new PluginConfigKey("worlds", Arrays.asList(new String[0]));
	
}
