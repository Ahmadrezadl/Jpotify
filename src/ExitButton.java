import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends JButton implements ActionListener {
    public ExitButton() {
        System.out.println("Exit Button Start Adding!");
        setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\exitButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        this.addActionListener(this);
        this.setBackground(Color.BLACK);
        this.setToolTipText("Exit Program");
        System.out.println("Exit Button Added!");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("Exiting Program...");
            System.exit(1);

    }
}
