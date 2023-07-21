import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class esempio2{
    public static void main(String[] args){
        Socket sClient;
        InetAddress ia;
        InetSocketAddress isa;

        sClient = new Socket();
        try{
            ia = InetAddress.getLocalHost();
            isa = new InetSocketAddress(ia, 0);
            sClient.bind(isa);
            System.out.println("Porta allocata: "+ sClient.getLocalPort());
            Thread.sleep(120 * 1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}