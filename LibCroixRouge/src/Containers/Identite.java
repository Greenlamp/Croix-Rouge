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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Identite implements Serializable{
    private String matricule;
    private String nom;
    private String prenom;
    private String sexe;
    private String nomJeuneFille;
    private Date dateNaissance;
    private byte[] photo;
    private boolean completed;
    private boolean permanent;

    public Identite(){
        setMatricule(null);
        setNom(null);
        setPrenom(null);
        setSexe("H");
        setNomJeuneFille(null);
        setDateNaissance(null);
        setPhoto(null);
        setCompleted(false);
        setPermanent(false);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom != null && nom.isEmpty()) nom = null;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if(prenom != null && prenom.isEmpty()) prenom = null;
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        if(nomJeuneFille != null && nomJeuneFille.isEmpty()) nomJeuneFille = null;
        this.nomJeuneFille = nomJeuneFille;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setDateNaissanceString(String dateNaissance) {
        if(EasyDate.isValidDate(dateNaissance, null)){
            try {
                this.dateNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
            } catch (ParseException ex) {
            }
        }else{
            this.dateNaissance = null;
        }
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setBlobPhoto(java.sql.Blob blob) {
        if(blob != null){
            try {
                int blobLength = (int) blob.length();
                this.photo = blob.getBytes(1, blobLength);
                blob.free();
            } catch (SQLException ex) {
                Logger.getLogger(Identite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public void setPermanent(int permanent) {
        this.permanent = (permanent == 1 ? true : false);
    }
}
