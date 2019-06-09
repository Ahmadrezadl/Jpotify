

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

public class SongButton extends JButton implements ActionListener {
    String link;
    String name;
    LeftMenu leftMenu;
    BottomMenu bottomMenu;
    FileInputStream music;

    public SongButton(String link , String name , LeftMenu leftMenu, BottomMenu bottomMenu/* , Playlist playlist*/) {
        super();
        System.out.println("Music Resumed");
        this.setToolTipText(name);
        this.setBorder(null);
        System.out.println("Song Button Start Creating...");
        this.link = link;
        this.name = name;
        this.leftMenu = leftMenu;
        this.bottomMenu = bottomMenu;
//        this.playlist = playlist;
        this.addActionListener(this);
        try {
            music = new FileInputStream(link);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Mp3File song = null;
        try {
            song = new Mp3File(link);
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        if(song.hasId3v1Tag()) {
            ID3v2 songTag = song.getId3v2Tag();
            byte[] imageData = songTag.getAlbumImage();
            if (imageData != null) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new ByteArrayInputStream(imageData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon cov = new ImageIcon(img);
                Image image = cov.getImage();
                Image newimg = image.getScaledInstance(166 , 168 , java.awt.Image.SCALE_SMOOTH);
                cov = new ImageIcon(newimg);
                this.setIcon(cov);
            }
            else
            {
                Image coverImage = null;
                try {
                    coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.setIcon(new ImageIcon(coverImage));
            }
        }
        else
        {
            Image coverImage = null;
            try {
                coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setIcon(new ImageIcon(coverImage));
        }
        System.out.println("Song Button Created!");
    }
    public String getLink()
    {
        return link;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        bottomMenu.setMusic(link);
    }
}
