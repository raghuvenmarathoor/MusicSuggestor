/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class AlbumInfoBean {

    private String album_Id = "";
    private String track_Id = "";
    private String album_name = "";

    public String getAlbum_Id() {
        return album_Id;
    }

    public void setAlbum_Id(String album_Id) {
        this.album_Id = album_Id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getTrack_Id() {
        return track_Id;
    }

    public void setTrack_Id(String track_Id) {
        this.track_Id = track_Id;
    }
    
}
