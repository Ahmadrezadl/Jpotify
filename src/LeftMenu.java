import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftMenu extends JPanel {
    private JLabel label;
    private BottomMenu bottomMenu;
    AddPlayListButton addPlayListButton;
    public JPanel panePanel;
    public LeftMenu(AppObjects appObjects) {
        super();
        System.out.println("Left Menu Start Adding...");
        appObjects.setLeftMenu(this);
        this.bottomMenu = bottomMenu;
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setBackground(new Color(176 , 0 , 9));
        Border border = BorderFactory.createLineBorder(new Color(176 , 0 , 9), 10);
        this.setBorder(border);

        panePanel = new JPanel();
        System.out.println("Left Menu Added!");
    }
}                                                     
                                                      