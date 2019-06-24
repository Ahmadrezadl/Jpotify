import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel {
    ArrayList<SongButton> songs;
    int i;
    AppObjects appObjects;
    public String name;
    public PlaylistPanel(AppObjects appObjects,String name)
    {
        super();
        this.name = name;
        this.appObjects = appObjects;

            this.setBackground(new Color(0x17091c));
        songs = new ArrayList<>();
        System.out.println("Playlist Panel Start adding...");
        i = 0;
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(layout);
        appObjects.addPlayList(this);
        System.out.println("Playlist Panel Added!");
    }
    public SongButton[] getSongs()
    {
        SongButton[] temp = new SongButton[songs.size()];
        temp = songs.toArray(temp);
        return temp;
    }
    public void addSong(SongButton songButton)
    {
        boolean duplicate = false;
        SongButton duplicatedButton = null;
            for (SongButton btn : songs) {
                if (btn.getLink().equals(songButton.getLink())) {
                    duplicate = true;
                    duplicatedButton = btn;
                    break;
                }
            }
        if (!duplicate) {
            songs.remove(songButton);

            songs.add(songButton);
            this.add(songButton);
        }
        else
        {
            songs.remove(songButton);
            songs.add(songButton);
            duplicatedButton.setVisible(true);
        }
    }
    public void addSong(ImportMusicButton importMusicButton)
    {
        this.add(importMusicButton);
    }
}
