

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
import java.util.Objects;

public class SongButton extends JButton implements ActionListener {
    String link;
    String name;
    LeftMenu leftMenu;
    BottomMenu bottomMenu;
    FileInputStream music;
    PlaylistPanel playlistPanel;
    AppObjects appObjects;
    ImageIcon cov;
    public SongButton(String link , String name ,AppObjects appObjects,PlaylistPanel playlistPanel) {
        super();
        this.appObjects = appObjects;
        System.out.println("Music Resumed");
        this.setToolTipText(name);
        this.setBorder(null);
        System.out.println("Song Button Start Creating...");
        this.link = link;
        this.playlistPanel = playlistPanel;
        this.name = name;
        this.leftMenu = appObjects.getLeftMenu();
        this.bottomMenu = appObjects.getBottomMenu();
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
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
                 cov = new ImageIcon(img);
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
                    cov = new ImageIcon(coverImage);
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
                cov = new ImageIcon(coverImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setIcon(new ImageIcon(coverImage));
        }
        this.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                if(me.getButton() == MouseEvent.BUTTON3) {

                    remove();
                }
            }
        });
        System.out.println("Song Button Created!");
    }

    public String getAlbum(){
        Mp3File song = null;
        String temp;
        try {
            song = new Mp3File(link);
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        ID3v2 songTag;
        if(song.hasId3v1Tag()) {
            songTag = song.getId3v2Tag();
            temp = songTag.getAlbum();
        }
        else{
            temp = "Single";
        }
        if (temp == null)
        {
            return "Single";
        }
        return temp;
    }
    public void remove(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Delete this song from current playlist?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            this.setVisible(false);
//            for(SongButton s : playlistPanel.songs)
            String link = this.link;
            playlistPanel.songs.remove(this);
            playlistPanel.remove(this);
            SongButton debug = null;
            PlaylistPanel playlistPaneldebug = null;
            if(playlistPanel.name.equals("All Songs"))
            {
                for(PlaylistPanel p : appObjects.getPlayLists())
                {
                    for(SongButton s : p.getSongs())
                    {
                        if(s.link.equals(this.link))
                        {
                            debug = s;
                            playlistPaneldebug = p;
                        }
                    }
                }
                playlistPaneldebug.remove(debug);
                playlistPaneldebug.songs.remove(debug);
                for(PlaylistPanel p : appObjects.getPlayLists())
                {
                    for(SongButton s : p.getSongs())
                    {
                        if(s.link.equals(this.link))
                        {
                            debug = s;
                            playlistPaneldebug = p;
                        }
                    }
                }
                playlistPaneldebug.remove(debug);
                playlistPaneldebug.songs.remove(debug);
            }
            for(SongButton s : playlistPanel.songs)
            {
                if(s.link.equals(link))
                {

                    debug  = s;
                }
            }
            playlistPanel.songs.remove(debug);
        }
    }

    public void play (){
        appObjects.setLastPlayed(this);
        bottomMenu.setMusic(link,0);
    }
    public String getLink()
    {
        return link;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.play();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongButton that = (SongButton) o;
        return Objects.equals(link , that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link);
    }
}
