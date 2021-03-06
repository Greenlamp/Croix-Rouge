/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import Containers.Activite;
import Containers.ActiviteFormation;
import Containers.Adresse;
import DBA.DbMySql;
import Containers.CelluleGrille;
import Containers.Complementaire;
import Containers.Decouverte;
import Containers.Formation;
import Containers.Formations;
import Containers.Grille;
import Containers.Groupe;
import Containers.Identite;
import Containers.Key;
import Containers.ReservationVehicule;
import Containers.Residence;
import Containers.Telephone;
import Containers.Urgence;
import Containers.Utilisateur;
import Containers.Vehicule;
import Containers.Volontaire;
import EasyDate.EasyDate;
import FileAccess.FileAccess;
import Util.Parametres;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.LibCritereAndroid.Criteres.DBA;
import my.LibCritereAndroid.Criteres.TraitementRecherche;
import my.LibCritereAndroid.Recherche.TupleRecherche;


public class DbRequests implements DBA{
    DbMySql mysql = null;

    public DbRequests(){
        String host = FileAccess.getConfig("configs", "HOST_MYSQL");
        String port = FileAccess.getConfig("configs", "PORT_MYSQL");
        String user = FileAccess.getConfig("configs", "USER_MYSQL");
        String pass = FileAccess.getConfig("configs", "PASS_MYSQL");
        String db = FileAccess.getConfig("configs", "DB_MYSQL");
        mysql = new DbMySql(host, port, user, pass, db);
    }

    public DbMySql getMysql(){
        return mysql;
    }

    public void insertVolontaire(Identite identite, Adresse adresse, String remarque, int idPersonneUrgence, int idDecouverte, int idPrestation, int idRenseignement, int idAdresseLegal, int idAdresseResidence, int idTelephone, int idActivite) throws Exception{
        if(identite == null){
            return;
        }
        String request = "INSERT INTO Volontaires(matricule, nom, nomEpouse, prenom, dateNaissance, sexe, email, remarques, photo, completed, permanent, idPersonneUrgence, idDecouverte, idPrestation, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, idActivite) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(identite.getMatricule());
        params.addString(identite.getNom());
        params.addString(identite.getNomJeuneFille());
        params.addString(identite.getPrenom());
        params.addDate(identite.getDateNaissance());
        params.addString(identite.getSexe());
        if(adresse != null){
            params.addString(adresse.getEmail());
        }else{
            params.addNull();
        }
        params.addString(remarque);
        params.addBlob(identite.getPhoto());
        params.addInt((identite.isCompleted() ? 1 : 0));
        params.addInt((identite.isPermanent() ? 1 : 0));
        params.addInt(idPersonneUrgence);
        params.addInt(idDecouverte);
        params.addInt(idPrestation);
        params.addInt(idRenseignement);
        params.addInt(idAdresseLegal);
        params.addInt(idAdresseResidence);
        params.addInt(idTelephone);
        params.addInt(idActivite);

        mysql.pUpdate(request, params);
    }

    /*public void insertVolontaire(String matricule, String nom, String prenom, String nomEpoux, Date dateNaissance, String sexe, String email, String remarque, int idPersonneUrgence, int idDecouverte, int idPrestation, int idRenseignement, int idAdresseLegal, int idAdresseResidence, int idTelephone, int idActivite) throws Exception{
        String request = "INSERT INTO Volontaires(matricule, nom, nomEpouse, prenom, dateNaissance, sexe, email, remarques, idPersonneUrgence, idDecouverte, idPrestation, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, idActivite) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(matricule);
        params.addString(nom);
        params.addString(nomEpoux);
        params.addString(prenom);
        params.addDate(dateNaissance);
        params.addString(sexe);
        params.addString(email);
        params.addString(remarque);
        params.addInt(idPersonneUrgence);
        params.addInt(idDecouverte);
        params.addInt(idPrestation);
        params.addInt(idRenseignement);
        params.addInt(idAdresseLegal);
        params.addInt(idAdresseResidence);
        params.addInt(idTelephone);
        params.addInt(idActivite);

        mysql.pUpdate(request, params);
    }*/

    public boolean checkMatricule(String matricule) throws Exception{
        boolean found = false;
        String request = "SELECT matricule FROM volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            found = true;
        }
        return found;
    }

    /*public int insertPersonneUrgence(String nom, String prenom, String telephone) throws Exception{
        if(nom == null && prenom == null && telephone == null){
            return -1;
        }
        int idPersonneUrgence = -1;
        String request = "INSERT INTO PersonneUrgence(nom, prenom, telephone) VALUES(?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(nom);
        params.addString(prenom);
        params.addString(telephone);
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM PersonneUrgence";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idPersonneUrgence = rs.getInt("id");
        }
        return idPersonneUrgence;
    }*/

    public int insertDecouvertes(Decouverte decouverte) throws Exception{
        if(decouverte == null){
            return -1;
        }
        int idDecouverte = getIdDecouverte(decouverte.getDescription());
        if(idDecouverte != -1){
            return idDecouverte;
        }
        String request = "INSERT INTO Decouverte(description) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(decouverte.getDescription());
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Decouverte";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idDecouverte = rs.getInt("id");
        }
        return idDecouverte;
    }

    public int getIdDecouverte(String description) throws Exception{
        int idDecouverte = -1;
        String request = "SELECT idDecouverte FROM Decouverte WHERE description = ?";
        Parametres params = new Parametres();
        params.addString(description);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idDecouverte = rs.getInt("idDecouverte");
        }
        return idDecouverte;
    }

    /*public int insertDecouvertes(String description) throws Exception{
        if(description == null){
            return -1;
        }
        int idDecouverte = -1;
        String request = "INSERT INTO Decouverte(description) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(description);
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Decouverte";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idDecouverte = rs.getInt("id");
        }
        return idDecouverte;
    }*/

    public int insertLangue(String langue) throws Exception{
        if(langue == null){
            return -1;
        }
        int idLangue = getIdLangue(langue);
        if(idLangue != -1){
            return idLangue;
        }
        String request = "INSERT INTO Langue(nom) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(langue);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Langue";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idLangue = rs.getInt("id");
        }
        return idLangue;
    }

    public int getIdLangue(String langue) throws Exception{
        if(langue == null || langue.isEmpty()){
            return -1;
        }
        int idLangue = -1;
        String request = "SELECT idLangue FROM Langue WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(langue);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idLangue = rs.getInt("idLangue");
        }
        return idLangue;
    }

    public int insertRenseignement(Complementaire complementaire) throws Exception{
        if(complementaire == null){
            return -1;
        }
        int idLangueMaternelle = insertLangue(complementaire.getLangueMaternelle());
        int idRenseignement = -1;
        String request = "INSERT INTO Renseignements"
                + "(activitePro, SituationActuelle, Diplome, Categorie, DateObtention, permanent, selectionMedicale, dateValidite, numCompteBancaire, idLangueMaternelle)"
                + " VALUES"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(complementaire.getActivitePro());
        params.addString(complementaire.getActivite());
        params.addString(complementaire.getQualification());
        params.addString(complementaire.getCategorie());
        params.addDate(complementaire.getDateObtention());
        params.addInt((complementaire.isPermanent() ? 1 : 0));
        params.addInt((complementaire.isSelectionMedicale() ? 1 : 0));
        params.addString(complementaire.getDateValidité());
        params.addString(complementaire.getNumCompteBancaire());
        params.addInt(idLangueMaternelle);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Renseignements";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idRenseignement = rs.getInt("id");
        }
        for(String langue : complementaire.getListeLangue()){
            int idLangue = insertLangue(langue);
            request = "INSERT INTO LanguesConnue(idRenseignements, idLangue) VALUES(?, ?)";
            params = new Parametres();
            params.addInt(idRenseignement);
            params.addInt(idLangue);
            mysql.pUpdate(request, params);
        }
        return idRenseignement;
    }

    /*public int insertRenseignement(String activitePro, String activite, String qualification, String permis, String categorie, Date dateObtention, String selectionMedicale, String dateValidité, int idLangueMaternelle) throws Exception{
        if(activitePro == null && activite == null && qualification == null && permis == null && categorie == null && dateObtention == null &&
                selectionMedicale == null && dateValidité == null){
            return -1;
        }
        int idRenseignement = -1;
        String request = "INSERT INTO Renseignement"
                + "(activitePro, SituationActuelle, Diplome, PermisConduire, Categorie, DateObtention, selectionMedicale, dateValidite, numCompteBancaire, idLangueMaternelle)"
                + " VALUES"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(activitePro);
        params.addString(activite);
        params.addString(qualification);
        params.addString(permis);
        params.addString(categorie);
        params.addDate(dateObtention);
        params.addString(selectionMedicale);
        params.addString(dateValidité);
        params.addInt(idLangueMaternelle);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Renseignement";
        ResultSet rs = mysql.pSelect(request, null);

        while(rs.next()){
            idRenseignement = rs.getInt("id");
        }
        return idRenseignement;
    }*/

    public void insertLanguesConnue(int idRenseignement, LinkedList<String> listeLangue) throws Exception{
        for(String langue : listeLangue){
            int idLangue = insertLangue(langue);
            String request = "INSERT INTO LanguesConnue(idRenseignement, idLangue) VALUES(?, ?)";
            Parametres params = new Parametres();
            params.addInt(idRenseignement);
            params.addInt(idLangue);

            mysql.pUpdate(request, params);
        }
    }

    public void insertFormationsSuivie(String matricule, LinkedList<Formation> listeFormation) throws Exception{
        for(Formation formation : listeFormation){
            String request = "INSERT INTO Formation(nomFormation, dateObtention, dateExpiration, numero, lieu, copie, numeroService112) VALUES(?, ?, ?, ?, ?, ?, ?)";
            Parametres params = new Parametres();
            params.addString(formation.getNom());
            params.addDate(formation.getDateObtention());
            params.addDate(formation.getDatePeremption());
            params.addString(formation.getNumero());
            params.addString(formation.getLieu());
            //params.addFile(formation.getPhotocopie());
            params.addString(formation.getNumeroService112());

            mysql.pUpdate(request, params);

            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Formation";
            ResultSet rs = mysql.pSelect(request, null);
            int idFormation = -1;
            while(rs.next()){
                idFormation = rs.getInt("id");
            }

            request = "INSERT INTO FormationsSuivie(matricule, idFormation) VALUES(?, ?)";
            params = new Parametres();
            params.addString(matricule);
            params.addInt(idFormation);

            mysql.pUpdate(request, params);
        }
    }

    public int insertPays(String pays) throws Exception{
        if(pays == null || pays.isEmpty()){
            return -1;
        }
        int idPays = getIdPays(pays);
        if(idPays != -1){
            return idPays;
        }

        String request = "INSERT INTO Pays(nomPays) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(pays);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Pays";
        ResultSet rs = mysql.pSelect(request, null);
        idPays = -1;
        while(rs.next()){
            idPays = rs.getInt("id");
        }
        return idPays;
    }

    public int getIdPays(String pays) throws Exception{
        if(pays == null || pays.isEmpty()){
            return -1;
        }
        int idPays = -1;
        String request = "SELECT idPays FROM Pays WHERE nomPays = ?";
        Parametres params = new Parametres();
        params.addString(pays);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idPays = rs.getInt("idPays");
        }
        return idPays;
    }

    public int insertVille(String ville) throws Exception {
        if(ville == null || ville.isEmpty()){
            return -1;
        }
        int idVille = getIdVille(ville);
        if(idVille != -1){
            return idVille;
        }

        String request = "INSERT INTO Ville(nomVille) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(ville);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Ville";
        ResultSet rs = mysql.pSelect(request, null);
        idVille = -1;
        while(rs.next()){
            idVille = rs.getInt("id");
        }
        return idVille;
    }

    public int getIdVille(String ville) throws Exception{
        if(ville == null || ville.isEmpty()){
            return -1;
        }
        int idVille = -1;
        String request = "SELECT idVille FROM Ville WHERE nomVille = ?";
        Parametres params = new Parametres();
        params.addString(ville);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idVille = rs.getInt("idVille");
        }
        return idVille;
    }

    public int insertAdresseLegale(Adresse adresse, String matricule) throws Exception{
        if(adresse == null){
            return -1;
        }
        int idAdresse = -1;
        int idVille = insertVille(adresse.getVille());
        int idPays = insertPays(adresse.getPays());
        String request = "INSERT INTO Adresse(rueAvenueBd, numero, codePostal, boite, idPays, idVille) VALUES(?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(adresse.getRue());
        params.addString(adresse.getNuméro());
        params.addString(adresse.getCodePostal());
        params.addString(adresse.getBoite());
        params.addInt(idPays);
        params.addInt(idVille);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Adresse";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idAdresse = rs.getInt("id");
        }

        return idAdresse;

    }

    public int insertAdresseResidence(Residence residence, String matricule) throws Exception {
        if(residence == null || matricule == null){
            return -1;
        }
        int idAdresse = -1;
        int idVille = insertVille(residence.getVille());
        int idPays = insertPays(residence.getPays());
        String request = "INSERT INTO Adresse(rueAvenueBd, numero, codePostal, boite, idPays, idVille) VALUES(?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(residence.getRue());
        params.addString(residence.getNuméro());
        params.addString(residence.getCodePostal());
        params.addString(residence.getBoite());
        params.addInt(idPays);
        params.addInt(idVille);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Adresse";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idAdresse = rs.getInt("id");
        }

        return idAdresse;
    }

    public int insertTelephone(Telephone telephone, String matricule) throws Exception {
        if(telephone == null || matricule == null){
            return -1;
        }
        int idTelephone = -1;
        String request = "INSERT INTO Telephone(gsm, autreGsm, telephoneFix, telephonePro, fax) VALUES(?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(telephone.getGsm());
        params.addString(telephone.getAutreGsm());
        params.addString(telephone.getTelephoneFix());
        params.addString(telephone.getTelephoneProfesionnelle());
        params.addString(telephone.getFax());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Telephone";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idTelephone = rs.getInt("id");
        }

        return idTelephone;
    }

    public int insertPersonneUrgence(Urgence urgence, String matricule) throws Exception{
        if(urgence == null || matricule == null){
            return -1;
        }
        int idPersonneUrgence = -1;
        String request = "INSERT INTO PersonneUrgence(nom, prenom, telephone) VALUES(?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(urgence.getNom());
        params.addString(urgence.getPrenom());
        params.addString(urgence.getTelephone());
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM PersonneUrgence";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idPersonneUrgence = rs.getInt("id");
        }

        return idPersonneUrgence;
    }

    public int insertActivite(Activite activite, String matricule) throws Exception {
        if(activite == null || matricule == null){
            return -1;
        }
        int idActivite = -1;
        String request = "INSERT INTO Activite(dateDebut, fragmentDateDebut, centreSecours, sisu, copiePermis, numeroRegistreNational) VALUES(?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addDate(activite.getDateDebut());
        params.addString(activite.getFragmentDateDebut());
        params.addString(activite.getCentreSecour());
        params.addString(activite.getSisu());
        params.addBlob(activite.getPermis());
        params.addString(activite.getNumeroRegistre());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Activite";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idActivite = rs.getInt("id");
        }

        for(ActiviteFormation formation: activite.getListeFormation()){
            int idFormation = insertActiviteFormation(formation);
            if(idFormation != -1){
                request = "INSERT INTO FormationSuivieActivite(idActivite, idFormationActivite) VALUES(?, ?)";
                params = new Parametres();
                params.addInt(idActivite);
                params.addInt(idFormation);
                mysql.pUpdate(request, params);
            }
        }
        return idActivite;
    }

    private int insertActiviteFormation(ActiviteFormation formation) throws Exception{
        if(formation == null){
            return -1;
        }
        int idFormation = -1;
        String request = "INSERT INTO FormationActivite(nomFormationActivite, status) VALUES(? ,?)";
        Parametres params = new Parametres();
        params.addString(formation.getNom());
        params.addString(formation.getStatus());
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM FormationActivite";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idFormation = rs.getInt("id");
        }
        return idFormation;
    }

    public void insertFormations(Formations formations, String matricule) throws Exception{
        if(formations == null || matricule == null || formations.getListeFormation().isEmpty()){
            return;
        }
        for(Formation formation : formations.getListeFormation()){
            int idFormation = insertFormation(formation);
            if(idFormation != -1){
                String request = "INSERT INTO FormationsSuivie(matricule, idFormation) VALUES(?, ?)";
                Parametres params = new Parametres();
                params.addString(matricule);
                params.addInt(idFormation);
                mysql.pUpdate(request, params);
            }
        }
    }

    private int insertFormation(Formation formation) throws Exception{
        if(formation == null || formation.getNom() == null || formation.getNom().isEmpty()){
            return -1;
        }
        int idFormation = -1;
        String request = "INSERT INTO Formation(nomFormation, dateObtention, dateExpiration, dateExamen, numero, lieu, copie, numeroService112, permanent, fragmentDateObtention, fragmentDateExpiration, fragmentDateExamen) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(formation.getNom());
        params.addDate(formation.getDateObtention());
        params.addDate(formation.getDatePeremption());
        params.addDate(formation.getDateExamen());
        params.addString(formation.getNumero());
        params.addString(formation.getLieu());
        params.addBlob(formation.getBlobPhotocopie());
        params.addString(formation.getNumeroService112());
        params.addInt((formation.isPermanent() ? 1 : 0));
        params.addString(formation.getFragmentDateObtention());
        params.addString(formation.getFragmentDateExpiration());
        params.addString(formation.getFragmentDateExamen());
        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Formation";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idFormation = rs.getInt("id");
        }

        return idFormation;
    }

    /*public int insertAdresse(String rue, int numéro, int codePostal, String boite, int idPaysLegal, int idVilleLegal, String matricule) throws Exception {
        if(rue == null || rue.isEmpty() || numéro == -1 || codePostal == -1){
            return -1;
        }
        int idAdresse = -1;
        String request = "INSERT INTO Adresse(rueAvenueBd, numero, codePostal, boite, idPays, idVille, matriculeVolontaire) VALUES(?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(rue);
        params.addInt(numéro);
        params.addInt(codePostal);
        params.addString(boite);
        params.addInt(idPaysLegal);
        params.addInt(idVilleLegal);
        params.addString(matricule);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Adresse";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idAdresse = rs.getInt("id");
        }
        return idAdresse;
    }*/

    /*public int insertTelephone(String gsm, String autreGsm, String telephoneFix, String telephoneProfesionnelle, String fax) throws Exception {
        if((gsm == null || gsm.isEmpty()) && (autreGsm == null || autreGsm.isEmpty()) && (telephoneFix == null || telephoneFix.isEmpty()) && (telephoneProfesionnelle == null || telephoneProfesionnelle.isEmpty()) && (fax == null || fax.isEmpty())){
            return -1;
        }
        int idTelephone = -1;
        String request = "INSERT INTO Telephone(gsm, autreGsm, telephoneFix, telephonePro, fax) VALUES(?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(gsm);
        params.addString(autreGsm);
        params.addString(telephoneFix);
        params.addString(telephoneProfesionnelle);
        params.addString(fax);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Telephone";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idTelephone = rs.getInt("id");
        }
        return idTelephone;
    }*/

    public void checkLogin(String login) throws Exception {
        boolean found = false;
        String request = "SELECT login FROM utilisateurs WHERE login = ?";
        Parametres params = new Parametres();
        params.addString(login);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            found = true;
        }
        if(!found){
            throw new Exception("Login incorrect");
        }
    }

    public int checLoginPassword(String login, String password) throws Exception {
        boolean found = false;
        int idUtilisateur = -1;
        String request = "SELECT idUtilisateur FROM utilisateurs WHERE login = ? AND password = ?";
        Parametres params = new Parametres();
        params.addString(login);
        params.addString(password);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            found = true;
            idUtilisateur = rs.getInt("idUtilisateur");
        }
        if(!found){
            throw new Exception("passowrd incorrect");
        }
        return idUtilisateur;
    }

    public LinkedList<String[]> getVolontairesAll() throws Exception {
        LinkedList<String[]> listeVolontaires = new LinkedList<>();
        String request = "SELECT Volontaires.nom, Volontaires.prenom, Volontaires.dateNaissance, Ville.nomVille, Volontaires.completed FROM (Volontaires LEFT JOIN Adresse ON(Volontaires.idAdresseLegale = Adresse.idAdresse)) LEFT JOIN Ville ON(Ville.idVille = Adresse.idVille)";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Timestamp date = rs.getTimestamp("dateNaissance");
            String nomVille = rs.getString("nomVille");
            String dateNaissance = EasyDate.getDateOnly(date);
            String completed = (rs.getInt("completed") == 1 ? "Complet" : "Incomplet");
            String[] data = {nom, prenom, dateNaissance, nomVille, completed};
            listeVolontaires.add(data);
        }
        return listeVolontaires;
    }

    public LinkedList<String[]> getDroits() throws Exception {
        LinkedList<String[]> listeDroits = new LinkedList<>();
        String request = "SELECT nom, description FROM Droits";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            String[] data = {nom, description};
            listeDroits.add(data);
        }
        return listeDroits;
    }

    public LinkedList<String[]> getGroupes() throws Exception {
        LinkedList<String[]> listeGroupes = new LinkedList<>();
        String request = "SELECT nomGroupe, niveau FROM Groupes";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nomGroupe = rs.getString("nomGroupe");
            int niveau = rs.getInt("niveau");
            String[] data = {nomGroupe, String.valueOf(niveau)};
            listeGroupes.add(data);
        }
        return listeGroupes;
    }

    public LinkedList<String[]> getUtilisateurs() throws Exception {
        LinkedList<String[]> listeUtilisateurs = new LinkedList<>();
        String request = "SELECT login, prioritaire, dateDerniereConnexion FROM Utilisateurs";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String login = rs.getString("login");
            int prioritaire = rs.getInt("prioritaire");
            Timestamp dateDerniereConnexion = rs.getTimestamp("dateDerniereConnexion");
            String[] data = {login, String.valueOf(prioritaire), EasyDate.getDateHour(dateDerniereConnexion)};
            listeUtilisateurs.add(data);
        }
        return listeUtilisateurs;
    }

    public LinkedList<String> getMyDroits(int idUser) throws Exception {
        LinkedList<String> listeDroits = new LinkedList<>();
        if(idUser == -1){
            return listeDroits;
        }
        String request =  "SELECT Droits.nom "
                        + "FROM Utilisateurs, Appartenir, Groupes, PossederDroit, Droits "
                        + "WHERE Utilisateurs.idUtilisateur = ? "
                        + "AND Appartenir.idUtilisateur = Utilisateurs.idUtilisateur "
                        + "AND Appartenir.idGroupe = Groupes.idGroupe "
                        + "AND PossederDroit.idGroupe = Groupes.idGroupe "
                        + "AND PossederDroit.idDroit = Droits.idDroit;";
        Parametres params = new Parametres();
        params.addInt(idUser);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nom = rs.getString("nom");
            listeDroits.add(nom);
        }
        return listeDroits;
    }

    public int getIdUser(String login) throws Exception {
        if(login == null || login.isEmpty()){
            return -1;
        }
        int idUtilisateur = -1;
        boolean found = false;
        String request = "SELECT idUtilisateur FROM utilisateurs WHERE login = ?";
        Parametres params = new Parametres();
        params.addString(login);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idUtilisateur = rs.getInt("idUtilisateur");
            found = true;
        }
        if(!found){
            throw new Exception("Login inconnu");
        }
        return idUtilisateur;
    }

    public Map<String, String> getDataUser(int id) throws Exception {
        /*Map<String, String> dataUser = new TreeMap<String, String>();
        return dataUser;*/
        return new TreeMap<>();
    }

    private String escapeChar(String description) {
        String retour = description.replaceAll("'", "+");
        retour = retour.replace("+", "\\'");
        return retour;
    }

    public LinkedList<String[]> getEquipes() throws Exception {
        LinkedList<String[]> listeEquipes = new LinkedList<>();
        String request =  "SELECT nom, dateCreation, count(matricule) as 'count'"
                        + "FROM Equipe E, Lier L "
                        + "WHERE E.idEquipe = L.idEquipe "
                        + "GROUP BY(nom)";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            Timestamp date = rs.getTimestamp("dateCreation");
            String dateCreation = EasyDate.getDateOnly(date);
            int count = rs.getInt("count");
            String[] data = {nom, dateCreation, String.valueOf(count)};
            listeEquipes.add(data);
        }
        return listeEquipes;
    }

    public int insertEquipe(String nom) throws Exception {
        if(nom == null ||nom.isEmpty()){
            return -1;
        }
        int idEquipe = -1;
        String request = "INSERT INTO Equipe(nom, dateCreation) VALUES(?, ?)";
        Parametres params = new Parametres();
        params.addString(nom);
        params.addDate(new Date());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Equipe";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idEquipe = rs.getInt("id");
        }
        return idEquipe;
    }

    public String getMatricule(String nom, String prenom) throws Exception {
        if(nom == null || prenom == null){
            return null;
        }
        String matricule = null;
        String request = "SELECT matricule FROM volontaires WHERE nom = ? AND prenom = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        params.addString(prenom);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            matricule = rs.getString("matricule");
        }
        return matricule;
    }

    public void insertMembreEquipe(int idEquipe, String matricule) throws Exception {
        String request = "INSERT INTO Lier(idEquipe, matricule) VALUES(?, ?)";
        Parametres params = new Parametres();
        params.addInt(idEquipe);
        params.addString(matricule);
        mysql.pUpdate(request, params);
    }

    public LinkedList<String> getFormations() throws Exception {
        LinkedList<String> listeFormations = new LinkedList<>();
        String request = "SELECT nomFormation FROM formation";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nomFormation = rs.getString("nomFormation");
            listeFormations.add(nomFormation);
        }
        return listeFormations;
    }

    @Override
    public LinkedList<TupleRecherche> searchByCritere(String request) {
        LinkedList<TupleRecherche> resultat = new LinkedList<>();
        try {
            ResultSet rs = mysql.pSelect(request, null);
            while(rs.next()){
                String nomResultat = rs.getString("nom");
                String prenomResultat = rs.getString("prenom");
                resultat.add(new TupleRecherche(nomResultat, prenomResultat));
            }
        } catch (Exception ex) {
            Logger.getLogger(DbRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    public LinkedList<Object[]> getGrillesHoraires() throws Exception{
        LinkedList<Object[]> listeGrilles = new LinkedList<>();
        String request = "SELECT numéroSemaine, dateDebut, dateFin, dateCréation, dateModification, idVehicule, idLieu, année FROM grillehoraire";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            int numéroSemaine = rs.getInt("numéroSemaine");
            Timestamp dateDebut = rs.getTimestamp("dateDebut");
            String dateDebutS = EasyDate.getDateOnly(dateDebut);
            Timestamp dateFin = rs.getTimestamp("dateFin");
            String dateFinS = EasyDate.getDateOnly(dateFin);
            Timestamp dateCréation = rs.getTimestamp("dateCréation");
            String dateCréationS = EasyDate.getDateHour(dateCréation);
            Timestamp dateModification = rs.getTimestamp("dateModification");
            String dateModificationS = EasyDate.getDateHour(dateModification);
            int idVehicule = rs.getInt("idVehicule");
            Vehicule vehicule = getVehicule(idVehicule);
            int idLieu = rs.getInt("idLieu");
            String lieu = getLieu(idLieu);
            int année = rs.getInt("année");

            Object[] data = {String.valueOf(numéroSemaine), String.valueOf(année), dateDebutS, dateFinS, dateCréationS, dateModificationS, vehicule.getNom(), lieu};
            listeGrilles.add(data);
        }
        return listeGrilles;
    }

    public int insertGrilleHoraire(Grille grille) throws Exception{
        if(grille.getSemaine() == -1 && grille.getDateDebut() == null && grille.getDateFin() == null && grille.getVehicule() == null && grille.getLieu() == null && grille.getAnnee() == -1){
            return -1;
        }
        int idGrilleHoraire = getIdGrilleHoraire(grille.getSemaine(), grille.getAnnee(), grille.getVehicule().getNom(), grille.getLieu());
        if(idGrilleHoraire != -1){
            return idGrilleHoraire;
        }
        int idVehicule = insertVehicule(grille.getVehicule());
        int idLieu = insertLieu(grille.getLieu());

        String request = "INSERT INTO grilleHoraire"
                + "(dateCréation, dateModification, numéroSemaine, dateDebut, dateFin, idVehicule, idLieu, locked, année)"
                + " VALUES(now(), now(), ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addInt(grille.getSemaine());
        params.addDate(EasyDate.getDateOnly(grille.getDateDebut()));
        params.addDate(EasyDate.getDateOnly(grille.getDateFin()));
        params.addInt(idVehicule);
        params.addInt(idLieu);
        params.addInt(0);
        params.addInt(grille.getAnnee());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM grilleHoraire";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idGrilleHoraire = rs.getInt("id");
        }
        return idGrilleHoraire;
    }

    private int insertVehicule(Vehicule vehicule) throws Exception{
        if(vehicule == null){
            return -1;
        }
        int idVehicule = getIdVehicule(vehicule.getNom());
        if(idVehicule != -1){
            return idVehicule;
        }
        String request = "INSERT INTO Vehicules(nom, numeroPlaque) VALUES(?, ?)";
        Parametres params = new Parametres();
        params.addString(vehicule.getNom());
        params.addString(vehicule.getNumeroPlaque());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Vehicules";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idVehicule = rs.getInt("id");
        }
        return idVehicule;
    }

    private int insertLieu(String lieu) throws Exception{
        if(lieu == null){
            return -1;
        }
        int idLieu = getIdLieu(lieu);
        if(idLieu != -1){
            return idLieu;
        }
        String request = "INSERT INTO Lieux(nom) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(lieu);

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Lieux";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idLieu = rs.getInt("id");
        }
        return idLieu;
    }

    public int insertCellule(CelluleGrille cellule, int idGrilleHoraire) throws Exception{
        if(cellule.getDate() == null || cellule.getHeureDebut() == null || cellule.getHeureFin() == null || cellule.getRole() == null|| cellule.getJour() == null || idGrilleHoraire == -1){
            return -1;
        }
        int idCellule = getIdCellule(idGrilleHoraire, cellule.getDate(), cellule.getHeureDebut(), cellule.getHeureFin(), cellule.getRole(), cellule.getRow(), cellule.getColumn());
        if(idCellule != -1){
            return idCellule;
        }
        String request = "INSERT INTO caseHoraire(idGrilleHoraire, date, heureDebutPrestation, heureFinPrestation, role, numRow, numColumn, jour, detail) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);
        params.addDate(EasyDate.getDateOnly(cellule.getDate()));
        params.addDateHour(cellule.getHeureDebut());
        params.addDateHour(cellule.getHeureFin());
        params.addString(cellule.getRole());
        params.addInt(cellule.getRow());
        params.addInt(cellule.getColumn());
        params.addString(cellule.getJour());
        params.addString(cellule.getDetail());

        mysql.pUpdate(request, params);

        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id', idCaseHoraire FROM caseHoraire";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idCellule = rs.getInt("idCaseHoraire");
        }
        return idCellule;
    }

    public int getIdCellule(int idGrilleHoraire, Date date, Date heureDebut, Date heureFin, String role, int row, int column) throws Exception{
        if(idGrilleHoraire == -1 || date == null || heureDebut == null|| heureFin == null || role == null){
            return -1;
        }
        int idCellule = -1;
        String request = "SELECT idCaseHoraire FROM CaseHoraire WHERE idGrilleHoraire = ? AND date = ? AND heureDebutPrestation = ? AND heureFinPrestation = ? AND role = ? AND numrow = ? AND numcolumn = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);
        params.addDate(date);
        params.addDateHour(heureDebut);
        params.addDateHour(heureFin);
        params.addString(role);
        params.addInt(row);
        params.addInt(column);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idCellule = rs.getInt("idCaseHoraire");
        }
        return idCellule;
    }

    public int EditCellule(CelluleGrille oldCellule, int idGrilleHoraire, CelluleGrille newCellule) throws Exception{
        if(idGrilleHoraire == -1 || oldCellule.getHeureDebut() == null || oldCellule.getHeureFin() == null || oldCellule.getDate() == null || newCellule.getHeureDebut() == null || newCellule.getHeureFin() == null){
            return -1;
        }
        int idCellule = getIdCellule(idGrilleHoraire, oldCellule.getDate(), oldCellule.getHeureDebut(), oldCellule.getHeureFin(), oldCellule.getRole(), oldCellule.getRow(), oldCellule.getColumn());
        if(idCellule == -1){
            return -1;
        }

        String request = "UPDATE caseHoraire SET heureDebutPrestation = ?, heureFinPrestation = ?, detail = ? WHERE idCaseHoraire = ?";
        Parametres params = new Parametres();
        params.addDateHour(newCellule.getHeureDebut());
        params.addDateHour(newCellule.getHeureFin());
        params.addString(newCellule.getDetail());
        params.addInt(idCellule);

        mysql.pUpdate(request, params);
        return idCellule;
    }

    public void insertAssignationCaseHoraire(int idCaseHoraire, String matricule) throws Exception{
        if(idCaseHoraire != -1 && matricule != null){
            String request = "INSERT INTO AssignationCaseHoraire(matricule, idCaseHoraire) VALUES(?, ?)";
            Parametres params = new Parametres();
            params.addString(matricule);
            params.addInt(idCaseHoraire);

            mysql.pUpdate(request, params);
        }
    }

    public void EditAssignationCaseHoraire(int idCaseHoraire, String matricule) throws Exception{
        if(idCaseHoraire != -1 && matricule != null && !matricule.isEmpty()){
            String request = "DELETE FROM AssignationCaseHoraire WHERE idCaseHoraire = ?";
            Parametres params = new Parametres();
            params.addInt(idCaseHoraire);
            mysql.pUpdate(request, params);

            request = "INSERT INTO AssignationCaseHoraire(matricule, idCaseHoraire) VALUES(?, ?)";
            params = new Parametres();
            params.addString(matricule);
            params.addInt(idCaseHoraire);
            mysql.pUpdate(request, params);
        }else if (idCaseHoraire != -1 && (matricule == null || matricule.isEmpty())){
            String request = "DELETE FROM AssignationCaseHoraire WHERE idCaseHoraire = ?";
            Parametres params = new Parametres();
            params.addInt(idCaseHoraire);
            mysql.pUpdate(request, params);
        }
    }

    public int getIdGrilleHoraire(int semaine, int année, String vehicule, String lieu) throws Exception{
        if(semaine == -1 || année == -1 || vehicule == null || lieu == null){
            return -1;
        }
        int idVehicule = getIdVehicule(vehicule);
        int idLieu = getIdLieu(lieu);
        int idGrille = -1;
        String request = "SELECT idGrilleHoraire FROM GrilleHoraire WHERE numéroSemaine = ? AND année = ? AND idVehicule = ? AND idLieu = ?";
        Parametres params = new Parametres();
        params.addInt(semaine);
        params.addInt(année);
        params.addInt(idVehicule);
        params.addInt(idLieu);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idGrille = rs.getInt("idGrilleHoraire");
        }
        return idGrille;
    }

    public Grille getGrille(int idGrilleHoraire) throws Exception{
        if(idGrilleHoraire == -1){
            return null;
        }
        Grille grille = new Grille();
        String request = "SELECT numéroSemaine, dateDebut, dateFin, idVehicule, idLieu, année FROM GrilleHoraire WHERE idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            int numeroSemaine = rs.getInt("numéroSemaine");
            java.util.Date dateDebut = rs.getDate("dateDebut");
            //String dateDebut = EasyDate.getDateOnly(rs.getString("dateDebut"));
            java.util.Date dateFin = rs.getDate("dateFin");
            //String dateFin = EasyDate.getDateOnly(rs.getString("dateFin"));
            int idVehicule = rs.getInt("idVehicule");
            Vehicule vehicule = getVehicule(idVehicule);
            int idLieu = rs.getInt("idLieu");
            String lieu = getLieu(idLieu);
            int annee = rs.getInt("année");

            grille.setAnnee(annee);
            grille.setSemaine(numeroSemaine);
            grille.setDateDebut(dateDebut);
            grille.setDateFin(dateFin);
            grille.setLieu(lieu);
            grille.setVehicule(vehicule);
        }
        return grille;
    }

    public LinkedList<Key> getCellules(int idGrilleHoraire) throws Exception{
        LinkedList<Key> cellules = new LinkedList<>();
        String request = "SELECT date, heureDebutPrestation, heureFinPrestation, role, numrow, numcolumn, jour, detail FROM CaseHoraire WHERE idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            Date date = rs.getTimestamp("date");
            Date heureDebutPrestation = rs.getTimestamp("heureDebutPrestation");
            Date heureFinPrestation = rs.getTimestamp("heureFinPrestation");
            String role = rs.getString("role");
            int row = rs.getInt("numRow");
            int column = rs.getInt("numColumn");
            String jour = rs.getString("jour");
            String detail = rs.getString("detail");

            CelluleGrille cellule = new CelluleGrille();
            cellule.setJour(jour);
            cellule.setRole(role);
            cellule.setColumn(column);
            cellule.setRow(row);
            cellule.setDate(date);
            cellule.setHeureDebut(heureDebutPrestation);
            cellule.setHeureFin(heureFinPrestation);
            cellule.setDetail(detail);
            Key key = new Key(row, column, cellule);
            cellules.add(key);
        }
        return cellules;
    }

    public void getVolontaireGrille(Grille grille, int idGrilleHoraire) throws Exception{
        if(grille == null || idGrilleHoraire == -1){
            return;
        }
        String request = "SELECT CaseHoraire.idCaseHoraire, date, heureDebutPrestation, heureFinPrestation, role, numRow, numColumn, jour, nom, prenom, detail FROM (CaseHoraire INNER JOIN assignationCaseHoraire ON(CaseHoraire.idCaseHoraire = assignationCaseHoraire.idCaseHoraire)) INNER JOIN volontaires ON(volontaires.matricule = assignationCaseHoraire.matricule) WHERE CaseHoraire.idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            int row = rs.getInt("numRow");
            int column = rs.getInt("numColumn");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String detail = rs.getString("detail");
            String nomComplet = nom + " " + prenom + " (" + detail + ")";
            for(Key keys : grille.getGrilles()){
                if(keys.getX() == row && keys.getY() == column){
                    keys.getValue().setNomPrenom(nomComplet);
                    break;
                }
            }
        }
    }

    public int editGrilleHoraire(Grille oldGrille, Grille newGrille) throws Exception{
        int idGrille = getIdGrilleHoraire(oldGrille.getSemaine(), oldGrille.getAnnee(), oldGrille.getVehicule().getNom(), oldGrille.getLieu());
        if(idGrille == -1){
            return idGrille;
        }
        String request = "UPDATE GrilleHoraire SET dateModification = now(), numéroSemaine = ?, dateDebut = ?, dateFin = ?, idVehicule = ?, idLieu = ?, année = ? WHERE idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(newGrille.getSemaine());
        params.addDate(newGrille.getDateDebut());
        params.addDate(newGrille.getDateFin());
        params.addInt(getIdVehicule(newGrille.getVehicule().getNom()));
        params.addInt(getIdLieu(newGrille.getLieu()));
        params.addInt(newGrille.getAnnee());
        params.addInt(idGrille);

        mysql.pUpdate(request, params);
        return idGrille;
    }

    public boolean checkLockGrille(int semaine, int annee, String nomVehicule, String lieu) throws Exception{
        int idVehicule = getIdVehicule(nomVehicule);
        String request = "SELECT locked FROM GrilleHoraire WHERE numéroSemaine = ? AND idVehicule = ? AND année = ? and idlieu = ?";
        int locked = -1;
        int idLieu = getIdLieu(lieu);
        Parametres params = new Parametres();
        params.addInt(semaine);
        params.addInt(idVehicule);
        params.addInt(annee);
        params.addInt(idLieu);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            locked = rs.getInt("locked");
        }
        if(locked == 0){
            return false;
        }else{
            return true;
        }
    }

    public void lockGrille(int semaine, int année, String nomVehicule, String lieu) throws Exception{
        int idVehicule = getIdVehicule(nomVehicule);
        int idLieu = getIdLieu(lieu);
        String request = "UPDATE GrilleHoraire SET locked = 1 WHERE numéroSemaine = ? AND année = ? AND idVehicule = ? AND idLieu = ?";
        Parametres params = new Parametres();
        params.addInt(semaine);
        params.addInt(année);
        params.addInt(idVehicule);
        params.addInt(idLieu);

        mysql.pUpdate(request, params);
    }

    public void unlockGrille(int semaine, int année, Vehicule vehicule, String lieu) throws Exception{
        int idVehicule = getIdVehicule(vehicule.getNom());
        int idLieu = getIdLieu(lieu);
        String request = "UPDATE GrilleHoraire SET locked = 0 WHERE numéroSemaine = ? AND année = ? AND idVehicule = ? AND idLieu = ?";
        Parametres params = new Parametres();
        params.addInt(semaine);
        params.addInt(année);
        params.addInt(idVehicule);
        params.addInt(idLieu);

        mysql.pUpdate(request, params);
    }

    public void unlockGrille(int idGrilleHoraire)  throws Exception{
        String request = "UPDATE GrilleHoraire SET locked = 0 WHERE idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);

        mysql.pUpdate(request, params);
    }

    public void checkAlreadyLogged(String login)  throws Exception{
        String request = "SELECT connected FROM Utilisateurs where login = ?";
        Parametres params = new Parametres();
        params.addString(login);
        ResultSet rs = mysql.pSelect(request, params);
        int connected = 0;
        while(rs.next()){
            connected = rs.getInt("connected");
        }
        if(connected == 1){
            throw new Exception("Utilisateur déja connecté");
        }
    }

    public void userLogged(int idUser) throws Exception{
        String request = "UPDATE Utilisateurs SET connected = 1, dateDerniereConnexion = now() where idUtilisateur = ?";
        Parametres params = new Parametres();
        params.addInt(idUser);

        mysql.pUpdate(request, params);
    }

    public void userUnlogged(int idUser) throws Exception{
        String request = "UPDATE Utilisateurs SET connected = 0 where idUtilisateur = ?";
        Parametres params = new Parametres();
        params.addInt(idUser);

        mysql.pUpdate(request, params);
    }

    public Volontaire getVolontaire(String matricule) throws Exception{
        Volontaire volontaire = new Volontaire();
        Identite identite = getIdentite(matricule);
        Decouverte decouverte = getDecouverte(matricule);
        Complementaire complementaire = getComplementaire(matricule);
        Adresse adresse = getAdresseLegale(matricule);
        Residence residence = getAdresseResidence(matricule);
        Telephone telephone = getTelephone(matricule);
        Urgence urgence = getPersonneUrgence(matricule);
        Formations formations = getFormations(matricule);
        Activite activite = getActivite(matricule);
        volontaire.setIdentite(identite);
        volontaire.setDecouverte(decouverte);
        volontaire.setComplementaire(complementaire);
        volontaire.setAdresse(adresse);
        volontaire.setResidence(residence);
        volontaire.setTelephone(telephone);
        volontaire.setUrgence(urgence);
        volontaire.setFormations(formations);
        volontaire.setActivite(activite);
        return volontaire;
    }

    public String[] getVolontaireAndroid(String matricule) throws Exception{
        Identite identite = getIdentite(matricule);
        if(identite == null){
            return null;
        }
        Adresse adresse = getAdresseLegale(matricule);
        Telephone telephone = getTelephone(matricule);

        String email = adresse.getEmail();

        String gsm = null;
        String aadresse = null;
        String aadresse2 = null;

        if(telephone == null){
            gsm = "Pas de gsm";
        }else{
            gsm = telephone.getGsm();
        }

        if(email == null || email.isEmpty()){
            email = "Pas d'email";
        }

        if(gsm == null || gsm.isEmpty()){
            gsm = "Pas de gsm";
        }

        if(adresse == null){
            aadresse = "Pas d'adresse";
            aadresse2 = "";
        }else{
            if(adresse.getRue() != null && !adresse.getRue().isEmpty() && adresse.getNuméro() != null && !adresse.getNuméro().isEmpty() &&
                    adresse.getCodePostal() != null && !adresse.getCodePostal().isEmpty() && adresse.getVille() != null && !adresse.getVille().isEmpty()){
                aadresse = adresse.getRue() + " n°" + adresse.getNuméro();
                aadresse2 = adresse.getCodePostal() + " " + adresse.getVille();
            }else{
                aadresse = "Pas d'adresse";
            aadresse2 = "";
            }
        }

        String[] retour = {
            "Matricule: " +identite.getMatricule(),
            "Nom: " +identite.getNom(),
            "Prenom: " + identite.getPrenom(),
            "Sexe: " + identite.getSexe() ,
            "Date de naissance: " + EasyDate.getDateOnly(identite.getDateNaissance()),
            aadresse,
            aadresse2,
            email,
            gsm
        };
        return retour;
    }

    public Identite getIdentite(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        Identite identite = null;
        String request = "SELECT nom, nomEpouse, prenom, dateNaissance, sexe, photo, completed, permanent FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nom = rs.getString("nom");
            String nomEpouse = rs.getString("nomEpouse");
            String prenom = rs.getString("prenom");
            Timestamp date = rs.getTimestamp("dateNaissance");
            String sexe = rs.getString("sexe");
            java.sql.Blob photo= rs.getBlob("photo");
            int completed = rs.getInt("completed");
            int permanent = rs.getInt("permanent");

            identite = new Identite();
            identite.setNom(nom);
            identite.setNomJeuneFille(nomEpouse);
            identite.setPrenom(prenom);
            identite.setSexe(sexe);
            if(date != null){
                identite.setDateNaissance(new Date(date.getTime()));
            }
            identite.setBlobPhoto(photo);
            identite.setCompleted((completed == 1 ? true : false));
            identite.setPermanent(permanent);
            identite.setMatricule(matricule);
        }
        return identite;
    }

    public Decouverte getDecouverte(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        Decouverte decouverte = null;
        String request = "SELECT description FROM Decouverte WHERE idDecouverte = (SELECT idDecouverte FROM Volontaires WHERE matricule = ?)";
        Parametres params = new Parametres();
        params.addString(matricule);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String description = rs.getString("description");
            decouverte = new Decouverte();
            decouverte.setDescription(description);
        }
        return decouverte;
    }

    private Complementaire getComplementaire(String matricule) throws Exception {
        if(matricule == null){
            return null;
        }
        int idRenseignement = getIdRenseignement(matricule);
        if(idRenseignement == -1){
            return null;
        }

        Complementaire complementaire = new Complementaire();
        String request = "SELECT nom FROM Langue WHERE idLangue =(SELECT idLangueMaternelle FROM Renseignements WHERE idRenseignement = ?)";
        Parametres params = new Parametres();
        params.addInt(idRenseignement);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String langueMaternelle = rs.getString("nom");
            complementaire.setLangueMaternelle(langueMaternelle);
        }

        request = "SELECT nom FROM Langue WHERE idLangue IN(SELECT idLangue FROM LanguesConnue WHERE idRenseignements = ?)";
        params = new Parametres();
        params.addInt(idRenseignement);
        rs = mysql.pSelect(request, params);
        while(rs.next()){
            complementaire.addLangue(rs.getString("nom"));
        }

        request = "SELECT activitePro, SituationActuelle, Diplome, Categorie, DateObtention, permanent, selectionMedicale, dateValidite, numCompteBancaire FROM Renseignements WHERE idRenseignement = ?";
        params = new Parametres();
        params.addInt(idRenseignement);
        rs = mysql.pSelect(request, params);
        while(rs.next()){
            String activitePro = rs.getString("activitePro");
            String activite = rs.getString("SituationActuelle");
            String diplome = rs.getString("Diplome");
            String categorie = rs.getString("Categorie");
            Timestamp dateObtention = rs.getTimestamp("DateObtention");
            int permanent = rs.getInt("permanent");
            int selectionMedicale = rs.getInt("selectionMedicale");
            String dateValidite = rs.getString("dateValidite");
            String numCompteBancaire = rs.getString("numCompteBancaire");

            complementaire.setActivitePro(activitePro);
            complementaire.setActivite(activite);
            complementaire.setQualification(diplome);
            complementaire.setCategorie(categorie);
            if(dateObtention != null){
                java.util.Date dateUtil = new Date(dateObtention.getTime());
                complementaire.setDateObtention(dateUtil);
            }
            complementaire.setPermanent((permanent == 1 ? true : false));
            complementaire.setSelectionMedicale((selectionMedicale == 1 ? true : false));
            complementaire.setDateValidité(dateValidite);
            complementaire.setNumCompteBancaire(numCompteBancaire);
        }
        return complementaire;
    }

    public Adresse getAdresseLegale(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        Adresse adresse = null;
        int idAdresseLegale = getIdAdresseLegale(matricule);
        if(idAdresseLegale == -1){
            return null;
        }
        String request = "SELECT rueAvenueBd, numero, codePostal, boite, idPays, idVille FROM Adresse WHERE idAdresse = ?";
        Parametres params = new Parametres();
        params.addInt(idAdresseLegale);

        ResultSet rs = mysql.pSelect(request, params);
        int idPays = -1;
        int idVille = -1;
        while(rs.next()){
            String rue = rs.getString("rueAvenueBd");
            String numero = rs.getString("numero");
            String codePostal = rs.getString("codePostal");
            String boite = rs.getString("boite");
            idPays = rs.getInt("idPays");
            idVille = rs.getInt("idVille");


            adresse = new Adresse();
            adresse.setRue(rue);
            adresse.setNuméro(numero);
            adresse.setCodePostal(codePostal);
            adresse.setBoite(boite);
        }

        if(idPays != -1){
            request = "SELECT nomPays FROM Pays WHERE idPays = ?";
            params = new Parametres();
            params.addInt(idPays);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                String pays = rs.getString("nomPays");
                if(adresse == null){
                    adresse = new Adresse();
                }
                adresse.setPays(pays);
            }
        }
        if(idVille != -1){
            request = "SELECT nomVille FROM Ville WHERE idVille = ?";
            params = new Parametres();
            params.addInt(idVille);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                String ville = rs.getString("nomVille");
                if(adresse == null){
                    adresse = new Adresse();
                }
                adresse.setVille(ville);
            }
        }
        request = "SELECT email FROM Volontaires WHERE matricule = ?";
        params = new Parametres();
        params.addString(matricule);
        rs = mysql.pSelect(request, params);
        while(rs.next()){
            String email = rs.getString("email");
            if(adresse == null){
                adresse = new Adresse();
            }
            adresse.setEmail(email);
        }
        return adresse;
    }

    public Residence getAdresseResidence(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        Residence residence = null;
        int idAdresseResidence = getIdAdresseResidence(matricule);
        if(idAdresseResidence == -1){
            return null;
        }
        String request = "SELECT rueAvenueBd, numero, codePostal, boite, idPays, idVille FROM Adresse WHERE idAdresse = ?";
        Parametres params = new Parametres();
        params.addInt(idAdresseResidence);

        ResultSet rs = mysql.pSelect(request, params);
        int idPays = -1;
        int idVille = -1;
        while(rs.next()){
            String rue = rs.getString("rueAvenueBd");
            String numero = rs.getString("numero");
            String codePostal = rs.getString("codePostal");
            String boite = rs.getString("boite");
            idPays = rs.getInt("idPays");
            idVille = rs.getInt("idVille");


            residence = new Residence();
            residence.setRue(rue);
            residence.setNuméro(numero);
            residence.setCodePostal(codePostal);
            residence.setBoite(boite);
        }
        if(idPays != -1){
            request = "SELECT nomPays FROM Pays WHERE idPays = ?";
            params = new Parametres();
            params.addInt(idPays);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                String pays = rs.getString("nomPays");
                if(residence == null){
                    residence = new Residence();
                }
                residence.setPays(pays);
            }
        }
        if(idVille != -1){
            request = "SELECT nomVille FROM Ville WHERE idVille = ?";
            params = new Parametres();
            params.addInt(idVille);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                String ville = rs.getString("nomVille");
                if(residence == null){
                    residence = new Residence();
                }
                residence.setVille(ville);
            }
        }
        return residence;
    }

    private Telephone getTelephone(String matricule) throws Exception {
        if(matricule == null){
            return null;
        }
        int idTelephone = getIdTelephone(matricule);
        if(idTelephone == -1){
            return null;
        }
        Telephone telephone = null;
        String request = "SELECT gsm, autreGsm, telephoneFix, telephonePro, fax FROM Telephone WHERE idTelephone = ?";
        Parametres params = new Parametres();
        params.addInt(idTelephone);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String gsm = rs.getString("gsm");
            String autreGsm = rs.getString("autreGsm");
            String telephoneFix = rs.getString("telephoneFix");
            String telephonePro = rs.getString("telephonePro");
            String fax = rs.getString("fax");
            telephone = new Telephone();
            telephone.setGsm(gsm);
            telephone.setAutreGsm(autreGsm);
            telephone.setTelephoneFix(telephoneFix);
            telephone.setTelephoneProfesionnelle(telephonePro);
            telephone.setFax(fax);
        }
        return telephone;
    }

    private Urgence getPersonneUrgence(String matricule) throws Exception {
        if(matricule == null){
            return null;
        }
        int idUrgence = getIdPersonneUrgence(matricule);
        if(idUrgence == -1){
            return null;
        }
        Urgence urgence = null;
        String request = "SELECT nom, prenom, telephone FROM PersonneUrgence WHERE idPersonneUrgence = ?";
        Parametres params = new Parametres();
        params.addInt(idUrgence);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String telephone = rs.getString("telephone");
            urgence = new Urgence();
            urgence.setNom(nom);
            urgence.setPrenom(prenom);
            urgence.setTelephone(telephone);
        }
        return urgence;
    }

    private Formations getFormations(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        Formations formations = null;
        String request = "SELECT idFormation FROM FormationsSuivie WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        LinkedList<Integer> listeId = new LinkedList<>();
        while(rs.next()){
            int idFormation = rs.getInt("idFormation");
            listeId.add(idFormation);
        }
        if(!listeId.isEmpty()){
            formations = new Formations();
        }
        for(int idFormation : listeId){
            Formation formation = getFormation(idFormation);
            formations.addFormation(formation);
        }
        return formations;
    }

    private Activite getActivite(String matricule) throws Exception{
        if(matricule == null){
            return null;
        }
        int idActivite = getIdActivite(matricule);
        if(idActivite == -1){
            return null;
        }
        Activite activite = new Activite();

        String request = "SELECT dateDebut, fragmentDateDebut, centreSecours, sisu, copiePermis, numeroRegistreNational FROM Activite WHERE idActivite = ?";
        Parametres params = new Parametres();
        params.addInt(idActivite);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            Timestamp dateDebut = rs.getTimestamp("dateDebut");
            String fragmentDateDebut = rs.getString("fragmentDateDebut");
            String centreSecours = rs.getString("centreSecours");
            String sisu = rs.getString("sisu");
            java.sql.Blob copiePermis = rs.getBlob("copiePermis");
            String numeroRegistreNational = rs.getString("numeroRegistreNational");

            if(dateDebut != null){
                activite.setDateDebut(new Date(dateDebut.getTime()));
            }
            activite.setFragmentDateDebut(fragmentDateDebut);
            activite.setCentreSecour(centreSecours);
            activite.setSisu(sisu);
            activite.setPermisBlob(copiePermis);
            activite.setNumeroRegistre(numeroRegistreNational);
        }

        LinkedList<Integer> listeId = getIdFormationActivite(matricule);
        for(int idFormationActivite : listeId){
            ActiviteFormation activiteFormation = getActiviteFormation(idFormationActivite);
            activite.addFormation(activiteFormation);
        }

        return activite;
    }

    private ActiviteFormation getActiviteFormation(int idFormationActivite) throws Exception{
        if(idFormationActivite == -1){
            return null;
        }
        ActiviteFormation activiteFormation = new ActiviteFormation();
        String request = "SELECT nomFormationActivite, status FROM FormationActivite WHERE idFormationActivite = ?";
        Parametres params = new Parametres();
        params.addInt(idFormationActivite);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nomFormationActivite = rs.getString("nomFormationActivite");
            String status = rs.getString("status");
            activiteFormation.setNom(nomFormationActivite);
            activiteFormation.setStatus(status);
        }
        return activiteFormation;
    }

    private Formation getFormation(int idFormation) throws Exception{
        if(idFormation == -1){
            return null;
        }
        Formation formation = null;
        String request = "SELECT nomFormation, dateObtention, dateExpiration, dateExamen, numero, lieu, copie, numeroService112, permanent, fragmentDateObtention, fragmentDateExpiration, fragmentDateExamen FROM Formation WHERE idFormation = ?";
        Parametres params = new Parametres();
        params.addInt(idFormation);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nomFormation = rs.getString("nomFormation");
            Timestamp dateObtention = rs.getTimestamp("dateObtention");
            Timestamp dateExpiration = rs.getTimestamp("dateExpiration");
            Timestamp dateExamen = rs.getTimestamp("dateExamen");
            String numero = rs.getString("numero");
            String lieu = rs.getString("lieu");
            java.sql.Blob photocopie= rs.getBlob("copie");
            String numeroService112 = rs.getString("numeroService112");
            boolean permanent = (rs.getInt("permanent") == 1 ? true : false);
            String fragmentDateObtention = rs.getString("fragmentDateObtention");
            String fragmentDateExpiration = rs.getString("fragmentDateExpiration");
            String fragmentDateExamen = rs.getString("fragmentDateExamen");

            formation = new Formation();
            formation.setNom(nomFormation);
            if(dateObtention != null){
                formation.setDateObtention(new Date(dateObtention.getTime()));
            }
            if(dateExpiration != null){
                formation.setDatePeremption(new Date(dateExpiration.getTime()));
            }
            if(dateExamen != null){
                formation.setDateExamen(new Date(dateExamen.getTime()));
            }
            formation.setNumero(numero);
            formation.setLieu(lieu);
            formation.setBlobPhotocopieInBlob(photocopie);
            formation.setNumeroService112(numeroService112);
            formation.setPermanent(permanent);
            formation.setFragmentDateObtention(fragmentDateObtention);
            formation.setFragmentDateExpiration(fragmentDateExpiration);
            formation.setFragmentDateExamen(fragmentDateExamen);
        }
        return formation;
    }

    public void editIdentite(String matricule, Identite identite) throws Exception{
        if(identite == null || matricule == null){
            return;
        }
        String request = "UPDATE volontaires SET nom = ?, prenom = ?, nomEpouse = ?, dateNaissance = ?, sexe = ?, photo = ?, completed = ?, permanent = ? WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(identite.getNom());
        params.addString(identite.getPrenom());
        params.addString(identite.getNomJeuneFille());
        params.addDate(identite.getDateNaissance());
        params.addString(identite.getSexe());
        params.addBlob(identite.getPhoto());
        params.addInt((identite.isCompleted() ? 1 : 0));
        params.addInt((identite.isPermanent() ? 1 : 0));
        params.addString(matricule);
        mysql.pUpdate(request, params);
    }

    public void editDecouverte(String matricule, Decouverte decouverte) throws Exception{
        if(decouverte == null){
            return;
        }
        int idDecouverte = getIdDecouverte(decouverte.getDescription());
        if(idDecouverte == -1){
            //Ancienne façon d'insérer des données
            //idDecouverte = insertDecouvertes(decouverte.getDescription());
            idDecouverte = insertDecouvertes(decouverte);
        }
        String request = "UPDATE Volontaires SET idDecouverte = ?";
        Parametres params = new Parametres();
        params.addInt(idDecouverte);
        mysql.pUpdate(request, params);
    }

    public void editComplementaire(String matricule, Complementaire complementaire) throws Exception {
        if(complementaire == null || matricule == null){
            return;
        }
        int idRenseignement = getIdRenseignement(matricule);
        if(idRenseignement != -1){
            String request = "DELETE FROM LanguesConnue WHERE idRenseignements = ?";
            Parametres params = new Parametres();
            params.addInt(idRenseignement);
            mysql.pUpdate(request, params);

            int idLangueMaternelle = insertLangue(complementaire.getLangueMaternelle());

            request = "UPDATE Renseignements SET activitePro = ?, SituationActuelle = ?, Diplome = ?, Categorie = ?, DateObtention = ?, permanent = ?, selectionMedicale = ?, dateValidite = ?, numCompteBancaire = ?, idLangueMaternelle = ? WHERE idRenseignement = ?";
            params = new Parametres();
            params.addString(complementaire.getActivitePro());
            params.addString(complementaire.getActivite());
            params.addString(complementaire.getQualification());
            params.addString(complementaire.getCategorie());
            params.addDate(complementaire.getDateObtention());
            params.addInt((complementaire.isPermanent() ? 1 : 0));
            params.addInt((complementaire.isSelectionMedicale() ? 1 : 0));
            params.addString(complementaire.getDateValidité());
            params.addString(complementaire.getNumCompteBancaire());
            params.addInt(idLangueMaternelle);
            params.addInt(idRenseignement);

            mysql.pUpdate(request, params);

            for(String langue : complementaire.getListeLangue()){
                int idLangue = insertLangue(langue);
                request = "INSERT INTO LanguesConnue(idRenseignements, idLangue) VALUES(?, ?)";
                params = new Parametres();
                params.addInt(idRenseignement);
                params.addInt(idLangue);
                mysql.pUpdate(request, params);
            }
        }else{
            insertRenseignement(complementaire);
        }
    }

    public void editAdresseLegale(String matricule, Adresse adresse) throws Exception {
        if(adresse == null || matricule == null){
            return;
        }
        int idPays = insertPays(adresse.getPays());
        int idVille = insertVille(adresse.getVille());

        int idAdresse = getIdAdresseLegale(matricule);
        if(idAdresse == -1){
            insertAdresseLegale(adresse, matricule);

            String request = "UPDATE Volontaires SET email = ? WHERE matricule = ?";
            Parametres params = new Parametres();
            params.addString(adresse.getEmail());
            params.addString(matricule);
            mysql.pUpdate(request, params);
        }else{
            String request = "UPDATE Adresse SET rueAvenueBd = ?, numero = ?, codePostal = ?, boite = ?, idPays = ?, idVille = ? WHERE idAdresse = ?";
            Parametres params = new Parametres();
            params.addString(adresse.getRue());
            params.addString(adresse.getNuméro());
            params.addString(adresse.getCodePostal());
            params.addString(adresse.getBoite());
            params.addInt(idPays);
            params.addInt(idVille);
            params.addInt(idAdresse);

            mysql.pUpdate(request, params);

            request = "UPDATE Volontaires SET email = ? WHERE matricule = ?";
            params = new Parametres();
            params.addString(adresse.getEmail());
            params.addString(matricule);
            mysql.pUpdate(request, params);
        }

    }

    public void editAdresseResidence(String matricule, Residence residence) throws Exception {
        if(residence == null || matricule == null){
            return;
        }
        int idPays = insertPays(residence.getPays());
        int idVille = insertVille(residence.getVille());

        int idAdresse = getIdAdresseResidence(matricule);
        if(idAdresse == -1){
            insertAdresseResidence(residence, matricule);
        }else{
            String request = "UPDATE Adresse SET rueAvenueBd = ?, numero = ?, codePostal = ?, boite = ?, idPays = ?, idVille = ? WHERE idAdresse = ?";
            Parametres params = new Parametres();
            params.addString(residence.getRue());
            params.addString(residence.getNuméro());
            params.addString(residence.getCodePostal());
            params.addString(residence.getBoite());
            params.addInt(idPays);
            params.addInt(idVille);
            params.addInt(idAdresse);

            mysql.pUpdate(request, params);
        }
    }

    public void editTelephone(String matricule, Telephone telephone) throws Exception {
        if(telephone == null || matricule == null){
            return;
        }
        int idTelephone = getIdTelephone(matricule);
        if(idTelephone == -1){
            insertTelephone(telephone, matricule);
        }else{
            String request = "UPDATE Telephone SET gsm = ?, autreGsm = ?, telephoneFix = ?, telephonePro = ?, fax = ? WHERE idTelephone = ?";
            Parametres params = new Parametres();
            params.addString(telephone.getGsm());
            params.addString(telephone.getAutreGsm());
            params.addString(telephone.getTelephoneFix());
            params.addString(telephone.getTelephoneProfesionnelle());
            params.addString(telephone.getFax());
            params.addInt(idTelephone);

            mysql.pUpdate(request, params);
        }
    }

    public void editPersonneUrgence(String matricule, Urgence urgence) throws Exception{
        if(urgence == null || matricule == null){
            return;
        }
        int idPersonneUrgence = getIdPersonneUrgence(matricule);
        if(idPersonneUrgence == -1){
            insertPersonneUrgence(urgence, matricule);
        }else{
            String request = "UPDATE PersonneUrgence SET nom = ?, prenom = ?, telephone = ? WHERE idPersonneUrgence = ?";
            Parametres params = new Parametres();
            params.addString(urgence.getNom());
            params.addString(urgence.getPrenom());
            params.addString(urgence.getTelephone());
            params.addInt(idPersonneUrgence);
            mysql.pUpdate(request, params);
        }
    }

    public void editFormations(String matricule, Formations formations) throws Exception{
        if(matricule == null || formations == null){
            return;
        }
        String request = "SELECT idFormation FROM FormationsSuivie WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        LinkedList<Integer> listeId = new LinkedList<>();
        while(rs.next()){
            int idFormation = rs.getInt("idFormation");
            listeId.add(idFormation);
        }

        request = "DELETE FROM FormationsSuivie WHERE matricule = ?";
        mysql.pUpdate(request, params);

        for(int idFormation : listeId){
            request = "DELETE FROM Formation WHERE idFormation = ?";
            params = new Parametres();
            params.addInt(idFormation);
            mysql.pUpdate(request, params);
        }

        insertFormations(formations, matricule);
    }

    public void editActivite(String matricule, Activite activite) throws Exception{
        if(activite == null || matricule == null){
            return;
        }
        int idActivite = getIdActivite(matricule);
        if(idActivite == -1){
            idActivite = insertActivite(activite, matricule);
            return;
        }

        LinkedList<Integer> listeId = getIdFormationActivite(matricule);

        String request = "DELETE FROM FormationSuivieActivite where idActivite = ?";
        Parametres params = new Parametres();
        params.addInt(idActivite);
        mysql.pUpdate(request, params);

        for(int idFormation : listeId){
            request = "DELETE FROM FormationActivite WHERE idFormationActivite = ?";
            params = new Parametres();
            params.addInt(idFormation);
            mysql.pUpdate(request, params);
        }

        for(ActiviteFormation formation: activite.getListeFormation()){
            int idFormation = insertActiviteFormation(formation);
            request = "INSERT INTO FormationSuivieActivite(idActivite, idFormationActivite) VALUES(?, ?)";
            params = new Parametres();
            params.addInt(idActivite);
            params.addInt(idFormation);
            mysql.pUpdate(request, params);
        }

        request = "UPDATE Activite SET dateDebut = ?, fragmentDateDebut = ?, centreSecours = ?, sisu = ?, copiePermis = ?, numeroRegistreNational = ? WHERE idActivite = ?";
        params = new Parametres();
        params.addDate(activite.getDateDebut());
        params.addString(activite.getFragmentDateDebut());
        params.addString(activite.getCentreSecour());
        params.addString(activite.getSisu());
        params.addBlob(activite.getPermis());
        params.addString(activite.getNumeroRegistre());
        params.addInt(idActivite);
        mysql.pUpdate(request, params);
    }


    public int getIdRenseignement(String matricule) throws Exception{
        int idRenseignement = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idRenseignement FRom Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idRenseignement = rs.getInt("idRenseignement");
        }
        return idRenseignement;
    }

    public boolean checkNomPrenom(String nom, String prenom) throws Exception {
        boolean found = false;
        String request = "SELECT nom FROM volontaires where nom = ? AND prenom = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        params.addString(prenom);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            found = true;
        }
        return found;
    }

    public Groupe getGroupe(String nom) throws Exception {
        Groupe groupe = null;
        String request = "SELECT Groupes.niveau, Droits.nom FROM (Groupes LEFT JOIN PossederDroit ON (Groupes.idGroupe = PossederDroit.idGroupe)) LEFT JOIN Droits ON (PossederDroit.idDroit = Droits.idDroit) WHERE Groupes.nomGroupe = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        ResultSet rs = mysql.pSelect(request, params);
        LinkedList<String> listeDroits = new LinkedList<>();
        while(rs.next()){
            int niveau = rs.getInt("niveau");
            String droit = rs.getString("nom");
            if(groupe == null){
                groupe = new Groupe();
                groupe.setNiveau(niveau);
            }
            if(droit != null){
                listeDroits.add(droit);
            }
        }
        if(groupe != null){
            groupe.setDroits(listeDroits);
            groupe.setNom(nom);
        }
        return groupe;
    }

    public void nouveauGroupe(Groupe groupe) throws Exception{
        String request = "INSERT INTO Groupes(nomGroupe, niveau) VALUES(?, ?)";
        Parametres params = new Parametres();
        params.addString(groupe.getNom());
        params.addInt(groupe.getNiveau());
        mysql.pUpdate(request, params);

        int idGroupe = -1;
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Groupes";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idGroupe = rs.getInt("id");
        }

        for(String nomDroit : groupe.getDroits()){
            request = "SELECT idDroit FROM Droits WHERE nom = ?";
            params = new Parametres();
            params.addString(nomDroit);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                int idDroit = rs.getInt("idDroit");
                request = "INSERT INTO PossederDroit(idGroupe, idDroit) VALUES(?, ?)";
                params = new Parametres();
                params.addInt(idGroupe);
                params.addInt(idDroit);
                mysql.pUpdate(request, params);
            }
        }
    }

    public void modifierGroupe(Groupe oldGroupe, Groupe newGroupe) throws Exception{
        int idGroupe = getIdGroupe(oldGroupe.getNom());
        String request = "UPDATE Groupes SET nomGroupe = ?, niveau = ? WHERE idGroupe = ?";
        Parametres params = new Parametres();
        params.addString(newGroupe.getNom());
        params.addInt(newGroupe.getNiveau());
        params.addInt(idGroupe);

        mysql.pUpdate(request, params);

        request = "DELETE FROM PossederDroit WHERE idGroupe = ?";
        params = new Parametres();
        params.addInt(idGroupe);
        mysql.pUpdate(request, params);

        LinkedList<Integer> listeId = new LinkedList<>();
        for(String nomDroit : newGroupe.getDroits()){
            request = "SELECT idDroit FROM Droits WHERE nom = ?";
            params = new Parametres();
            params.addString(nomDroit);
            ResultSet rs = mysql.pSelect(request, params);
            while(rs.next()){
                int idDroit = rs.getInt("idDroit");
                if(!listeId.contains(idDroit)){
                    listeId.add(idDroit);
                }
            }
        }

        for(int idDroit : listeId){
            request = "INSERT INTO PossederDroit(idGroupe, idDroit) VALUES(?, ?)";
            params = new Parametres();
            params.addInt(idGroupe);
            params.addInt(idDroit);
            mysql.pUpdate(request, params);
        }
    }

    private int getIdGroupe(String nom) throws Exception {
        String request = "SELECT idGroupe FROM Groupes WHERE nomGroupe = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        ResultSet rs = mysql.pSelect(request, params);

        int idGroupe = -1;
        while(rs.next()){
            idGroupe = rs.getInt("idGroupe");
        }
        return idGroupe;
    }

    public void supprimerGroupe(String nom) throws Exception{
        int idGroupe = getIdGroupe(nom);

        String request = "DELETE FROM PossederDroit WHERE idGroupe = ?";
        Parametres params = new Parametres();
        params.addInt(idGroupe);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Groupes WHERE idGroupe = ?";
        params = new Parametres();
        params.addInt(idGroupe);
        mysql.pUpdate(request, params);
    }

    public Utilisateur getUtilisateur(int idUser) throws Exception {
        Utilisateur utilisateur = null;
        String request = "Select login, password, dateDerniereConnexion, connected, prioritaire, nomGroupe FROM (Utilisateurs LEFT JOIN Appartenir ON(Utilisateurs.idUtilisateur = Appartenir.idUtilisateur)) LEFT JOIN Groupes ON(Groupes.idGroupe = Appartenir.idGroupe) WHERE Utilisateurs.idUtilisateur = ?";
        Parametres params = new Parametres();
        params.addInt(idUser);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            if(utilisateur == null){
                utilisateur = new Utilisateur();
                String login = rs.getString("login");
                String password = rs.getString("password");
                Timestamp dateDerniereConnexion = rs.getTimestamp("dateDerniereConnexion");
                int connected = rs.getInt("connected");
                int prioritaire = rs.getInt("prioritaire");
                utilisateur.setLogin(login);
                utilisateur.setPassword(password);
                utilisateur.setDateDerniereConnexion(EasyDate.getDateHour(dateDerniereConnexion));
                utilisateur.setConnected(connected);
                utilisateur.setPrioritaire(prioritaire);
            }
            String nomGroupe = rs.getString("nomGroupe");
            utilisateur.addGroupe(nomGroupe);
        }
        return utilisateur;
    }

    public void nouveauUtilisateur(Utilisateur utilisateur) throws Exception {
        String request = "INSERT INTO Utilisateurs(login, password, dateDerniereConnexion, "
                + "connected, prioritaire) VALUES(?, ?, ?, ?, ?)";
        Parametres params = new Parametres();
        params.addString(utilisateur.getLogin());
        params.addString(utilisateur.getPassword());
        params.addDate(utilisateur.getDateDerniereConnexion());
        params.addInt(utilisateur.getConnected());
        params.addInt(utilisateur.getPrioritaire());

        mysql.pUpdate(request, params);

        int idUtilisateur = -1;
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Utilisateurs";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            idUtilisateur = rs.getInt("id");
        }

        for(String nomGroupe : utilisateur.getGroupes()){
            request = "SELECT idGroupe FROM Groupes WHERE nomGroupe = ?";
            params = new Parametres();
            params.addString(nomGroupe);
            rs = mysql.pSelect(request, params);
            while(rs.next()){
                int idGroupe = rs.getInt("idGroupe");
                request = "INSERT INTO Appartenir(idUtilisateur, idGroupe) VALUES(?, ?)";
                params = new Parametres();
                params.addInt(idUtilisateur);
                params.addInt(idGroupe);
                mysql.pUpdate(request, params);
            }
        }
    }

    public void supprimerUtilisateur(String login) throws Exception {
        int idUtilisateur = getIdUser(login);

        String request = "DELETE FROM Appartenir WHERE idUtilisateur = ?";
        Parametres params = new Parametres();
        params.addInt(idUtilisateur);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Utilisateurs WHERE idUtilisateur = ?";
        params = new Parametres();
        params.addInt(idUtilisateur);
        mysql.pUpdate(request, params);
    }

    public void modifierUtilisateur(Utilisateur oldUtilisateur, Utilisateur newUtilisateur) throws Exception {
        int idUtilisateur = getIdUser(oldUtilisateur.getLogin());
        String request = "UPDATE Utilisateurs SET login = ?, password = ?, dateDerniereConnexion = ?, connected = ?, prioritaire = ? WHERE idUtilisateur = ?";
        Parametres params = new Parametres();
        params.addString(newUtilisateur.getLogin());
        params.addString(newUtilisateur.getPassword());
        params.addDate(newUtilisateur.getDateDerniereConnexion());
        params.addInt(newUtilisateur.getConnected());
        params.addInt(newUtilisateur.getPrioritaire());
        params.addInt(idUtilisateur);

        mysql.pUpdate(request, params);

        request = "DELETE FROM Appartenir WHERE idUtilisateur = ?";
        params = new Parametres();
        params.addInt(idUtilisateur);
        mysql.pUpdate(request, params);

        LinkedList<Integer> listeId = new LinkedList<>();
        for(String nomGroupe : newUtilisateur.getGroupes()){
            request = "SELECT idGroupe FROM Groupes WHERE nomGroupe = ?";
            params = new Parametres();
            params.addString(nomGroupe);
            ResultSet rs = mysql.pSelect(request, params);
            while(rs.next()){
                int idGroupe = rs.getInt("idGroupe");
                if(!listeId.contains(idGroupe)){
                    listeId.add(idGroupe);
                }
            }
        }

        for(int idGroupe : listeId){
            request = "INSERT INTO Appartenir(idUtilisateur, idGroupe) VALUES(?, ?)";
            params = new Parametres();
            params.addInt(idUtilisateur);
            params.addInt(idGroupe);
            mysql.pUpdate(request, params);
        }
    }

    public void supprimerVolontaire(String matricule) throws Exception {
        Parametres params = new Parametres();
        params.addString(matricule);

        int idPersonneUrgence = getIdPersonneUrgence(matricule);
        int idPrestation = getIdPrestation(matricule);
        int idRenseignement = getIdRenseignement(matricule);
        LinkedList<Integer> listeIdFormation = getIdFormation(matricule);
        int idAdresseLegale = getIdAdresseLegale(matricule);
        int idAdresseResidence = getIdAdresseResidence(matricule);
        LinkedList<Integer> listeIdFormationActivite = getIdFormationActivite(matricule);
        int idActivite = getIdActivite(matricule);
        int idTelephone = getIdTelephone(matricule);

        String request = "UPDATE Volontaires SET idPersonneUrgence = null, idDecouverte = null, idPrestation = null, idRenseignement = null, idAdresseLegale = null, idAdresseResidence = null, idTelephone = null, idActivite = null WHERE matricule = ?";
        mysql.pUpdate(request, params);

        request = "DELETE FROM PersonneUrgence WHERE idPersonneUrgence = ?;";
        params = new Parametres();
        params.addInt(idPersonneUrgence);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Prestation WHERE idPrestation = ?;";
        params = new Parametres();
        params.addInt(idPrestation);
        mysql.pUpdate(request, params);

        request = "DELETE FROM LanguesConnue WHERE idRenseignements = ?;";
        params = new Parametres();
        params.addInt(idRenseignement);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Renseignements WHERE idRenseignement = ?;";
        params = new Parametres();
        params.addInt(idRenseignement);
        mysql.pUpdate(request, params);

        request = "DELETE FROM FormationsSuivie WHERE matricule = ?";
        mysql.pUpdate(request, params);

        for(int idFormation : listeIdFormation){
            request = "DELETE FROM Formation WHERE idFormation =?;";
            params = new Parametres();
            params.addInt(idFormation);
            mysql.pUpdate(request, params);
        }

        request = "DELETE FROM FormationsSuivie WHERE matricule = ?;";
        params = new Parametres();
        params.addString(matricule);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Adresse WHERE idAdresse = ?;";
        params = new Parametres();
        params.addInt(idAdresseLegale);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Adresse WHERE idAdresse = ?;";
        params = new Parametres();
        params.addInt(idAdresseResidence);
        mysql.pUpdate(request, params);

        request = "DELETE FROM FormationSuivieActivite WHERE idActivite = ?;";
        params = new Parametres();
        params.addInt(idActivite);
        mysql.pUpdate(request, params);

        for(int idFormationActivite : listeIdFormationActivite){
            request = "DELETE FROM FormationActivite WHERE idFormationActivite = ?;";
            params = new Parametres();
            params.addInt(idFormationActivite);
            mysql.pUpdate(request, params);
        }

        request = "DELETE FROM Activite WHERE idActivite = ?;";
        params = new Parametres();
        params.addInt(idActivite);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Telephone WHERE idTelephone = ?;";
        params = new Parametres();
        params.addInt(idTelephone);
        mysql.pUpdate(request, params);

        request = "DELETE FROM AssignationCaseHoraire WHERE matricule = ?";
        params = new Parametres();
        params.addString(matricule);
        mysql.pUpdate(request, params);

        request = "DELETE FROM Volontaires WHERE matricule = ?";
        params = new Parametres();
        params.addString(matricule);
        mysql.pUpdate(request, params);

    }

    private int getIdPersonneUrgence(String matricule) throws Exception {
        int idPersonneUrgence = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idPersonneUrgence FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idPersonneUrgence = rs.getInt("idPersonneUrgence");
        }
        return idPersonneUrgence;
    }

    private int getIdPrestation(String matricule) throws Exception{
        int idPrestation = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idPrestation FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idPrestation = rs.getInt("idPrestation");
        }
        return idPrestation;
    }

    private LinkedList<Integer> getIdFormation(String matricule) throws Exception{
        LinkedList<Integer> listeId = new LinkedList<>();
        if(matricule == null){
            return listeId;
        }
        String request = "SELECT idFormation FROM FormationsSuivie WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            int idFormation = rs.getInt("idFormation");
            listeId.add(idFormation);
        }
        return listeId;
    }

    private int getIdAdresseLegale(String matricule) throws Exception{
        int idAdresseLegale = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idAdresseLegale FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idAdresseLegale = rs.getInt("idAdresseLegale");
        }
        return idAdresseLegale;
    }

    private int getIdAdresseResidence(String matricule) throws Exception{
        int idAdresseResidence = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idAdresseResidence FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idAdresseResidence = rs.getInt("idAdresseResidence");
        }
        return idAdresseResidence;
    }

    private LinkedList<Integer> getIdFormationActivite(String matricule) throws Exception{
        LinkedList<Integer> listeId = new LinkedList<>();
        if(matricule == null){
            return listeId;
        }
        String request = "SELECT idFormationActivite FROM FormationSuivieActivite WHERE idActivite = (SELECT idActivite FROM Volontaires WHERE matricule = ?)";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            int idFormationActivite = rs.getInt("idFormationActivite");
            listeId.add(idFormationActivite);
        }
        return listeId;
    }

    private int getIdActivite(String matricule) throws Exception{
        int idActivite = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idActivite FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idActivite = rs.getInt("idActivite");
        }
        return idActivite;
    }

    private int getIdTelephone(String matricule) throws Exception{
        int idTelephone = -1;
        if(matricule == null){
            return -1;
        }
        String request = "SELECT idTelephone FROM Volontaires WHERE matricule = ?";
        Parametres params = new Parametres();
        params.addString(matricule);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idTelephone = rs.getInt("idTelephone");
        }
        return idTelephone;
    }

    public LinkedList<Object[]> getVehicules() throws Exception{
        LinkedList<Object[]> listeVehicule = new LinkedList<>();
        String request = "SELECT nom, numeroPlaque FROM Vehicules";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String numeroPlaque = rs.getString("numeroPlaque");
            listeVehicule.add(new Object[] {nom, numeroPlaque});
        }
        return listeVehicule;
    }

    public LinkedList<Object[]> getVehicules(int semaine, int annee) throws Exception{
        LinkedList<Object[]> listeVehicule = new LinkedList<>();
        String request = "SELECT nom, numeroPlaque FROM vehicules WHERE idVehicule NOT IN(SELECT idVehicule FROM Reservations WHERE numeroSemaine= ? AND annee = ?)";
        Parametres params = new Parametres();
        params.addInt(semaine);
        params.addInt(annee);
        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nom = rs.getString("nom");
            String numeroPlaque = rs.getString("numeroPlaque");
            listeVehicule.add(new Object[] {nom, numeroPlaque});
        }
        return listeVehicule;
    }

    public Vehicule getVehicule(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return null;
        }
        Vehicule vehicule = null;
        String request = "SELECT nom, numeroPlaque FROM Vehicules WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String numeroPlaque = rs.getString("numeroPlaque");
            vehicule = new Vehicule();
            vehicule.setNom(nom);
            vehicule.setNumeroPlaque(numeroPlaque);
        }
        return vehicule;
    }

    public void nouveauVehicule(Vehicule vehicule) throws Exception{
        if(vehicule == null){
            return;
        }

        String request = "INSERT INTO Vehicules(nom, numeroPlaque) VALUES(?, ?)";
        Parametres params = new Parametres();
        params.addString(vehicule.getNom());
        params.addString(vehicule.getNumeroPlaque());

        mysql.pUpdate(request, params);
    }

    public void supprimerVehicule(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return;
        }
        String request = "DELETE FROM Vehicules WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        mysql.pUpdate(request, params);
    }

    public void modifierVehicule(Vehicule oldVehicule, Vehicule newVehicule) throws Exception{
        if(oldVehicule == null || newVehicule == null){
            return;
        }
        String request = "UPDATE Vehicules SET nom = ?, numeroPlaque = ? WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(newVehicule.getNom());
        params.addString(newVehicule.getNumeroPlaque());
        params.addString(oldVehicule.getNom());

        mysql.pUpdate(request, params);
    }

    public LinkedList<Object[]> getLieux() throws Exception{
        LinkedList<Object[]> listeLieux = new LinkedList<>();
        String request = "SELECT nom FROM Lieux";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            listeLieux.add(new Object[] {nom});
        }
        return listeLieux;
    }

    public String getLieu(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return null;
        }
        String lieu = null;
        String request = "SELECT nom FROM Lieux WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            lieu = nom;
        }
        return lieu;
    }

    public void nouveauLieu(String lieu) throws Exception{
        if(lieu == null || lieu.isEmpty()){
            return;
        }

        String request = "INSERT INTO Lieux(nom) VALUES(?)";
        Parametres params = new Parametres();
        params.addString(lieu);

        mysql.pUpdate(request, params);
    }

    public void modifierLieu(String oldLieu, String newLieu) throws Exception{
        if(oldLieu == null || newLieu == null || oldLieu.isEmpty() || newLieu.isEmpty()){
            return;
        }
        String request = "UPDATE Lieux SET nom = ? WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(newLieu);
        params.addString(oldLieu);

        mysql.pUpdate(request, params);
    }

    public void supprimerLieu(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return;
        }
        String request = "DELETE FROM Lieux WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);
        mysql.pUpdate(request, params);
    }

    private Vehicule getVehicule(int idVehicule) throws Exception{
        if(idVehicule == -1){
            return null;
        }
        Vehicule vehicule = null;
        String request = "SELECT nom, numeroPlaque FROM Vehicules WHERE idVehicule = ?";
        Parametres params = new Parametres();
        params.addInt(idVehicule);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            String nom = rs.getString("nom");
            String numeroPlaque = rs.getString("numeroPlaque");
            vehicule = new Vehicule();
            vehicule.setNom(nom);
            vehicule.setNumeroPlaque(numeroPlaque);
        }
        return vehicule;
    }

    private String getLieu(int idLieu) throws Exception{
        if(idLieu == -1){
            return null;
        }
        String lieu = null;
        String request = "SELECT nom FROM Lieux WHERE idLieu = ?";
        Parametres params = new Parametres();
        params.addInt(idLieu);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            lieu = rs.getString("nom");
        }
        return lieu;
    }

    private int getIdVehicule(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return -1;
        }
        int idVehicule = -1;
        String request = "SELECT idVehicule FROM Vehicules WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idVehicule = rs.getInt("idVehicule");
        }
        return idVehicule;
    }

    private int getIdLieu(String nom) throws Exception{
        if(nom == null || nom.isEmpty()){
            return -1;
        }
        int idLieu = -1;
        String request = "SELECT idLieu FROM Lieux WHERE nom = ?";
        Parametres params = new Parametres();
        params.addString(nom);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            idLieu = rs.getInt("idLieu");
        }
        return idLieu;
    }

    public void supprimerGrille(int idGrilleHoraire) throws Exception{
        if(idGrilleHoraire == -1){
            return;
        }
        String request = "SELECT idCaseHoraire FROM CaseHoraire WHERE idGrilleHoraire = ?";
        Parametres params = new Parametres();
        params.addInt(idGrilleHoraire);

        ResultSet rs = mysql.pSelect(request, params);
        LinkedList<Integer> listeId = new LinkedList<>();
        while(rs.next()){
            int idCaseHoraire = rs.getInt("idCaseHoraire");
            listeId.add(idCaseHoraire);
        }

        for(int idCaseHoraire : listeId){
            request = "DELETE FROM AssignationCaseHoraire WHERE idCaseHoraire = ?";
            params = new Parametres();
            params.addInt(idCaseHoraire);
            mysql.pUpdate(request, params);
        }

        for(int idCaseHoraire : listeId){
            request = "DELETE FROM CaseHoraire WHERE idCaseHoraire = ?";
            params = new Parametres();
            params.addInt(idCaseHoraire);
            mysql.pUpdate(request, params);
        }

        request = "DELETE FROM GrilleHoraire WHERE idGrilleHoraire = ?";
        params = new Parametres();
        params.addInt(idGrilleHoraire);
        mysql.pUpdate(request, params);
    }

    public LinkedList<Object[]> getReservations() throws Exception{
        LinkedList<Object[]> listeReservation = new LinkedList<>();
        String request = "SELECT idVehicule, numeroSemaine, annee FROM Reservations";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            int idVehicule = rs.getInt("idVehicule");
            int semaine = rs.getInt("numeroSemaine");
            int annee = rs.getInt("annee");
            Vehicule vehicule = getVehicule(idVehicule);
            listeReservation.add(new Object[] {vehicule.getNom(), semaine, annee});
        }
        return listeReservation;
    }

    public ReservationVehicule getReservation(String nomVehicule, int semaine, int annee) throws Exception{
        if(nomVehicule == null || nomVehicule.isEmpty() || semaine == -1 || annee == -1){
            return null;
        }
        ReservationVehicule reservationVehicule = null;

        int idVehicule = getIdVehicule(nomVehicule);
        if(idVehicule == -1){
            return null;
        }
        String request = "SELECT idVehicule, numeroSemaine, annee FROM Reservations WHERE idVehicule = ? AND numeroSemaine = ? AND annee = ?";
        Parametres params = new Parametres();
        params.addInt(idVehicule);
        params.addInt(semaine);
        params.addInt(annee);

        ResultSet rs = mysql.pSelect(request, params);
        while(rs.next()){
            reservationVehicule = new ReservationVehicule();
            reservationVehicule.setNomVehicule(nomVehicule);
            reservationVehicule.setSemaine(semaine);
            reservationVehicule.setAnnee(annee);
        }
        return reservationVehicule;
    }

    public void nouveauReservation(ReservationVehicule reservation) throws Exception{
        if(reservation == null){
            return;
        }

        int idVehicule = getIdVehicule(reservation.getNomVehicule());
        if(idVehicule == -1){
            return;
        }

        String request = "INSERT INTO Reservations(idVehicule, numeroSemaine, annee) VALUES(?, ?, ?)";
        Parametres params = new Parametres();
        params.addInt(idVehicule);
        params.addInt(reservation.getSemaine());
        params.addInt(reservation.getAnnee());

        mysql.pUpdate(request, params);
    }

    public void modifierReservation(ReservationVehicule oldReservation, ReservationVehicule newReservation) throws Exception{
        if(oldReservation == null || newReservation == null){
            return;
        }
        String request = "UPDATE Reservations SET idVehicule = ?, numeroSemaine = ?, annee = ? WHERE idVehicule = ? AND numeroSemaine = ? AND annee = ?";
        Parametres params = new Parametres();
        int oldIdVehicule = getIdVehicule(oldReservation.getNomVehicule());
        int newIdVehicule = getIdVehicule(oldReservation.getNomVehicule());

        params.addInt(newIdVehicule);
        params.addInt(newReservation.getSemaine());
        params.addInt(newReservation.getAnnee());

        params.addInt(oldIdVehicule);
        params.addInt(oldReservation.getSemaine());
        params.addInt(oldReservation.getAnnee());

        mysql.pUpdate(request, params);
    }

    public void supprimerReservation(String nomVehicule, int semaine, int annee) throws Exception{
        if(nomVehicule == null || nomVehicule.isEmpty() || semaine == -1 || annee == -1){
            return;
        }
        int idVehicule = getIdVehicule(nomVehicule);
        if(idVehicule == -1){
            return;
        }

        String request = "DELETE FROM Reservations WHERE idVehicule = ? AND numeroSemaine = ? AND annee = ?";
        Parametres params = new Parametres();
        params.addInt(idVehicule);
        params.addInt(semaine);
        params.addInt(annee);

        mysql.pUpdate(request, params);
    }

    public LinkedList<TupleRecherche> checkDisponibiliteVolontaire(LinkedList<TupleRecherche> resultats, LinkedList<Object[]> dates) throws Exception{
        if(dates == null){
            return resultats;
        }
        LinkedList<TupleRecherche> copie = new LinkedList<TupleRecherche>();
        copie.addAll(resultats);
        for(TupleRecherche elm : resultats){
            String nom = elm.getNom();
            String prenom = elm.getPrenom();
            String matricule = getMatricule(nom, prenom);
            if(matricule == null){
                throw new Exception("volontaire introuvable");
            }
            for(Object[] date : dates){
                Date dateJour = (Date)date[0];
                Date heureDebut = (Date)date[1];
                Date heureFin = (Date)date[2];
                String request = "SELECT idCaseHoraire FROM AssignationCaseHoraire WHERE matricule = ? AND idCaseHoraire IN(SELECT idCaseHoraire FROM CaseHoraire WHERE date = ? AND((heureDebutPrestation < ? AND heureFinPrestation < ? AND heureFinPrestation > ?)OR(heureDebutPrestation > ? AND heureFinPrestation > ? AND heureDebutPrestation < ?)OR(heureDebutPrestation = ? AND heureFinPrestation = ?)))";
                Parametres params = new Parametres();
                params.addString(matricule);
                params.addDate(dateJour);
                params.addDateHour(heureDebut);
                params.addDateHour(heureFin);
                params.addDateHour(heureDebut);
                params.addDateHour(heureDebut);
                params.addDateHour(heureFin);
                params.addDateHour(heureFin);
                params.addDateHour(heureDebut);
                params.addDateHour(heureFin);


                ResultSet rs = mysql.pSelect(request, params);
                while(rs.next()){
                    int i = 0;
                    for(TupleRecherche elmCopie : copie){
                        if(elmCopie.getNom().equals(nom) && elmCopie.getPrenom().equals(prenom)){
                            copie.remove(i);
                            break;
                        }
                        i++;
                    }
                }
            }
        }
        return copie;
    }

    public LinkedList<Object[]> getBirthday() throws Exception{
        LinkedList<Object[]> retour = new LinkedList<Object[]>();
        String request = "SELECT nom, prenom, dateNaissance FROM Volontaires WHERE STR_TO_DATE(DATE_FORMAT(dateNaissance,'%d/%m'), '%d/%m') >= STR_TO_DATE(DATE_FORMAT(now(),'%d/%m'), '%d/%m') AND STR_TO_DATE(DATE_FORMAT(dateNaissance,'%d/%m'),'%d/%m') <= STR_TO_DATE(DATE_FORMAT(DATE_ADD(now(), INTERVAL + 7 DAY),'%d/%m'),'%d/%m');";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Date dateNaissance = rs.getTimestamp("dateNaissance");
            Object[] data = {nom, prenom, EasyDate.getDateOnly(dateNaissance)};
            retour.add(data);
        }
        return retour;
    }

    public LinkedList<Object[]> getHoraireMiss() throws Exception{
        LinkedList<Object[]> retour = new LinkedList<Object[]>();
        String request = "SELECT idVehicule, idLieu, numéroSemaine, dateDebut, dateFin FROM GrilleHoraire WHERE idGrilleHoraire IN(SELECT idGrilleHoraire FROM CaseHoraire Group By idGrilleHoraire HAVING(84 - count(detail) != 0))";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            int idVehicule = rs.getInt("idVehicule");
            int idLieu = rs.getInt("idLieu");
            String nomVehicule = getVehicule(idVehicule).getNom();
            String lieu = getLieu(idLieu);
            int semaine = rs.getInt("numéroSemaine");
            Date dateDebut = rs.getTimestamp("dateDebut");
            Date dateFin = rs.getTimestamp("dateFin");
            Object[] data = {nomVehicule, lieu, semaine, EasyDate.getDateOnly(dateDebut), EasyDate.getDateOnly(dateFin)};
            retour.add(data);
        }
        return retour;
    }

    public LinkedList<Object[]> getExpBrevet() throws Exception{
        LinkedList<Object[]> retour = new LinkedList<Object[]>();

        String request = "SELECT nom, prenom, A.nomFormation, A.dateExpiration FROM(SELECT 	FormationsSuivie.matricule, Formation.idFormation, nomFormation, DATE_FORMAT(dateExpiration, '%d/%m/%Y') AS 'dateExpiration' FROM Formation INNER JOIN(FormationsSuivie)ON(FormationsSuivie.idFormation = Formation.idFormation)WHERE STR_TO_DATE(DATE_FORMAT(DATE_ADD(dateExpiration, INTERVAL - 1 MONTH),'%d/%m/%Y'),'%d/%m/%Y') <= now()) A, Volontaires WHERE Volontaires.matricule = A.matricule";
        ResultSet rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String nomFormation = rs.getString("nomFormation");
            String dateExpiration = rs.getString("dateExpiration");
            Object[] data = {nom, prenom, nomFormation, dateExpiration};
            retour.add(data);
        }

        request = "SELECT nom, prenom, B.nomFormation, DATE_FORMAT(B.dateExpiration, '%d/%m/%Y') AS 'dateExpiration' FROM(SELECT matricule, nomFormation, STR_TO_DATE(SUBSTRING(fragmentDateExpiration, INSTR(fragmentDateExpiration, '#')+1, 15),REPLACE(REPLACE(SUBSTRING(fragmentDateExpiration, 1, INSTR(fragmentDateExpiration, '#') - 1), 'mm', '%m'), 'yyyy', '%Y')) AS 'dateExpiration' FROM Formation INNER JOIN(FormationsSuivie)ON(Formation.idFormation = FormationsSuivie.idFormation)WHERE fragmentDateExpiration != '') B, Volontaires WHERE B.dateExpiration <= now() AND Volontaires.matricule = B.matricule";
        rs = mysql.pSelect(request, null);
        while(rs.next()){
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String nomFormation = rs.getString("nomFormation");
            String dateExpiration = rs.getString("dateExpiration");
            Object[] data = {nom, prenom, nomFormation, dateExpiration};
            retour.add(data);
        }
        return retour;
    }
}
