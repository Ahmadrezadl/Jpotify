import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShuffleButton extends JButton implements ActionListener {
    AppObjects appObjects;
    boolean pressed;
    ImageIcon off;
    ImageIcon on;
    public ShuffleButton(AppObjects appObjects) {
        super();
        this.appObjects = appObjects;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setBackground(Color.white);
        this.setFocusable(false);
        this.addActionListener(this);
        pressed =false ;
        try {
            Image onImage = ImageIO.read(getClass().getResource("icons\\shuffleOn.png"));
            on = new ImageIcon(onImage.getScaledInstance(50 , 50 , Image.SCALE_SMOOTH));

        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
        try {
            Image offImage = ImageIO.read(getClass().getResource("icons\\ShuffleOff.png"));
            off = new ImageIcon(offImage.getScaledInstance(50 , 50 , Image.SCALE_SMOOTH));
            setIcon(off);

        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!pressed) {
            appObjects.getBottomMenu().shuffle = true;
            pressed = true;
            setIcon(on);
        }
        else{
            appObjects.getBottomMenu().shuffle = false;
            pressed = false;
            setIcon(off);

        }

    }
}
