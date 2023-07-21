import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class es2SrvIter{
    public static void main(String[] args){
        ServerSocket sSrv;
        Socket toClient;
        try{
            sSrv = new ServerSocket(0);
            System.out.println("Indirizzo: " + sSrv.getInetAddress() + "; porta: " + sSrv.getLocalPort());
            toClient = sSrv.accept();
            System.out.println("Ind client: " + toClient.getInetAddress() + "; porta: " + toClient.getPort());
            Thread.sleep(240 * 1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}