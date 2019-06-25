import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlbumsButton extends JButton implements ActionListener {
    AppObjects appObjects;
    public AlbumsButton(AppObjects appObjects) {
        super();
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);
        this.appObjects = appObjects;
        try {
            Image i = ImageIO.read(getClass().getResource("icons\\albumsButton.png"));
            this.setIcon(new ImageIcon(i));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(PlaylistPanel p :appObjects.getPlayLists())
        {
            if(!p.name.equals("Radio"))
            p.settingsFrame.setVisible(false);
            for(AlbumButton a : p.albums)
            {
            a.setVisible(true);
            }
            for(SongButton s : p.songs)
            {
                s.setVisible(false);
            }
        }
        appObjects.showMode =1;

    }
}
