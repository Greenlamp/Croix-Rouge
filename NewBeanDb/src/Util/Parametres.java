/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;


public class Parametres {
    private LinkedList<KeyPair> params;

    public Parametres(){
        setParams(new LinkedList<KeyPair>());
    }

    public LinkedList<KeyPair> getParams() {
        return params;
    }

    public void setParams(LinkedList<KeyPair> params) {
        this.params = params;
    }

    public void addString(String value) throws Exception{
        KeyPair keyPair = new KeyPair("string", value);
        params.add(keyPair);
    }

    public void addInt(int value) throws Exception{
        KeyPair keyPair = new KeyPair("int", value);
        params.add(keyPair);
    }

    public void addDate(java.util.Date value) throws Exception{
        java.sql.Date dateSql = new java.sql.Date(value.getTime());
        KeyPair keyPair = new KeyPair("date", dateSql);
        params.add(keyPair);
    }

    public void addDate(String value) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = sdf.parse(value);
        java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
        KeyPair keyPair = new KeyPair("date", dateSql);
        params.add(keyPair);
    }

    public static void setParametres(PreparedStatement ps, Parametres params) throws SQLException {
        if(params == null){
            return;
        }
        int i = 1;
        for(KeyPair kp : params.getParams()){
            if(kp.getKey().equals("string")){
                ps.setString(i, (String)kp.getValue());
            }else if(kp.getKey().equals("date")){
                ps.setDate(i, (java.sql.Date)kp.getValue());
            }if(kp.getKey().equals("int")){
                ps.setInt(i, (int)kp.getValue());
            }
            i++;
        }
    }
}
