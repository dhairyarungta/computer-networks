import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.naming.ldap.SortKey;

public class MultithreadServer {
    
    public static void main (String args[]){
        ServerSocket serverSocket=null;
        
        try{
            serverSocket = new ServerSocket(1234);
            serverSocket.setReuseAddress(true);


            while(true){
                // System.out.println("inside run");

                Socket client = serverSocket.accept();
                System.out.println("New client conected: "+client.getInetAddress().getHostAddress());

                ClientHandler clientSock = new ClientHandler(client);
                //class defined within server class, for multi threading

                new Thread(clientSock).start();

            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(serverSocket!=null){
                try{
                    serverSocket.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


    private static class ClientHandler implements Runnable{
        private final Socket clientSocket;

        public ClientHandler(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            PrintWriter out = null;
            BufferedReader in = null;

            try{
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;
                while((line= in.readLine())!=null){
                    System.out.println("Message from client : "+line);
                    // System.out.println("here");
                    out.println(line);//sending the same message backt to the client 
                    // System.out.println("here");


                }
            }catch(IOException e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(out!=null){
                        out.close();
                    }
                    if(in!=null){
                        in.close();
                        clientSocket.close();
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
