import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class esempio1{
    public static void main(String[] args){
        Socket sClient;
        InetAddress ia;
        InetSocketAddress isa;

        sClient = new Socket();
        try{
            ia = InetAddress.getLocalHost();
            isa = new InetSocketAddress(ia, 50000);
        }
        catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }
    }
}