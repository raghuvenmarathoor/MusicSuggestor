/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.db;

import com.echo.bean.UserInfoBean;
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
public class LoginInfo {

    Connection connection;

    public boolean checkLogin(UserInfoBean infoBean) {
        try {
            connection = new DatabaseConnection().getConnection();
            String login_Query = "SELECT username, PASSWORD FROM logininfo WHERE "
                    + "username = ? AND PASSWORD =?";
            PreparedStatement prepareStatement = connection.prepareStatement(login_Query);
            prepareStatement.setString(1, infoBean.getUsername());
            prepareStatement.setString(2, infoBean.getPassword());
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                if (username != null && !username.equalsIgnoreCase("")
                        && password != null && !password.equalsIgnoreCase("")) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(LoginInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
