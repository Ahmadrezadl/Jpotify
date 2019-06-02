import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel {
    ArrayList<SongButton> songs;
    int i;

    public PlaylistPanel()
    {
        super();
        System.out.println("Playlist Panel Start adding...");
        i = 0;
        GridBagLayout layout = new GridBagLayout();

        this.setLayout(layout);
        System.out.println("Playlist Panel Added!");
    }
    public void addSong(SongButton songButton)
    {
//        songs.add(songButton);
        i++;
        this.add(songButton,i);
    }
    public void addSong(ImportMusicButton importMusicButton)
    {
        this.add(importMusicButton, 0);
    }
}
