/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import EasyDate.EasyDate;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class Grille implements Serializable{
    private Vehicule vehicule;
    private String lieu;
    private LinkedList<Key> grilles;
    private int semaine;
    private int annee;
    private Date dateDebut;
    private Date dateFin;

    public Grille(){
        setLieu(null);
        setDateDebut(null);
        setDateFin(null);
        setGrilles(new LinkedList<Key>());
        setVehicule(null);
        setDateDebut(null);
        setDateFin(null);
    }

    public Grille(Grille grille) {
        setVehicule(grille.getVehicule());
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
        if(lieu != null && lieu.isEmpty()) lieu = null;
        this.lieu = lieu;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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
                String lettre = nom.substring(nom.length()-2, nom.length()-1);
                cellule.setDetail(lettre);
                grilles.remove(key);
                Key newKey = new Key(row, column, cellule);
                grilles.add(newKey);
                break;
            }
        }
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setDateDebutString(String dateDebut) {
        if(EasyDate.isValidDate(dateDebut, null)){
            try {
                this.dateDebut = new SimpleDateFormat("dd/MM/yyyy").parse(dateDebut);
            } catch (ParseException ex) {
            }
        }else{
            this.dateDebut = null;
        }
    }

    public void setDateFinString(String dateFin) {
        if(EasyDate.isValidDate(dateFin, null)){
            try {
                this.dateFin = new SimpleDateFormat("dd/MM/yyyy").parse(dateFin);
            } catch (ParseException ex) {
            }
        }else{
            this.dateFin = null;
        }
    }

    public CelluleGrille getValueAt(int x, int y){
        for(Key key : grilles){
            if(key.getX() == x && key.getY() == y){
                return key.getValue();
            }
        }
        return null;
    }
}
