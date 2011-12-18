/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2;

import java.util.Calendar;
import java.util.Date;
import org.hpiz.ShopAds2.Command.ShopAdsCommand;
import org.hpiz.ShopAds2.Threads.AnnounceThread;
import org.hpiz.ShopAds2.Util.ShopAdsPermissions;
import org.hpiz.ShopAds2.Util.Messaging.ShopAdsMessage;
import org.hpiz.ShopAds2.Util.ShopAdsEconomy;
import org.hpiz.ShopAds2.Util.ShopAdsConfig;
import org.hpiz.ShopAds2.Util.ShopAdsIO;
import org.hpiz.ShopAds2.Player.ShopAdsPlayerListener;
import org.hpiz.ShopAds2.Player.PlayerHandler;
import org.hpiz.ShopAds2.Shop.ShopHandler;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.Threads.OneSecondThread;


/**
 *
 * @author Chris
 */
public class ShopAds2 extends JavaPlugin {

    protected AnnounceThread thread;
    protected OneSecondThread thread2;
    public final Plugin plugin = this;
    private final ShopAdsPlayerListener playerListener = new ShopAdsPlayerListener(this);
    public static final Logger log = Logger.getLogger("Minecraft");
    public static final ShopAdsConfig config = new ShopAdsConfig();
    public static final ShopAdsMessage message = new ShopAdsMessage();
    public static String prefix;
    public static final ShopAdsEconomy economy = new ShopAdsEconomy();
    public static final ShopAdsPermissions permissions = new ShopAdsPermissions();
    public static final ShopAdsIO iO = new ShopAdsIO();
    public static final PlayerHandler playerHandler = new PlayerHandler();
    public static final ShopHandler shopHandler = new ShopHandler();

    public static BukkitScheduler scheduler;
    public static Server server;
    //Class initialization
    public static final ShopAdsCommand command = new ShopAdsCommand();

    public ShopAds2() {
        this.thread = new AnnounceThread(this);
        this.thread2 = new OneSecondThread(this);

    }

    @Override
    public void onEnable() {
        server = this.getServer();
        PluginDescriptionFile pdfFile = getDescription();
        log.info("[" + pdfFile.getName() + "]" + " Version " + pdfFile.getVersion() + " loading.");
        server.getPluginManager().registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Low, this);
        server.getPluginManager().registerEvent(Type.PLAYER_QUIT, playerListener, Priority.Low, this);
        getCommand("ad").setExecutor(command);
        getCommand("ads").setExecutor(command);
        getCommand("shops").setExecutor(command);
        scheduler = server.getScheduler();
        if (!setupPermissions()) {
            this.onDisable();
        }
        if (!setupEconomy()) {
            this.onDisable();
        }
        reload();



    }

    @Override
    public void onDisable() {
        message.console.disablePlugin();
        message.console.savingPlayers();
        iO.savePlayers();
        message.console.savingShops();
        iO.saveShops();
        log.info("[ShopAds2] Disabling plugin");
        scheduler.cancelTasks(this);
        server.getPluginManager().disablePlugin(this);
    }

    public void reload() {

        iO.loadConfig();
        iO.loadPlayers();
        iO.loadShops();
        message.console.numberOfShopsLoaded();
        if (playerHandler.isEmpty()) {
            // Do nothing
        } else {
            playerHandler.updateOwnedShopsFromFile();
        }
        prefix = (config.getLabelColor() + "[ShopAds] " + config.getMessageColor());
        Long interval = (Long.parseLong(String.valueOf(config.getAnnounceInterval())) * 20L);
        Long oneSecond = (1L * 20L);
        scheduler.scheduleAsyncRepeatingTask(this, thread2, oneSecond.longValue(), oneSecond.longValue());
        scheduler.scheduleAsyncRepeatingTask(this, this.thread, interval.longValue(), interval.longValue());



        /*       
        if (config.getDebug()) {
        Calendar calNow = Calendar.getInstance();
        Date dateNow = calNow.getTime();
        Date timeToEnd = calNow.getTime();
        timeToEnd.setTime(dateNow.getTime() + Long.parseLong("15") * 3600000);
        Location test = new Location(server.getWorld("world"), 4, 4, 4, 0.0F, 0.0F);
        String[] worldTo = new String[1];
        worldTo[0] = "world";
        Shop testShop;
        testShop = new Shop("DebugShop", test, "Debug", timeToEnd, false, server.getWorld("world"), "This shop was auto-generated", worldTo, true);
        if (!shopHandler.shopExists(testShop.getShopName())){
        
        
        shopHandler.addShop(testShop);
        
        }
      
    }
         
         */
}

public boolean playersOnline() {
        if (server.getOnlinePlayers()==null || server.getOnlinePlayers().length<1) {
            return false;
        }
        return true;
    }

    public 



boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class  

    );
        
    
    if (permissionProvider 

    
        

    
        != null) {
            permissions.permission = permissionProvider.getProvider();
        message.console.hookedPermissions(permissionProvider.getProvider().getName());
        return true;
    }
    


return false;
    }

    public 



boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class  

    );
        
    
    if (economyProvider 

    
        

    
        != null) {
            economy.economy = economyProvider.getProvider();
        message.console.hookedEconomy(economyProvider.getProvider().getName());
        return true;
    }
    


return false;
    }
}
