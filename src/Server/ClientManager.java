package Server;

import java.io.*;
import java.net.Socket;

public class ClientManager implements Runnable {
    Socket clientHolder;
    Server serverHolder;
    InputStream fromClientStream;
    OutputStream toClientStream;

    DataInputStream reader;
    PrintWriter writer;
    String name;
    public ClientManager(Server server , Socket client){
        serverHolder = server;
        clientHolder = client;
    }


    @Override
    public void run() {
        try {
            fromClientStream = clientHolder.getInputStream();
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream , true);

            writer.println("what is your name?");
            System.out.println("Server : What is your name?");
            name = reader.readUTF();

            serverHolder.addClientManager(name , this);

            while (true){
//                reading the command from
                String command = reader.readUTF();

            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
