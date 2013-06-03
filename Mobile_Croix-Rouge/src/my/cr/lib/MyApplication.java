/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.lib;

import android.app.Application;
import java.util.LinkedList;
import my.cr.SSL.NetworkClientSSL;


public class MyApplication extends Application{
    private NetworkClientSSL socket;
    private LinkedList<String> droits = null;
    private boolean error = false;
    private String message = null;

    public NetworkClientSSL getSocket() {
        return socket;
    }

    public void setSocket(NetworkClientSSL socket) {
        this.socket = socket;
    }

    public LinkedList<String> getDroits() {
        return droits;
    }

    public void setDroits(LinkedList<String> droits) {
        this.droits = droits;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
