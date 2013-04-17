/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Behind;

import PacketCom.PacketCom;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Traitement implements Runnable{
    private NetworkServer socket;



    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public Traitement(NetworkServer socket){
        this.socket = socket;
    }



    /**************************************************************************/
    /*Runnable*/
    /**************************************************************************/
    @Override
    public void run() {
        while(this.socket.isConnected()){
            try {
                PacketCom messageToClient = this.socket.receive();
                if(messageToClient != null){
                    this.socket.send(messageToClient);
                }
            } catch (IOException ex) {
                Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                return;
            } catch (Exception ex) {
                return;
            }
        }
    }



    /**************************************************************************/
    /*Getters - Setters*/
    /**************************************************************************/
    public NetworkServer getSocket() {
        return socket;
    }

    public void setSocket(NetworkServer socket) {
        this.socket = socket;
    }
}
