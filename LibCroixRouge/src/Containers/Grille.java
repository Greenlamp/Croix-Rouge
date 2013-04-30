/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Grille implements Serializable{
    private String nomAmbulance;
    private String lieu;
    private LinkedList<Key> grilles;
    private int semaine;
    private int annee;
    private String dateDebut;
    private String dateFin;

    public Grille(){
        setNomAmbulance(null);
        setLieu(null);
        setDateDebut(null);
        setDateFin(null);
        setGrilles(new LinkedList<Key>());
    }

    public Grille(Grille grille) {
        setNomAmbulance(grille.getNomAmbulance());
        setLieu(grille.getLieu());
        setDateDebut(grille.getDateDebut());
        setDateFin(grille.getDateFin());
        LinkedList<Key> liste = new LinkedList<>();
        for(Key key : grille.getGrilles()){
            CelluleGrille cellule = new CelluleGrille(key.getValue());
            Key newKey = new Key(key.getX(), key.getY(), cellule);
            liste.add(newKey);
        }
        setGrilles(liste);
        setSemaine(grille.getSemaine());
        setAnnee(grille.getAnnee());
        setDateDebut(grille.getDateDebut());
        setDateFin(grille.getDateFin());
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomAmbulance() {
        return nomAmbulance;
    }

    public void setNomAmbulance(String nomAmbulance) {
        this.nomAmbulance = nomAmbulance;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public LinkedList<Key> getGrilles() {
        return grilles;
    }

    public void setGrilles(LinkedList<Key> grilles) {
        this.grilles = grilles;
    }

    public void addCellule(int row, int column, CelluleGrille cellule){
        Key key = new Key(row, column, cellule);
        if(!grilles.contains(key)){
            grilles.add(key);
        }
    }

    public void removeCellule(int row, int column){
        Key key = new Key(row, column, null);
        if(grilles.contains(key)){
            grilles.remove(key);
        }
    }

    public void setNom(int row, int column, String nom){
        for(Key key : grilles){
            if(key.getX() == row && key.getY() == column){
                CelluleGrille cellule = key.getValue();
                cellule.setNomPrenom(nom);
                grilles.remove(key);
                Key newKey = new Key(row, column, cellule);
                grilles.add(newKey);
                break;
            }
        }
    }
}
