/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CelluleGrille implements Serializable{
    private String jour;
    private String role;
    private String nomPrenom;
    private Date date;
    private Date heureDebut;
    private Date heureFin;
    private int row;
    private int column;

    public CelluleGrille(){
        setJour(null);
        setRole(null);
        setNomPrenom(null);
        setDate(null);
        setHeureDebut(null);
        setHeureFin(null);
    }

    CelluleGrille(CelluleGrille value) {
        setJour(value.getJour());
        setRole(value.getRole());
        setNomPrenom(value.getNomPrenom());
        setRow(value.getRow());
        setColumn(value.getColumn());
        setDate(value.getDate());
        setHeureDebut(value.getHeureDebut());
        setHeureFin(value.getHeureFin());
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        if(jour != null && jour.isEmpty()) jour = null;
        this.jour = jour;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role != null && role.isEmpty()) role = null;
        this.role = role;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        if(nomPrenom != null && nomPrenom.isEmpty()) nomPrenom = null;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateString(String date) {
        if(EasyDate.EasyDate.isValidDate(date, null)){
            try {
                this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            } catch (ParseException ex) {
                Logger.getLogger(CelluleGrille.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureDebutString(String heureDebut) {
        try {
            this.heureDebut = new SimpleDateFormat("HH:mm").parse(heureDebut);
        } catch (ParseException ex) {
            Logger.getLogger(CelluleGrille.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public void setHeureFinString(String heureFin) {
        try {
            this.heureFin = new SimpleDateFormat("HH:mm").parse(heureFin);
        } catch (ParseException ex) {
            Logger.getLogger(CelluleGrille.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
