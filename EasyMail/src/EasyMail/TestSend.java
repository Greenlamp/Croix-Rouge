/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyMail;


public class TestSend {
    public static void main(String [] args){
        String sujet = "Ceci est un test";
        String message = "Ceci est un message test, à ne pas prendre en compte comme un réel message.";
        String to = "gabriel.knuts@gmail.com";
        EasyMail mail = new EasyMail(to, "GkCroixRouge@gmail.com", "Croix-Rouge");
        mail.sendMessage(sujet, message);
    }
}
