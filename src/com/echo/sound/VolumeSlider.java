/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.sound;

import javax.sound.sampled.FloatControl;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Staff
 */
public class VolumeSlider {

    JSlider volume;
    float val;

    public VolumeSlider(final FloatControl volumeControl, JSlider slider) {
        volume = slider;
        slider = new JSlider(
                (int) volumeControl.getMinimum() * 100,
                (int) volumeControl.getMaximum() * 100,
                (int) volumeControl.getValue() * 100);


        ChangeListener listener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                val = volume.getValue() / 100f;
                //System.out.println("val:" + val);
                volumeControl.setValue(val);
                System.out.println(
                        "Setting volume of " + volumeControl.toString()
                        + " to " + val);
            }
        };
        volume.addChangeListener(listener);
    }

    
}
