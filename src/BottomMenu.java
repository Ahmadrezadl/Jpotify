import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
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

public class BottomMenu extends JPanel implements Runnable{
    JLabel cover;
    Player player;
    FileInputStream music;
    public BottomMenu() {
        super();
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setBackground(new Color(176 , 0 , 9));
        cover = new JLabel();

        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        this.add(cover);


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
            Mp3File song = new Mp3File(filePath);
             player = new Player(music);
            Thread t1 =new Thread(this);
            t1.start();
            ID3v2 songTag = song.getId3v2Tag();
            here
            songTag.getAlbumArtist();
            songTag.getAlbum();
            songTag.getYear();
            songTag.getComment();
            songTag.getGenre();
            songTag.getTitle();
            song.getFilename();
            System.out.println(songTag.getTitle());
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
