import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayListButton extends JButton  implements ActionListener {
    PlaylistTabs playlistTabs;
    BottomMenu bottomMenu;
    LeftMenu leftMenu;
    PlaylistPanel allSongs;
    public AddPlayListButton(PlaylistTabs playlistTabs,BottomMenu bottomMenu,LeftMenu leftMenu,PlaylistPanel allSongs)
    {
        super();
        this.bottomMenu = bottomMenu;
        this.leftMenu = leftMenu;
        this.allSongs = allSongs;
        setBorder(null);
        this.playlistTabs = playlistTabs;
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\addPlaylistButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name= JOptionPane.showInputDialog("Enter Name of PlayList: ");
        PlaylistPanel newPanel = new PlaylistPanel();
        playlistTabs.add(name , newPanel);
        newPanel.addSong(new ImportMusicButton(bottomMenu,leftMenu,newPanel,allSongs));
    }
}
