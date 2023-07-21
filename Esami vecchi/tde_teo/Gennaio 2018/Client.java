import java.io.*;
import java.net.*;
public class Client {
	
	Socket server=null;
	int port =2345;
	DataInputStream in ;
	DataOutputStream out;
	
	public Socket connetti() {
		try {
			System.out.println("[0] - Tentativo di connessione");
			server = new Socket(InetAddress.getLocalHost(), port);
			System.out.println("[1] - Connesso con");
			in = new DataInputStream(server.getInputStream());
			out = new DataOutputStream(server.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("[E] - host sconosciuto");
		} catch (Exception e) {
			System.err.println("[E] - impossibile connettere");
		}
	
		return server;
	}
	

	public static void main(String[] args) throws Exception {
		Client c = new Client();
		c.connetti();
		c.out.writeUTF("*");
		System.out.println(c.in.readUTF());
		c.out.writeUTF("a");
		System.out.println(c.in.readUTF());
		c.out.writeUTF("2");
		System.out.println(c.in.readUTF());
		c.out.writeUTF("7");		
		System.out.println(c.in.readUTF());
		System.out.println(c.in.readUTF());
		System.out.println(c.in.readUTF());
		System.out.println(c.in.readDouble());
		if(c.server!=null) c.server.close();
	}
}
