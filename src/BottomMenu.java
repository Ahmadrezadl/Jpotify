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

public class BottomMenu extends JPanel {
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
            Player player = new Player(music);
            player.play();
            ID3v2 id3v2Tag = song.getId3v2Tag();
            System.out.println(id3v2Tag.getTitle());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
