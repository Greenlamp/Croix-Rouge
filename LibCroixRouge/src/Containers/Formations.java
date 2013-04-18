/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

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

}
