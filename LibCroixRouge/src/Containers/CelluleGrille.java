/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;


public class CelluleGrille implements Serializable{
    private String jour;
    private String heure;
    private String role;
    private String nomPrenom;
    private String date;
    private int row;
    private int column;

    public CelluleGrille(){
        setJour(null);
        setHeure(null);
        setRole(null);
        setNomPrenom(null);
        setDate(null);
    }

    CelluleGrille(CelluleGrille value) {
        setJour(value.getJour());
        setHeure(value.getHeure());
        setRole(value.getRole());
        setNomPrenom(value.getNomPrenom());
        setDate(value.getDate());
        setRow(value.getRow());
        setColumn(value.getColumn());
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
