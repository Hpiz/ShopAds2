/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsEconomy extends ShopAds2 {

    public static Economy economy = null;



    public boolean playerPayPlayer(Player sender, Player reciever, double amount) {
        if (economy.has(sender.getName(), amount)) {
            economy.withdrawPlayer(sender.getName(), amount);
            economy.depositPlayer(reciever.getName(), amount);
            return true;
        }
        return false;
    }

    public boolean payPlayer(Player reciever, double amount) {
        economy.depositPlayer(reciever.getName(), amount);
        return true;
    }

    public boolean chargePlayer(Player player, double amount) {
        if (economy.has(player.getName(), amount)) {
            economy.withdrawPlayer(player.getName(), amount);
            message.chargePlayer(player, amount);
            return true;
        }
        return false;
    }

    public boolean hasEnough(Player player, double amount) {
        message.console.debug("checking if " + player.getName() + " has " + amount);
        message.console.debug(economy.getName());
        if(economy.has(player.getName(), amount)){
            return true;
        }
        return false;
    }
    public Economy getEconomy(){
        return economy;
    }
    
    public String format (double d){
        return economy.format(d);
    }
}
