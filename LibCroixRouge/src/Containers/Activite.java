/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Activite implements Serializable{
    private Date dateDebut;
    private String fragmentDateDebut;
    private String centreSecour;
    private String sisu;
    private byte[] permis;
    private String numeroRegistre;
    private LinkedList<ActiviteFormation> listeFormation;

    public Activite(){
        setDateDebut(null);
        setFragmentDateDebut(null);
        setCentreSecour(null);
        setSisu(null);
        setPermis(null);
        setNumeroRegistre(null);
        setListeFormation(new LinkedList<ActiviteFormation>());
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getFragmentDateDebut() {
        return fragmentDateDebut;
    }

    public void setFragmentDateDebut(String fragmentDateDebut) {
        if(fragmentDateDebut != null && fragmentDateDebut.isEmpty()) fragmentDateDebut = null;
        this.fragmentDateDebut = fragmentDateDebut;
    }

    public String getCentreSecour() {
        return centreSecour;
    }

    public void setCentreSecour(String centreSecour) {
        if(centreSecour != null && centreSecour.isEmpty()) centreSecour = null;
        this.centreSecour = centreSecour;
    }

    public String getSisu() {
        return sisu;
    }

    public void setSisu(String sisu) {
        if(sisu != null && sisu.isEmpty()) sisu = null;
        this.sisu = sisu;
    }

    public byte[] getPermis() {
        return permis;
    }

    public void setPermis(byte[] permis) {
        this.permis = permis;
    }

    public String getNumeroRegistre() {
        return numeroRegistre;
    }

    public void setNumeroRegistre(String numeroRegistre) {
        if(numeroRegistre != null && numeroRegistre.isEmpty()) numeroRegistre = null;
        this.numeroRegistre = numeroRegistre;
    }

    public void addFormation(ActiviteFormation formation){
        if(formation != null){
            listeFormation.add(formation);
        }
    }

    public LinkedList<ActiviteFormation> getListeFormation() {
        return listeFormation;
    }

    public void setListeFormation(LinkedList<ActiviteFormation> listeFormation) {
        this.listeFormation = listeFormation;
    }

    public void setPermisBlob(java.sql.Blob blob) {
        if(blob != null){
            try {
                int blobLength = (int) blob.length();
                this.permis = blob.getBytes(1, blobLength);
                blob.free();
            } catch (SQLException ex) {
                Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
