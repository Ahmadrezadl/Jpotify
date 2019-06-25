import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

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

    public String[] getNames(){
        ArrayList<String> songsName = new ArrayList<>();
        for(SongButton song : songs)
        {
            songsName.add(song.name);
        }
        String[] temp = new String[songs.size()];
        temp = songsName.toArray(temp);
        return temp;
    }
    public void addSong(SongButton songButton)
    {
        boolean duplicate = false;
        SongButton duplicatedButton= null;
            for (SongButton btn : songs) {
                if (btn.getLink().equals(songButton.getLink())) {
                    duplicate = true;
                    songs.remove(songButton);
                    songs.remove(btn);
                    break;
                }
            }
        if (!duplicate) {
            songs.remove(songButton);
            songs.add(songButton);
            this.add(songButton);
            songButton.setVisible(true);
        }
        else
        {
            songs.remove(songButton);
            songs.add(songButton);
            this.add(songButton);
            songButton.setVisible(true);
        }
    }
    public void addSong(ImportMusicButton importMusicButton)
    {
        SettingsFrame settingsFrame =  new SettingsFrame(appObjects);
        this.add(settingsFrame);
        settingsFrame.add(importMusicButton);
        RenamePlayListButton renamePlayListButton = new RenamePlayListButton(appObjects,importMusicButton);
        settingsFrame.add(renamePlayListButton);
        DeletePlayListButton deletePlayListButton = new DeletePlayListButton(appObjects,importMusicButton);
        settingsFrame.add(deletePlayListButton);
    }
}
