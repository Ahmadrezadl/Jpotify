import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
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
    PauseButton pauseButton;
    JLabel fileName;
    Thread t1;
    VolumeBar volumeBar;
    JLabel percent;
    AppObjects appObjects;
    public BottomMenu(AppObjects appObjects) {
        super();
        this.appObjects = appObjects;
        appObjects.setBottomMenu(this);
        VolumeBar volumeBar = new VolumeBar(appObjects);
        appObjects.setVolumeBar(volumeBar);
        percent = new JLabel(volumeBar.getValue() + "%");
        appObjects.setPercent(percent);
        percent.setForeground(Color.WHITE);
        System.out.println("Bottom Menu Start Creating...");
        this.setLayout(new GridLayout(1,3));
        JPanel panelCenter = new JPanel();
        JPanel panelLeft = new JPanel();
        this.setBackground(new Color(0 , 0 , 0));
        panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelCenter.setLayout(new GridBagLayout());
        panelLeft.setBackground(new Color(0 , 0 , 0));
        panelLeft.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panelCenter.setBackground(new Color(0 , 0 , 0));
        this.add(panelLeft);
        this.add(panelCenter);

        cover = new JLabel();
        try {
            Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
            cover.setIcon(new ImageIcon(coverImage));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        pauseButton = new PauseButton(t1);
        panelCenter.add(pauseButton);
        System.out.println("New Pause Button Added");
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        this.add(panelRight);
        panelRight.setBackground(Color.BLACK);
        JPanel volumePanel = new JPanel();
        panelRight.add(volumePanel,BorderLayout.EAST);
        volumePanel.setBackground(Color.BLACK);
        volumePanel.setLayout(new FlowLayout());
        volumePanel.add(percent);volumePanel.add(volumeBar);

        isPlaying = false;
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        fileName = new JLabel("<html>Title: <br> Artist: <br>Album:  <br>Year: <br>Genre: <br>File Name:</html>");
        fileName.setFont(new Font("Serif", Font.PLAIN, 20));
        fileName.setForeground(Color.WHITE);
        panelLeft.add(cover);panelLeft.add(fileName);

        System.out.println("Bottom Menu Added!");

    }
    public void setMusic(BufferedInputStream in)
    {
        try {
            if(!pauseButton.isPlaying)
            {
                t1.resume();
                System.out.println("Music Resumed");
                isPlaying = true;
            }
            player.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            player = new AdvancedPlayer(in);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        appObjects.setPlayer(player);
        t1 =new Thread(this);
        t1.start();
        fileName.setText("<html>Title: " + "I Love Radio" +
                "<br>Online Stream" +
                "<br>Language: English<br><br><br> "  + "</html>");
        Image coverImage = null;
        try {
            coverImage = ImageIO.read(getClass().getResource("icons\\iloveradio.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cover.setIcon(new ImageIcon(coverImage));
        pauseButton.setT1(t1);
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
            if(!pauseButton.isPlaying)
            {
                t1.resume();
                System.out.println("Music Resumed");
                isPlaying = true;
            }
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
            appObjects.setPlayer(player);
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
                        "<br>Genre: " + songTag.getGenreDescription() + "</html>");

            }
            else{
                Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                cover.setIcon(new ImageIcon(coverImage));
                fileName.setText("<html>Title: " + "No Title" +
                        "<br>Artist:  " + "Unknown Artist" +
                        "<br>Album: " + "Single" +
                        "<br>Year: " + "xxxx" +
                        "<br>Genre: " + "Music" + "</html>");
            }
            System.out.println("All Dv3v2 Setup");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Music Descriptions Loaded!");
        pauseButton.setT1(t1);
    }
    public void playNext(){

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
