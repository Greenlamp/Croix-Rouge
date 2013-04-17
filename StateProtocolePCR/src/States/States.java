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
}
