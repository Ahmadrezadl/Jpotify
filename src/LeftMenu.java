import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LeftMenu extends JPanel {


    private final ImportMusicButton importMusic;
    private JLabel label;

    public LeftMenu() {
        super();
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        importMusic = new ImportMusicButton();
        this.setBackground(new Color(176 , 0 , 9));
        label = new JLabel();
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\PlayLists.png"));
            label.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setBackground(Color.BLACK);
        this.add(label);
        this.add(importMusic);



    }
}                                                     
                                                      