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
        resultats = new LinkedList<>();
        resultats.addAll(listeCritereCustoms.getFirst().getResultats());
        for(CritereCustom critere : listeCritereCustoms){
            for(TupleRecherche tupleResultat : resultats){
                boolean found = false;
                for(TupleRecherche tuple : critere.getResultats()){
                    if(tupleResultat.getNom().equals(tuple.getNom()) && tupleResultat.getPrenom().equals(tuple.getPrenom())){
                        found = true;
                    }
                }
                if(!found){
                    resultats.remove(tupleResultat);
                }
            }
        }
    }

    public LinkedList<TupleRecherche> getResultats() {
        return resultats;
    }

    public void setResultats(LinkedList<TupleRecherche> resultats) {
        this.resultats = resultats;
    }
}
