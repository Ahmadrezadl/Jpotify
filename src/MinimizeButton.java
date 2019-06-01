import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinimizeButton extends JButton implements ActionListener {
    MainFrame mainFrame;

    public MinimizeButton(MainFrame mainFrame) {
        System.out.println("Minimize Button Start Adding...");
        this.mainFrame = mainFrame;
        this.setToolTipText("Minimize Window");
        try {
            Image minimizeButton = ImageIO.read(getClass().getResource("icons\\minimizeButton.png"));
            this.setIcon(new ImageIcon(minimizeButton));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        this.addActionListener(this);
        this.setBorder(null);
        System.out.println("Minimize Button added!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.setState(Frame.ICONIFIED);
    }
}
