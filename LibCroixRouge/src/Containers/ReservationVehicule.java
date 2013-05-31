/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.Date;


public class ReservationVehicule implements Serializable{
    private String nomVehicule;
    private int semaine;
    private int annee;

    public ReservationVehicule(){
        setNomVehicule(null);
        setSemaine(-1);
        setAnnee(-1);
    }

    public ReservationVehicule(ReservationVehicule reservation) {
        setNomVehicule(reservation.getNomVehicule());
        setSemaine(reservation.getSemaine());
        setAnnee(reservation.getAnnee());
    }

    public String getNomVehicule() {
        return nomVehicule;
    }

    public void setNomVehicule(String nomVehicule) {
        if(nomVehicule != null && nomVehicule.isEmpty()) nomVehicule = null;
        this.nomVehicule = nomVehicule;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
