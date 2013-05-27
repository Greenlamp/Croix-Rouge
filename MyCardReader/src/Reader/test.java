/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Reader;
import be.belgium.eid.eidlib.BeID;
import be.belgium.eid.exceptions.EIDException;
import be.belgium.eid.objects.IDData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    public static void main(String args[]) {
        BeID beid = new BeID(true);
        try {
            IDData idData = beid.getIDData();
            System.out.println("sexe: " + idData.getSex());
            System.out.println("nom: " + idData.getName());
        } catch (EIDException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
