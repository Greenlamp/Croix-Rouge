/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCheck;

import EasyDate.EasyDate;
import javax.swing.JComboBox;


public class EasyCheck {
    public static boolean checkString(String value) {
        if(value == null || value.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkInt(int value) {
        if(value == -1){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkDate(String date, String format) {
        if(date.contains("-")){
            return true;
        }
        if(!EasyDate.isValidDate(date, format)){
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkDate(JComboBox Gjour, JComboBox Gmois, JComboBox Gannee) {
        String jourDebut = Gjour.getSelectedItem().toString();
        String moisDebut = Gmois.getSelectedItem().toString();
        String anneeDebut = Gannee.getSelectedItem().toString();
        String dateDebut = jourDebut + "/" + moisDebut + "/" + anneeDebut;
        return EasyCheck.checkDate(dateDebut, null);
    }

    public static boolean checkMail(String value) {
        return true;
    }
    public static boolean checkCreditCard(String value){
        return true;
    }
}
