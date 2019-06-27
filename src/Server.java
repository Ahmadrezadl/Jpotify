


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
   ServerSocket mServer;
    int serverPort = 20019;
    ArrayList<Thread> threads  = new ArrayList<Thread>();
    HashMap<String , ClientManager> clientsMap = new HashMap<String, ClientManager>();
    public ArrayList<String> clients = new ArrayList<>();


    public   Server() throws IOException {
        mServer = new ServerSocket(serverPort);
        System.out.println("server created.");

        while(true) {
            Socket client = mServer.accept();
            System.out.println("new Client added");
            Thread t = new Thread(new ClientManager(this , client));

            threads.add(t);

            t.start();

        }
        //public void addClientManager()

    }
    public void addClientManager(String clientName, ClientManager cm) {
        clientsMap.put(clientName, cm);
    }

    public ClientManager findClientManager(String clientName) {
        return clientsMap.get(clientName);
    }


    public ArrayList<ClientManager> findAllClientManagers() {
        ArrayList<ClientManager> result = new ArrayList<>();
        for (Map.Entry<String, ClientManager> entry : clientsMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
    public void ShowClients(HashMap<String , ClientManager> clientsMap){
        System.out.println(clientsMap.keySet());
    }

    public void addClient(String name){
        clients.add(name);
    }

    public ArrayList<String> getClients() {
        return clients;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();

    }


}
