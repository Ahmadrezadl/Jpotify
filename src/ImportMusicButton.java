import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
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
        chooser.setMultiSelectionEnabled(true);
        filter = new FileNameExtensionFilter("Mp3 File", "mp3");
        chooser.setFileFilter(filter);
        ImportMusicButton importMusicButton = this;
        this.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON3) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Delete this PlayList?","Warning",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){

                        int index = appObjects.getPlaylistTabs().getSelectedIndex();

                        appObjects.getPlaylistTabs().removeTabWithTitle(index);
                        appObjects.getPlaylists().remove(playlistPanel);
                    }

                }
            }
        });
        System.out.println("Import Music Button Added!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Opening file...");

        int returnVal = chooser.showOpenDialog(null);
        File[] files = chooser.getSelectedFiles();
        for(File file : files) {
            if (file.getName().charAt(file.getName().length() - 1) == '3') {
                if (returnVal == 0) {
                    SongButton songButton = new SongButton(file.getAbsolutePath() , file.getName() , appObjects , allSongsPanel);
                    allSongsPanel.addSong(songButton);
                    playlistPanel.addSong(new SongButton(file.getAbsolutePath() , file.getName() , appObjects , playlistPanel));

                    bottomMenu.pauseButton.setIcon(new ImageIcon(bottomMenu.pauseButton.pauseButtonIcon));
                    try {
                        bottomMenu.setMusic(file.getAbsolutePath());
                    } catch (Exception t) {
                        System.out.println(t);
                    }
                    System.out.println("File Opened!");
                }
            } else {
                JOptionPane.showMessageDialog(null ,
                        "Cannot Open " + chooser.getSelectedFile().getName() ,
                        "Error" ,
                        JOptionPane.ERROR_MESSAGE , null);
            }
        }
    }
}
