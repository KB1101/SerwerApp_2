package data;

import serwer.MultiThreadSerwer;
import sun.plugin.javascript.navig.LinkArray;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Kornel on 2016-06-04.
 */
public class MagazynDanych {

    public ArrayList<MultiThreadSerwer> users;
    public int activeUsers;
    private ConcurrentLinkedQueue movesPackets;
    private SeperateQueues seperateQueues;


    public MagazynDanych(){
        this.movesPackets= new ConcurrentLinkedQueue<String>();
        this.seperateQueues=new SeperateQueues(this);
        this.users = new ArrayList();
        this.activeUsers = 0;
    }
    public Queue getMovesPackets() {
        return movesPackets;
    }
    public void pushToMovesQueue(String string){
        movesPackets.add(string);
    }
    public SeperateQueues getSeperateQueues() {
        return seperateQueues;
    }

    public void setSeperateQueues(SeperateQueues seperateQueues) {
        this.seperateQueues = seperateQueues;
    }
    /*
    public void setMovesPackets(SynchronousQueue movesPackets) {
        this.movesPackets = movesPackets;
    }*/
    //public void push

}
