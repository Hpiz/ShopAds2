/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Shop.Shop;

/**
 *
 * @author Chris
 */
public class CommandMessage extends ShopAdsMessage {

    public void rates(Player player) {
        player.sendMessage(prefix + "The current rates are " + economy.getEconomy().format(config.getAdCost()) + " per hour.");
    }

    public void shopStats(Player player, Shop shop) {
        DecimalFormat df = new DecimalFormat("#.00");
        Calendar calNow = Calendar.getInstance();
        Date dateNow = calNow.getTime();
        if (shop.getTimeToEnd().compareTo(dateNow) > 0) {
            player.sendMessage(prefix + shop.getShopName() + " has " + df.format((dateNow.getTime() - shop.getTimeToEnd().getTime()) / 3600000) + " hours remaining.");
        }

    }

    public void ownedShopsSelf(Player player) {
        if (playerHandler.playerExists(player.getName())) {
            message.console.debug("Player exists");
            if (playerHandler.getPlayer(player.getName()).getOwnedShops() == 0) {
                player.sendMessage(prefix + "You are not currently advertising.");
                return;
            }
            if (playerHandler.getPlayer(player.getName()).getOwnedShops() == 1) {
                player.sendMessage(prefix + "You currently are advertising one shop.");
                return;
            }
            if (playerHandler.getPlayer(player.getName()).getOwnedShops() > 1) {
                player.sendMessage(prefix + "You currently are advertising " + playerHandler.getPlayer(player.getName()).getOwnedShops() + " shops.");
                return;
            }
        }
        message.console.debug("Player doesn't exist");

    }

    public void ownedShopsOther(Player requester, String player) {
        message.console.debug("ownedShopOther message");

        if (playerHandler.playerExists(player)) {
            if (playerHandler.getPlayer(player).getOwnedShops() == 0) {
                requester.sendMessage(prefix + player + " is not currently advertising.");
            }
            if (playerHandler.getPlayer(player).getOwnedShops() == 1) {
                requester.sendMessage(prefix + player + " currently are advertising one shop.");
            }
            if (playerHandler.getPlayer(player).getOwnedShops() > 1) {
                requester.sendMessage(prefix + player + " currently are advertising " + playerHandler.getPlayer(player).getOwnedShops() + " shops.");
            }
        }
        requester.sendMessage(prefix + player + " does not have any shops or doesn't exist.");

    }

    public void shopCreated(Player player, Shop newShop) {
        player.sendMessage(prefix + "You just created " + newShop.getShopName() + " which will run until " + newShop.getTimeToEnd().toString());

    }

    public void worldAddedToShop(Shop shop, Player player, World world) {
        player.sendMessage(prefix + "You added " + world.getName() + " to " + shop.getShopName());
    }

    public void shopNameChanged(Player player, Shop shop, String string) {
        player.sendMessage(prefix + "You changed the name of " + shop.getShopName() + " to " + string + ".");
    }

    public void listAds(Player player) {
        if (shops == null || shops.length < 1) {
            player.sendMessage(prefix + "There are no current ads.");
            return;
        }
        player.sendMessage(prefix + "These are all the current ads.");
        for (Shop s : shops) {
            if (s.shopAdvertising() && !s.shopExpired()) {
                player.sendMessage(config.getLabelColor() + "[" + s.getShopName() + "] " + config.getMessageColor() + s.getAd());
            }
        }
    }

    public void listShops(Player player) {
        if (shops == null || shops.length < 1) {
            player.sendMessage(prefix + "There are no shops available to teleport to.");
            return;
        }
        player.sendMessage(prefix + "These are all the current shops.");
        for (Shop s : shops) {
            if (s.shopAdvertising() && !s.shopExpired() && s.advertisesIn(player.getWorld().getName())) {
                if (s.getWorld() == player.getWorld()) {
                    player.sendMessage(config.getLabelColor() + s.getShopName());
                } else {
                    player.sendMessage(config.getLabelColor() + s.getShopName() + "*");
                }
            }
        }
    }

    public void disable(Player player) {
        player.sendMessage(prefix + "Plugin disabled.");
    }
    public void reload(Player player) {
        player.sendMessage(prefix + "Config, shops, and players reloaded.");
    }
}
