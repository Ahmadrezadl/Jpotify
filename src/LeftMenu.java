import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftMenu extends JPanel {


    private final ImportMusicButton importMusic;
    private JLabel label;
    private BottomMenu bottomMenu;
    public JScrollPane pane;
    public LeftMenu(BottomMenu bottomMenu) {
        super();
        this.bottomMenu = bottomMenu;
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        importMusic = new ImportMusicButton(bottomMenu , this);
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
        pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       pane.setBackground(Color.BLACK);
       pane.getViewport().setBackground(new Color(176 , 0 , 9));
        this.add(importMusic);
        this.add(label);
        this.add(pane);


    }
}                                                     
                                                      