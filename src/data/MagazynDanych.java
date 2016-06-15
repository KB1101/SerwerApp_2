package data;

import serwer.MultiThreadSerwer;
import sun.plugin.javascript.navig.LinkArray;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Kornel on 2016-06-04.
 */
public class MagazynDanych {

    private ArrayList<MultiThreadSerwer> multiSocket;
    private int activeUsers;


    public MagazynDanych(){
        this.multiSocket = new ArrayList();
        this.activeUsers = 0;
    }

    public void addSocket(MultiThreadSerwer socket){
        this.multiSocket.add(socket);
    }

    public int addUser(){
       this.activeUsers++;
        return (this.activeUsers - 1 );
    }
    public void delUser(){
        this.activeUsers--;
    }

}
