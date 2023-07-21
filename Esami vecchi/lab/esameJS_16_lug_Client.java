import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket client;

        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(tastiera);

        try {
            client = new Socket();

            System.out.print("Indirizzo: ");
            String indirizzo = br.readLine();

            System.out.print("Porta: ");
            int porta = Integer.parseInt(br.readLine());

            client.connect(new InetSocketAddress(indirizzo, porta));

            InputStream fromServer = client.getInputStream();
            OutputStream toServer = client.getOutputStream();

            String messToSend = ".";
            String messReceived = "";
            do {
                do {
                    System.out.print("Inserisci: ");
                    messToSend = br.readLine();
                    messToSend += "\0";

                    toServer.write(messToSend.getBytes(), 0, messToSend.length());

                    System.out.println("Mess: " + messToSend);
                    byte[] buff = new byte[100];
                    int letti = fromServer.read(buff);
                    if(letti > 0) {
                        messReceived = new String(buff, 0, letti);
                        System.out.println(messReceived);
                    }

                } while(!messReceived.startsWith("OK"));


            } while(!messToSend.equals("."));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
