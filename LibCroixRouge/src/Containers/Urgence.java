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


public class Urgence implements Serializable{
    private String nom;
    private String prenom;
    private String telephone;

    public Urgence(){
        setNom(null);
        setPrenom(null);
        setTelephone(null);
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if(telephone != null && telephone.isEmpty()) telephone = null;
        this.telephone = telephone;
    }
}
