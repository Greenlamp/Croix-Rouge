/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;


public class Carte {
    private String nom;
    private String prenom;
    private String sexe;
    private String rue;
    private String numero;
    private String dateNaissance;
    private String codePostal;
    private String ville;
    private String nationalite;
    private byte[] photo;

    public Carte(){
        setNom(null);
        setPrenom(null);
        setSexe(null);
        setRue(null);
        setNumero(null);
        setDateNaissance(null);
        setCodePostal(null);
        setVille(null);
        setNationalite(null);
        setPhoto(null);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNumero(int numero) {
        this.numero = String.valueOf(numero);
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = String.valueOf(codePostal);
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
