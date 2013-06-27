/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.util;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Staff
 */
public class AppVariables implements Serializable {

    public static String Code_Generator_Exe = "";
    public static boolean AllowIdentification = false;
    public static String MYSQL_URL = "jdbc:mysql://localhost:3306/echonest";
    public static String MYSQL_USERNAME = "root";
    public static String MYSQL_PASSWORD = "thegreat";
    public static String username = "";
    public static String password = "";
    public static int count = 4;
    public static File[] PLAY_LIST;
    public static int TOTAL_DURATION;
    public static String STATUS;
}
