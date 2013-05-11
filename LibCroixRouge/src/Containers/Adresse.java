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


public class Adresse implements Serializable{
    private String email;
    private String rue;
    private String numéro;
    private String boite;
    private String codePostal;
    private String ville;
    private String pays;

    public Adresse(){
        setEmail(null);
        setRue(null);
        setNuméro(null);
        setBoite(null);
        setCodePostal(null);
        setVille(null);
        setPays(null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null && email.isEmpty()) email = null;
        this.email = email;
    }

    public String getNuméro() {
        return numéro;
    }

    public void setNuméro(String numéro) {
        if(numéro != null && numéro.isEmpty()) numéro = null;
        this.numéro = numéro;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        if(codePostal != null && codePostal.isEmpty()) codePostal = null;
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if(ville != null && ville.isEmpty()) ville = null;
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        if(pays != null && pays.isEmpty()) pays = null;
        this.pays = pays;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        if(rue != null && rue.isEmpty()) rue = null;
        this.rue = rue;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        if(boite != null && boite.isEmpty()) boite = null;
        this.boite = boite;
    }

    void loadBackupTexte(BufferedReader buff) throws Exception{
        setEmail(buff.readLine());
        setRue(buff.readLine());
        setNuméro(buff.readLine());
        setBoite(buff.readLine());
        setCodePostal(buff.readLine());
        setVille(buff.readLine());
        setPays(buff.readLine());
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getEmail() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getEmail());
        }
        if(this.getRue() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getRue());
        }
        if(this.getNuméro() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNuméro());
        }
        if(this.getBoite() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getBoite());
        }
        if(this.getCodePostal() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getCodePostal());
        }
        if(this.getVille() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getVille());
        }
        if(this.getPays() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getPays());
        }
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Adresse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
