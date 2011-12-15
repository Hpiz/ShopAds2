/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.*;
import org.hpiz.ShopAds2.Shop.Shop;

/**
 *
 * @author Chris
 */
public class Mathematical extends ShopAds2{
    
        public boolean isNumber(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c) && !Character.isSpaceChar(c) && !Character.isWhitespace(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

    public double playerDistanceFromShop(Player p, Shop shop) {
        return shop.getLocation().distance(p.getLocation());
    }
  
}
