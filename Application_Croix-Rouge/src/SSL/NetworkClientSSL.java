/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SSL;

import ContainerSSL.SSLContainer;
import FileAccess.FileAccess;
import Protocole.ProtocoleClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import my.cr.PacketCom.PacketCom;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class NetworkClientSSL {
    private SSLSocket socket;
    ProtocoleClient protocole;
    private String host;
    private int port;
    SSLContainer sslContainer;

    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public NetworkClientSSL(String host, int port, boolean toConnect){
        this.sslContainer = getDataSSL();
        protocole = new ProtocoleClient();
        try {
            if(toConnect){
                InetAddress ip = InetAddress.getByName(host);
                socket = getSocket(host, port);
            }else{
                this.host = host;
                this.port = port;
            }
        } catch (Exception ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect(){
        try {
            InetAddress ip = InetAddress.getByName(this.host);
            socket = getSocket(this.host, this.port);
        } catch (Exception ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
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

    private SSLSocket getSocket(String host, int port) throws Exception{
        BouncyCastleProvider bc = new BouncyCastleProvider();
        Security.addProvider(bc);
        SSLContext SslC = null;
        SslC = SSLContext.getInstance("SSLv3");
        KeyManagerFactory kmf = null;
        kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(sslContainer.getKeystoreServer(), sslContainer.getPass().toCharArray());
        TrustManagerFactory tmf = null;
        tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(sslContainer.getKeystoreServer());
        SslC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory SslSFac = SslC.getSocketFactory();
        return (SSLSocket) SslSFac.createSocket(InetAddress.getByName(host), port);
    }

    private SSLContainer getDataSSL() {
        String path_keystore_client = FileAccess.getConfig("configs", "KS_CLIENT");
        String pass_keystore_client = FileAccess.getConfig("configs", "PASS");
        File path = FileAccess.getFile(path_keystore_client);


        KeyStore ks = null;
        X509Certificate certificat = null;
        PrivateKey privateKey = null;
        SSLContainer sslContainer = null;

        try {
            ks = KeyStore.getInstance("JKS");

            ks.load(new FileInputStream(path), pass_keystore_client.toCharArray());
            certificat = (X509Certificate)ks.getCertificate("client");
            certificat.checkValidity();

            privateKey = (PrivateKey) ks.getKey("client", pass_keystore_client.toCharArray());

            sslContainer = new SSLContainer();
            sslContainer.setKeystoreServer(ks);
            sslContainer.setCertificatServer(certificat);
            sslContainer.setPrivateKey(privateKey);
            sslContainer.setPass(pass_keystore_client);


        } catch (KeyStoreException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateExpiredException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateNotYetValidException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sslContainer;
    }
}
