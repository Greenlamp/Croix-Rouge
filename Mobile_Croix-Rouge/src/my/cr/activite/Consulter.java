/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.cr.SSL.NetworkClientSSL;
import my.cr.lib.MyApplication;
import my.cr.lib.Util;

/**
 *
 * @author Greenlamp
 */
public class Consulter extends Activity {

    /**
     * Called when the activity is first created.
     */
    String login;
    String password;
    Consulter consulter;
    LinkedList<String[]> data;
    NetworkClientSSL socket;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.consulter);
        Bundle b = getIntent().getExtras();
        MyApplication global = (MyApplication)getApplication();
        socket = global.getSocket();

        data = getListe();
        String[] StringArray = Util.convertToStringArray(data);
        Intent intent = new Intent("android.intent.action.AFFICHAGE");
        intent.putExtra("liste", StringArray);
        startActivity(intent);
        finish();
    }

    private LinkedList<String[]> getListe() {
        data = new LinkedList<String[]>();
        try {
            PacketCom packet = new PacketCom("GET_VOLONTAIRE_ALL", null);
            socket.send(packet);
            PacketCom reponse = socket.receive();
            if(reponse.getType().equals("GET_VOLONTAIRE_ALL_OUI")){
                LinkedList<String[]> listeVolontaires = (LinkedList<String[]>) reponse.getObjet();
                for(String[] elm : listeVolontaires){
                    data.add(new String[] {elm[0], elm[1]});
                }
            }
            return data;
        } catch (Exception ex) {
            Logger.getLogger(Consulter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
