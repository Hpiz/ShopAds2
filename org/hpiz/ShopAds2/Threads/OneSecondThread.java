/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Threads;

import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class OneSecondThread extends Thread {

    private final ShopAds2 plugin;

    public OneSecondThread(ShopAds2 plugin) {
        this.plugin = plugin;

    }

    @Override
    public void run() {
        // teleportHandler.tpReturn();
        plugin.playerHandler.returnTeleportedPlayers();
        return;
    }
}
