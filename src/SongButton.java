

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

public class SongButton extends JPanel implements ActionListener {
    String link;
    String name;
    LeftMenu leftMenu;
    BottomMenu bottomMenu;
    FileInputStream music;
    PlaylistPanel playlistPanel;
    AppObjects appObjects;
    ImageIcon cov;
    Mp3File song;
    JButton songButton;
    JLabel songLabel;
    JPopupMenu popupMenu;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    boolean liked;
    public SongButton(String link , String name ,AppObjects appObjects,PlaylistPanel playlistPanel) {
        super();
        liked = false;
        popupMenu = new JPopupMenu();
        popupMenu.setBackground(Color.black);
        btn1 = new JButton("Remove           ");
        btn1.setSize(5 , 30);
        btn2 = new JButton("Play                 ");
        btn2.setSize(5 , 30);
        btn3 = new JButton("Add to favorites");
        btn3.setSize(5 , 30);
        /////////////////////////////////////////////////////
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn1){
                    remove();
                    popupMenu.setVisible(false);
                }
                else if(e.getSource()==btn2){
                    play();
                    popupMenu.setVisible(false);
                }
                else if(e.getSource() == btn3){
                    addToFavorite();
                    popupMenu.setVisible(false);
                }

            }
        };
        popupMenu.add(btn1);
        btn1.addActionListener(listener);
        popupMenu.add(btn2);
        btn2.addActionListener(listener);
        popupMenu.add(btn3);
        btn3.addActionListener(listener);

//        popupMenu.add(new JMenuItem("item 1"));
//        popupMenu.add(new JMenuItem("item 2"));
//        popupMenu.add(new JMenuItem("item 3"));
        this.setBackground(new Color(37, 97, 176));
        this.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        songButton = new JButton();
        this.add(songButton);
        songLabel = new JLabel();
        if(name.length() >= 25)
            songLabel.setText("     "+name.substring(0 , 25)+"...");
        else
            songLabel.setText("     "+name);
        songLabel.setForeground(Color.WHITE);
        this.add(songLabel);
        this.appObjects = appObjects;
        System.out.println("Music Resumed");
        //this.setToolTipText(name);
        this.setBorder(null);
        System.out.println("Song Panel Start Creating...");
        this.link = link;
        this.playlistPanel = playlistPanel;
        this.name = name;
        this.leftMenu = appObjects.getLeftMenu();
        this.bottomMenu = appObjects.getBottomMenu();
        songButton.setBorderPainted(false);
        songButton.setContentAreaFilled(false);
        songButton.setFocusPainted(false);
        this.setOpaque(false);
//        this.playlist = playlist;
        songButton.addActionListener(this);
        try {
            music = new FileInputStream(link);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        song = null;
        try {
            song = new Mp3File(link);
            songButton.setToolTipText(appObjects.getBottomMenu().convertTime((int)song.getLengthInSeconds()));
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
                Image newimg = image.getScaledInstance(166 , 168 , Image.SCALE_SMOOTH);
                cov = new ImageIcon(newimg);
                songButton.setIcon(cov);
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
                songButton.setIcon(new ImageIcon(coverImage));
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
            songButton.setIcon(new ImageIcon(coverImage));
        }
//        songButton.addMouseListener(new MouseListener() {
//            public void mousePressed(MouseEvent me) { }
//            public void mouseReleased(MouseEvent me) { }
//            public void mouseEntered(MouseEvent me) { }
//            public void mouseExited(MouseEvent me) { }
//            public void mouseClicked(MouseEvent me) {
//                if(me.getButton() == MouseEvent.BUTTON3) {
//
//                    remove();
//                }
//            }
//        });
         songButton.addMouseListener(new MouseListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 if(e.getButton()== MouseEvent.BUTTON3){
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                 }

             }

             @Override
             public void mousePressed(MouseEvent e) {

             }

             @Override
             public void mouseReleased(MouseEvent e) {

             }

             @Override
             public void mouseEntered(MouseEvent e) {

             }

             @Override
             public void mouseExited(MouseEvent e) {

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
        int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Delete this song from "+ playlistPanel.name +" playlist?","Warning",dialogButton);
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
        liked = false;
        appObjects.getFavoriteButton().setIcon(appObjects.getFavoriteButton().off);
        for(SongButton s : appObjects.getFavorites().songs)
        {
            if(s.equals(this))
            {
                liked = true;
                appObjects.getFavoriteButton().setIcon(appObjects.getFavoriteButton().on);
            }
        }
        appObjects.setLastPlayed(this);
        for(SongButton s : playlistPanel.songs)
        {
            if(!s.equals(this))
            {
                playlistPanel.remove(s);
                playlistPanel.add(s);
            }
        }
        bottomMenu.setMusic(link,0);
    }
    public String getLink()
    {
        return link;
    }
    public String getTitle(){
        if(!appObjects.getProgressBar().isVisible())
        {
            return "Radio";
        }
        if(song.hasId3v2Tag())
        {
            String title = song.getId3v2Tag().getTitle();
            return title.replace('-',' ');
        }
        if(song.hasId3v1Tag())
        {
            String title = song.getId3v1Tag().getTitle();
            return title.replace('-',' ');
        }
         return "q";
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        appObjects.getProgressBar().setVisible(true);
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

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }
    public void addToFavorite(){
//        SongButton temp = new SongButton(getLink() , getName() , appObjects , appObjects.getFavorites() );
        appObjects.getFavorites().addSong( new SongButton(link , name , appObjects , appObjects.getFavorites() ));

    }
}
