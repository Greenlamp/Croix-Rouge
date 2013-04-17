/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PacketCom;

/**
 *
 * @author Greenlamp
 */
public interface Protocolable {
    public PacketCom messageFromClient(Object objet);
    public PacketCom messageFromServer(Object objet);
}
