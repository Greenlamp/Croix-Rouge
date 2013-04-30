/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Behind;

import PacketCom.PacketCom;
import PacketCom.Protocolable;
import Protocole.ProtocoleServeur;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NetworkServer {
    private ServerSocket socket;
    private Socket socketService;
    Protocolable protocole;
    boolean connected;



    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public NetworkServer(int port) throws Exception{
        this.protocole = new ProtocoleServeur();
        Class[] interfaces = this.protocole.getClass().getInterfaces();
        boolean protocolable = false;
        for(Class classe : interfaces){
            if(classe.getName().equals(Protocolable.class.getName())){
                protocolable = true;
            }
        }
        if(!protocolable){
            throw new Exception("Pas de protocole conforme.");
        }

        try {
            this.socket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public NetworkServer(int port, String nomProtocole) throws Exception{
        Class classProtocole = Class.forName(nomProtocole);
        protocole = (Protocolable) classProtocole.newInstance();
        Class[] interfaces = this.protocole.getClass().getInterfaces();
        boolean protocolable = false;
        for(Class classe : interfaces){
            if(classe.getName().equals(Protocolable.class.getName())){
                protocolable = true;
            }
        }
        if(!protocolable){
            throw new Exception("Pas de protocole conforme.");
        }

        try {
            this.socket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NetworkServer(Socket socketService){
        this.protocole = new ProtocoleServeur();
        this.socketService = socketService;
        this.connected = true;
    }



    /**************************************************************************/
    /*Methodes Connexion*/
    /**************************************************************************/
    public boolean accept(){
        System.out.println("En attente d'un client");
        try {
            this.setSocketService(this.getSocket().accept());
            System.out.println("Client connecté");
            this.connected = true;
            return true;
        } catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
            this.connected = false;
            return false;
        }
    }

    public void disconnect(){
        if(!this.getSocket().isClosed()){
            try {
                this.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void disconnectClient(){
        if(!this.getSocketService().isClosed()){
            try {
                this.getSocketService().close();
            } catch (IOException ex) {
                Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



    /**************************************************************************/
    /*Senders - Receivers*/
    /**************************************************************************/
    public void send(PacketCom packet) throws IOException, Exception{
        if(this.isConnected()){
            OutputStream os = this.getSocketService().getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject((Object)packet);
        }else{
            throw new Exception("Socket non connectée");
        }
    }

    public PacketCom receive() throws IOException, ClassNotFoundException, Exception{
        if(this.isConnected()){
            try{
                InputStream is = this.getSocketService().getInputStream();
                ObjectInputStream in = new ObjectInputStream(is);
                Object received = in.readObject();
                return this.protocole.messageFromClient(received);
            }catch(Exception ex){
                //Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Client déconnecté");
                throw new Exception("Client fermé");
            }
        }else{
            throw new Exception("Socket non connectée");
        }
    }



    /**************************************************************************/
    /*Getters - Setters*/
    /**************************************************************************/
    public boolean isConnected(){
        return this.connected;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    public Socket getSocketService() {
        return socketService;
    }

    public void setSocketService(Socket socketService) {
        this.socketService = socketService;
    }

}
