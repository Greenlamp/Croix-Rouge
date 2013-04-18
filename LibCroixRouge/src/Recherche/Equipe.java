/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche;

import java.io.Serializable;
import java.util.LinkedList;


public class Equipe implements Serializable{
    private String nom;
    private LinkedList<TupleRecherche> volontaires;

    public Equipe(){
        setNom(null);
        setVolontaires(null);
    }

    public Equipe(String nom,LinkedList<TupleRecherche> volontaires){
        setNom(nom);
        setVolontaires(volontaires);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LinkedList<TupleRecherche> getVolontaires() {
        return volontaires;
    }

    public void setVolontaires(LinkedList<TupleRecherche> volontaires) {
        this.volontaires = volontaires;
    }
}
