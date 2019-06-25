import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * It keeps the components of the left panel of
 */

public class LeftMenu extends JPanel {
    private JLabel label;
    private BottomMenu bottomMenu;
    AddPlayListButton addPlayListButton;
    public JPanel panePanel;
    public LeftMenu(AppObjects appObjects) {
        super();
        AlbumsButton albumsButton = new AlbumsButton(appObjects);
        MusicsButton musicsButton = new MusicsButton(appObjects);
        System.out.println("Left Menu Start Adding...");
        appObjects.setLeftMenu(this);
        Border border = BorderFactory.createLineBorder(Color.BLACK , 5);
        this.setBorder(border);
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setBackground(new Color(0x080825));
        this.setBorder(border);
        panePanel = new JPanel();
        this.add(musicsButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(albumsButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        System.out.println("Left Menu Added!");
    }
}                                                     
                                                      