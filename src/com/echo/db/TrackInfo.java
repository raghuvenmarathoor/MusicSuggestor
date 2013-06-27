/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.db;

import com.echo.bean.TrackInfoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Staff
 */
public class TrackInfo {

    Connection connection;

    public boolean addNewTrack(TrackInfoBean trackInfoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String addTrack_Query = "INSERT into trackinfo (trackid , artistid , genreid "
                    + ", title , duration , bitrate  ) VALUES (? , ? , "
                    + "? , ?  , ? , ?)";
            PreparedStatement prepareStatement = connection.prepareStatement(addTrack_Query);
            prepareStatement.setString(1, trackInfoBean.getTrack_Id());
            prepareStatement.setString(2, trackInfoBean.getArtist_ID());
            prepareStatement.setString(3, trackInfoBean.getGenre_ID());
            prepareStatement.setString(4, trackInfoBean.getTitle());
            prepareStatement.setString(5, trackInfoBean.getDuration());
            prepareStatement.setString(6, trackInfoBean.getBitrate());
            boolean checkTrackID = checkTrackID(trackInfoBean);
            if (checkTrackID) {
                return true;
            } else {
                int executeUpdate = prepareStatement.executeUpdate();
                if (executeUpdate > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            reverseInfo(trackInfoBean.getTrack_Id());
            Logger.getLogger(TrackInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void reverseInfo(String track_ID) {
        try {
            connection = new DatabaseConnection().getConnection();
            String artistId_query = "SELECT artistid FROM trackinfo WHERE trackid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(artistId_query);
            prepareStatement.setString(1, track_ID);
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                String artist_Id = executeQuery.getString("artistid");
                String artist_Query = "DELETE FROM artistinfo WHERE artistid = ?";
                PreparedStatement prepareStatement1 = connection.prepareStatement(artist_Query);
                prepareStatement1.setString(1, artist_Id);
                int executeUpdate = prepareStatement1.executeUpdate();
                if (executeUpdate > 0) {
                    String reverse_Query = "DELETE FROM trackinfo WHERE trackid = ?";
                    PreparedStatement prepareStatement2 = connection.prepareStatement(reverse_Query);
                    prepareStatement2.setString(1, track_ID);
                    prepareStatement2.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackInfo.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public boolean checkTrackID(TrackInfoBean trackInfoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String trackID_Query = "SELECT trackid FROM trackinfo WHERE trackid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(trackID_Query);
            prepareStatement.setString(1, trackInfoBean.getTrack_Id());
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                String trackID = executeQuery.getString("trackid");
                if (trackID.equalsIgnoreCase(trackInfoBean.getTrack_Id())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
