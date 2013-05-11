/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        if(value == null ||value.isEmpty()){
            addNull();
        }else{
            KeyPair keyPair = new KeyPair("string", value);
            params.add(keyPair);
        }
    }

    public void addInt(int value) throws Exception{
        if(value == -1){
            addNull();
        }else{
            KeyPair keyPair = new KeyPair("int", value);
            params.add(keyPair);
        }
    }

    public void addDate(java.util.Date value) throws Exception{
        if(value == null){
            addNull();
        }else{
            java.sql.Date dateSql = new java.sql.Date(value.getTime());
            KeyPair keyPair = new KeyPair("date", dateSql);
            params.add(keyPair);
        }
    }

    public void addDate(String value) throws Exception{
        if(value == null || value.isEmpty()){
            addNull();
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateUtil = sdf.parse(value);
            java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
            KeyPair keyPair = new KeyPair("date", dateSql);
            params.add(keyPair);
        }
    }

    public void addChar(char value){
        String value2 = String.valueOf(value);
        KeyPair keyPair = new KeyPair("String", value2);
        params.add(keyPair);
    }

    public void addNull() {
        KeyPair keyPair = new KeyPair("null", null);
        params.add(keyPair);
    }

    public void addFile(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            Blob blob = (Blob)fis;
            KeyPair keyPair = new KeyPair("Blob", blob);
            params.add(keyPair);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parametres.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            }else if(kp.getKey().equals("int")){
                ps.setInt(i, (int)kp.getValue());
            }else if(kp.getKey().equals("null")){
                ps.setNull(i, java.sql.Types.OTHER);
            }else if(kp.getKey().equals("Blob")){
                ps.setBlob(i, (Blob)kp.getValue());
            }
            i++;
        }
    }
}
