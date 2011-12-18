/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import org.bukkit.ChatColor;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsConfig extends ShopAds2 {

    private int announceInterval;
    private boolean sendToAll;
    private boolean enableTp;
    private boolean randomOrder;
    private double tpCost;
    private double transWorldAddition;
    private boolean adsOverWorlds;
    private int tpTimeout;
    private int maxAdRunTime;
    private int shopsPerPlayer;
    private double adCost;
    private String tpCostDestination;
    private int announceRadius;
    private boolean debug = false;
    private boolean announceDebug = false;
    //private ChatColor labelColor;
    //private ChatColor messageColor;
    //private enum labelColor {AQUA,BLACK,BLUE,DARK_AQUA,DARK_BLUE,DARK_GRAY,DARK_GREEN,DARK_PURPLE,DARK_RED,GOLD,GREEN,GRAY,LIGHT_PURPLE,RED,WHITE,YELLOW};
    //private enum messageColor {AQUA,BLACK,BLUE,DARK_AQUA,DARK_BLUE,DARK_GRAY,DARK_GREEN,DARK_PURPLE,DARK_RED,GOLD,GREEN,GRAY,LIGHT_PURPLE,RED,WHITE,YELLOW};
    private String lColor;
    private String mColor;

    public ShopAdsConfig() {
        return;
    }

    public ChatColor getLabelColor() {

        if (lColor.equalsIgnoreCase("aqua")) {
            return ChatColor.AQUA;
        }
        if (lColor.equalsIgnoreCase("black")) {
            return ChatColor.BLACK;
        }
        if (lColor.equalsIgnoreCase("blue")) {
            return ChatColor.BLUE;
        }
        if (lColor.equalsIgnoreCase("dark_aqua")) {
            return ChatColor.DARK_AQUA;
        }
        if (lColor.equalsIgnoreCase("dark_blue")) {
            return ChatColor.DARK_BLUE;
        }
        if (lColor.equalsIgnoreCase("dark_gray")) {
            return ChatColor.DARK_GRAY;
        }
        if (lColor.equalsIgnoreCase("dark_green")) {
            return ChatColor.DARK_GREEN;
        }
        if (lColor.equalsIgnoreCase("dark_purple")) {
            return ChatColor.DARK_PURPLE;
        }
        if (lColor.equalsIgnoreCase("dark_red")) {
            return ChatColor.DARK_RED;
        }
        if (lColor.equalsIgnoreCase("gold")) {
            return ChatColor.GOLD;
        }
        if (lColor.equalsIgnoreCase("gray")) {
            return ChatColor.GRAY;
        }
        if (lColor.equalsIgnoreCase("green")) {
            return ChatColor.GREEN;
        }
        if (lColor.equalsIgnoreCase("light_purple")) {
            return ChatColor.LIGHT_PURPLE;
        }
        if (lColor.equalsIgnoreCase("red")) {
            return ChatColor.RED;
        }
        if (lColor.equalsIgnoreCase("white")) {
            return ChatColor.WHITE;
        }
        if (lColor.equalsIgnoreCase("yellow")) {
            return ChatColor.YELLOW;
        }

        return ChatColor.WHITE;


    }

    public void setLabelColor(String color) {
        message.console.debug("setting label color to :" + color);
        lColor = color;
        message.console.debug("after lcolor=color : " + lColor);

    }

    public ChatColor getMessageColor() {

        if (mColor.equalsIgnoreCase("aqua")) {
            return ChatColor.AQUA;
        }
        if (mColor.equalsIgnoreCase("black")) {
            return ChatColor.BLACK;
        }
        if (mColor.equalsIgnoreCase("blue")) {
            return ChatColor.BLUE;
        }
        if (mColor.equalsIgnoreCase("dark_aqua")) {
            return ChatColor.DARK_AQUA;
        }
        if (mColor.equalsIgnoreCase("dark_blue")) {
            return ChatColor.DARK_BLUE;
        }
        if (mColor.equalsIgnoreCase("dark_gray")) {
            return ChatColor.DARK_GRAY;
        }
        if (mColor.equalsIgnoreCase("dark_green")) {
            return ChatColor.DARK_GREEN;
        }
        if (mColor.equalsIgnoreCase("dark_purple")) {
            return ChatColor.DARK_PURPLE;
        }
        if (mColor.equalsIgnoreCase("dark_red")) {
            return ChatColor.DARK_RED;
        }
        if (mColor.equalsIgnoreCase("gold")) {
            return ChatColor.GOLD;
        }
        if (mColor.equalsIgnoreCase("gray")) {
            return ChatColor.GRAY;
        }
        if (mColor.equalsIgnoreCase("green")) {
            return ChatColor.GREEN;
        }
        if (mColor.equalsIgnoreCase("light_purple")) {
            return ChatColor.LIGHT_PURPLE;
        }
        if (mColor.equalsIgnoreCase("red")) {
            return ChatColor.RED;
        }
        if (mColor.equalsIgnoreCase("white")) {
            return ChatColor.WHITE;
        }
        if (mColor.equalsIgnoreCase("yellow")) {
            return ChatColor.YELLOW;
        }

        return ChatColor.WHITE;


    }

    public void setMessageColor(String color) {
        message.console.debug("setting message color");
        mColor = color;
    }

    public int getAnnounceRadius() {
        return announceRadius;
    }

    public void setAnnounceRadius(int announceRadius) {
        this.announceRadius = announceRadius;
    }

    public String getTpCostDestination() {
        return tpCostDestination;
    }

    public void setTpCostDestination(String tpCostDestination) {
        this.tpCostDestination = tpCostDestination;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public double getAdCost() {
        return adCost;
    }

    public void setAdCost(double adCost) {
        this.adCost = adCost;
    }

    public boolean getAdsOverWorlds() {
        return adsOverWorlds;
    }

    public void setAdsOverWorlds(boolean adsOverWorlds) {
        this.adsOverWorlds = adsOverWorlds;
    }

    public int getAnnounceInterval() {
        return announceInterval;
    }

    public void setAnnounceInterval(int announceInterval) {
        this.announceInterval = announceInterval;
    }

    public boolean getEnableTp() {
        return enableTp;
    }

    public void setEnableTp(boolean enableTp) {
        this.enableTp = enableTp;
    }

    public int getMaxAdRunTime() {
        return maxAdRunTime;
    }

    public void setMaxAdRunTime(int maxAdRunTime) {
        this.maxAdRunTime = maxAdRunTime;
    }

    public boolean getRandomOrder() {
        return randomOrder;
    }

    public void setRandomOrder(boolean randomOrder) {
        this.randomOrder = randomOrder;
    }

    public boolean getSendToAll() {
        return sendToAll;
    }

    public void setSendToAll(boolean sendToAll) {
        this.sendToAll = sendToAll;
    }

    public int getShopsPerPlayer() {
        return shopsPerPlayer;
    }

    public void setShopsPerPlayer(int shopsPerPlayer) {
        this.shopsPerPlayer = shopsPerPlayer;
    }

    public double getTpCost() {
        return tpCost;
    }

    public void setTpCost(double tpCost) {
        this.tpCost = tpCost;
    }

    public int getTpTimeout() {
        return tpTimeout;
    }

    public void setTpTimeout(int tpTimeout) {
        this.tpTimeout = tpTimeout;
    }

    public double getTransWorldAddition() {
        return transWorldAddition;
    }

    public void setTransWorldAddition(double transWorldAddition) {
        this.transWorldAddition = transWorldAddition;
    }

    public boolean getAnnounceDebug() {
        
        return announceDebug;
    }

    public void setAnnounceDebug(boolean announceDebug) {
        this.announceDebug = announceDebug;
    }
    
}
