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

    public ClientManager(Server server, Socket client) {
        serverHolder = server;
        clientHolder = client;
    }


    @Override
    public void run() {
        try {
            fromClientStream = clientHolder.getInputStream();
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);

            writer.println("what is your name?");
            System.out.println("Server : What is your name?");


            serverHolder.addClientManager(name, this);

            while (true) {
//                reading the command from client
                String command = reader.readLine();

//                now decide by command
                if (command.equals("BYE")) {
                    System.out.println("good bye " + name);
                    break;
                } else if (command.equals("SFILE")) {
                    String fileName = reader.readLine();
                    String to = reader.readLine();

                    int fileLength = Integer.parseInt(reader.readLine());

                    byte[] fileData = new byte[fileLength];

                    reader.readFully(fileData);

                    sendFileToAnotherClient(fileName, to, fileData);
                }
            }
        } catch (Exception e) {
        }

    }
    private void sendFileToAnotherClient(String fileName, String to, byte[] fileData) {

        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendFile(name, fileName, fileData);

    }

    private void sendFile(String from, String fileName, byte[] fileData) {
        try {
            writer.println("FILE");
            writer.println(from);
            writer.println(fileName);
            writer.println("" + fileData.length);

            toClientStream.write(fileData, 0, fileData.length);
            toClientStream.flush();// force to send data

        } catch (IOException e) {
        }
    }


}

