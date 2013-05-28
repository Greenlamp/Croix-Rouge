/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ContainerSSL;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;


public class SSLContainer {
    private KeyStore keystore;
    private X509Certificate certificat;
    private PrivateKey privateKey;
    private String pass;

    public SSLContainer(){
        setKeystoreServer(null);
        setCertificatServer(null);
        setPrivateKey(null);
        setPass(null);
    }

    public X509Certificate getCertificatServer() {
        return certificat;
    }

    public void setCertificatServer(X509Certificate certificatServer) {
        this.certificat = certificatServer;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public KeyStore getKeystoreServer() {
        return keystore;
    }

    public void setKeystoreServer(KeyStore keystoreServer) {
        this.keystore = keystoreServer;
    }
}