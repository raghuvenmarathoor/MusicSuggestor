/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.settings;

import com.echo.gui.EchoPlayer;
import com.echo.util.AppVariables;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author New User
 */
public class checkSettings {
      public static AppSettings mySettings = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
        mySettings = new AppSettings();
        File settingsFile = new File("./settings/appset.ser");
        if(settingsFile.exists() == false){
           
           
            SettingsGUI mySettingFrame = new SettingsGUI("First Time Settings");
            mySettingFrame.setVisible(true);
            
            try {
                mySettings = SettingsManager.getSettings();
                AppVariables.username = mySettings.username;
                AppVariables.password = mySettings.password;
                AppVariables.Code_Generator_Exe = mySettings.codegenLocation;
        
                AppVariables.AllowIdentification = mySettings.enableCodegen;
            } catch (FileNotFoundException ex) {
                
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                mySettings = SettingsManager.getSettings();
                AppVariables.username = mySettings.username;
                AppVariables.password = mySettings.password;
                AppVariables.Code_Generator_Exe = mySettings.codegenLocation;
                //JOptionPane.showMessageDialog(null, AppVariables.username+AppVariables.Code_Generator_Exe);
                AppVariables.AllowIdentification = mySettings.enableCodegen;
            } catch (FileNotFoundException ex) {
                
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            initialisePlayer();
        }
        
    }
    public static void initialisePlayer(){
        try {
                mySettings = SettingsManager.getSettings();
                EchoPlayer myPlayer = new EchoPlayer();
                myPlayer.setVisible(true);
                
            } catch (FileNotFoundException ex) {
                //JOptionPane.showMessageDialog(this,"settings error! File not found");
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EchoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
