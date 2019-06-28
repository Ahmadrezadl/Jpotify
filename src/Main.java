

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * make frame & change the LookAndFeel
 * @author G-squad menhaye Maryam
 * @version 1.2
 */
public class Main{
    public static void main(String[]args) throws IOException {
        UIManager.put("ProgressBar.background", Color.BLACK);
        UIManager.put("ProgressBar.foreground", Color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", Color.RED);
        UIManager.put("ProgressBar.selectionForeground", Color.RED);
        System.out.println("Program Started!");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Cannot Load UI",
                    "Error 01",
                    JOptionPane.WARNING_MESSAGE);
        }

        AppObjects appObjects = new AppObjects();
        LoginFrame loginFrame = new LoginFrame(appObjects);
        while (loginFrame.isVisible())
            System.out.println("Waiting for login");
        MainFrame mainFrame = new MainFrame(appObjects);
        for (PlaylistPanel p : appObjects.getPlayLists())
        {
            for(AlbumButton a : p.albums)
            {
                a.setVisible(false);
            }
            for(SongButton s : p.songs)
            {
                s.setVisible(true);
            }
        }
    }
}
