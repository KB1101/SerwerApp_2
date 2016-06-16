package data;

import serwer.MultiThreadSerwer;

import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Dawid on 2016-06-16.
 */
public class SeperateQueues {
    private LinkedList<ConcurrentLinkedQueue<String>> queueList;
    private MagazynDanych magazyn;

    public SeperateQueues( MagazynDanych magazynDanych) {
        this.queueList = new LinkedList<>();
        this.magazyn=magazynDanych;
        new Thread(()->{while(true){
            if(magazyn.getMovesPackets().size() > 0) {
                String stringFromMainQueue = (String) magazyn.getMovesPackets().poll();
                System.out.println("z separate ---->" + stringFromMainQueue);
                queueList.forEach(queue -> queue.add(stringFromMainQueue));
                for (MultiThreadSerwer mts: this.magazyn.users) {
                    mts.send();
                }
            }
        }
        }).start();
    }

    public ConcurrentLinkedQueue addQueue(){
        ConcurrentLinkedQueue<String> queue= new ConcurrentLinkedQueue<>();
        queueList.add(queue);
        return queue;
    }

    public LinkedList<ConcurrentLinkedQueue<String>> getQueueList() {
        return queueList;
    }

    public void setQueueList(LinkedList<ConcurrentLinkedQueue<String>> queueList) {
        this.queueList = queueList;
    }

}
