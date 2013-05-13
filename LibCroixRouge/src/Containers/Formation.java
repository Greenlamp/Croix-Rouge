/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import EasyDate.EasyDate;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Formation implements Serializable{
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

    public Formation(){
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
        if(nom != null && nom.isEmpty()) nom = null;
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
        if(lieu != null && lieu.isEmpty()) lieu = null;
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
        if(numeroService112 != null && numeroService112.isEmpty()) numeroService112 = null;
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
        if(numero != null && numero.isEmpty()) numero = null;
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
        if(fragmentDateObtention != null && fragmentDateObtention.isEmpty()) fragmentDateObtention = null;
        this.fragmentDateObtention = fragmentDateObtention;
    }

    public String getFragmentDateExpiration() {
        return fragmentDateExpiration;
    }

    public void setFragmentDateExpiration(String fragmentDateExpiration) {
        if(fragmentDateExpiration != null && fragmentDateExpiration.isEmpty()) fragmentDateExpiration = null;
        this.fragmentDateExpiration = fragmentDateExpiration;
    }

    public String getFragmentDateExamen() {
        return fragmentDateExamen;
    }

    public void setFragmentDateExamen(String fragmentDateExamen) {
        if(fragmentDateExamen != null && fragmentDateExamen.isEmpty()) fragmentDateExamen = null;
        this.fragmentDateExamen = fragmentDateExamen;
    }

    void backupTexte(ByteArrayOutputStream sb) {
        if(this.getNom() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNom());
        }

        if(this.getDateObtention() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, EasyDate.getDateOnly(this.getDateObtention()));
        }

        if(this.getNumero() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNumero());
        }

        if(this.getDatePeremption() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, EasyDate.getDateOnly(this.getDatePeremption()));
        }

        if(this.getDateExamen() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, EasyDate.getDateOnly(this.getDateExamen()));
        }

        if(this.getLieu() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getLieu());
        }

        if(this.getNumeroService112() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getNumeroService112());
        }

        ajouterLigne(sb, (this.isPermanent() ? "true" : "false"));

        if(this.getFragmentDateExpiration() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getFragmentDateExpiration());
        }

        if(this.getFragmentDateObtention() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getFragmentDateObtention());
        }

        if(this.getFragmentDateExamen() == null){
            ajouterLigne(sb, "");
        }else{
            ajouterLigne(sb, this.getFragmentDateExamen());
        }
    }

    void loadBackupTexte(String nom, BufferedReader buff) throws Exception {
        setNom(nom);

        String date = buff.readLine();
        if(EasyDate.isValidDate(date, null)){
            setDateObtention(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }

        setNumero(buff.readLine());

        date = buff.readLine();
        if(EasyDate.isValidDate(date, null)){
            setDatePeremption(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }

        date = buff.readLine();
        if(EasyDate.isValidDate(date, null)){
            setDateExamen(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }

        setLieu(buff.readLine());

        setNumeroService112(buff.readLine());

        setPermanent((buff.readLine().equals("true") ? true : false));

        setFragmentDateExpiration(buff.readLine());

        setFragmentDateObtention(buff.readLine());

        setFragmentDateExamen(buff.readLine());
    }

    private void ajouterLigne(ByteArrayOutputStream sb, String text) {
        try {
            sb.write(text.getBytes());
            sb.write("\n".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Complementaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] getBlobPhotocopie() {
        return blobPhotocopie;
    }

    public void setBlobPhotocopie(byte[] blobPhotocopie) {
        this.blobPhotocopie = blobPhotocopie;
    }

    public void setBlobPhotocopieInBlob(java.sql.Blob blob) {
        if(blob != null){
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
