import javax.swing.*;
import javax.swing.border.Border;
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
                this.setLayout(new BorderLayout());
                socket = new Socket("127.0.0.1", 9898);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(appObjects.getUserName().getText());
                this.setBackground(Color.BLACK);
                Border border = BorderFactory.createLineBorder(Color.BLACK , 20);
                this.setBorder(border);
                Thread t = new Thread(this);
                t.start();
                friends = new JLabel("Friends: ");
                this.add(friends,BorderLayout.NORTH);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.getAllFonts();
                Font font = new Font("Jokerman", Font.PLAIN, 16);
                friends.setFont(font);
                friends.setForeground(Color.WHITE);
                friends.setBackground(Color.BLACK);
                friends.setMaximumSize(new Dimension(320,500));
                friends.setMinimumSize(new Dimension(320,0));
//                friends.setPreferredSize(new Dimension(320,2));

        }

        @Override
        public void run() {
                while(true) {
                        try {
                                sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        friendsText = "<html><font size = 10>Friends Activity:</font> <br>__________________________________<br>";
                        if(appObjects.getBottomMenu().pauseButton.isPlaying)
                                out.println("Listening to: <br>" + appObjects.getLastPlayed().getTitle() + "<br>" +appObjects.getProgressBar().getString());
                        else
                        out.println("Online!");
                        try {
                                String clientsNumber = in.readLine();
                                int clients = Integer.parseInt(clientsNumber);
                                for(int i = 0;i < clients;i++)
                                {
                                        friendsText = friendsText + in.readLine() + "<br>" + "__________________________________" + "<br>";
                                }
                                friendsText = friendsText + "</html>";
                                friends.setText(friendsText);

                        } catch (IOException e) {
                                friends.setText("Offline Mode");
                                e.printStackTrace();
                        }
                }

        }
}

