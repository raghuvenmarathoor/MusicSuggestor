/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.db;

import com.echo.bean.ArtistInfoBean;
import com.echo.bean.CodeStringBean;
import com.echo.bean.TrackInfoBean;
import com.echo.bean.UserInfoBean;
import com.echo.bean.UserPlayListBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Staff
 */
public class CodeStringInfo {

    Connection connection;
    TrackInfo trackInfo = new TrackInfo();
    ArtistInfo artistInfo = new ArtistInfo();

    public boolean addCodeString(CodeStringBean codeStringBean, ArtistInfoBean artistInfoBean, TrackInfoBean trackInfoBean, UserInfoBean infoBean) {
        if (checkCodeString(codeStringBean, infoBean).equalsIgnoreCase("na")) {

            try {
                connection = new DatabaseConnection().getConnection();
                String add_String = "INSERT into codestringinfo (codestring, track_id , crc , username ) "
                        + "VALUES (? , ? , ? ,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(add_String);
                preparedStatement.setString(1, codeStringBean.getCode_String());
                preparedStatement.setString(2, codeStringBean.getTrack_ID());
                preparedStatement.setLong(3, codeStringBean.getCrc());
                preparedStatement.setString(4, infoBean.getUsername());
                boolean insertNewUserPlayListInfo = insertNewUserPlayListInfo(codeStringBean, infoBean);
                boolean addNewArtist = artistInfo.addNewArtist(artistInfoBean);
                boolean addNewTrack = trackInfo.addNewTrack(trackInfoBean);
                if (insertNewUserPlayListInfo && addNewTrack && addNewArtist) {
                    int executeUpdate = preparedStatement.executeUpdate();
                    if (executeUpdate > 0) {

                        return true;
                    } else {
                        trackInfo.reverseInfo(codeStringBean.getTrack_ID());
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                trackInfo.reverseInfo(codeStringBean.getTrack_ID());
                Logger.getLogger(CodeStringInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public String checkCodeString(CodeStringBean codeStringBean, UserInfoBean infoBean) {
        try {

            connection = new DatabaseConnection().getConnection();
            String check_Query = "SELECT * FROM codestringinfo WHERE codestring = ? and username = ? and crc = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(check_Query);
            prepareStatement.setString(1, codeStringBean.getCode_String());
            prepareStatement.setString(2, infoBean.getUsername());
            prepareStatement.setLong(3, codeStringBean.getCrc());
            ResultSet executeQuery = prepareStatement.executeQuery();

            if (executeQuery.next()) {
                String codeString = executeQuery.getString("codestring");
                String username = executeQuery.getString("username");
                long crc = executeQuery.getLong("crc");
                if (codeString.equals(codeStringBean.getCode_String()) && username.equals(infoBean.getUsername()) && crc == codeStringBean.getCrc()) {
                    updateTrackCount(codeStringBean, infoBean);
                    return "";

                } else {
                    JOptionPane.showMessageDialog(null, "inside checkCodeString() else part");
                    return "na";
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(CodeStringInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "na";
    }

    public void updateTrackCount(CodeStringBean codeStringBean, UserInfoBean infoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String updateTrack_Count = "UPDATE userplaylistinfo SET count = ? WHERE trackid = ? AND username = ?";
            UserPlayListBean trackCount = getTrackCount(codeStringBean, infoBean);
            if (trackCount != null) {
                PreparedStatement prepareStatement = connection.prepareStatement(updateTrack_Count);
                prepareStatement.setInt(1, (trackCount.getCount()) + 1);
                prepareStatement.setString(2, codeStringBean.getTrack_ID());
                prepareStatement.setString(3, infoBean.getUsername());
                prepareStatement.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Cannot Retrieve Count from database");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CodeStringInfo.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    private boolean insertNewUserPlayListInfo(CodeStringBean codeStringBean, UserInfoBean infoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String insert_UserPlaylist = "INSERT into userplaylistinfo ( trackid , username , count ) VALUES (?,?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(insert_UserPlaylist);
            prepareStatement.setString(1, codeStringBean.getTrack_ID());
            prepareStatement.setString(2, infoBean.getUsername());
            prepareStatement.setInt(3, 1);
            int executeUpdate = prepareStatement.executeUpdate();
            if (executeUpdate > 0) {
                return true;
            } else {
                trackInfo.reverseInfo(codeStringBean.getTrack_ID());

                return false;
            }

        } catch (SQLException ex) {
            trackInfo.reverseInfo(codeStringBean.getTrack_ID());

            Logger.getLogger(CodeStringInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;


    }

    public UserPlayListBean getTrackCount(CodeStringBean codeStringBean, UserInfoBean infoBean) {
        UserPlayListBean listBean = new UserPlayListBean();
        try {

            connection = new DatabaseConnection().getConnection();
            String count_Query = "SELECT count FROM userplaylistinfo WHERE username=? AND trackid =?";
            PreparedStatement prepareStatement = connection.prepareStatement(count_Query);
            prepareStatement.setString(1, infoBean.getUsername());
            prepareStatement.setString(2, codeStringBean.getTrack_ID());
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                listBean.setCount(Integer.parseInt(executeQuery.getString("count")));
            } else {
                JOptionPane.showMessageDialog(null, "Cannot Retrieve Count from database");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Retrieve Count from database");
        }
        return listBean;



    }
}
