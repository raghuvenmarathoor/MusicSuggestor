/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.render;

import com.echo.db.SuggestionInfoBean;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Staff
 */
public class SuggestionListTableCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
if(value instanceof URL){
    URL url =(URL) value;
    url.getQuery();
    url.getFile();
    url.getPath();
    url.getAuthority();
    url.getRef();
    url.toString();
    if(url.toString().equalsIgnoreCase("http://google.com")){
          label.setText("google");
    } else if(url.toString().equalsIgnoreCase("http://yahoo.com")){
          label.setText("yahoo");
    }
      
}
        

        return label;
    }
}
