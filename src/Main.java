import controller.Kontroler;
import data.MagazynDanych;
import serwer.MultiThreadSerwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kornel on 2016-06-04.
 */
public class Main {
    public static final int PORT = 8088;
    private static final MagazynDanych magazyn = new MagazynDanych();
    private static final Kontroler kontroler = new Kontroler(magazyn);

    public static void main(String[] args) {
        try {
            new Thread(kontroler).start();

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println(serverSocket.getInetAddress());

            while (true) {
                Socket socket = serverSocket.accept();

                MultiThreadSerwer mts = new MultiThreadSerwer(socket,magazyn,kontroler);
                new Thread(mts).start();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
