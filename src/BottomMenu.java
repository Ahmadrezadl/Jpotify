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
    JLabel albumArtist;
    JLabel  album;
    JLabel year;
     JLabel comment;
     JLabel genre;
     JLabel title;
     JLabel fileName;
    public BottomMenu() {
        super();
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setBackground(new Color(176 , 0 , 9));
        cover = new JLabel();
        isPlaying = false;
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        fileName = new JLabel("File Name: ");
        title = new JLabel("Title: ");
        albumArtist = new JLabel("Artist: ");
        album = new JLabel("Album: ");
        year = new JLabel("Year: ");
        genre = new JLabel("Genre: ");
        fileName.setSize(new Dimension(100 , 100));
//        fileName.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        albumArtist.setFont(new Font("Serif", Font.PLAIN, 20));
        this.add(title);
        this.add(albumArtist);
        this.add(fileName);
        this.add(genre);
        this.add(album);
        this.add(year);
        this.add(cover);


    }
    public void setMusic(String filePath)
    {
//        player.stop();
        try {
             music = new FileInputStream(filePath);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            Mp3File song = new Mp3File(filePath);
            player = new AdvancedPlayer(music);
            Thread t1 =new Thread(this);
            t1.start();
            ID3v2 songTag = song.getId3v2Tag();
            byte[] imageData = songTag.getAlbumImage();
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
            ImageIcon cov = new ImageIcon(img);

            cover.setIcon(cov);
            albumArtist.setText("Artist: " + songTag.getAlbumArtist());
            album.setText("Album: " + songTag.getAlbum());
            year.setText("Year: " + songTag.getYear());
//            comment.setText(songTag.getComment());
            genre.setText("Genre: " + songTag.getGenreDescription());
            title.setText("Title: " + songTag.getTitle());
            fileName.setText("File Name: " + song.getFilename());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            player.stop();
            player.play();

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
