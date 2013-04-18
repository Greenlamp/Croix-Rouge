/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;


public class Complementaire implements Serializable{
    private String activitePro;
    private String Activite;
    private String langueMaternelle;
    private LinkedList<String> listeLangue;
    private String qualification;
    private boolean permis;
    private String categorie;
    private Date dateObtention;
    private boolean selectionMedicale;
    private String dateValidité;

    public Complementaire(){
        setActivitePro(null);
        setLangueMaternelle(null);
        setListeLangue(new LinkedList<String>());
        setQualification(null);
        setPermis(false);
        setCategorie(null);
        setDateObtention(null);
        setSelectionMedicale(false);
        setDateValidité(null);
        setActivite(null);
    }

    public String getActivitePro() {
        return activitePro;
    }

    public void setActivitePro(String activitePro) {
        this.activitePro = activitePro;
    }

    public String getLangueMaternelle() {
        return langueMaternelle;
    }

    public void setLangueMaternelle(String langueMaternelle) {
        this.langueMaternelle = langueMaternelle;
    }

    public void addLangue(String langue){
        listeLangue.add(langue);
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
        this.qualification = qualification;
    }

    public boolean isPermis() {
        return permis;
    }

    public void setPermis(boolean permis) {
        this.permis = permis;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
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
        this.dateValidité = dateValidité;
    }

    public String getActivite() {
        return Activite;
    }

    public void setActivite(String Activite) {
        this.Activite = Activite;
    }
}
