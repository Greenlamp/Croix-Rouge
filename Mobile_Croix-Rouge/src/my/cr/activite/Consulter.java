/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.cr.lib.Vars;
import my.cr.network.NetworkClient;

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

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.consulter);
        Bundle b = getIntent().getExtras();
        login = b.getString("login");
        password = b.getString("password");
        data = getListe();
        GridView gridview = (GridView) findViewById(R.id.Ggrid);
        ArrayAdapter<String> aa = getArrayAdapter(data);
        gridview.setAdapter(aa);
        addClickListener(gridview);
    }

    private ArrayAdapter<String> getArrayAdapter(LinkedList<String[]> data) {
        int size = data.size();
        size *= 2;
        String[] items = new String[size];
        int i=0;
        for(String[] elm : data){
            items[i] = elm[0];
            i++;
            items[i] = elm[1];
            i++;
        }
        return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
    }

    private LinkedList<String[]> getListe() {
        LinkedList<String[]> data = new LinkedList<String[]>();
        data.add(new String[] {"nom", "prenom"});
        NetworkClient socket = new NetworkClient(Vars.HOST, Vars.PORT, true);
        String[] infos = {login, password};
        PacketCom packet = new PacketCom("LOGIN", (Object)infos);
        socket.send(packet);
        try {
            PacketCom reponse = socket.receive();
            if(reponse.getType().equals("LOGIN_OUI")){
                socket.send(new PacketCom("GET_MY_DROITS", null));
                reponse = socket.receive();
                if(reponse.getType().equals("GET_MY_DROITS_OUI")){
                    packet = new PacketCom("GET_VOLONTAIRE_ALL", null);
                    socket.send(packet);
                    reponse = socket.receive();
                    if(reponse.getType().equals("GET_VOLONTAIRE_ALL_OUI")){
                        LinkedList<String[]> listeVolontaires = (LinkedList<String[]>) reponse.getObjet();
                        for(String[] elm : listeVolontaires){
                            data.add(new String[] {elm[0], elm[1]});
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Consulter.class.getName()).log(Level.SEVERE, null, ex);
        }
        socket.disconnect();
        return data;
    }

    private void addClickListener(GridView gridview) {
        consulter = this;
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                int line = ((i - (i%2))/2);
                if(line == 0){
                    return;
                }
                String nom = data.get(line)[0];
                String prenom = data.get(line)[1];
            }
        });
    }
}
