/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.LibCritereAndroid.Criteres;

import java.util.LinkedList;
import my.LibCritereAndroid.Recherche.TupleRecherche;


public class ByPermanent implements CritereCustom{
    private boolean permanent;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByPermanent(){
        setPermanent(false);
        setResultats(null);
        setDbRequest(null);
    }

    public ByPermanent(boolean permanent, DBA dbRequest){
        setPermanent(permanent);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        //resultats = dbRequest.searchByNom(getNom());
        int perm = (isPermanent() ? 1 : 0);
        String request = "SELECT nom, prenom FROM volontaires WHERE permanent = '"+perm+"'";
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

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
}
