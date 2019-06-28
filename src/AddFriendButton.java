import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFriendButton extends JButton implements ActionListener {
    AppObjects appObjects;

    public AddFriendButton(AppObjects appObjects) {
        this.appObjects = appObjects;
        appObjects.setAddFriendButton(this);
        this.addActionListener(this);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);

        try {
            Image exitButtonIcon = ImageIO.read(getClass().getResource("icons\\addFriendsIcon.png"));

            this.setIcon(new ImageIcon(exitButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("File Missing...");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = JOptionPane.showInputDialog("Enter The UserName of Your Friends: " , "UserName");
        appObjects.addFriend(userName);
    }
}
