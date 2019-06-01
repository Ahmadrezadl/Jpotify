import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

public class ImportMusicButton extends JButton implements ActionListener {
    BottomMenu bottomMenu;
    LeftMenu leftMenu;
    JPanel jPanel;
    JFileChooser chooser;
    FileNameExtensionFilter filter;
    public ImportMusicButton(BottomMenu bottomMenu , LeftMenu leftMenu , JPanel jPanel){
        System.out.println("Import Music Button Creating...");
        setBorder(null);
        this.leftMenu = leftMenu;
        this.jPanel = jPanel;
        this.bottomMenu = bottomMenu;
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\addMusicButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        this.addActionListener(this);
        this.setBorder(null);
        this.setBackground(Color.BLACK);
        chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Mp3 File", "mp3");
        chooser.setFileFilter(filter);
        System.out.println("Import Music Button Added!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Opening file...");
        int returnVal = chooser.showOpenDialog(null);
        SongButton songButton = new SongButton(chooser.getSelectedFile().getAbsolutePath(),chooser.getSelectedFile().getName(),leftMenu,bottomMenu);
        jPanel.add(songButton);
        try {
            bottomMenu.setMusic(chooser.getSelectedFile().getAbsolutePath());
        }
        catch (Exception t)
        {
            System.out.println(t);
        }
        System.out.println("File Opened!");
    }
}
