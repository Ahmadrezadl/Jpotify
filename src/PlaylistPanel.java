import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class PlaylistPanel extends JPanel {
    ArrayList<SongButton> songs;
    ArrayList<AlbumButton> albums;
    int i;
    SettingsFrame settingsFrame;
    AppObjects appObjects;
    public String name;
    public PlaylistPanel(AppObjects appObjects,String name)
    {
        super();
        albums = new ArrayList<>();
        this.name = name;
        this.appObjects = appObjects;

            this.setBackground(new Color(0x17091c));
        songs = new ArrayList<>();
        System.out.println("Playlist Panel Start adding...");
        i = 0;
        NewFlowLayout layout = new NewFlowLayout(FlowLayout.LEFT);
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

    public String[] getNames(PlaylistPanel allSongs){
        ArrayList<String> songsName = new ArrayList<>();
        for(SongButton song : allSongs.songs)
        {
            boolean duplicate = false;
            for(SongButton s : songs)
            {
                if(song.link.equals(s.link))
                {
                    duplicate = true;
                    break;
                }
            }
            if(!duplicate)
            songsName.add(song.name);
        }
        String[] temp = new String[songs.size()];
        temp = songsName.toArray(temp);
        return temp;
    }
    public void addSong(SongButton songButton)
    {
            for (SongButton btn : songs) {
                if (btn.getLink().equals(songButton.getLink())) {
                    return;
                }
            }
            songs.add(songButton);
            this.add(songButton);
            boolean firstInAlbum = true;
            for(AlbumButton albumButton : albums)
            {
                if(albumButton.name.equals(songButton.getAlbum()))
                {
                    firstInAlbum = false;
                    albumButton.songs.add(songButton);
                    break;
                }
            }
            if(firstInAlbum)
            {
                AlbumButton b = new AlbumButton(songButton.getAlbum(),appObjects,this,songButton);
                albums.add(b);
                this.add(b);
                b.setVisible(true);
            }
            songs.add(songButton);
            songButton.setVisible(true);
            this.refresh();

    }

    public void refresh(){
        if(appObjects.showMode == 1) {
            for (PlaylistPanel p : appObjects.getPlayLists()) {
                if (!p.name.equals("Radio"))
                    p.settingsFrame.setVisible(false);
                for (AlbumButton a : p.albums) {
                    a.setVisible(true);
                }
                for (SongButton s : p.songs) {
                    s.setVisible(false);
                }
            }
        }
        else
        {
            for(PlaylistPanel p :appObjects.getPlayLists())
            {
                if(!p.name.equals("Radio")) {
                    p.settingsFrame.setVisible(true);
                    for (AlbumButton a : p.albums) {
                        a.setVisible(false);
                    }
                    for (SongButton s : p.songs) {
                        s.setVisible(true);
                    }
                }
            }
        }
    }
    public void addSong(ImportMusicButton importMusicButton)
    {
        settingsFrame =  new SettingsFrame(appObjects);
        SettingsFrame s = settingsFrame;
        this.add(settingsFrame);
        settingsFrame.add(importMusicButton);
        RenamePlayListButton renamePlayListButton = new RenamePlayListButton(appObjects,importMusicButton);
        settingsFrame.add(renamePlayListButton);
        DeletePlayListButton deletePlayListButton = new DeletePlayListButton(appObjects,importMusicButton);
        settingsFrame.add(deletePlayListButton);
        SearchButton searchButton = new SearchButton(appObjects,importMusicButton);
        settingsFrame.add(searchButton);
    }
}
