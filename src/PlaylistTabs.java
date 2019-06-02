import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlaylistTabs extends JTabbedPane {
    ArrayList <JPanel> panels;
    public PlaylistTabs(BottomMenu bottomMenu, LeftMenu leftMenu)
    {
        super();
        ImageIcon tab1Icon = new ImageIcon("icons\\musicLogo.png");
        System.out.println("PlayList Tabs Adding...");
        PlaylistPanel allSongs = new PlaylistPanel();
        this.addTab("All Songs",tab1Icon,allSongs,"test");
//        this.setMnemonicAt(0, KeyEvent.VK_1);
        ImportMusicButton addMusic = new ImportMusicButton(bottomMenu,leftMenu,allSongs);
        allSongs.addSong(addMusic);
        PlaylistPanel favorites = new PlaylistPanel();
        this.add("Favorites" , favorites);
        favorites.addSong(new ImportMusicButton(bottomMenu,leftMenu,favorites));
        System.out.println("PlayList Tabs Added");
    }
}
