/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Staff
 */
public class Search {

    public void SearchInternet(String siteurl, String selectedSearch, String artistName, String trackName) {

        // https://www.google.com/search?q=search+engine+optimisation;
        // String uri = "https://www.google.com/search?q=";
        if (selectedSearch.equalsIgnoreCase("google")) {
            try {

                URL url = new URL(siteurl + artistName.replaceAll(" ", "+") +"+"+ trackName.replaceAll(" ", "+"));
                System.out.println(url);
                try {
                    //Web Start
                    //BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
                    //bs.showDocument(url);
                    if (Desktop.isDesktopSupported()) { // JDK 1.6.0
                        Desktop.getDesktop().browse(url.toURI());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selectedSearch.equalsIgnoreCase("yahoo")) {
            try {
                URL url = new URL(siteurl + "p=" + artistName.replaceAll(" ", "%20") + trackName.replaceAll(" ", "%20") + "&fr=sfp&pqstr=" + artistName.replaceAll(" ", "%20") + trackName.replaceAll(" ", "%20"));
                System.out.println(url);

                if (Desktop.isDesktopSupported()) {

                    try {
                        // JDK 1.6.0
                        Desktop.getDesktop().browse(url.toURI());

                    } catch (URISyntaxException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }
}
