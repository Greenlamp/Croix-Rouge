/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.LibCritereAndroid.Recherche.Critere;
import my.LibCritereAndroid.Recherche.TupleRecherche;
import my.cr.PacketCom.PacketCom;
import my.cr.lib.EasyAlert;
import my.cr.lib.Vars;
import my.cr.network.NetworkClient;

/**
 *
 * @author Greenlamp
 */
public class Recherche extends Activity {

    /**
     * Called when the activity is first created.
     */
    String login, password;
    NetworkClient socket;
    LinkedList<Critere> listeCriteres;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.recherche);
        Bundle b = getIntent().getExtras();
        login = b.getString("login");
        password = b.getString("password");
        comboDateNaissance();
        addListener();
    }

    private void comboDateNaissance() {
        Spinner Gjour = (Spinner)findViewById(R.id.Gjour);
        Spinner Gmois = (Spinner)findViewById(R.id.Gmois);
        Spinner Gannee = (Spinner)findViewById(R.id.Gannee);

        ArrayAdapter<CharSequence> aaJour = ArrayAdapter.createFromResource(getBaseContext(), R.array.jours, android.R.layout.simple_spinner_item);
        aaJour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> aaMois = ArrayAdapter.createFromResource(getBaseContext(), R.array.mois, android.R.layout.simple_spinner_item);
        aaJour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        LinkedList<Integer> listeAnnee = new LinkedList<Integer>();
        Date today = new Date();
        int annee = Integer.parseInt(new SimpleDateFormat("yyyy").format(today));
        for(int i = annee-16; i>=1920; i--){
            listeAnnee.add(i);
        }
        ArrayAdapter<Integer> aaAnnee = new ArrayAdapter<Integer>(getBaseContext(), android.R.layout.simple_spinner_item, listeAnnee);
        aaAnnee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Gjour.setAdapter(aaJour);
        Gmois.setAdapter(aaMois);
        Gannee.setAdapter(aaAnnee);
    }

    private void addListener() {
        Button Grechercher = (Button)findViewById(R.id.Grechercher);
        Grechercher.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                traiterCritere();
            }
        });
    }

    public void traiterCritere(){
        listeCriteres = new LinkedList<Critere>();
        int cpt=0;

        CheckBox cNom = (CheckBox)findViewById(R.id.Cnom);
        if(cNom.isChecked()){
            EditText Gnom = (EditText)findViewById(R.id.Gnom);
            listeCriteres.add(new Critere(cpt, "nom", Gnom.getText().toString()));
            cpt++;
        }

        CheckBox cPrenom = (CheckBox)findViewById(R.id.Cprenom);
        if(cPrenom.isChecked()){
            EditText Gprenom = (EditText)findViewById(R.id.Gprenom);
            listeCriteres.add(new Critere(cpt, "prenom", Gprenom.getText().toString()));
            cpt++;
        }


        if(listeCriteres.size() > 0){
            envoyerDonnee();
        }
    }

    private void envoyerDonnee() {
        if(doLogin()){
            if(doDroit()){
                doSearch();
            }
        }
    }

    private boolean doLogin() {
        socket = new NetworkClient(Vars.HOST, Vars.PORT, true);
        String[] infos = {login, password};
        PacketCom packet = new PacketCom("LOGIN", (Object)infos);
        socket.send(packet);
        PacketCom reponse = null;
        try {
            reponse = socket.receive();
            if(reponse.getType().equals("LOGIN_OUI")){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean doDroit() {
        socket.send(new PacketCom("GET_MY_DROITS", null));
        PacketCom reponse = null;
        try {
            reponse = socket.receive();
            if(reponse.getType().equals("GET_MY_DROITS_OUI")){
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void doSearch() {
        PacketCom packet = new PacketCom("RECHERCHE", (Object)listeCriteres);
        socket.send(packet);
        PacketCom reponse = null;
        try {
            reponse = socket.receive();
            if(reponse.getType().equals("RECHERCHE_OUI")){
                TraitementResultat((LinkedList<TupleRecherche>) reponse.getObjet());
            }else{
                EasyAlert.alert(this, "ERREUR", (String)reponse.getObjet(), null);
            }
        } catch (Exception ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void TraitementResultat(LinkedList<TupleRecherche> liste) {
        if(liste.size() == 0){
            EasyAlert.alert(this, null, "Aucuns résultats", null);
            listeCriteres = null;
        }else{
            LinkedList<String[]> listeVolontaire = new LinkedList<String[]>();
            for(TupleRecherche elm : liste){
                String[] nomPrenom = {elm.getNom(), elm.getPrenom()};
                listeVolontaire.add(nomPrenom);
            }
            Intent intent = new Intent("android.intent.action.AFFICHAGE");

            String[] StringArray = convertToStringArray(listeVolontaire);
            intent.putExtra("liste", StringArray);
            startActivity(intent);
        }
    }

    private String[] convertToStringArray(LinkedList<String[]> data) {
        int size = data.size();
        size *= 2;
        size += 2;
        String[] items = new String[size];
        items[0] = "[NOM]";
        items[1] = "[PRENOM]";
        int i=2;
        for(String[] elm : data){
            items[i] = elm[0];
            i++;
            items[i] = elm[1];
            i++;
        }
        return items;
    }
}
