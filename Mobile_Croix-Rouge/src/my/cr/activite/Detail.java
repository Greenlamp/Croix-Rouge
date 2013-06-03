/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.cr.SSL.NetworkClientSSL;
import my.cr.lib.MyApplication;

/**
 *
 * @author Greenlamp
 */
public class Detail extends Activity {

    /**
     * Called when the activity is first created.
     */
    NetworkClientSSL socket;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.detail);
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        String prenom = b.getString("prenom");
        MyApplication global = (MyApplication)getApplication();
        socket = global.getSocket();
        String[] volontaire = getVolontaire(nom, prenom);
        try{
            if(volontaire != null && volontaire.length > 0){
                actualiserListe(volontaire);
            }
        }catch(Exception ex){
            Logger.getLogger(Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualiserListe(String[] volontaire) {
        TextView Gmatricule = (TextView)findViewById(R.id.Gmatricule);
        Gmatricule.setText(volontaire[0]);

        TextView Gnom = (TextView)findViewById(R.id.Gnom);
        Gnom.setText(volontaire[1]);

        TextView Gprenom = (TextView)findViewById(R.id.Gprenom);
        Gprenom.setText(volontaire[2]);

        TextView Gsexe = (TextView)findViewById(R.id.Gsexe);
        Gsexe.setText(volontaire[3]);

        TextView GdateNaissance = (TextView)findViewById(R.id.GdateNaissance);
        GdateNaissance.setText(volontaire[4]);

        TextView Gadresse = (TextView)findViewById(R.id.Gadresse);
        Gadresse.setText(volontaire[5]);

        TextView Gadresse2 = (TextView)findViewById(R.id.Gadresse2);
        Gadresse2.setText(volontaire[6]);

        TextView Gemail = (TextView)findViewById(R.id.Gemail);
        Gemail.setText(volontaire[7]);

        TextView Ggsm = (TextView)findViewById(R.id.Ggsm);
        Ggsm.setText(volontaire[8]);
    }

    private String[] getVolontaire(String nom, String prenom) {
        String[] data = null;
        try {
            PacketCom packet = new PacketCom("GET_DETAILS_ANDROID", new String[] {nom, prenom});
            socket.send(packet);
            PacketCom reponse = socket.receive();
            if(reponse.getType().equals("GET_DETAILS_ANDROID_OUI")){
                data = (String[]) reponse.getObjet();
            }
        } catch (Exception ex) {
            Logger.getLogger(Detail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
