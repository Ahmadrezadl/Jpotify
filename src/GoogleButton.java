
import sun.security.krb5.internal.APOptions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
/**
 * this button builds a button that open a web page to search for lyrics in google.com
 * @author G-squad menhaye maryam
 * @version 1.0
 * @since 1.0
 */

public class GoogleButton extends JButton implements ActionListener {
    AppObjects appObjects;

    public GoogleButton(AppObjects appObjects) {
        super();
        this.appObjects = appObjects;
        this.addActionListener(this);
        this.setFocusable(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setBorder(null);
        setToolTipText("Google This Song");
        try {
            Image pauseButtonIcon = ImageIO.read(getClass().getResource("icons\\googleButton.png"));
            this.setIcon(new ImageIcon(pauseButtonIcon));
        }
        catch (Exception e)
        {
            System.out.println("ERROR");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("http://www.google.com/search?q=" +
                    appObjects.getLastPlayed().getTitle().replaceAll(" " , "%20") + "%20" +
                    appObjects.getLastPlayed().getArtist().replaceAll(" " , "%20")));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
}
