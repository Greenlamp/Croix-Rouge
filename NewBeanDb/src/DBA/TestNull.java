/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBA;

import Util.Parametres;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestNull {
    public static void main(String[] args){
        DbMySql mysql = new DbMySql("localhost", "3306", "root", "", "croixrouge");
        try {
            mysql.lockTable(new String[]{"Utilisateurs"});
            String request = "INSERT INTO Utilisateurs(login, password, connected) VALUES(?, ?, ?)";
            String login = "a";
            String password = "a";

            Parametres params = new Parametres();
            params.addString(login);
            params.addNull();
            params.addNull();
            //params.addString(password);

            mysql.pUpdate(request, params);

            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
            mysql.rollback();
        }
    }
}
