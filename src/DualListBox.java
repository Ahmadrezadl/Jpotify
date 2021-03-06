import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

public class DualListBox extends JPanel {

    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);


    private JLabel sourceLabel;

    private JList sourceList;

    private AppObjects appObjects;

    private SortedListModel sourceListModel;

    private SortedListModel destListModel;


    private PlaylistPanel playlistPanel;
    JFrame f;
    public DualListBox(AppObjects appObjects,PlaylistPanel playlistPanel) {
        initScreen();
        this.setBackground(new Color(0x290006));
        this.playlistPanel = playlistPanel;
        f = new JFrame("Add Songs To PlayList");
        f.setBackground(Color.BLACK);
        f.setResizable(false);
        f.setLocation(new Point(100,100));
        f.setVisible(true);
        f.pack();
        this.addSourceElements(playlistPanel.getNames(appObjects.getAllSongsPanel()));
        this.appObjects= appObjects;

        f.getContentPane().add(this, BorderLayout.CENTER);
        f.setSize(500, 500);
        f.setVisible(true);

    }


    public void addSourceElements(Object newValue[]) {
        fillListModel(sourceListModel, newValue);
    }


    public void addDestinationElements(Object newValue[]) {
        fillListModel(destListModel, newValue);
    }
    public void addDestinationElements(Object newValue) {
        fillListModel(destListModel, newValue);
    }

    private void fillListModel(SortedListModel model, Object newValues[]) {
        try{
            model.addAll(newValues);
        }
        catch (NullPointerException e)
        {
            f.setVisible(false);

        }
    }
    private void fillListModel(SortedListModel model, Object newValues) {
        try{
            model.addAll(newValues);
        }
        catch (NullPointerException e)
        {
            f.setVisible(false);

        }
    }

    private void clearSourceSelected() {
        Object selected[] = sourceList.getSelectedValues();
        for (int i = selected.length - 1; i >= 0; --i) {
            sourceListModel.removeElement(selected[i]);
            String n = selected[i].toString();
                for (SongButton s : appObjects.getAllSongsPanel().songs) {
                    if (s.name.equals(n))
                    {
                        playlistPanel.addSong(new SongButton(s.link,s.name,appObjects,playlistPanel));

                    }
                }
        }
        sourceList.getSelectionModel().clearSelection();
    }



    private void initScreen() {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridBagLayout());
        sourceLabel = new JLabel("Available Musics");
        sourceListModel = new SortedListModel();
        sourceList = new JList(sourceListModel);
        sourceList.setBackground(new Color(0x290006));
        sourceList.setForeground(Color.WHITE);
        sourceList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    Object selected = sourceList.getSelectedValue();
                    addDestinationElements(selected);
                    clearSourceSelected();
                }
            }
        });
        add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5,
                1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));



        destListModel = new SortedListModel();


    }


    private class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object selected[] = sourceList.getSelectedValues();
            addDestinationElements(selected);
            clearSourceSelected();

        }
    }


}

