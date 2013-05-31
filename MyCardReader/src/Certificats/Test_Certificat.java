/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Certificats;

import be.belgium.eid.eidcommon.ByteConverter;
import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;
import be.belgium.eid.security.CertificateChain;
import be.belgium.eid.security.RNCertificate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test_Certificat {
    public static void main(String args[]) {
        BeID eID = new BeID(false);


        try {
            CertificateChain certChain = eID.getCertificateChain();
            RNCertificate rnCert = eID.getNationalRegisterCertificate();
            System.out.println("Verification de la validité du certificat ...");
            //Crl = liste des identifiants des certificats qui ont été révoqué ou ne sont plus valables
            //OCSP = permet de vérifier la validité d'un certificat X509
            boolean verifyCRL = eID.verifyCRL(certChain, rnCert);
            //boolean verifyOCSP = eID.verifyOCSP(certChain);
            if(verifyCRL){
                System.out.println("Certificats validé");

                //Challenge sans arguments
                byte[] challenge = eID.getChallenge();
                byte[] challengeReponse = eID.getChallengeResponse(challenge);

                System.out.println("challenge: " + ByteConverter.hexify(challenge));
                System.out.println("challengeReponse: " + ByteConverter.hexify(challengeReponse));

                //Challenge avec arguments
                String argument = "coucou";

                challenge = null;
                challengeReponse = null;
                challenge = ByteConverter.unhexify(argument);
                challengeReponse = eID.getChallengeResponse(challenge);

                System.out.println("Pour le challenge " + argument + ", la réponse est: " + ByteConverter.hexify(challengeReponse));
            }else{
                System.out.println("les certificats sont invalides ou révoqué");
            }
        } catch (EIDException ex) {
            Logger.getLogger(Test_Certificat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Test_Certificat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
