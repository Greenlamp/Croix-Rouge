/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Protocole;

import PacketCom.PacketCom;
import PacketCom.Protocolable;
import States.States;


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
        }else{
            return new PacketCom(States.ERROR, "ERROR");
        }
    }
}
