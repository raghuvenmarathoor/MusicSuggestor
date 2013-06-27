/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;


public class ControlComponent extends JPanel implements BasicPlayerListener{
	private static final long serialVersionUID = 1L;
	
	public static final String PLAY_BTN_ACTION_COMMAND = "Play";
	public static final String PAUSE_BTN_ACTION_COMMAND = "Pause";
	public static final String STOP_BTN_ACTION_COMMAND = "Stop";
	public static final String FF_BTN_ACTION_COMMAND = "FF";
	public static final String RW_RTP_BTN_ACTION_COMMAND = "RW";
	
	public static int changedGainValue = -1;
	
	private ImageIcon imgPlay = new ImageIcon("./res/play.png");
	private ImageIcon imgPause = new ImageIcon("./res/pause.png");
	private ImageIcon imgStop = new ImageIcon("./res/stop.png");
	private ImageIcon imgPrev = new ImageIcon("./res/prev.png");
	private ImageIcon imgNext = new ImageIcon("./res/next.png");
	private ImageIcon imgList = new ImageIcon("./res/list.png");

	private DalSongPlayer dsp = null;

	private BasicPlayer player;

	private JProgressBar jProgbar = null;
	private JSlider volume = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton21 = null;
	
	private Dimension comSize = new Dimension(35,20);

	private int tagSize = 0;
	private long duration = 0L;
	private int byteslength;
	private int termVar = 0;
	
	public ControlComponent(DalSongPlayer dsp){
		this.dsp = dsp;
		FlowLayout flowLayout1 = new FlowLayout();
		flowLayout1.setAlignment(java.awt.FlowLayout.LEFT);
		flowLayout1.setVgap(0);
		flowLayout1.setHgap(0);

		this.setLayout(new BorderLayout());
//		this.setPreferredSize(new Dimension(300,20));

		
		
		setBackground(null);
		
		initComponent();

	}
	
	public void initControl(){
		// Instantiate BasicPlayer.
		player = new BasicPlayer();

		
		// Register BasicPlayerTest to BasicPlayerListener events.
		// It means that this object will be notified on BasicPlayer
		// events such as : opened(...), progress(...), stateUpdated(...)
		player.addBasicPlayerListener(this);
		
	}
	
	public void initComponent(){

		JPanel westPane = new JPanel();
		westPane.setLayout(new GridLayout());
		
		westPane.add(getJButton());
		westPane.add(getJButton1());
		westPane.add(getJButton2());
		westPane.add(getJButton3());
		westPane.add(getPlayListBtn());
		
		
		add(westPane, BorderLayout.WEST);
		add(getJProgBar(), BorderLayout.CENTER);
		add(getJSlider2(), BorderLayout.EAST);
	}
	
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setIcon(imgPlay);	//ÀÌ¹ÌÁö¸¦ ¹öÆ°¿¡ Ãß°¡
			jButton.setToolTipText("Àç»ý");
			jButton.setPreferredSize(comSize);
			jButton.setMargin(new Insets(0, 0, 0, 0));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					playORpause();
				}
			});
		}
		return jButton;
	}
	

	public void playORpause(){
		if(player.getStatus() == BasicPlayer.PLAYING){
			try{
				player.pause();
				jButton.setIcon(imgPlay);
				jButton.setToolTipText("Àç»ý");
			} catch(BasicPlayerException e){
				e.printStackTrace();
			}
		} else if(player.getStatus() == BasicPlayer.PAUSED){
			try{
				player.resume();
				setVolume();
				jButton.setIcon(imgPause);
				jButton.setToolTipText("ÀÏ½ÃÁ¤Áö");
			} catch(BasicPlayerException e){
				e.printStackTrace();
			}
		} else{
			try{
				dsp.setSyncCntlInit();
				
				player.play();
				setVolume();
				
				jButton.setIcon(imgPause);
				jButton.setToolTipText("ÀÏ½ÃÁ¤Áö");
			} catch(BasicPlayerException e){
				e.printStackTrace();
			}
		}
	}

	private void setVolume() throws BasicPlayerException{
		if(changedGainValue == -1){
			player.setGain(0.7);
		}else{
			player.setGain((double)changedGainValue/100);
		}
	}
	
	public void setVolume(double t){
		try{
			player.setGain(player.getGainValue()+t);
		}catch(BasicPlayerException e){}
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setIcon(imgStop);
			jButton1.setToolTipText("Á¤Áö");
			jButton1.setPreferredSize(comSize);
			jButton1.setMargin(new Insets(0, 0, 0, 0));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						player.stop();
						player.seek(0);
						
						jProgbar.setValue(0);
						jProgbar.setString("- / -");
						
						jButton.setIcon(imgPlay);
						jButton.setToolTipText("Àç»ý");
					} catch(BasicPlayerException ex){
						ex.printStackTrace();
					}
					
				}
			});
		}
		return jButton1;
	}
	
	/**
	 * This method initializes jButton2	
	 * 	¡ì¡í
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setIcon(imgPrev);
			jButton2.setToolTipText("ÀÌÀü°î");
			jButton2.setPreferredSize(comSize);
			jButton2.setMargin(new Insets(0, 0, 0, 0));
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//dsp.startMedia((dsp.getPlayList()).getPrevSongFile());
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setIcon(imgNext);
			jButton3.setToolTipText("´ÙÀ½°î");
			jButton3.setPreferredSize(comSize);
			jButton3.setMargin(new Insets(0, 0, 0, 0));
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//dsp.startMedia((dsp.getPlayList()).getNextSongFile());
				}
			});
		}
		return jButton3;
	}
	private JButton getPlayListBtn() {
		if (jButton21 == null) {
			jButton21 = new JButton();
			jButton21.setIcon(imgList);
			jButton21.setToolTipText("ÇÃ·¹ÀÌ¸®½ºÆ®");
			jButton21.setPreferredSize(comSize);
			jButton21.setMargin(new Insets(0, 0, 0, 0));
			jButton21.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if(dsp.getPlayList().isVisible())
//						dsp.getPlayList().setVisible(false);
//					else
//						dsp.getPlayList().showThisList();
				}
			});
		}
		return jButton21;
	}
	
	private JProgressBar getJProgBar(){
		if (jProgbar == null) {
			jProgbar = new JProgressBar();
			jProgbar.setPreferredSize(new Dimension(200,15));
			jProgbar.setBackground(null);
			jProgbar.setString("- / -");
			jProgbar.setStringPainted(true);
			jProgbar.setMinimum(0);

			jProgbar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					
//					System.out.println("bar max : " + jProgbar.getMaximum());
//					System.out.println("bar width : " + jProgbar.getWidth());
//					System.out.println("mouse clicked : " + e.getX());
			
					long value = (e.getX() * jProgbar.getMaximum()) / jProgbar.getWidth();
					
					jProgbar.setValue((int)value);
					try{
						
						player.seek( value*10000L );
						setVolume();
					} catch(BasicPlayerException ex){ex.printStackTrace();}
				}

			});
			
		}
		
		return jProgbar;
	}
	
	private JSlider getJSlider2() {
		if (volume == null) {
			volume = new JSlider(JSlider.HORIZONTAL);
			volume.setPreferredSize(new Dimension(50,20));
//			volume.setBackground(null);
			volume.setBorder(BorderFactory.createEtchedBorder());
			volume.setPaintTicks(false);
			volume.setMaximum(100);
			
			if(changedGainValue == -1){
				volume.setValue(70);
			}else{
				volume.setValue(changedGainValue);
			}

			// »ç¿ëÀÚÀÇ º¼·ý Á¶ÀýÀ» ¹Þ´Â´Ù.
			volume.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					changedGainValue = volume.getValue();
					try{
						player.setGain((double)changedGainValue/100);
					} catch(BasicPlayerException ex){ ex.printStackTrace(); }
				}
			});
		}
		return volume;
	}
	
		
	public void mediaTimeChanged(long bytesread, long mediatime, long duration){
		int dura, mTime;
		
		dura = Math.round((float)duration/1000);
		mTime = Math.round((float)mediatime/1000);
		
		

//		System.out.print("test dura : " + dura);
//		System.out.println("   test mtime : " + mTime);
		
		try {
			int maxProg = (int)(byteslength / 10000);
			int valueProg = (int)(bytesread / 10000);
			
			jProgbar.setMaximum(maxProg);
			jProgbar.setValue(valueProg);
			jProgbar.setString(getTimeNormlType(mTime) + " / " + getTimeNormlType(dura));
		} catch(Exception e){
			
		}
//		System.out.println("Unrealized  : " + control.getPlayer().Unrealized);
//		System.out.println("Realizing  : " + control.getPlayer().Realizing);
//		System.out.println("Realized  : " + control.getPlayer().Realized);
//		System.out.println("Prefecing  : " + control.getPlayer().Prefetching);
//		System.out.println("Prefetched  : " + control.getPlayer().Prefetched);
//		System.out.println("startd  : " + control.getPlayer().Started);
		
//		System.out.println("Prefecing  : " + control.getPlayerState());
		
	}
    
	private String getTimeNormlType(int sec){
    	int s;
    	int m;
    	int h;
    	
    	String timeStr;

   	
    	s = (int)(sec%60);
    	m = (int)((sec/60)%60);
    	h = (int)(sec/3600);
    	    	
    	if(h != 0){
    		timeStr = String.format("%02d:%02d:%02d", h, m, s);
    	} else {
    		timeStr = String.format("%02d:%02d", m, s);
    	}
		
		return timeStr;
    }

	public void loadFile(File f, int tagSize, long duration){
		try{
			this.duration = duration;
			this.tagSize = tagSize;
			player.open(f);

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Open callback, stream is ready to play.
	 *
	 * properties map includes audio format dependant features such as
	 * bitrate, duration, frequency, channels, number of frames, vbr flag,
	 * id3v2/id3v1 (for MP3 only), comments (for Ogg Vorbis), ... 
	 *
	 * @param stream could be File, URL or InputStream
	 * @param properties audio stream properties.
	 */
	public void opened(Object stream, Map properties)
	{
		// Pay attention to properties. It's useful to get duration, 
		// bitrate, channels, even tag such as ID3v2.
//		display("opened : "+properties.toString());		

		byteslength = ((Integer) properties.get("audio.length.bytes")).intValue();
//		System.out.println("lenghth : " + byteslength);
		
//		duration = (Long)properties.get("duration");
//		duration = getTimeLengthEstimation(properties);
		
		
		
//		System.out.println("dura : " + (Long)properties.get("duration"));
//		System.out.println("dura2 : " + duration);
		
	
		
//		jProgbar.setMaximum( byteslength );
		
		// º¼·ýÁ¶Àý ÃÊ±âÈ­
//		volumn.setValue((int)(player.getGainValue()));
	}
		
	/**
	 * Progress callback while playing.
	 * 
	 * This method is called severals time per seconds while playing.
	 * properties map includes audio format features such as
	 * instant bitrate, microseconds position, current frame number, ... 
	 * 
	 * @param bytesread from encoded stream.
	 * @param microseconds elapsed (<b>reseted after a seek !</b>).
	 * @param pcmdata PCM samples.
	 * @param properties audio stream parameters.
	 */
	public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties)
	{
		// Pay attention to properties. It depends on underlying JavaSound SPI
		// MP3SPI provides mp3.equalizer.
//		display("progress : "+properties.toString());

		if(termVar%10 == 0){

			float progress = (bytesread-tagSize) * 1.0f / byteslength * 1.0f;
			long secondsAmount = (long) (duration * progress);

			
			mediaTimeChanged(bytesread, secondsAmount, duration);
			dsp.refreshLyricPane(secondsAmount, duration);
			
//			System.out.println("bytes : " + bytesread);
//			System.out.println("prog : " + progress);
//			System.out.println("sec : " + secondsAmount);

			termVar=0;
		}
		termVar++;
	}
	
	/**
	 * Notification callback for basicplayer events such as opened, eom ...
	 *  
	 * @param event
	 */
	public void stateUpdated(BasicPlayerEvent event)
	{

		// Notification of BasicPlayer states (opened, playing, end of media, ...)
//		display("stateUpdated : "+event.toString());
		
		if(event.getCode()==BasicPlayerEvent.EOM){
			dsp.nextMedia();
		} else if (event.getCode()==BasicPlayerEvent.SEEKED){
			dsp.setValInit();
		} else if (event.getCode()==BasicPlayerEvent.STOPPED)
		{
			
		}
	}

	/**
	 * A handle to the BasicPlayer, plugins may control the player through
	 * the controller (play, stop, ...)
	 * @param controller : a handle to the player
	 */	
	public void setController(BasicController controller)
	{
		display("setController : "+controller);
	}
	
	public void display(String msg)
	{
		System.out.println(msg);
	}
	
	 /**
     * Try to compute time length in milliseconds.
     * @param properties
     * @return
     */

	/*
	public long getTimeLengthEstimation2(Map properties)
    {
        int bps = -1;
        long bitlength = -1;
        double msec;

        bitlength = byteslength*8;
        
//        System.out.println("bitlength : " + bitlength);
        
        bps = ((Integer) properties.get("mp3.bitrate.nominal.bps")).intValue();
        System.out.println("bps : " + bps);
        
        bps = (bps /1000) * 1024;
//        System.out.println("brate : " + bps);
        
        msec = ((double)bitlength / (double)bps) * 1000;
        
        System.out.println("msec : " + msec);
        
        return (long)msec + 5000;
    }

    public long getTimeLengthEstimation(Map properties)
    {
        long milliseconds = -1;
        int byteslength = -1;
        if (properties != null)
        {
            if (properties.containsKey("audio.length.bytes"))
            {
                byteslength = ((Integer) properties.get("audio.length.bytes")).intValue();
            }
            if (properties.containsKey("duration"))
            {
                milliseconds = (int) (((Long) properties.get("duration")).longValue()) / 1000;
            }
            else
            {
                // Try to compute duration
                int bitspersample = -1;
                int channels = -1;
                float samplerate = -1.0f;
                int framesize = -1;
                if (properties.containsKey("audio.samplesize.bits"))
                {
                    bitspersample = ((Integer) properties.get("audio.samplesize.bits")).intValue();
                }
                if (properties.containsKey("audio.channels"))
                {
                    channels = ((Integer) properties.get("audio.channels")).intValue();
                }
                if (properties.containsKey("audio.samplerate.hz"))
                {
                    samplerate = ((Float) properties.get("audio.samplerate.hz")).floatValue();
                }
                if (properties.containsKey("audio.framesize.bytes"))
                {
                    framesize = ((Integer) properties.get("audio.framesize.bytes")).intValue();
                }
                if (bitspersample > 0)
                {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * channels * (bitspersample / 8)));
                }
                else
                {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * framesize));
                }
            }
        }
        return milliseconds - 1000;
    }
	*/
	
 }