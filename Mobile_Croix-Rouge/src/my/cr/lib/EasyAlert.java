/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class EasyAlert {
    public static void alert(Context applicationContext, String titre, String message, String msgButton) {
        AlertDialog alertDialog = new AlertDialog.Builder(applicationContext).create();
        if(titre == null){
            titre = "message";
        }
        alertDialog.setTitle(titre);
        alertDialog.setMessage(message);
        if(msgButton == null){
            msgButton = "OK";
        }
        alertDialog.setButton(msgButton, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {

            }
        });
        alertDialog.show();
    }

}
