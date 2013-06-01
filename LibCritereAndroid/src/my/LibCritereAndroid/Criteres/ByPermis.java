/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.LibCritereAndroid.Criteres;

import java.util.LinkedList;
import my.LibCritereAndroid.Recherche.TupleRecherche;


public class ByPermis implements CritereCustom{
    private String nom;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByPermis(){
        setNom(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByPermis(String nom, DBA dbRequest){
        setNom(nom);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        //resultats = dbRequest.searchByNom(getNom());
        //amu, tms, tpms, tpmr
        String nomFormation = null;
        if(nom.equals("amu")){
            nomFormation = "Ambulancier AMU";
        }else if(nom.equals("tms")){
            nomFormation = "Ambulancier TMS";
        }else if(nom.equals("tpms")){

        }else if(nom.equals("tpmr")){

        }
        String request = "SELECT nom, prenom FROM Volontaires WHERE Volontaires.matricule IN(SELECT matricule FROM FormationsSuivie WHERE FormationsSuivie.idFormation IN(SELECT idFormation FROM Formation WHERE nomFormation = '"+nomFormation+"'))";
        resultats = dbRequest.searchByCritere(request);
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
