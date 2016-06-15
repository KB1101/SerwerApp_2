package serwer;


import controller.Kontroler;
import data.MagazynDanych;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Kornel on 2016-06-04.
 */
public class MultiThreadSerwer implements Runnable{
    private Socket socket;
    private MagazynDanych magazyn;
    private Kontroler kontroler;

    public MultiThreadSerwer(Socket socket, MagazynDanych magazyn, Kontroler kontroler){
        this.socket = socket;
        this.magazyn = magazyn;
        this.kontroler = kontroler;

    }

    public void run(){
        try{
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            DataInputStream dis = new DataInputStream(inputStream);
            DataOutputStream dos = new DataOutputStream(outputStream);
            SocketAddress sockaddr = this.socket.getRemoteSocketAddress();

            System.out.println("Nawiązano połaczenie z: " + sockaddr.toString() );
            process(dis,dos);
            System.out.println("Klient zakończył połaczenie: " + sockaddr.toString() );

            dos.close();
            dis.close();
            inputStream.close();
            outputStream.close();



        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void process(DataInputStream dis,DataOutputStream dos ){
        try {


        } catch (Exception ex){
            System.out.println(ex);
        }
    }

}



//int uid = magazyn.addUser(socket.getInetAddress().toString());
            /*String suid = Integer.toString(uid);

            dos.writeBytes("ID: "+suid+"\n\r");
            char[][] charPlansza //= this.plansza.getPlansza();
            for(int i=0;i<32;i++){
                String dane = new String(charPlansza[i]);
                dos.writeBytes(dane+"\n\r");
            }
            dos.writeBytes("WAIT"+"\n\r");

            / * Nie mozna zrobić Wait() i Notify() bo połaczenie na sockecie pada * /
            while (true){
                Thread.sleep(25);
                if(this.magazyn.checkUsers()) break;
            }
            dos.writeBytes("START"+"\n\r");
            this.plansza.usersPositionUpdate();

            / * zaczynamy gre * /
            int bytes;
            byte[] buffer = new byte[1024];
            while ((bytes = dis.read(buffer)) != -1) {
                / * Dane w formacie: "UID,{1,2,3,4,5};"
                 * 1 - w gore,
                 * 2 - w prawo,
                 * 3 - w dół
                 * 4 - w lewo
                 * 5 - strzał
                 * np: Uzytkownik o ID:=1 strzela -> 1,5;
                 * * /
                this.plansza.userAcction(buffer,bytes,uid);
                //System.out.write(buffer, 0, bytes);
                //dos.write(buffer, 0, bytes);

                for(int i=0;i<32;i++){
                    String dane = new String(charPlansza[i]);
                    dos.writeBytes(dane+"\n\r");
                }

                String stats = this.magazyn.getAllUsersStats();
                dos.writeBytes(stats+"\n\r");
                if(!this.plansza.getGameStatus())
                    break;
                }
            }


            magazyn.delUser(uid);*/