package Server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServerMessagesManager implements Runnable {
    DataInputStream readerHolder;

    public ServerMessagesManager(DataInputStream reader) {
        readerHolder = reader;
    }

    public void run() {

        while (true) {
            try {
                String command = readerHolder.readLine();

                if (command.equals("FILE")) {
                    String from = readerHolder.readLine();
                    String fileName = readerHolder.readLine();
                    int fileLength = Integer.parseInt(readerHolder.readLine());

                    byte[] fileData = new byte[fileLength];
                    readerHolder.readFully(fileData);

                    // save file to "Files" Folder
                    File filesFolder = new File("Files");
                    if (!filesFolder.exists()) {
                        filesFolder.mkdir();
                    }
                    File file = new File(filesFolder, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(fileData);
                    fos.flush();
                    fos.close();

                    System.out.println("New File received From " + from);
                }
            } catch (IOException e) {
            }

        }
    }
}
