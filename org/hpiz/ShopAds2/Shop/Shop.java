package org.hpiz.ShopAds2.Shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.hpiz.ShopAds2.ShopAds2;

public class Shop extends ShopAds2 implements Serializable {

    private String shopName;
    private AdLocation location;
    private String creator;
    private Date timeToEnd;
    private boolean runsForever;
    private String advertisement;
    private ArrayList<String> worldsToAdvertiseIn;
    private boolean isAdvertising;
    private boolean isExpired;
    private double moneyEarned;
    private int timesTeleportedTo;

    public Shop(String newShopName, Location shopLocation, String newCreator, Date time, boolean forever, World w, String ad, String[] advertiseTo, boolean advertising) {
        this.shopName = newShopName;
        this.location = new AdLocation(shopLocation);
        this.creator = newCreator;
        this.timeToEnd = time;
        this.advertisement = ad;
        this.runsForever = forever;
        this.worldsToAdvertiseIn = new ArrayList<String>(Arrays.asList(advertiseTo));
        this.isExpired = false;
        this.moneyEarned = 0.00;
        this.timesTeleportedTo = 0;
        this.isAdvertising = advertising;
    }

    public World getWorld() {
        return location.getWorld();
    }

    public boolean advertisesIn(String world) {
        for (String s : this.worldsToAdvertiseIn) {
            if (s.equalsIgnoreCase(world)) {
                return true;
            }
        }
        return false;
    }

    public World[] getWorldsToAdvertiseIn() {
        World[] worlds = new World[this.worldsToAdvertiseIn.size()];
        for (int i = 0; i < worlds.length; i++) {
            worlds[i] = server.getWorld(worldsToAdvertiseIn.get(i));
        }
        return worlds;
    }

    public double getMoneyEarned() {
        return this.moneyEarned;
    }

    public String getShopName() {
        return this.shopName;
    }

    public int getTimesTeleportedTo() {
        return this.timesTeleportedTo;
    }

    public AdLocation getLocation() {
        return this.location;
    }

    public String getAd() {
        return this.advertisement;
    }

    public Date getTimeToEnd() {
        return this.timeToEnd;
    }

    public String getShopOwner() {
        return this.creator;
    }

    public boolean shopExpired() {
        return this.isExpired;
    }

    public boolean runsForever() {
        return this.runsForever;
    }

    public boolean shopAdvertising() {
        return this.isAdvertising;
    }

    public void setShopAdvertising(boolean b) {
        this.isAdvertising = b;
    }

    public void setShopExpired(boolean b) {
        this.isExpired = b;
    }

    public void setTimeToEnd(Date time) {
        this.timeToEnd = time;
    }

    public void setWorldsToAdvertiseIn(World[] w) {
        String[] worlds = new String[w.length];
        for (int i = 0; i < w.length; i++) {
            worlds[i] = w[i].getName();
            this.worldsToAdvertiseIn = new ArrayList<String>(Arrays.asList(worlds));
        }
    }

    public void shopTeleportedTo() {
        this.timesTeleportedTo++;
    }

    public void addMoneyEarned(double money) {
        this.moneyEarned = +money;
    }

    public void setLocation(Location location) {
        this.location = new AdLocation(location);
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public void setIsAdvertising(boolean isAdvertising) {
        this.isAdvertising = isAdvertising;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void addWorldToAdvertiseIn(World world) {
        if (this.worldsToAdvertiseIn.contains(world.getName())) {
            return;
        }
        this.worldsToAdvertiseIn.add(world.getName());
    }

    public String[] getWorldsToAdvertiseInAsString() {
        String[] worlds = new String[this.worldsToAdvertiseIn.size()];
        for (int i = 0; i < worlds.length; i++) {
            worlds[i] = this.worldsToAdvertiseIn.get(i);
        }
        return worlds;
    }
}
