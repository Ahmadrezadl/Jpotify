import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoriteButton extends JButton implements ActionListener {
    AppObjects appObjects;
    boolean liked;

    public FavoriteButton(AppObjects appObjects) {
        this.appObjects = appObjects;
        liked = false;
        this.setText("like");
        this.setFocusable(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!appObjects.getLastPlayed().liked){
            appObjects.getFavorites().addSong(new SongButton(appObjects.getLastPlayed().link , appObjects.getLastPlayed().name , appObjects , appObjects.getFavorites()));
            appObjects.getAllSongsPanel().refresh();
            liked = true;
            appObjects.getLastPlayed().liked = true;
        }
    }
}
