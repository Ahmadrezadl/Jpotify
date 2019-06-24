import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePlayListButton extends JButton implements ActionListener {
    ImportMusicButton importMusicButton;
    AppObjects appObjects;
    public DeletePlayListButton(AppObjects appObjects, ImportMusicButton importMusicButton)
    {
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.appObjects = appObjects;
        this.importMusicButton = importMusicButton;

        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\deletePlayListButton.png"));
            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            importMusicButton.removePlayList();
    }
}
