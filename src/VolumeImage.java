import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VolumeImage extends JButton implements ActionListener {
    Image v1;
    Image v2;
    Image v3;
    Image v4;
    AppObjects appObjects;

    public VolumeImage(AppObjects appObjects) {
            this.appObjects = appObjects;
        try {
            v1 = ImageIO.read(getClass().getResource("icons\\v1.png"));
            v2 = ImageIO.read(getClass().getResource("icons\\v2.png"));
            v3 = ImageIO.read(getClass().getResource("icons\\v3.png"));
            v4 = ImageIO.read(getClass().getResource("icons\\v4.png"));
            this.setIcon(new ImageIcon(v1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addActionListener(this);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);

    }
    public void SetIcon(int percent)
    {
        if(percent > 70)
        this.setIcon(new ImageIcon(v1));
        else if(percent >40)
            this.setIcon(new ImageIcon(v2));
        else if(percent > 0)
            this.setIcon(new ImageIcon(v3));
        else
            this.setIcon(new ImageIcon(v4));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(appObjects.getVolumeBar().getValue() == 0)) {
            appObjects.getVolumeBar().setValue(0);
            this.setIcon(new ImageIcon(v4));
        }
        else
        {
            appObjects.getVolumeBar().setValue(100);
            this.setIcon(new ImageIcon(v1));
        }
    }
}
