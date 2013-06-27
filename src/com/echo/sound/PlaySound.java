/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.sound;

import com.echo.util.AppContants;
import com.echo.util.AppVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;

/**
 *
 * @author Staff
 */
public class PlaySound extends BasicPlayer implements BasicPlayerListener {

    BasicPlayer basicPlayer;
    BasicController basicController;
    File currentFile = null;
    public int status = -2;
    public static long time = -1;
    double volume = 0.00;
    public long filelength = 0;

    public PlaySound() {
        basicPlayer = new BasicPlayer();
        // basicController = (BasicController) basicPlayer;
    }

    @Override
    public void opened(Object o, Map map) {
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
        time = l / (1000 * 1000);
        
    //System.out.println(AppContants.ELAPSED_TIME_SECONDS_THRESHOLD);



    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
    }

    @Override
    public void setController(BasicController bc) {
    }

    public void play(File playFile) {
        try {

            basicController = (BasicController) basicPlayer;
            basicPlayer.addBasicPlayerListener(this);
            basicController.open(playFile);
            currentFile = playFile;
            basicController.play();
            basicController.setGain(0.5);
            status = basicPlayer.getStatus();




        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setVolume(double volumeSliderValue) {

        if (basicController != null) {
            try {
                basicController.setGain(.050 + volumeSliderValue);
            } catch (BasicPlayerException ex) {
                JOptionPane.showMessageDialog(null, "Volume changing error:" + ex.getMessage());
            }
        }
    }

    public void pauseSong() {
        try {
            if (basicController != null) {
                System.out.println("inside pause song ()");
                basicController.pause();

                status = basicPlayer.getStatus();
            }

        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopSong() {
        try {
            if (basicController != null) {
                System.out.println("inside stop song()");
                basicController.stop();

                status = basicPlayer.getStatus();


            }
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resumeSong() {
        try {
            if (basicController != null) {
                System.out.println("inside resume song()");
                basicController.resume();
                status = basicPlayer.getStatus();

            }
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTimeInSeconds(File currentPlaying) {
        try {
            MP3AudioHeader audioHeader = new MP3AudioHeader(currentPlaying);

            String trackLengthAsString = audioHeader.getTrackLengthAsString();
            String[] splitTimes = trackLengthAsString.split(":");
            int timeInMinToSec = Integer.parseInt(splitTimes[0]) * 60;
            int timeInSec = Integer.parseInt(splitTimes[1]);
            int total_Time_In_SEC = 0;
            if (timeInSec != 0) {
                total_Time_In_SEC = timeInMinToSec + timeInSec;
                AppVariables.TOTAL_DURATION = total_Time_In_SEC;
                return total_Time_In_SEC;
            } else {
                total_Time_In_SEC = timeInMinToSec;
                AppVariables.TOTAL_DURATION = total_Time_In_SEC;
                return total_Time_In_SEC;
            }

        } catch (IOException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void seekSlider() {

        try {
            System.out.println("inside seekSlider");
            if (basicController != null) {

                basicController.seek((long) PlaySound.time);
                status = basicPlayer.getStatus();
            }
        } catch (BasicPlayerException ex) {
            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public void seekMedia(long value) {

        
        
//        try {
//            System.out.println("inside seekSlider");
//            //long skipBytes = Math.round( new FileInputStream(currentFile).read() * posValue); 
//            
//            if (basicController != null) {
//                //long seek = basicController.seek(skipBytes(PlaySound.time));
//                System.out.println("skipByte:" + skipBytes(PlaySound.time));
//                status = basicPlayer.getStatus();
//            }
//        } catch (BasicPlayerException ex) {
//            Logger.getLogger(PlaySound.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      

        try {

            basicController.seek(value * 10000L);

        } catch (BasicPlayerException ex) {
            ex.printStackTrace();
        }
    }
}
