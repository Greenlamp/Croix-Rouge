/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import EasyDate.EasyDate;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Complementaire implements Serializable{
    private String activitePro;
    private String Activite;
    private String langueMaternelle;
    private LinkedList<String> listeLangue;
    private String qualification;
    private String categorie;
    private boolean permanent;
    private Date dateObtention;
    private boolean amu;
    private boolean tms;
    private boolean vsl;
    private boolean tpmr;
    private boolean selectionMedicale;
    private String dateValidité;
    private String numCompteBancaire;

    public Complementaire(){
        setActivitePro(null);
        setActivite(null);
        setLangueMaternelle(null);
        setListeLangue(new LinkedList<String>());
        setQualification(null);
        setCategorie(null);
        setPermanent(false);
        setDateObtention(null);
        setAmu(false);
        setTms(false);
        setVsl(false);
        setTpmr(false);
        setSelectionMedicale(false);
        setDateValidité(null);
        setNumCompteBancaire(null);
    }

    public String getActivitePro() {
        return activitePro;
    }

    public void setActivitePro(String activitePro) {
        if(activitePro != null && activitePro.isEmpty()) activitePro = null;
        this.activitePro = activitePro;
    }

    public String getLangueMaternelle() {
        return langueMaternelle;
    }

    public void setLangueMaternelle(String langueMaternelle) {
        if(langueMaternelle != null && langueMaternelle.isEmpty()) langueMaternelle = null;
        this.langueMaternelle = langueMaternelle;
    }

    public void addLangue(String langue){
        if(langue != null && langue.isEmpty()) langue = null;
        if(langue != null){
            listeLangue.add(langue);
        }
    }

    public LinkedList<String> getListeLangue() {
        return listeLangue;
    }

    public void setListeLangue(LinkedList<String> listeLangue) {
        this.listeLangue = listeLangue;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        if(qualification != null && qualification.isEmpty()) qualification = null;
        this.qualification = qualification;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        if(categorie != null && categorie.isEmpty()) categorie = null;
        this.categorie = categorie;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public boolean isSelectionMedicale() {
        return selectionMedicale;
    }

    public void setSelectionMedicale(boolean selectionMedicale) {
        this.selectionMedicale = selectionMedicale;
    }

    public String getDateValidité() {
        return dateValidité;
    }

    public void setDateValidité(String dateValidité) {
        if(dateValidité != null && dateValidité.isEmpty()) dateValidité = null;
        this.dateValidité = dateValidité;
    }

    public String getActivite() {
        return Activite;
    }

    public void setActivite(String Activite) {
        if(Activite != null && Activite.isEmpty()) Activite = null;
        this.Activite = Activite;
    }

    public boolean isAmu() {
        return amu;
    }

    public void setAmu(boolean amu) {
        this.amu = amu;
    }

    public boolean isTms() {
        return tms;
    }

    public void setTms(boolean tms) {
        this.tms = tms;
    }

    public boolean isVsl() {
        return vsl;
    }

    public void setVsl(boolean vsl) {
        this.vsl = vsl;
    }

    public boolean isTpmr() {
        return tpmr;
    }

    public void setTpmr(boolean tpmr) {
        this.tpmr = tpmr;
    }

    public String getNumCompteBancaire() {
        return numCompteBancaire;
    }

    public void setNumCompteBancaire(String numCompteBancaire) {
        if(numCompteBancaire != null && numCompteBancaire.isEmpty()) numCompteBancaire = null;
        this.numCompteBancaire = numCompteBancaire;
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getActivitePro() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getActivitePro());
        }

        if(this.getActivite() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getActivite());
        }

        if(this.getLangueMaternelle() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getLangueMaternelle());
        }

        if(listeLangue.size() >= 1){
            if(this.getListeLangue().get(0) == null){
                ajouterLigne(sb, "");
            }else{
                ajouterLigne(sb, this.getListeLangue().get(0));
            }
        }else{
            ajouterLigne(sb, "");
        }

        if(listeLangue.size() >= 2){
            if(this.getListeLangue().get(1) == null){
                ajouterLigne(sb, "");
            }else{
                ajouterLigne(sb, this.getListeLangue().get(1));
            }
        }else{
            ajouterLigne(sb, "");
        }
        if(listeLangue.size() >= 3){
            if(this.getListeLangue().get(2) == null){
                ajouterLigne(sb, "");
            }else{
                ajouterLigne(sb, this.getListeLangue().get(2));
            }
        }else{
            ajouterLigne(sb, "");
        }

        if(this.getQualification() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getQualification());
        }

        if(this.getCategorie() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getCategorie());
        }

        ajouterLigne(sb, (this.isPermanent() ? "true" : "false"));

        if(this.getDateObtention() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, EasyDate.getDateOnly(this.getDateObtention()));
        }

        ajouterLigne(sb, (this.isAmu() ? "true" : "false"));
        ajouterLigne(sb, (this.isTms() ? "true" : "false"));
        ajouterLigne(sb, (this.isVsl() ? "true" : "false"));
        ajouterLigne(sb, (this.isTpmr() ? "true" : "false"));

        ajouterLigne(sb, (this.isSelectionMedicale() ? "true" : "false"));

        if(this.getDateValidité() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getDateValidité());
        }

        if(this.getNumCompteBancaire() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNumCompteBancaire());
        }
    }

    void loadBackupTexte(BufferedReader buff) throws Exception{
        setActivitePro(buff.readLine());
        setActivite(buff.readLine());
        setLangueMaternelle(buff.readLine());
        for(int i=0; i<3; i++){
            addLangue(buff.readLine());
        }
        setQualification(buff.readLine());
        setCategorie(buff.readLine());
        setPermanent((buff.readLine().equals("true") ? true : false));
        String date = buff.readLine();
        if(EasyDate.isValidDate(date, null)){
            setDateObtention(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }
        setAmu((buff.readLine().equals("true") ? true : false));
        setTms((buff.readLine().equals("true") ? true : false));
        setVsl((buff.readLine().equals("true") ? true : false));
        setTpmr((buff.readLine().equals("true") ? true : false));
        setSelectionMedicale((buff.readLine().equals("true") ? true : false));
        setDateValidité(buff.readLine());
        setNumCompteBancaire(buff.readLine());
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Complementaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
}
