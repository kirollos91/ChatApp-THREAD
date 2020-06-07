import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server{
    
    private static ServerSocket serverSocket ;
    private static final int port = 1234;
    
    public static void main(String[] args) 
    {
        try
        {
            serverSocket = new ServerSocket(port);                           //  ربط السوكت بالبورد
        }
        catch(Exception ex)
        {
            System.out.println("Unable to set up port");
            System.exit(1);
        }
        try
        {
            do
            {
                Socket client = serverSocket.accept();
                System.out.println("\nNEW client accepted.\n");
                ClientHandler handler = new ClientHandler(client);
                handler.start();
            }
            while(true);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            
        }
        
    }
}

class ClientHandler extends Thread
{
    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private String received;
    
    public ClientHandler(Socket socket)
    {
        client = socket;
        try
        {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(),true);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void run()
    {
        do
        {
            received = input.nextLine();
            output.println("ECHO: " + received);


        }
        while(!received.equals("QUIT"));
        try
        {
            if(client != null)
            {
                System.out.println("Closing connection.....");
                client.close();
                //System.exit(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Unable to disconnect!!");

        }
    }
}
