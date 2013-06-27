/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author New User
 */
public class SettingsManager {
    
     public static AppSettings getSettings() throws FileNotFoundException, IOException, ClassNotFoundException{
         
        FileInputStream fis = new FileInputStream("./settings/appset.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (AppSettings)ois.readObject();
    }
    public static void setSettings(AppSettings settoSave) throws IOException{
        FileOutputStream fos = new FileOutputStream("./settings/appset.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(settoSave);
        oos.flush();
        oos.close();
    }
}
