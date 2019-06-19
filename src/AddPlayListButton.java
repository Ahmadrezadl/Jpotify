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
    AppObjects appObjects;
    public AddPlayListButton(AppObjects appObjects)
    {
        super();
        this.appObjects = appObjects;
        appObjects.setAddPlayListButton(this);
        this.bottomMenu = appObjects.getBottomMenu();
        this.leftMenu = appObjects.getLeftMenu();
        this.allSongs = appObjects.getAllSongsPanel();
        setBorder(null);
        this.playlistTabs = appObjects.getPlaylistTabs();
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
        System.out.println("TEST");
        playlistTabs.add(name , newPanel);
        newPanel.addSong(new ImportMusicButton(appObjects,allSongs));

    }
}
