/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;


public class Adresse {
    private String email;
    private String rue;
    private int numéro;
    private int boite;
    private int codePostal;
    private String ville;
    private String pays;

    public Adresse(){
        setEmail(null);
        setRue(null);
        setNuméro(-1);
        setBoite(-1);
        setCodePostal(-1);
        setVille(null);
        setPays(null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNuméro() {
        return numéro;
    }

    public void setNuméro(int numéro) {
        this.numéro = numéro;
    }

    public int getBoite() {
        return boite;
    }

    public void setBoite(int boite) {
        this.boite = boite;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }
}
