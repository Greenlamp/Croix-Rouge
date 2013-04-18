/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.util.LinkedList;


public class ByNom implements CritereCustom{
    private String nom;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByNom(){
        setNom(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByNom(String nom, DBA dbRequest){
        setNom(nom);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        resultats = dbRequest.searchByNom(getNom());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addResultat(TupleRecherche tuple){
        getResultats().add(tuple);
    }

    @Override
    public LinkedList<TupleRecherche> getResultats() {
        return resultats;
    }

    public void setResultats(LinkedList<TupleRecherche> resultats) {
        this.resultats = resultats;
    }

    public DBA getDbRequest() {
        return dbRequest;
    }

    public void setDbRequest(DBA dbRequest) {
        this.dbRequest = dbRequest;
    }
}
