import java.net.InetAddress;
import java.net.UnknownHostException;

public class indirizzi2{
    public static void main(String []args){
        String nome = "www.pornhub.com";
        try{
            InetAddress []iaa = InetAddress.getAllByName((nome));
            for (int i = 0; i < iaa.length; i++){
                System.out.println("Indirizzo " + iaa[i].getHostName() + " --> " + iaa[i].getHostAddress());
            }
        }
        catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }
    } 
}