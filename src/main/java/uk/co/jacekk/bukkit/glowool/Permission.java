package uk.co.jacekk.bukkit.glowool;

import org.bukkit.permissions.PermissionDefault;

import uk.co.jacekk.bukkit.baseplugin.permissions.PluginPermission;

public class Permission {
	
	public static final PluginPermission ADMIN_COUNT	= new PluginPermission("glowool.admin.count", PermissionDefault.OP, "Allows the player to show the number of lit blocks");
	public static final PluginPermission ADMIN_RESET	= new PluginPermission("glowool.admin.reset", PermissionDefault.OP, "Allows the player to reset all lit blocks");
	public static final PluginPermission ADMIN_RELIGHT	= new PluginPermission("glowool.admin.relight", PermissionDefault.OP, "Allows the player to for all blocks to relight");
	
}
