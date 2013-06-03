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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greenlamp
 */
public class Affichage extends Activity {

    /**
     * Called when the activity is first created.
     */
    String[] listeVolontaire;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.affichage);
        Bundle b = getIntent().getExtras();
        listeVolontaire = b.getStringArray("liste");
        if(listeVolontaire != null && listeVolontaire.length > 0){
            actualiserListe(listeVolontaire);
        }
        GridView tableau = (GridView)findViewById(R.id.Ggrid);
        addClickListener(tableau);
    }

    private void actualiserListe(String[] listeVolontaire) {
        GridView gridview = (GridView) findViewById(R.id.Ggrid);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeVolontaire);
        gridview.setAdapter(aa);
    }

    private void addClickListener(GridView gridview) {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Log.e("myLog:", "i: " + i);
                int line = i - (i%2);
                Log.e("myLog:", "line: " + line);
                if(line == 0){
                    return;
                }
                String nom = listeVolontaire[line];
                String prenom = listeVolontaire[line+1];
                try {
                    Intent intent = new Intent("android.intent.action.DETAIL");
                    intent.putExtra("nom", nom);
                    intent.putExtra("prenom", prenom);
                    startActivity(intent);
                } catch (Exception ex) {
                    Logger.getLogger(Affichage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
