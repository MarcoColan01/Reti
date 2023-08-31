import java.io.IOException;
import java.net.*;

public class server_febbraio22 {
    public static void main(String[] args) throws IOException{
        DatagramSocket sSrv;
        try{
            boolean chiudi = false;
            sSrv = new DatagramSocket();
            int cs = 0, cw = 0;
            System.out.println("Indirizzo: " + sSrv.getLocalAddress() + "; porta: " + sSrv.getLocalPort());
            int dim_buffer = 100;
            byte[] buffer = new byte[dim_buffer];
            DatagramPacket dpin  = new DatagramPacket(buffer, dim_buffer);
            sSrv.receive(dpin);
            cs++;
            String stringa = new String(buffer, 0, dpin.getLength());
            for(;;){
                if(stringa == "p"){
                System.out.println("Il client con indirizzo" + dpin.getAddress() +"ha avviato una nuova partita");
                cs++;
                String frase = "k";
                DatagramPacket dp = new DatagramPacket(frase.getBytes(), frase.length());
                InetSocketAddress isa = new InetSocketAddress(sSrv.getLocalPort());
                dp.setSocketAddress(isa);
                sSrv.send(dp);
                }else if(stringa == "f" || stringa == "s" || stringa == "c"){
                    /*
                     * rand = 1  forbice
                     * rand = 2  sasso
                     * rand = 3  carta
                     */
                    double rand = (int)(Math.random()*3);       
                    String mossa = "";
                    if(rand == 1){
                        mossa = "f";
                    }else if(rand == 2){
                        mossa = "s";
                    }else if(rand == 3){
                        mossa = "c";
                    }
                    System.out.println("Mossa client: " + stringa + "\nMossa server: " + mossa);
                    DatagramPacket dp = new DatagramPacket(mossa.getBytes(), mossa.length());
                    InetSocketAddress isa = new InetSocketAddress(sSrv.getLocalPort());
                    dp.setSocketAddress(isa);
                    sSrv.send(dp);
                }else if (stringa == "y" || stringa == "i"){
                    if(stringa == "y"){
                        cw++;
                    }
                    System.out.println("Numero partite giocate dal server: " + cs + "\nNumero partite vinte dal server: " + cw);
                    String frase = "b";
                    DatagramPacket dp = new DatagramPacket(frase.getBytes(), frase.length());
                    InetSocketAddress isa = new InetSocketAddress(sSrv.getLocalPort());
                    dp.setSocketAddress(isa);
                    sSrv.send(dp);
                }else{
                    chiudi = true;
                }
                if(chiudi){
                    sSrv.close();
                    break;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
