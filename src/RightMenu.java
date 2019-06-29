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
        public RightMenu(AppObjects appObjects , String ip) throws IOException {
                this.appObjects = appObjects;
                setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
                socket = new Socket(ip, 9898);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(appObjects.getUserName().getText());
                AddFriendButton addFriendButton = new AddFriendButton(appObjects);
                this.add(addFriendButton);
                this.add(Box.createRigidArea(new Dimension(0, 15)));
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
//                friends.setMaximumSize(new Dimension(250,300));
//                friends.setMinimumSize(new Dimension(250,0));
//                friends.setPreferredSize(new Dimension(300,250));

        }

        @Override
        public void run() {
                while(true) {
                        try {
                                sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        friendsText = "<html><font size = 6>Friends Activity:</font> <br>_____________________________<br>";
                        if(appObjects.getBottomMenu().pauseButton.isPlaying) {
                                String name;
                                String title;
                                if(appObjects.getUserName().getText().length()>12)
                                 name = appObjects.getUserName().getText().substring(0,12) + "...";
                                else
                                        name = appObjects.getUserName().getText();
                                if(appObjects.getLastPlayed().getTitle().length() > 35)
                                        title = appObjects.getLastPlayed().getTitle().substring(0,35) + "...";
                                else
                                        title = appObjects.getLastPlayed().getTitle();
                                out.println("<font size = 5>" +
                                        name +
                                        "</font><br>Listening to: <br>" +
                                        title +
                                        "<br>" + appObjects.getProgressBar().getString());
                        }
                        else {
                                String name;
                                if(appObjects.getUserName().getText().length()>12)
                                        name = appObjects.getUserName().getText().substring(0,12) + "...";
                                else
                                        name = appObjects.getUserName().getText();
                                out.println("<font size = 5>" +
                                        name +
                                        "</font><br>Online!");
                        }
                        try {
                                String clientsNumber = in.readLine();
                                int clients = Integer.parseInt(clientsNumber);
                                for(int i = 0;i < clients;i++)
                                {
                                        boolean b = false;
                                        String newData = in.readLine();
                                        for(String friend : appObjects.getFriends())
                                        {
                                                if(newData.toLowerCase().contains(friend.toLowerCase()))
                                                {
                                                        b = true;
                                                        break;
                                                }
                                        }
                                        if(b)
                                        {
                                        friendsText =
                                                friendsText +
                                                        newData +
                                                        "<br>______________________________<br>";
                                        }
                                }
                                friendsText = friendsText + "</html>";
                                friends.setText(friendsText);

                        } catch (IOException e) {
                                friends.setText("<html><font size = 8>Offline Mode</font></html>");

                                e.printStackTrace();break;
                        }
                }

        }
}

