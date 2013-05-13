/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Volontaire implements Serializable{
    private Identite identite;
    private Decouverte decouverte;
    private Complementaire complementaire;
    private Adresse adresse;
    private Residence residence;
    private Telephone telephone;
    private Urgence urgence;
    private Formations formations;
    private Activite activite;

    public Volontaire(){
        setIdentite(null);
        setDecouverte(null);
        setComplementaire(null);
        setAdresse(null);
        setResidence(null);
        setTelephone(null);
        setUrgence(null);
        setFormations(null);
        setActivite(null);
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

    public void backupTexte(String loginUser) {
        String nameFile = "CurrentVolontaire_" + loginUser + ".ser";
        try {
            FileOutputStream  fichier = new FileOutputStream (nameFile);
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            //Logger.getLogger(Volontaire.class.getName()).log(Level.SEVERE, null, ex);
        }
/*
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if(getIdentite() != null) getIdentite().backupTexte(baos);
        else new Identite().backupTexte(baos);

        if(getDecouverte() != null) getDecouverte().backupTexte(baos);
        else new Decouverte().backupTexte(baos);

        if(getComplementaire() != null) getComplementaire().backupTexte(baos);
        else new Complementaire().backupTexte(baos);

        if(getAdresse() != null) getAdresse().backupTexte(baos);
        else new Adresse().backupTexte(baos);

        if(getResidence() != null) getResidence().backupTexte(baos);
        else new Residence().backupTexte(baos);

        if(getTelephone() != null) getTelephone().backupTexte(baos);
        else new Telephone().backupTexte(baos);

        if(getUrgence() != null) getUrgence().backupTexte(baos);
        else new Urgence().backupTexte(baos);

        if(getFormations() != null) getFormations().backupTexte(baos);
        else new Formations().backupTexte(baos);



        try {
            FileWriter file = new FileWriter(nameFile);
            FileOutputStream fos = new FileOutputStream(new File(nameFile));
            fos.write(baos.toByteArray());
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Volontaire.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    private void ajouterLigne(StringBuilder sb, String text) {
        sb.append(text);
        sb.append("\n");
    }

    public void clearBackupTexte(String loginUser) {
        String nameFile = "CurrentVolontaire_" + loginUser + ".ser";
        File file = new File(nameFile);
        if(file.exists()){
            file.delete();
        }
    }

    public void loadBackupTexte(String loginUser) {
        String nameFile = "CurrentVolontaire_" + loginUser + ".ser";
        try {
            FileInputStream fichier = new FileInputStream(nameFile);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            Volontaire volontaire = (Volontaire) ois.readObject();
            setIdentite(volontaire.getIdentite());
            setAdresse(volontaire.getAdresse());
            setResidence(volontaire.getResidence());
            setTelephone(volontaire.getTelephone());
            setUrgence(volontaire.getUrgence());
            setDecouverte(volontaire.getDecouverte());
            setComplementaire(volontaire.getComplementaire());
            setFormations(volontaire.getFormations());
            setActivite(volontaire.getActivite());
            ois.close();
        } catch (Exception ex) {
            //Logger.getLogger(Volontaire.class.getName()).log(Level.SEVERE, null, ex);
        }

/*
        try {
            BufferedReader buff = new BufferedReader(new FileReader(nameFile));

            Identite identite = new Identite();
            identite.loadBackupTexte(buff);

            Decouverte decouverte = new Decouverte();
            decouverte.loadBackupTexte(buff);

            Complementaire complementaire = new Complementaire();
            complementaire.loadBackupTexte(buff);

            Adresse adresse = new Adresse();
            adresse.loadBackupTexte(buff);

            Residence residence = new Residence();
            residence.loadBackupTexte(buff);

            Telephone telephone = new Telephone();
            telephone.loadBackupTexte(buff);

            Urgence urgence = new Urgence();
            urgence.loadBackupTexte(buff);

            Formations formations = new Formations();
            formations.loadBackupTexte(buff);

            this.setIdentite(identite);
            this.setDecouverte(decouverte);
            this.setComplementaire(complementaire);
            this.setAdresse(adresse);
            this.setResidence(residence);
            this.setTelephone(telephone);
            this.setUrgence(urgence);
            this.setFormations(formations);

            buff.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Volontaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Volontaire.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}
