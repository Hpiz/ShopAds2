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
        if (shops == null || shops.length < 1) {
            log.info("[ShopAds2] No shops were found.");
            return;
        }
        if (shops.length == 1) {
            log.info("[ShopAds2] 1 shop was loaded.");
        } else {
            log.info("[ShopAds2] " + shops.length + " shops were loaded.");
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
}
