/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Containers;

import java.io.Serializable;
import java.util.LinkedList;


public class Utilisateur implements Serializable{
    private String login;
    private String password;
    private String dateDerniereConnexion;
    private int connected;
    private int prioritaire;
    private LinkedList<String> groupes;

    public Utilisateur(){
        setLogin(null);
        setPassword(null);
        setDateDerniereConnexion(null);
        setConnected(-1);
        setPrioritaire(-1);
        setGroupes(new LinkedList<String>());
    }

    public Utilisateur(Utilisateur utilisateur) {
        setLogin(utilisateur.getLogin());
        setPassword(utilisateur.getPassword());
        setDateDerniereConnexion(utilisateur.getDateDerniereConnexion());
        setConnected(utilisateur.getConnected());
        setPrioritaire(utilisateur.getPrioritaire());
        setGroupes(new LinkedList<String>());
        for(String groupe : utilisateur.getGroupes()){
            addGroupe(groupe);
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

    public String getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    public void setDateDerniereConnexion(String dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    public int getConnected() {
        return connected;
    }

    public void setConnected(int connected) {
        this.connected = connected;
    }

    public int getPrioritaire() {
        return prioritaire;
    }

    public void setPrioritaire(int prioritaire) {
        this.prioritaire = prioritaire;
    }

    public LinkedList<String> getGroupes() {
        return groupes;
    }

    public void setGroupes(LinkedList<String> groupes) {
        this.groupes = groupes;
    }

    public void addGroupe(String groupe){
        this.groupes.add(groupe);
    }
}
