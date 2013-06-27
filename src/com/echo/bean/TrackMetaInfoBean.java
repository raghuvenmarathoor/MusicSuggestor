/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.bean;

/**
 *
 * @author Staff
 */
public class TrackMetaInfoBean {

    String bitRate = "";
    String trackLength = "";
    long numberOfFrames;
    long startBytes;
    double preciseTrackLength = 0.0;
    String artistInfo = "";
    String albumInfo = "";
    String titleInfo = "";
    String genreInfo = "";

    public String getBitRate() {
        return bitRate;
    }

    public void setBitRate(String bitRate) {
        this.bitRate = bitRate;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public long getNumberOfFrames() {
        return numberOfFrames;
    }

    public void setNumberOfFrames(long numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
    }

    public long getStartBytes() {
        return startBytes;
    }

    public void setStartBytes(long startBytes) {
        this.startBytes = startBytes;
    }

    public double getPreciseTrackLength() {
        return preciseTrackLength;
    }

    public void setPreciseTrackLength(double preciseTrackLength) {
        this.preciseTrackLength = preciseTrackLength;
    }

    public String getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(String albumInfo) {
        this.albumInfo = albumInfo;
    }

    public String getArtistInfo() {
        return artistInfo;
    }

    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo;
    }

    public String getGenreInfo() {
        return genreInfo;
    }

    public void setGenreInfo(String genreInfo) {
        this.genreInfo = genreInfo;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }
}
