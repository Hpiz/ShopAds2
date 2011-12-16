/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Shop;

import org.bukkit.World;
import org.hpiz.ShopAds2.ShopAds2;
import org.hpiz.ShopAds2.Util.ServerInterface;

/**
 *
 * @author Chris
 */
public class ShopHandler extends ShopAds2 {

    public void addShop(Shop shop) {
        message.console.debug("ShopHandler addshop");
        if (shops != null && shops.length > 0) {
            message.console.debug("some shops already exist");
            Shop[] transfer = new Shop[shops.length + 1];
            for (int i = 0; i < shops.length; i++) {
                transfer[i] = shops[i];
            }
            transfer[shops.length] = shop;
            shops = new Shop[transfer.length];
            shops = transfer;
            playerHandler.getPlayer(shop.getShopOwner()).addOwnedShop();
            return;
        }
        message.console.debug ("no shops already existed");
        shops = new Shop[1];
        shops[0] = shop;
        playerHandler.getPlayer(shop.getShopOwner()).addOwnedShop();
        message.console.debug("Shop -" + shops[0].getShopName() + " was created");
    }

    public void removeShop(Shop shop) {
        if (shops != null && shops.length > 1) {
            if (shopExists(shop)) {
                Shop[] transfer = new Shop[shops.length - 1];
                int index = getShopIndex(shop);
                for (int i = 0; i < index; i++) {
                    transfer[i] = shops[i];
                }
                for (int i = index + 1; i < shops.length; i++) {
                    transfer[i - 1] = shops[i];
                }
                shops = new Shop[transfer.length];
                shops = transfer;
            }
        }
        shops = null;

    }

    public Shop getShopByName(String name) {
        if (shops != null && shops.length > 0) {
            for (int i = 0; i < shops.length; i++) {
                if (shops[i].getShopName().equalsIgnoreCase(name)) {
                    return shops[i];
                }
            }
        }
        return null;

    }

    public boolean shopExists(Shop shop) {
        if (shops != null && shops.length > 0) {
            for (Shop test : shops) {
                if (shop == test) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean shopExists(String shop){
        for(Shop test :shops){
            if(test.getShopName().equalsIgnoreCase(shop)){
                return true;
            }
        }
        return false;
    }

    public int getShopIndex(Shop shop) {
        if (shops != null && shops.length > 0) {
            for (int i = 0; i < shops.length; i++) {
                if (shop == shops[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void addWorldToShop(Shop shop, String string) {
        if(!serverInterface.worldExists(string)){
            return;
        }
        shop.addWorldToAdvertiseIn(serverInterface.getWorld(string));
    }
    
}
