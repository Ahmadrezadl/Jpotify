import javax.swing.*;
import java.awt.*;

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        super();
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}