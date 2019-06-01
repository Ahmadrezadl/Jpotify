import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.*;
import mpatric.mp3agic.ID3v2;
import mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class BottomMenu extends JPanel implements Runnable{
    JLabel cover;
    AdvancedPlayer player;
    boolean isPlaying;
   FileInputStream music;
     JLabel comment;
     JLabel title;
     JLabel fileName;
    Thread t1;
    public BottomMenu() {
        super();
        System.out.println("Bottom Menu Start Creating...");
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setBackground(new Color(0 , 0 , 0));
        cover = new JLabel();
        try {
            Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
            cover.setIcon(new ImageIcon(coverImage));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        isPlaying = false;
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        fileName = new JLabel("<html>Title: <br> Artist: <br>Album:  <br>Year: <br>Genre: <br>File Name:</html>");
        fileName.setFont(new Font("Serif", Font.PLAIN, 20));
        fileName.setForeground(Color.WHITE);
        this.add(cover);
        this.add(fileName);
        System.out.println("Bottom Menu Added!");

    }
    public void setMusic(String filePath)
    {
        System.out.println("Loading Music Descriptions...");
        try {
             music = new FileInputStream(filePath);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("File Created!");
        try {
            player.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Last File Closed!");
        try {
            Mp3File song = new Mp3File(filePath);
            player = new AdvancedPlayer(music);
            t1 =new Thread(this);
            t1.start();
            if(song.hasId3v1Tag()) {
                ID3v2 songTag = song.getId3v2Tag();
                byte[] imageData = songTag.getAlbumImage();
                if(imageData != null) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                    ImageIcon cov = new ImageIcon(img);
                    Image image = cov.getImage();
                    Image newimg = image.getScaledInstance(166 , 168 , java.awt.Image.SCALE_SMOOTH);
                    cov = new ImageIcon(newimg);
                    cover.setIcon(cov);
                }
                else
                {
                    Image coverImage = null;
                    try {
                        coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cover.setIcon(new ImageIcon(coverImage));
                }
                System.out.println("Cover Added!");
                fileName.setText("<html>Title: " + songTag.getTitle() +
                        "<br>Artist:  " + songTag.getAlbumArtist() +
                        "<br>Album: " + songTag.getAlbum() +
                        "<br>Year: " + songTag.getYear() +
                        "<br>Genre: " + songTag.getGenreDescription() +
                        "<br>File Directory: " + song.getFilename() + "</html>");

            }
            else{
                Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                cover.setIcon(new ImageIcon(coverImage));
                fileName.setText("<html>Title: " + "No Title" +
                        "<br>Artist:  " + "Unknown Artist" +
                        "<br>Album: " + "Single" +
                        "<br>Year: " + "xxxx" +
                        "<br>Genre: " + "Music" +
                        "<br>File Directory: " + song.getFilename() + "</html>");
            }
            System.out.println("All Dv3v2 Setup");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Music Descriptions Loaded!");
    }

    @Override
    public void run() {
        try {
            System.out.println("Playing Music...");
            player.play();
            System.out.println("Music ended!");
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
