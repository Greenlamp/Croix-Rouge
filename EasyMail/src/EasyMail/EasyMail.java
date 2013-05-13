/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyMail;

import java.security.Security;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EasyMail {
    private String to;
    MimeMessage message;
    private String login;
    private String password;

    EasyMail(String to, String login, String password) {
        setTo(to);
        setLogin(login);
        setPassword(password);
        init();
    }

    private void init() {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(getLogin(), getPassword());
                        }
                });

        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(getLogin()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo()));
        } catch (Exception ex) {
            Logger.getLogger(EasyMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    void sendMessage(String sujet, String message) {
        try {
            this.message.setSubject(sujet);
            this.message.setText(message);
            Transport.send(this.message);
        } catch (MessagingException ex) {
            Logger.getLogger(EasyMail.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
