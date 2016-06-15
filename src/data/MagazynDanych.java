package data;

import sun.plugin.javascript.navig.LinkArray;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Kornel on 2016-06-04.
 */
public class MagazynDanych {

    private ArrayList<Socket> users;
    private int activeUsers;


    public MagazynDanych(){
        this.users = new ArrayList();
        this.activeUsers = 0;
    }

}
