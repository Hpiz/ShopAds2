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
public class SetCommandMessage extends ShopAdsMessage{
    
    

    public void shopSettings(Player player, String shop) {
        if(shopHandler.getShop(shop).getShopOwner().equalsIgnoreCase(player.getName())){
            displayShopSettings(player,shopHandler.getShop(shop));
        }else{
            if (permissions.hasSetOtherPermission(player)){
                this.displayShopSettings(player, shopHandler.getShop(shop));
            }
        }
    }

    private void displayShopSettings(Player player, Shop shop) {
        player.sendMessage(prefix + shop.getShopName() + "'s settings");
        player.sendMessage("Location: "+ shop.getLocation().getLocation().toString());
        player.sendMessage(worldsToAdvertiseInMessage(shop));
        player.sendMessage("Ad runs forever: " + String.valueOf(shop.runsForever()));
    }
    private String worldsToAdvertiseInMessage(Shop shop){
        String message = "Worlds advertising: ";
        World[] worlds = shop.getWorldsToAdvertiseIn();
        for (int i = 1 ; i < worlds.length ; i++){
            message = (message + ", " + worlds[i].getName());
        }
        return message;
    }
}
