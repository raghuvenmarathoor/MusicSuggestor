/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

/**
 *
 * @author Staff
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Map;



import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

//import com.jgoodies.looks.plastic.*;
//import com.jgoodies.looks.plastic.theme.*;
//
//import dalsong.engine.Lyric;
//import dalsong.mp3info.EncodingInfo;
//import dalsong.mp3info.Mp3InfoReader;
//import dalsong.playlist.PlayListManager;
//import dalsong.util.JFontChooser;
//import dalsong.util.Utility;

public class DalSongPlayer extends JFrame implements  ActionListener {
	private static final long serialVersionUID = 1L;
	public static final int ENGLISH_MODE = 0;
	public static final int KOREAN_MODE = 1;
	
	
	
	private String TITLE_BAR_TEXT="";
	private String PLAYLIST_TITLE_BAR_TEXT="";
	
	private String MENU_FILE="";
	private String MENU_OPTION="";
	private String MENU_THEME="";
	private String MENU_HELP="";
	
	private String OPEN_MENU_ACTION_COMMAND = "";
	private String PLAYLIST_MENU_ACTION_COMMAND = "";
	private String EXIT_MENU_ACTION_COMMAND = "";
	private String OPT_MINI_ACTION_COMMAND = "";
	private String OPT_LYRIC_ACTION_COMMAND = "";
	private String OPT_SYNC_ACTION_COMMAND = "";
	public static String OPT_FONT_ACTION_COMMAND = "";
	private final String THMEME_ACTION_COMMAND[] = {
															"BrownSugar",
															"DarkStar",
															"DesertBlue",
															"DesertBluer",
															"DesertGreen",
															"DesertRed",
															"DesertYellow",
															"ExperienceBlue",
															"ExperienceGreen",
															"ExperienceRoyale",
															"LightGray",
															"Silver",
															"SkyBlue",
															"SkyBluer",
															"SkyGreen",
															"SkyKrupp",
															"SkyPink",
															"SkyRed",
															"SkyYellow"};
	
	public static String ABOUT_DLG_ACTION_COMMAND = "";
	
	public static String POPUP_MENU_DELETE = "";
	public static String POPUP_MENU_INFORMATION = "";
	
	public static String TAG_TRACK = "";
	public static String TAG_TITLE = "";
	public static String TAG_ARTIST = "";
	public static String TAG_ALBUM = "";
	public static String TAG_YEAR = "";
	public static String TAG_GENRE = "";
	public static String TAG_COMMENT = "";
	public static String TAG_COMPOSER = "";
	public static String TAG_ORIGINAL_ARTIST = "";
	public static String TAG_COPYRIGHT = "";
	public static String TAG_URL = "";
	public static String TAG_ENCODED_BY = "";
		
//	private JFontChooser fontChooser;
	public JFileChooser fileDlg;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("´Þ½ö ÆÄÀÏ(*.mp3)", "mp3");
//	private Font lyricFont = new Font("arial", Font.PLAIN, 12);
	
//	private EncodingInfo ei = new EncodingInfo();
//	private Mp3InfoReader ir = new Mp3InfoReader();
//	private Lyric lyrc = new Lyric();
//	
//	private PlayListManager playlist = null;	
	private ControlComponent controlCmpnt = null;
	
	private int stateTheme;
	private String stateDirectiory;

	private boolean isVisiblePlayList = true;
	
	
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
//	private LyricPane lyricPane = new LyricPane();
	private JLabel jLabel_SongTitle = null;
	private JDialog controlSyncDlg = null;
	private JLabel syncLabel = null;


	private String tempMp3FileName;
	private int sync = 0;
	
	private int tagsize = 0;
	private long duration = 0;
	
	private int languageMode;
	
	public DalSongPlayer(){
		super();
		
		setInitState();
		
		setTitle(TITLE_BAR_TEXT);
		setMenuBar();
		setListener();
		
		this.setSize(320, 400);
		this.setMinimumSize(new Dimension(232,97));
		this.setContentPane(getJContentPane());
		//set icon
		Image img = Toolkit.getDefaultToolkit().getImage("./res/moon.png");
		setIconImage( img );
	
		
		this.setVisible(true);
		
		//getPlayList().setVisible(true);
		getControlSyncDlg();
		
		controlCmpnt.initControl();
			
		
//		fontChooser = new JFontChooser(this);
	}
	
//	private void setLanguage(int mode){
//		languageMode = mode;
//		
//		Map<String, String> menuTextMap = Utility.getTextData(mode);
//		
//		TITLE_BAR_TEXT = menuTextMap.get("titleBarText");
//		PLAYLIST_TITLE_BAR_TEXT = menuTextMap.get("playListTitleBarText");
//		
//		MENU_FILE = menuTextMap.get("menuFile");
//		MENU_OPTION = menuTextMap.get("menuOption");
//		MENU_THEME = menuTextMap.get("menuTheme");
//		MENU_HELP = menuTextMap.get("menuHelp");
//		
//		OPEN_MENU_ACTION_COMMAND = menuTextMap.get("menuItemOpen");
//		PLAYLIST_MENU_ACTION_COMMAND = menuTextMap.get("menuItemPlaylist");
//		EXIT_MENU_ACTION_COMMAND = menuTextMap.get("menuItemExit");
//		OPT_MINI_ACTION_COMMAND = menuTextMap.get("menuItemMiniMode");
//		OPT_LYRIC_ACTION_COMMAND = menuTextMap.get("menuItemLyricMode");
//		OPT_SYNC_ACTION_COMMAND = menuTextMap.get("menuItemSync");
//		OPT_FONT_ACTION_COMMAND = menuTextMap.get("menuItemFont");
//		
//		ABOUT_DLG_ACTION_COMMAND = menuTextMap.get("menuItemAbout");
//		
//		POPUP_MENU_DELETE = menuTextMap.get("popupMenuDelete");
//		POPUP_MENU_INFORMATION = menuTextMap.get("popupMenuInformation");
//		
//		TAG_TRACK = menuTextMap.get("tagTrack");
//		TAG_TITLE =  menuTextMap.get("tagTitle");
//		TAG_ARTIST =  menuTextMap.get("tagArtist");
//		TAG_ALBUM = menuTextMap.get("tagAlbum");
//		TAG_YEAR =  menuTextMap.get("tagYear");
//		TAG_GENRE = menuTextMap.get("tagGenre");
//		TAG_COMMENT =  menuTextMap.get("tagComment");
//		TAG_COMPOSER = menuTextMap.get("tagComposer");
//		TAG_ORIGINAL_ARTIST = menuTextMap.get("tagOriginalArtist");
//		TAG_COPYRIGHT =  menuTextMap.get("tagCopyright");
//		TAG_URL = menuTextMap.get("tagUrl");
//		TAG_ENCODED_BY =  menuTextMap.get("tagEncodedBy");
//	}
	
	private void setInitState(){
		FileReader fr = null;
		BufferedReader br = null;
		
		String[] lastDirectory = {"", ""};
		String[] theme = {"", ""};
		String[] position = {"", ""};
		String[] width = {"", ""};
		String[] height = {"", ""};
		String[] volume = {"", ""};
		String[] font = {"", "", "", ""};
		String[] language = {"", ""};
		
		try {
			fr = new FileReader( new File("dalsong.ini") );
			br = new BufferedReader(fr);
			
		} catch(FileNotFoundException e) {
			e.printStackTrace(System.err);
//			System.exit(1);
		}
		
		try{
			lastDirectory = (br.readLine()).split("=");
			theme = (br.readLine()).split("=");
			position = (br.readLine()).split("=");
			width = (br.readLine()).split("=");
			height = (br.readLine()).split("=");
			volume = (br.readLine()).split("=");
			font = (br.readLine()).split("=");
			language = (br.readLine()).split("=");
						
			setLastDirectory(lastDirectory[1]);
			//setTheme(Integer.parseInt(theme[1]));	
			setPosition(position[1]);
			setSize(Integer.parseInt(width[1]), Integer.parseInt(height[1]));
			ControlComponent.changedGainValue = Integer.parseInt(volume[1]);
			setFont(font[1]);
			//setLanguage(Integer.parseInt(language[1]));
			
			
			fr.close();
			br.close();
		} catch(Exception e){
			e.printStackTrace(System.err);
			//setTheme(7);
			
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frm = this.getSize();
			int xpos = (int)(screen.getWidth()/2-frm.getWidth()/2);
			int ypos = (int)(screen.getHeight()/2-frm.getHeight()/2);
			this.setLocation(xpos,ypos);
		}
		
		
	}
	
	private void setLastDirectory(String d){
		if(d == null){ d = ""; }
		fileDlg = new JFileChooser(d);
	}
	
//	private void setTheme(int themeNumber){
//		stateTheme = themeNumber;
//		
//		switch(themeNumber){
//		case 0 : PlasticLookAndFeel.setPlasticTheme(new BrownSugar());
//			break;
//		case 1 : PlasticLookAndFeel.setPlasticTheme(new DarkStar());
//			break;
//		case 2 : PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
//			break;
//		case 3 : PlasticLookAndFeel.setPlasticTheme(new DesertBluer());
//			break;
//		case 4 : PlasticLookAndFeel.setPlasticTheme(new DesertGreen());
//			break;
//		case 5 : PlasticLookAndFeel.setPlasticTheme(new DesertRed());
//			break;
//		case 6 : PlasticLookAndFeel.setPlasticTheme(new DesertYellow());
//			break;
//		case 7 : PlasticLookAndFeel.setPlasticTheme(new ExperienceBlue());
//			break;
//		case 8 : PlasticLookAndFeel.setPlasticTheme(new ExperienceGreen());
//			break;
//		case 9 : PlasticLookAndFeel.setPlasticTheme(new ExperienceRoyale());
//			break;
//		case 10 : PlasticLookAndFeel.setPlasticTheme(new LightGray());
//			break;
//		case 11 : PlasticLookAndFeel.setPlasticTheme(new Silver());
//			break;
//		case 12 : PlasticLookAndFeel.setPlasticTheme(new SkyBlue());
//			break;
//		case 13 : PlasticLookAndFeel.setPlasticTheme(new SkyBluer());
//			break;
//		case 14 : PlasticLookAndFeel.setPlasticTheme(new SkyGreen());
//			break;
//		case 15 : PlasticLookAndFeel.setPlasticTheme(new SkyKrupp());
//			break;
//		case 16 : PlasticLookAndFeel.setPlasticTheme(new SkyPink());
//			break;
//		case 17 : PlasticLookAndFeel.setPlasticTheme(new SkyRed());
//			break;
//		case 18 : PlasticLookAndFeel.setPlasticTheme(new SkyYellow());
//			break;
//		default : PlasticLookAndFeel.setPlasticTheme(new ExperienceBlue());
//			break;
//		}
//		
//		try {
//			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
//			SwingUtilities.updateComponentTreeUI(this);
//			SwingUtilities.updateComponentTreeUI(playlist);
//			
//		} catch (Exception ex) {}
//		
//		
//		//ÆÄÀÏ¼±ÅÃ ´ÙÀÌ¾ó·Î±×µµ Å×¸¶¸¦ Àû¿ëÇÏ±â À§ÇØ¼­...
//		setLastDirectory(stateDirectiory);
//		//±Û²Ã¼±ÅÃ ´ÙÀÌ¾ó·Î±×µµ Å×¸¶¸¦ Àû¿ëÇÏ±â À§ÇØ¼­...
//		fontChooser = new JFontChooser(this);
//		fontChooser.setFont(lyricPane.getFont());
//		//ÇÃ·¹ÀÌ¸®½ºÆ® ÆË¾÷¿¡µµ...
//		if(playlist != null){
//			playlist.initJPopupMenu();
//			playlist.refreashFileChooserDlg();
//		}
//	}
	
	private void setPosition(String p){
		if(p != null){	
			String pos[] = p.split(",");
			this.setLocation(Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
		}
	}
	
	private void setFont(String f){
		Font font;
		String[] fontStr = f.split(",");
		
		if(Boolean.parseBoolean(fontStr[1]))
			font = new Font(fontStr[0], Font.ITALIC, Integer.parseInt(fontStr[2]));
		else 
			font = new Font(fontStr[0], Font.PLAIN, Integer.parseInt(fontStr[2]));
		
//		lyricPane.setFont(font);
		
	}
	
	public void setMenuBar(){
		JMenuBar mainMenuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu(MENU_FILE);
		fileMenu.add(makeMenuItem(OPEN_MENU_ACTION_COMMAND));
		fileMenu.add(makeMenuItem(PLAYLIST_MENU_ACTION_COMMAND));
		fileMenu.addSeparator();
		fileMenu.add(makeMenuItem(EXIT_MENU_ACTION_COMMAND));
		mainMenuBar.add(fileMenu);
		
		JMenu optionMenu = new JMenu(MENU_OPTION);
		optionMenu.add(makeMenuItem(OPT_MINI_ACTION_COMMAND));
		optionMenu.add(makeMenuItem(OPT_LYRIC_ACTION_COMMAND));
		optionMenu.addSeparator();
		optionMenu.add(makeMenuItem(OPT_SYNC_ACTION_COMMAND));
		optionMenu.add(makeMenuItem(OPT_FONT_ACTION_COMMAND)); 
		optionMenu.addSeparator();
		JMenu langMenu = new JMenu("Language");
	    optionMenu.add(langMenu);
		
	    // ButtonGroup for radio buttons
	    ButtonGroup directionGroup = new ButtonGroup();

	    JRadioButtonMenuItem englishItem = new JRadioButtonMenuItem("English");
	    englishItem.addActionListener(this);
	    langMenu.add(englishItem);
	    directionGroup.add(englishItem);

	    JRadioButtonMenuItem koreanItem = new JRadioButtonMenuItem("Korean");
	    koreanItem.addActionListener(this);
	    langMenu.add(koreanItem);
	    directionGroup.add(koreanItem);
	    
	    if(languageMode == ENGLISH_MODE){
	    	englishItem.setSelected(true);
	    } else {
	    	koreanItem.setSelected(true);
	    }
		mainMenuBar.add(optionMenu);
		
		JMenu themeMenu = new JMenu(MENU_THEME);
		for(int i=0; i < THMEME_ACTION_COMMAND.length; i++)
			themeMenu.add(makeMenuItem(THMEME_ACTION_COMMAND[i]));
		mainMenuBar.add(themeMenu);
		
		JMenu helpMenu = new JMenu(MENU_HELP);
		helpMenu.add(makeMenuItem(ABOUT_DLG_ACTION_COMMAND));
		mainMenuBar.add(helpMenu);
		
		this.setJMenuBar(mainMenuBar);
	}
	
	private void setListener(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				goExit();
				exit();
			}
			
//			public void windowIconified(WindowEvent e) {
//				isVisiblePlayList = playlist.isVisible();
//				playlist.setVisible(false);
//			}
//
//			public void windowDeiconified(WindowEvent e) {
//				if(isVisiblePlayList){
//					playlist.setVisible(true);
//				}
//			}
						
		});
		
		addComponentListener(new ComponentAdapter(){
			public void componentMoved(ComponentEvent ce){
//				System.out.println("x = "+getX()+", y = "+getY());
//				if(playlist != null && playlist.isVisible()){
////					playlist.showThisList();
//					
//				}
			}
		});		
	}
	
	
		
	private void goExit(){
		System.out.println("Á¾·á");
		saveState();
//		playlist.saveList();
	}
	
	//ÆÄÀÏ¿¡ ÇöÀç»óÅÂ¸¦ ÀúÀåÇÑ´Ù.
	private void saveState(){
		 try {
		      PrintStream out = new PrintStream(new FileOutputStream("dalsong.ini"));
		      
		      out.println("last directory="+stateDirectiory);
		      out.println("theme="+stateTheme);
		      out.println("position=" + this.getX() + "," + this.getY());
		      out.println("width=" + this.getWidth());
		      out.println("hight=" + this.getHeight());
		      out.println("volume=" + ControlComponent.changedGainValue);
////		      out.println("font=" + lyricPane.getFont().getFontName() + "," + 
//		    		  				lyricPane.getFont().isItalic() + "," +
//		    		  				lyricPane.getFont().getSize());
		      out.println("language=" + languageMode);
		      out.flush();
		      out.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
	}

	private JMenuItem makeMenuItem(String commandString) {
		JMenuItem item = new JMenuItem(commandString);
		item.addActionListener(this);
		return item;
	}
	
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getNorthPanel(), BorderLayout.NORTH);
//			jContentPane.add(lyricPane, BorderLayout.CENTER);
			jContentPane.add(getSouthPanel(), BorderLayout.SOUTH);
				
		}
		return jContentPane;
	}
	
	
	private JPanel getNorthPanel() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BorderLayout());
			jPanel1.setBackground(Color.WHITE);
			
			controlCmpnt = new ControlComponent(this);
			jPanel1.add(controlCmpnt, BorderLayout.CENTER);
		}
		return jPanel1;
	}
	
	private JPanel getSouthPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout.setVgap(0);
			flowLayout.setHgap(6);
			
			jLabel_SongTitle = new JLabel();
			jLabel_SongTitle.setText("Title");
						
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.setBorder(BorderFactory.createEtchedBorder());
			jPanel.add(jLabel_SongTitle, java.awt.BorderLayout.NORTH);
		}
		return jPanel;
	}
	
	
	private JDialog getControlSyncDlg(){
		if(controlSyncDlg == null) {
			controlSyncDlg = new JDialog();
			controlSyncDlg.setLocationRelativeTo(this);
			controlSyncDlg.setTitle(OPT_SYNC_ACTION_COMMAND);
			
			JPanel p = new JPanel();
			
			p.setLayout(new BorderLayout());

			
			syncLabel = new JLabel(Integer.toString(sync));
			syncLabel.setHorizontalAlignment(JTextField.CENTER);
			
			JButton fastBtn = new JButton("Fast");
			fastBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					sync = sync + 50;
					syncLabel.setText(Integer.toString(sync));
		
				}
			});
						
			JButton slowBtn = new JButton("Slow");
			slowBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					sync = sync - 50;
					syncLabel.setText(Integer.toString(sync));
				}
			});

			JPanel center = new JPanel();
			center.add(fastBtn);
			center.add(slowBtn);
			
			p.add(syncLabel, BorderLayout.NORTH);
			p.add(center, BorderLayout.CENTER);
			
			controlSyncDlg.setContentPane(p);
			
			
			controlSyncDlg.setResizable(false);
			controlSyncDlg.pack();
		}
		
		return controlSyncDlg;
	}
	
	
//	public PlayListManager getPlayList() {
//		if (playlist == null) {
//			playlist = new PlayListManager(this);
//			playlist.setTitle(PLAYLIST_TITLE_BAR_TEXT);
//		}
//		return playlist;
//	}

	
	public void refreshLyricPane(long mediatime, long duration){
						
		long mTime = mediatime;

		//¿©±â°¡ Á» ¾ß¸Å´Ù... 
		//mp3°¡ Àç»ýµÇ°í ÀÖ´Â ½Ã°£(ÄÁÆ®·Ñ¿¡¼­ ½Ç½Ã°£À¸·Î ³Ñ¾î¿È)°ú ÀÚ¸·¿¡ ÀÖ´Â
		//½Ã°£À» ºñ±³ÇØ¾ß ÇÏ´Âµ¥ ±ÍÂú¾Æ¼­ ±×³É ':'¿Í '.'¸¦ »©°í ±×³É ºÙ¿©¼­
		//6ÀÚ¸® intÇüÀ¸·Î ¸¸µé¾ú´Ù..
//		int t = Utility.getTimeNormlType(mTime);
				
		try{
//			lyricPane.setCurrentLyric(t+sync);
		}catch(Exception e){}
		
	}
	
	private void changeLanguage(int mode){
		//setLanguage(mode);
		setTitle(TITLE_BAR_TEXT);
//		playlist.setTitle(PLAYLIST_TITLE_BAR_TEXT);
//		playlist.initJPopupMenu();
		setMenuBar();
		this.setVisible(false);
		this.setVisible(true);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// ActionListener ±¸Çö ºÎºÐ
	// ------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e){
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals(OPEN_MENU_ACTION_COMMAND)) {
			openMediaFile();
		}else if(actionCommand.equals(PLAYLIST_MENU_ACTION_COMMAND)) {
//			getPlayList().setVisible(true);
		}else if(actionCommand.equals(EXIT_MENU_ACTION_COMMAND)) {
			goExit();
			exit();
		}else if(actionCommand.equals(OPT_MINI_ACTION_COMMAND)) {
			this.setSize(232,97);
		}else if(actionCommand.equals(OPT_LYRIC_ACTION_COMMAND)) {
			setSize(320, 400);
		}else if(actionCommand.equals(OPT_SYNC_ACTION_COMMAND)) {
			getControlSyncDlg().setVisible(true);
		}else if(actionCommand.equals(OPT_FONT_ACTION_COMMAND)) {
//			int result = fontChooser.showDialog(lyricPane.getFont());
//
//			if (result != JFontChooser.CANCEL_OPTION) {
//				lyricPane.setFont(fontChooser.getFont());
////				lyricFont = fontChooser.getFont();
////				setTextPaneFont(fontChooser.getFont());
//			}
		}else if(actionCommand.equals("English")){
			changeLanguage(ENGLISH_MODE);
		}else if(actionCommand.equals("Korean")){
			changeLanguage(KOREAN_MODE);
		}else if(actionCommand.equals(ABOUT_DLG_ACTION_COMMAND)) {
//			new AboutDialog2(this, languageMode);
		}else {
			//Å×¸¶¸¦ ¹Ù²Û´Ù.

//				if(actionCommand.equals(THMEME_ACTION_COMMAND[0])){
//					setTheme(0);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[1])){
//					setTheme(1);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[2])){
//					setTheme(2);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[3])){
//					setTheme(3);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[4])){
//					setTheme(4);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[5])){
//					setTheme(5);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[6])){
//					setTheme(6);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[7])){
//					setTheme(7);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[8])){
//					setTheme(8);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[9])){
//					setTheme(9);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[10])){
//					setTheme(10);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[11])){
//					setTheme(11);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[12])){
//					setTheme(12);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[13])){
//					setTheme(13);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[14])){
//					setTheme(14);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[15])){
//					setTheme(15);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[16])){
//					setTheme(16);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[17])){
//					setTheme(17);
//				} else if(actionCommand.equals(THMEME_ACTION_COMMAND[18])){
//					setTheme(18);
//				} 
			
		}
	}
		
	// ------------------------------------------------------------------------
	private void openMediaFile(){
		fileDlg.setFileFilter(filter);
		if(fileDlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			try{
				stateDirectiory = fileDlg.getSelectedFile().getAbsolutePath();
//				playlist.insertItem(fileDlg.getSelectedFile());
				startMedia(fileDlg.getSelectedFile());
			}catch(Exception e){
			}
		}
	}
	
	/*
	//--------------------------------------------------------------------------
	//½Ç½Ã°£ °¡»çÃ¢ÀÇ ÆùÆ®¸¦ ¹Ù²Ù´Â ¸Þ¼Òµå
	private void setTextPaneFont(Font f){
		StyleConstants.setFontFamily(standard, f.getFontName());
		StyleConstants.setFontSize(standard, f.getSize());
		StyleConstants.setItalic(standard, f.isItalic());
		
		StyleConstants.setFontFamily(keyWord, f.getFontName());
		StyleConstants.setFontSize(keyWord, f.getSize());
		StyleConstants.setItalic(keyWord, f.isItalic());
		
		doc.setCharacterAttributes(0, doc.getLength(), standard, true);
		
	}
	*/
	
	// ------------------------------------------------------------------------
	//ÄÞÆ÷³ÍÆ®ÀÇ ÆùÆ®¸¦ ¹Ù²Ù´Â ¸Þ¼Òµå
	public static void recursivelySetFonts(Component comp, Font font) {
        comp.setFont(font);
        if (comp instanceof Container) {
            Container cont = (Container) comp;
            for(int j=0, ub=cont.getComponentCount(); j<ub; ++j)
                recursivelySetFonts(cont.getComponent(j), font);
        }
    }

	
	private void exit(){
		System.exit(-1);
	}
	
	public void setValInit(){
//		lyricPane.setSyncChanged();
	}
	
	public void setSyncCntlInit(){
		sync = 0;
		syncLabel.setText("0");
	}
	
	public void startMedia(File mediaFile){
		setValInit();

		try{
			RandomAccessFile raFile = new RandomAccessFile(mediaFile, "r"); 
//			ei = ir.read(raFile);
			
//			duration = (long)(ei.getPreciseLength()*1000);
			
			raFile.close();
		}catch(Exception e){
			System.out.println("¿¡·¯!!!!!!!!!");
			return ;
		}
		
		//mp3 ÇÃ·¹ÀÌ °ü·Ã
//		controlCmpnt.loadFile(mediaFile, lyrc.getTagSize(), duration);
		controlCmpnt.playORpause();
		
		//Á¦¸ñ Ç¥½Ã °ü·Ã
		jLabel_SongTitle.setText(mediaFile.getName());

		//½Ç½Ã°£ °¡»ç º¸±â °ü·Ã
		showLyric(mediaFile.getPath());
		
		
		
		
	}
	
	public void nextMedia(){
//		startMedia(playlist.getNextSongFile());
	}
	
	public void showLyric(String mp3FilePath){
		boolean isAvailableServer;
					
//		isAvailableServer = lyrc.initLyric(mp3FilePath);
//		
//		lyricPane.clean();
//		if(isAvailableServer){
//			lyricPane.setText(lyrc.getSplitLyric());
//			lyricPane.setTime(lyrc.getSplitTime());
//		}
		
	}
	
	void fatal (String s) {
		System.err.println("FATAL ERROR: " + s);
		throw new Error(s);
	}
	
	
	public static void main(String args[]){
		
		
//		JFrame.setDefaultLookAndFeelDecorated(true); 
//		JDialog.setDefaultLookAndFeelDecorated(true);
				
		
		
		new DalSongPlayer();
	}

}