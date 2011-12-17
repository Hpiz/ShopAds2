package org.hpiz.ShopAds2.Threads;

import java.util.Random;
import java.util.Scanner;
import org.hpiz.ShopAds2.ShopAds2;

public class AnnounceThread extends Thread {

    private final Random randomGenerator;
    private final ShopAds2 plugin;
    private int lastAnnouncement = 0;
    private int[] announcementOrder = null;
    private int randomIndex;

    public AnnounceThread(ShopAds2 SA) {
        this.randomGenerator = new Random();
        this.plugin = SA;
    }

    @Override
    public void run() {



        if (plugin.shopHandler.shopsEmpty()) {
                if (plugin.playersOnline()) {
                    if (this.plugin.config.getRandomOrder()) {
                        plugin.message.console.debug("Random is on");
                      
                        if (announcementOrder == null) {
                            plugin.message.console.debug("generating");
                            announcementOrder = new int[plugin.shopHandler.getSize()];
                            for (int i = 0; i < announcementOrder.length; i++) {
                                announcementOrder[i] = i;
                            }
                            randomIndex = 0;
                            for (int i = 0; i < this.announcementOrder.length; i++) {
                                int randomPosition = this.randomGenerator.nextInt(this.announcementOrder.length);
                                int temp = this.announcementOrder[i];
                                this.announcementOrder[i] = this.announcementOrder[randomPosition];
                                this.announcementOrder[randomPosition] = temp;
                            }
                        }
                        plugin.message.advertise.advertise(plugin.shopHandler.getShop(randomIndex));

                        randomIndex++;
                        if (randomIndex >= announcementOrder.length) {
                            announcementOrder = null;
                            randomIndex = 0;
                        }
                    } else {
                        if (this.lastAnnouncement >= plugin.shopHandler.getSize()) {

                            this.lastAnnouncement = 0;
                        }
                        if (this.lastAnnouncement < plugin.shopHandler.getSize()) {
                           // plugin.message.consoleMessage.debug("Last: " + lastAnnouncement);
                           // plugin.message.consoleMessage.debug("Shops: " + plugin.shops.length);
                            plugin.message.advertise.advertise(plugin.shopHandler.getShop(this.lastAnnouncement));
                            this.lastAnnouncement++;
                        }
                    }
                }
            }
        }
    }
