/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;


public class Telephone {
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
        this.gsm = gsm;
    }

    public String getAutreGsm() {
        return autreGsm;
    }

    public void setAutreGsm(String autreGsm) {
        this.autreGsm = autreGsm;
    }

    public String getTelephoneFix() {
        return telephoneFix;
    }

    public void setTelephoneFix(String telephoneFix) {
        this.telephoneFix = telephoneFix;
    }

    public String getTelephoneProfesionnelle() {
        return telephoneProfesionnelle;
    }

    public void setTelephoneProfesionnelle(String telephoneProfesionnelle) {
        this.telephoneProfesionnelle = telephoneProfesionnelle;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
