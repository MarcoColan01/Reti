import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

class Elenco{
    int treno;
    int ritardo;
}

public class febbraio17_server{
    public static void main (String []args){
        DatagramSocket toClient;
        DatagramSocket toStation;
        //Elenco[] situazione = new Elenco(10);


        try{
           // toClient = new DatagramSocket();
            toStation = new DatagramSocket();
           // System.out.println("Indirizzo per il client: " + toClient.getLocalAddress() + "; porta: " + toClient.getLocalPort());

            System.out.println("Indirizzo per la stazione: " + toStation.getLocalAddress() + "; porta: " + toStation.getLocalPort());

            int dim_buffer = 100;
           // byte[] bufferclient = new byte[dim_buffer];
            byte[] bufferstation = new byte[dim_buffer];
            //DatagramPacket dpinclient = new DatagramPacket(bufferclient, dim_buffer);
            DatagramPacket dpinstation = new DatagramPacket(bufferstation, dim_buffer);

           // toClient.receive(dpinclient);
            toStation.receive(dpinstation);

            String stringa = new String(bufferstation, 0, dpinstation.getLength());
            System.out.println("Ricevuto: " + stringa + "dalla stazione");
          
            
            //toClient.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}