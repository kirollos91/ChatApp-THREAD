import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    private static InetAddress host;
    private static final int port =1234;



    public static void main(String[] args) 
    {
        try
        {
            host =  InetAddress.getLocalHost();

        }   
        catch(Exception ex)
        {
            System.out.println("\nHost ID not found !");
            System.exit(1);
        }
        sendMessage();

    }

    private static void sendMessage()
    {
        Socket  socket =null ;
        Scanner userEntry = new Scanner(System.in);
        try
        {
            socket = new Socket(host, port);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);
            
            String message, response;
            do
            {
                System.out.print("\nEnter message(QUIT) to exit : ");
                message = userEntry.nextLine();
                networkOutput.println(message);
                response = networkInput.nextLine();
                System.out.print("\nSERVER> " + response);
            }
            while(!message.equals("QUIT"));
        } 
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        
        try
        {
            System.out.println("\nClosing connection......");
            socket.close();
            userEntry.close();
        }catch(Exception ex)
        {
            System.out.println("\nUnable to Disconnect!");
            System.exit(1);
        }
        

    }
}