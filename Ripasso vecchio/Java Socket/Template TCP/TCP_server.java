import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;

public class TCP_server{
    public static void main(String []args){
        ServerSocket sSrv;
        Socket toClient;
        int dim_buffer = 100;
        int letti;

        try{
            //Parte 1: creazione connessione
            sSrv = new ServerSocket(0);
            System.out.println("Indirizzo: " + sSrv.getInetAddress() + " porta: " + sSrv.getLocalPort());
            toClient = sSrv.accept();
            System.out.println("Ind Client: " + toClient.getInetAddress() + " porta: " + toClient.getPort());

            //Parte 2: scambio dati
            byte buffer[] = new byte[dim_buffer];
            InputStream fromCl = toClient.getInputStream();
            letti = fromCl.read(buffer);
            if (letti > 0){
                String stampa = new String(buffer, 0, letti);
                System.out.println("Ricevuta stringa: " + stampa + " di " + letti + " byte");
            }
        //Parte 3: chiusura connessione
        toClient.close();
        sSrv.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
