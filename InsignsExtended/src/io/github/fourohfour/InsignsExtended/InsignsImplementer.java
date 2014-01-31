package io.github.fourohfour.InsignsExtended;



import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.blablubbabc.insigns.Changer;
import de.blablubbabc.insigns.InSigns;


public class InsignsImplementer {
    
    public InsignsImplementer(Plugin insignsPlugin) {
        
        InSigns insigns = (InSigns) insignsPlugin;
        insigns.addChanger(new Changer("[ISOP]", "insigns.create.isop") {

			@Override
			public String getValue(Player arg0, Location arg1) {
				if (arg0.isOp()){
					return "You are OP!";
				} else return "You are not OP";
			}

        });
        
        insigns.addChanger(new Changer("[TIME]", "insigns.create.time") {

			@Override
			public String getValue(Player arg0, Location arg1) {
				Double time = (double)(Bukkit.getWorld(arg0.getWorld().getName()).getTime()) / 1000;
				return ((Integer)(time.intValue() + 6)).toString() + "h " + ((Integer)((Double)(60*(time % 1))).intValue()).toString() + "m";
				
			}
        	
        });
    }
}