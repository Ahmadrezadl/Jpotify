

import javax.imageio.ImageIO;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
/**
 * it builds  a button that adds a playListPanel and sets a playListTab for it
 * @author G-squad menhaye maryam
 * @version 1.0
 */

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
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
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

        String name= JOptionPane.showInputDialog("Enter Name of PlayList: ","New PlayList");
        if(!(name.equals("") || name.equals(" "))) {
            boolean duplicate = false;
            for(PlaylistPanel p : appObjects.getPlaylists())
            {
                if(p.name.equals(name))
                {
                    duplicate = true;
                    break;
                }
            }
            if(duplicate)
            {
                JOptionPane.showMessageDialog(null,
//                        good thinking!!
                        "Please Choose Another Name For Your PlayList",
                        "Name is Duplicate",
                        JOptionPane.WARNING_MESSAGE);
            }
            else {
                PlaylistPanel newPanel = new PlaylistPanel(appObjects , name);
                JScrollPane newPane = new JScrollPane(newPanel);
                playlistTabs.add(name , newPane);
                newPanel.addSong(new ImportMusicButton(appObjects , newPanel));
                new DualListBox(appObjects,newPanel);

            }
        }

    }
}
