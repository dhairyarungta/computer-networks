import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer{
    static int QOTD  = 17 ;
    public static void main (String[]args){
        DatagramSocket socket= null;
        try {
            socket = new DatagramSocket(QOTD);
            System.out.println("UDP server listening to "+ QOTD);

        }catch(SocketException e){
            e.printStackTrace();
        }

        try {
            while(true){
                //Listen to UDP request from client 
                byte[] buffer = new byte [512];

                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                System.out.println("waiting for reuqest");

                socket.receive(request);
                String received = new String(request.getData(), 0, request.getLength());
                System.out.println(received);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                //Send request back to client 

                String replyString = "i love hooli";
                byte [] replyBuf = replyString.getBytes("UTF-8");

                DatagramPacket reply = new DatagramPacket(replyBuf,replyBuf.length,clientAddress,clientPort);
                System.out.println("Sending reply to client");
                socket.send(reply);
                System.out.println("reply sent by socket");

            }
        }
        catch (IOException e){
            e.printStackTrace();

        }
        finally{
            socket.close();
        }
    }
}