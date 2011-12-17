/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.World;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsWorlds extends ShopAds2{
    private ArrayList<World>worlds = new ArrayList<World>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }
    
    public ShopAdsWorlds(List<World>newWorlds){
        worlds = new ArrayList<World> (newWorlds);
        message.console.debug(" World loaded: " + worlds.get(0).getName());
    }

    public int getWorld(String world) {
        for (int i=0; i<worlds.size();i++){
            if(worlds.get(i).getName().equalsIgnoreCase(world)){
                return i;
            }
        }
        return -1;
    }
}
