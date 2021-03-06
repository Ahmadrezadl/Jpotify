import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * it creates a button in the leftMenu and is used for showing albums panel in center menu
 * @author G-squad menhaye Maryam
 * @version  1.1
 * @since 1.0
 */
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
                int i = 0;
                for(SongButton s : p.songs)
                {
                    if(a.name.equals(s.getAlbum()))
                        i++;
                }
                System.out.println(i);
                if(i > 2)
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
