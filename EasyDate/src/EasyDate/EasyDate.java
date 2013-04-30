/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyDate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EasyDate {
    public static String getDateHour(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String out = sdf.format(new Date(timestamp.getTime()));
        return out;
    }
    public static String getDateOnly(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String out = sdf.format(new Date(timestamp.getTime()));
        return out;
    }
    public static String getHourOnly(Timestamp timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String out = sdf.format(new Date(timestamp.getTime()));
        return out;
    }


    public static String getDateHour(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String out = sdf.format(date);
        return out;
    }
    public static String getDateOnly(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String out = sdf.format(date);
        return out;
    }
    public static String getHourOnly(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String out = sdf.format(date);
        return out;
    }


    public static String getDateHour(java.sql.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String out = sdf.format(new Date(date.getTime()));
        return out;
    }
    public static String getDateOnly(java.sql.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String out = sdf.format(new Date(date.getTime()));
        return out;
    }
    public static String getHourOnly(java.sql.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String out = sdf.format(new Date(date.getTime()));
        return out;
    }


    public static String getDateHour(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String out = null;
        try {
            out = sdf.format(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(EasyDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    public static String getDateOnly(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String out = null;
        try {
            out = sdf.format(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(EasyDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }
    public static String getHourOnly(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String out = null;
        try {
            out = sdf.format(sdf.parse(date).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(EasyDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public static boolean isValidDate(String date, String format){
        if(format == null){
            format = "dd/MM/yyyy";
        }
        Date dateC = null;
        String after = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(true);
            dateC = sdf.parse(date);
            after = sdf.format(dateC);
        } catch (Exception ex) {
            return false;
        }
        
        try{
            if(!date.equals(after)){
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
