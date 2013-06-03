/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.cr.PacketCom.PacketCom;
import my.cr.SSL.NetworkClientSSL;
import my.cr.lib.EasyGui;
import my.cr.lib.MyApplication;
import my.cr.lib.Vars;

/**
 *
 * @author Greenlamp
 */
public class Connexion extends Activity {

    /**
     * Called when the activity is first created.
     */
    NetworkClientSSL socket;
    String login;
    String password;

    protected static final int TIMER_RUNTIME = 10000; // in ms --> 10s

    @Override
    protected void onResume() {
        super.onResume();
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.Gloading);
        progressBar.setVisibility(View.INVISIBLE);
        Button bConnexion = (Button)findViewById(R.id.Bconnexion);
        bConnexion.setEnabled(true);
        TextView Glogin = (TextView)findViewById(R.id.Glogin);
        TextView Gpassword = (TextView)findViewById(R.id.Gpassword);
        Glogin.setEnabled(true);
        Gpassword.setEnabled(true);

    }
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.connexion);
        //startService(new Intent(this, MyService.class));
        addListenerBoutonConnexion();
        MyApplication global = (MyApplication)getApplication();
        messageErreur(global.isError());
    }

    private void addListenerBoutonConnexion() {
        Button boutonConnexion = (Button)findViewById(R.id.Bconnexion);
        boutonConnexion.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                ProgressBar progressBar = (ProgressBar)findViewById(R.id.Gloading);
                progressBar.setVisibility(View.VISIBLE);
                Button bConnexion = (Button)findViewById(R.id.Bconnexion);
                bConnexion.setEnabled(false);
                TextView Glogin = (TextView)findViewById(R.id.Glogin);
                TextView Gpassword = (TextView)findViewById(R.id.Gpassword);
                Glogin.setEnabled(true);
                Gpassword.setEnabled(true);
                new Thread(){
                    @Override
                    public void run(){
                        actionConnexion();
                    }
                }.start();
            }
        });
    }

    private void actionConnexion() {
        login = EasyGui.getTvString(findViewById(R.id.Glogin));
        password = EasyGui.getTvString(findViewById(R.id.Gpassword));
        Log.e("myLog:", " R.raw.ks3: " + R.raw.ks3);
        socket = new NetworkClientSSL(Vars.HOST, Vars.PORT, true, this, R.raw.ks3);

        MyApplication global = (MyApplication)getApplication();
        global.setSocket(socket);

        String[] infos = {login, password};
        PacketCom packet = new PacketCom("LOGIN", (Object)infos);
        socket.send(packet);
        PacketCom reponse = null;
        try {
            reponse = socket.receive();
            traitementReponse(reponse);
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            global.setError(true);
            global.setMessage("Impossible de se connecter");
            Intent intent = new Intent("android.intent.action.CONNEXION");
            startActivity(intent);
            finish();
        }
    }

    private void traitementReponse(PacketCom reponse) {
        MyApplication global = (MyApplication)getApplication();
        String type = reponse.getType();
        if(type.equals("LOGIN_OUI")){
            socket.send(new PacketCom("GET_MY_DROITS", null));
            try {
                PacketCom retour = socket.receive();
                if(retour.getType().equals("GET_MY_DROITS_OUI")){
                    LinkedList<String> droits = (LinkedList<String>)retour.getObjet();
                    MyApplication test = (MyApplication)getApplication();
                    test.setDroits(droits);
                    Intent intent = new Intent("android.intent.action.MENU");
                    startActivity(intent);
                }
            } catch (Exception ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(type.equals("LOGIN_NON_USER")){
            socket.disconnect();
            global.setError(true);
            global.setMessage("Le login est incorrect");
            Intent intent = new Intent("android.intent.action.CONNEXION");
            startActivity(intent);
            finish();
        }else if(type.equals("LOGIN_NON_PASS")){
            socket.disconnect();
            global.setError(true);
            global.setMessage("Le password est incorrect");
            Intent intent = new Intent("android.intent.action.CONNEXION");
            startActivity(intent);
            finish();
        }else{
            String message = (String)reponse.getObjet();
            socket.disconnect();
            global.setError(true);
            global.setMessage(message);
            Intent intent = new Intent("android.intent.action.CONNEXION");
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //stopService(new Intent(this, MyService.class));
        finish();
    }

    private void messageErreur(boolean error) {
        MyApplication global = (MyApplication)getApplication();
        TextView Gerror = (TextView) findViewById(R.id.Gerror);
        if(error){
            Gerror.setText("Erreur: " + global.getMessage());
            Gerror.setVisibility(TextView.VISIBLE);
            global.setError(false);
        }else{
            Gerror.setVisibility(TextView.INVISIBLE);
        }
    }
}
