/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.threads;

import com.echo.gui.EchoPlayer;
import com.echo.sound.PlaySound;
import com.echo.util.AppContants;
import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;

/**
 *
 * @author Staff
 */
public class MyPlayerThread implements Runnable {

    PlaySound ps = null;
    File currentPlaying = null;
    DurationThread durationThread = null;

    public MyPlayerThread(PlaySound playSound, File currentPlaying) {
        this.currentPlaying = currentPlaying;
        this.ps = playSound;
        durationThread = new DurationThread(currentPlaying);
    }

    @Override
    public void run() {

        while (ps.status == BasicPlayer.PLAYING) {

            if (PlaySound.time == AppContants.ELAPSED_TIME_SECONDS_THRESHOLD) {
                System.out.println("inside time check");
                durationThread.insertInfos();


            }
        }

    }

    public void playSong(File currentFile, boolean fingerStatus) {

        ps.play(currentFile);
        if (fingerStatus) {
            new Thread(this).start();
        }
       

    }
}
