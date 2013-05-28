/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import States.States;
import my.cr.PacketCom.PacketCom;
import my.cr.PacketCom.Protocolable;


public class ProtocoleClient implements Protocolable{

    public PacketCom messageFromClient(Object objet){
        return null;
    }

    public PacketCom messageFromServer(Object objet) {
        PacketCom packet = (PacketCom) objet;
        PacketCom message = traiterPacketClientSide(packet);
        return message;
    }

    private PacketCom traiterPacketClientSide(PacketCom packet) {
        String type = packet.getType();
        System.out.println("type reçu: " + type);
        Object contenu = packet.getObjet();

        if (type.equals(States.LOGIN_OUI)) {
            return packet;
        }else if (type.equals(States.LOGIN_NON)) {
            return packet;
        }else if (type.equals(States.LOGIN_NON_USER)) {
            return packet;
        }else if (type.equals(States.LOGIN_NON_PASS)) {
            return packet;
        }else if (type.equals(States.GET_VOLONTAIRE_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_DROITS_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_GROUPES_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_UTILISATEURS_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_MY_DROITS_OUI)) {
            return packet;
        }else if (type.equals(States.GET_DETAILS_USER_OUI)) {
            return packet;
        }else if (type.equals(States.NOUVEAU_VOLONTAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.NOUVEAU_VOLONTAIRE_NON)) {
            return packet;
        }else if (type.equals(States.GET_EQUIPES_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_EQUIPES_ALL_NON)) {
            return packet;
        }else if (type.equals(States.RECHERCHE_OUI)) {
            return packet;
        }else if (type.equals(States.RECHERCHE_NON)) {
            return packet;
        }else if (type.equals(States.NOUVELLE_EQUIPE_OUI)) {
            return packet;
        }else if (type.equals(States.NOUVELLE_EQUIPE_NON)) {
            return packet;
        }else if (type.equals(States.GET_LISTE_FORMATIONS_OUI)) {
            return packet;
        }else if (type.equals(States.GET_LISTE_FORMATIONS_NON)) {
            return packet;
        }else if (type.equals(States.GET_GRILLES_HORAIRES_OUI)) {
            return packet;
        }else if (type.equals(States.GET_GRILLES_HORAIRES_NON)) {
            return packet;
        }else if (type.equals(States.NEW_GRILLE_HORAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.NEW_GRILLE_HORAIRE_NON)) {
            return packet;
        }else if (type.equals(States.GET_GRILLE_OUI)) {
            return packet;
        }else if (type.equals(States.GET_GRILLE_NON)) {
            return packet;
        }else if (type.equals(States.NEW_GRILLE_HORAIRE_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_GRILLE_HORAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.EDIT_GRILLE_HORAIRE_NON)) {
            return packet;
        }else if (type.equals(States.GRILLE_LOCKED)) {
            return packet;
        }else if (type.equals(States.GRILLE_UNLOCKED)) {
            return packet;
        }else if (type.equals(States.UNLOCK_GRILLE_OUI)) {
            return packet;
        }else if (type.equals(States.UNLOCK_GRILLE_NON)) {
            return packet;
        }else if (type.equals(States.GET_VOLONTAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.GET_VOLONTAIRE_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_VOLONTAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.EDIT_VOLONTAIRE_NON)) {
            return packet;
        }else if (type.equals(States.GET_GROUPE_OUI)) {
            return packet;
        }else if (type.equals(States.GET_GROUPE_NON)) {
            return packet;
        }else if (type.equals(States.NEW_GROUPE_OUI)) {
            return packet;
        }else if (type.equals(States.NEW_GROUPE_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_GROUPE_OUI)) {
            return packet;
        }else if (type.equals(States.EDIT_GROUPE_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_GROUPE_OUI)) {
            return packet;
        }else if (type.equals(States.DELETE_GROUPE_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_GROUPE_NON)) {
            return packet;
        }else if (type.equals(States.GET_UTILISATEUR_OUI)) {
            return packet;
        }else if (type.equals(States.GET_UTILISATEUR_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_UTILISATEUR_OUI)) {
            return packet;
        }else if (type.equals(States.DELETE_UTILISATEUR_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_UTILISATEUR_OUI)) {
            return packet;
        }else if (type.equals(States.EDIT_UTILISATEUR_NON)) {
            return packet;
        }else if (type.equals(States.NEW_UTILISATEUR_OUI)) {
            return packet;
        }else if (type.equals(States.NEW_UTILISATEUR_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_VOLONTAIRE_OUI)) {
            return packet;
        }else if (type.equals(States.DELETE_VOLONTAIRE_NON)) {
            return packet;
        }else if (type.equals(States.GET_VEHICULES_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_VEHICULES_ALL_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_VEHICULE_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_VEHICULE_OUI)) {
            return packet;
        }else if (type.equals(States.NEW_VEHICULE_NON)) {
            return packet;
        }else if (type.equals(States.NEW_VEHICULE_OUI)) {
            return packet;
        }else if (type.equals(States.DELETE_VEHICULE_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_VEHICULE_OUI)) {
            return packet;
        }else if (type.equals(States.GET_VEHICULE_OUI)) {
            return packet;
        }else if (type.equals(States.GET_VEHICULE_NON)) {
            return packet;
        }else if (type.equals(States.GET_LIEUX_ALL_OUI)) {
            return packet;
        }else if (type.equals(States.GET_LIEUX_ALL_NON)) {
            return packet;
        }else if (type.equals(States.GET_LIEU_OUI)) {
            return packet;
        }else if (type.equals(States.GET_LIEU_NON)) {
            return packet;
        }else if (type.equals(States.NEW_LIEU_OUI)) {
            return packet;
        }else if (type.equals(States.NEW_LIEU_NON)) {
            return packet;
        }else if (type.equals(States.EDIT_LIEU_OUI)) {
            return packet;
        }else if (type.equals(States.EDIT_LIEU_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_LIEU_OUI)) {
            return packet;
        }else if (type.equals(States.DELETE_LIEU_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_GRILLE_NON)) {
            return packet;
        }else if (type.equals(States.DELETE_GRILLE_OUI)) {
            return packet;
        }else{
            return new PacketCom(States.ERROR, "ERROR");
        }
    }
}
