import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenu extends JPopupMenu implements ActionListener {
    AppObjects appObjects;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    ActionListener ee =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==btn1){

            }

        }
    };
    public PopupMenu(AppObjects appObjects){
        super();
        this.appObjects = appObjects;
        btn1 = new JButton("remove") ;
        btn1.addActionListener(ee);
        btn2 = new JButton("play");
        btn1.addActionListener(ee);
        btn3 = new JButton("add to favorite");
        btn1.addActionListener(ee);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.setVisible(true);
        appObjects.setPopupMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
