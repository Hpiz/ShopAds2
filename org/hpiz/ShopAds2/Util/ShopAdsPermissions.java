/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsPermissions extends ShopAds2 {

    public static Permission permission = null;

    /*  protected Boolean setupPermissions()
    {
    RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
    if (permissionProvider != null) {
    permission = permissionProvider.getProvider();
    }
    return (permission != null);
    }
     */
    public boolean hasCreatorPermission(Player player) {
        if (permission.has(player, "ShopAds.Creator")) {
            return true;
        }
        return false;
    }

    public boolean hasCreatePermission(Player player) {
        if (this.hasCreatorPermission(player)) {
            return true;
        }
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Creator.create")) {
            return true;
        }
        return false;
    }

    public boolean hasSetPermission(Player player) {
        if (this.hasCreatorPermission(player)) {
            return true;
        }
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Creator.set.self")) {
            return true;
        }
        return false;
    }

    public boolean hasStatsPermission(Player player) {
        if (this.hasCreatorPermission(player)) {
            return true;
        }
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Creator.stats.self")) {
            return true;
        }
        return false;
    }

    public boolean hasStatsOtherPermission(Player player) {
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Admin.stats.other")) {
            return true;
        }
        return false;
    }

    public boolean hasAdminDeletePermission(Player player) {
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Admin.delete.other")) {
            return true;
        }
        return false;
    }

    public boolean hasDeleteOwnPermission(Player player) {
        if (this.hasCreatorPermission(player)) {
            return true;
        }
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Creator.delete.own")) {
            return true;
        }
        return false;
    }

    public boolean hasAdminPermission(Player player) {
        if (permission.has(player, "ShopAds.Admin") || permission.has(player, "ShopAds.*") || player.isOp()) {
            return true;
        }
        return false;
    }

    public boolean hasSetOtherPermission(Player player) {
        if (this.hasCreatorPermission(player)) {
            return true;
        }
        if (this.hasAdminPermission(player)) {
            return true;
        }
        if (permission.has(player, "ShopAds.Creator.set.other")) {
            return true;
        }
        return false;
    }
}
