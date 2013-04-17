/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

public class Volontaire {
    private Identite identite;
    private Adresse adresse;
    private Residence residence;
    private Telephone telephone;
    private Urgence urgence;
    private Decouverte decouverte;
    private Complementaire complementaire;
    private Formations formations;

    public Volontaire(){
        setIdentite(null);
        setAdresse(null);
        setResidence(null);
        setTelephone(null);
        setUrgence(null);
        setDecouverte(null);
        setComplementaire(null);
        setFormations(null);
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    public Decouverte getDecouverte() {
        return decouverte;
    }

    public void setDecouverte(Decouverte decouverte) {
        this.decouverte = decouverte;
    }

    public Complementaire getComplementaire() {
        return complementaire;
    }

    public void setComplementaire(Complementaire complementaire) {
        this.complementaire = complementaire;
    }

    public Formations getFormations() {
        return formations;
    }

    public void setFormations(Formations formations) {
        this.formations = formations;
    }
}
