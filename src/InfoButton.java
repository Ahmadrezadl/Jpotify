import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InfoButton extends JButton implements ActionListener {
    JFrame jFrame;
    public InfoButton() {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        jFrame = new JFrame("Credits");
        JLabel Logo = new JLabel();
        Image jpotifyLogo = null;
        try {
            jpotifyLogo = ImageIO.read(getClass().getResource("icons\\credits.png"));
            Logo.setIcon(new ImageIcon(jpotifyLogo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jFrame.add(Logo);
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\info.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        this.addActionListener(this);
        jFrame.setVisible(false);
        jFrame.setSize(900,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setResizable(false);
        jFrame.setLocation(dim.width/2-jFrame.getSize().width/2, dim.height/2-jFrame.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame.setVisible(true);
    }
}
