/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.LinkedList;


public class Groupe implements Serializable{
    private String nom;
    private int niveau;
    private LinkedList<String> droits;

    public Groupe(){
        setNom(null);
        setNiveau(-1);
        setDroits(null);
    }

    public Groupe(Groupe groupe) {
        setNom(groupe.getNom());
        setNiveau(groupe.getNiveau());
        LinkedList<String> liste = new LinkedList<>();
        for(String droit : groupe.getDroits()){
            liste.add(droit);
        }
        setDroits(liste);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public LinkedList<String> getDroits() {
        return droits;
    }

    public void setDroits(LinkedList<String> droits) {
        this.droits = droits;
    }
}
