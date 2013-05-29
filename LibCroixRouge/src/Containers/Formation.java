/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Containers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Formation implements Serializable {

    private String nom;
    private Date dateObtention;
    private String numero;
    private Date datePeremption;
    private Date dateExamen;
    private String lieu;
    private String numeroService112;
    //private File photocopie;
    private byte[] blobPhotocopie;
    private boolean permanent;
    private String fragmentDateObtention;
    private String fragmentDateExpiration;
    private String fragmentDateExamen;

    public Formation() {
        setNom(null);
        setDateObtention(null);
        setNumero(null);
        setDatePeremption(null);
        setDateExamen(null);
        setLieu(null);
        setNumeroService112(null);
        //setPhotocopie(null);
        setBlobPhotocopie(null);
        setPermanent(false);
        setFragmentDateExpiration(null);
        setFragmentDateObtention(null);
        setFragmentDateExamen(null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom != null && nom.isEmpty()) {
            nom = null;
        }
        this.nom = nom;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        if (lieu != null && lieu.isEmpty()) {
            lieu = null;
        }
        this.lieu = lieu;
    }

    public Date getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(Date dateExamen) {
        this.dateExamen = dateExamen;
    }

    public String getNumeroService112() {
        return numeroService112;
    }

    public void setNumeroService112(String numeroService112) {
        if (numeroService112 != null && numeroService112.isEmpty()) {
            numeroService112 = null;
        }
        this.numeroService112 = numeroService112;
    }

    /*public File getPhotocopie() {
     return photocopie;
     }

     public void setPhotocopie(File photocopie) {
     this.photocopie = photocopie;
     }*/
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero != null && numero.isEmpty()) {
            numero = null;
        }
        this.numero = numero;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public String getFragmentDateObtention() {
        return fragmentDateObtention;
    }

    public void setFragmentDateObtention(String fragmentDateObtention) {
        if (fragmentDateObtention != null && fragmentDateObtention.isEmpty()) {
            fragmentDateObtention = null;
        }
        this.fragmentDateObtention = fragmentDateObtention;
    }

    public String getFragmentDateExpiration() {
        return fragmentDateExpiration;
    }

    public void setFragmentDateExpiration(String fragmentDateExpiration) {
        if (fragmentDateExpiration != null && fragmentDateExpiration.isEmpty()) {
            fragmentDateExpiration = null;
        }
        this.fragmentDateExpiration = fragmentDateExpiration;
    }

    public String getFragmentDateExamen() {
        return fragmentDateExamen;
    }

    public void setFragmentDateExamen(String fragmentDateExamen) {
        if (fragmentDateExamen != null && fragmentDateExamen.isEmpty()) {
            fragmentDateExamen = null;
        }
        this.fragmentDateExamen = fragmentDateExamen;
    }

    public byte[] getBlobPhotocopie() {
        return blobPhotocopie;
    }

    public void setBlobPhotocopie(byte[] blobPhotocopie) {
        this.blobPhotocopie = blobPhotocopie;
    }

    public void setBlobPhotocopieInBlob(java.sql.Blob blob) {
        if (blob != null) {
            try {
                int blobLength = (int) blob.length();
                this.blobPhotocopie = blob.getBytes(1, blobLength);
                blob.free();
            } catch (SQLException ex) {
                Logger.getLogger(Formation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
