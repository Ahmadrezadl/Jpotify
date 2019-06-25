import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class AlbumsButton extends JButton {
    AppObjects appObjects;
    public AlbumsButton(AppObjects appObjects) {
        super();
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);
        this.appObjects = appObjects;
        try {
            Image i = ImageIO.read(getClass().getResource("icons\\albumsButton.png"));
            this.setIcon(new ImageIcon(i));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
