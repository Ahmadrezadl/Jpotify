import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RadioChannel extends JButton implements ActionListener {
    String link;
    AppObjects appObjects;
    public RadioChannel(int i,AppObjects appObjects){
        super();
        this.setToolTipText("I Love Radio");
        this.setBorder(null);
        this.appObjects  = appObjects;
        if(i == 0)
        {
            try {
                Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\iloveradio.png"));
                this.setIcon(new ImageIcon(exitButtonIcon));
            }
            catch (Exception ignored)
            {

            }
            this.link = "http://stream01.iloveradio.de/iloveradio1.mp3";
            this.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
            appObjects.getBottomMenu().setMusic(in);


        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
