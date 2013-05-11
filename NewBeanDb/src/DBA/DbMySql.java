/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DBA;

import FileAccess.FileAccess;
import Util.Parametres;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbMySql {
    private String host;
    private String port;
    private String login;
    private String password;
    private String dbName;
    private boolean connected;
    private boolean transacted;

    private Connection connection;

    public DbMySql(String host, String port, String login, String password, String dbName) {
        setHost(host);
        setPort(port);
        setLogin(login);
        setPassword(password);
        setDbName(dbName);
        setConnected(false);
        setTransacted(false);

        connect();

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException ex) {
            Logger.getLogger(DbMySql.class.getName()).log(Level.SEVERE, null, ex);
            try {
                disconnect();
            } catch (Exception ex1) {
                Logger.getLogger(DbMySql.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void startpTransaction() throws Exception{
        if(isTransacted()){
            throw new Exception("Transaction déja en cours");
        }else if(!isConnected()){
            throw new Exception("Connextion échoué");
        }else{
            setTransacted(true);
        }
    }

    public ResultSet pSelect(String request, Parametres params) throws Exception{
        if(!isTransacted()){
            startpTransaction();
        }
        PreparedStatement ps = connection.prepareStatement(request);
        Parametres.setParametres(ps, params);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public void pUpdate(String request, Parametres params) throws Exception{
        if(!isTransacted()){
            startpTransaction();
        }
        PreparedStatement ps = connection.prepareStatement(request);
        Parametres.setParametres(ps, params);
        ps.executeUpdate();
    }

    public void commit() {
        try {
            if(isTransacted()){
                pSelect("COMMIT", null);
                connection.commit();
                unlockTable();
                setTransacted(false);
            }
        } catch (Exception ex) { }
    }

    public void rollback() {
        try {
            if(isTransacted()){
                connection.rollback();
                unlockTable();
                setTransacted(false);
            }
        } catch (Exception ex) { }
    }

    private void connect() {
        try {
            String url = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDbName();
            String driver = FileAccess.getConfig("configs", "DRIVER_MYSQL");
            Class.forName(driver);
            setConnection(DriverManager.getConnection(url, getLogin(), getPassword()));
            setConnected(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbMySql.class.getName()).log(Level.SEVERE, null, ex);
            setConnected(false);
        }
    }

    public void disconnect() throws Exception {
        if(isTransacted()){
            throw new Exception("Transaction en cours");
        }else if(!isConnected()){
            throw new Exception("Connexion déja fermée");
        }else if(isConnected()){
            connection.close();
        }
    }

    public void lockTable(String[] tables) throws Exception {
        String request = "LOCK TABLES ";
        int count = tables.length;
        int i = 0;
        for(String table : tables){
            if(i == count - 1){
                request += table + " WRITE";
            }else{
                request += table + " WRITE, ";
            }
            i++;
        }
        pUpdate(request, null);
    }

    public void unlockTable() throws Exception {
        String request = "UNLOCK TABLES";
        pUpdate(request, null);
    }




    /*----------*/
    /*GET/SET   */
    /*----------*/
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isTransacted() {
        return transacted;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
