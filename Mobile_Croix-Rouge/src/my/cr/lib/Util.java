/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.lib;

import java.util.LinkedList;


public class Util {

    public static String[] convertToStringArray(LinkedList<String[]> data) {
        int size = data.size();
        size *= 2;
        size += 2;
        String[] items = new String[size];
        items[0] = "[NOM]";
        items[1] = "[PRENOM]";
        int i=2;
        for(String[] elm : data){
            items[i] = elm[0];
            i++;
            items[i] = elm[1];
            i++;
        }
        return items;
    }

}
