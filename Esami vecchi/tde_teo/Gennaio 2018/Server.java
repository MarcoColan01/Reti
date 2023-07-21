import java.io.*;
import java.net.*;


public class Server {
	int port=2345;
	ServerSocket server =null;
	Socket client=null;
	DataInputStream in;
	DataOutputStream out;
	public Double calcola(String s, double a, double b) {
		if(s.compareTo("+")==0) {
			return a+b;
		}else if(s.compareTo("-")==0) {
			return a-b;
		}if(s.compareTo("*")==0) {
			return a*b;
		}else {
			return a/b;
		}
	}
	public String operazione() {
		String s="";
		try {
			s=in.readUTF();
			System.out.println ("[3] - Ricevuto simbolo :"+ s);
			if ( s.compareTo("+")==0 ||s.compareTo("-")==0||s.compareTo("*")==0||s.compareTo("/")==0) {
				out.writeUTF("Operazione legittima "+s+"\nScrivi il primo numero:");
				out.flush();
			}else {
				out.writeUTF("Input non valido");
			}
		} catch (Exception e) {
			System.err.println("[E] - errore operazione");
		}
		return s;

	}
	public Double numero() throws Exception{
		double a=0;
		boolean giusto=false;
		while(!giusto) {
			try {
				a=Double.parseDouble(in.readUTF());
				System.out.println ("[3] - Ricevuto numero :"+ a);
				out.writeUTF("Operazione legittima "+a);
				out.flush();
				giusto=true;
			}catch (Exception e) {
				out.writeUTF("Input non valido");
				giusto=false;
			}
		}
		return a;
	}
	public static void main(String[] args) throws Exception {
		Server s=new Server();
		String str;
		double a,b;
		System.out.println ("[0] - Inizializzo server");
		s.server = new ServerSocket(s.port);
		while(true) {
			System.out.println ("[1] - Attendo connessione");
			s.client=s.server.accept();
			System.out.println ("[2] - Connessione effettuata con:" + s.client.getInetAddress().toString());
			s.in = new DataInputStream(s.client.getInputStream());
			s.out= new DataOutputStream(s.client.getOutputStream());
			
			str =s.operazione();
			a=s.numero();
			s.out.writeUTF("Scrivi il prossimo numero:");
			b=s.numero();
			s.out.writeUTF("input: "+ a + str + b + "\n");
			s.out.flush();
			s.out.writeDouble(s.calcola(str, a, b));
		}
	
		
	}
}
