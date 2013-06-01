/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.util.LinkedList;


public class ByCodePostal implements CritereCustom{
    private String codePostal;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByCodePostal(){
        setCodePostal(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByCodePostal(String codePostal, DBA dbRequest){
        setCodePostal(codePostal);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        String request = "SELECT nom, prenom FROM Volontaires INNER JOIN Adresse ON(Volontaires.idAdresseLegale = adresse.idAdresse) WHERE adresse.codePostal = '"+codePostal+"';";
        resultats = dbRequest.searchByCritere(request);
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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
