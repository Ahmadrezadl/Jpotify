import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShuffleButton extends JButton implements ActionListener {
    AppObjects appObjects;
    boolean pressed;
    public ShuffleButton(AppObjects appObjects) {
        super("shuffle");
//        this.setText("Fuck");
        this.appObjects = appObjects;
//        this.setBorderPainted(false);
//        this.setContentAreaFilled(false);
//        this.setBorder(null);
        this.setBackground(Color.white);
        this.setFocusable(false);
        this.addActionListener(this);
        pressed =false ;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!pressed) {
            appObjects.getBottomMenu().shuffle = true;
            pressed = true;
            this.setText("shuffled");
        }
        else{
            appObjects.getBottomMenu().shuffle = false;
            pressed = false;
            this.setText("shuffle");

        }

    }
}
