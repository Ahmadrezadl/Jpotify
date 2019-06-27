import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ExitButton extends JButton implements ActionListener {
    AppObjects appObjects;
    public ExitButton(AppObjects appObjects) {
        System.out.println("Exit Button Start Adding!");
        setBorder(null);
        appObjects.setExitButton(this);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.appObjects = appObjects;
        this.setFocusPainted(false);
        this.setOpaque(false);
        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\exitButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
        this.addActionListener(this);
        this.setBackground(Color.BLACK);
        this.setToolTipText("Exit Program");
        System.out.println("Exit Button Added!");
    }
    public void exit(){
        System.out.println("Exiting Program...");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("playLists.txt"))) {
            for(PlaylistPanel playList : appObjects.getPlaylists())
            {
                bufferedWriter.write(playList.name+"\n");
            }

        } catch (IOException x) {
            // exception handling
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File("musics.txt"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        writer.print("");
        writer.close();

        try(BufferedWriter out = new BufferedWriter(new FileWriter("musics.txt"))) {
            for(PlaylistPanel playList : appObjects.getPlaylists())
            {
                for(SongButton songButton : playList.songs)
                {
                    out.write(playList.name+"\n");
                    out.write(songButton.link + "\n");
                }
            }

        } catch (IOException x) {
            // exception handling
        }
        try(BufferedWriter o = new BufferedWriter(new FileWriter("username.txt"))) {
            o.write(appObjects.getUserName().getText());

        } catch (IOException x) {
            // exception handling
        }
        System.exit(1);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
            exit();
    }
}
