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
import my.cr.SSL.NetworkClientSSL;
import my.cr.lib.EasyAlert;
import my.cr.lib.MyApplication;
import my.cr.lib.Util;

/**
 *
 * @author Greenlamp
 */
public class Recherche extends Activity {

    /**
     * Called when the activity is first created.
     */
    String login, password;
    NetworkClientSSL socket;
    LinkedList<Critere> listeCriteres;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.recherche);
        Bundle b = getIntent().getExtras();
        try{
            MyApplication global = (MyApplication)getApplication();
            socket = global.getSocket();
            comboDateNaissance();
            addListener();
        } catch (Exception ex) {
            Logger.getLogger(Recherche.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        CheckBox cDateNaissance = (CheckBox)findViewById(R.id.CdateNaissance);
        if(cDateNaissance.isChecked()){
            Spinner Gjour = (Spinner)findViewById(R.id.Gjour);
            Spinner Gmois = (Spinner)findViewById(R.id.Gmois);
            Spinner Gannee = (Spinner)findViewById(R.id.Gannee);

            String jour = Gjour.getSelectedItem().toString();
            String mois = Gmois.getSelectedItem().toString();
            String annee = Gannee.getSelectedItem().toString();

            String dateNaissance = jour + "/" + mois + "/" + annee;
            Log.e("mylog: ", "dateNaissance: " + dateNaissance);

            listeCriteres.add(new Critere(cpt, "dateNaissance", dateNaissance));
            cpt++;
        }

        CheckBox GdotCp = (CheckBox)findViewById(R.id.GdotCp);
        if(GdotCp.isChecked()){
            EditText GcodePostal = (EditText)findViewById(R.id.GcodePostal);
            String recup = GcodePostal.getText().toString();
            if(recup != null && !recup.equals("")){
                try{
                    Integer.parseInt(recup);
                    listeCriteres.add(new Critere(cpt, "codePostal", GcodePostal.getText().toString()));
                    cpt++;
                }catch(Exception ex){
                    EasyAlert.alert(this, "Erreur", "CodePostal ignoré car incorrect", null);
                }
            }
        }

        CheckBox Gamu = (CheckBox)findViewById(R.id.Gamu);
        if(Gamu.isChecked()){
            listeCriteres.add(new Critere(cpt, "permis", "amu"));
            cpt++;
        }

        CheckBox Gtms = (CheckBox)findViewById(R.id.Gtms);
        if(Gtms.isChecked()){
            listeCriteres.add(new Critere(cpt, "permis", "tms"));
            cpt++;
        }

        CheckBox Gpermanent = (CheckBox)findViewById(R.id.Gpermanent);
        if(Gtms.isChecked()){
            listeCriteres.add(new Critere(cpt, "permanent", "permanent"));
            cpt++;
        }

        CheckBox Gvolontaire = (CheckBox)findViewById(R.id.Gvolontaire);
        if(Gtms.isChecked()){
            listeCriteres.add(new Critere(cpt, "permanent", "volontaire"));
            cpt++;
        }


        if(listeCriteres.size() > 0){
            doSearch();
        }else{
            Intent intent = new Intent("android.intent.action.CONSULTER");
            startActivity(intent);
        }
    }

    private void doSearch() {
        Object[] data = {listeCriteres, null};
        PacketCom packet = new PacketCom("RECHERCHE", (Object)data);
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

            String[] StringArray = Util.convertToStringArray(listeVolontaire);;
            intent.putExtra("liste", StringArray);
            startActivity(intent);
        }
    }
}
