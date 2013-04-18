/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.LinkedList;


public class Decouverte implements Serializable{
    private LinkedList<String> listeDescription;

    public Decouverte(){
        listeDescription = new LinkedList<>();
    }

    public LinkedList<String> getListeDescription() {
        return listeDescription;
    }

    public void setListeDescription(LinkedList<String> listeDescription) {
        this.listeDescription = listeDescription;
    }

    public void addDescription(String description){
        listeDescription.add(description);
    }
}
