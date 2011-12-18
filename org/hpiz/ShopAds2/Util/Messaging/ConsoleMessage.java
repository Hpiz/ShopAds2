/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util.Messaging;

/**
 *
 * @author Chris
 */
public class ConsoleMessage extends ShopAdsMessage {

    public void numberOfShopsLoaded() {
        if (shopHandler.shopsEmpty()) {
            log.info("[ShopAds2] No shops were found.");
            return;
        }
        if (shopHandler.getSize() == 1) {
            log.info("[ShopAds2] 1 shop was loaded.");
        } else {
            log.info("[ShopAds2] " + shopHandler.getSize() + " shops were loaded.");
        }
        return;
    }

    public void checkConfigOption(String parameter) {
        log.info("[ShopAds2] Check the config option '" + parameter + "'.");
    }

    public void debug(String message) {
        if (config.getDebug()) {
            log.info("[ShopAds2 DEBUG-MODE] " + message);
        }
    }

    public void hookedEconomy(String name) {
        log.info("[ShopAds2] Hooked into " + name);
    }

    public void hookedPermissions(String name) {
        log.info("[ShopAds2] Hooked into " + name);
    }

    public void disablePlugin() {
        log.info("[ShopAds2] Plugin disabled.");
    }

    public void playersFileReset() {
        log.info("[ShopAds2] PLAYERS FILE DOESN'T MATCH UP WITH SHOPS, SUGGEST DELETING SHOPS.DAT FILE.");
    }

    public void savingShops() {
        log.info("[ShopAds2] Saving shops.");
    }

    public void savingPlayers() {
        log.info("[ShopAds2] Saving players.");
    }

    public void announceDebug(String string) {
        if (config.getAnnounceDebug()) {
            log.info("[ShopAds2 ANNOUNCE DEBUG] " + string);
        }
    }
}
