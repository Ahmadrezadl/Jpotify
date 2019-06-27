import Server.Server;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightMenu extends JPanel implements ActionListener {
        DefaultListModel<String> list ;
        JButton btn;
        JLabel label;
        public RightMenu(AppObjects appObjects){
                label = new JLabel("friends");
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
                for(int i = 0 ; i < )
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
