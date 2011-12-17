/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Shop.Shop;

/**
 *
 * @author Chris
 */
public class AdvertisementMessage extends ShopAdsMessage{
    
    public void advertise(Shop shop) {
       
        if (config.getSendToAll()) {
            if (!config.getAdsOverWorlds()) {
                advertiseNotOverWorldsToAll(shop);
            } else {
                advertiseToAll(shop);
            }
        } else {
            if (!config.getAdsOverWorlds()) {
                advertiseNotOverWorlds(shop);
            } else {
                advertiseShop(shop);
            }

        }

    }

    public void advertiseNotOverWorldsToAll(Shop shop) {
        message.console.debug("advertiseNotOverWorldsToAll shop message");
        for (Player p : onlinePlayers.getOnlinePlayers()) {
            for (World shopWorld : shop.getWorldsToAdvertiseIn()) {
                if (p.getWorld() == shopWorld) {
                    if (config.getAnnounceRadius() > 0) {
                        if (config.getAnnounceRadius() > math.playerDistanceFromShop(p, shop)) {
                            p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                            return;
                        }
                    }
                    p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());

                }
            }
        }
    }

    public void advertiseNotOverWorlds(Shop shop) {
        message.console.debug("advertiseNotOverWorldToAll shop message");
        for (Player p : onlinePlayers.getOnlinePlayers()) {
            if (playerHandler.playerExists(p.getName())) {
                if (playerHandler.getPlayer(p.getName()).getWantsAds()) {
                    return;
                }
            }
            for (World shopWorld : shop.getWorldsToAdvertiseIn()) {
                if (p.getWorld() == shopWorld) {
                    if (config.getAnnounceRadius() > 0) {
                        if (config.getAnnounceRadius() > math.playerDistanceFromShop(p, shop)) {
                            p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                            return;
                        }
                    }
                    p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());

                }
            }
        }
    }

    public void advertiseToAll(Shop shop) {
        message.console.debug("advertiseToAll shop message");
        for (Player p : onlinePlayers.getOnlinePlayers()) {
            for (World shopWorld : shop.getWorldsToAdvertiseIn()) {
                if (p.getWorld() == shopWorld) {
                    if (config.getAnnounceRadius() > 0) {
                        if (config.getAnnounceRadius() > math.playerDistanceFromShop(p, shop)) {
                            p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                            return;
                        }
                    }
                    p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                }

            }
        }
    }

    public void advertiseShop(Shop shop) {
        message.console.debug("advertise shop message");
        for (Player p : onlinePlayers.getOnlinePlayers()) {
            if (playerHandler.playerExists(p.getName())) {
                if (playerHandler.getPlayer(p.getName()).getWantsAds()) {
                    return;
                }
            }

            for (World shopWorld : shop.getWorldsToAdvertiseIn()) {
                if (p.getWorld() == shopWorld) {
                    if (config.getAnnounceRadius() > 0) {
                        if (config.getAnnounceRadius() > math.playerDistanceFromShop(p, shop)) {
                            p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                            return;
                        }
                    }
                    p.sendMessage(config.getLabelColor() + "[" + shop.getShopName() + "] " + shop.getAd());
                }

            }
        }
    }
    
}
