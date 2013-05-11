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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Identite implements Serializable{
    private String matricule;
    private String nom;
    private String prenom;
    private String sexe;
    private String nomJeuneFille;
    private Date dateNaissance;

    public Identite(){
        setMatricule(null);
        setNom(null);
        setPrenom(null);
        setSexe("H");
        setNomJeuneFille(null);
        setDateNaissance(null);
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getMatricule() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getMatricule());
        }
        if(this.getNom() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNom());
        }
        if(this.getPrenom() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getPrenom());
        }
        if(this.getSexe() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getSexe());
        }
        if(this.getNomJeuneFille() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNomJeuneFille());
        }
        if(this.getDateNaissance() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, EasyDate.getDateOnly(this.getDateNaissance()));
        }
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Identite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadBackupTexte(BufferedReader buff) throws Exception {
        setMatricule(buff.readLine());
        setNom(buff.readLine());
        setPrenom(buff.readLine());
        setSexe(buff.readLine());
        setNomJeuneFille(buff.readLine());
        String date = buff.readLine();
        if(EasyDate.isValidDate(date, null)){
            setDateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }
    }
}
