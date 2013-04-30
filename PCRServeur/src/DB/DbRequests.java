/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import Containers.Adresse;
import Containers.CelluleGrille;
import Containers.Formation;
import Containers.Grille;
import Containers.Key;
import Containers.Residence;
import Database.Jdbc_MySQL;
import Recherche.Criteres.DBA;
import Recherche.TupleRecherche;
import java.beans.Beans;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbRequests implements DBA{
    Jdbc_MySQL mySql = null;

    public DbRequests(){
        try {
            mySql = (Jdbc_MySQL) Beans.instantiate(null, "Database.Jdbc_MySQL");
            mySql.init("localhost", "3306", "root", "", "croixrouge");
        } catch (IOException ex) {
            Logger.getLogger(DbRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DbRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertVolontaire(String matricule, String nom, String prenom, String nomEpoux, Date dateNaissance, char sexe, String email, String remarque, int idPersonneUrgence, int idDecouverte, int idPrestation, int idRenseignement, int idAdresseLegal, int idAdresseResidence, int idTelephone, int idActivite) throws Exception {
        java.sql.Date dateNaissanceSql = new java.sql.Date(dateNaissance.getTime());
        String request = "INSERT INTO Volontaires"
                + "("
                + "matricule,"
                + " nom,"
                + " nomEpouse,"
                + " prenom,"
                + " dateNaissance,"
                + " sexe,"
                + " email,"
                + " remarques,"
                + " idPersonneUrgence,"
                + " idDecouverte,"
                + " idPrestation,"
                + " idRenseignement,"
                + " idAdresseLegale,"
                + " idAdresseResidence,"
                + " idTelephone,"
                + " idActivite"
                + ")"
                + " VALUES("
                + " '"+matricule+"',"
                + " "+(nom.isEmpty() ? null : "'"+nom+"'")+","
                + " "+(nomEpoux.isEmpty() ? null : "'"+nomEpoux+"'")+","
                + " "+(prenom.isEmpty() ? null : "'"+prenom+"'")+","
                + " '"+dateNaissanceSql+"',"
                + " '"+sexe+"',"
                + " "+(email.isEmpty() ? null : "'"+email+"'")+","
                + " "+(remarque.isEmpty() ? null : "'"+remarque+"'")+","
                + " "+(idPersonneUrgence == -1 ? null : "'"+idPersonneUrgence+"'")+","
                + " "+(idDecouverte == -1 ? null : "'"+idDecouverte+"'")+","
                + " "+(idPrestation == -1 ? null : "'"+idPrestation+"'")+","
                + " "+(idRenseignement == -1 ? null : "'"+idRenseignement+"'")+","
                + " "+(idAdresseLegal == -1 ? null : "'"+idAdresseLegal+"'")+","
                + " "+(idAdresseResidence == -1 ? null : "'"+idAdresseResidence+"'")+","
                + " "+(idTelephone == -1 ? null : "'"+idTelephone+"'")+","
                + " "+(idActivite == -1 ? null : "'"+idActivite+"'")
                + ")";
        mySql.closeStatementClean();
        mySql.update(request);
    }

    public void checkLogin(String login) throws Exception {
        boolean found = false;
        String request =  "SELECT login "
                        + "FROM utilisateurs "
                        + "WHERE login = '"+login+"';";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            found = true;
        }
        mySql.closeStatement();
        if(!found){
            throw new Exception("Login incorrect");
        }
    }

    public int checLoginPassword(String login, String password) throws Exception {
        boolean found = false;
        int idUtilisateur = -1;
        String request =  "SELECT idUtilisateur "
                        + "FROM utilisateurs "
                        + "WHERE login = '"+login+"' AND password = '"+password+"';";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            found = true;
            idUtilisateur = tuples.getInt("idutilisateur");
        }
        mySql.closeStatement();
        if(!found){
            throw new Exception("passowrd incorrect");
        }
        return idUtilisateur;
    }

    public LinkedList<String[]> getVolontairesAll() throws Exception {
        LinkedList<String[]> listeVolontaires = null;
        String request =  "SELECT Volontaires.nom, Volontaires.prenom, Volontaires.dateNaissance, Ville.nomVille "
                        + "FROM Volontaires, Adresse, Ville "
                        + "WHERE Volontaires.idAdresseLegale= Adresse.idAdresse "
                        + "AND Ville.idVille = Adresse.idVille;";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeVolontaires == null){
                listeVolontaires = new LinkedList<>();
            }
            String nom = null;
            String prenom = null;
            java.sql.Date dateNaissance = null;
            String ville = null;

            nom = tuples.getString("nom");
            prenom = tuples.getString("prenom");
            dateNaissance = tuples.getDate("dateNaissance");
            ville = tuples.getString("nomVille");

            java.util.Date dateNaissanceUtil = new java.util.Date(dateNaissance.getTime());
            String dateNaissanceString = new SimpleDateFormat("dd/MM/yyyy").format(dateNaissanceUtil);
            String[] data = {nom, prenom, dateNaissanceString, ville};
            listeVolontaires.add(data);
        }
        mySql.closeStatement();
        return listeVolontaires;
    }

    public LinkedList<String[]> getDroits() throws Exception {
        LinkedList<String[]> listeDroits = null;
        String request =  "SELECT nom, description "
                        + "FROM Droits;";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeDroits == null){
                listeDroits = new LinkedList<>();
            }
            String nom = null;
            String description = null;

            nom = tuples.getString("nom");
            description = tuples.getString("description");

            String[] data = {nom, description};
            listeDroits.add(data);
        }
        mySql.closeStatement();
        return listeDroits;
    }

    public LinkedList<String[]> getGroupes() throws Exception {
        LinkedList<String[]> listeGroupes = null;
        String request =  "SELECT nomGroupe, niveau "
                        + "FROM Groupes;";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeGroupes == null){
                listeGroupes = new LinkedList<>();
            }
            String nom = null;
            String niveau = null;
            int niveauInt = -1;

            nom = tuples.getString("nomGroupe");
            niveauInt = tuples.getInt("niveau");
            if(niveauInt != -1){
                niveau = String.valueOf(niveauInt);
            }

            String[] data = {nom, niveau};
            listeGroupes.add(data);
        }
        mySql.closeStatement();
        return listeGroupes;
    }

    public LinkedList<String[]> getUtilisateurs() throws Exception {
        LinkedList<String[]> listeUtilisateurs = null;
        String request =  "SELECT login, prioritaire "
                        + "FROM Utilisateurs;";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeUtilisateurs == null){
                listeUtilisateurs = new LinkedList<>();
            }
            String login = null;
            String prioritaire = null;
            int prioritaireInt = -1;

            login = tuples.getString("login");
            prioritaireInt = tuples.getInt("prioritaire");
            if(prioritaireInt != -1){
                prioritaire = String.valueOf(prioritaireInt);
            }

            String[] data = {login, prioritaire};
            listeUtilisateurs.add(data);
        }
        mySql.closeStatement();
        return listeUtilisateurs;
    }

    public LinkedList<String> getMyDroits(int idUser) throws Exception {
        LinkedList<String> droits = null;
        String request =  "SELECT d.nom "
                        + "FROM Utilisateurs u, Appartenir a, Groupes g, PossederDroit p, Droits d "
                        + "WHERE u.idUtilisateur = '"+idUser+"' "
                        + "AND a.idUtilisateur = u.idUtilisateur "
                        + "AND a.idGroupe = g.idGroupe "
                        + "AND p.idGroupe = g.idGroupe "
                        + "AND p.idDroit = d.idDroit;";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(droits == null){
                droits = new LinkedList<>();
            }
            String nom = null;
            nom = tuples.getString("nom");

            droits.add(nom);
        }
        mySql.closeStatement();
        return droits;
    }

    public int getIdUser(String login) throws Exception {
        int idUser = -1;
        boolean found = false;
        String request =  "SELECT idUtilisateur "
                        + "FROM utilisateurs "
                        + "WHERE login = '"+login+"';";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            idUser = tuples.getInt("idUtilisateur");
            found = true;
        }
        mySql.closeStatement();
        if(!found){
            throw new Exception("Login inconnu");
        }
        return idUser;
    }

    public Map<String, String> getDataUser(int id) throws Exception {
        Map<String, String> dataUser = new TreeMap<String, String>();
        return dataUser;

    }

    public int insertPersonneUrgence(String nom, String prenom, String telephone) throws Exception {
        String request = "INSERT INTO PersonneUrgence(nom, prenom, telephone) VALUES('"+nom+"', '"+prenom+"', '"+telephone+"')";
        mySql.closeStatementClean();
        mySql.update(request);
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM PersonneUrgence";
        int idPersonneUrgence = -1;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            idPersonneUrgence = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idPersonneUrgence;
    }

    public int insertDecouvertes(LinkedList<String> listeDescription) throws Exception {
        if(listeDescription.isEmpty()){
            return -1;
        }
        String description = listeDescription.getFirst();
        description = escapeChar(description);
        String request = "SELECT idDecouverte FROM Decouverte WHERE description = '"+description+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idDecouverte = -1;
        while(tuples.next()){
            found = true;
            idDecouverte = tuples.getInt("idDecouverte");
        }
        mySql.closeStatement();

        if(!found){
            request = "INSERT INTO Decouverte(description) VALUES('"+description+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Decouverte";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idDecouverte = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idDecouverte;
    }

    public int insertLangueMaternelle(String langueMaternelle) throws Exception {
        String request = "SELECT idLangue FROM Langue WHERE nom = '"+langueMaternelle+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idLangue = -1;
        while(tuples.next()){
            found = true;
            idLangue = tuples.getInt("idLangue");
        }
        mySql.closeStatement();

        if(!found){
            request = "INSERT INTO Langue(nom) VALUES('"+langueMaternelle+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Langue";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idLangue = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idLangue;
    }

    public int insertRenseignement(String activitePro, String activite, String qualification, String permis, String categorie, Date dateObtention, String selectionMedicale, String dateValidité, int idLangueMaternelle) throws Exception {
        java.sql.Date dateObtentionSQL = new java.sql.Date(dateObtention.getTime());
        String request = "INSERT INTO Renseignements(activitePro, SituationActuelle, Diplome, PermisConduire, Categorie, DateObtention, selectionMedicale, dateValidite, numCompteBancaire, idLangueMaternelle) VALUES('"+activitePro+"', '"+activite+"', '"+qualification+"', '"+permis+"', '"+categorie+"', '"+dateObtentionSQL+"', '"+selectionMedicale+"', '"+dateValidité+"', '', '"+idLangueMaternelle+"')";
        mySql.closeStatementClean();
        mySql.update(request);
        int idRenseignement = -1;
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Renseignements";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            idRenseignement = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idRenseignement;
    }

    public void insertLanguesConnue(int idRenseignement, LinkedList<String> listeLangue) throws Exception {
        LinkedList<Integer> listeId = new LinkedList<>();
        for(String langue : listeLangue){
            String request = "SELECT idLangue FROM Langue WHERE nom = '"+langue+"'";
            boolean found = false;
            mySql.closeStatementClean();
            ResultSet tuples = (ResultSet)mySql.select(request);
            int idLangue = -1;
            while(tuples.next()){
                found = true;
                idLangue = tuples.getInt("idLangue");
                listeId.add(idLangue);
            }
            mySql.closeStatement();
            if(!found && !langue.isEmpty()){
                request = "INSERT INTO Langue(nom) VALUES('"+langue+"')";
                mySql.update(request);
                request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Langue";
                mySql.closeStatementClean();
                tuples = (ResultSet)mySql.select(request);
                while(tuples.next()){
                    idLangue = tuples.getInt("id");
                    listeId.add(idLangue);
                }
                mySql.closeStatement();
            }
        }
        for(int idLangue : listeId){
            String request = "INSERT INTO LanguesConnue(idRenseignements, idLangue) VALUES('"+idRenseignement+"', '"+idLangue+"')";
            mySql.closeStatementClean();
            mySql.update(request);
        }
    }

    public void insertFormationsSuivie(String matricule, LinkedList<Formation> listeFormation) throws Exception {
        LinkedList<Integer> listeId = new LinkedList<>();
        for(Formation formation : listeFormation){
            String request = "SELECT idFormation FROM Formation WHERE nomFormation = '"+formation.getNom()+"'";
            boolean found = false;
            mySql.closeStatementClean();
            ResultSet tuples = (ResultSet)mySql.select(request);
            int idFormation = -1;
            while(tuples.next()){
                found = true;
                idFormation = tuples.getInt("idFormation");
                listeId.add(idFormation);
            }
            mySql.closeStatement();
            if(!found){
                java.sql.Date dateObtentionSQL = new java.sql.Date(formation.getDateObtention().getTime());
                java.sql.Date dateExpirationSQL = new java.sql.Date((formation.getDatePeremption() != null ? formation.getDatePeremption().getTime() : formation.getDateExamen().getTime()));
                request = "INSERT INTO Formation(nomFormation, dateObtention, dateExpiration, numero, lieu, copie, numeroService112) "
                        + "VALUES('"+formation.getNom()+"', '"+dateObtentionSQL+"', '"+dateExpirationSQL+"', '"+formation.getNumero()+"', '"+formation.getLieu()+"', '"+formation.getPhotocopie()+"', '"+formation.getNumeroService112()+"')";
                mySql.closeStatementClean();
                mySql.update(request);
                request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Formation";
                mySql.closeStatementClean();
                tuples = (ResultSet)mySql.select(request);
                while(tuples.next()){
                    idFormation = tuples.getInt("id");
                    listeId.add(idFormation);
                }
                mySql.closeStatement();
            }
        }
        for(int idFormation : listeId){
            String request = "INSERT INTO FormationsSuivie(matricule, idFormation) VALUES('"+matricule+"', '"+idFormation+"')";
            mySql.closeStatementClean();
            try{
                mySql.update(request);
            }catch(Exception ex){

            }
        }
    }

    public int insertPaysLegal(String pays) throws Exception {
        String request = "SELECT idPays FROM Pays WHERE nomPays = '"+pays+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idPays = -1;
        while(tuples.next()){
            found = true;
            idPays = tuples.getInt("idPays");
        }
        mySql.closeStatement();
        if(!found && !pays.isEmpty()){
            request = "INSERT INTO Pays(nomPays) VALUES('"+pays+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Pays";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idPays = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idPays;
    }

    public int insertPaysResidence(String pays) throws Exception {
        String request = "SELECT idPays FROM Pays WHERE nomPays = '"+pays+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idPays = -1;
        while(tuples.next()){
            found = true;
            idPays = tuples.getInt("idPays");
        }
        mySql.closeStatement();
        if(!found && !pays.isEmpty()){
            request = "INSERT INTO Pays(nomPays) VALUES('"+pays+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Pays";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idPays = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idPays;
    }

    public int insertVilleLegal(String ville) throws Exception {
        String request = "SELECT idVille FROM Ville WHERE nomVille = '"+ville+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idVille = -1;
        while(tuples.next()){
            found = true;
            idVille = tuples.getInt("idVille");
        }
        mySql.closeStatement();
        if(!found && !ville.isEmpty()){
            request = "INSERT INTO Ville(nomVille) VALUES('"+ville+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Ville";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idVille = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idVille;
    }

    public int insertVilleResidence(String ville) throws Exception {
        String request = "SELECT idVille FROM Ville WHERE nomVille = '"+ville+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idVille = -1;
        while(tuples.next()){
            found = true;
            idVille = tuples.getInt("idVille");
        }
        mySql.closeStatement();
        if(!found && !ville.isEmpty()){
            request = "INSERT INTO Ville(nomVille) VALUES('"+ville+"')";
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Ville";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                idVille = tuples.getInt("id");
            }
            mySql.closeStatement();
        }
        return idVille;
    }

    public int insertAdresseLegale(String rue, int numéro, int codePostal, int boite, int idPaysLegal, int idVilleLegal, String matricule) throws Exception {
        if(rue.isEmpty()){
            return -1;
        }
        String request = "INSERT INTO Adresse(rueAvenueBd, numero, codePostal, boite, idPays, idVille, matriculeVolontaire) VALUES('"+rue+"', '"+numéro+"', '"+codePostal+"', '"+(boite == -1 ? null : boite)+"', '"+idPaysLegal+"', '"+idVilleLegal+"', '"+matricule+"')";
        mySql.update(request);
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Adresse";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idAdresse = -1;
        while(tuples.next()){
            idAdresse = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idAdresse;
    }

    public int insertAdresseResidence(String rue, int numéro, int codePostal, int boite, Residence residence, int idPaysResidence, int idVilleResidence, String matricule) throws Exception {
        if(rue.isEmpty()){
            return -1;
        }
        String request = "INSERT INTO Adresse(rue-avenue-bd, numero, codePostal, boite, idPays, idVille, matriculeVolontaire) VALUES('"+rue+"', '"+numéro+"', '"+codePostal+"', '"+boite+"', '"+idPaysResidence+"', '"+idVilleResidence+"', '"+matricule+"')";
        mySql.update(request);
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Adresse";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idAdresse = -1;
        while(tuples.next()){
            idAdresse = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idAdresse;
    }

    public int insertTelephone(String gsm, String autreGsm, String telephoneFix, String telephoneProfesionnelle, String fax) throws Exception {
        /*String request = "INSERT INTO Telephone(gsm, autreGsm, telephoneFix, telephonePro, fax) "
                + "VALUES("
                + "'"+(gsm.isEmpty() ? null : gsm)+"',"
                + " '"+(autreGsm.isEmpty() ? null : autreGsm)+"',"
                + " '"+(telephoneFix.isEmpty() ? null : telephoneFix)+"',"
                + " '"+(telephoneProfesionnelle.isEmpty() ? null : telephoneProfesionnelle)+"',"
                + " '"+(fax.isEmpty() ? null : fax)+"'"
                + ")";*/
        String request = "INSERT INTO Telephone(gsm, autreGsm, telephoneFix, telephonePro, fax) VALUES("+(gsm.isEmpty() ? null : "'"+gsm+"'")+", "+(autreGsm.isEmpty() ? null : "'"+autreGsm+"'")+","+(telephoneFix.isEmpty() ? null : "'"+telephoneFix+"'")+" ,"+(telephoneProfesionnelle.isEmpty() ? null : "'"+telephoneProfesionnelle+"'")+" ,"+(fax.isEmpty() ? null : "'"+fax+"'")+")";
        mySql.closeStatementClean();
        mySql.update(request);
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Telephone";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idTelephone = -1;
        while(tuples.next()){
            idTelephone = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idTelephone;
    }

    private String escapeChar(String description) {
        String retour = description.replaceAll("'", "+");
        retour = retour.replace("+", "\\'");
        return retour;
    }

    public boolean checkMatricule(String matricule) throws Exception {
        boolean found = false;
        String request =  "SELECT matricule "
                        + "FROM volontaires "
                        + "WHERE matricule = '"+matricule+"';";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            found = true;
        }
        mySql.closeStatement();
        return found;
    }

    public LinkedList<String[]> getEquipes() throws Exception {
        LinkedList<String[]> listeEquipes = new LinkedList<>();
        String request =  "SELECT nom, dateCreation, count(matricule) as 'count'"
                        + "FROM Equipe E, Lier L "
                        + "WHERE E.idEquipe = L.idEquipe "
                        + "GROUP BY(nom)";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeEquipes == null){
                listeEquipes = new LinkedList<>();
            }
            String nom = null;
            java.sql.Date dateCreation = null;
            int count = -1;

            nom = tuples.getString("nom");
            if(nom == null){
                return listeEquipes;
            }
            dateCreation = tuples.getDate("dateCreation");
            count = tuples.getInt("count");

            java.util.Date dateUtil = new java.util.Date(dateCreation.getTime());

            String[] data = {nom, new SimpleDateFormat("dd/MM/yyyy").format(dateUtil), String.valueOf(count)};
            listeEquipes.add(data);
        }
        mySql.closeStatement();
        return listeEquipes;
    }

    public int insertEquipe(String nom) throws Exception {
        String request = "INSERT INTO Equipe(nom, dateCreation) VALUES('"+nom+"', now())";
        mySql.closeStatementClean();
        mySql.update(request);
        request = "SELECT DISTINCT LAST_INSERT_ID() as 'id' FROM Equipe";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idEquipe = -1;
        while(tuples.next()){
            idEquipe = tuples.getInt("id");
        }
        mySql.closeStatement();
        return idEquipe;
    }

    public String getMatricule(String nom, String prenom) throws Exception {
        String matricule = null;
        String request = "SELECT matricule FROM Volontaires WHERE nom = '"+nom+"' AND prenom = '"+prenom+"'";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            matricule = tuples.getString("matricule");
        }
        mySql.closeStatement();
        return matricule;
    }

    public void insertMembreEquipe(int idEquipe, String matricule) throws Exception {
        String request = "INSERT INTO Lier(idEquipe, matricule) VALUES('"+idEquipe+"', '"+matricule+"')";
        mySql.closeStatementClean();
        mySql.update(request);
    }

    public LinkedList<String> getFormations() throws Exception {
        LinkedList<String> listeFormations = new LinkedList<>();
        String request =  "SELECT nomFormation FROM formation";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeFormations == null){
                listeFormations = new LinkedList<>();
            }
            String nomFormation = null;

            nomFormation = tuples.getString("nomFormation");
            if(nomFormation == null){
                return listeFormations;
            }
            listeFormations.add(nomFormation);
        }
        mySql.closeStatement();
        return listeFormations;
    }

    @Override
    public LinkedList<TupleRecherche> searchByCritere(String request) {
        LinkedList<TupleRecherche> resultat = new LinkedList<>();
        try {
            mySql.closeStatementClean();
            ResultSet tuples = (ResultSet)mySql.select(request);
            while(tuples.next()){
                String nomResultat = tuples.getString("nom");
                String prenomResultat = tuples.getString("prenom");
                resultat.add(new TupleRecherche(nomResultat, prenomResultat));
            }
            mySql.closeStatement();
        } catch (Exception ex) {
            Logger.getLogger(DbRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    public LinkedList<String[]> getGrillesHoraires() throws Exception{
        LinkedList<String[]> listeGrilles = new LinkedList<>();
        String request = "SELECT numéroSemaine, dateDebut, dateFin, dateCréation, dateModification, ambulance, lieu, année FROM grillehoraire";

        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            if(listeGrilles == null){
                listeGrilles = new LinkedList<>();
            }
            String ambulance = null;

            int numeroSemaine = tuples.getInt("numéroSemaine");
            int année = tuples.getInt("année");
            java.sql.Date dateDebut = tuples.getDate("dateDebut");
            java.sql.Date dateFin = tuples.getDate("dateFin");
            java.sql.Date dateCréation = tuples.getDate("dateCréation");
            java.sql.Date dateModification = tuples.getDate("dateModification");
            java.util.Date dateTest = new java.util.Date(dateModification.getTime());
            String test = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dateTest);
            ambulance= tuples.getString("ambulance");
            String lieu = tuples.getString("lieu");

            if(ambulance == null){
                return listeGrilles;
            }
            String[] data = {String.valueOf(numeroSemaine),
                String.valueOf(année),
                new SimpleDateFormat("dd/MM/yyyy").format(dateDebut),
                new SimpleDateFormat("dd/MM/yyyy").format(dateFin),
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dateCréation),
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dateModification),
                ambulance,
                lieu
            };
            listeGrilles.add(data);
        }
        mySql.closeStatement();
        return listeGrilles;
    }

    public int insertGrilleHoraire(int semaine, String dateDebut, String dateFin, String nomAmbulance, String lieu, int annee) throws Exception{
        String request = "SELECT idGrilleHoraire FROM GrilleHoraire WHERE numéroSemaine = '"+semaine+"' AND ambulance = '"+nomAmbulance+"' AND année = '"+annee+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idGrilleHoraire = -1;
        while(tuples.next()){
            found = true;
            idGrilleHoraire = tuples.getInt("idGrilleHoraire");
        }
        mySql.closeStatement();
        if(!found && nomAmbulance != null && lieu != null && annee != -1 && semaine != -1){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateUtil = sdf.parse(dateDebut);
            java.sql.Date dateDebutSQL = null;
            java.sql.Date dateFinSQL = null;

            dateDebutSQL = new java.sql.Date(dateUtil.getTime());
            dateUtil = sdf.parse(dateFin);
            dateFinSQL = new java.sql.Date(dateUtil.getTime());

            request = "INSERT INTO GrilleHoraire(dateCréation, dateModification, numéroSemaine, dateDebut, dateFin, ambulance, lieu, locked, année) VALUES"
                    + "(now(), now(), '"+semaine+"', '"+dateDebutSQL+"', '"+dateFinSQL+"', '"+nomAmbulance+"', '"+lieu+"', 0, '"+annee+"')";
            mySql.closeStatementClean();
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID(), idGrilleHoraire FROM GrilleHoraire";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            idGrilleHoraire = -1;
            while(tuples.next()){
                idGrilleHoraire = tuples.getInt("idGrilleHoraire");
            }
            mySql.closeStatement();
        }else{
            throw new Exception("Grille déja existante");
        }
        return idGrilleHoraire;

    }

    public int insertCellule(String jour, String date, String heure, String role, int row, int column, int idGrilleHoraire) throws Exception{
        String request = "SELECT idCaseHoraire FROM CaseHoraire WHERE idGrilleHoraire = '"+idGrilleHoraire+"' AND jour = '"+jour+"' AND heurePrestation = '"+heure+"' AND role = '"+role+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idCaseHoraire = -1;
        while(tuples.next()){
            found = true;
            idCaseHoraire = tuples.getInt("idCaseHoraire");
        }
        mySql.closeStatement();
        if(!found && idGrilleHoraire != -1 && heure != null && date != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dateUtil = sdf.parse(date);
            java.sql.Date dateSQL = null;
            dateSQL = new java.sql.Date(dateUtil.getTime());

            request = "INSERT INTO caseHoraire(idGrilleHoraire, date, heurePrestation, role, numRow, numColumn, jour) VALUES"
                    + "('"+idGrilleHoraire+"', '"+dateSQL+"', '"+heure+"', '"+role+"', '"+row+"', '"+column+"', '"+jour+"')";
            mySql.closeStatementClean();
            mySql.update(request);
            request = "SELECT DISTINCT LAST_INSERT_ID(), idCaseHoraire FROM caseHoraire";
            mySql.closeStatementClean();
            tuples = (ResultSet)mySql.select(request);
            idCaseHoraire = -1;
            while(tuples.next()){
                idCaseHoraire = tuples.getInt("idCaseHoraire");
            }
            mySql.closeStatement();
        }
        return idCaseHoraire;
    }

    public int EditCellule(String jour, String date, String heure, String role, int row, int column, int idGrilleHoraire, String nouvelleHeure) throws Exception{
        if(idGrilleHoraire == -1 || heure == null || date == null){
            return -1;
        }
        String request = "SELECT idCaseHoraire FROM CaseHoraire WHERE idGrilleHoraire = '"+idGrilleHoraire+"' AND jour = '"+jour+"' AND heurePrestation = '"+heure+"' AND role = '"+role+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idCaseHoraire = -1;
        while(tuples.next()){
            found = true;
            idCaseHoraire = tuples.getInt("idCaseHoraire");
        }
        mySql.closeStatement();

        if(found){
            request = "UPDATE caseHoraire SET heurePrestation = '"+nouvelleHeure+"' WHERE idCaseHoraire = '"+idCaseHoraire+"'";
            mySql.closeStatementClean();
            mySql.update(request);
        }

        return idCaseHoraire;
    }

    public void insertAssignationCaseHoraire(int idCaseHoraire, String matricule) throws Exception{
        if(idCaseHoraire != -1 && matricule != null){
            String request = "INSERT INTO AssignationCaseHoraire(matricule, idCaseHoraire) VALUES"
                    + "('"+matricule+"', '"+idCaseHoraire+"')";
            mySql.closeStatementClean();
            mySql.update(request);
        }
    }

    public void EditAssignationCaseHoraire(int idCaseHoraire, String matricule) throws Exception{
        if(idCaseHoraire != -1 && matricule != null && !matricule.isEmpty()){
            String request = "DELETE FROM AssignationCaseHoraire WHERE idCaseHoraire = '"+idCaseHoraire+"'";
            mySql.closeStatementClean();
            mySql.update(request);

            request = "INSERT INTO AssignationCaseHoraire(matricule, idCaseHoraire) VALUES"
                    + "('"+matricule+"', '"+idCaseHoraire+"')";
            mySql.closeStatementClean();
            mySql.update(request);
        }else if (idCaseHoraire != -1 && (matricule == null || matricule.isEmpty())){
            String request = "DELETE FROM AssignationCaseHoraire WHERE idCaseHoraire = '"+idCaseHoraire+"'";
            mySql.closeStatementClean();
            mySql.update(request);
        }
    }

    public int getIdGrilleHoraire(int semaine, int année, String ambulance, String lieu) throws Exception{
        int idGrilleHoraire = -1;
        String request = "SELECT idGrilleHoraire FROM GrilleHoraire WHERE numéroSemaine = '"+semaine+"' AND année = '"+année+"' AND ambulance = '"+ambulance+"' AND lieu = '"+lieu+"'";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            idGrilleHoraire = tuples.getInt("idGrilleHoraire");
        }
        mySql.closeStatement();
        return idGrilleHoraire;

    }

    public Grille getGrille(int idGrilleHoraire) throws Exception{
        Grille grille = new Grille();
        String request = "SELECT numéroSemaine, dateDebut, dateFin, ambulance, lieu, année FROM GrilleHoraire WHERE idGrilleHoraire = '"+idGrilleHoraire+"'";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            int numeroSemaine = tuples.getInt("numéroSemaine");
            java.sql.Date dateDebut = tuples.getDate("dateDebut");
            java.sql.Date dateFin = tuples.getDate("dateFin");
            String ambulance = tuples.getString("ambulance");
            String lieu = tuples.getString("lieu");
            int annee = tuples.getInt("année");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            grille.setAnnee(annee);
            grille.setSemaine(numeroSemaine);
            grille.setDateDebut(sdf.format(dateDebut));
            grille.setDateFin(sdf.format(dateFin));
            grille.setLieu(lieu);
            grille.setNomAmbulance(ambulance);
        }
        mySql.closeStatement();
        return grille;
    }

    public LinkedList<Key> getCellules(int idGrilleHoraire) throws Exception{
        LinkedList<Key> cellules = new LinkedList<>();
        String request = "SELECT idCaseHoraire, date, heurePrestation, role, numRow, numColumn, jour FROM CaseHoraire WHERE idGrilleHoraire = '"+idGrilleHoraire+"'";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            java.sql.Date date = tuples.getDate("date");
            String heurePrestation = tuples.getString("heurePrestation");
            String role = tuples.getString("role");
            int row = tuples.getInt("numRow");
            int column = tuples.getInt("numColumn");
            String jour = tuples.getString("jour");

            CelluleGrille cellule = new CelluleGrille();
            cellule.setJour(jour);
            cellule.setHeure(heurePrestation);
            cellule.setRole(role);
            cellule.setDate(new SimpleDateFormat("dd/MM/yyyy").format(date));
            cellule.setColumn(column);
            cellule.setRow(row);
            Key key = new Key(row, column, cellule);
            cellules.add(key);
        }
        mySql.closeStatement();
        return cellules;
    }

    public void getVolontaireGrille(Grille grille, int idGrilleHoraire) throws Exception{
        String request = "SELECT c.idCaseHoraire, date, heurePrestation, role, numRow, numColumn, jour, nom, prenom FROM (CaseHoraire c INNER JOIN assignationCaseHoraire a ON(c.idCaseHoraire = a.idCaseHoraire)) INNER JOIN volontaires v ON(v.matricule = a.matricule) WHERE c.idGrilleHoraire = '"+idGrilleHoraire+"'";
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        while(tuples.next()){
            int row = tuples.getInt("numRow");
            int column = tuples.getInt("numColumn");
            String nom = tuples.getString("nom");
            String prenom = tuples.getString("prenom");
            String nomComplet = nom + " " + prenom;
            for(Key keys : grille.getGrilles()){
                if(keys.getX() == row && keys.getY() == column){
                    keys.getValue().setNomPrenom(nomComplet);
                    break;
                }
            }
        }
        mySql.closeStatement();
    }

    public int editGrilleHoraire(int semaine, int annee, String nomAmbulance, String lieu) throws Exception{
        String request = "SELECT idGrilleHoraire FROM GrilleHoraire WHERE numéroSemaine = '"+semaine+"' AND ambulance = '"+nomAmbulance+"' AND année = '"+annee+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int idGrilleHoraire = -1;
        while(tuples.next()){
            found = true;
            idGrilleHoraire = tuples.getInt("idGrilleHoraire");
        }
        mySql.closeStatement();
        if(found){
            request = "UPDATE GrilleHoraire SET dateModification = now() WHERE idGrilleHoraire = '"+idGrilleHoraire+"'";
            mySql.closeStatementClean();
            mySql.update(request);
        }
        return idGrilleHoraire;
    }

    public boolean checkLockGrille(int semaine, int annee, String nomAmbulance, String lieu) throws Exception{
        String request = "SELECT locked FROM GrilleHoraire WHERE numéroSemaine = '"+semaine+"' AND ambulance = '"+nomAmbulance+"' AND année = '"+annee+"'";
        boolean found = false;
        mySql.closeStatementClean();
        ResultSet tuples = (ResultSet)mySql.select(request);
        int locked = -1;
        while(tuples.next()){
            found = true;
            locked = tuples.getInt("locked");
        }
        mySql.closeStatement();
        if(locked == 0){
            return false;
        }else{
            return true;
        }
    }

    public void lockGrille(int semaine, int année, String ambulance, String lieu) throws Exception{
        String request = "UPDATE GrilleHoraire SET locked = 1 WHERE numéroSemaine = '"+semaine+"' AND année = '"+année+"' AND ambulance = '"+ambulance+"' AND lieu = '"+lieu+"'";
        mySql.closeStatementClean();
        mySql.update(request);
    }

    public void unlockGrille(int semaine, int année, String ambulance, String lieu) throws Exception{
        String request = "UPDATE GrilleHoraire SET locked = 0 WHERE numéroSemaine = '"+semaine+"' AND année = '"+année+"' AND ambulance = '"+ambulance+"' AND lieu = '"+lieu+"'";
        mySql.closeStatementClean();
        mySql.update(request);
    }

    public void unlockGrille(int idGrilleHoraire)  throws Exception{
        String request = "UPDATE GrilleHoraire SET locked = 0 WHERE idGrilleHoraire = '"+idGrilleHoraire+"'";
        mySql.closeStatementClean();
        mySql.update(request);
    }
}
