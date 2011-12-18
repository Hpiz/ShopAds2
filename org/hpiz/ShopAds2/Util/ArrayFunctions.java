/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hpiz.ShopAds2.Util;

import org.hpiz.ShopAds2.ShopAds2;

/**
 *
 * @author Chris
 */
public class ArrayFunctions extends ShopAds2 {

    public Object[] addToArray(Object[] O, Object o) {
        if (O != null && O.length > 0) {
            Object[] transfer = new Object[O.length + 1];
            for (int i = 0; i < O.length; i++) {
                transfer[i] = O[i];
            }
            transfer[O.length] = o;
            O = new Object[transfer.length];
            O = transfer;
            return O;
        }
        O = new Object[1];
        O[0] = o;
        return O;



    }
}
