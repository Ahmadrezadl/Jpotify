import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener , Runnable{
    private JButton pauseButton;
    AdvancedPlayer player;
    Thread t1;
    boolean isPlaying;
    Image playButtonIcon;
    Image pauseButtonIcon;
    public PauseButton(Thread t1){
        super();
        this.setBorder(null);
        this.setBackground(Color.BLACK);
        this.setFocusable(false);
        isPlaying = true;
        this.t1 = t1;
        try {
            pauseButtonIcon = ImageIO.read(getClass().getResource("icons\\pauseButton.png"));
            playButtonIcon = ImageIO.read(getClass().getResource("icons\\playButton.png"));
            this.setIcon(new ImageIcon(pauseButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        this.addActionListener(this);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isPlaying) {
            t1.suspend();
            isPlaying = false;
            this.setIcon(new ImageIcon(playButtonIcon));
            System.out.println("Music Paused");
        }
        else {
            t1.resume();
            System.out.println("Music Resumed");
            this.setIcon(new ImageIcon(pauseButtonIcon));
            isPlaying = true;
        }
    }

    public void setT1(Thread t1) {
        this.t1 = t1;
    }
    public void setPlaying(boolean isPlaying)
    {
        this.isPlaying = isPlaying;
    }

    @Override
    public void run() {

    }
}
