/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.util.LinkedList;


public class ByPrenom implements CritereCustom{
    private String prenom;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByPrenom(){
        setPrenom(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByPrenom(String prenom, DBA dbRequest){
        setPrenom(prenom);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        //setResultats(getDbRequest().searchByPrenom(getPrenom()));
        String request = "SELECT nom, prenom FROM volontaires WHERE prenom = '"+prenom+"'";
        resultats = dbRequest.searchByCritere(request);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

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
