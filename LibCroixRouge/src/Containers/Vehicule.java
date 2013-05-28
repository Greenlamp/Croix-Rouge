/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;


public class Vehicule implements Serializable{
    private String nom;
    private String numeroPlaque;

    public Vehicule(){
        setNom(null);
        setNumeroPlaque(null);
    }

    public Vehicule(String nom, String numeroPlaque){
        setNom(nom);
        setNumeroPlaque(numeroPlaque);
    }

    public Vehicule(Vehicule vehicule) {
        setNom(vehicule.getNom());
        setNumeroPlaque(vehicule.getNumeroPlaque());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroPlaque() {
        return numeroPlaque;
    }

    public void setNumeroPlaque(String numeroPlaque) {
        this.numeroPlaque = numeroPlaque;
    }
}
