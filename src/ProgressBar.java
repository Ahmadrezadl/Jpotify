import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JProgressBar {
    public ProgressBar(AppObjects appObjects){
        super();
        this.setStringPainted(true);
        this.setForeground(Color.blue);
        this.setBackground(Color.BLACK);
        this.setValue(0);
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(500,50));
        appObjects.setProgressBar(this);

    }
}