/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBA;

import Util.Parametres;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestRollback {
    public static void main(String[] args){
        DbMySql mysql = new DbMySql("localhost", "3306", "root", "", "croixrouge");
        try {
            insertSac(mysql, 4);
            insertSac(mysql, 5);
            //throw new Exception("");
            insertSac(mysql, 6);
            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(TestRollback.class.getName()).log(Level.SEVERE, null, ex);
            mysql.rollback();
        }
    }

    private static void insertSac(DbMySql mysql, int sac) throws Exception {
        String request = "INSERT INTO sacs(numeroSac) VALUES(?)";
        Parametres params = new Parametres();
        params.addInt(sac);
        mysql.pUpdate(request, params);
    }
}
