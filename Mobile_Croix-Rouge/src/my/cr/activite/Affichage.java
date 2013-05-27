/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.LinkedList;
import my.cr.lib.EasyAlert;

/**
 *
 * @author Greenlamp
 */
public class Affichage extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.affichage);
        Bundle b = getIntent().getExtras();
        String[] listeVolontaire = b.getStringArray("liste");
        if(listeVolontaire != null && listeVolontaire.length > 0){
            actualiserListe(listeVolontaire);
        }
    }

    private void actualiserListe(String[] listeVolontaire) {
        GridView gridview = (GridView) findViewById(R.id.Ggrid);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeVolontaire);
        gridview.setAdapter(aa);
    }
}
