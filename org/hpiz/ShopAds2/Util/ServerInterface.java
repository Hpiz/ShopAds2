/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ServerInterface extends ShopAds2{
   
    public World getWorld (String string){
        for (World w : getWorlds()) {
            if (w.getName().equalsIgnoreCase(string)) {
                return w;
            }
        }
        return null;
    }
   public boolean worldExists(String string) {
        for (World w : getWorlds()) {
            if (w.getName().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }


    
}
