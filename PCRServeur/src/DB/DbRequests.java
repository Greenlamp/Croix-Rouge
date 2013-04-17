/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import Database.Jdbc_MySQL;
import java.beans.Beans;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbRequests {
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

    public void insertVolontaire(String nom, String prenom, String nomEpoux, String dateNaissance, String sexe, String email, String rue, String numero, String ville, String codePostal, String boite, String nationalite) throws Exception {
        java.sql.Date dateNaissanceSql = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance).getTime());
        char sexeC = (sexe.equalsIgnoreCase("homme") ? 'M' : 'F');
        String request = "INSERT INTO Volontaires(matricule, nom, nomEpouse, prenom, dateNaissance, sexe, email) VALUES('"+nom+"-"+prenom+"', '"+nom+"', '"+nomEpoux+"', '"+prenom+"', '"+dateNaissanceSql+"', '"+sexeC+"', '"+email+"')";
        mySql.update(request);
        mySql.closeStatement();
    }

    public void checkLogin(String login) throws Exception {
        boolean found = false;
        String request =  "SELECT login "
                        + "FROM utilisateurs "
                        + "WHERE login = '"+login+"';";
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


}
