import java.net.*;
import java.io.*;
public class client {
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try
        {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out =
                    new DataOutputStream(outToServer);
            InetAddress addr = InetAddress.getLocalHost();
            if (addr.getHostName().equals("node1") || addr.getHostName().equals("node2") || addr.getHostName().equals("node3")) {
                out.writeUTF("~~~~~~~~~Hello from " + addr.getHostName() + " " + "ip :" + addr.getHostAddress()+"~~~~~~~~~~~");
            }
            else {
                out.writeUTF("I'm " + addr.getHostName() +" not the host you looking for!");
            }
                InputStream inFromServer = client.getInputStream();
                DataInputStream in =
                        new DataInputStream(inFromServer);
                System.out.println("Server says " + in.readUTF());
                client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
