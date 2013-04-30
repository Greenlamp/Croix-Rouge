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
    public static String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";

    public static String LOGIN = "LOGIN";

    public static String GET_MY_DROITS = "GET_MY_DROITS";

    public static String NOUVEAU_VOLONTAIRE = "NOUVEAU_VOLONTAIRE";
    public static String GET_VOLONTAIRE_ALL = "GET_VOLONTAIRE_ALL";

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

    public static String GET_GRILLE = "GET_GRILLE";
    public static String CHECK_LOCK_GRILLE = "CHECK_LOCK_GRILLE";

    public static String UNLOCK_GRILLE = "UNLOCK_GRILLE";

    //Client
    public static String LOGIN_OUI = "LOGIN_OUI";
    public static String LOGIN_NON = "LOGIN_NON";

    public static String GET_MY_DROITS_OUI = "GET_MY_DROITS_OUI";

    public static String LOGIN_NON_USER = "LOGIN_NON_USER";
    public static String LOGIN_NON_PASS = "LOGIN_NON_PASS";

    public static String NOUVEAU_VOLONTAIRE_OUI = "NOUVEAU_VOLONTAIRE_OUI";
    public static String NOUVEAU_VOLONTAIRE_NON = "NOUVEAU_VOLONTAIRE_NON";

    public static String GET_VOLONTAIRE_ALL_OUI = "GET_VOLONTAIRE_ALL_OUI";

    public static String GET_DROITS_ALL_OUI = "GET_DROITS_ALL_OUI";
    public static String GET_GROUPES_ALL_OUI = "GET_GROUPES_ALL_OUI";
    public static String GET_UTILISATEURS_ALL_OUI = "GET_UTILISATEURS_ALL_OUI";

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

    public static String GET_GRILLE_OUI = "GET_GRILLE_OUI";
    public static String GET_GRILLE_NON = "GET_GRILLE_NON";

    public static String GRILLE_LOCKED = "GRILLE_LOCKED";
    public static String GRILLE_UNLOCKED = "GRILLE_UNLOCKED";

    public static String UNLOCK_GRILLE_OUI = "UNLOCK_GRILLE_OUI";
    public static String UNLOCK_GRILLE_NON = "UNLOCK_GRILLE_NON";
}
