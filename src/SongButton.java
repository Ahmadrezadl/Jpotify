

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongButton extends JButton implements ActionListener {
    String link;
    String name;
    LeftMenu leftMenu;
    BottomMenu bottomMenu;

    public SongButton(String link , String name , LeftMenu leftMenu, BottomMenu bottomMenu) {
        super(name);
        this.link = link;
        this.name = name;
        this.leftMenu = leftMenu;
        this.bottomMenu = bottomMenu;
        this.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bottomMenu.setMusic(link);
    }
}
