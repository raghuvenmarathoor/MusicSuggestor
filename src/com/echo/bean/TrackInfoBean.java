/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class TrackInfoBean {

    private String track_Id = "";
    private String track_path = "";
    private String title = "";
    private String duration = "";
    private String bitrate = "";
    private String album_Id = "";
    private String artist_ID = "";
    private String Genre_ID = "";

    public String getGenre_ID() {
        return Genre_ID;
    }

    public void setGenre_ID(String Genre_ID) {
        this.Genre_ID = Genre_ID;
    }

    public String getAlbum_Id() {
        return album_Id;
    }

    public void setAlbum_Id(String album_Id) {
        this.album_Id = album_Id;
    }

    public String getArtist_ID() {
        return artist_ID;
    }

    public void setArtist_ID(String artist_ID) {
        this.artist_ID = artist_ID;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrack_Id() {
        return track_Id;
    }

    public void setTrack_Id(String track_Id) {
        this.track_Id = track_Id;
    }

    public String getTrack_path() {
        return track_path;
    }

    public void setTrack_path(String track_path) {
        this.track_path = track_path;
    }
}
