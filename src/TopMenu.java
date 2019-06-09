import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class ris made for the top menu.
 * top menu contains MinimizeButton , ExitButton and ... .
 * @author ElhamRazi
 * @version 1.1
 * @since 1.0
 */

public class TopMenu extends JPanel {
    MainFrame mainFrame;
    public TopMenu(MainFrame mainFrame){
        super();
        System.out.println("Top Menu Start Adding...");
        this.mainFrame = mainFrame;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        ExitButton exitButton = new ExitButton();
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(exitButton);
        MinimizeButton minimizeButton = new MinimizeButton(mainFrame);
        add(minimizeButton);
        this.setVisible(true);
        System.out.println("Top Menu added!");
    }

}
