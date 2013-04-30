/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import EasyDate.EasyDate;
import Util.Parametres;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.calendar.Gregorian;


public class TestDate {
    public static void main(String[] args){
        DbMySql mysql = new DbMySql("localhost", "3306", "root", "", "croixrouge");
        //requete select 1
        try {
            mysql.lockTable(new String[]{"grilleHoraire"});
            String request = "select dateCréation FROM grilleHoraire where idGrilleHoraire = ?";
            int idGrilleHoraire = 1;

            Parametres params = new Parametres();
            params.addInt(idGrilleHoraire);

            ResultSet rs = mysql.pSelect(request, params);
            while(rs.next()){
                Timestamp timestamp = rs.getTimestamp("dateCréation");
                String aff = EasyDate.getDateHour(timestamp);
                System.out.println(aff);
                aff = EasyDate.getDateOnly(timestamp);
                System.out.println(aff);
                aff = EasyDate.getHourOnly(timestamp);
                System.out.println(aff);
            }
            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
            mysql.rollback();
        }

        System.out.println("30/04/2013: " + EasyDate.isValidDate("30/04/2013", null));
        System.out.println("3/04/2013: " + EasyDate.isValidDate("3/04/2013", null));
        System.out.println("4/2013: " + EasyDate.isValidDate("4/2013", null));
        System.out.println(" :" + EasyDate.isValidDate(" ", null));
        System.out.println("40/06/2013: " + EasyDate.isValidDate("40/06/2013", null));
        System.out.println("30-04-2013: " + EasyDate.isValidDate("30-04-2013", null));
        System.out.println("29/02/2013: " + EasyDate.isValidDate("29/02/2013", null));
        System.out.println("29/02/2012: " + EasyDate.isValidDate("29/02/2012", null));
        System.out.println("aa/aa/2012: " + EasyDate.isValidDate("aa/aa/2012", null));
        System.out.println("30/04/2013 12:36: " + EasyDate.isValidDate("30/04/2013 12:36", "dd/MM/yyyy HH:mm"));
        System.out.println("30/04/2013 12:72: " + EasyDate.isValidDate("30/04/2013 12:72", "dd/MM/yyyy HH:mm"));
    }



}
