import java.io.*;
import java.net.*;

public class client_febbraio22 {
    public static void main(String[]args) throws IOException{
        DatagramSocket sClient;
        int score = 0;
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
            String frase = "p";
            byte[] buffer = frase.getBytes();
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            dp.setSocketAddress(isa);
            sClient.send(dp);
            DatagramPacket dpin  = new DatagramPacket(buffer, 100);
            sClient.receive(dpin);
            String risposta = new String(buffer, 0, dpin.getLength());
            if(risposta == "k"){
                boolean continua = true;
                for(;;){
                    InputStreamReader tastiera = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(tastiera);
                    String mossa = br.readLine();
                    buffer = mossa.getBytes();
                    DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
                    dp2.setSocketAddress(isa);
                    sClient.send(dp2);
                    DatagramPacket dpin2  = new DatagramPacket(buffer, 100);
                    sClient.receive(dpin2);
                    String mossaSrv = new String(buffer, 0, dpin.getLength());
                    if(mossaSrv == "f"){
                       
                    }
                }
            }else{
                System.out.println("Connessione al server rifiutata");
            }
        }catch(SocketException se){
            se.printStackTrace();
        }
    }
}
