import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {
    private JButton pauseButton;
    AdvancedPlayer player;
    Thread t1;
    boolean isPlaying;
    public PauseButton(Thread t1){
        super();
        this.setBorder(null);
        this.setBackground(Color.BLACK);
        isPlaying = true;
        this.t1 = t1;
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\pauseButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isPlaying == true) {
            t1.suspend();
            isPlaying = false;
        }
        else {
            t1.resume();
            isPlaying = true;
        }
    }
}
