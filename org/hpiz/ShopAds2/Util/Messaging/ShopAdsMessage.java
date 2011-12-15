/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging;

import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.ShopAds2;
import org.hpiz.ShopAds2.Util.Mathematical;

/**
 *
 * @author Chris
 */
public class ShopAdsMessage extends ShopAds2 {

    public final static ConsoleMessage console = new ConsoleMessage();
    public final static CommandUsageMessage commandUsage = new CommandUsageMessage();
    public final static CommandMessage command = new CommandMessage();
    public final static AdvertisementMessage advertise = new AdvertisementMessage();
    public final static ErrorMessage error = new ErrorMessage();
    protected Mathematical math;

 
    public void tpTimeout(Player player) {
        player.sendMessage(prefix + "You have been returned to your previous location.");
    }

    public void chargePlayer(Player player, double amount) {
        player.sendMessage(prefix + "You were charged " + economy.format(amount));
    }
}
