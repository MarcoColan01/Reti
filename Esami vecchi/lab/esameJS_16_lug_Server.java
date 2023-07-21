import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket clientSocket;

        boolean settedOp = false,
                settedFirstOp = false;
        double firstOp = 0.,
                secondOp = 0.;
        String op = "";


        ArrayList<String> op_ammesse = new ArrayList<>();
        op_ammesse.add("+");
        op_ammesse.add("-");
        op_ammesse.add("/");
        op_ammesse.add("*");

        try {
            serverSocket = new ServerSocket(0);
            System.out.println(serverSocket.getInetAddress() +"; " + serverSocket.getLocalPort());

            while(true) {
                try {
                    clientSocket = serverSocket.accept();
                    settedFirstOp = false;
                    settedOp = false;
                    System.out.println("Nuovo client [" + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + "]");

                    InputStream fromClient = clientSocket.getInputStream();
                    OutputStream toClient = clientSocket.getOutputStream();
                    String messReceived = ".";
                    do {
                        byte[] buff = new byte[100];
                        int letti = fromClient.read(buff);
                        System.out.println(letti);
                        String messToSend = "";

                        if (letti > 0) {
                            messReceived = new String(buff, 0, letti);
                            System.out.println("Client: " + messReceived);
                            if(!messReceived.equals(".")) {
                                if (!settedOp) {
                                    if (op_ammesse.contains(messReceived)) {
                                        op = messReceived;
                                        settedOp = true;
                                        messToSend = "OK";
                                    } else {
                                        messToSend = "Illegal operation!\n";
                                    }
                                } else {
                                    try {
                                        if (!settedFirstOp) {
                                            firstOp = Double.parseDouble(messReceived);
                                            System.out.println("Primo operando: " + firstOp);
                                            messToSend = "OK";
                                            settedFirstOp = true;
                                        } else if (settedFirstOp) {
                                            secondOp = Double.parseDouble(messReceived);
                                            System.out.println("Secondo operando: " + secondOp);

                                            messToSend = "OK\n";
                                            double result = 0.;
                                            if (op.equals("+")) {
                                                result = firstOp + secondOp;
                                            } else if (op.equals("-")) {
                                                result = firstOp - secondOp;
                                            } else if (op.equals("*")) {
                                                result = firstOp * secondOp;
                                            } else {
                                                result = firstOp / secondOp;
                                            }

                                            System.out.println("Risultato: " + result);

                                            messToSend += String.valueOf(result);

                                            settedFirstOp = false;
                                            settedOp = false;
                                        }

                                    } catch (NumberFormatException e) {
                                        messToSend = "Illegal number!\n";
                                    }
                                }
                            }


                            System.out.println(messReceived);
                        } else {
                            messToSend = "Errore\n";
                            System.out.println("Errore");
                        }

                        toClient.write(messToSend.getBytes(), 0, messToSend.length());

                    } while(!messReceived.equals("."));

                    toClient.write("OK".getBytes(), 0, "OK".length());


                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout exception!");
                }
            }

        } catch (SocketException e) {
            System.out.println("Timeout exception!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
