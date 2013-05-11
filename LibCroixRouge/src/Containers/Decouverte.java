/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Decouverte implements Serializable{
    private String description;

    public Decouverte(){
        setDescription(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null && description.isEmpty()) description = null;
        this.description = description;
    }

    void loadBackupTexte(BufferedReader buff) throws Exception{
        setDescription(buff.readLine());
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getDescription() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getDescription());
        }
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Decouverte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
