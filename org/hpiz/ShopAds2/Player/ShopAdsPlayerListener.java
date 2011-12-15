/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsPlayerListener extends PlayerListener {

    private final ShopAds2 plugin;

    public ShopAdsPlayerListener(ShopAds2 p) {
        plugin = p;
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!plugin.playerHandler.playerExists(event.getPlayer().getName())){
        plugin.playerHandler.addPlayer(new ShopAdsPlayer (event.getPlayer().getName(), true, 0));
    
        }

    }
   
    
    
}
