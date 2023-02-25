import java.io.IOException;
import java.net.*;

public class Rfc865UdpClient
{
    /*
    Name: Harvey Specter
    Group: Always1
    IP Address: 155.23.14.254
     */
    public static void main(String[] args) throws SocketException, UnknownHostException
    {

        //
        // 1. Open UDP socket
        //

        DatagramSocket socket = new DatagramSocket();
        // InetAddress address = InetAddress.getByName("hwlab1.scse.ntu.edu.sg");
        InetAddress address = InetAddress.getByName("localhost");

        try
        {

         //
         // 2. Send UDP request to the server
         //

          String msg = new String("Dhairya rungta, A21");
          byte[] buffer = msg.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, 17);
            socket.send(request);



         //
         // 3. Receive UDP reply from the server
         //


            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
            String received = new String(reply.getData(), 0, reply.getLength());
            System.out.println(received);

        } catch(IOException e) {}
    }
}