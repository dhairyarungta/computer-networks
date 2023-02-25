import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Rfc865UdpClient2{
    static int QOTD= 17;
    static String Server_Name = "localhost"; 
    // static String Server_Name = "hwlab1.scse.ntu.edu.sg";

    public static void main (String [] args){
        DatagramSocket socket  =null;
        try {
            InetAddress serverAddress = InetAddress.getByName(Server_Name);
            socket  = new DatagramSocket();
            socket.connect(serverAddress, QOTD);
            System.out.println("UDP clinet connected to server "+ serverAddress.getCanonicalHostName());

        }catch(Exception e){
            e.printStackTrace();        
            System.exit(-1);
        }

        try{
            String content= "Dhairya Rungta A21 172.21.145.81";
            // String content = "rungta dhairya A21 ,"+ InetAddress.getLocalHost().getHostAddress();
           
            //send request to server
            byte [] buf = content.getBytes("UTF-8");
            System.out.println("Sending content to server");
            DatagramPacket request = new DatagramPacket(buf, buf.length);
            socket.send(request);
            System.out.println("Message sent to server");
            
            //receive the reply from server 
            byte []  replyBuf = new byte [512]   ;
            DatagramPacket reply = new DatagramPacket(replyBuf,replyBuf.length);
            System.out.println("waiting for reply");
            socket.receive(reply);



            //Process the reply
            String replyContent = new String(replyBuf);
            System.out.println("received reply" + replyContent);
        }

        catch(IOException e){
            e.printStackTrace();
            // System.out.println("here");
        }
        finally {
            socket.close();
        }
    }
}