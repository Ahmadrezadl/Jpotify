

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
                if (command.equals("CHT")) {
                    String from = readerHolder.readLine();
                    String text = readerHolder.readLine();

                    System.out.println(from + " : [" + text + "]");
                } else if (command.equals("RESULT")) {
                    String result = readerHolder.readLine();
                    System.out.println("ans :" + result);
                }else if(command.equals("SHW")){
                    String tmp = readerHolder.readLine();
                    System.out.println("friends are "+tmp);
                }
                else if (command.equals("FILE")) {
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

                    System.out.println("New File recieved From " + from);
                }
            } catch (IOException e) {
            }

        }
    }
}
