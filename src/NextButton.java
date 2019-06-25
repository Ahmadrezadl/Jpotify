import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NextButton extends JButton implements ActionListener {
    AppObjects appObjects;
    public NextButton(AppObjects appObjects){
        super("test");
        this.appObjects = appObjects;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        this.setBackground(Color.BLACK);
        this.setFocusable(false);
        this.addActionListener(this);
        try {
            Image image = ImageIO.read(getClass().getResource("icons\\nextButton.png"));
            this.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appObjects.getBottomMenu().playNext();

    }
}
