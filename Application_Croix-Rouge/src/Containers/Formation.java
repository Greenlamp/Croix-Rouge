/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.File;
import java.util.Date;


public class Formation {
    private String nom;
    private Date dateObtention;
    private int numero;
    private Date datePeremption;
    private Date dateExamen;
    private String lieu;
    private String numeroService112;
    private File photocopie;

    public Formation(){
        setNom(null);
        setDateObtention(null);
        setNumero(-1);
        setDatePeremption(null);
        setDateExamen(null);
        setLieu(null);
        setNumeroService112(null);
        setPhotocopie(null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        this.numeroService112 = numeroService112;
    }

    public File getPhotocopie() {
        return photocopie;
    }

    public void setPhotocopie(File photocopie) {
        this.photocopie = photocopie;
    }

}
