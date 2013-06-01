/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import Containers.Grille;
import Containers.Groupe;
import Containers.Key;
import Containers.ReservationVehicule;
import Containers.Utilisateur;
import Containers.Vehicule;
import Containers.Volontaire;
import DB.DbRequests;
import States.States;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.LibCritereAndroid.Criteres.ByCodePostal;
import my.LibCritereAndroid.Criteres.ByDateNaissance;
import my.LibCritereAndroid.Criteres.ByFormation;
import my.LibCritereAndroid.Criteres.ByNom;
import my.LibCritereAndroid.Criteres.ByPrenom;
import my.LibCritereAndroid.Criteres.CritereCustom;
import my.LibCritereAndroid.Criteres.TraitementRecherche;
import my.LibCritereAndroid.Recherche.Critere;
import my.cr.PacketCom.PacketCom;
import my.cr.PacketCom.Protocolable;


public class ProtocoleServeur implements Protocolable{
    private DbRequests dbRequests = null;

    private int idUser = -1;
    private LinkedList<String> droitsOffi = null;

    public ProtocoleServeur(){
        dbRequests = new DbRequests();
    }

    public PacketCom messageFromClient(Object objet){
        PacketCom packet = (PacketCom) objet;
        PacketCom messageToClient = traiterPacketServerSide(packet);
        System.out.println("retour: " + messageToClient.getType());
        return messageToClient;
    }

    public PacketCom messageFromServer(Object objet) {
        return null;
    }

    private PacketCom traiterPacketServerSide(PacketCom packet) {
        String type = packet.getType();
        System.out.println("type reçu: " + type);
        Object contenu = packet.getObjet();

        if(type.equals(States.LOGIN)){
            return actionLogin(type, contenu);
        }else if(type.equals(States.NOUVEAU_VOLONTAIRE)){
            return actionNouveauVolontaire(type, contenu);
        }else if(type.equals(States.DISCONNECT)){
            return actionDisconnect(type, contenu);
        }else if(type.equals(States.GET_VOLONTAIRE_ALL)){
            return actionGetVolontaires(type, contenu, "all");
        }else if(type.equals(States.GET_DROITS_ALL)){
            return actionGetDroits(type, contenu);
        }else if(type.equals(States.GET_GROUPES_ALL)){
            return actionGetGroupes(type, contenu);
        }else if(type.equals(States.GET_UTILISATEURS_ALL)){
            return actionGetUtilisateurs(type, contenu);
        }else if(type.equals(States.GET_MY_DROITS)){
            return actionGetMyDroits(type, contenu);
        }else if(type.equals(States.GET_DETAILS_USER)){
            return actionGetDetailsUser(type, contenu);
        }else if(type.equals(States.GET_EQUIPES_ALL)){
            return actionGetEquipes(type, contenu);
        }else if(type.equals(States.RECHERCHE)){
            return actionRecherche(type, contenu);
        }else if(type.equals(States.GET_LISTE_FORMATIONS)){
            return actionGetFormation(type, contenu);
        }else if(type.equals(States.GET_GRILLES_HORAIRES)){
            return actionGetGrillesHoraires(type, contenu);
        }else if(type.equals(States.NEW_GRILLE_HORAIRE)){
            return actionNewGrillesHoraires(type, contenu);
        }else if(type.equals(States.EDIT_GRILLE_HORAIRE)){
            return actionEditGrillesHoraires(type, contenu);
        }else if(type.equals(States.GET_GRILLE)){
            return actionGetGrille(type, contenu);
        }else if(type.equals(States.DELETE_GRILLE)){
            return actionDeleteGrille(type, contenu);
        }else if(type.equals(States.CHECK_LOCK_GRILLE)){
            return actionCheckLockGrille(type, contenu);
        }else if(type.equals(States.UNLOCK_GRILLE)){
            return actionUnlockGrille(type, contenu);
        }else if(type.equals(States.GET_VOLONTAIRE)){
            return actionGetVolontaire(type, contenu);
        }else if(type.equals(States.EDIT_VOLONTAIRE)){
            return actionEditVolontaire(type, contenu);
        }else if(type.equals(States.GET_GROUPE)){
            return actionGetGroupe(type, contenu);
        }else if(type.equals(States.NEW_GROUPE)){
            return actionNewGroupe(type, contenu);
        }else if(type.equals(States.EDIT_GROUPE)){
            return actionEditGroupe(type, contenu);
        }else if(type.equals(States.DELETE_GROUPE)){
            return actionDeleteGroupe(type, contenu);
        }else if(type.equals(States.GET_UTILISATEUR)){
            return actionGetUtilisateur(type, contenu);
        }else if(type.equals(States.EDIT_UTILISATEUR)){
            return actionEditUtilisateur(type, contenu);
        }else if(type.equals(States.DELETE_UTILISATEUR)){
            return actionDeleteUtilisateur(type, contenu);
        }else if(type.equals(States.NEW_UTILISATEUR)){
            return actionNouveauUtilisateur(type, contenu);
        }else if(type.equals(States.DELETE_VOLONTAIRE)){
            return actionDeleteVolontaire(type, contenu);
        }else if(type.equals(States.GET_VEHICULES_ALL)){
            return actionGetVehiculeAll(type, contenu);
        }else if(type.equals(States.GET_VEHICULES_DISPO)){
            return actionGetVehiculeDispo(type, contenu);
        }else if(type.equals(States.NEW_VEHICULE)){
            return actionNewVehicule(type, contenu);
        }else if(type.equals(States.EDIT_VEHICULE)){
            return actionEditVehicule(type, contenu);
        }else if(type.equals(States.DELETE_VEHICULE)){
            return actionDeleteVehicule(type, contenu);
        }else if(type.equals(States.GET_VEHICULE)){
            return actionGetVehicule(type, contenu);
        }else if(type.equals(States.GET_LIEUX_ALL)){
            return actionGetLieuxAll(type, contenu);
        }else if(type.equals(States.GET_LIEU)){
            return actionGetLieu(type, contenu);
        }else if(type.equals(States.NEW_LIEU)){
            return actionNewLieu(type, contenu);
        }else if(type.equals(States.EDIT_LIEU)){
            return actionEditLieu(type, contenu);
        }else if(type.equals(States.DELETE_LIEU)){
            return actionDeleteLieu(type, contenu);
        }else if(type.equals(States.GET_RESERVATION_ALL)){
            return actionGetReservationAll(type, contenu);
        }else if(type.equals(States.GET_RESERVATION)){
            return actionGetReservation(type, contenu);
        }else if(type.equals(States.NEW_RESERVATION)){
            return actionNewReservation(type, contenu);
        }else if(type.equals(States.EDIT_RESERVATION)){
            return actionEditReservation(type, contenu);
        }else if(type.equals(States.DELETE_RESERVATION)){
            return actionDeleteReservation(type, contenu);
        }else{
            return new PacketCom(States.ERROR, "ERROR");
        }
    }

    private PacketCom actionLogin(String type, Object contenu) {
        String[] infos = (String[]) contenu;
        String login = infos[0];
        String password = infos[1];
        try {
            dbRequests.getMysql().lockTable(new String[]{"utilisateurs"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.ERROR, "Erreur de vérouillage");
        }
        try {
            dbRequests.checkLogin(login);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.LOGIN_NON_USER, null);
        }
        try {
            idUser = dbRequests.checLoginPassword(login, password);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.LOGIN_NON_PASS, null);
        }
        try {
            //dbRequests.checkAlreadyLogged(login);
        } catch (Exception ex) {
            dbRequests.getMysql().rollback();
            return new PacketCom(States.LOGIN_NON, "cet utilisateur est déja connecté");
        }
        try {
            dbRequests.userLogged(idUser);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.LOGIN_NON, "Impossible de se connecter");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.LOGIN_OUI, null);
    }

    private PacketCom actionNouveauVolontaire(String type, Object contenu) {
        if(!droitsOffi.contains("CREATE_VOLUNTEER")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES,
                    "Vous ne possédez pas le droit de créer de nouveau volontaires");
            return packetRetour;
        }
        Volontaire volontaire = (Volontaire)contenu;
        return insererVolontaire(volontaire);
    }

    private PacketCom insererVolontaire(Volontaire volontaire){
        try{
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse", "FormationsSuivie", "Activite", "FormationSuivieActivite", "FormationActivite"});
            boolean check = false;
            check = dbRequests.checkMatricule(volontaire.getIdentite().getMatricule());
            if(!check){
                check = dbRequests.checkNomPrenom(volontaire.getIdentite().getNom(), volontaire.getIdentite().getPrenom());
            }
            if(!check){
                int idPersonneUrgence = -1;
                int idDecouverte = -1;
                int idRenseignement = -1;
                int idActivite = -1;
                int idAdresseLegale = -1;
                int idAdresseResidence = -1;
                int idTelephone = -1;

                idDecouverte = dbRequests.insertDecouvertes(volontaire.getDecouverte());
                idRenseignement = dbRequests.insertRenseignement(volontaire.getComplementaire());
                idAdresseLegale = dbRequests.insertAdresseLegale(volontaire.getAdresse(), volontaire.getIdentite().getMatricule());
                idAdresseResidence = dbRequests.insertAdresseResidence(volontaire.getResidence(), volontaire.getIdentite().getMatricule());
                idTelephone = dbRequests.insertTelephone(volontaire.getTelephone(), volontaire.getIdentite().getMatricule());
                idPersonneUrgence = dbRequests.insertPersonneUrgence(volontaire.getUrgence(), volontaire.getIdentite().getMatricule());
                idActivite = dbRequests.insertActivite(volontaire.getActivite(), volontaire.getIdentite().getMatricule());
                dbRequests.insertVolontaire(volontaire.getIdentite(), volontaire.getAdresse(), "", idPersonneUrgence, idDecouverte, -1, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, idActivite);
                dbRequests.insertFormations(volontaire.getFormations(), volontaire.getIdentite().getMatricule());



            }else{
                dbRequests.getMysql().rollback();
                return new PacketCom(States.NOUVEAU_VOLONTAIRE_NON, "Volontaire déja existant");
            }
            dbRequests.getMysql().commit();
            return new PacketCom(States.NOUVEAU_VOLONTAIRE_OUI, null);
        }catch(Exception ex){
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NOUVEAU_VOLONTAIRE_NON, "Une erreur s'est produite");
        }
    }

    private PacketCom actionEditVolontaire(String type, Object contenu) {
        Volontaire volontaire = (Volontaire)contenu;
        String matricule = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse", "FormationsSuivie", "Activite", "FormationSuivieActivite", "FormationActivite"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VOLONTAIRE_NON, "Impossible de modifier le volontaire");
        }
        try {
            matricule = dbRequests.getMatricule(volontaire.getIdentite().getNom(), volontaire.getIdentite().getPrenom());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VOLONTAIRE_NON, "Impossible de modifier le volontaire");
        }
        if(matricule != null && matricule.equals(volontaire.getIdentite().getMatricule())){
            matricule = volontaire.getIdentite().getMatricule();
        }else if(matricule != null){
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VOLONTAIRE_NON, "ce volontaire existe déja");
        }else{
            matricule = volontaire.getIdentite().getMatricule();
        }
        try {
            dbRequests.editIdentite(matricule, volontaire.getIdentite());
            dbRequests.editDecouverte(matricule, volontaire.getDecouverte());
            dbRequests.editComplementaire(matricule, volontaire.getComplementaire());
            dbRequests.editAdresseLegale(matricule, volontaire.getAdresse());
            dbRequests.editAdresseResidence(matricule, volontaire.getResidence());
            dbRequests.editTelephone(matricule, volontaire.getTelephone());
            dbRequests.editPersonneUrgence(matricule, volontaire.getUrgence());
            dbRequests.editFormations(matricule, volontaire.getFormations());
            dbRequests.editActivite(matricule, volontaire.getActivite());

        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VOLONTAIRE_NON, "Impossible de modifier le volontaire");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_VOLONTAIRE_OUI, "Modification du volontaire réussie");
    }

    private PacketCom actionGetVolontaire(String type, Object contenu) {
        String[] data = (String[]) contenu;
        String nom = data[0];
        String prenom = data[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse", "FormationsSuivie", "Activite", "FormationSuivieActivite", "FormationActivite"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_VOLONTAIRE_NON, "Impossible de récupérer le volontaire");
        }

        String matricule;
        try {
            matricule = dbRequests.getMatricule(nom, prenom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_VOLONTAIRE_NON, "Impossible de récupérer le volontaire");
        }
        if(matricule == null){
            return new PacketCom(States.GET_VOLONTAIRE_NON, "Impossible de récupérer le volontaire");
        }
        Volontaire volontaire = null;
        try {
            volontaire = dbRequests.getVolontaire(matricule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_VOLONTAIRE_NON, "Impossible de récupérer le volontaire");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_VOLONTAIRE_OUI, (Object)volontaire);
    }

    private PacketCom actionDeleteVolontaire(String type, Object contenu) {
        String[] data = (String[])contenu;
        String nom = data[0];
        String prenom = data[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"Volontaires", "PersonneUrgence", "Prestation", "Renseignements", "FormationsSuivie", "Formation", "Adresse", "AssignationCaseHoraire", "FormationActivite", "FormationSuivieActivite", "Activite", "LanguesConnue", "Telephone"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_VOLONTAIRE_NON, "Impossible de supprimer le volontaire");
        }
        String matricule = null;
        try {
            matricule = dbRequests.getMatricule(nom, prenom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_VOLONTAIRE_NON, "Impossible de supprimer le volontaire");
        }

        if(matricule == null){
            return new PacketCom(States.DELETE_VOLONTAIRE_NON, "Le volontaire n'existe pas");
        }
        try {
            dbRequests.supprimerVolontaire(matricule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_VOLONTAIRE_NON, "Impossible de supprimer le volontaire");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_VOLONTAIRE_OUI, null);
    }

    private PacketCom actionGetVolontaires(String type, Object contenu, String portee) {
        PacketCom packetReponse = null;
        if(portee.equals("all")){
            if(!droitsOffi.contains("READ_DATA_ALL")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
            LinkedList<String[]> listeVolontaire = null;
            try {
                dbRequests.getMysql().lockTable(new String[]{"volontaires", "Adresse", "Ville"});
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
                packetReponse = new PacketCom(States.GET_VOLONTAIRE_ALL_NON, "Impossible de récupérer la liste des volontaires");
            }
            try {
                listeVolontaire = dbRequests.getVolontairesAll();
                packetReponse = new PacketCom(States.GET_VOLONTAIRE_ALL_OUI, (Object)listeVolontaire);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
                packetReponse = new PacketCom(States.GET_VOLONTAIRE_ALL_NON, "Impossible de récupérer la liste des volontaires");
            }
        }else{
            if(!droitsOffi.contains("READ_DATA_MYSELF")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
            //TODO: si on ne veut pas tout.
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetDroits(String type, Object contenu) {
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_MANAGE_RIGHTS")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<String[]> listeDroits = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Droits"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_DROITS_ALL_NON, "Impossible de récupérer la liste des droits");
        }
        try {
            listeDroits = dbRequests.getDroits();
            packetReponse = new PacketCom(States.GET_DROITS_ALL_OUI, (Object)listeDroits);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_DROITS_ALL_NON, "Impossible de récupérer la liste des droits");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetGroupes(String type, Object contenu) {
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_MANAGE_GROUP")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<String[]> listeGroupes = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Groupes"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GROUPES_ALL_NON, "Impossible de récupérer la liste des groupes");
        }
        try {
            listeGroupes = dbRequests.getGroupes();
            packetReponse = new PacketCom(States.GET_GROUPES_ALL_OUI, (Object)listeGroupes);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GROUPES_ALL_NON, "Impossible de récupérer la liste des groupes");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetUtilisateurs(String type, Object contenu) {
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_MANAGE_USER")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<String[]> listeUtilisateurs = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_UTILISATEURS_ALL_NON, "Impossible de récupérer la liste des utilisateurs");
        }
        try {
            listeUtilisateurs = dbRequests.getUtilisateurs();
            packetReponse = new PacketCom(States.GET_UTILISATEURS_ALL_OUI, (Object)listeUtilisateurs);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_UTILISATEURS_ALL_NON, "Impossible de récupérer la liste des utilisateurs");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetMyDroits(String type, Object contenu) {
        PacketCom packetReponse = null;
        LinkedList<String> listeDroits = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Droits", "Utilisateurs", "Appartenir", "Groupes", "PossederDroit"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_MY_DROITS_NON, "Impossible de récupérer la liste des droits");
        }
        try {
            listeDroits = dbRequests.getMyDroits(idUser);
            droitsOffi = listeDroits;
            packetReponse = new PacketCom(States.GET_MY_DROITS_OUI, (Object)listeDroits);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_MY_DROITS_NON, "Impossible de récupérer la liste des droits");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetDetailsUser(String type, Object contenu) {
        PacketCom packetReponse = null;
        String login = (String)contenu;
        int idUser = -1;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_DETAILS_USER_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        try {
            idUser = dbRequests.getIdUser(login);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_DETAILS_USER_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        if(idUser == this.idUser){
            if(!droitsOffi.contains("READ_DATA_MYSELF")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
        }else{
            if(!droitsOffi.contains("READ_DATA_ALL")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
        }
        Map<String, String> dataUser = null;
        try {
            dataUser = dbRequests.getDataUser(idUser);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_DETAILS_USER_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        if(dataUser != null){
            packetReponse = new PacketCom(States.GET_DETAILS_USER_OUI, (Object)dataUser);
        }else{
            packetReponse = new PacketCom(States.GET_DETAILS_USER_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetEquipes(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGER_TEAM")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        PacketCom packetReponse = null;
        LinkedList<String[]> listeEquipes = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Equipe", "Lier"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_EQUIPES_ALL_NON, "Impossible de récupérer la liste des équipes");
        }
        try {
            listeEquipes = dbRequests.getEquipes();
            packetReponse = new PacketCom(States.GET_EQUIPES_ALL_OUI, (Object)listeEquipes);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_EQUIPES_ALL_NON, "Impossible de récupérer la liste des équipes");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionRecherche(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGER_TEAM")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }

        LinkedList<CritereCustom> listeCritereCustoms = new LinkedList<CritereCustom>();
        Object[] data = (Object[]) contenu;
        LinkedList<Critere> listeCriteres = (LinkedList<Critere>)data[0];
        LinkedList<Object[]> dates = (LinkedList<Object[]>)data[1];

        for(Critere critere : listeCriteres){
            if(critere.getType().equals("nom")){
                ByNom byNom = new ByNom(critere.getDonnee(), dbRequests);
                byNom.doSearch();
                listeCritereCustoms.add(byNom);
            }else if(critere.getType().equals("prenom")){
                ByPrenom byPrenom = new ByPrenom(critere.getDonnee(), dbRequests);
                byPrenom.doSearch();
                listeCritereCustoms.add(byPrenom);
            }else if(critere.getType().equals("formation")){
                ByFormation byFormation = new ByFormation(critere.getDonnee(), dbRequests);
                byFormation.doSearch();
                listeCritereCustoms.add(byFormation);
            }else if(critere.getType().equals("dateNaissance")){
                ByDateNaissance byDateNaissance = new ByDateNaissance(critere.getDonnee(), dbRequests);
                byDateNaissance.doSearch();
                listeCritereCustoms.add(byDateNaissance);
            }else if(critere.getType().equals("codePostal")){
                ByCodePostal byCodePostal = new ByCodePostal(critere.getDonnee(), dbRequests);
                byCodePostal.doSearch();
                listeCritereCustoms.add(byCodePostal);
            }
        }

        TraitementRecherche traitementRecherche = new TraitementRecherche();
        traitementRecherche.Filtrer(listeCritereCustoms);
        try {
            traitementRecherche.setResultats(dbRequests.checkDisponibiliteVolontaire(traitementRecherche.getResultats(), dates));
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.RECHERCHE_NON, "Une erreur s'est produite durant la recherche");
        }

        return new PacketCom(States.RECHERCHE_OUI, (Object)traitementRecherche.getResultats());
    }

    /*private PacketCom actionNouvelleEquipe(String type, Object contenu) {
        Equipe equipe = (Equipe) contenu;
        PacketCom packetReponse = null;
        int idEquipe = -1;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Equipe", "Lier", "volontaires"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NOUVELLE_EQUIPE_NON, (Object)ex.getMessage());
        }
        try {
            idEquipe = dbRequests.insertEquipe(equipe.getNom());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NOUVELLE_EQUIPE_NON, (Object)ex.getMessage());
        }
        if(idEquipe == -1){
            return new PacketCom(States.NOUVELLE_EQUIPE_NON, "Impossible de trouver l'équipe");
        }
        for(TupleRecherche tuple : equipe.getVolontaires()){
            String matricule = null;
            try {
                matricule = dbRequests.getMatricule(tuple.getNom(), tuple.getPrenom());
                dbRequests.insertMembreEquipe(idEquipe, matricule);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
                return new PacketCom(States.NOUVELLE_EQUIPE_NON, (Object)ex.getMessage());
            }
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.NOUVELLE_EQUIPE_OUI, null);
    }*/

    private PacketCom actionGetFormation(String type, Object contenu) {
        //TODO - Créer un droit pour récup les formations
        PacketCom packetReponse = null;
        LinkedList<String> listeFormations = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"formation"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_NON, "Impossible de trouver les formations");
        }
        try {
            listeFormations = dbRequests.getFormations();
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_OUI, (Object)listeFormations);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_NON, "Impossible de trouver les formations");
        }
        dbRequests.getMysql().commit();
        return packetReponse;

    }

    private PacketCom actionGetGrillesHoraires(String type, Object contenu) {
        //TODO - Créer un droit pour récup les grilles horaires
        if(!droitsOffi.contains("SEE_HORAIRE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }
        LinkedList<Object[]> listeGrillesHoraires = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "Vehicules", "Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLES_HORAIRES_NON, "Impossible de trouver les grilles horaire");
        }
        try {
            listeGrillesHoraires = dbRequests.getGrillesHoraires();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLES_HORAIRES_NON, "Impossible de trouver les grilles horaire");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_GRILLES_HORAIRES_OUI, (Object)listeGrillesHoraires);
    }

    private PacketCom actionDeleteGrille(String type, Object contenu) {
        if(!droitsOffi.contains("EDIT_HORAIRE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int année = (int)data[1];
        String ambulance = (String)data[2];
        String lieu = (String)data[3];
        int idGrilleHoraire = -1;

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "Vehicules", "Lieux", "CaseHoraire", "AssignationCaseHoraire"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_GRILLE_NON, "Impossible de supprimer la grille horaire");
        }
        try {
            idGrilleHoraire = dbRequests.getIdGrilleHoraire(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }

        try {
            dbRequests.supprimerGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_GRILLE_NON, "Impossible de supprimer la grille horaire");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_GRILLE_OUI, null);
    }

    private PacketCom actionGetGrille(String type, Object contenu) {
        if(!droitsOffi.contains("EDIT_HORAIRE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int année = (int)data[1];
        String ambulance = (String)data[2];
        String lieu = (String)data[3];
        int idGrilleHoraire = -1;
        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "CaseHoraire", "assignationCaseHoraire", "Vehicules", "Lieux", "Volontaires"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }
        try {
            idGrilleHoraire = dbRequests.getIdGrilleHoraire(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }
        try {
            dbRequests.lockGrille(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }


        Grille grille = null;
        try {
            grille = dbRequests.getGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }

        LinkedList<Key> cellules = null;
        try {
            cellules = dbRequests.getCellules(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Cellules de la grille irrécupérable");
        }

        grille.setGrilles(cellules);
        try {
            dbRequests.getVolontaireGrille(grille, idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Volontaires de la grille inconnue");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_GRILLE_OUI, (Object)grille);
    }

    private PacketCom actionNewGrillesHoraires(String type, Object contenu) {
        Grille grille = (Grille)contenu;
        int idGrilleHoraire = -1;

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "volontaires", "caseHoraire", "AssignationCaseHoraire", "Vehicules", "Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur de création de nouvelle grille");
        }
        int id = -1;
        try {
            id = dbRequests.getIdGrilleHoraire(grille.getSemaine(), grille.getAnnee(), grille.getVehicule().getNom(), grille.getLieu());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur de création de nouvelle grille");
        }

        if(id!= -1){
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "La grille horaire pour la semaine " + grille.getSemaine() + " déja existante");
        }

        try {
            idGrilleHoraire = dbRequests.insertGrilleHoraire(grille);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "La grille horaire pour la semaine " + grille.getSemaine() + " déja existante");
        }

        for(Key key : grille.getGrilles()){
            boolean matriculed = false;
            String nom = null;
            String prenom = null;
            String nomColle = key.getValue().getNomPrenom();
            String matricule = null;
            if(nomColle != null && !nomColle.isEmpty()){
                String[] split = nomColle.split(" ");
                nom = split[0];
                prenom = split[1];
                try {
                    matricule = dbRequests.getMatricule(nom, prenom);
                    matriculed = true;
                } catch (Exception ex) {
                    Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                    dbRequests.getMysql().rollback();
                    return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Volontaires introuvable dans la grille");
                }
            }
            int idCaseHoraire= -1;
            try {
                idCaseHoraire = dbRequests.insertCellule(key.getValue(), idGrilleHoraire);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Impossible d'insérer la cellule dans la grille");
            }
            if(matriculed){
                try {
                    dbRequests.insertAssignationCaseHoraire(idCaseHoraire, matricule);
                } catch (Exception ex) {
                    Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                    dbRequests.getMysql().rollback();
                    return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Impossible d'insérer la cellule dans la grille");
                }
            }
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_GRILLE_HORAIRE_OUI, null);
    }

    private PacketCom actionEditGrillesHoraires(String type, Object contenu) {
        Object[] data = (Object[]) contenu;
        Grille oldGrille = (Grille)data[0];
        Grille newGrille = (Grille)data[1];
        int idGrilleHoraire = -1;

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "volontaires", "caseHoraire", "AssignationCaseHoraire", "Vehicules", "Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }
        int id = -1;
        try {
            id = dbRequests.getIdGrilleHoraire(newGrille.getSemaine(), newGrille.getAnnee(), newGrille.getVehicule().getNom(), newGrille.getLieu());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur de création de nouvelle grille");
        }
        if(!(oldGrille.getSemaine() == newGrille.getSemaine() && oldGrille.getAnnee() == newGrille.getAnnee() && oldGrille.getVehicule().getNom().equals(newGrille.getVehicule().getNom()) && oldGrille.getLieu().equals(newGrille.getLieu()))){
            if(id!= -1){
                dbRequests.getMysql().rollback();
                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "La grille horaire pour la semaine " + newGrille.getSemaine() + " déja existante");
            }
        }

        try {
            idGrilleHoraire = dbRequests.editGrilleHoraire(oldGrille, newGrille);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }

        for(Key key : newGrille.getGrilles()){
            String nom = null;
            String prenom = null;
            String nomColle = key.getValue().getNomPrenom();
            String matricule = null;
            if(nomColle != null && !nomColle.isEmpty()){
                String[] split = nomColle.split(" ");
                nom = split[0];
                prenom = split[1];
                try {
                    matricule = dbRequests.getMatricule(nom, prenom);
                } catch (Exception ex) {
                    Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                    dbRequests.getMysql().rollback();
                    return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                }
            }
            int idCaseHoraire = -1;
            for(Key keyOld : oldGrille.getGrilles()){
                if(keyOld.getX() == key.getX() && keyOld.getY() == key.getY()){
                    try {
                        idCaseHoraire = dbRequests.EditCellule(keyOld.getValue(), idGrilleHoraire, key.getValue());
                    } catch (Exception ex) {
                        Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                        dbRequests.getMysql().rollback();
                        return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                    }
                    if(keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() == null){

                    }else{
                        if((keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() != null)){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                            }
                        }else if(keyOld.getValue().getNomPrenom() != null && key.getValue().getNomPrenom() == null){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                            }
                        }else if(!keyOld.getValue().getNomPrenom().equals(key.getValue().getNomPrenom())){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                            }
                        }
                    }
                    break;
                }
            }
        }
        try {
            dbRequests.unlockGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_GRILLE_HORAIRE_OUI, null);
    }

    private PacketCom actionCheckLockGrille(String type, Object contenu) {
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int année = (int)data[1];
        String nomVehicule = (String)data[2];
        String lieu = (String)data[3];

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "Vehicules", "Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }

        boolean locked = true;
        try {
            locked = dbRequests.checkLockGrille(semaine, année, nomVehicule, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GRILLE_LOCKED, "Grille inconnue");
        }

        if(!locked){
            dbRequests.getMysql().commit();
            return new PacketCom(States.GRILLE_UNLOCKED, null);
        }else{
            dbRequests.getMysql().commit();
            return new PacketCom(States.GRILLE_LOCKED, null);
        }
    }

    private PacketCom actionUnlockGrille(String type, Object contenu) {
        Grille grille = (Grille)contenu;
        int semaine = grille.getSemaine();
        int année = grille.getAnnee();
        Vehicule vehicule = grille.getVehicule();
        String lieu = grille.getLieu();

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "Vehicules", "Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }
        try {
            dbRequests.unlockGrille(semaine, année, vehicule, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.UNLOCK_GRILLE_NON, null);
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.UNLOCK_GRILLE_OUI, null);
    }

    private PacketCom actionDisconnect(String type, Object contenu) {
        try {
            dbRequests.getMysql().lockTable(new String[]{"utilisateurs"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.ERROR, "Erreur de déconnection");
        }
        try {
            dbRequests.userUnlogged(idUser);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.ERROR, "Erreur de déconnection");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.DISCONNECT, "déconnection ok");
    }

    private PacketCom actionGetGroupe(String type, Object contenu) {
        String nom = (String)contenu;

        try {
            dbRequests.getMysql().lockTable(new String[]{"Groupes", "Droits", "PossederDroit"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GROUPE_NON, "Impossible de récupérer le groupe");
        }

        Groupe groupe = null;
        try {
            groupe = dbRequests.getGroupe(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GROUPE_NON, "Impossible de récupérer le groupe");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_GROUPE_OUI, (Object)groupe);
    }

    private PacketCom actionNewGroupe(String type, Object contenu) {
        Groupe groupe = (Groupe)contenu;

        try {
            dbRequests.getMysql().lockTable(new String[]{"Groupes", "Droits", "PossederDroit"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GROUPE_NON, "Impossible d'ajouter le groupe");
        }

        Groupe exist;
        try {
            exist = dbRequests.getGroupe(groupe.getNom());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GROUPE_NON, "Impossible d'ajouter le groupe");
        }
        if(exist != null){
            return new PacketCom(States.NEW_GROUPE_NON, "Groupe déja existant");
        }

        try {
            dbRequests.nouveauGroupe(groupe);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GROUPE_NON, "Impossible d'ajouter le groupe");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_GROUPE_OUI, null);
    }

    private PacketCom actionEditGroupe(String type, Object contenu) {
        Groupe[] groupes = (Groupe[])contenu;
        Groupe oldGroupe = groupes[0];
        Groupe newGroupe = groupes[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"Groupes", "Droits", "PossederDroit"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GROUPE_NON, "Impossible de modifier le groupe");
        }

        Groupe exist = null;
        if(!oldGroupe.getNom().equals(newGroupe.getNom())){
            try {
                exist = dbRequests.getGroupe(newGroupe.getNom());
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GROUPE_NON, "Impossible de modifier le groupe");
            }
        }
        if(exist != null){
            return new PacketCom(States.EDIT_GROUPE_NON, "Groupe déja existant");
        }

        try {
            dbRequests.modifierGroupe(oldGroupe, newGroupe);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_GROUPE_NON, "Impossible de modifier le groupe");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_GROUPE_OUI, null);
    }

    private PacketCom actionDeleteGroupe(String type, Object contenu) {
        String nom = (String)contenu;

        try {
            dbRequests.getMysql().lockTable(new String[]{"Groupes", "Droits", "PossederDroit"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_GROUPE_NON, "Impossible de supprimer le groupe");
        }

        try {
            dbRequests.supprimerGroupe(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_GROUPE_NON, "Impossible de supprimer le groupe");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_GROUPE_OUI, null);
    }

    private PacketCom actionGetUtilisateur(String type, Object contenu) {
        PacketCom packetReponse = null;
        String login = (String)contenu;
        int idUser = -1;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs", "Appartenir", "Groupes"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_UTILISATEUR_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        try {
            idUser = dbRequests.getIdUser(login);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_UTILISATEUR_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        if(idUser == this.idUser){
            if(!droitsOffi.contains("READ_DATA_MYSELF")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
        }else{
            if(!droitsOffi.contains("READ_DATA_ALL")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
        }
        Utilisateur utilisateur = null;
        try {
            utilisateur = dbRequests.getUtilisateur(idUser);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_UTILISATEUR_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        if(utilisateur != null){
            packetReponse = new PacketCom(States.GET_UTILISATEUR_OUI, (Object)utilisateur);
        }else{
            packetReponse = new PacketCom(States.GET_UTILISATEUR_NON, "Impossible de récupérer les détails de l'utilisateur");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionNouveauUtilisateur(String type, Object contenu) {
        Utilisateur utilisateur = (Utilisateur)contenu;

        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs", "Appartenir", "Groupes"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_UTILISATEUR_NON, "Impossible d'ajouter l'utilisateur");
        }

        int idUser = -1;
        try {
            idUser = dbRequests.getIdUser(utilisateur.getLogin());
        } catch (Exception ex) {
        }

        if(idUser != -1){
            return new PacketCom(States.NEW_UTILISATEUR_NON, "Utilisateur déja existant");
        }

        try {
            dbRequests.nouveauUtilisateur(utilisateur);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_UTILISATEUR_NON, "Impossible d'ajouter l'utilisateur");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_UTILISATEUR_OUI, null);
    }

    private PacketCom actionDeleteUtilisateur(String type, Object contenu) {
        String login = (String)contenu;

        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs", "Appartenir", "Groupes"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_UTILISATEUR_NON, "Impossible de supprimer l'utilisateur");
        }

        try {
            dbRequests.supprimerUtilisateur(login);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_UTILISATEUR_NON, "Impossible de supprimer l'utilisateur");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_UTILISATEUR_OUI, null);
    }

    private PacketCom actionEditUtilisateur(String type, Object contenu) {
        Utilisateur[] utilisateur = (Utilisateur[])contenu;
        Utilisateur oldUtilisateur = utilisateur[0];
        Utilisateur newUtilisateur = utilisateur[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"Utilisateurs", "Appartenir", "Groupes"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_UTILISATEUR_NON, "Impossible de modifier l'utilisateur");
        }

        int idUser = -1;
        try {
            idUser = dbRequests.getIdUser(newUtilisateur.getLogin());
        } catch (Exception ex) {
        }
        if(!oldUtilisateur.getLogin().equals(newUtilisateur.getLogin())){
            if(idUser != -1){
                return new PacketCom(States.EDIT_UTILISATEUR_NON, "Utilisateur déja existant");
            }
        }

        try {
            dbRequests.modifierUtilisateur(oldUtilisateur, newUtilisateur);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_UTILISATEUR_NON, "Impossible de modifier l'utilisateur");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_UTILISATEUR_OUI, null);
    }

    private PacketCom actionGetVehiculeAll(String type, Object contenu) {
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<Object[]> listeVehicule = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_VEHICULES_ALL_NON, "Impossible de récupérer la liste des véhicules");
        }
        try {
            listeVehicule = dbRequests.getVehicules();
            packetReponse = new PacketCom(States.GET_VEHICULES_ALL_OUI, (Object)listeVehicule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_VEHICULES_ALL_NON, "Impossible de récupérer la liste des véhicules");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetVehiculeDispo(String type, Object contenu) {
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int annee = (int)data[1];
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<Object[]> listeVehicule = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules", "Reservations"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_VEHICULES_DISPO_NON, "Impossible de récupérer la liste des véhicules");
        }
        try {
            listeVehicule = dbRequests.getVehicules(semaine, annee);
            packetReponse = new PacketCom(States.GET_VEHICULES_DISPO_OUI, (Object)listeVehicule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_VEHICULES_DISPO_NON, "Impossible de récupérer la liste des véhicules");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionNewVehicule(String type, Object contenu) {
        Vehicule vehicule = (Vehicule)contenu;
        if(!droitsOffi.contains("CREATE_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_VEHICULE_NON, "Impossible d'ajouter le véhicule");
        }

        Vehicule exist;
        try {
            exist = dbRequests.getVehicule(vehicule.getNom());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_VEHICULE_NON, "Impossible d'ajouter le véhicule");
        }
        if(exist != null){
            return new PacketCom(States.NEW_VEHICULE_NON, "Véhicule déja existant");
        }

        try {
            dbRequests.nouveauVehicule(vehicule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_VEHICULE_NON, "Impossible d'ajouter le véhicule");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_VEHICULE_OUI, null);
    }

    private PacketCom actionEditVehicule(String type, Object contenu) {
        Vehicule[] vehicules = (Vehicule[])contenu;
        Vehicule oldVehicule = vehicules[0];
        Vehicule newVehicule = vehicules[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VEHICULE_NON, "Impossible de modifier le véhicule");
        }

        Vehicule exist = null;
        if(!oldVehicule.getNom().equals(newVehicule.getNom())){
            try {
                exist = dbRequests.getVehicule(newVehicule.getNom());
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VEHICULE_NON, "Impossible de modifier le véhicule");
            }
        }
        if(exist != null){
            return new PacketCom(States.EDIT_VEHICULE_NON, "Véhicule déja existant");
        }

        try {
            dbRequests.modifierVehicule(oldVehicule, newVehicule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_VEHICULE_NON, "Impossible de modifier le véhicule");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_VEHICULE_OUI, null);
    }

    private PacketCom actionDeleteVehicule(String type, Object contenu) {
        String nom = (String)contenu;
        if(!droitsOffi.contains("EDIT_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_VEHICULE_NON, "Impossible de supprimer le véhicule");
        }

        try {
            dbRequests.supprimerVehicule(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_VEHICULE_NON, "Impossible de supprimer le véhicule");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_VEHICULE_OUI, null);
    }

    private PacketCom actionGetVehicule(String type, Object contenu) {
        String nom = (String)contenu;
        if(!droitsOffi.contains("SEE_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_VEHICULE_NON, "Impossible de récupérer le véhicule");
        }

        Vehicule vehicule = null;
        try {
            vehicule = dbRequests.getVehicule(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_VEHICULE_NON, "Impossible de récupérer le véhicule");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_VEHICULE_OUI, (Object)vehicule);
    }

    private PacketCom actionGetLieuxAll(String type, Object contenu) {
        PacketCom packetReponse = null;
        if(!droitsOffi.contains("SEE_LIEU")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<Object[]> listeLieux = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_LIEUX_ALL_NON, "Impossible de récupérer la liste des lieux");
        }
        try {
            listeLieux = dbRequests.getLieux();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_LIEUX_ALL_NON, "Impossible de récupérer la liste des lieux");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_LIEUX_ALL_OUI, (Object)listeLieux);
    }

    private PacketCom actionGetLieu(String type, Object contenu) {
        String nom = (String)contenu;
        if(!droitsOffi.contains("SEE_LIEU")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_LIEU_NON, "Impossible de récupérer le lieu");
        }

        String lieu = null;
        try {
            lieu = dbRequests.getLieu(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_LIEU_NON, "Impossible de récupérer le lieu");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_LIEU_OUI, (Object)lieu);
    }

    private PacketCom actionNewLieu(String type, Object contenu) {
        String lieu = (String)contenu;
        if(!droitsOffi.contains("CREATE_LIEU")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_LIEU_NON, "Impossible d'ajouter le lieu");
        }

        String exist;
        try {
            exist = dbRequests.getLieu(lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_LIEU_NON, "Impossible d'ajouter le lieu");
        }
        if(exist != null){
            return new PacketCom(States.NEW_LIEU_NON, "Lieu déja existant");
        }

        try {
            dbRequests.nouveauLieu(lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_LIEU_NON, "Impossible d'ajouter le lieu");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_LIEU_OUI, null);
    }

    private PacketCom actionEditLieu(String type, Object contenu) {
        String[] lieux = (String[])contenu;
        String oldLieu = lieux[0];
        String newLieu = lieux[1];

        try {
            dbRequests.getMysql().lockTable(new String[]{"Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_LIEU_NON, "Impossible de modifier le lieu");
        }

        String exist = null;
        if(!oldLieu.equals(newLieu)){
            try {
                exist = dbRequests.getLieu(newLieu);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_LIEU_NON, "Impossible de modifier le lieu");
            }
        }
        if(exist != null){
            return new PacketCom(States.EDIT_LIEU_NON, "Lieu déja existant");
        }

        try {
            dbRequests.modifierLieu(oldLieu, newLieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_LIEU_NON, "Impossible de modifier le lieu");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_LIEU_OUI, null);
    }

    private PacketCom actionDeleteLieu(String type, Object contenu) {
        String nom = (String)contenu;
        if(!droitsOffi.contains("EDIT_LIEU")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Lieux"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_LIEU_NON, "Impossible de supprimer le lieu");
        }

        try {
            dbRequests.supprimerLieu(nom);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_LIEU_NON, "Impossible de supprimer le lieu");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_LIEU_OUI, null);
    }

    private PacketCom actionGetReservationAll(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_RESERVATIONS_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        LinkedList<Object[]> listeReservation = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"Reservations", "Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_RESERVATION_ALL_NON, "Impossible de récupérer la liste des véhicules réservé");
        }
        try {
            listeReservation = dbRequests.getReservations();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_RESERVATION_ALL_NON, "Impossible de récupérer la liste des véhicules réservé");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_RESERVATION_ALL_OUI, (Object)listeReservation);
    }

    private PacketCom actionGetReservation(String type, Object contenu) {
        Object[] data = (Object[])contenu;
        String vehicule = (String)data[0];
        int semaine = (int)data[1];
        int annee = (int)data[2];

        if(!droitsOffi.contains("SEE_RESERVATIONS_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Reservations", "Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_RESERVATION_NON, "Impossible de récupérer la réservation du véhicule");
        }

        ReservationVehicule reservation = null;
        try {
            reservation = dbRequests.getReservation(vehicule, semaine, annee);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_RESERVATION_NON, "Impossible de récupérer la réservation du véhicule");
        }
        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_RESERVATION_OUI, (Object)reservation);
    }

    private PacketCom actionNewReservation(String type, Object contenu) {
        ReservationVehicule reservation = (ReservationVehicule)contenu;
        if(!droitsOffi.contains("CREATE_RESERVATIONS_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Reservations", "Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_RESERVATION_NON, "Impossible d'ajouter la réservation du véhicule");
        }

        ReservationVehicule exist;
        try {
            exist = dbRequests.getReservation(reservation.getNomVehicule(), reservation.getSemaine(), reservation.getAnnee());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_RESERVATION_NON, "Impossible d'ajouter la réservation du véhicule");
        }
        if(exist != null){
            return new PacketCom(States.NEW_RESERVATION_NON, "Réservation déja existante");
        }

        try {
            dbRequests.nouveauReservation(reservation);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_RESERVATION_NON, "Impossible d'ajouter la réservation");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.NEW_RESERVATION_OUI, null);
    }

    private PacketCom actionEditReservation(String type, Object contenu) {
        ReservationVehicule[] reservations = (ReservationVehicule[])contenu;
        ReservationVehicule oldReservation = reservations[0];
        ReservationVehicule newReservation = reservations[1];

        if(!droitsOffi.contains("EDIT_RESERVATIONS_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Reservations", "Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_RESERVATION_NON, "Impossible de modifier la réservation");
        }

        ReservationVehicule exist = null;
        if(!oldReservation.equals(newReservation)){
            try {
                exist = dbRequests.getReservation(newReservation.getNomVehicule(), newReservation.getSemaine(), newReservation.getAnnee());
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_RESERVATION_NON, "Impossible de modifier la réservation");
            }
        }
        if(exist != null){
            return new PacketCom(States.EDIT_RESERVATION_NON, "Réservation déja existante");
        }

        try {
            dbRequests.modifierReservation(oldReservation, newReservation);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.EDIT_RESERVATION_NON, "Impossible de modifier la réservation");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.EDIT_RESERVATION_OUI, null);
    }

    private PacketCom actionDeleteReservation(String type, Object contenu) {
        Object[] data = (Object[])contenu;
        String vehicule = (String)data[0];
        int semaine = (int)data[1];
        int annee = (int)data[2];
        if(!droitsOffi.contains("EDIT_RESERVATIONS_VEHICULE")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas les droits nécessaires");
            return packetRetour;
        }

        try {
            dbRequests.getMysql().lockTable(new String[]{"Reservations", "Vehicules"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_RESERVATION_NON, "Impossible de supprimer la réservation");
        }

        try {
            dbRequests.supprimerReservation(vehicule, semaine, annee);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.DELETE_RESERVATION_NON, "Impossible de supprimer la réservation");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.DELETE_RESERVATION_OUI, null);
    }
}
