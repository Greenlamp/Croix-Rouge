/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SSL;

import ContainerSSL.SSLContainer;
import FileAccess.FileAccess;
import Protocole.ProtocoleServeur;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import my.cr.PacketCom.PacketCom;
import my.cr.PacketCom.Protocolable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class NetworkServerSSL {
    private SSLServerSocket socket;
    private Socket socketService;
    Protocolable protocole;
    boolean connected;
    SSLContainer sslContainer;



    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public NetworkServerSSL(int port) throws Exception{
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

        this.sslContainer = getDataSSL();

        try {
            this.socket = getSSLServerSocket(port);
        } catch (Exception ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NetworkServerSSL(int port, String nomProtocole) throws Exception{
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

        this.sslContainer = getDataSSL();

        try {
            this.socket = getSSLServerSocket(port);
        } catch (Exception ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NetworkServerSSL(Socket socketService){
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
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
            this.connected = false;
            return false;
        }
    }

    public void disconnect(){
        if(!this.getSocket().isClosed()){
            try {
                this.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void disconnectClient(){
        if(!this.getSocketService().isClosed()){
            try {
                this.getSocketService().close();
            } catch (IOException ex) {
                Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
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
                //Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
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

    public SSLServerSocket getSocket() {
        return socket;
    }

    public void setSocket(SSLServerSocket socket) {
        this.socket = socket;
    }

    public Socket getSocketService() {
        return socketService;
    }

    public void setSocketService(Socket socketService) {
        this.socketService = socketService;
    }

    private SSLServerSocket getSSLServerSocket(int port) throws Exception{
        SSLContext SslC = null;
        SslC = SSLContext.getInstance("SSLv3");
        KeyManagerFactory kmf = null;
        kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(sslContainer.getKeystoreServer(), sslContainer.getPass().toCharArray());
        TrustManagerFactory tmf = null;
        tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(sslContainer.getKeystoreServer());
        SslC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLServerSocketFactory SslSFac = SslC.getServerSocketFactory();
        return (SSLServerSocket) SslSFac.createServerSocket(port);
    }

    private SSLContainer getDataSSL() {
        BouncyCastleProvider bc = new BouncyCastleProvider();
        Security.addProvider(bc);
        String path_keystore_server = FileAccess.getConfig("configs", "KS_SERVER");
        String pass_keystore_server = FileAccess.getConfig("configs", "PASS");
        File path = FileAccess.getFile(path_keystore_server);


        KeyStore ks = null;
        X509Certificate certificat = null;
        PrivateKey privateKey = null;
        SSLContainer sslContainer = null;

        try {
            ks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream(path), pass_keystore_server.toCharArray());
            certificat = (X509Certificate)ks.getCertificate("server");
            certificat.checkValidity();

            privateKey = (PrivateKey) ks.getKey("server", pass_keystore_server.toCharArray());

            sslContainer = new SSLContainer();
            sslContainer.setKeystoreServer(ks);
            sslContainer.setCertificatServer(certificat);
            sslContainer.setPrivateKey(privateKey);
            sslContainer.setPass(pass_keystore_server);


        } catch (KeyStoreException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateExpiredException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateNotYetValidException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(NetworkServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sslContainer;
    }

}
