/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recherche.Criteres;

import Recherche.TupleRecherche;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ByDateNaissance implements CritereCustom{
    private String dateNaissance;
    private LinkedList<TupleRecherche> resultats;
    private DBA dbRequest;

    public ByDateNaissance(){
        setDateNaissance(null);
        setResultats(null);
        setDbRequest(null);
    }

    public ByDateNaissance(String nomFormation, DBA dbRequest){
        setDateNaissance(nomFormation);
        setResultats(null);
        setDbRequest(dbRequest);
    }

    public void doSearch(){
        //resultats = dbRequest.searchByDateNaissance(getDateNaissance());
        String dateFormat = formaterDate(dateNaissance);
        String request = "SELECT nom, prenom FROM volontaires WHERE dateNaissance = '"+dateFormat+"'";
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    private String formaterDate(String dateNaissance) {
        String format = null;
        java.util.Date dateUtil;
        try {
            dateUtil = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
            format = new SimpleDateFormat("yyyy-MM-dd").format(dateUtil);
        } catch (ParseException ex) {
            Logger.getLogger(ByDateNaissance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return format;
    }
}
