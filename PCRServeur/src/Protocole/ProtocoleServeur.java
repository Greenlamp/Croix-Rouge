/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import Containers.Grille;
import Containers.Groupe;
import Containers.Key;
import Containers.Utilisateur;
import Containers.Volontaire;
import DB.DbRequests;
import PacketCom.PacketCom;
import PacketCom.Protocolable;
import Recherche.Critere;
import Recherche.Criteres.ByDateNaissance;
import Recherche.Criteres.ByFormation;
import Recherche.Criteres.ByNom;
import Recherche.Criteres.ByPrenom;
import Recherche.Criteres.CritereCustom;
import Recherche.Criteres.TraitementRecherche;
import Recherche.Equipe;
import Recherche.TupleRecherche;
import States.States;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        }else if(type.equals(States.NOUVELLE_EQUIPE)){
            return actionNouvelleEquipe(type, contenu);
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
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit de créer de nouveau volontaires");
            return packetRetour;
        }
        Volontaire volontaire = (Volontaire)contenu;
        return insererVolontaire(volontaire);
    }

    private PacketCom insererVolontaire(Volontaire volontaire){
        try{
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse"});
            boolean check = false;
            check = dbRequests.checkMatricule(volontaire.getIdentite().getMatricule());
            if(!check){
                check = dbRequests.checkNomPrenom(volontaire.getIdentite().getNom(), volontaire.getIdentite().getPrenom());
            }
            if(!check){
                int idPersonneUrgence = -1;
                int idDecouverte = -1;
                int idRenseignement = -1;
                int idPaysLegal = -1;
                int idVilleLegal = -1;
                int idAdresseLegale = -1;
                int idPaysResidence = -1;
                int idVilleResidence = -1;
                int idAdresseResidence = -1;
                int idTelephone = -1;



                /*
                if(volontaire.getFormations() != null) dbRequests.insertFormationsSuivie(volontaire.getIdentite().getMatricule(), volontaire.getFormations().getListeFormation());
                */

                /*OLD*/
                //if(volontaire.getDecouverte() != null) idDecouverte = dbRequests.insertDecouvertes(volontaire.getDecouverte().getDescription());
                //if(volontaire.getComplementaire() != null) idLangueMaternelle = dbRequests.insertLangue(volontaire.getComplementaire().getLangueMaternelle());
                //if(volontaire.getComplementaire() != null) dbRequests.insertLanguesConnue(idRenseignement, volontaire.getComplementaire().getListeLangue());
                //if(volontaire.getComplementaire() != null) idRenseignement = dbRequests.insertRenseignement(volontaire.getComplementaire().getActivitePro(), volontaire.getComplementaire().getActivite(), volontaire.getComplementaire().getQualification(), (volontaire.getComplementaire().isPermis() ? "Oui" : "Non"), volontaire.getComplementaire().getCategorie(), volontaire.getComplementaire().getDateObtention(), (volontaire.getComplementaire().isSelectionMedicale() ? "Oui" : "Non"), volontaire.getComplementaire().getDateValidité(), idLangueMaternelle);
                //if(volontaire.getAdresse() != null) idPaysLegal = dbRequests.insertPays(volontaire.getAdresse().getPays());
                //if(volontaire.getAdresse() != null) idVilleLegal = dbRequests.insertVille(volontaire.getAdresse().getVille());
                //if(volontaire.getAdresse() != null) idAdresseLegale = dbRequests.insertAdresse(volontaire.getAdresse().getRue(), volontaire.getAdresse().getNuméro(), volontaire.getAdresse().getCodePostal(), volontaire.getAdresse().getBoite(), idPaysLegal, idVilleLegal, volontaire.getIdentite().getMatricule());
                //if(volontaire.getResidence() != null) idPaysResidence = dbRequests.insertPays(volontaire.getResidence().getPays());
                //if(volontaire.getResidence() != null) idVilleResidence = dbRequests.insertVille(volontaire.getResidence().getVille());
                //if(volontaire.getTelephone() != null) idTelephone = dbRequests.insertTelephone(volontaire.getTelephone().getGsm(), volontaire.getTelephone().getAutreGsm(), volontaire.getTelephone().getTelephoneFix(), volontaire.getTelephone().getTelephoneProfesionnelle(), volontaire.getTelephone().getFax());
                //if(volontaire.getUrgence() != null) idPersonneUrgence = dbRequests.insertPersonneUrgence(volontaire.getUrgence().getNom(), volontaire.getUrgence().getPrenom(), volontaire.getUrgence().getTelephone());
                //if(volontaire.getResidence() != null) idAdresseResidence = dbRequests.insertAdresse(volontaire.getResidence().getRue(), volontaire.getResidence().getNuméro(), volontaire.getResidence().getCodePostal(), volontaire.getResidence().getBoite(), idPaysResidence, idVilleResidence, volontaire.getIdentite().getMatricule());
                                //if(volontaire.getIdentite() != null) dbRequests.insertVolontaire(volontaire.getIdentite().getMatricule(), volontaire.getIdentite().getNom(), volontaire.getIdentite().getPrenom(), volontaire.getIdentite().getNomJeuneFille(), volontaire.getIdentite().getDateNaissance(), volontaire.getIdentite().getSexe(), (volontaire.getAdresse() == null ? null : volontaire.getAdresse().getEmail()), "", idPersonneUrgence, idDecouverte, -1, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, -1);

                /*NEW*/
                idDecouverte = dbRequests.insertDecouvertes(volontaire.getDecouverte());
                idRenseignement = dbRequests.insertRenseignement(volontaire.getComplementaire());
                idAdresseLegale = dbRequests.insertAdresseLegale(volontaire.getAdresse(), volontaire.getIdentite().getMatricule());
                idAdresseResidence = dbRequests.insertAdresseResidence(volontaire.getResidence(), volontaire.getIdentite().getMatricule());
                idTelephone = dbRequests.insertTelephone(volontaire.getTelephone(), volontaire.getIdentite().getMatricule());
                idPersonneUrgence = dbRequests.insertPersonneUrgence(volontaire.getUrgence(), volontaire.getIdentite().getMatricule());
                dbRequests.insertVolontaire(volontaire.getIdentite(), volontaire.getAdresse(), "", idPersonneUrgence, idDecouverte, -1, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, -1);



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
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse"});
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
            dbRequests.getMysql().lockTable(new String[]{"volontaires", "PersonneUrgence", "Decouverte", "langue", "Renseignements", "LanguesConnue", "Formation", "Pays", "Ville", "Telephone", "Adresse"});
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

        LinkedList<CritereCustom> listeCritereCustoms = new LinkedList<>();
        LinkedList<Critere> listeCriteres = (LinkedList<Critere>) contenu;

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
            }
        }

        TraitementRecherche traitementRecherche = new TraitementRecherche();
        traitementRecherche.Filtrer(listeCritereCustoms);

        return new PacketCom(States.RECHERCHE_OUI, (Object)traitementRecherche.getResultats());
    }

    private PacketCom actionNouvelleEquipe(String type, Object contenu) {
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
    }

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
        PacketCom packetReponse = null;
        LinkedList<String[]> listeGrillesHoraires = null;
        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_NON, "Impossible de trouver les formations");
        }
        try {
            listeGrillesHoraires = dbRequests.getGrillesHoraires();
            packetReponse = new PacketCom(States.GET_GRILLES_HORAIRES_OUI, (Object)listeGrillesHoraires);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_NON, "Impossible de trouver les formations");
        }
        dbRequests.getMysql().commit();
        return packetReponse;
    }

    private PacketCom actionGetGrille(String type, Object contenu) {
        PacketCom packetReponse = null;
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int année = (int)data[1];
        String ambulance = (String)data[2];
        String lieu = (String)data[3];
        int idGrilleHoraire = -1;
        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "CaseHoraire", "assignationCaseHoraire"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }
        try {
            idGrilleHoraire = dbRequests.getIdGrilleHoraire(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }

        Grille grille = null;
        try {
            grille = dbRequests.getGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }

        LinkedList<Key> cellules = null;
        try {
            cellules = dbRequests.getCellules(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GRILLE_NON, "Cellules de la grille irrécupérable");
        }

        grille.setGrilles(cellules);
        try {
            dbRequests.getVolontaireGrille(grille, idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            packetReponse = new PacketCom(States.GET_GRILLE_NON, "Volontaires de la grille inconnue");
        }

        dbRequests.getMysql().commit();
        return new PacketCom(States.GET_GRILLE_OUI, (Object)grille);
    }

    private PacketCom actionNewGrillesHoraires(String type, Object contenu) {
        Grille grille = (Grille)contenu;
        int idGrilleHoraire = -1;

        try {
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "volontaires", "caseHoraire", "AssignationCaseHoraire"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur de création de nouvelle grille");
        }

        try {
            idGrilleHoraire = dbRequests.insertGrilleHoraire(grille.getSemaine(), grille.getDateDebut(), grille.getDateFin(), grille.getNomAmbulance(), grille.getLieu(), grille.getAnnee());
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
            if(!nomColle.isEmpty()){
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
                idCaseHoraire = dbRequests.insertCellule(key.getValue().getJour(), key.getValue().getDate(), key.getValue().getHeure(), key.getValue().getRole(), key.getValue().getRow(), key.getValue().getColumn(), idGrilleHoraire);
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
            dbRequests.getMysql().lockTable(new String[]{"grillehoraire", "volontaires", "caseHoraire", "AssignationCaseHoraire"});
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
        }

        try {
            idGrilleHoraire = dbRequests.editGrilleHoraire(oldGrille.getSemaine(), oldGrille.getAnnee(), oldGrille.getNomAmbulance(), oldGrille.getLieu());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
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
                    return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                }
            }
            int idCaseHoraire = -1;
            String newHeure = null;
            for(Key keyOld : oldGrille.getGrilles()){
                if(keyOld.getX() == key.getX() && keyOld.getY() == key.getY()){
                    newHeure = key.getValue().getHeure();
                    try {
                        idCaseHoraire = dbRequests.EditCellule(keyOld.getValue().getJour(), keyOld.getValue().getDate(), keyOld.getValue().getHeure(), keyOld.getValue().getRole(), keyOld.getValue().getRow(), keyOld.getValue().getColumn(), idGrilleHoraire, key.getValue().getHeure());
                    } catch (Exception ex) {
                        Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                        dbRequests.getMysql().rollback();
                        return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                    }
                    if(keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() == null){

                    }else{
                        if((keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() != null)){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                            }
                        }else if(keyOld.getValue().getNomPrenom() != null && key.getValue().getNomPrenom() == null){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
                            }
                        }else if(!keyOld.getValue().getNomPrenom().equals(key.getValue().getNomPrenom())){
                            try {
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            } catch (Exception ex) {
                                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                                dbRequests.getMysql().rollback();
                                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur d'édition de grille");
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
        String ambulance = (String)data[2];
        String lieu = (String)data[3];

        boolean locked = true;
        try {
            locked = dbRequests.checkLockGrille(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            dbRequests.getMysql().rollback();
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
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
        String ambulance = grille.getNomAmbulance();
        String lieu = grille.getLieu();
        try {
            dbRequests.unlockGrille(semaine, année, ambulance, lieu);
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
}
