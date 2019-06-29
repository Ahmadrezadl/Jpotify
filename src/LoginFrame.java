import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoginFrame extends JFrame {
    AppObjects appObjects;
    JTextField username;
    JTextField ip;
    public LoginFrame(AppObjects appObjects) throws HeadlessException {
        super("Login");
        this.setUndecorated(true);
        appObjects.setLoginFrame(this);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        this.setLayout(new BorderLayout());

        this.appObjects = appObjects;
        JLabel Logo = new JLabel();
        Image jpotifyLogo = null;
        try {
            jpotifyLogo = ImageIO.read(getClass().getResource("icons\\logo2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);
        Logo.setIcon(new ImageIcon(jpotifyLogo));
        Logo.setForeground(Color.blue);
        Logo.setFont(new Font("Serif", Font.BOLD, 20));
//        bottom.setLayout(new FlowLayout());
        JLabel username = new JLabel("Username:");
        username.setForeground(Color.WHITE);

        JLabel password = new JLabel("IP Address:");
         this.username = new JTextField();
        File file = new File("username.txt");
        try {
            Scanner sc = new Scanner(file);
            this.username.setText(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        username.setSize(100,100);
        ip = new JTextField();
        JButton login = new JButton("Login");
//        login.setSize(100,100);
        bottom.setLayout(null);
        password.setForeground(Color.WHITE);
        username.setBounds(200, 70, 200, 30);
        password.setBounds(200, 110, 200, 30);
        this.username.setBounds(300, 70, 400, 30);
        ip.setBounds(300, 110, 400, 30);
        login.setBounds(450, 160, 100, 30);
        top.add(Logo);
        bottom.add(username);
        bottom.add(this.username);
        bottom.add(password);
        bottom.add(ip);
        bottom.add(login);
        this.getRootPane().setDefaultButton(login);
        ip.setText("127.0.0.1");
        LoginFrame loginFrame = this;
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
            }
        });
        this.add(top,BorderLayout.NORTH);
        this.add(bottom,BorderLayout.CENTER);
        this.setSize(1000, 600);
//        this.setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
    }
}
