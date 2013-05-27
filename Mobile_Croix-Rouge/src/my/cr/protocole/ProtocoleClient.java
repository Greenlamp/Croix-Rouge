/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.protocole;

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

        return packet;
    }
}
