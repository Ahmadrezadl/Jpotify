import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class is made for the top menu.
 * top menu contains MinimizeButton , ExitButton and ... .
 * @author G-Squad Menhaye Maryam
 * @version 1.1
 * @since 1.0
 */

public class TopMenu extends JPanel {
    MainFrame mainFrame;
    public TopMenu(AppObjects appObjects){
        super();
        appObjects.setTopMenu(this);
        System.out.println("Top Menu Start Adding...");
        this.mainFrame = appObjects.getMainFrame();
        setBackground(new Color(0x000000));
        setLayout(new BorderLayout());
        ExitButton exitButton = new ExitButton(appObjects);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        add(exitButton);
        MinimizeButton minimizeButton = new MinimizeButton(mainFrame);
        add(minimizeButton);
        this.setVisible(true);
        System.out.println("Top Menu added!");
    }

}
