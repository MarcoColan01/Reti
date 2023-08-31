import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTCP {
    public static void main(String[]args){
        ServerSocket sSrv;
        Socket toClient;
        try{
            sSrv = new ServerSocket(0);
            System.out.println("Indirizzo: " + sSrv.getInetAddress() + "; porta: " + sSrv.getLocalPort());
            toClient = sSrv.accept();
            System.out.println("Indirizzo client: " + toClient.getInetAddress() + "; porta: " + toClient.getPort());
           // Thread.sleep(240*1000);
            int dim_buffer = 100;
            byte buffer[] = new byte[dim_buffer];
            InputStream fromCl = toClient.getInputStream();
            int letti = fromCl.read(buffer);
            String stampa = new String(buffer, 0, letti);
            System.out.println("Ricevuta stringa: " + stampa + " di " + letti + " byte");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
