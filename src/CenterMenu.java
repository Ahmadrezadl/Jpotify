import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterMenu extends JPanel {
    public CenterMenu(AppObjects appObjects) {
        super();
        System.out.println("Center Menu Start Adding...");
        appObjects.setCenterMenu(this);
        setLayout(new BorderLayout());
        this.setBackground(new Color(176 , 0 , 9));
        Border border = BorderFactory.createLineBorder(Color.BLACK , 5);
        this.setBorder(border);

        PlaylistTabs coversMenu = new PlaylistTabs(appObjects);
        add(coversMenu , BorderLayout.CENTER);
        System.out.println("Center Menu Added!");
    }
}
