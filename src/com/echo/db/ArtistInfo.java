/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.db;

import com.echo.bean.ArtistInfoBean;
import com.echo.util.AppContants;
import com.echo.util.AppVariables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Staff
 */
public class ArtistInfo {

    Connection connection;

    public boolean addNewArtist(ArtistInfoBean artistInfoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String addTrack_Query = "INSERT into artistinfo (artistid, artistname)  VALUES (?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(addTrack_Query);
            prepareStatement.setString(1, artistInfoBean.getArtist_id());
            prepareStatement.setString(2, artistInfoBean.getArtist_name());
            boolean checkArtistId = checkArtistId(artistInfoBean);
            if (checkArtistId) {
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
            Logger.getLogger(ArtistInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;


    }

    public boolean checkArtistId(ArtistInfoBean artistInfoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String check_artistQuery = "SELECT artistid FROM artistinfo WHERE artistid = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(check_artistQuery);
            prepareStatement.setString(1, artistInfoBean.getArtist_id());
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                String artistid = executeQuery.getString("artistid");
                if (artistid.equalsIgnoreCase(artistInfoBean.getArtist_id())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public ArrayList<SuggestionInfoBean> getPopularSongs() {
        ArrayList<SuggestionInfoBean> popularArrayList = new ArrayList();

        try {
            connection = new DatabaseConnection().getConnection();
            String popular_Songs_Query = "SELECT  DISTINCT artistname ,"
                    + " title FROM artistinfo , trackinfo , "
                    + "userplaylistinfo  WHERE trackinfo.artistid = artistinfo.artistid"
                    + " AND userplaylistinfo.trackid = trackinfo.trackid AND "
                    + "username <> ? ORDER BY (SELECT COUNT(artistinfo.artistname)"
                    + " FROM artistinfo , trackinfo WHERE artistinfo.artistid = trackinfo.artistid)";

            PreparedStatement preparedStatement = connection.prepareStatement(popular_Songs_Query);
            preparedStatement.setString(1, AppVariables.username);
            ResultSet executeQuery = preparedStatement.executeQuery();
            while (executeQuery.next()) {
                SuggestionInfoBean suggestionInfoBean = new SuggestionInfoBean();
                suggestionInfoBean.setArtistName(executeQuery.getString("artistname"));
                suggestionInfoBean.setTitleName(executeQuery.getString("title"));
                popularArrayList.add(suggestionInfoBean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArtistInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return popularArrayList;

    }

    public ArrayList<SuggestionInfoBean> getUserFavSongs() {
        ArrayList<SuggestionInfoBean> favArrayList = new ArrayList();
        try {
            connection = new DatabaseConnection().getConnection();
            String user_fav_Songs = "SELECT  DISTINCT artistname , title FROM artistinfo"
                    + " , trackinfo , userplaylistinfo  WHERE "
                    + "trackinfo.artistid = artistinfo.artistid AND userplaylistinfo.trackid = trackinfo.trackid"
                    + " AND userplaylistinfo.count > ? AND username<> ?  "
                    + "ORDER BY (SELECT COUNT(artistinfo.artistname)"
                    + " FROM artistinfo , trackinfo WHERE artistinfo.artistid = trackinfo.artistid)";

            PreparedStatement preparedStatement = connection.prepareStatement(user_fav_Songs);
            preparedStatement.setInt(1, AppVariables.count);
            preparedStatement.setString(2, AppVariables.username);
            ResultSet executeQuery = preparedStatement.executeQuery();
            while (executeQuery.next()) {
                SuggestionInfoBean suggestionInfoBean = new SuggestionInfoBean();
                suggestionInfoBean.setArtistName(executeQuery.getString("artistname"));
                suggestionInfoBean.setTitleName(executeQuery.getString("title"));
                favArrayList.add(suggestionInfoBean);

            }


        } catch (SQLException ex) {
            Logger.getLogger(ArtistInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favArrayList;
    }
}
