

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    public static void main(String[] args) throws Exception {
        System.out.println("Server is running...");
        int clientNumber = 0;
        ServerInfo infos = new ServerInfo();
        InetAddress inetAddress= InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());

        try (ServerSocket listener = new ServerSocket(9898)) {

            while (true) {
                System.out.println("Waiting for a client to connect...");
                new ServerHandler(listener.accept(), clientNumber++, infos).start();
            }
        }
    }

}
class ServerHandler extends Thread {
    private Socket socket;
    private int clientNumber;
    ServerInfo infos;
    public ServerHandler(Socket socket, int clientNumber,ServerInfo infos) {
        this.socket = socket;
        this.infos = infos;
        this.clientNumber = clientNumber;
        System.out.println("New client #" + clientNumber + " connected at " + socket);
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress().getHostAddress());

    }

    public void run() {
        Client c = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String name = in.readLine();
            System.out.println(name);
            c = new Client(clientNumber,name,"Online!");
            infos.clients.add(c);
            while(true) {
                sleep(1000);
                String status = in.readLine();
                c.status = status;
                out.println(infos.clients.size());
                for(Client client : infos.clients)
                {
                    out.println(client.print());
                }
            }

        } catch (IOException e) {
            System.out.println("Error handling client #" + clientNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
            infos.clients.remove(c);
            System.out.println("Connection with client #" + clientNumber + " closed");
        }
    }
}
class Client{
    int clientNumber;
    String name;
    String status;

    public String print(){
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(int clientNumber , String name , String status) {
        this.clientNumber = clientNumber;
        this.name = name;
        this.status = status;
    }
}

class ServerInfo{
    public ArrayList<Client> clients;


    public ServerInfo() {
        clients = new ArrayList<>();
    }


}