/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import DB.DbRequests;
import PacketCom.PacketCom;
import PacketCom.Protocolable;
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
        String[] data = (String[]) contenu;
        String nom = data[0];
        String prenom = data[1];
        String nomEpoux = data[2];
        String dateNaissance = data[3];
        String sexe = data[4];
        String email = data[5];
        String rue = data[6];
        String numero = data[7];
        String ville = data[8];
        String codePostal = data[9];
        String boite = data[10];
        String nationalite = data[11];

        try {
            dbRequests.insertVolontaire(nom, prenom, nomEpoux, dateNaissance, sexe, email, rue, numero, ville, codePostal, boite, nationalite);
        } catch (Exception ex) {
            Logger.getLogger(ProtocoleServeur.class.getName()).log(Level.SEVERE, null, ex);
            return new PacketCom(States.NOUVEAU_VOLONTAIRE_NON, "ERROR");
        }
        PacketCom packetRetour = new PacketCom(States.NOUVEAU_VOLONTAIRE_OUI, null);
        return packetRetour;
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
}
