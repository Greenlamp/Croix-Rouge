/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import FileAccess.FileAccess;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Jdbc_MySQL extends Jdbc_dba{
    private String host = null;
    private String port = null;
    private String username = null;
    private String password = null;
    private String dbName = null;
    private String separator = null;
    
    public void init(String host, String port, String username, String password, String dbName) throws Exception{
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dbName = dbName;

        this.setUrl(buildUrl(this.host, this.port, this.dbName));
        this.setDriver(FileAccess.getConfig("configs", "DRIVER_MYSQL"));
        this.separator = FileAccess.getConfig("configs", "SEPARATOR");

        loadDriver();
        createConnection();
    }

    private String buildUrl(String host, String port, String dbName) {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }

    private void createConnection() throws SQLException{
        this.setConnection(DriverManager.getConnection(this.getUrl(), this.username, this.password));
    }

    public int count(String table) throws Exception, SQLException{
        int count = 0;
        if(this.isBusy()){
            throw(new Exception("Statement occupé"));
        }
        this.setStatement(this.getConnection().createStatement());
        ResultSet resultset = this.getStatement().executeQuery("SELECT count(*) AS 'count' from " + table);
        resultset.next();
        count = resultset.getInt("count");
        closeStatement();
        return count;
    }

    public void update(String request) throws Exception, SQLException{
        if(this.isBusy()){
            throw(new Exception("Statement occupé"));
        }
        this.setStatement(this.getConnection().createStatement());
        this.getStatement().executeUpdate(request);
        closeStatement();
    }
}
