import java.io.*;
import java.net.*;

public class clientUDP {
    public static void main(String[]args) throws IOException{
        DatagramSocket sClient;
        try{
            String nome_host = "localhost";
            int porta = 7000;
            if(args.length != 2){
                throw new IllegalArgumentException("num. parametri non corretto");
            }
            nome_host = args[0];
            porta = Integer.parseInt(args[1]);
            if(porta <= 0){
                throw new IllegalArgumentException("porta non valida");
            }
            sClient = new DatagramSocket();
            InetSocketAddress isa = new InetSocketAddress(nome_host, porta);
            InputStreamReader tastiera = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(tastiera);
            String frase = br.readLine();
            byte[] buffer = frase.getBytes();
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            dp.setSocketAddress(isa);
            sClient.send(dp);
        }catch(SocketException se){
            se.printStackTrace();
        }
    }
}