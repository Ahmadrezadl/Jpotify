import javax.swing.*;
import java.awt.*;

public class VolumeBar extends JSlider {
    public VolumeBar(){
        super();
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(100,20));
        this.setFocusable(false);
    }

}
