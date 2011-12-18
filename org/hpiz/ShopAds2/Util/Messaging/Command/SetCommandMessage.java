/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging.Command;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.Util.Messaging.ShopAdsMessage;

/**
 *
 * @author Chris
 */
public class SetCommandMessage extends ShopAdsMessage {

    public void shopSettings(Player player, String shop) {
        if (shopHandler.getShop(shop).getShopOwner().equalsIgnoreCase(player.getName())) {
            displayShopSettings(player, shopHandler.getShop(shop));
        } else {
            if (permissions.hasSetOtherPermission(player)) {
                this.displayShopSettings(player, shopHandler.getShop(shop));
            }
        }
    }

    private void displayShopSettings(Player player, Shop shop) {
        player.sendMessage(prefix + shop.getShopName() + "'s settings");
        player.sendMessage(config.getMessageColor() + "Location: " + shop.getLocation().getLocation().toString());
        player.sendMessage(config.getMessageColor() + worldsToAdvertiseInMessage(shop));
        player.sendMessage(config.getMessageColor() + "Ad runs forever: " + String.valueOf(shop.runsForever()));
    }

    private String worldsToAdvertiseInMessage(Shop shop) {
        super.message.console.debug("Building worlds to advertise in");
        String message = "Worlds advertising: ";
        super.message.console.debug("Worlds to advertise in size: " + shop.getWorldsToAdvertiseInAsString().length);
        String[] worlds = shop.getWorldsToAdvertiseInAsString();
        
            message = (message + worlds[0]);
        for (int i = 1; i < worlds.length; i++) {
            
            message = (message + ", " + worlds[i]);
        }
        return message;
    }
}
