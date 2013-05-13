/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;


public class ActiviteFormation implements Serializable{
    private String nom;
    private String status;

    public ActiviteFormation(){
        setNom(null);
        setStatus(null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom != null && nom.isEmpty()) nom = null;
        this.nom = nom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status != null && status.isEmpty()) status = null;
        this.status = status;
    }
}
