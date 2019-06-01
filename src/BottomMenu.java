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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

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


    }
    public void setMusic(String filePath)
    {
        try {
             music = new FileInputStream(filePath);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            player.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {


            Mp3File song = new Mp3File(filePath);
            player = new AdvancedPlayer(music);
            t1 =new Thread(this);
            t1.start();
            ID3v2 songTag = song.getId3v2Tag();
            byte[] imageData = songTag.getAlbumImage();
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
            ImageIcon cov = new ImageIcon(img);
            Image image = cov.getImage();
            Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            cov = new ImageIcon(newimg);
            cover.setIcon(cov);

            fileName.setText("<html>Title: " + songTag.getTitle() +
                    "<br>Artist:  " + songTag.getAlbumArtist() +
                    "<br>Album: " + songTag.getAlbum() +
                    "<br>Year: " + songTag.getYear() +
                    "<br>Genre: " + songTag.getGenreDescription() +
                    "<br>File Directory: " + song.getFilename() + "</html>");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
