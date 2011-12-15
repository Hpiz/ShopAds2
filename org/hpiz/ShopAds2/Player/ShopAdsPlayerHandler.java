/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import java.util.Calendar;
import java.util.Date;
import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsPlayerHandler extends ShopAds2 {

    public static ShopAdsPlayer[] shopAdsPlayers;
    public TeleportedPlayer[] teleportedPlayers;
    
    public ShopAdsPlayer[] getPlayers() {
        
        return shopAdsPlayers;
    }
    
    public void setPlayers(ShopAdsPlayer[] players) {
        this.shopAdsPlayers = players;
    }

    public ShopAdsPlayer getPlayer(String name) {
        for (ShopAdsPlayer output : shopAdsPlayers) {
            if (output.getName().equalsIgnoreCase(name)) {
                return output;
            }
        }
        message.console.debug("Input name was not found");
        return null;
    }

    public boolean playerExists(String name) {
        if (shopAdsPlayers == null) {
            return false;
        }
        for (ShopAdsPlayer player : shopAdsPlayers) {
            if (name.equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addPlayer(ShopAdsPlayer player) {
        
        if (shopAdsPlayers == null) {
            
            shopAdsPlayers = new ShopAdsPlayer[1];
            shopAdsPlayers[0] = player;
            message.console.debug(shopAdsPlayers[0].getName());
            return true;
        }
        
        ShopAdsPlayer[] holder = new ShopAdsPlayer[shopAdsPlayers.length];
        holder = shopAdsPlayers;
        
        
        message.console.debug("setting new player to " + (holder.length + 1));
        shopAdsPlayers = new ShopAdsPlayer[holder.length + 1];
        message.console.debug("players length: " + shopAdsPlayers.length);
        for (int x = 0; x < shopAdsPlayers.length - 1; x++) {
            message.console.debug(String.valueOf(x));
            shopAdsPlayers[x] = holder[x];
        }
        shopAdsPlayers[holder.length] = player;
        return true;
    }
    
    public boolean removeTeleportedPlayer(TeleportedPlayer p) {
        if (this.teleportedPlayers == null || this.teleportedPlayers.length < 1) {
            return true;
        }
        int index = -1;
        for (int i = 0; i < teleportedPlayers.length; i++) {
            if (this.teleportedPlayers[i].equals(p)) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        TeleportedPlayer[] temp = new TeleportedPlayer[teleportedPlayers.length];
        for (int i = 0; i < index; i++) {
            temp[i] = teleportedPlayers[i];
        }
        for (int i = index + 1; i < teleportedPlayers.length; i++) {
            temp[i - 1] = teleportedPlayers[i];
        }
        teleportedPlayers = new TeleportedPlayer[temp.length];
        teleportedPlayers = temp;
        return true;
    }

    public boolean returnTeleportedPlayers() {
        if (this.teleportedPlayers == null || this.teleportedPlayers.length < 1) {
            return true;
        }
        
        for (TeleportedPlayer p : this.teleportedPlayers) {
            if (p.isExpired()) {
                this.removeTeleportedPlayer(p);
            }
        }
        return true;
    }
    
    public void updateOwnedShopsFromFile() {
        
        for (ShopAdsPlayer p : this.getPlayers()) {            
            int count = 0;
            if (shops==null||shops.length<1){
                p.setOwnedShops(count);
                continue;
            }
            for (Shop s : shops) {
                if (s.getShopOwner().equalsIgnoreCase(p.getName())) {
                    count++;
                }
            }
            p.setOwnedShops(count);
        }
    }
}
