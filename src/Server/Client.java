package Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket mSocket;
    int port = 20019;
    String serverAddress = "127.0.0.1";
    String to;

    InputStream fromServerStream;
    OutputStream toServerStream;

    DataInputStream reader;
    PrintWriter writer;

    public Client() throws IOException {

        mSocket = new Socket(serverAddress, port);

        System.out.println("connect to server...");

        fromServerStream = mSocket.getInputStream();
        toServerStream = mSocket.getOutputStream();

        reader = new DataInputStream(fromServerStream);
        writer = new PrintWriter(toServerStream, true);

        //1: read message from server
        String msg = reader.readUTF();
        System.out.println("Server : " + msg);

        Thread t = new Thread(new ServerMessagesManager(reader));
        t.start();

        menu();
    }

    public void menu() throws IOException {
        Scanner sc = new Scanner(System.in);


        BufferedReader br = new BufferedReader(new FileReader("username.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            String name = sb.toString();
            sendName(name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        while (true) {
            System.out.println("__________");
            System.out.println("enter your command number");
            System.out.println("1)send a music file\n" + "2)exit");
            int commandNumber = Integer.parseInt(sc.nextLine());

            switch (commandNumber) {
                case 1:

                    System.out.println("Enter receiver's name");
                    to = sc.nextLine();

                    System.out.println("enter file path");
                    String fileName = sc.nextLine();
                    sendFile(to, fileName);
                    break;

                case 2:
                    writer.println("BYE");
            }
        }

    }

    private void sendFile(String to, String fileName) {
        File file = new File(fileName);
        long fileLength = file.length();

        writer.println("SFILE");
        writer.println(file.getName());
        writer.println(to);
        writer.println("" + fileLength);

        try {

            // convert file to byte array
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            byte[] fileData = new byte[(int) fileLength];
            dis.readFully(fileData);

            // send byte array to server
            toServerStream.write(fileData);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void sendName(String name) {
        writer.println(name);
    }
    public static void main(String[] args){
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
