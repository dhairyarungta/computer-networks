import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultithreadClient {

    public static void main(String []args){
        Socket socket = null;

        try{
            socket = new Socket("localhost",1234);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc  = new Scanner(System.in);
            String line = "";

            while(!line.equals("exit")){
                line = sc.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Reply received from server"+ in.readLine());
            }
            sc.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
