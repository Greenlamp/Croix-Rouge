/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCheck;

import EasyDate.EasyDate;


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
        if(!EasyDate.isValidDate(date, format)){
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkMail(String value) {
        return true;
    }
    public static boolean checkCreditCard(String value){
        return true;
    }
}
