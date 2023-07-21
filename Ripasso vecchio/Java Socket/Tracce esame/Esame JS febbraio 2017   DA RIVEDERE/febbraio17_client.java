import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class febbraio17_client {
    public static void main(String[] args) {
        DatagramSocket client;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            client = new DatagramSocket();
            // connetti al server
            while(true) {
                try {
                    // server
                    System.out.println("Comunica con un Server: ");
                    System.out.print(">>> Host Name: ");
                    InetAddress indirizzo = InetAddress.getByName(br.readLine());
                    System.out.print(">>> Port: ");
                    int porta = Integer.parseInt(br.readLine());
                    
                    // scambio dati
                    for(;;){
                        System.out.print(">>> Treno: ");
                        String messToSend = br.readLine();
                        if (messToSend == "."){
                            break;
                        }
                        byte[] buf = messToSend.getBytes();
                        DatagramPacket dpOut = new DatagramPacket(buf, buf.length, indirizzo, porta);
                        client.send(dpOut);
    
                        // ricevi risposta
                        buf = new byte[100];
                        DatagramPacket dpIn = new DatagramPacket(buf, buf.length);
                        client.receive(dpIn);
                        String messReceived = new String(
                            dpIn.getData(), 0, dpIn.getLength());
                        System.out.println("Risposta da Server:\n"+messReceived);
                    }
                   
                    
                } catch(Exception e) {
                    e.printStackTrace();
                    System.out.println("Errore nella connessione, riprova");
                }
            }

        } catch (Exception e) { e.printStackTrace(); }
    }
}
