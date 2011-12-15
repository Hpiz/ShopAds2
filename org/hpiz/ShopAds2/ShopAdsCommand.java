/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2;

import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.Player.ShopAdsPlayer;
import java.util.Calendar;
import java.util.Date;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.Shop.ShopHandler;

/**
 *
 * @author Chris
 */
public class ShopAdsCommand extends ShopAds2 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        message.console.debug("Command caught");
        if (sender instanceof Player) {
            if (playerCommand((Player) sender, cmd, commandLabel, args)) {
                return true;
            }
        } else {
            if (consoleCommand(cmd, commandLabel, args)) {
                return true;
            }
        }
        return false;
    }

    private boolean consoleCommand(Command cmd, String commandLabel, String[] args) {

        return false;
    }

    private boolean playerCommand(Player player, Command cmd, String commandLabel, String[] args) {
        if (commandLabel.equalsIgnoreCase("ad") || commandLabel.equalsIgnoreCase("ads")) {

            if (args.length > 0) {

                if (args[0].equalsIgnoreCase("on")) {
                    on(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("off")) {
                    off(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("stat") || args[0].equalsIgnoreCase("stats")) {
                    if (!permissions.hasStatsPermission(player)) {
                        message.error.noPermission(player, "STATS");
                        return true;
                    }
                    stats(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("delete")) {
                    if (!permissions.hasDeleteOwnPermission(player)) {
                        message.error.noPermission(player, "DELETE");
                        return true;
                    }
                    delete(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("create")) {
                    if (!permissions.hasCreatePermission(player)) {
                        message.error.noPermission(player, "CREATE");
                        return true;
                    }
                    create(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("rate") || args[0].equalsIgnoreCase("rates")) {
                    rates(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("list")) {
                    list(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("rel") || args[0].equalsIgnoreCase("reload")) {
                    if (!permissions.hasAdminPermission(player)) {
                        message.error.noPermission(player, "RELOAD");
                        return true;
                    }
                    reload(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("config")) {
                    if (!permissions.hasAdminPermission(player)) {
                        message.error.noPermission(player, "CONFIG");
                        return true;
                    }
                    config(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("disable")) {
                    if (!permissions.hasAdminPermission(player)) {
                        message.error.noPermission(player, "DISABLE");
                        return true;
                    }
                    disable(player, args);
                    return true;
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (!permissions.hasSetPermission(player)) {
                        message.error.noPermission(player, "SET");
                        return true;
                    }
                    set(player, args);
                    return true;
                }
                return true;




            }
            message.commandUsage.fullCommandMenu(player);
        }
        return false;
    }

    private void on(Player player, String[] args) {
        message.console.debug("on command");
        if (args.length == 1) {
            if (!playerHandler.playerExists(player.getName())) {
                playerHandler.addPlayer(new ShopAdsPlayer(player.getName(), true, 0));
                return;
            }
            playerHandler.getPlayer(player.getName()).setWantsAds(true);
        }
        message.commandUsage.onCommand(player);
    }

    private void off(Player player, String[] args) {
        message.console.debug("off command");
        if (args.length == 1) {
            if (!playerHandler.playerExists(player.getName())) {
                playerHandler.addPlayer(new ShopAdsPlayer(player.getName(), false, 0));
                return;
            }
            playerHandler.getPlayer(player.getName()).setWantsAds(false);
        }
        message.commandUsage.offCommand(player);
    }

    private void stats(Player player, String[] args) {
        if (args.length == 2) {
            message.command.ownedShopsOther(player, args[1]);
            return;
        }
        if (args.length == 1) {
            message.console.debug("stats command");
            message.command.ownedShopsSelf(player);
            return;
        }
        message.commandUsage.statsCommand(player);
    }

    private void delete(Player player, String[] args) {

        message.console.debug("delete command");
        if (args.length == 2) {
            String shopToDelete = args[1];
            boolean shopNotYours = false;
            boolean shopNotFound = false;
            int index = -1;
            for (int x = 0; x < shops.length; x++) {
                if (shops[x].getShopName().equalsIgnoreCase(shopToDelete)) {
                    if (shops[x].getShopOwner().equalsIgnoreCase(player.getName())) {
                        index = x;
                        shopNotYours = false;
                        shopNotFound = false;
                        break;
                    } else {
                        shopNotYours = true;
                    }
                } else {
                    shopNotFound = true;

                }
            }
            if (index >= 0) {
                Shop[] mover = new Shop[shops.length - 1];
                for (int x = 0; x < index; x++) {
                    mover[x] = shops[x];
                }
                for (int x = index + 1; x < shops.length; x++) {
                    mover[x - 1] = shops[x];
                }
                shops = new Shop[mover.length];
                shops = mover;
                return;
            }
        }
        message.commandUsage.deleteCommand(player);

    }

    private void create(Player player, String[] args) {
        message.console.debug("create command");
        message.console.debug("args.length = " + args.length);
        if (args.length >= 3) {
            message.console.debug("args is the correct size");
            message.console.debug("Character at args[2] = " + args[2].charAt(0));
            message.console.debug("Character at args[3] = " + args[3].charAt(0));
            if (Character.isLetter(args[2].charAt(0))) {
                createShopUnlimited(player, args);
            } else if (Character.isDigit(args[2].charAt(0))) {
                createShopWithTime(player, args);
                return;
            }

        }
        message.commandUsage.createCommand(player);
    }

    private void rates(Player player, String[] args) {
        message.console.debug("rates command");
        message.command.rates(player);
        return;

    }

    private void list(Player player, String[] args) {
        message.console.debug("list command");
        message.command.listAds(player);

        return;
    }

    private void config(Player player, String[] args) {
        message.console.debug("config command");
        if (args.length == 1) {
            message.commandUsage.configCommand(player);
            return;
        }
        if (args.length == 2) {
            noValue(player, args);
            return;
        }
        if (args.length == 3) {
            changeConfig(player, args);
            return;
        }
        return;
    }

    private void reload(Player player, String[] args) {
        message.console.debug("reload command");

    }

    private void disable(Player player, String[] args) {
        message.console.debug("disable command");
        super.onDisable();
    }

    private void set(Player player, String[] args) {
        message.console.debug("set command");
        // set
        if (args.length == 1) {
            message.error.noShopEntered(player);
            message.commandUsage.setCommand(player);
            return;
        }
        //shop
        if (args.length == 2) {
            message.commandUsage.setCommand(player);
            return;
        }
        //location
        if (args.length == 3) {
            setGeneral(player, args);
            return;
        }
        //myshop
        if (args.length >= 4) {
            setSpecific(player, args);
            return;
        }

    }

    private void createShopWithTime(Player player, String[] args) {
        message.console.debug("createShopWithTime started");
        if (Integer.parseInt(args[2]) <= config.getMaxAdRunTime()) {
            if (!economy.hasEnough(player, (Double.parseDouble(args[2]) * config.getAdCost()))) {
                message.error.insufficientFunds(player, (Double.parseDouble(args[2]) * config.getAdCost()));
                return;
            }
            String[] worlds = new String[1];
            worlds[0] = player.getWorld().getName();
            Calendar calNow = Calendar.getInstance();
            Date dateNow = calNow.getTime();
            Date timeToEnd = calNow.getTime();
            timeToEnd.setTime(dateNow.getTime() + Long.parseLong(args[2]) * 3600000);
            StringBuilder ad = new StringBuilder (args[3]);
            if (args.length > 4) {
                for (int i = 3; i < args.length; i++) {
                    ad.append(" ");
                    ad.append(args[i]);
                }
            }
            Shop newShop = new Shop(args[1], player.getLocation(), player.getName(), timeToEnd, false, player.getWorld(), ad.toString(), worlds, true);
            shopHandler.addShop(newShop);
            economy.chargePlayer(player, (Double.parseDouble(args[2]) * config.getAdCost()));
            message.command.shopCreated(player, newShop);
        } else {
            message.error.overMaxRunTime(player, Integer.parseInt(args[2]));
        }
    }

    private void createShopUnlimited(Player player, String[] args) {
    }

    private void noValue(Player player, String[] args) {
        message.console.debug("noValue");
        String key = args[1];
        if (key.equalsIgnoreCase("announceInterval")) {
            message.commandUsage.announceIntervalUsage(player);
        } else if (key.equalsIgnoreCase("sendToAll")) {
            message.commandUsage.sendToAllUsage(player);
        } else if (key.equalsIgnoreCase("enableTp")) {
            message.commandUsage.enableTpUsage(player);
        } else if (key.equalsIgnoreCase("randomOrder")) {
            message.commandUsage.randomOrderUsage(player);
        } else if (key.equalsIgnoreCase("tpCost")) {
            message.commandUsage.tpCostUsage(player);
        } else if (key.equalsIgnoreCase("transWorldAddition")) {
            message.commandUsage.transWorldAdditionAllUsage(player);
        } else if (key.equalsIgnoreCase("maxAdRunTime")) {
            message.commandUsage.maxAdRunTimeUsage(player);
        } else if (key.equalsIgnoreCase("shopsPerPlayer")) {
            message.commandUsage.shopsPerPlayerUsage(player);
        } else if (key.equalsIgnoreCase("adCost")) {
            message.commandUsage.adCostUsage(player);
        } else if (key.equalsIgnoreCase("tpCostDestination")) {
            message.commandUsage.tpCostDestinationUsage(player);
        } else if (key.equalsIgnoreCase("announceRadius")) {
            message.commandUsage.announceRadiusUsage(player);
        } else {
            message.commandUsage.configCommand(player);
        }

    }

    private void changeConfig(Player player, String[] args) {
        message.console.debug("changeConfig");
        String key = args[1];
        String value = args[2];


        if (key.equalsIgnoreCase("announceInterval")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setAnnounceInterval(Integer.parseInt(value));
            } else {
                message.commandUsage.announceIntervalUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("sendToAll")) {
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                config.setSendToAll(Boolean.parseBoolean(value));
            } else {
                message.commandUsage.sendToAllUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("enableTp")) {
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                config.setEnableTp(Boolean.parseBoolean(value));
            } else {
                message.commandUsage.enableTpUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("randomOrder")) {
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                config.setRandomOrder(Boolean.parseBoolean(value));
            } else {
                message.commandUsage.randomOrderUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("tpCost")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setTpCost(Double.parseDouble(value));
            } else {
                message.commandUsage.tpCostUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("transWorldAddition")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setTransWorldAddition(Double.parseDouble(value));
            } else {
                message.commandUsage.transWorldAdditionAllUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("maxAdRunTime")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setMaxAdRunTime(Integer.parseInt(value));
            } else {
                message.commandUsage.maxAdRunTimeUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("shopsPerPlayer")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setShopsPerPlayer(Integer.parseInt(value));
            } else {
                message.commandUsage.shopsPerPlayerUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("adCost")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setAdCost(Integer.parseInt(value));
            } else {
                message.commandUsage.adCostUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("tpCostDestination")) {
            if (value.equalsIgnoreCase("player") || value.equalsIgnoreCase("shop") || value.equalsIgnoreCase("owner")) {
                config.setTpCostDestination("shop");
            } else if (value.equalsIgnoreCase("server") || value.equalsIgnoreCase("nobody") || value.equalsIgnoreCase("consume")) {
                config.setTpCostDestination("server");
            } else {
                message.commandUsage.tpCostDestinationUsage(player);
            }
            return;
        }


        if (key.equalsIgnoreCase("announceRadius")) {
            if (Character.isDigit(value.charAt(0))) {
                config.setAnnounceRadius(Integer.parseInt(value));
            } else {
                message.commandUsage.announceRadiusUsage(player);
            }
            return;
        }
    }

    private char setCommandParser(String[] args) {
        message.console.debug("setCommandParser");
        char action = 'z';
        if (args[2].equalsIgnoreCase("location") || args[2].equalsIgnoreCase("loc") || args[2].equalsIgnoreCase("l")) {
            action = 'l';
        }
        if (args[2].equalsIgnoreCase("name") || args[2].equalsIgnoreCase("n")) {
            action = 'n';
        }
        if (args[2].equalsIgnoreCase("advertisement") || args[2].equalsIgnoreCase("ad") || args[2].equalsIgnoreCase("a")) {
            action = 'a';
        }
        if (args[2].equalsIgnoreCase("world") || args[2].equalsIgnoreCase("w")) {
            action = 'w';
        }
        return action;
    }
    // example input /ad set myshop location or /ad set myshop name

    private void setGeneral(Player player, String[] args) {
        message.console.debug("setGeneral");
        String shopName = args[1];
        char action = setCommandParser(args);
        Shop shop = shops[shopHandler.getShopByName(shopName)];

        if (!shop.getShopOwner().equalsIgnoreCase(player.getName())) {
            message.error.notYourShop(player, shop);
        }
        switch (action) {
            case 'a':
                message.error.noAdEntered(player);
                break;
            case 'l':
                shop.setLocation(player.getLocation());
                break;
            case 'n':
                message.error.noNameEntered(player);
                break;
            case 'w':

                shop.addWorldToAdvertiseIn(player.getWorld());
                message.command.worldAddedToShop(shop, player, player.getWorld());
            default:
                message.commandUsage.setCommand(player);
                break;
        }
    }

    private void setSpecific(Player player, String[] args) {
        message.console.debug("setSpecific");
        String shopName = args[1];
        char action = setCommandParser(args);
        Shop shop = shops[shopHandler.getShopByName(shopName)];
        switch (action) {
            case 'a':
                StringBuilder sb = null;
                for (int i = 3; i < args.length; i++) {
                    sb.append(args[i]);
                    sb.append(" ");
                }
                shop.setAdvertisement(sb.toString());
                shops[shopHandler.getShopByName(shopName)] = shop;
                break;
            case 'l':
                shop.setLocation(player.getLocation());
                message.error.inputIgnored(player, args[3]);
                break;
            case 'n':
                shop.setShopName(args[3]);
                message.command.shopNameChanged(player, shops[shopHandler.getShopByName(shopName)], args[3]);
                break;
            case 'w':
                shopHandler.addWorldToShop(shop, args[3]);
                message.command.worldAddedToShop(shop, player, serverInterface.getWorld(args[3]));
                break;
            default:
                message.commandUsage.setCommand(player);
                break;
        }


    }

    private void setGeneralOther(Player player, Shop shop, String[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
