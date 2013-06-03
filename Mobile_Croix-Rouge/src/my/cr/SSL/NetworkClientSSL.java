/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.SSL;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import my.cr.PacketCom.PacketCom;
import my.cr.activite.Menu;
import my.cr.activite.R;
import my.cr.protocole.ProtocoleClient;
import my.lib.ContainerSSL.SSLContainer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class NetworkClientSSL implements Serializable {
    private SSLSocket socket;
    ProtocoleClient protocole;
    private String host;
    private int port;
    SSLContainer sslContainer;
    Activity parent;
    int ks;

    /**************************************************************************/
    /*Constructeurs*/
    /**************************************************************************/
    public NetworkClientSSL(String host, int port, boolean toConnect, Activity parent, int ks){
        this.parent = parent;
        this.ks = ks;
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
            Toast.makeText(parent, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public NetworkClientSSL(Activity parent, SSLSocket sslSocket) {
        protocole = new ProtocoleClient();
        this.parent = parent;
        this.socket = sslSocket;
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
        if(getSocket() != null){
            return true;
        }else{
            return false;
        }
    }

    public void disconnect(){
        if(this.isConnected()){
            try {
                getSocket().close();
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
                OutputStream os = this.getSocket().getOutputStream();
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
            InputStream is = this.getSocket().getInputStream();
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
        SslC = SSLContext.getInstance("TLS");
        KeyManagerFactory kmf = null;
        kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(sslContainer.getKeystoreServer(), sslContainer.getPass().toCharArray());
        TrustManagerFactory tmf = null;
        tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(sslContainer.getKeystoreServer());
        SslC.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        SSLSocketFactory SslSFac = SslC.getSocketFactory();
        return (SSLSocket) SslSFac.createSocket(InetAddress.getByName(host), port);
    }

    private SSLContainer getDataSSL() {
        String sep = System.getProperty("file.separator");
        String path = System.getProperty("user.dir") + sep + "my.cr.ks" + sep + "KS_CR_Client.jks";

        String pass_keystore_client = "CroixR";
        InputStream is = parent.getResources().openRawResource(ks);
        try {
            Log.e("myLog:", " is.available(): " + is.available());
        } catch (IOException ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }


        KeyStore ks = null;
        X509Certificate certificat = null;
        PrivateKey privateKey = null;
        SSLContainer sslContainer = null;

        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());

            ks.load(is, pass_keystore_client.toCharArray());

            certificat = (X509Certificate)ks.getCertificate("client");
            certificat.checkValidity();

            privateKey = (PrivateKey) ks.getKey("client", pass_keystore_client.toCharArray());

            sslContainer = new SSLContainer();
            sslContainer.setKeystoreServer(ks);
            sslContainer.setCertificatServer(certificat);
            sslContainer.setPrivateKey(privateKey);
            sslContainer.setPass(pass_keystore_client);
        } catch (Exception ex) {
            Logger.getLogger(NetworkClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sslContainer;
    }

    public SSLSocket getSocket() {
        return socket;
    }
}
