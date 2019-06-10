import javax.swing.*;

import java.util.ArrayList;

/**
 * this class represents different tabs for different playLists.
 * playLists such as favorites , all songs &... .
 * @author ahmadrezadl
 *
 */

public class PlaylistTabs extends JTabbedPane {
    AddPlayListButton addPlayListButton;
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

        addPlayListButton = new AddPlayListButton(this,bottomMenu,leftMenu,allSongs);
        leftMenu.add(addPlayListButton);

        System.out.println("PlayList Tabs Added");

    }
}
