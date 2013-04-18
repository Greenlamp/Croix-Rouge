/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche;

import java.io.Serializable;


public class TupleRecherche implements Serializable{
    private String nom;
    private String prenom;

    public TupleRecherche(){
        setNom(null);
        setPrenom(null);
    }

    public TupleRecherche(String nom, String prenom){
        setNom(nom);
        setPrenom(prenom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
