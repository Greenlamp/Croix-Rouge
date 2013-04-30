/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import Containers.Adresse;
import Containers.Grille;
import Containers.Identite;
import Containers.Key;
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
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
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
        }else{
            return new PacketCom(States.ERROR, "ERROR");
        }
    }

    private PacketCom actionLogin(String type, Object contenu) {
        String[] infos = (String[]) contenu;
        String login = infos[0];
        String password = infos[1];

        try {
            dbRequests.checkLogin(login);
        } catch (Exception ex) {
            System.out.println("ex: " + ex.getMessage());
            return new PacketCom(States.LOGIN_NON_USER, null);
        }
        try {
            idUser = dbRequests.checLoginPassword(login, password);
        } catch (Exception ex) {
            return new PacketCom(States.LOGIN_NON_PASS, null);
        }
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

    private PacketCom actionGetVolontaires(String type, Object contenu, String portee) {
        PacketCom packetReponse = null;
        if(portee.equals("all")){
            if(!droitsOffi.contains("READ_DATA_ALL")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
            LinkedList<String[]> listeVolontaire = null;
            try {
                listeVolontaire = dbRequests.getVolontairesAll();
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(listeVolontaire != null){
                packetReponse = new PacketCom(States.GET_VOLONTAIRE_ALL_OUI, (Object)listeVolontaire);
            }else{
                //TODO: traiter si la liste est vide.
            }
        }else{
            if(!droitsOffi.contains("READ_DATA_MYSELF")){
                PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
                return packetRetour;
            }
            //TODO: si on ne veut pas tout.
        }
        return packetReponse;
    }

    private PacketCom actionGetDroits(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGE_RIGHTS")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        PacketCom packetReponse = null;
        LinkedList<String[]> listeDroits = null;
        try {
            listeDroits = dbRequests.getDroits();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeDroits != null){
            packetReponse = new PacketCom(States.GET_DROITS_ALL_OUI, (Object)listeDroits);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetGroupes(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGE_GROUP")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        PacketCom packetReponse = null;
        LinkedList<String[]> listeGroupes = null;
        try {
            listeGroupes = dbRequests.getGroupes();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeGroupes != null){
            packetReponse = new PacketCom(States.GET_GROUPES_ALL_OUI, (Object)listeGroupes);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetUtilisateurs(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGE_USER")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        PacketCom packetReponse = null;
        LinkedList<String[]> listeUtilisateurs = null;
        try {
            listeUtilisateurs = dbRequests.getUtilisateurs();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeUtilisateurs != null){
            packetReponse = new PacketCom(States.GET_UTILISATEURS_ALL_OUI, (Object)listeUtilisateurs);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetMyDroits(String type, Object contenu) {
        PacketCom packetReponse = null;
        LinkedList<String> droits = null;
        try {
            droits = dbRequests.getMyDroits(idUser);
            droitsOffi = droits;
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(droits != null){
            packetReponse = new PacketCom(States.GET_MY_DROITS_OUI, (Object)droits);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetDetailsUser(String type, Object contenu) {
        String login = (String)contenu;
        int id = -1;
        try {
            id = dbRequests.getIdUser(login);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.GET_DETAILS_USER_NON, "ERROR");
        }
        if(id == this.idUser){
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
            dataUser = dbRequests.getDataUser(id);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(dataUser != null){
            return new PacketCom(States.GET_DETAILS_USER_OUI, (Object)dataUser);
        }else{
            return new PacketCom(States.GET_DETAILS_USER_NON, "ERROR");
        }
    }

    private PacketCom insererVolontaire(Volontaire volontaire) {
        String matricule = volontaire.getIdentite().getNom() + "-" + volontaire.getIdentite().getPrenom();

        boolean check = false;
        try {
            check = dbRequests.checkMatricule(matricule);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!check){
            int idPersonneUrgence = -1;
            int idDecouverte = -1;
            int idLangueMaternelle = -1;
            int idRenseignement = -1;
            int idPaysLegal = -1;
            int idVilleLegal = -1;
            int idAdresseLegale = -1;
            int idPaysResidence = -1;
            int idVilleResidence = -1;
            int idAdresseResidence = -1;
            int idTelephone = -1;
            try {
                if(volontaire.getUrgence() != null) idPersonneUrgence = dbRequests.insertPersonneUrgence(volontaire.getUrgence().getNom(), volontaire.getUrgence().getPrenom(), volontaire.getUrgence().getTelephone());
                if(volontaire.getDecouverte() != null) idDecouverte = dbRequests.insertDecouvertes(volontaire.getDecouverte().getListeDescription());
                if(volontaire.getComplementaire() != null) idLangueMaternelle = dbRequests.insertLangueMaternelle(volontaire.getComplementaire().getLangueMaternelle());
                if(volontaire.getComplementaire() != null) idRenseignement = dbRequests.insertRenseignement(volontaire.getComplementaire().getActivitePro(), volontaire.getComplementaire().getActivite(), volontaire.getComplementaire().getQualification(), (volontaire.getComplementaire().isPermis() ? "Oui" : "Non"), volontaire.getComplementaire().getCategorie(), volontaire.getComplementaire().getDateObtention(), (volontaire.getComplementaire().isSelectionMedicale() ? "Oui" : "Non"), volontaire.getComplementaire().getDateValidité(), idLangueMaternelle);
                if(volontaire.getComplementaire() != null) dbRequests.insertLanguesConnue(idRenseignement, volontaire.getComplementaire().getListeLangue());
                if(volontaire.getFormations() != null) dbRequests.insertFormationsSuivie(matricule, volontaire.getFormations().getListeFormation());
                if(volontaire.getAdresse() != null) idPaysLegal = dbRequests.insertPaysLegal(volontaire.getAdresse().getPays());
                if(volontaire.getAdresse() != null) idVilleLegal = dbRequests.insertVilleLegal(volontaire.getAdresse().getVille());
                if(volontaire.getAdresse() != null) idAdresseLegale = dbRequests.insertAdresseLegale(volontaire.getAdresse().getRue(), volontaire.getAdresse().getNuméro(), volontaire.getAdresse().getCodePostal(), volontaire.getAdresse().getBoite(), idPaysLegal, idVilleLegal, matricule);
                if(volontaire.getResidence() != null) idPaysResidence = dbRequests.insertPaysResidence(volontaire.getResidence().getPays());
                if(volontaire.getResidence() != null) idVilleResidence = dbRequests.insertVilleResidence(volontaire.getResidence().getVille());
                if(volontaire.getResidence() != null) idAdresseResidence = dbRequests.insertAdresseResidence(volontaire.getResidence().getRue(), volontaire.getResidence().getNuméro(), volontaire.getResidence().getCodePostal(), volontaire.getResidence().getBoite(), volontaire.getResidence(), idPaysResidence, idVilleResidence, matricule);
                if(volontaire.getTelephone() != null) idTelephone = dbRequests.insertTelephone(volontaire.getTelephone().getGsm(), volontaire.getTelephone().getAutreGsm(), volontaire.getTelephone().getTelephoneFix(), volontaire.getTelephone().getTelephoneProfesionnelle(), volontaire.getTelephone().getFax());
                dbRequests.insertVolontaire(matricule, volontaire.getIdentite().getNom(), volontaire.getIdentite().getPrenom(), volontaire.getIdentite().getNomJeuneFille(), volontaire.getIdentite().getDateNaissance(), volontaire.getIdentite().getSexe(), (volontaire.getAdresse() == null ? null : volontaire.getAdresse().getEmail()), "", idPersonneUrgence, idDecouverte, -1, idRenseignement, idAdresseLegale, idAdresseResidence, idTelephone, -1);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                return new PacketCom(States.NOUVEAU_VOLONTAIRE_NON, "Une erreur s'est produite.");
            }
            return new PacketCom(States.NOUVEAU_VOLONTAIRE_OUI, null);
        }else{
            return new PacketCom(States.NOUVEAU_VOLONTAIRE_NON, "Volontaire déja existant");
        }
    }

    private PacketCom actionGetEquipes(String type, Object contenu) {
        if(!droitsOffi.contains("SEE_MANAGER_TEAM")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }
        PacketCom packetReponse = null;
        LinkedList<String[]> listeEquipes = null;
        try {
            listeEquipes = dbRequests.getEquipes();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeEquipes != null){
            packetReponse = new PacketCom(States.GET_EQUIPES_ALL_OUI, (Object)listeEquipes);
        }else{
            //TODO: traiter si la liste est vide.
        }
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
        int idEquipe;
        try {
            idEquipe = dbRequests.insertEquipe(equipe.getNom());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.NOUVELLE_EQUIPE_NON, (Object)"Nom d'équipe déja utilisé");
        }
        for(TupleRecherche tuple : equipe.getVolontaires()){
            String matricule;
            try {
                matricule = dbRequests.getMatricule(tuple.getNom(), tuple.getPrenom());
                dbRequests.insertMembreEquipe(idEquipe, matricule);
            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                return new PacketCom(States.NOUVELLE_EQUIPE_NON, (Object)ex.getMessage());
            }
        }
        return new PacketCom(States.NOUVELLE_EQUIPE_OUI, null);
    }

    private PacketCom actionGetFormation(String type, Object contenu) {
        /*if(!droitsOffi.contains("SEE_MANAGE_GROUP")){
            PacketCom packetRetour = new PacketCom(States.INSUFFICIENT_PRIVILEGES, "Vous ne possédez pas le droit d'obtenir ces informations");
            return packetRetour;
        }*/
        //TODO - Créer un droit pour récup les formations
        PacketCom packetReponse = null;
        LinkedList<String> listeFormations = null;
        try {
            listeFormations = dbRequests.getFormations();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeFormations != null){
            packetReponse = new PacketCom(States.GET_LISTE_FORMATIONS_OUI, (Object)listeFormations);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetGrillesHoraires(String type, Object contenu) {
        //TODO - Créer un droit pour récup les grilles horaires
        PacketCom packetReponse = null;
        LinkedList<String[]> listeGrillesHoraires = null;
        try {
            listeGrillesHoraires = dbRequests.getGrillesHoraires();
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(listeGrillesHoraires != null){
            packetReponse = new PacketCom(States.GET_GRILLES_HORAIRES_OUI, (Object)listeGrillesHoraires);
        }else{
            //TODO: traiter si la liste est vide.
        }
        return packetReponse;
    }

    private PacketCom actionGetGrille(String type, Object contenu) {
        Object[] data = (Object[]) contenu;
        int semaine = (int)data[0];
        int année = (int)data[1];
        String ambulance = (String)data[2];
        String lieu = (String)data[3];
        try {
            dbRequests.lockGrille(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }

        int idGrilleHoraire = -1;
        try {
            idGrilleHoraire = dbRequests.getIdGrilleHoraire(semaine, année, ambulance, lieu);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }


        Grille grille = null;
        try {
            grille = dbRequests.getGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.GET_GRILLE_NON, "Impossible de récupérer la grille");
        }


        LinkedList<Key> cellules = null;
        try {
            cellules = dbRequests.getCellules(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.GET_GRILLE_NON, "cellules inconnue");
        }

        grille.setGrilles(cellules);
        try {
            dbRequests.getVolontaireGrille(grille, idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.GET_GRILLE_NON, "Erreur lors de la récupération des volontaires pour la grille");
        }

        return new PacketCom(States.GET_GRILLE_OUI, (Object)grille);
    }

    private PacketCom actionNewGrillesHoraires(String type, Object contenu) {
        Grille grille = (Grille)contenu;
        int idGrilleHoraire;
        try {
            idGrilleHoraire = dbRequests.insertGrilleHoraire(grille.getSemaine(), grille.getDateDebut(), grille.getDateFin(), grille.getNomAmbulance(), grille.getLieu(), grille.getAnnee());
        } catch (Exception ex) {
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
                } catch (Exception ex) {
                    Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                    return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur au niveau de l'insertion de la recupération du matricule");
                }
                matriculed = true;
            }
            int idCaseHoraire;
            try {
                idCaseHoraire = dbRequests.insertCellule(key.getValue().getJour(), key.getValue().getDate(), key.getValue().getHeure(), key.getValue().getRole(), key.getValue().getRow(), key.getValue().getColumn(), idGrilleHoraire);
                if(matriculed){
                    dbRequests.insertAssignationCaseHoraire(idCaseHoraire, matricule);
                }
            } catch (Exception ex) {
                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur au niveau de l'insertion de la case horaire");
            }
        }
        return new PacketCom(States.NEW_GRILLE_HORAIRE_OUI, null);
    }

    private PacketCom actionEditGrillesHoraires(String type, Object contenu) {
        Object[] data = (Object[]) contenu;
        Grille oldGrille = (Grille)data[0];
        Grille newGrille = (Grille)data[1];
        int idGrilleHoraire;
        try {
            idGrilleHoraire = dbRequests.editGrilleHoraire(oldGrille.getSemaine(), oldGrille.getAnnee(), oldGrille.getNomAmbulance(), oldGrille.getLieu());
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "La grille horaire pour la semaine " + oldGrille.getSemaine() + " est introuvable");
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
                    return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur au niveau de l'insertion de la recupération du matricule");
                }
            }
            int idCaseHoraire = -1;
            try {
                String newHeure = null;
                for(Key keyOld : oldGrille.getGrilles()){
                    if(keyOld.getX() == key.getX() && keyOld.getY() == key.getY()){
                        newHeure = key.getValue().getHeure();
                        idCaseHoraire = dbRequests.EditCellule(keyOld.getValue().getJour(), keyOld.getValue().getDate(), keyOld.getValue().getHeure(), keyOld.getValue().getRole(), keyOld.getValue().getRow(), keyOld.getValue().getColumn(), idGrilleHoraire, key.getValue().getHeure());

                        if(keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() == null){

                        }else{
                            if((keyOld.getValue().getNomPrenom() == null && key.getValue().getNomPrenom() != null)){
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            }else if(keyOld.getValue().getNomPrenom() != null && key.getValue().getNomPrenom() == null){
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            }else if(!keyOld.getValue().getNomPrenom().equals(key.getValue().getNomPrenom())){
                                dbRequests.EditAssignationCaseHoraire(idCaseHoraire, matricule);
                            }
                        }
                        break;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
                return new PacketCom(States.NEW_GRILLE_HORAIRE_NON, "Erreur au niveau de l'insertion de la case horaire");
            }
        }
        try {
            dbRequests.unlockGrille(idGrilleHoraire);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            return new PacketCom(States.GET_GRILLE_NON, "Grille inconnue");
        }
        if(!locked){
            return new PacketCom(States.GRILLE_UNLOCKED, null);
        }else{
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
            return new PacketCom(States.UNLOCK_GRILLE_NON, null);
        }
        return new PacketCom(States.UNLOCK_GRILLE_OUI, null);
    }
}
