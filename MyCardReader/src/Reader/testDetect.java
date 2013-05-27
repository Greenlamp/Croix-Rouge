/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Reader;

import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.event.CardListener;


public class testDetect {
    public static void main(String args[]) {
        BeID beid = new BeID(true);
        if(beid.isConnected()){
            beid.enableCardListener(new CardListener() {

                @Override
                public void cardInserted() {
                    System.out.println("Carte insérée");
                }

                @Override
                public void cardRemoved() {
                    System.out.println("Carte retirée");
                }
            });
        }
    }
}
