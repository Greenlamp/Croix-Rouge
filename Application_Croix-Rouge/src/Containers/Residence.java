/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;


public class Residence {
    private String rue;
    private int numéro;
    private int boite;
    private int codePostal;
    private String ville;
    private String pays;

    public Residence(){
        setRue(null);
        setNuméro(-1);
        setBoite(-1);
        setCodePostal(-1);
        setVille(null);
        setPays(null);
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
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

    public void setBoite(int boiteR) {
        this.boite = boiteR;
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
}
