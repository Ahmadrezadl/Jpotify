import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoriteButton extends JButton implements ActionListener {
    AppObjects appObjects;
    boolean liked;
    ImageIcon off;
    ImageIcon on;

    public FavoriteButton(AppObjects appObjects) {
        this.appObjects = appObjects;
        appObjects.setFavoriteButton(this);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);
        liked = false;
        try {
            Image onImage = ImageIO.read(getClass().getResource("icons\\favOn.png"));
            on = new ImageIcon(onImage.getScaledInstance(50 , 50 , Image.SCALE_SMOOTH));

        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
        try {
            Image offImage = ImageIO.read(getClass().getResource("icons\\favOff.png"));
            off = new ImageIcon(offImage.getScaledInstance(50 , 50 , Image.SCALE_SMOOTH));
            setIcon(off);

        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
        this.setFocusable(false);
        this.addActionListener(this);
    }

    public void update(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!appObjects.getLastPlayed().liked){
            appObjects.getFavorites().addSong(new SongButton(appObjects.getLastPlayed().link , appObjects.getLastPlayed().name , appObjects , appObjects.getFavorites()));
            appObjects.getAllSongsPanel().refresh();
            appObjects.getLastPlayed().liked = true;
            setIcon(on);
        }
        else
        {
            appObjects.getLastPlayed().liked = false;
            SongButton s = null;
            for(SongButton songButton : appObjects.getFavorites().songs)
            {
                if(appObjects.getLastPlayed().equals(songButton))
                {
                    s = songButton;
                }
            }
            s.remove();
            appObjects.getFavorites().remove(s);
            setIcon(off);
        }
    }
}
