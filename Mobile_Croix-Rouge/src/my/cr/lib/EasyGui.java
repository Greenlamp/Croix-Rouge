/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.lib;

import android.view.View;
import android.widget.TextView;


public class EasyGui{

    public static String getTvString(View view) {
        TextView tv = (TextView) view;
        return tv.getText().toString();
    }

    public static int getTvInt(View view) {
        TextView tv = (TextView) view;
        String string = tv.getText().toString();
        int nb = -1;
        try{
            nb = Integer.parseInt(string);
        }catch(Exception ex){
            nb = -1;
        }
        return nb;
    }

}
