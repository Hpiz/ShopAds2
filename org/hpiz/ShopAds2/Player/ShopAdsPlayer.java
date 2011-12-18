/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import java.io.Serializable;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsPlayer extends ShopAds2 implements Serializable {

    private String name;
    private boolean wantsAds;
    private int ownedShops;

    public ShopAdsPlayer(String name, boolean wantsAds, int ownedShops) {
        this.name = name;
        this.wantsAds = wantsAds;
        this.ownedShops = ownedShops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnedShops() {
        return ownedShops;
    }

    public void setOwnedShops(int ownedShops) {
        this.ownedShops = ownedShops;
    }

    public boolean getWantsAds() {
        return wantsAds;
    }

    public void setWantsAds(boolean wantsAds) {
        this.wantsAds = wantsAds;
    }

    public void addOwnedShop() {
        ownedShops++;
    }

    public void subtractOwnedShop() {
        if (ownedShops == 1) {
            ownedShops = 0;
        } else {
            if (ownedShops == 0) {
                return;
            } else {
                ownedShops--;
            }
        }
    }
}
