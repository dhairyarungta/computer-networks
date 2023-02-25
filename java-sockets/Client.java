//Basic TCP sockets in java

import java.io.*;
import java.net.*;
public class Client {
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Client(String address, int port){
        try{
            socket = new Socket(address,port);
            System.out.println("Successfully connected to server");
            
            in = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e){
            System.out.println("Exception: "+ e);
            return;
        }

        String message = "";

        while(!message.equals("end")){
            try{
                message = in.readLine();
                out.writeUTF(message);
            }
            catch(IOException i){
                System.out.println("i");
            }
        }
    
        try{
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    
    }
    public static void main(String args[]){
        Client client = new Client("localhost", 17);
    }
 }   
