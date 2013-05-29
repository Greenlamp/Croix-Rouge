/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.Date;


public class ReservationVehicule implements Serializable{
    private String nomVehicule;
    private Date debut;
    private Date fin;

    public ReservationVehicule(){
        setNomVehicule(null);
        setDebut(null);
        setFin(null);
    }

    public String getNomVehicule() {
        return nomVehicule;
    }

    public void setNomVehicule(String nomVehicule) {
        if(nomVehicule != null && nomVehicule.isEmpty()) nomVehicule = null;
        this.nomVehicule = nomVehicule;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
}
