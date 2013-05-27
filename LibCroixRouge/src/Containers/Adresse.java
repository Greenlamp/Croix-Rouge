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
}
