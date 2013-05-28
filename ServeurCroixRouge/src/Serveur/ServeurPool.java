/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Serveur;

import Behind.Traitement;
import SSL.NetworkServerSSL;
import Threads.PoolThread;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServeurPool implements Runnable{
    NetworkServerSSL reseau;
    int port;
    PoolThread poolThread;



    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public ServeurPool(int port, int nbThreads){
        try {
            this.reseau = new NetworkServerSSL(port);
            this.poolThread = new PoolThread(nbThreads);
        } catch (Exception ex) {
            Logger.getLogger(ServeurPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ServeurPool(int port, int nbThreads, String protocole) {
        try {
            this.reseau = new NetworkServerSSL(port, protocole);
            this.poolThread = new PoolThread(nbThreads);
        } catch (Exception ex) {
            Logger.getLogger(ServeurPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    /**************************************************************************/
    /*Runnable*/
    /**************************************************************************/
    @Override
    public void run() {
        if(reseau != null){
            boolean goOn = true;
            int nbClient = 0;
            while(goOn){
                goOn = reseau.accept();
                Traitement traitement = new Traitement(new NetworkServerSSL(reseau.getSocketService()));
                this.poolThread.assign(traitement);
                nbClient++;
            }
            reseau.disconnect();
        }
    }
}
