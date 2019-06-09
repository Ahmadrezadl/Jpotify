import javax.swing.*;

import java.util.ArrayList;

public class PlaylistTabs extends JTabbedPane {
    ArrayList <JPanel> panels;
    public PlaylistTabs(BottomMenu bottomMenu, LeftMenu leftMenu)
    {
        super();
        ImageIcon tab1Icon = new ImageIcon("icons\\musicLogo.png");
        System.out.println("PlayList Tabs Adding...");

        PlaylistPanel allSongs = new PlaylistPanel();
        this.addTab("All Songs",allSongs);
        allSongs.addSong(new ImportMusicButton(bottomMenu,leftMenu,allSongs,allSongs));

        PlaylistPanel favorites = new PlaylistPanel();
        this.add("Favorites" , favorites);
        favorites.addSong(new ImportMusicButton(bottomMenu,leftMenu,favorites,allSongs));

        System.out.println("PlayList Tabs Added");

    }
}
