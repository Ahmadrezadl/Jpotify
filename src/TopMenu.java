import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * this class is made for the top menu.
 * top menu contains MinimizeButton , ExitButton and ... .
 * @author G-Squad Menhaye Maryam
 * @version 1.1
 * @since 1.0
 */

public class TopMenu extends JPanel {
    MainFrame mainFrame;
    Scanner sc;
    public TopMenu(AppObjects appObjects){
        super();

        appObjects.setTopMenu(this);
        System.out.println("Top Menu Start Adding...");
        this.mainFrame = appObjects.getMainFrame();
        setBackground(new Color(0x000000));
        setLayout(new BorderLayout());
        ExitButton exitButton = new ExitButton(appObjects);
        this.setLayout(new BorderLayout());
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        this.add(right,BorderLayout.EAST);
        this.add(left,BorderLayout.WEST);
        JLabel jpotify = new JLabel();
        try {
            Image coverImage = ImageIO.read(getClass().getResource("icons\\logoTop.png"));
            jpotify.setIcon(new ImageIcon(coverImage));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        JButton userName = new JButton("Guest");
        File file = new File("username.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        userName.setText(sc.nextLine());
        userName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= JOptionPane.showInputDialog("Enter New UserName: ");
                userName.setText(name);
            }
        });
        userName.setBorderPainted(false);
        userName.setContentAreaFilled(false);
        userName.setFocusPainted(false);
        userName.setOpaque(false);
        appObjects.setUserName(userName);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        Font font = new Font("Jokerman", Font.PLAIN, 20);
        userName.setFont(font);
        userName.setForeground(Color.WHITE);
        left.setBackground(Color.BLACK);
        right.setBackground(Color.BLACK);
        left.add(jpotify);
        left.add(userName);
        right.setLayout(new FlowLayout(FlowLayout.RIGHT));
        right.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        right.add(exitButton);
        MinimizeButton minimizeButton = new MinimizeButton(mainFrame);
        right.add(minimizeButton);
        this.setVisible(true);
        System.out.println("Top Menu added!");
    }

}
