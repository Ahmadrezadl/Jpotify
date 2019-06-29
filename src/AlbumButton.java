

import mpatric.mp3agic.ID3v2;
import mpatric.mp3agic.InvalidDataException;
import mpatric.mp3agic.Mp3File;
import mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * it sets a SongButton cover for an album in center menu , since all the songs of one album have the same cover as the album:)
 * @author G-squad menhaye maryam
 * @version 1.0
 */

public class AlbumButton extends JButton implements ActionListener {
    String link;
    String name;
    FileInputStream music;
    AppObjects appObjects;
    ArrayList<SongButton> songs;
    PlaylistPanel playlistPanel;
    public AlbumButton(String name ,AppObjects appObjects,PlaylistPanel playlistPanel,SongButton songButton) {
        super();
        this.appObjects = appObjects;
        this.name = name;
        this.playlistPanel = playlistPanel;
        songs = new ArrayList<>();
        this.setToolTipText(name);
//        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.addActionListener(this);
        this.setIcon(songButton.cov);
        this.setToolTipText(name);
//        this.setVerticalAlignment ( SwingConstants.BOTTOM ) ;


    }


    public String getLink()
    {
        return link;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(AlbumButton albumButton : playlistPanel.albums)
        {
            albumButton.setVisible(false);
        }
        for(SongButton songButton : playlistPanel.songs)
        {
            if (songButton.getAlbum().equals(this.name))
            {
                songButton.setVisible(true);
            }
            else {
                songButton.setVisible(false);
            }
        }

    }
}
