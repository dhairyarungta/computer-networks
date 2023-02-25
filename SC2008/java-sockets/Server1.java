import java.net.*;
import java.io.*;

public class Server1 {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream in = null;


    public Server1 (int port){
    
        try{
            
            serverSocket = new ServerSocket(port);
            // serverSocket = new ServerSocket();
            // the  above can be used if we wish not to bind the socket to a specific port.
            System.out.println("Server established.");
            System.out.println("Waiting for request from client.");

            socket = serverSocket.accept();
            System.out.println("Client-server connection established.");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String message = "";

            while(!message.equals("end")){
                try{
                    message = in.readUTF();
                    System.out.println(message);
                }
                catch(IOException i){
                    System.out.println("Exception: "+i);
                }
            }
            System.out.print("Closing Connection");

            socket.close();
        }
    catch(IOException i){
        System.out.println("Exception: "+i);
    }
    
    }
    
    public static void main(String args[]){
        Server1 server1 = new Server1(17);
    }
}
