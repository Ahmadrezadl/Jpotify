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
    public PlaylistTabs(AppObjects appObjects)
    {
        super();
        appObjects.setPlaylistTabs(this);
        ImageIcon tab1Icon = new ImageIcon("icons\\musicLogo.png");
        System.out.println("PlayList Tabs Adding...");

        PlaylistPanel allSongs = new PlaylistPanel();
        appObjects.setAllSongsPanel(allSongs);
        this.addTab("All Songs",allSongs);
        allSongs.addSong(new ImportMusicButton(appObjects,appObjects.getAllSongsPanel()));
        PlaylistPanel favorites = new PlaylistPanel();
        this.add("Favorites" , favorites);
        favorites.addSong(new ImportMusicButton(appObjects,favorites));

        addPlayListButton = new AddPlayListButton(appObjects);

        appObjects.getLeftMenu().add(addPlayListButton);

        System.out.println("PlayList Tabs Added");

    }
}
