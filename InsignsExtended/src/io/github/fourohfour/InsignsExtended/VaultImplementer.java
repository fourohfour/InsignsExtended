package io.github.fourohfour.InsignsExtended;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import de.blablubbabc.insigns.Changer;
import de.blablubbabc.insigns.InSigns;

public class VaultImplementer {
    public static Chat chat = null;
	public static Permission permission = null;
    public static Economy economy = null;
    
    public static InsignsExtended plugin;
    
    public VaultImplementer(InsignsExtended owning, Plugin insignsPlugin){
    	plugin = owning;
    	this.setupEconomy();
    	InSigns insigns = (InSigns) insignsPlugin;
        insigns.addChanger(new Changer("[BALANCERAW]", "insigns.create.vault.balance.raw") {
			@Override
			public String getValue(Player arg0, Location arg1) {
					if (economy == null){
						return "No Economy Found";
					} else return String.valueOf(economy.bankBalance(arg0.getName()).amount);
			}

        });
        
        insigns.addChanger(new Changer("[BALANCE]", "insigns.create.vault.balance.normal") {
			@Override
			public String getValue(Player arg0, Location arg1) {
					if (economy == null){
						return "No Economy Found";
					} else if (economy.bankBalance(arg0.getName()).amount == 0){
						return String.valueOf(economy.bankBalance(arg0.getName()).amount) + " " + economy.currencyNameSingular();
					} else return String.valueOf(economy.bankBalance(arg0.getName()).amount) + " " + economy.currencyNamePlural();
			}

        });
    }

    public boolean setupPermissions(){
        RegisteredServiceProvider<Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    public boolean setupChat(){
        RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    public boolean setupEconomy(){
        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }

}
