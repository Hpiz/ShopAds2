/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Shop;

import java.util.ArrayList;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Player.ShopAdsPlayer;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopHandler extends ShopAds2 {

    private ArrayList<Shop> shops = new ArrayList<Shop>();

    public ArrayList<Shop> getPlayersShops(Player player) {
        ArrayList<Shop> playersShops = new ArrayList<Shop>();
        for (Shop s : shops) {
            if (s.getShopOwner().equalsIgnoreCase(player.getName())) {
                playersShops.add(s);
            }
        }
        return playersShops;
    }

    public ArrayList<Shop> getPlayersShops(String player) {
        ArrayList<Shop> playersShops = new ArrayList<Shop>();
        for (Shop s : shops) {
            if (s.getShopOwner().equalsIgnoreCase(player)) {
                playersShops.add(s);
            }
        }
        return playersShops;
    }

    public void addShop(Shop shop) {
        if (this.shopExists(shop)) {
            return;
        }
        shops.add(shop);
        message.console.debug(String.valueOf(playerHandler.playerExists("Debug")));
        
        if (!playerHandler.isEmpty()) {
            if (playerHandler.playerExists(shop.getShopOwner())) {
                playerHandler.getPlayer(shop.getShopOwner()).addOwnedShop();
            }
        }
    }

    public void setShop(int index, Shop shop) {
        shops.set(index, shop);
    }

    public void removeShop(Shop shop) {
        shops.remove(shop);
        playerHandler.getPlayer(shop.getShopOwner()).addOwnedShop();

    }

    public Shop getShop(String name) {
        Shop[] temp = new Shop[shops.size()];
        shops.toArray(temp);
        if (temp != null && temp.length > 0) {
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].getShopName().equalsIgnoreCase(name)) {
                    return temp[i];
                }
            }
        }
        return null;

    }

    public boolean shopsEmpty() {
        return shops.isEmpty();
    }

    public boolean shopExists(Shop shop) {
        if (shops.contains(shop)) {
            return true;
        }
        return false;
    }

    public boolean shopExists(String shop) {
        Shop[] temp = new Shop[shops.size()];
        shops.toArray(temp);
        if (temp != null && temp.length > 0) {
            for (Shop test : temp) {
                if (test.getShopName().equalsIgnoreCase(shop)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getShopIndex(Shop shop) {
        return shops.indexOf(shop);
    }

    public void addWorldToShop(Shop shop, String string) {
        try {
            shop.addWorldToAdvertiseIn(server.getWorld(string));
        } catch (Exception e) {
            return;
        }
    }

    public boolean ownsShop(Shop shop, Player player) {
        if (player.getName() == shop.getShopOwner()) {
            return true;
        }
        return false;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public int getSize() {
        return shops.size();
    }

    public Shop getShop(int index) {
        return shops.get(index);
    }
}
