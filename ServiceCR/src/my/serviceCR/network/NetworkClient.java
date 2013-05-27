/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.serviceCR.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.serviceCR.protocole.ProtocoleClient;


public class NetworkClient {
    private Socket socket;
    ProtocoleClient protocole;
    private String host;
    private int port;

    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public NetworkClient(String host, int port, boolean toConnect){
        protocole = new ProtocoleClient();
        try {
            if(toConnect){
                InetAddress ip = InetAddress.getByName(host);
                socket = new Socket(ip, port);
            }else{
                this.host = host;
                this.port = port;
            }
        } catch (Exception ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NetworkClient(Socket socket){
        protocole = new ProtocoleClient();
        this.socket = socket;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public void connect(){
        try {
            InetAddress ip = InetAddress.getByName(this.host);
            socket = new Socket(ip, this.port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isConnected(){
        if(socket != null){
            return true;
        }else{
            return false;
        }
    }

    public void disconnect(){
        if(this.isConnected()){
            try {
                socket.close();
                socket = null;
                System.out.println("Déconnection réussie");
            } catch (IOException ex) {
                Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**************************************************************************/
    /*Senders - Receivers*/
    /**************************************************************************/
    public void send(PacketCom packet){
        if(this.isConnected()){
            try {
                OutputStream os = this.socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject((Object)packet);
            } catch (IOException ex) {
                Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Socket non connectée");
        }
    }

    public PacketCom receive() throws Exception{
        try{
            InputStream is = this.socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Object objet = (Object)ois.readObject();
            PacketCom MessageFromServer = protocole.messageFromServer(objet);
            return MessageFromServer;
        }catch(Exception ex){
            this.disconnect();
            throw (Exception)ex;
        }
    }
}
