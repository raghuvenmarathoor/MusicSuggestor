/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.threads;

import com.echo.bean.SongMetadata;
import com.echo.bean.TrackMetaInfoBean;
import com.echo.util.AppVariables;
import com.echo.util.CheckCRC;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;

/**
 *
 * @author Staff
 */
public class DurationThread {

    File[] playFileList = null;
    double duration = 0.0;
    String jasonCodeString = "";
    String line = "";
    String codeString = "";
    long crc;
    Calendar calendar;
    TrackMetaInfoBean trackMetaInfoBean;
    File currentlyPlaying = null;

    public DurationThread(File[] playFiles) {
        this.playFileList = playFiles;
    }

    public DurationThread(File file) {
        this.currentlyPlaying = file;
    }

    public void insertInfos() {
        System.out.println("inside insert info()");
        CheckCRC checkCRC = new CheckCRC();
        crc = checkCRC.getCRC(currentlyPlaying.getAbsolutePath());
        codeString = getCodeString(currentlyPlaying);
        String[] split = codeString.split(":");
//        double total_Duration = getDuration(split[4]);
        String total_Duration = trackMetaInfoBean.getTrackLength();
        System.out.println("bitrate:" + split[2]);
        System.out.println("code String:" + split[0]);
        System.out.println("crc:" + crc);
        System.out.println("totatl duration:" + total_Duration);



        //passing to db--> calling the required method
        RequestThread requestThread = new RequestThread(total_Duration, split[2], split[0], crc);
        new Thread(requestThread).start();

    }

// codestring of a single mp3 file...
    public String getCodeString(final File file) {
        String replString = "";

        try {

            System.out.println("inside if in getCodeString");
            ProcessBuilder builder = new ProcessBuilder(AppVariables.Code_Generator_Exe.trim(), file.getAbsolutePath().trim(), "20", "40");
            Process process = builder.start();
            InputStream is = process.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            while ((line = bufferedReader.readLine()) != null) {

                jasonCodeString = jasonCodeString + line;
            }
            System.out.println("codestring collection:" + jasonCodeString);

            //getting codestring
            String[] jsonCode = jasonCodeString.split("code");

            String[] split = jsonCode[jsonCode.length - 1].split(",");
            String[] split1 = split[0].replace('"', ' ').trim().split(":");
            System.out.println("real code string : " + split1[1].trim());


            //getting bitrate

            String bitRate = jasonCodeString.substring(jasonCodeString.indexOf("bitrate"), jasonCodeString.indexOf("sample_rate"));
            System.out.println("bitrtate:" + bitRate.replace('"', ' ').replace(',', ' ').trim());

            //getting duration
            trackMetaInfoBean = getTrackInfos(file);
            System.out.println("duration of track:" + trackMetaInfoBean.getTrackLength());

            replString = split1[1].trim() + ":" + bitRate.replace('"', ' ').replace(',', ' ').trim() + ":" + trackMetaInfoBean.getTrackLength();

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(DurationThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return replString;

    }

//    private double getDuration(String string) {
//        int parseInt = Integer.parseInt(string);
//        double time_mins = parseInt / 60;
//        double time_sec = parseInt % 60;
//        double time_secs = time_sec / 60;
//        double total_duration = time_mins + time_secs;
//
//        return total_duration;
//
//    }
    public ArrayList<String> getTrackDuration() {


        ArrayList<String> durationList = new ArrayList();
        File currentFile = null;
        for (int i = 0; i < playFileList.length; i++) {
//            ProcessBuilder builder = null;
//            Process process = null;
//            InputStream is = null;
            currentFile = new File(playFileList[i].getAbsolutePath());
            trackMetaInfoBean = getTrackInfos(currentFile);

//                System.out.println("eachfile:" + playFileList[i].getAbsolutePath());
//                builder = new ProcessBuilder(AppVariables.Code_Generator_Exe.trim(), playFileList[i].getAbsolutePath(), "20", "40");
//
//                process = builder.start();
//                is = process.getInputStream();
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//
//                while ((line = bufferedReader.readLine()) != null) {
//
//                    jasonCodeString = jasonCodeString + line;
//
//
//                }
//                String bitRate = jasonCodeString.substring(jasonCodeString.indexOf("bitrate"), jasonCodeString.indexOf("sample_rate"));
//                System.out.println("bitrtate:" + bitRate.replace('"', ' ').replace(',', ' ').trim());

            //getting duration
            // String duration = jasonCodeString.substring(jasonCodeString.indexOf("duration"), jasonCodeString.indexOf("filename"));

            //System.out.println("duration:" + duration.replace('"', ' ').replace(',', ' ').trim().replaceAll("duration", "").replaceAll(":", "").trim());
            //durations = duration.replace('"', ' ').replace(',', ' ').trim().replaceAll("duration", "").replaceAll(":", "").trim();

            durationList.add(trackMetaInfoBean.getTrackLength());

//                bufferedReader.close();
//                is.close();
//                jasonCodeString = "";

        }



        return durationList;
    }

//    public TrackMetaInfoBean setMetaInfo(File file) {
//        try {
//            MP3AudioHeader audioHeader = new MP3AudioHeader(file);
//            System.out.println("inside setMetaInfo()");
//
//
//            trackMetaInfoBean = new TrackMetaInfoBean();
//
//            System.out.println("precise:" + audioHeader.getPreciseTrackLength());
//
//            trackMetaInfoBean.setNumberOfFrames(audioHeader.getNumberOfFrames());
//            trackMetaInfoBean.setStartBytes(audioHeader.getMp3StartByte());
//            trackMetaInfoBean.setBitRate(audioHeader.getBitRate());
//            trackMetaInfoBean.setTrackLength(audioHeader.getTrackLengthAsString());
//            trackMetaInfoBean.setPreciseTrackLength(audioHeader.getPreciseTrackLength());
//
//
//
//
//        } catch (IOException ex) {
//            Logger.getLogger(DurationThread.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidAudioFrameException ex) {
//            Logger.getLogger(DurationThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return trackMetaInfoBean;
//    }
    public TrackMetaInfoBean getTrackInfos(File currentFile) {
        //try {
//            ProcessBuilder builder = null;
//            Process process = null;
//            InputStream is = null;
//            builder = new ProcessBuilder(AppVariables.Code_Generator_Exe.trim(), currentFile.getAbsolutePath(), "20", "40");
//
//            process = builder.start();
//            is = process.getInputStream();
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
//
//            while ((line = bufferedReader.readLine()) != null) {
//
//                jasonCodeString = jasonCodeString + line;
//
//            }
//            System.out.println("json code string:"+ jasonCodeString);
//            System.out.println("inside setMetaInfo()");
//            MP3AudioHeader audioHeader = new MP3AudioHeader(currentFile);
//
//            String substring1 = jasonCodeString.substring(jasonCodeString.indexOf("artist"), jasonCodeString.indexOf("release"));
//            String[] subStringsplit1 = substring1.split(":");
//            String suString2 = jasonCodeString.substring(jasonCodeString.indexOf("release"), jasonCodeString.indexOf("title"));
//            String[] subString2Split = suString2.split(":");
//            String subString3 = jasonCodeString.substring(jasonCodeString.indexOf("title"), jasonCodeString.indexOf("genre"));
//            String[] subString3Split = subString3.split(":");
//            String subString4 = jasonCodeString.substring(jasonCodeString.indexOf("genre"), jasonCodeString.indexOf("bitrate"));
//            String[] subString4split = subString4.split(":");

            SongMetadata sngMeta = new SongMetadata(currentFile.getAbsolutePath());
            trackMetaInfoBean = new TrackMetaInfoBean();
            trackMetaInfoBean.setNumberOfFrames(sngMeta.getNoOfFrames());
            trackMetaInfoBean.setStartBytes(sngMeta.getMp3StartByte());
            trackMetaInfoBean.setBitRate(sngMeta.getBitRate());
            trackMetaInfoBean.setTrackLength(sngMeta.getTrackLength());
            trackMetaInfoBean.setPreciseTrackLength(sngMeta.getTrackLengthInteger());
            trackMetaInfoBean.setAlbumInfo(sngMeta.getAlbum());
            trackMetaInfoBean.setTitleInfo(sngMeta.getTitle());
            trackMetaInfoBean.setGenreInfo(sngMeta.getGenre());
            trackMetaInfoBean.setArtistInfo(sngMeta.getArtist());






//        } catch (InvalidAudioFrameException ex) {
//              //JOptionPane.showMessageDialog(null,"not decode");
//            Logger.getLogger(DurationThread.class.getName()).log(Level.SEVERE, null, ex);
//          
//        } catch (IOException ex) {
//              JOptionPane.showMessageDialog(null,"not decode");
//           // Logger.getLogger(DurationThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return trackMetaInfoBean;



    }
}
