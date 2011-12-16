/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging.Command;

import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Util.Messaging.ShopAdsMessage;

/**
 *
 * @author Chris
 */
public class CommandUsageMessage extends ShopAdsMessage{
    
    
    // Commands
    public void fullCommandMenu(Player player) {
        message.console.debug("Full COmmand List");
        player.sendMessage(prefix + "Commands");
        if (!config.getSendToAll()) {

            if (permissions.hasCreatePermission(player)) {
                createCommand(player);
            }
            if (permissions.hasSetPermission(player)) {
                setCommand(player);
            }
            if (permissions.hasDeleteOwnPermission(player)) {
                deleteCommand(player);
            }
            if (permissions.hasAdminPermission(player) || permissions.hasStatsOtherPermission(player)) {
                statsOtherCommand(player);
            } else {
                if (permissions.hasStatsPermission(player)) {
                    statsCommand(player);
                }
            }


            ratesCommand(player);
            listCommand(player);
            onCommand(player);
            offCommand(player);
            if (permissions.hasAdminPermission(player)) {
                adminDeleteCommand(player);
                reloadCommand(player);
                disableCommand(player);
            }
            shopsCommand(player);

        } else {
            if (permissions.hasCreatePermission(player)) {
                createCommand(player);
            }
            if (permissions.hasSetPermission(player)) {
                setCommand(player);
            }
            if (permissions.hasDeleteOwnPermission(player)) {
                deleteCommand(player);
            }
            if (permissions.hasAdminPermission(player) || permissions.hasStatsOtherPermission(player)) {
                statsOtherCommand(player);
            } else {
                if (permissions.hasStatsPermission(player)) {
                    statsCommand(player);
                }
            }

            ratesCommand(player);
            listCommand(player);
            if (permissions.hasAdminPermission(player)) {
                adminDeleteCommand(player);
                reloadCommand(player);
                disableCommand(player);
            }
            shopsCommand(player);

        }

    }

    public void shopsCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/shops - List shops available to tp");
    }

    public void disableCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad disable - Disables the plugin instantly");
    }

    public void reloadCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad reload - Reload the config and ads");
    }

    public void adminDeleteCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad adel [shopname] - Deletes any shop");
    }

    public void offCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad off - Stop receiving ads");
    }

    public void onCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad on - Start receiving ads");
    }

    public void listCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad list - Lists all the current ads");
    }

    public void ratesCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad rates - Returns the current daily rate");
    }

    public void statsCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad stats - Display information about all your current ads");
    }

    public void statsOtherCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad stats (player) - Display information about someones ads");
    }

    public void deleteCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad del [shopname] - Stop your currently running ad");
    }

    public void setCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad set [shopname] [loc/world/name/ad] - Modifies one of your shops");
    }

    public void createCommand(Player player) {
        player.sendMessage(config.getMessageColor() + "/ad c [shopname] [number of hrs] [message]");
    }

    
    public void configCommand(Player player) {
        message.console.debug("configCommand Message");
        player.sendMessage(prefix + "config options:");
        player.sendMessage("/ad config [key] [value]");
        player.sendMessage("announceInterval: " + config.getAnnounceInterval());
        player.sendMessage("sendToAll: " + config.getSendToAll());
        player.sendMessage("enableTp: " + config.getEnableTp());
        player.sendMessage("randomOrder: " + config.getRandomOrder());
        player.sendMessage("tpCost: " + config.getTpCost());
        player.sendMessage("transWorldAddition: " + config.getTransWorldAddition());
        player.sendMessage("adsOverWorlds: " + config.getAdsOverWorlds());
        player.sendMessage("tpTimeout: " + config.getTpTimeout());
        player.sendMessage("maxAdRunTime: " + config.getMaxAdRunTime());
        player.sendMessage("shopsPerPlayer: " + config.getShopsPerPlayer());
        player.sendMessage("adCost: " + config.getAdCost());
        player.sendMessage("tpCostDestination: " + config.getTpCostDestination());
        player.sendMessage("announceRadius: " + config.getAnnounceRadius());
    }
    
    
    public void announceIntervalUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config announceInterval [number in seconds]");
    }

    public void sendToAllUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config sendToAll [true/false]");
    }

    public void enableTpUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config enableTp [true/false]");
    }

    public void randomOrderUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config randomOrder [true/false]");
    }

    public void tpCostUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config tpCost [number]");
    }

    public void transWorldAdditionAllUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config transWorldAddition [number]");
    }

    public void maxAdRunTimeUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config maxAdRunTime [number in hours]");
    }

    public void shopsPerPlayerUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config shopsPerPlayer [number]");
    }

    public void adCostUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config adCost [number]");
    }

    public void tpCostDestinationUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config tpCostDestination [shop/server]");
    }

    public void announceRadiusUsage(Player player) {
        incorrectUsage(player);
        player.sendMessage("/ad config announceRadius [number of blocks]");
    }
    
    public void incorrectUsage(Player player) {
        player.sendMessage(prefix + "Command Usage:");
    }

    
}
