/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Serveur;

public class Main {
    public static void main(String args[]) {
        int port = 12345;//A changer avec FileAccess.
        ServeurPool serveurPool = new ServeurPool(port, 5, "Protocole.ProtocoleServeur");
        Thread myThread = new Thread(serveurPool);
        myThread.start();
    }
}
