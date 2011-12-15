/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import org.hpiz.ShopAds2.Shop.Shop;
import org.hpiz.ShopAds2.Player.ShopAdsPlayer;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ShopAdsIO extends ShopAds2 {

    private File configFile = new File("plugins/ShopAds/config.yml");
    private File shopsFile = new File("plugins/ShopAds/shops.dat");
    private File playerFile = new File("plugins/ShopAds/player.dat");
    private File shopAdsDir = new File("plugins/ShopAds");
    private final ShopAds2 plugin = new ShopAds2();

    public boolean loadConfig() {
        message.console.debug("loadingConfig");
        Properties pr = new Properties();
        if (this.configFile.exists()) {
            try {

                FileInputStream in = new FileInputStream(configFile);
                pr.load(in);
                try {
                    config.setAnnounceInterval(Integer.parseInt(pr.getProperty("announceInterval")));
                } catch (Exception e) {
                    message.console.checkConfigOption("announceInterval");
                }
                try {
                    config.setSendToAll(Boolean.parseBoolean(pr.getProperty("sendToAll")));
                } catch (Exception e) {
                    message.console.checkConfigOption("sendToAll");
                }
                try {
                    config.setEnableTp(Boolean.parseBoolean(pr.getProperty("enableTp")));
                } catch (Exception e) {
                    message.console.checkConfigOption("enableTp");
                }
                try {
                    config.setAdsOverWorlds(Boolean.parseBoolean(pr.getProperty("adsOverWorlds")));
                } catch (Exception e) {
                    message.console.checkConfigOption("adsOverWorlds");
                }
                try {
                    config.setRandomOrder(Boolean.parseBoolean(pr.getProperty("enableTp")));
                } catch (Exception e) {
                    message.console.checkConfigOption("enableTp");
                }
                try {
                    config.setAdCost(Double.parseDouble(pr.getProperty("adCost")));
                } catch (Exception e) {
                    message.console.checkConfigOption("adCost");
                }
                try {
                    config.setMaxAdRunTime(Integer.parseInt(pr.getProperty("maxAdRunTime")));
                } catch (Exception e) {
                    message.console.checkConfigOption("maxAdRunTime");
                }
                try {
                    config.setShopsPerPlayer(Integer.parseInt(pr.getProperty("shopsPerPlayer")));
                } catch (Exception e) {
                    message.console.checkConfigOption("shopsPerPlayer");
                }
                try {
                    config.setTpCost(Double.parseDouble(pr.getProperty("tpCost")));
                } catch (Exception e) {
                    message.console.checkConfigOption("tpCost");
                }
                try {
                    config.setTpTimeout(Integer.parseInt(pr.getProperty("tpTimeout")));
                } catch (Exception e) {
                    message.console.checkConfigOption("tpTimeout");
                }
                try {
                    config.setTransWorldAddition(Integer.parseInt(pr.getProperty("transWorldAddition")));
                } catch (Exception e) {
                    message.console.checkConfigOption("transWorldAddition");
                }
                try {
                    config.setAnnounceRadius(Integer.parseInt(pr.getProperty("announceRadius")));
                } catch (Exception e) {
                    message.console.checkConfigOption("announceRadius");
                }
                try {
                    config.setLabelColor(pr.getProperty("labelColor").toUpperCase());
                } catch (Exception e) {
                    message.console.checkConfigOption("labelColor");
                }
                try {
                    config.setMessageColor(pr.getProperty("messageColor").toUpperCase());
                } catch (Exception e) {
                    message.console.checkConfigOption("messageColor");
                }

                try {
                    config.setTpCostDestination(pr.getProperty("tpCostDestination"));
                } catch (Exception e) {
                    message.console.checkConfigOption("tpCostDestination");
                }
                 try {
                    config.setRandomOrder(Boolean.parseBoolean(pr.getProperty("randomOrder")));
                } catch (Exception e) {
                    message.console.checkConfigOption("randomOrder");
                }
                
                try {
                    config.setDebug(Boolean.parseBoolean(pr.getProperty("debug")));
                } catch (Exception e) {
                    config.setDebug(false);
                }



                log.info("[ShopAds2] Config loaded!");
            } catch (IOException e) {
                log.info("[ShopAds2] Config file exists but is corrupt, please delete it!");
            }
        } else {
            if (!this.shopAdsDir.exists()) {
                this.shopAdsDir.mkdir();
            }
            createDefaultConfig();
            this.loadConfig();
            return true;
        }

        return false;
    }

    public boolean loadPlayers() {
        message.console.debug("loadingPlayers");
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        FileInputStream fis;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(playerFile);
            in = new ObjectInputStream(fis);
        } catch (IOException ex) {
        }
        int playerObjects = this.getObjectCount(playerFile);
        ShopAdsPlayer player = null;
        boolean end = false;
        do {
            try {
                player = (ShopAdsPlayer) in.readObject();
            } catch (IOException ex) {
                end = true;
            } catch (ClassNotFoundException ex) {
                log.info("Something terribly important is missing (ShopAdsPlayer)");
                return false;
            } catch (NullPointerException ex) {
                end = true;
            }
            if (end == false) {

                playerHandler.addPlayer(player);
            }
        } while (!end);
        return true;
    }

    public boolean loadShops() {
        message.console.debug("loadingShops");
        if (!shopsFile.exists()) {
            try {
                shopsFile.createNewFile();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        FileInputStream fis;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(shopsFile);
            in = new ObjectInputStream(fis);
        } catch (IOException ex) {
        }
        int shopsObjects = this.getObjectCount(shopsFile);
        Shop shop = null;
        boolean end = false;
        do {
            try {
                shop = (Shop) in.readObject();
            } catch (IOException ex) {
                end = true;
            } catch (ClassNotFoundException ex) {
                log.info("Something terribly important is missing (Shop)");
                return false;
            } catch (NullPointerException ex) {
                end = true;
            }
            if (end == false) {
                shopHandler.addShop(shop);
            }
        } while (!end);
        return true;
    }

    public boolean saveShops() {
        if (shops == null || shops.length < 1) {
            return true;
        }
        message.console.debug("savingShops");

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {

            fos = new FileOutputStream(shopsFile);
            out = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //message.consoleMessage.debug("Length of SAPlayers5 : " + playerHandler.getPlayers().length);
        message.console.debug(shops[0].getShopName());
        for (Shop p : shops) {
            try {
                message.console.debug("Saving Shop : " + p.getShopName());
                out.writeObject(p);

            } catch (IOException ex) {
                Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
                message.console.debug("There was an error on " + p.getShopName());
            }

        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
            message.console.debug("There was an error closing the player writer");
        }


        return false;
    }

    public int getObjectCount(File file) {
        message.console.debug("getObjectCount on " + file.getName());
        int count = 0;
        FileInputStream fis;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
        } catch (EOFException ex) {
            return 0;
        } catch (IOException ex) {

            return 0;
        }
        Object object = null;
        while (1 > 0) {
            try {
                object = in.readObject();
                count++;
            } catch (NullPointerException ex) {
                break;
            } catch (IOException ex) {
                break;
            } catch (ClassNotFoundException ex) {
                break;
            }
        }
        message.console.debug("Object Count : " + String.valueOf(count));
        return count;
    }

    public void createDefaultConfig() {
        try {
            this.configFile.createNewFile();
            try {
                PrintWriter out = new PrintWriter(new FileWriter(configFile));
                out.println("#'shopsPerPlayer' - The maximum number of ads allowed to each player");
                out.println("#'announceInterval' - The time in seconds between ad announcements [number(secs)]");
                out.println("#'randomOrder' - Should the ads be in a random order [true/false]");
                out.println("#'adcost' - The cost per hour of advertising [number(currency)]");
                out.println("#'maxAdRunTime' - The longest time you want an ad to run for [number(hours)]");
                out.println("#'sendToAll' - Whether to send to all players, disregarding their choice [true/false]");
                out.println("#'tpCost' - Price to charge the player to teleport (0 for free)[number(currency)]");
                out.println("#'tpCostDest' - The destination of the money that is collected from tp [shop/server]");
                out.println("#'transWorldAddition' - How much more to charge when tp to a shop in a different world (0 to disable)([number(currency)]");
                out.println("#'announceRadius' - The distance in blocks that an advertisement will be heard (0 for unlimited)[number(blocks)]");
                out.println("#'adsOverWorlds' - Controls whether ads are broadcasted over to other worlds [true/false]");
                out.println("#'enableTp' - Allows or denies the tp of players to ad locations [true/false]");
                out.println("#'tpTimeout' - The time in seconds until returning player to previous location (0 to disable)[number(seconds)]");
                out.println("shopsPerPlayer=1");
                out.println("announceInterval=240");
                out.println("adCost=20");
                out.println("maxAdRunTime=24");
                out.println("randomOrder=false");
                out.println("sendToAll=true");
                out.println("tpCost=0");
                out.println("tpCostDest=shop");
                out.println("transWorldAddition=0");
                out.println("announceRadius=0");
                out.println("adsOverWorlds=true");
                out.println("enableTp=true");
                out.println("tpTimeout=60");
               out.println("labelColor=GOLD");
               out.println("messageColor=GRAY");
                out.close();
                log.info("[ShopAds2] No config found, created default config");
            } catch (IOException e) {
                log.info("[ShopAds2] Error writing to config");
            }
        } catch (IOException ioe) {
            log.info("[ShopAds] Error creating config file");
        }
    }

    public boolean savePlayers() {
        if (playerHandler.getPlayers() == null || playerHandler.getPlayers().length < 1) {
            return true;
        }
        message.console.debug("savingPlayers");

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {

            fos = new FileOutputStream(playerFile);
            out = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //message.consoleMessage.debug("Length of SAPlayers5 : " + playerHandler.getPlayers().length);
        message.console.debug(playerHandler.shopAdsPlayers[0].getName());
        for (ShopAdsPlayer p : playerHandler.getPlayers()) {
            try {
                message.console.debug("Saving Player : " + p.getName());
                out.writeObject(p);

            } catch (IOException ex) {
                Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
                message.console.debug("There was an error on " + p.getName());
            }

        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ShopAdsIO.class.getName()).log(Level.SEVERE, null, ex);
            message.console.debug("There was an error closing the player writer");
        }


        return false;
    }
}
