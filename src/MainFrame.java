import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class MainFrame extends JFrame{
    Image img;
    public MainFrame(String title) throws HeadlessException {
        super(title);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1650,1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        setLayout(new BorderLayout());
        TopMenu topMenu = new TopMenu(this);
        add(topMenu , BorderLayout.NORTH);
        BottomMenu bottomMenu = new BottomMenu();
        add(bottomMenu , BorderLayout.SOUTH);
        LeftMenu leftMenu = new LeftMenu(bottomMenu);
        add(leftMenu , BorderLayout.WEST);
        this.getContentPane().setBackground(new Color(176 , 0 , 9));
        this.setVisible(true);
    }
    public void paintComponent(Graphics g)
    {
        g.drawImage(img,0,0,this);
    }

}
