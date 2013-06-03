/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

/**
 *
 * @author Greenlamp
 */
public interface States {

    //Serveur
    public static String ERROR = "ERROR";
    public static String DISCONNECT = "DISCONNECT";
    public static String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";

    public static String LOGIN = "LOGIN";

    public static String GET_MY_DROITS = "GET_MY_DROITS";

    public static String GET_VOLONTAIRE = "GET_VOLONTAIRE";

    public static String NOUVEAU_VOLONTAIRE = "NOUVEAU_VOLONTAIRE";
    public static String EDIT_VOLONTAIRE = "EDIT_VOLONTAIRE";
    public static String GET_VOLONTAIRE_ALL = "GET_VOLONTAIRE_ALL";
    public static String DELETE_VOLONTAIRE = "DELETE_VOLONTAIRE";

    public static String GET_DROITS_ALL = "GET_DROITS_ALL";
    public static String GET_GROUPES_ALL = "GET_GROUPES_ALL";
    public static String GET_UTILISATEURS_ALL = "GET_UTILISATEURS_ALL";

    public static String GET_DETAILS_USER = "GET_DETAILS_USER";

    public static String GET_EQUIPES_ALL = "GET_EQUIPES_ALL";

    public static String RECHERCHE = "RECHERCHE";

    public static String NOUVELLE_EQUIPE = "NOUVELLE_EQUIPE";

    public static String GET_LISTE_FORMATIONS = "GET_LISTE_FORMATIONS";

    public static String GET_GRILLES_HORAIRES = "GET_GRILLES_HORAIRES";

    public static String NEW_GRILLE_HORAIRE = "NEW_GRILLE_HORAIRE";
    public static String EDIT_GRILLE_HORAIRE = "EDIT_GRILLE_HORAIRE";
    public static String DELETE_GRILLE = "DELETE_GRILLE";
    public static String GET_GRILLE = "GET_GRILLE";
    public static String CHECK_LOCK_GRILLE = "CHECK_LOCK_GRILLE";

    public static String UNLOCK_GRILLE = "UNLOCK_GRILLE";

    public static String NEW_GROUPE = "NEW_GROUPE";
    public static String GET_GROUPE = "GET_GROUPE";
    public static String EDIT_GROUPE = "EDIT_GROUPE";
    public static String DELETE_GROUPE = "DELETE_GROUPE";

    public static String NEW_UTILISATEUR = "NEW_UTILISATEUR";
    public static String GET_UTILISATEUR = "GET_UTILISATEUR";
    public static String EDIT_UTILISATEUR = "EDIT_UTILISATEUR";
    public static String DELETE_UTILISATEUR = "DELETE_UTILISATEUR";

    public static String GET_VEHICULES_ALL = "GET_VEHICULES_ALL";
    public static String GET_VEHICULES_DISPO = "GET_VEHICULES_DISPO";
    public static String GET_VEHICULE = "GET_VEHICULE";
    public static String NEW_VEHICULE = "NEW_VEHICULE";
    public static String EDIT_VEHICULE = "EDIT_VEHICULE";
    public static String DELETE_VEHICULE = "DELETE_VEHICULE";

    public static String GET_LIEUX_ALL = "GET_LIEUX_ALL";
    public static String GET_LIEU = "GET_LIEU";
    public static String NEW_LIEU = "NEW_LIEU";
    public static String EDIT_LIEU = "EDIT_LIEU";
    public static String DELETE_LIEU = "DELETE_LIEU";

    public static String GET_RESERVATION_ALL = "GET_RESERVATION_ALL";
    public static String GET_RESERVATION = "GET_RESERVATION";
    public static String NEW_RESERVATION = "NEW_RESERVATION";
    public static String EDIT_RESERVATION = "EDIT_RESERVATION";
    public static String DELETE_RESERVATION = "DELETE_RESERVATION";

    public static String GET_BIRTHDAY = "GET_BIRTHDAY";
    public static String GET_EXP_BREVET = "GET_EXP_BREVET";
    public static String GET_HORAIRE_MISS = "GET_HORAIRE_MISS";

    public static String GET_DETAILS_ANDROID = "GET_DETAILS_ANDROID";








    //Client
    public static String LOGIN_OUI = "LOGIN_OUI";
    public static String LOGIN_NON = "LOGIN_NON";

    public static String GET_VOLONTAIRE_OUI = "GET_VOLONTAIRE_OUI";
    public static String GET_VOLONTAIRE_NON = "GET_VOLONTAIRE_NON";

    public static String GET_MY_DROITS_OUI = "GET_MY_DROITS_OUI";
    public static String GET_MY_DROITS_NON = "GET_MY_DROITS_NON";

    public static String LOGIN_NON_USER = "LOGIN_NON_USER";
    public static String LOGIN_NON_PASS = "LOGIN_NON_PASS";

    public static String NOUVEAU_VOLONTAIRE_OUI = "NOUVEAU_VOLONTAIRE_OUI";
    public static String NOUVEAU_VOLONTAIRE_NON = "NOUVEAU_VOLONTAIRE_NON";

    public static String EDIT_VOLONTAIRE_OUI = "EDIT_VOLONTAIRE_OUI";
    public static String EDIT_VOLONTAIRE_NON = "EDIT_VOLONTAIRE_NON";

    public static String GET_VOLONTAIRE_ALL_OUI = "GET_VOLONTAIRE_ALL_OUI";
    public static String GET_VOLONTAIRE_ALL_NON = "GET_VOLONTAIRE_ALL_NON";

    public static String GET_DROITS_ALL_OUI = "GET_DROITS_ALL_OUI";
    public static String GET_DROITS_ALL_NON = "GET_DROITS_ALL_NON";
    public static String GET_GROUPES_ALL_OUI = "GET_GROUPES_ALL_OUI";
    public static String GET_GROUPES_ALL_NON = "GET_GROUPES_ALL_NON";
    public static String GET_UTILISATEURS_ALL_OUI = "GET_UTILISATEURS_ALL_OUI";
    public static String GET_UTILISATEURS_ALL_NON = "GET_UTILISATEURS_ALL_NON";

    public static String GET_DETAILS_USER_OUI = "GET_DETAILS_USER_OUI";
    public static String GET_DETAILS_USER_NON = "GET_DETAILS_USER_NON";

    public static String GET_EQUIPES_ALL_OUI = "GET_EQUIPES_ALL_OUI";
    public static String GET_EQUIPES_ALL_NON = "GET_EQUIPES_ALL_NON";

    public static String RECHERCHE_OUI = "RECHERCHE_OUI";
    public static String RECHERCHE_NON = "RECHERCHE_NON";

    public static String NOUVELLE_EQUIPE_OUI = "NOUVELLE_EQUIPE_OUI";
    public static String NOUVELLE_EQUIPE_NON = "NOUVELLE_EQUIPE_NON";

    public static String GET_LISTE_FORMATIONS_OUI = "GET_LISTE_FORMATIONS_OUI";
    public static String GET_LISTE_FORMATIONS_NON = "GET_LISTE_FORMATIONS_NON";

    public static String GET_GRILLES_HORAIRES_OUI = "GET_GRILLES_HORAIRES_OUI";
    public static String GET_GRILLES_HORAIRES_NON = "GET_GRILLES_HORAIRES_NON";

    public static String NEW_GRILLE_HORAIRE_OUI = "NEW_GRILLE_HORAIRE_OUI";
    public static String NEW_GRILLE_HORAIRE_NON = "NEW_GRILLE_HORAIRE_NON";
    public static String EDIT_GRILLE_HORAIRE_OUI = "EDIT_GRILLE_HORAIRE_OUI";
    public static String EDIT_GRILLE_HORAIRE_NON = "EDIT_GRILLE_HORAIRE_NON";
    public static String DELETE_GRILLE_OUI = "DELETE_GRILLE_OUI";
    public static String DELETE_GRILLE_NON = "DELETE_GRILLE_NON";

    public static String GET_GRILLE_OUI = "GET_GRILLE_OUI";
    public static String GET_GRILLE_NON = "GET_GRILLE_NON";

    public static String GRILLE_LOCKED = "GRILLE_LOCKED";
    public static String GRILLE_UNLOCKED = "GRILLE_UNLOCKED";

    public static String UNLOCK_GRILLE_OUI = "UNLOCK_GRILLE_OUI";
    public static String UNLOCK_GRILLE_NON = "UNLOCK_GRILLE_NON";

    public static String GET_GROUPE_OUI = "GET_GROUPE_OUI";
    public static String GET_GROUPE_NON = "GET_GROUPE_NON";

    public static String NEW_GROUPE_OUI = "NEW_GROUPE_OUI";
    public static String NEW_GROUPE_NON = "NEW_GROUPE_NON";

    public static String EDIT_GROUPE_OUI = "EDIT_GROUPE_OUI";
    public static String EDIT_GROUPE_NON = "EDIT_GROUPE_NON";

    public static String DELETE_GROUPE_OUI = "DELETE_GROUPE_OUI";
    public static String DELETE_GROUPE_NON = "DELETE_GROUPE_NON";

    public static String GET_UTILISATEUR_OUI = "GET_UTILISATEUR_OUI";
    public static String GET_UTILISATEUR_NON = "GET_UTILISATEUR_NON";

    public static String DELETE_UTILISATEUR_OUI = "DELETE_UTILISATEUR_OUI";
    public static String DELETE_UTILISATEUR_NON = "DELETE_UTILISATEUR_NON";

    public static String EDIT_UTILISATEUR_OUI = "EDIT_UTILISATEUR_OUI";
    public static String EDIT_UTILISATEUR_NON = "EDIT_UTILISATEUR_NON";

    public static String NEW_UTILISATEUR_OUI = "NEW_UTILISATEUR_OUI";
    public static String NEW_UTILISATEUR_NON = "NEW_UTILISATEUR_NON";

    public static String DELETE_VOLONTAIRE_OUI = "DELETE_VOLONTAIRE_OUI";
    public static String DELETE_VOLONTAIRE_NON = "DELETE_VOLONTAIRE_NON";

    public static String GET_VEHICULES_ALL_OUI = "GET_VEHICULES_ALL_OUI";
    public static String GET_VEHICULES_ALL_NON = "GET_VEHICULES_ALL_NON";

    public static String NEW_VEHICULE_OUI = "NEW_VEHICULE_OUI";
    public static String NEW_VEHICULE_NON = "NEW_VEHICULE_NON";

    public static String EDIT_VEHICULE_OUI = "EDIT_VEHICULE_OUI";
    public static String EDIT_VEHICULE_NON = "EDIT_VEHICULE_NON";

    public static String DELETE_VEHICULE_OUI = "DELETE_VEHICULE_OUI";
    public static String DELETE_VEHICULE_NON = "DELETE_VEHICULE_NON";

    public static String GET_VEHICULE_OUI = "GET_VEHICULE_OUI";
    public static String GET_VEHICULE_NON = "GET_VEHICULE_NON";


    public static String GET_LIEUX_ALL_OUI = "GET_LIEUX_ALL_OUI";
    public static String GET_LIEUX_ALL_NON = "GET_LIEUX_ALL_NON";

    public static String GET_LIEU_OUI = "GET_LIEU_OUI";
    public static String GET_LIEU_NON = "GET_LIEU_NON";

    public static String NEW_LIEU_OUI = "NEW_LIEU_OUI";
    public static String NEW_LIEU_NON = "NEW_LIEU_NON";

    public static String EDIT_LIEU_OUI = "EDIT_LIEU_OUI";
    public static String EDIT_LIEU_NON = "EDIT_LIEU_NON";

    public static String DELETE_LIEU_OUI = "DELETE_LIEU_OUI";
    public static String DELETE_LIEU_NON = "DELETE_LIEU_NON";


    public static String GET_RESERVATION_ALL_OUI = "GET_RESERVATION_ALL_OUI";
    public static String GET_RESERVATION_ALL_NON = "GET_RESERVATION_ALL_NON";

    public static String GET_RESERVATION_OUI = "GET_RESERVATION_OUI";
    public static String GET_RESERVATION_NON = "GET_RESERVATION_NON";

    public static String NEW_RESERVATION_OUI = "NEW_RESERVATION_OUI";
    public static String NEW_RESERVATION_NON = "NEW_RESERVATION_NON";

    public static String EDIT_RESERVATION_OUI = "EDIT_RESERVATION_OUI";
    public static String EDIT_RESERVATION_NON = "EDIT_RESERVATION_NON";

    public static String DELETE_RESERVATION_OUI = "DELETE_RESERVATION_OUI";
    public static String DELETE_RESERVATION_NON = "DELETE_RESERVATION_NON";

    public static String GET_VEHICULES_DISPO_OUI = "GET_VEHICULES_DISPO_OUI";
    public static String GET_VEHICULES_DISPO_NON = "GET_VEHICULES_DISPO_NON";

    public static String GET_BIRTHDAY_OUI = "GET_BIRTHDAY_OUI";
    public static String GET_BIRTHDAY_NON = "GET_BIRTHDAY_NON";

    public static String GET_EXP_BREVET_OUI = "GET_EXP_BREVET_OUI";
    public static String GET_EXP_BREVET_NON = "GET_EXP_BREVET_NON";

    public static String GET_HORAIRE_MISS_OUI = "GET_HORAIRE_MISS_OUI";
    public static String GET_HORAIRE_MISS_NON = "GET_HORAIRE_MISS_NON";

    public static String GET_DETAILS_ANDROID_OUI = "GET_DETAILS_ANDROID_OUI";
    public static String GET_DETAILS_ANDROID_NON = "GET_DETAILS_ANDROID_NON";
}
