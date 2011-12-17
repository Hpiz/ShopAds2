/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Shop;

import java.io.Serializable;
import org.bukkit.Location;
import org.bukkit.World;

import org.bukkit.util.Vector;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class AdLocation extends ShopAds2 implements Serializable {

    private String world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public AdLocation(Location loc) {
        //compiled code
        x=loc.getX();
        y=loc.getY();
        z=loc.getZ();
        pitch = loc.getPitch();
        yaw = loc.getYaw();
        world = loc.getWorld().getName();
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }



    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }


    public void setX(double x) {
        //compiled code
        this.x = x;
    }

    public Vector getDirection() {
        return new Location (serverInterface.getWorld(world),x,y,z,pitch,yaw).getDirection();
    }
    
    public Location getLocation(){
        return new Location (serverInterface.getWorld(world),x,y,z,pitch,yaw);
    }

    public double distance(Location o) {
        //compiled code
        return new Location (serverInterface.getWorld(world),x,y,z,pitch,yaw).distance(o);
    }

    public World getWorld() {
        message.console.debug ("World in shop: " + world);
        
        World realWorld = worlds.getWorlds().get(worlds.getWorld(world));
        return realWorld;
    }
    
   
    


}
