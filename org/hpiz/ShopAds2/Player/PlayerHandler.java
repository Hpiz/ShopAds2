/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import java.util.ArrayList;
import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class PlayerHandler extends ShopAds2 {

    public static ArrayList<ShopAdsPlayer> players = new ArrayList<ShopAdsPlayer>();
    public ArrayList<TeleportedPlayer> teleportedPlayers;

    public void initializeTeleportedPlayers() {
        teleportedPlayers = new ArrayList<TeleportedPlayer>();
    }

    public ArrayList<ShopAdsPlayer> getPlayers() {

        return players;
    }

    public void setPlayers(ArrayList<ShopAdsPlayer> newPlayers) {
        this.players = newPlayers;
    }

    public ShopAdsPlayer getPlayer(String name) {
        for (ShopAdsPlayer test : players) {
            if (test.getName().equalsIgnoreCase(name)) {
                return test;
            }

        }
        return null;
    }

    public boolean playerExists(String name) {
        try {
            ShopAdsPlayer p = this.getPlayer(name);
            if(players.contains(p)){
            return true;
            }else{
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean addPlayer(ShopAdsPlayer player) {
        if (!players.isEmpty()) {
            message.console.debug("players is not empty when adding player");
            if (players.contains(player)) {
                message.console.debug(player.getName() + " was not added to players.");
                return false;
            } else {
                players.add(player);
                message.console.debug(player.getName() + " was added to players.");
                return true;
            }
        } else {
            message.console.debug("players is empty when adding player");
            players.add(player);
            message.console.debug(player.getName() + " was added to players.");
            return true;
        }
    }

    public boolean removeTeleportedPlayer(TeleportedPlayer p) {
        try {
            teleportedPlayers.remove(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean returnTeleportedPlayers() {
        if (teleportedPlayers == null){
            return true;
        }
        if (teleportedPlayers.isEmpty()){
            return true;
        }
        for (TeleportedPlayer p : teleportedPlayers) {
            if (p.isExpired()) {
                if (removeTeleportedPlayer(p)) {
                    p.getPlayer().teleport(p.getOldLoc());
                    message.tpTimeout(p.getPlayer());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (players.isEmpty()) {
            return true;

        }
        return false;
    }

    public void updateOwnedShopsFromFile() {

        for (ShopAdsPlayer p : this.getPlayers()) {
            int count = 0;
            if (shopHandler.shopsEmpty()) {
                p.setOwnedShops(count);
                continue;
            }
            for (Shop s : shopHandler.getShops()) {
                if (s.getShopOwner().equalsIgnoreCase(p.getName())) {
                    count++;
                }
            }
            p.setOwnedShops(count);
        }
    }

    public boolean isNull() {
        if(players==null){
            return true;
        }
        return false;
    }

    public ShopAdsPlayer getPlayer(int i) {
        return players.get(i);
    }
}
