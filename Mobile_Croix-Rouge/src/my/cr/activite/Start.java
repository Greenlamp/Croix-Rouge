/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.cr.activite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greenlamp
 */
public class Start extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.start);
        createThreadTimeOut();
    }
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    private void createThreadTimeOut() {
        Thread th = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    startActivity(new Intent("android.intent.action.CONNEXION"));
                }
            }
        };
        th.start();
    }
}
