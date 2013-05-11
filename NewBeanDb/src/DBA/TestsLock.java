/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBA;

import java.util.logging.Level;
import java.util.logging.Logger;


public class TestsLock {
    public static void main(String[] args){
        DbMySql mysql = new DbMySql("localhost", "3306", "root", "", "croixrouge");
        try {
            mysql.lockTable(new String[]{"sacs", "telephone"});
            mysql.commit();
        } catch (Exception ex) {
            Logger.getLogger(TestsLock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
