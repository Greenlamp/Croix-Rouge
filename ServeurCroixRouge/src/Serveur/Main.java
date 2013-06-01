/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Serveur;

import FileAccess.FileAccess;


public class Main {
    public static void main(String args[]) {
        int port = Integer.parseInt(FileAccess.getConfig("configs", "PORT"));
        int nbThread = Integer.parseInt(FileAccess.getConfig("configs", "NB_THREADS"));
        ServeurPool serveurPool = new ServeurPool(port, 5, "Protocole.ProtocoleServeur");
        Thread myThread = new Thread(serveurPool);
        myThread.start();
    }
}
