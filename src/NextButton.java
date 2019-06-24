import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButton extends JButton implements ActionListener {
    AppObjects appObjects;
    public NextButton(AppObjects appObjects){
        super("test");
        this.appObjects = appObjects;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appObjects.getBottomMenu().playNext();

    }
}
