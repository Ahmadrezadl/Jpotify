import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistTabs extends JTabbedPane {
    ArrayList <JPanel> panels;
    public PlaylistTabs(BottomMenu bottomMenu, LeftMenu leftMenu)
    {
        super();
        System.out.println("PlayList Tabs Adding...");
//        pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel allSongs = new JPanel();
        this.add("All Songs",allSongs);
        ImportMusicButton addMusic = new ImportMusicButton(bottomMenu,leftMenu,allSongs);
        allSongs.add(addMusic);
        JPanel favorites = new JPanel();
        this.add("Favorites" , favorites);
        favorites.add(new ImportMusicButton(bottomMenu,leftMenu,favorites));
        System.out.println("PlayList Tabs Added");
    }
}
