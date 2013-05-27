/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.LibCritereAndroid.Criteres;

import java.util.LinkedList;
import my.LibCritereAndroid.Recherche.TupleRecherche;


public class ByFormation implements CritereCustom{
    private String nomFormation;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByFormation(){
        setNomFormation(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByFormation(String nomFormation, DBA dbRequest){
        setNomFormation(nomFormation);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        String request = "SELECT nom, prenom FROM formationsSuivie f1, formation f2, volontaires v WHERE f1.idFormation = f2.idFormation AND v.matricule = f1.matricule AND f2.nomFormation = '"+nomFormation+"'";
        resultats = dbRequest.searchByCritere(request);
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

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }
}
