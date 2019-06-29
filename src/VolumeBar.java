import javazoom.jl.player.JavaSoundAudioDevice;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import javax.sound.sampled.AudioSystem;

public class VolumeBar extends JSlider {
    VolumeBar volumeBar;
    AppObjects appObjects;
    FloatControl volumeControl;
    public VolumeBar(AppObjects appObjects){
        super();
        this.appObjects = appObjects;
        setUp();
    }
    private void setUp(){
        this.volumeBar = this;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(100,20));
        Port.Info source = Port.Info.SPEAKER;
        if (AudioSystem.isLineSupported(source))
        {
            try
            {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                this.setValue((int)volumeControl.getValue()*100);
            }
            catch (LineUnavailableException ex)
            {
                System.err.println("source not supported");
                ex.printStackTrace();
            }
        }
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {

                appObjects.getPercent().setText( volumeBar.getValue() + "%");
                appObjects.getVolumeImage().SetIcon(volumeBar.getValue());
                if (AudioSystem.isLineSupported(source))
                {
                    try
                    {
                        Port outline = (Port) AudioSystem.getLine(source);
                        outline.open();
                         volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                        volumeControl.setValue(volumeBar.getValue()/100f);
                    }
                    catch (LineUnavailableException ex)
                    {
                        System.err.println("source not supported");
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.setFocusable(false);
    }

}
