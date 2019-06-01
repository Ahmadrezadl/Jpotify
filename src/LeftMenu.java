import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftMenu extends JPanel {
    private JLabel label;
    private BottomMenu bottomMenu;
    public JTree playLists;
    public JPanel panePanel;
    public LeftMenu(BottomMenu bottomMenu) {
        super();
        System.out.println("Left Menu Start Adding...");
        this.bottomMenu = bottomMenu;
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setBackground(new Color(176 , 0 , 9));
        label = new JLabel();
        try {
            Image playListsIcon = ImageIO.read(getClass().getResource("icons\\PlayLists.png"));
            label.setIcon(new ImageIcon(playListsIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        Border border = BorderFactory.createLineBorder(new Color(176 , 0 , 9), 10);
        this.setBorder(border);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setBackground(Color.BLACK);
        panePanel = new JPanel();
        this.add(label);
        System.out.println("Left Menu Added!");
    }
}                                                     
                                                      