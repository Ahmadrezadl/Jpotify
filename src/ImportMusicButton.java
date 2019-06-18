import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
//import org.apache.commons.net.*;
import java.util.Comparator;


public class ImportMusicButton extends JButton implements ActionListener {
    BottomMenu bottomMenu;
    LeftMenu leftMenu;
    PlaylistPanel playlistPanel;
    JFileChooser chooser;
    FileNameExtensionFilter filter;
    PlaylistPanel allSongsPanel;
    AppObjects appObjects;
    public ImportMusicButton(AppObjects appObjects, PlaylistPanel playlistPanel){
        System.out.println("Import Music Button Creating...");
        setBorder(null);
        this.appObjects = appObjects;
        this.allSongsPanel = appObjects.getAllSongsPanel();
        this.leftMenu = appObjects.getLeftMenu();
        this.playlistPanel = playlistPanel;
        this.bottomMenu = appObjects.getBottomMenu();
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\addMusicButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
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
        if (chooser.getSelectedFile().getName().charAt( chooser.getSelectedFile().getName().length()-1 ) == '3')
        {
            if(returnVal == 0) {
                SongButton songButton = new SongButton(chooser.getSelectedFile().getAbsolutePath() , chooser.getSelectedFile().getName() , appObjects);
                allSongsPanel.addSong(songButton);
                playlistPanel.addSong(new SongButton(chooser.getSelectedFile().getAbsolutePath() , chooser.getSelectedFile().getName() , appObjects));

                bottomMenu.pauseButton.setIcon(new ImageIcon(bottomMenu.pauseButton.pauseButtonIcon));
                try {
                    bottomMenu.setMusic(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception t) {
                    System.out.println(t);
                }
                System.out.println("File Opened!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Cannot Open " + chooser.getSelectedFile().getName(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE,null);
        }
    }
}
