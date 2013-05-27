/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.LibCritereAndroid.Criteres;

import java.io.Serializable;
import java.util.LinkedList;
import my.LibCritereAndroid.Recherche.TupleRecherche;


public class TraitementRecherche implements Serializable{
    private LinkedList<TupleRecherche> resultats;

    public TraitementRecherche(){
        setResultats(null);
    }

    public void Filtrer(LinkedList<CritereCustom> listeCritereCustoms){
        //AND
        resultats = new LinkedList<TupleRecherche>();
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
