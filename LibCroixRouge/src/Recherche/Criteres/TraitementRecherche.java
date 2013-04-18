/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.io.Serializable;
import java.util.LinkedList;


public class TraitementRecherche implements Serializable{
    private LinkedList<TupleRecherche> resultats;

    public TraitementRecherche(){
        setResultats(null);
    }

    public void Filtrer(LinkedList<CritereCustom> listeCritereCustoms){
        //AND
        resultats = listeCritereCustoms.getFirst().getResultats();
        for(CritereCustom elm : listeCritereCustoms){
            resultats.retainAll(elm.getResultats());
        }
    }

    public LinkedList<TupleRecherche> getResultats() {
        return resultats;
    }

    public void setResultats(LinkedList<TupleRecherche> resultats) {
        this.resultats = resultats;
    }
}
