package io.github.fourohfour.InsignsExtended;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class InsignsExtended extends JavaPlugin{
	@Override
	public void onEnable(){
		Plugin insignsPlugin = getServer().getPluginManager().getPlugin("InSigns");
		Plugin vault = getServer().getPluginManager().getPlugin("Vault");
	        if((insignsPlugin != null) && insignsPlugin.isEnabled()) {
	            new InsignsImplementer(insignsPlugin);
	            if (vault != null) {
	            	new VaultImplementer(this, insignsPlugin);
		            Bukkit.getLogger().info("Hooked into Vault");
			    }
	            Bukkit.getLogger().info("Found InSigns Plugin");
	        } else {
	        	Bukkit.getLogger().info("Could not find InSigns Plugin");
	        }
	}

	@Override
	public void onDisable(){
		
	}
	
}
