import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCP_client{
    public static void main(String []args){
        Socket sClient;
        InetAddress ia;
        InetSocketAddress MYisa;
        InetSocketAddress isa;
        String frase;

        sClient = new Socket();
        try{
            //Parte 1: creazione connessione 
            ia = InetAddress.getLocalHost();
            MYisa = new InetSocketAddress(ia, 0);
            sClient.bind(MYisa);
            System.out.println("Porta locale: " + sClient.getLocalPort());

            isa = new InetSocketAddress(ia, 63519);   //#PORTA = PORTA STAMPATA DAL SERVER DA INSERIRE !!!!!!!!
            sClient.connect(isa);
            System.out.println("Indirizzo server: " + sClient.getInetAddress() + "; porta: " + sClient.getPort());

            //Parte 2: scambio dati
            InputStreamReader tastiera = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(tastiera);
            frase = br.readLine();
            OutputStream toSrv = sClient.getOutputStream();
            toSrv.write(frase.getBytes(), 0, frase.length());

            //Thread.sleep(120*1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                sClient.close();
            }catch(Exception e){
                System.err.println("Client error");
                e.printStackTrace();
            }
        }
    }
}