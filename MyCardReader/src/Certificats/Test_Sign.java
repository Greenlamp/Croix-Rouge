/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Certificats;

import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.eidlib.BeID.SignatureType;
import be.belgium.eid.exceptions.EIDException;
import be.belgium.eid.security.AuthenticationCertificate;
import be.belgium.eid.security.CACertificate;
import be.belgium.eid.security.CertificateChain;
import be.belgium.eid.security.RNCertificate;
import be.belgium.eid.security.RootCertificate;
import be.belgium.eid.security.SignatureCertificate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test_Sign {
    public static void main(String args[]) {
        BeID eID = new BeID(false);
        /*try {
            //Récupération des certificats
            CertificateChain certChain = eID.getCertificateChain();
            AuthenticationCertificate authenticationCert = certChain.getAuthenticationCert();
            CACertificate certificateAuthorityCert = certChain.getCertificateAuthorityCert();
            RootCertificate rootCert = certChain.getRootCert();
            SignatureCertificate signatureCert = certChain.getSignatureCert();

            //Verify verifie si il est soit
            //-Valide
            //-Pas encore valide
            //-Expiré
            authenticationCert.verify();
            X509Certificate x509authenticationCert = authenticationCert.getX509Certificate();
            PublicKey authenticationCertPublicKey = x509authenticationCert.getPublicKey();
            System.out.println("PublicKey authenticationCert: " + authenticationCertPublicKey);
            System.out.println("Issuer: " + x509authenticationCert.getIssuerDN().getName());
            System.out.println("\n");


            certificateAuthorityCert.verify();
            X509Certificate x509certificateAuthorityCert = certificateAuthorityCert.getX509Certificate();
            PublicKey certificateAuthorityCertPublicKey = x509certificateAuthorityCert.getPublicKey();
            System.out.println("PublicKey certificateAuthorityCert: " + certificateAuthorityCertPublicKey);
            System.out.println("Issuer: " + x509certificateAuthorityCert.getIssuerDN().getName());
            System.out.println("\n");

            rootCert.verify();
            X509Certificate x509rootCert = rootCert.getX509Certificate();
            PublicKey rootCertPublicKey = x509rootCert.getPublicKey();
            System.out.println("PublicKey x509rootCert: " + rootCertPublicKey);
            System.out.println("Issuer: " + x509rootCert.getIssuerDN().getName());
            System.out.println("\n");

            signatureCert.verify();
            X509Certificate x509signatureCert = signatureCert.getX509Certificate();
            PublicKey signatureCertPublicKey = x509signatureCert.getPublicKey();
            System.out.println("PublicKey x509signatureCert: " + signatureCertPublicKey);
            System.out.println("Issuer: " + x509signatureCert.getIssuerDN().getName());
            System.out.println("\n");

            RNCertificate rnCert = eID.getNationalRegisterCertificate();
            rnCert.verify();
            boolean verifyCRL = eID.verifyCRL(certChain, rnCert);
            boolean verifyOCSP = eID.verifyOCSP(certChain);

        } catch (EIDException ex) {
            Logger.getLogger(Test_Sign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Test_Sign.class.getName()).log(Level.SEVERE, null, ex);
        }*/



        //Signature d'un fichier avec vérification de la signature.
        File file = new File("c:/test.csv");

        try {
            byte[] signature = eID.generateSignature(FileToByte(file), "1608", SignatureType.AUTHENTICATIONSIG);

            boolean verifySignature = eID.verifySignature(FileToByte(file), signature, SignatureType.AUTHENTICATIONSIG);
            if(verifySignature){
                System.out.println("Signature correct");
            }else{
                System.out.println("Signature incorrect");
            }
        } catch (EIDException ex) {
            Logger.getLogger(Test_Sign.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] FileToByte(File fichier){
        if(fichier == null){
            return null;
        }
        int length = (int) fichier.length();
        byte[] data = new byte[length];
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichier));
            bis.read(data, 0, length);
            return data;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test_Sign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test_Sign.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
