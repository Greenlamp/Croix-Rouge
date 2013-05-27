/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.cr.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import my.cr.network.NetworkClient;


public class MyService extends Service{
    NetworkClient socket;
    String host = "192.168.0.2";
    int port = 8500;

    @Override
    public void onCreate(){
        super.onCreate();
        socket = new NetworkClient(host, port, true);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(socket.isConnected()){
            socket.disconnect();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
