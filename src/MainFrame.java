import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFrame extends JFrame{
    Image img;
    public MainFrame(String title) throws HeadlessException {
        super(title);
        JFrame loadingFrame = new JFrame("Loading...");
        JLabel Logo = new JLabel();
        Image jpotifyLogo = null;
        try {
            jpotifyLogo = ImageIO.read(getClass().getResource("icons\\logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logo.setIcon(new ImageIcon(jpotifyLogo));
        loadingFrame.add(Logo);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setSize(520,210);
        loadingFrame.setBackground(Color.WHITE);
        loadingFrame.setResizable(false);
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setUndecorated(true);
        loadingFrame.setVisible(true);
        System.out.println ("Main Frame Start Adding...");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100,100);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        setLayout(new BorderLayout());
        TopMenu topMenu = new TopMenu(this);
        add(topMenu , BorderLayout.NORTH);
        BottomMenu bottomMenu = new BottomMenu();
        LeftMenu leftMenu = new LeftMenu(bottomMenu);
        add(leftMenu , BorderLayout.WEST);
        CenterMenu centerMenu = new CenterMenu(bottomMenu , leftMenu);
        add(centerMenu , BorderLayout.CENTER);
        this.getContentPane().setBackground(new Color(176 , 0 , 9));
        this.setVisible(true);
        loadingFrame.setVisible(false);
        System.out.println("Main Frame Added!");
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }

}
