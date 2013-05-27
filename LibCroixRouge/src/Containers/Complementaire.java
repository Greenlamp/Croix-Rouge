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

    public String getNumCompteBancaire() {
        return numCompteBancaire;
    }

    public void setNumCompteBancaire(String numCompteBancaire) {
        if(numCompteBancaire != null && numCompteBancaire.isEmpty()) numCompteBancaire = null;
        this.numCompteBancaire = numCompteBancaire;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
}
