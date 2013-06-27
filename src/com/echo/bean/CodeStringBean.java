/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class CodeStringBean {

    private String track_ID = "";
    private String code_String = "";
    private long crc;

    public String getCode_String() {
        return code_String;
    }

    public void setCode_String(String code_String) {
        this.code_String = code_String;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }

    public String getTrack_ID() {
        return track_ID;
    }

    public void setTrack_ID(String track_ID) {
        this.track_ID = track_ID;
    }
}
