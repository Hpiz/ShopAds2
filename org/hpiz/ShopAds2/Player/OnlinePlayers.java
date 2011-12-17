/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class OnlinePlayers extends ShopAds2{

    ArrayList<Player> onlinePlayers;
    
    public OnlinePlayers() {
        onlinePlayers = new ArrayList<Player>();
    }
    
    public ArrayList<Player> getOnlinePlayers() {
        return onlinePlayers;
    }
    
    public void addPlayer(Player player) {
        onlinePlayers.add(player);   
       message.console.debug(onlinePlayers.get(0).getName());
    }

    public void removePlayer(Player player) {
        message.console.debug("before onlinePlayer remove: " + onlinePlayers.size());
        onlinePlayers.remove(player);
        message.console.debug("after onlinePlayer remove: " + onlinePlayers.size());
    }
}

