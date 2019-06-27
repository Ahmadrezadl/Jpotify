import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButton extends JButton implements ActionListener {

    AppObjects appObjects;
    ImportMusicButton importMusicButton;
    public SearchButton(AppObjects appObjects,ImportMusicButton importMusicButton) {
        super();
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setBorder(null);

        this.appObjects = appObjects;
        this.importMusicButton = importMusicButton;
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\searchButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name= JOptionPane.showInputDialog("Search for: ");
        for(SongButton s : importMusicButton.playlistPanel.songs)
        {
            if(s.name.toLowerCase().contains(name.toLowerCase()) || s.getTitle().toLowerCase().contains(name.toLowerCase()))
            {
                s.setVisible(true);
            }
            else
            {
                s.setVisible(false);
            }
        }
        for(AlbumButton a : importMusicButton.playlistPanel.albums)
        {
            a.setVisible(false);
        }
    }
}
