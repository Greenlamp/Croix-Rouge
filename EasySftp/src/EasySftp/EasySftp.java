/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasySftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EasySftp {
    private String host;
    private String login;
    private String password;
    private String folder;
    Session session;
    ChannelSftp c;

    public EasySftp(String host, String login, String password, String folder) {
        setHost(host);
        setLogin(login);
        setPassword(password);
        setFolder(folder);
        init();
    }

    private void init() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(getLogin(), getHost(), 22);
            session.setPassword(getPassword().getBytes(Charset.forName("ISO-8859-1")));
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
        } catch (JSchException ex) {
            Logger.getLogger(EasySftp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Channel channel = session.openChannel("sftp");
            channel.connect();
            c = (ChannelSftp) channel;
        } catch (JSchException ex) {
            Logger.getLogger(EasySftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String message){
        Date date = new Date();
        String today = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(date);
        String nameFile = "Bug-" + today + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nameFile)));
            writer.write(message);
            writer.close();
            send(new File(nameFile));
            new File(nameFile).delete();
        } catch (IOException ex) {
            Logger.getLogger(EasySftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void send(File file) {
        try {
            c.put(file.getPath(), folder + "/" + file.getName());
            c.disconnect();
        } catch (SftpException ex) {
            Logger.getLogger(EasySftp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

}
