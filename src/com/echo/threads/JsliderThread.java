/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.threads;

import com.echo.bean.TrackMetaInfoBean;
import com.echo.sound.PlaySound;
import com.echo.util.AppVariables;
import javax.swing.JSlider;
import javazoom.jlgui.basicplayer.BasicPlayer;

/**
 *
 * @author Staff
 */
public class JsliderThread implements Runnable {

    public PlaySound playSound;
    TrackMetaInfoBean metaInfoBean;
    JSlider jSlider;
    double sliderValue = 0.0;

    public JsliderThread(PlaySound playSound, TrackMetaInfoBean metaInfoBean, JSlider slider) {
        this.playSound = playSound;
        this.metaInfoBean = metaInfoBean;
        this.jSlider = slider;
    }

    @Override
    public void run() {

        while (playSound.status == 0) {
            jSlider.setValue((int) PlaySound.time);
            if (playSound.status == BasicPlayer.STOPPED) {
                jSlider.setValue(0);
            }
            if (PlaySound.time == AppVariables.TOTAL_DURATION) {
                System.out.println("True inside time check of total duration");
                AppVariables.STATUS = "COMPLETE";

            }
        }


    }
}
