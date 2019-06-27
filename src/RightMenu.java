import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class RightMenu extends JPanel implements Runnable{
        AppObjects appObjects;
        Socket socket;
        BufferedReader in;
        PrintWriter out;
        JButton refresh;
        JLabel friends;
        String friendsText;
        public RightMenu(AppObjects appObjects) throws IOException {
                this.appObjects = appObjects;

                socket = new Socket("127.0.0.1", 9898);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(appObjects.getUserName().getText());
                this.setBackground(Color.BLACK);

                Thread t = new Thread(this);
                t.start();
                friends = new JLabel("Friends: ");
                this.add(friends);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.getAllFonts();
                Font font = new Font("Jokerman", Font.PLAIN, 20);
                friends.setFont(font);
                friends.setForeground(Color.WHITE);
                friends.setBackground(Color.BLACK);
                friends.setMaximumSize(new Dimension(300,500));
                friends.setMinimumSize(new Dimension(300,500));
                friends.setPreferredSize(new Dimension(300,500));

        }

        @Override
        public void run() {
                while(true) {
                        try {
                                sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        friendsText = "<html>Friends Activity: <br>";
                        if(appObjects.getBottomMenu().pauseButton.isPlaying)
                                out.println("Listening to:" + appObjects.getLastPlayed().getTitle());
                        else
                        out.println("Online!");
                        try {
                                String clientsNumber = in.readLine();
                                int clients = Integer.parseInt(clientsNumber);
                                System.out.println(clients);
                                for(int i = 0;i < clients;i++)
                                {
                                        System.out.println("im here");
                                        friendsText = friendsText + in.readLine() + "<br>" + "______________________________" + "<br>";
                                }
                                friendsText = friendsText + "</html>";
                                friends.setText(friendsText);

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

        }
}

