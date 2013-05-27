/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.cr.lib.EasyAlert;
import my.cr.lib.EasyGui;
import my.cr.lib.Vars;
import my.cr.network.NetworkClient;
import my.cr.service.MyService;

/**
 *
 * @author Greenlamp
 */
public class Connexion extends Activity {

    /**
     * Called when the activity is first created.
     */
    NetworkClient socket;
    String login;
    String password;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.connexion);
        //startService(new Intent(this, MyService.class));
        addListenerBoutonConnexion();
    }

    private void addListenerBoutonConnexion() {
        Button boutonConnexion = (Button)findViewById(R.id.Bconnexion);
        boutonConnexion.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                actionConnexion();
            }
        });
    }

    private void actionConnexion() {
        login = EasyGui.getTvString(findViewById(R.id.Glogin));
        password = EasyGui.getTvString(findViewById(R.id.Gpassword));
        NetworkClient socket = new NetworkClient(Vars.HOST, Vars.PORT, true);
        String[] infos = {login, password};
        PacketCom packet = new PacketCom("LOGIN", (Object)infos);
        socket.send(packet);
        PacketCom reponse = null;
        try {
            reponse = socket.receive();
            traitementReponse(reponse);
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traitementReponse(PacketCom reponse) {
        String type = reponse.getType();
        if(type.equals("LOGIN_OUI")){
            Intent intent = new Intent("android.intent.action.MENU");
            intent.putExtra("login", login);
            intent.putExtra("password", password);
            startActivity(intent);
        }else if(type.equals("LOGIN_NON_USER")){
            EasyAlert.alert(this, "Erreur", "Le login est incorrect", null);
            socket.disconnect();
        }else if(type.equals("LOGIN_NON_PASS")){
            EasyAlert.alert(this, "Erreur", "Le password est incorrect", null);
            socket.disconnect();
        }else{
            String message = (String)reponse.getObjet();
            socket.disconnect();
            EasyAlert.alert(this, "Erreur", message, null);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this, MyService.class));
        finish();
    }
}
