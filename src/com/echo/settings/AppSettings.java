/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.settings;

import com.echo.util.AppVariables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author New User
 */
public class AppSettings implements Serializable {
    
    public boolean enableCodegen = false;
    public String codegenLocation = "abc";
    public String username = "admin";
    public String password = "admin";
    
//    public static AppSettings getSettings() throws FileNotFoundException, IOException, ClassNotFoundException{
//        FileInputStream fis = new FileInputStream("./settings/appset.ser");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        return (AppSettings)ois.readObject();
//    }
//    public void setSettings(AppSettings settoSave) throws IOException{
//        FileOutputStream fos = new FileOutputStream("./settings/appset.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(settoSave);
//        //AppSettings mySet = new AppSettings();
//        
//    }
}
