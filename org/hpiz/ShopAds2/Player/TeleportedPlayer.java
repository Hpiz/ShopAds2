/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Player;

import java.util.Calendar;
import java.util.Date;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class TeleportedPlayer extends ShopAds2 {

    private Player player;
    private Date tpExpire;
    private Location oldLoc;

    public TeleportedPlayer(Player p, Date newTpExpire, Location newOldLoc) {
        player = p;

        tpExpire = newTpExpire;
        oldLoc = newOldLoc;

    }

    public boolean isExpired() {
        Calendar calNow = Calendar.getInstance();
        Date dateNow = calNow.getTime();
        return dateNow.before(this.tpExpire);
    }

    public Location getOldLoc() {
        return oldLoc;
    }

    public Player getPlayer() {
        return player;
    }

    public void returnPlayer() {
        player.teleport(oldLoc);
        message.tpTimeout(player);
    }
}
