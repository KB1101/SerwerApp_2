package serwer;


import controller.Kontroler;
import data.MagazynDanych;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Kornel on 2016-06-04.
 */
public class MultiThreadSerwer implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private MagazynDanych magazyn;
    private Kontroler kontroler;
    private ConcurrentLinkedQueue queue;

    public MultiThreadSerwer(Socket socket, MagazynDanych magazyn, Kontroler kontroler) {
        this.socket = socket;
        this.magazyn = magazyn;
        this.queue= magazyn.getSeperateQueues().addQueue();
        this.kontroler = kontroler;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        SocketAddress sockaddr = null;
        try {
            sockaddr = this.socket.getRemoteSocketAddress();
            System.out.println("Nawiązano połaczenie z: " + sockaddr.toString());
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while (true) {
                try {
                    String packet = bufferedReader.readLine();
                    this.magazyn.pushToMovesQueue(packet);
                    packet = (String)queue.poll();
                    if (packet != null) {
                        bufferedWriter.write(packet);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Klient zakończył połaczenie: " + sockaddr.toString());
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (Exception e) {

            }

        }
    }
}