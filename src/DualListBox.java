import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JList destList;

    private SortedListModel destListModel;

    private JLabel destLabel;

    private JButton addButton;

    private JButton removeButton;

    private PlaylistPanel playlistPanel;

    public DualListBox(AppObjects appObjects,PlaylistPanel playlistPanel) {
        initScreen();
        this.playlistPanel = playlistPanel;
        JFrame f = new JFrame("Add Songs To PlayList");
        f.setBackground(Color.BLACK);
        f.setResizable(false);
        f.setLocation(new Point(100,100));
        f.setVisible(true);
        f.pack();
        this.addSourceElements(appObjects.getAllSongsPanel().getNames());
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

    private void fillListModel(SortedListModel model, Object newValues[]) {
        model.addAll(newValues);
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

    private void clearDestinationSelected() {
        Object selected[] = destList.getSelectedValues();
        for (int i = selected.length - 1; i >= 0; --i) {
            destListModel.removeElement(selected[i]);
        }
        destList.getSelectionModel().clearSelection();
    }

    private void initScreen() {
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridBagLayout());
        sourceLabel = new JLabel("Available Musics");
        sourceListModel = new SortedListModel();
        sourceList = new JList(sourceListModel);
        add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5,
                1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));

        addButton = new JButton("ADD >>");
        add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        addButton.addActionListener(new AddListener());

        destLabel = new JLabel("Added Musics");
        destListModel = new SortedListModel();
        destList = new JList(destListModel);
        add(destLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                EMPTY_INSETS, 0, 0));
        add(new JScrollPane(destList), new GridBagConstraints(2, 1, 1, 5, .5,
                1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                EMPTY_INSETS, 0, 0));
    }


    private class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object selected[] = sourceList.getSelectedValues();
            addDestinationElements(selected);
            System.out.println("wtf");
            clearSourceSelected();

        }
    }

    private class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object selected[] = destList.getSelectedValues();
            addSourceElements(selected);
            clearDestinationSelected();
        }
    }
}

