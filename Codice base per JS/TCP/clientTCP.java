import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class clientTCP{
    public static void main(String[]args){
        Socket sClient;
        InetAddress ia;
        InetSocketAddress isa;
        sClient = new Socket();
        try{
            ia = InetAddress.getLocalHost();
            isa = new InetSocketAddress(ia, 52417);     //inserire la porta del server
            sClient.connect(isa);
            System.out.println("Porta locale: " + sClient.getLocalPort());
            System.out.println("Indirizzo: " + sClient.getInetAddress() + "; porta: " + sClient.getPort());
            //Thread.sleep(120*1000);
            InputStreamReader tastiera = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(tastiera);
            String frase = br.readLine();
            OutputStream toSrv = sClient.getOutputStream();
            toSrv.write(frase.getBytes(), 0, frase.length());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}