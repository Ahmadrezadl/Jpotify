import javazoom.jl.player.Player;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import sun.awt.shell.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main extends JFrame{
    public static void main(String[]args)
    {

        JFileChooser chooser = new JFileChooser();


        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3 File", "mp3");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            FileInputStream file = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
            Player player = new Player(file);
            player.play();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
