import java.io.IOException;
import java.net.*;

public class serverUDP{
    public static void main(String[] args) throws IOException{
        DatagramSocket sSrv;
        try{
            sSrv = new DatagramSocket();
            System.out.println("Indirizzo: " + sSrv.getLocalAddress() + "; porta: " + sSrv.getLocalPort());
            int dim_buffer = 100;
            byte[] buffer = new byte[dim_buffer];
            DatagramPacket dpin  = new DatagramPacket(buffer, dim_buffer);
            sSrv.receive(dpin);
            String stringa = new String(buffer, 0, dpin.getLength());
            System.out.println("ricevuto: " + stringa);
            InetAddress ia = dpin.getAddress();
            int porta = dpin.getPort();
            System.out.println("Indirizzo: " + ia.getHostAddress() + "; porta: " + porta);
            sSrv.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}