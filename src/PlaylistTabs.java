import javax.swing.*;

import java.awt.*;
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
        setForeground(new Color(0x17091c));
        this.setBackground(new Color(0x17091c));
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
        PlaylistPanel radio = new PlaylistPanel();
        this.add("Radio" , radio);
        RadioChannel iloveradio = new RadioChannel(0,appObjects);
        radio.add(iloveradio);
        addPlayListButton = new AddPlayListButton(appObjects);

        appObjects.getLeftMenu().add(addPlayListButton);

        System.out.println("PlayList Tabs Added");

    }
    public void removeTabWithTitle(int i) {
        String tabTitle = this.getTitleAt(i);
        if(!(tabTitle.equals("All Songs") || tabTitle.equals("Favorites")))
        this.remove(i);
        else
            JOptionPane.showMessageDialog(null,
                    "Sorry You Can't Remove " + tabTitle + " Playlist!",
                    "Error 02",
                    JOptionPane.WARNING_MESSAGE);

    }

}
