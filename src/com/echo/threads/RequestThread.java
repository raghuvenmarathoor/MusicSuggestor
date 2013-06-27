/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.threads;

import com.echo.bean.ArtistInfoBean;
import com.echo.bean.CodeStringBean;
import com.echo.bean.TrackInfoBean;
import com.echo.bean.UserInfoBean;
import com.echo.db.CodeStringInfo;
import com.echo.util.AppContants;
import com.echo.util.AppVariables;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Staff
 */
public class RequestThread implements Runnable {

    File file;
    String codeString = "";
    String track_Id = "";
    long crc;
    String bitRate = "";
    String duration = "";
    String response_status = "";
    String artist_Name = "";
    String artist_Id = "";
    String title_Track = "";

    public RequestThread(File file) {
        this.file = file;

    }

    RequestThread(String duration, String bitrate, String codeString, long crc) {
        this.duration = duration;
        this.bitRate = bitrate;
        this.codeString = codeString;
        this.crc = crc;
    }

    @Override
    public void run() {
        try {
            URL url = new URL("http://developer.echonest.com/api/v4/song/identify?api_key=" + AppContants.API_KEY + "&code=" + codeString);
            URLConnection connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String str = br.readLine();
            System.out.println("response:" + str);

            String[] split = str.split(":");

            String[] split2 = split[9].split(",");
            System.out.println("split2:" + split2[0]);
            System.out.println("replaced:" + split2[0].replace('"', ' ').trim());
            String[] split1 = split2[0].replace('"', ' ').trim().split(" ");
            System.out.println("message:" + split1[0]);
            //response_status = split2[0].replace('"', ' ').substring(split2[0].replace('"', ' ').indexOf(""), split2[0].replace('"', ' ').indexOf("}")).trim();
            response_status = split1[0];
            if (response_status.equals(AppContants.STATUS)) {

                //get title

                String[] split3 = split[8].split(",");
                System.out.println("Tilte:" + split3[0].replace('"', ' ').trim());
                title_Track = split3[0].replace('"', ' ').trim();
                //get artist id

                String[] split4 = split[10].split(",");
                System.out.println("Artist ID:" + split4[0].replace('"', ' ').trim());
                artist_Id = split4[0].replace('"', ' ').trim();

                //get artist name

                String[] split5 = split[11].split(",");
                System.out.println("Artist Name:" + split5[0].replace('"', ' ').trim());
                artist_Name = split5[0].replace('"', ' ').trim();
                //get track id:

                String[] split6 = split[12].split(",");
                System.out.println("Track ID:" + split6[0].replace('"', ' ').substring(split6[0].replace('"', ' ').indexOf(""), split6[0].replace('"', ' ').indexOf("}")).trim());


                track_Id = split6[0].replace('"', ' ').substring(split6[0].replace('"', ' ').indexOf(""), split6[0].replace('"', ' ').indexOf("}")).trim();
                System.out.println("crc:" + crc);
                System.out.println("codestring:" + codeString);
                CodeStringBean codeStringBean = new CodeStringBean();
                codeStringBean.setCode_String(codeString);
                codeStringBean.setCrc(crc);
                codeStringBean.setTrack_ID(track_Id);
                ArtistInfoBean artistInfoBean = new ArtistInfoBean();
                artistInfoBean.setArtist_id(artist_Id);
                artistInfoBean.setArtist_name(artist_Name);
                TrackInfoBean trackInfoBean = new TrackInfoBean();
                trackInfoBean.setTrack_Id(track_Id);
                trackInfoBean.setArtist_ID(artist_Id);
                trackInfoBean.setTitle(title_Track);
                trackInfoBean.setBitrate(bitRate);
                trackInfoBean.setDuration(duration);
                UserInfoBean infoBean = new UserInfoBean();
                infoBean.setUsername(AppVariables.username);
                CodeStringInfo codeStringInfo = new CodeStringInfo();
                codeStringInfo.addCodeString(codeStringBean, artistInfoBean, trackInfoBean, infoBean);



            } else {
                JOptionPane.showMessageDialog(null, "Cannot retrieve data for this song");
            }




        } catch (IOException ex) {
            Logger.getLogger(RequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
