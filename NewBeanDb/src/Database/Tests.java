/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Util.Parametres;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tests {
    public static void main(String[] args){
        DbMySql mysql = new DbMySql("localhost", "3306", "root", "", "croixrouge");
        //requete select 1
        try {
            String request = "SELECT connected FROM utilisateurs WHERE login = ?";
            String login = "admin";

            Parametres params = new Parametres();
            params.addString(login);

            ResultSet rs = mysql.pSelect(request, params);
            while(rs.next()){
                int connected = rs.getInt("connected");
                System.out.println("connected: " + connected);
            }
            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
            mysql.rollback();
        }

        //requete update 1
        try {
            mysql.lockTable(new String[]{"utilisateurs", "ville"});
            String request = "UPDATE utilisateurs SET connected = ? WHERE login = ?";
            int connected = 1;
            String login = "admin";

            Parametres params = new Parametres();
            params.addInt(connected);
            params.addString(login);

            mysql.pUpdate(request, params);
            //requete update 2
            request = "UPDATE utilisateurs SET connected = ? WHERE login = ?";
            connected = 0;

            params = new Parametres();
            params.addInt(connected);
            params.addString(login);

            mysql.pUpdate(request, params);

            //requete update 3
            request = "UPDATE ville SET nomVille = ? WHERE idVille = ?";
            String nomVille = "dtc";
            int idVille = 4;

            params = new Parametres();
            params.addString(nomVille);
            params.addInt(idVille);

            mysql.pUpdate(request, params);

            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
            mysql.rollback();
        }

        //requete select 2
        try {
            String request = "SELECT connected FROM utilisateurs WHERE login = ?";
            String login = "admin";

            Parametres params = new Parametres();
            params.addString(login);

            ResultSet rs = mysql.pSelect(request, params);
            while(rs.next()){
                int connected = rs.getInt("connected");
                System.out.println("connected: " + connected);
            }
            mysql.commit();
        } catch (Exception ex) {
            mysql.rollback();
        }
        try {
            mysql.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
