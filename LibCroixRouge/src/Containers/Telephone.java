/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Telephone implements Serializable{
    private String gsm;
    private String autreGsm;
    private String telephoneFix;
    private String telephoneProfesionnelle;
    private String fax;

    public Telephone(){
        setGsm(null);
        setAutreGsm(null);
        setTelephoneFix(null);
        setTelephoneProfesionnelle(null);
        setFax(null);
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        if(gsm != null && gsm.isEmpty()) gsm = null;
        this.gsm = gsm;
    }

    public String getAutreGsm() {
        return autreGsm;
    }

    public void setAutreGsm(String autreGsm) {
        if(autreGsm != null && autreGsm.isEmpty()) autreGsm = null;
        this.autreGsm = autreGsm;
    }

    public String getTelephoneFix() {
        return telephoneFix;
    }

    public void setTelephoneFix(String telephoneFix) {
        if(telephoneFix != null && telephoneFix.isEmpty()) telephoneFix = null;
        this.telephoneFix = telephoneFix;
    }

    public String getTelephoneProfesionnelle() {
        return telephoneProfesionnelle;
    }

    public void setTelephoneProfesionnelle(String telephoneProfesionnelle) {
        if(telephoneProfesionnelle != null && telephoneProfesionnelle.isEmpty()) telephoneProfesionnelle = null;
        this.telephoneProfesionnelle = telephoneProfesionnelle;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        if(fax != null && fax.isEmpty()) fax = null;
        this.fax = fax;
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getGsm() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getGsm());
        }
        if(this.getAutreGsm() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getAutreGsm());
        }
        if(this.getTelephoneFix() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getTelephoneFix());
        }
        if(this.getTelephoneProfesionnelle() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getTelephoneProfesionnelle());
        }
        if(this.getFax() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getFax());
        }
    }

    void loadBackupTexte(BufferedReader buff) throws IOException {
        setGsm(buff.readLine());
        setAutreGsm(buff.readLine());
        setTelephoneFix(buff.readLine());
        setTelephoneProfesionnelle(buff.readLine());
        setFax(buff.readLine());
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Telephone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
