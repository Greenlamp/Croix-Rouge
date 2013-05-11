/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;


public class Formations implements Serializable{
    private LinkedList<Formation> listeFormation;

    public Formations(){
        listeFormation = new LinkedList<>();
    }

    public void addFormation(Formation formation){
        listeFormation.add(formation);
    }

    public LinkedList<Formation> getListeFormation() {
        return listeFormation;
    }

    public void setListeFormation(LinkedList<Formation> listeFormation) {
        this.listeFormation = listeFormation;
    }

    void backupTexte(ByteArrayOutputStream baos) {
        boolean found = false;
        for(Formation formation : listeFormation){
            if(formation.getNom() != null && formation.getNom().equals("Brevet Européen de Premiers Secours")){
                formation.backupTexte(baos);
                found = true;
            }
        }
        if(!found){
            Formation formation = new Formation();
            formation.backupTexte(baos);
        }

        found = false;
        for(Formation formation : listeFormation){
            if(formation.getNom() != null && formation.getNom().equals("Brevet de secourisme")){
                formation.backupTexte(baos);
                found = true;
            }
        }
        if(!found){
            Formation formation = new Formation();
            formation.backupTexte(baos);
        }

        found = false;
        for(Formation formation : listeFormation){
            if(formation.getNom() != null && formation.getNom().equals("Ambulancier TMS")){
                formation.backupTexte(baos);
                found = true;
            }
        }
        if(!found){
            Formation formation = new Formation();
            formation.backupTexte(baos);
        }
        
        Formation formation = new Formation();
        formation.backupTexte(baos);
        formation.backupTexte(baos);
        formation.backupTexte(baos);
        formation.backupTexte(baos);
        formation.backupTexte(baos);
        formation.backupTexte(baos);
        formation.backupTexte(baos);
    }

    void loadBackupTexte(BufferedReader buff) throws IOException, Exception {
        for(int i=0; i<10; i++){
            Formation formation = new Formation();
            String nom = buff.readLine();
            if(nom != null){
                formation.loadBackupTexte(nom, buff);
            }else{
                formation.loadBackupTexte(null, buff);
            }
            this.addFormation(formation);
        }
    }

}
