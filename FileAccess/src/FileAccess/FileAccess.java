/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileAccess {
    public static String getConfig(String nameFile, String key){
        String retour = null;
        String sep = System.getProperty("file.separator");
        String path = System.getProperty("user.dir") + sep + nameFile + ".properties";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileAccess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(prop.containsKey(key)){
            retour = prop.getProperty(key);
        }
        return retour;
    }

    public static File getFile(String nameFile){
        File retour = null;
        String sep = System.getProperty("file.separator");
        String path = System.getProperty("user.dir") + sep + nameFile;
        retour = new File(path);
        return retour;
    }
}
