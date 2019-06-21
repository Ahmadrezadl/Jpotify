import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;

/**
 * It holds all the components we've made so far such as buttons , playlist tabs and... .
 * @author G-squad menhaye Maryam
 * @version 1.0
 */

public class AppObjects {

    private AddPlayListButton addPlayListButton;
    private MainFrame mainFrame;
    private BottomMenu bottomMenu;
    private LeftMenu leftMenu;
    private CenterMenu centerMenu;
    private PlaylistTabs playlistTabs;
    private TopMenu topMenu;
    private PlaylistPanel allSongsPanel;
    private VolumeBar volumeBar;
    private JLabel percent;
    private AdvancedPlayer player;

    public void setPlayer(AdvancedPlayer player) {
        this.player = player;
    }

    public AdvancedPlayer getPlayer() {
        return player;
    }

    public void setVolumeBar(VolumeBar volumeBar) {
        this.volumeBar = volumeBar;
    }

    public void setPercent(JLabel percent) {
        this.percent = percent;
    }

    public VolumeBar getVolumeBar() {
        return volumeBar;
    }

    public JLabel getPercent() {
        return percent;
    }

    public PlaylistPanel getAllSongsPanel() {
        return allSongsPanel;
    }

    public void setAllSongsPanel(PlaylistPanel allSongsPanel) {
        this.allSongsPanel = allSongsPanel;
    }

    public AddPlayListButton getAddPlayListButton() {
        return addPlayListButton;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public BottomMenu getBottomMenu() {
        return bottomMenu;
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public CenterMenu getCenterMenu() {
        return centerMenu;
    }

    public PlaylistTabs getPlaylistTabs() {
        return playlistTabs;
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public void setLeftMenu(LeftMenu leftMenu) {
        this.leftMenu = leftMenu;
    }

    public void setCenterMenu(CenterMenu centerMenu) {
        this.centerMenu = centerMenu;
    }

    public void setPlaylistTabs(PlaylistTabs playlistTabs) {
        this.playlistTabs = playlistTabs;
    }

    public void setTopMenu(TopMenu topMenu) {
        this.topMenu = topMenu;
    }

    public void setBottomMenu(BottomMenu bottomMenu) {
        this.bottomMenu = bottomMenu;
    }

    public void setAddPlayListButton(AddPlayListButton addPlayListButton) {
        this.addPlayListButton = addPlayListButton;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
