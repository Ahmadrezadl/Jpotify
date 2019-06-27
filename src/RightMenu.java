


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RightMenu extends JPanel implements ActionListener {
        DefaultListModel<String> list ;
        JButton btn;
        JLabel label;
        Server server ;
        public RightMenu(AppObjects appObjects) throws IOException {
                label = new JLabel("friends");
                list = new DefaultListModel<>();
                list.addElement("5");
                list.addElement("54654654");
                JList l = new JList<>(list);
                this.setLayout(new BorderLayout());
                this.setBackground(Color.black);
                add(label , BorderLayout.NORTH);
                add(l , BorderLayout.CENTER);
                btn = new JButton("send music");
                add(btn , BorderLayout.SOUTH);
                btn.addActionListener(this);
                appObjects.setRightMenu(this);
                Border border = BorderFactory.createLineBorder(Color.BLACK , 5);
                this.setBorder(border);
                l.setBackground(Color.blue);






        }



        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

        }
}
