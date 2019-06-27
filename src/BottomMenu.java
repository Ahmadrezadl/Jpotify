import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import mpatric.mp3agic.ID3v2;
import mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class BottomMenu extends JPanel implements Runnable{
    JLabel cover;
    AdvancedPlayer player;
    boolean isPlaying;
    FileInputStream music;
    PauseButton pauseButton;
    JLabel fileName;
    Thread t1;
    VolumeBar volumeBar;
    JLabel percent;
    AppObjects appObjects;
    Mp3File song;
    String filePath;
    int p;
    boolean shuffle;
    long lastFrame;
    Color purple = new Color(0x000000);
    public BottomMenu(AppObjects appObjects) {
        super();
        lastFrame = 0;
        shuffle = false;
        this.appObjects = appObjects;
        appObjects.setBottomMenu(this);
        VolumeBar volumeBar = new VolumeBar(appObjects);
        appObjects.setVolumeBar(volumeBar);
        percent = new JLabel(volumeBar.getValue() + "%");
        appObjects.setPercent(percent);
        percent.setForeground(Color.WHITE);
        System.out.println("Bottom Menu Start Creating...");
        this.setLayout(new GridLayout(1,3));
        JPanel playPanel = new JPanel();
        JPanel panelLeft = new JPanel();
        this.setBackground(purple);
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout());
        panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        playPanel.setLayout(new GridBagLayout());
        playPanel.setBackground(Color.BLACK);
        JPanel sliderPanel = new JPanel();
        panelCenter.add(sliderPanel,BorderLayout.SOUTH);
        panelCenter.add(playPanel,BorderLayout.CENTER);
        panelLeft.setBackground(purple);
        panelCenter.setBackground(Color.BLACK);
        panelLeft.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        playPanel.setBackground(Color.BLACK);
        ProgressBar progressBar = new ProgressBar(  appObjects);
        sliderPanel.add(progressBar);
        sliderPanel.setBackground(Color.BLACK);
        this.add(panelLeft);
        this.add(panelCenter);

        cover = new JLabel();
        try {
            Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
            cover.setIcon(new ImageIcon(coverImage));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        playPanel.add(new PreviousButton(appObjects));
        pauseButton = new PauseButton(t1,appObjects);
        playPanel.add(pauseButton);
        NextButton nextButton = new NextButton(appObjects);
        playPanel.add(nextButton);

        System.out.println("New Pause Button Added");
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        this.add(panelRight);
        panelRight.setBackground(purple);
        JPanel volumePanel = new JPanel();
        panelRight.add(volumePanel,BorderLayout.EAST);
        volumePanel.setBackground(purple);
        volumePanel.setLayout(new FlowLayout());
        volumePanel.add(percent);volumePanel.add(volumeBar);
        isPlaying = false;
        Border border = BorderFactory.createLineBorder(Color.BLACK , 10);
        this.setBorder(border);
        fileName = new JLabel("<html>Title: <br> Artist: <br>Album:  <br>Year: <br>Genre: <br>Duration:</html>");
        fileName.setFont(new Font("Serif", Font.PLAIN, 20));
        fileName.setForeground(Color.WHITE);
        panelLeft.add(cover);panelLeft.add(fileName);

        System.out.println("Bottom Menu Added!");

    }
    public void setMusic(BufferedInputStream in)
    {
        try {
            if(!pauseButton.isPlaying)
            {
                System.out.println("Music Resumed");
                isPlaying = true;
            }
            player.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            player = new AdvancedPlayer(in);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        appObjects.setPlayer(player);
        t1 =new Thread(this);
        t1.start();
        fileName.setText("<html>Title: " + "I Love Radio" +
                "<br>Online Stream" +
                "<br>Language: English<br><br><br> "  + "</html>");
        Image coverImage = null;
        try {
            coverImage = ImageIO.read(getClass().getResource("icons\\iloveradio.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cover.setIcon(new ImageIcon(coverImage));
        pauseButton.setT1(t1);
        appObjects.getProgressBar().setVisible(false);

    }
    public void setMusic(String filePath,int p)
    {
        this.p = p;
        this.filePath = filePath;
        System.out.println("Loading Music Descriptions...");
        try {
             music = new FileInputStream(filePath);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("File Created!");
        try {
            if(!pauseButton.isPlaying)
            {
                System.out.println("Music Resumed");
                isPlaying = true;
                pauseButton.isPlaying = true;
            }
            player.close();
//            t1.interrupt();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Last File Closed!");
        try {
            song = new Mp3File(filePath);
            appObjects.setPlayingMusic(filePath);
            player = new AdvancedPlayer(music);
            appObjects.setPlayer(player);
            t1 =new Thread(this);
            t1.start();
            if(song.hasId3v1Tag()) {
                ID3v2 songTag = song.getId3v2Tag();
                byte[] imageData = songTag.getAlbumImage();
                if(imageData != null) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                    ImageIcon cov = new ImageIcon(img);
                    Image image = cov.getImage();
                    Image newimg = image.getScaledInstance(166 , 168 , java.awt.Image.SCALE_SMOOTH);
                    cov = new ImageIcon(newimg);
                    cover.setIcon(cov);

                }
                else
                {
                    Image coverImage = null;
                    try {
                        coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cover.setIcon(new ImageIcon(coverImage));
                }
                System.out.println("Cover Added!");
                fileName.setText("<html>Title: " + songTag.getTitle() +
                        "<br>Artist:  " + songTag.getAlbumArtist() +
                        "<br>Album: " + songTag.getAlbum() +
                        "<br>Year: " + songTag.getYear() +
                        "<br>Genre: " + songTag.getGenreDescription() +
                        "<br>Duration: " + convertTime((int)song.getLengthInSeconds()) + "</html>");

            }
            else{
                Image coverImage = ImageIO.read(getClass().getResource("icons\\musicCover.png"));
                cover.setIcon(new ImageIcon(coverImage));
                fileName.setText("<html>Title: " + "No Title" +
                        "<br>Artist:  " + "Unknown Artist" +
                        "<br>Album: " + "Single" +
                        "<br>Year: " + "xxxx" +
                        "<br>Genre: " + "Music" +
                        "<br>Duration: " + convertTime((int)song.getLengthInSeconds()) + "</html>");
            }
            System.out.println("All Dv3v2 Setup");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Music Descriptions Loaded!");
        pauseButton.setT1(t1);

    }
    public void playNext()
    {
        SongButton willPLaying = null;
//        if(shuffle)
//        {
//
//        }
//        else
        {
            SongButton[] temp = appObjects.getLastPlayed().playlistPanel.getSongs();
            int j;
            for(j = 0; j < appObjects.getLastPlayed().playlistPanel.songs.size();j++)
            {
                if((temp[j].link.equals(appObjects.getLastPlayed().link)))
                {
                    break;
                }
            }
            for(int i = j; i < appObjects.getLastPlayed().playlistPanel.songs.size();i++)
            {
                        if(!(temp[i].link.equals(appObjects.getLastPlayed().link)))
                        {

                            willPLaying = temp[i];//System.out.println(willPLaying.name);
                            break;
                        }
            }
            willPLaying.play();


        }
    }
    public void playPrevious(){
        SongButton willPLaying = null;
//        if(shuffle)
//        {
//
//        }
//        else
        {
            SongButton[] temp = appObjects.getLastPlayed().playlistPanel.getSongs();
            int j;
            for(j = appObjects.getLastPlayed().playlistPanel.songs.size()-1; j > -1 ;j--)
            {
                if((temp[j].link.equals(appObjects.getLastPlayed().link)))
                {
                    break;
                }
            }
            for(int i = j; i > -1;i--)
            {
                if(!(temp[i].link.equals(appObjects.getLastPlayed().link)))
                {

                    willPLaying = temp[i];//System.out.println(willPLaying.name);
                    break;
                }
            }
            willPLaying.play();


        }
    }

    public String convertTime(int t){
        int m = 0;
        int s = t;
        while(s > 59)
        {
            m++;
            s += -60;
        }
        if(s > 9 && m>9)
        return "" + m + ":" + s;
        if(s> 9 && m <10)
            return ""+m+":"+s;
        if(s < 10 && m < 10)
            return "" + m + ":" + "0" + s;
        return ""+ m + ":" + "0" + s;

    }


    @Override
    public void run() {
        try {
            if(p == 0)
            {
                appObjects.getProgressBar().progressBarVal = 0;
            }
            double start = p;
            pauseButton.isPlaying = true;
            System.out.println("Playing Music...");
            double positionPercent;
//            System.out.println(start);
            player.play(1);
            for(int i = 0;i<p;i++) {
                player.skipFrame();
            }
            while(player.play(1))
            {
                try{
                if(appObjects.getProgressBar().isVisible()) {

                    positionPercent = (((((double) player.getPosition()) / 1000) / song.getLengthInSeconds()) * 100);
                    appObjects.getProgressBar().setValue((int) positionPercent+appObjects.getProgressBar().progressBarVal);


                   appObjects.getProgressBar().setString("" + convertTime((int)((((positionPercent+appObjects.getProgressBar().progressBarVal)*song.getLengthInSeconds())/100))));
                }
                else
                {
                    appObjects.getProgressBar().setString("Live Stream!");
                }
                while(!pauseButton.isPlaying)
                {
                    System.out.println("Music Paused");
                }}
                catch (NullPointerException e)
                {
                    e.printStackTrace();
                }


            }
//            System.out.println(appObjects.isSongMoved());
//            if(appObjects.isSongMoved())
//            {
//                appObjects.setSongMoved(false);
//            }
//            else
//            {
//                playNext();
//            }

            System.out.println("Music ended!");
            appObjects.getProgressBar().setValue(100);
            appObjects.getProgressBar().setString(convertTime((int)song.getLengthInSeconds()));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

}
