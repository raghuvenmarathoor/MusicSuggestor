/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.db;

import com.echo.util.AppContants;
import com.echo.util.AppVariables;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Staff
 */
public class DatabaseConnection {

    Connection connection = null;

    public Connection getConnection() {

        if (connection == null) {
            createConnection();
        } else {
            return connection;
        }
        return connection;
    }

    private void createConnection() {
        try {
            Class.forName(AppContants.JDBC_MYSQL_DRIVER);
            connection = DriverManager.getConnection(AppVariables.MYSQL_URL, AppVariables.MYSQL_USERNAME, AppVariables.MYSQL_PASSWORD);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
