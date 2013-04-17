/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import java.beans.Beans;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestAccess {
    public static void main(String[] args){
        try{
            Jdbc_MySQL dbSql = null;
            dbSql = (Jdbc_MySQL) Beans.instantiate(null, "Database.Jdbc_MySQL");
            dbSql.init("localhost", "3306", "root", "", "croixrouge");

            //Count
            System.out.println("==Count==");
            System.out.println("Count: " + dbSql.count("administration"));

            //Select
            System.out.println("==Select==");
            String request = "Select * from administration";
            ResultSet tuples = (ResultSet)dbSql.select(request);
            while(tuples.next()){
                String login = tuples.getString("login");
                String password = tuples.getString("password");
                System.out.println(login + " - " + password);
            }
            dbSql.closeStatement();

            System.out.println("==Update==");
            request = "Update administration SET login = 'admin' Where login = 'blabla'";
            dbSql.update(request);
            request = "Select * from administration";
            tuples = (ResultSet)dbSql.select(request);
            while(tuples.next()){
                String login = tuples.getString("login");
                String password = tuples.getString("password");
                System.out.println(login + " - " + password);
            }
            dbSql.closeStatement();

        } catch (Exception ex) {
            Logger.getLogger(TestAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
