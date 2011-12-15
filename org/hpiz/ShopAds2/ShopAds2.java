/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2;

import org.hpiz.ShopAds2.Threads.AnnounceThread;
import org.hpiz.ShopAds2.Util.ShopAdsPermissions;
import org.hpiz.ShopAds2.Util.Messaging.ShopAdsMessage;
import org.hpiz.ShopAds2.Util.ShopAdsEconomy;
import org.hpiz.ShopAds2.Util.ShopAdsConfig;
import org.hpiz.ShopAds2.Util.ShopAdsIO;
import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.Player.ShopAdsPlayerListener;
import org.hpiz.ShopAds2.Player.ShopAdsPlayerHandler;
import org.hpiz.ShopAds2.Shop.ShopHandler;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.hpiz.ShopAds2.Threads.OneSecondThread;
import org.hpiz.ShopAds2.Util.Mathematical;
import org.hpiz.ShopAds2.Util.ServerInterface;

/**
 *
 * @author Chris
 */
public class ShopAds2 extends JavaPlugin {
public static Player[] onlinePlayers;
public static World[] worlds;
    protected AnnounceThread thread;
    protected OneSecondThread thread2;
    public final Plugin plugin = this;
    public final Server server = getServer();
    private final ShopAdsPlayerListener playerListener = new ShopAdsPlayerListener(this);
    public static final Logger log = Logger.getLogger("Minecraft");
    public static final ShopAdsConfig config = new ShopAdsConfig();
    public static final ShopAdsMessage message = new ShopAdsMessage();
    public static String prefix;
    public static final ShopAdsEconomy economy = new ShopAdsEconomy();
    public static final ShopAdsPermissions permissions = new ShopAdsPermissions();
    public static Shop[] shops;
    public static final ShopAdsIO iO = new ShopAdsIO();
    public static final ShopAdsPlayerHandler playerHandler = new ShopAdsPlayerHandler();
    public static final ShopHandler shopHandler = new ShopHandler();
    public static final ServerInterface serverInterface = new ServerInterface();
    //Class initialization
    public static final ShopAdsCommand command = new ShopAdsCommand();

    public ShopAds2() {
        this.thread = new AnnounceThread(this);
        this.thread2 = new OneSecondThread(this);
    }

    public Shop[] getShops() {
        return shops;
    }

    @Override
    public void onEnable() {

        PluginDescriptionFile pdfFile = getDescription();
        log.info("[" + pdfFile.getName() + "]" + " Version " + pdfFile.getVersion() + " loading.");
        this.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, playerListener, Priority.Low, this);
        getCommand("ad").setExecutor(command);
        getCommand("ads").setExecutor(command);
        getCommand("shops").setExecutor(command);
        reload();


    }

    @Override
    public void onDisable() {

        iO.savePlayers();
        iO.saveShops();
        log.info("[ShopAds2] Disabling plugin");
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.cancelTasks(this);
        getServer().getPluginManager().disablePlugin(this);

    }

    public void reload() {


        iO.loadConfig();
        iO.loadPlayers();
        iO.loadShops();
        message.console.numberOfShopsLoaded();
       
        if (playerHandler.getPlayers() == null || playerHandler.getPlayers().length < 0) {
            // Do nothing
        } else {
            playerHandler.updateOwnedShopsFromFile();
        }
        if (!setupPermissions()) {
            this.onDisable();
        }
        if (!setupEconomy()) {
            this.onDisable();
        }
        prefix = (config.getLabelColor() + "[ShopAds] " + config.getMessageColor());
        Long interval = (Long.parseLong(String.valueOf(config.getAnnounceInterval())) * 25L);
        Long oneSecond = (1L * 25L);
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleAsyncRepeatingTask(this, thread2, oneSecond.longValue(), oneSecond.longValue());
        scheduler.scheduleAsyncRepeatingTask(this, this.thread, interval.longValue(), interval.longValue());
        this.setWorlds();
    }
    public Player[] getOnlinePlayers(){
        //message.consoleMessage.debug("Player one: " + onlinePlayers[0]);
            return onlinePlayers;
    
    }
    public World[] getWorlds(){
        //message.consoleMessage.debug("Player one: " + onlinePlayers[0]);
            return worlds;
    
    }

    public boolean playersOnline() {
        if (getServer().getOnlinePlayers() == null) {
            return false;
        } else if (getServer().getOnlinePlayers().length > 0) {
          // message.consoleMessage.debug("Players are online");
            onlinePlayers = new Player[getServer().getOnlinePlayers().length];
           // message.consoleMessage.debug("SA Players set to a length of " +onlinePlayers.length);
            onlinePlayers = getServer().getOnlinePlayers();
          //  message.consoleMessage.debug("First player set to " + onlinePlayers[0].getName());
            return true;
        } else {
            return false;
        }
    }

    public boolean setupPermissions() {


        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permissions.permission = permissionProvider.getProvider();
            message.console.hookedPermissions(permissionProvider.getProvider().getName());
            return true;
        }
        return false;

    }

    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy.economy = economyProvider.getProvider();
            message.console.hookedEconomy(economyProvider.getProvider().getName());
            return true;
        }
        return false;
    }

    private void setWorlds() {
        int size = 0;
        size = getServer().getWorlds().size();
        this.worlds = new World[size];
        for (int i=0; i<size;i++){
            worlds[i] = getServer().getWorlds().get(i);
        }
    }
}
