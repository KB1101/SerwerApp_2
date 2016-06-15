package data;

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
            String stringFromMainQueue= (String)magazyn.getMovesPackets().poll();
            queueList.forEach(queue -> queue.add(stringFromMainQueue));
        }
        });
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
