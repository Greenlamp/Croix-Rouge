/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasySftp;

import java.io.File;


public class test {
    public static void main(String [] args){
        EasySftp sftp = new EasySftp("192.168.2.3", "server", "server", "report");
        File file = new File("test.txt");
        //sftp.send(file);
        sftp.send("ceci est un test");
        System.out.println("ok");
    }
}
