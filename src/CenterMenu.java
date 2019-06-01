import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CenterMenu extends JPanel {
    public CenterMenu(BottomMenu bottomMenu , LeftMenu leftMenu) {
        super();
        System.out.println("Center Menu Start Adding...");
        setLayout(new BorderLayout());
        this.setBackground(new Color(176 , 0 , 9));
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        add(bottomMenu , BorderLayout.SOUTH);
        PlaylistTabs coversMenu = new PlaylistTabs(bottomMenu, leftMenu);
        add(coversMenu , BorderLayout.CENTER);
        System.out.println("Center Menu Added!");
    }
}
