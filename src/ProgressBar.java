import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ProgressBar extends JProgressBar {
    int progressBarVal;
    public ProgressBar(AppObjects appObjects){
        super();
        this.setStringPainted(true);
        this.setBackground(Color.red);
        this.setValue(0);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(500,50));
        appObjects.setProgressBar(this);
        ProgressBar progressBar = this;
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                long el = 0;
                progressBarVal = (int)Math.round(((double)mouseX / (double)progressBar.getWidth()) * progressBar.getMaximum());
                try {
                    FileInputStream f = new FileInputStream(appObjects.getPlayingMusic());
                    AdvancedPlayer p = new AdvancedPlayer(f);
                    el = p.findNumbersOfFrame();

                } catch (FileNotFoundException | JavaLayerException e1) {
                    e1.printStackTrace();
                }

                appObjects.getBottomMenu().lastFrame = appObjects.getPlayer().getPosition();
                float frame =  el * ((float)progressBarVal/100);
                appObjects.getBottomMenu().setMusic(appObjects.getPlayingMusic(),(int)(frame));
                progressBar.setValue(progressBarVal);
            }
        });


    }
}