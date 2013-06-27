/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class UserPlayListBean {

    private String username = "";
    private String track_Id = "";
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTrack_Id() {
        return track_Id;
    }

    public void setTrack_Id(String track_Id) {
        this.track_Id = track_Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
