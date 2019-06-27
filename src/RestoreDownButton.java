import sun.awt.WindowClosingListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class RestoreDownButton extends JButton implements ActionListener {
    AppObjects appObjects;

    public RestoreDownButton(AppObjects appObjects) {
        this.appObjects = appObjects;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setToolTipText("Minimize Window");
        try {
            Image minimizeButton = ImageIO.read(getClass().getResource("icons\\restoreButton.png"));
            this.setIcon(new ImageIcon(minimizeButton));
        } catch (Exception e) {
            System.out.println(e);
        }
        this.addActionListener(this);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                appObjects.getExitButton().exit();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appObjects.getMainFrame().setVisible(false);
        JFrame miniFrame = new JFrame("Jpotify");
        miniFrame.add(appObjects.getBottomMenu());
        miniFrame.setSize(1800  , 250);
        miniFrame.setMinimumSize(new Dimension(1800,250));
        miniFrame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent arg0) {
                miniFrame.setVisible(false);
                appObjects.getMainFrame().add(appObjects.getBottomMenu(),BorderLayout.SOUTH);
                appObjects.getMainFrame().setVisible(true);
            }


        });
        miniFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                appObjects.getExitButton().exit();
            }
        });

        miniFrame.setVisible(true);

    }

}
