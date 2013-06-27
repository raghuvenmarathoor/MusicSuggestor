/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SuggestionJFrame.java
 *
 * Created on Mar 26, 2013, 5:27:20 PM
 */
package com.echo.gui;

import com.echo.db.ArtistInfo;
import com.echo.db.SuggestionInfoBean;
import com.echo.render.SuggestionListTableCellRender;
import com.echo.util.AppContants;
import com.echo.util.Search;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Staff
 */
public class SuggestionJFrame extends javax.swing.JFrame {

    ArtistInfo artistInfo;
    ArrayList<SuggestionInfoBean> suggestSongs;
    String selectedItem = "";

    /** Creates new form SuggestionJFrame */
    public SuggestionJFrame() {

        initComponents();
        SuggestionjComboBox.setSelectedItem(AppContants.SUGGESTION_POPULAR);
        SearchjComboBox.setSelectedItem("Google");
        SuggestionjTable.getColumn("Search").setCellRenderer(new SuggestionListTableCellRender());

        SearchjComboBox.addItem("Google");
        SearchjComboBox.addItem("Yahoo");
        SuggestionjComboBox.addItem("Popular");
        SuggestionjComboBox.addItem("User Favourites");

        suggestSongs = new ArrayList();



    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        SuggestionjTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        SuggestionjComboBox = new javax.swing.JComboBox();
        SearchjComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Echo Suggestions");
        setName("Suggestions"); // NOI18N
        setResizable(false);

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        SuggestionjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL No:", "Artist Name", "Track Name", "Search"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SuggestionjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuggestionjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SuggestionjTable);
        SuggestionjTable.getColumnModel().getColumn(0).setResizable(false);
        SuggestionjTable.getColumnModel().getColumn(3).setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        SuggestionjComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SuggestionjComboBoxItemStateChanged(evt);
            }
        });

        SearchjComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SearchjComboBoxItemStateChanged(evt);
            }
        });

        jLabel1.setText("Suggestion");

        jLabel2.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SuggestionjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(SuggestionjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SearchjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void SearchjComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SearchjComboBoxItemStateChanged
    // TODO add your handling code here:
    String search = SearchjComboBox.getSelectedItem().toString();
    artistInfo = new ArtistInfo();
    if (selectedItem.equalsIgnoreCase(AppContants.SUGGESTION_POPULAR)) {
        suggestSongs = artistInfo.getPopularSongs();
        setTable(search, suggestSongs);
    } else if (selectedItem.equalsIgnoreCase(AppContants.SUGGESTION_FAV)) {
        suggestSongs = artistInfo.getUserFavSongs();
        setTable(search, suggestSongs);
    }




}//GEN-LAST:event_SearchjComboBoxItemStateChanged

private void SuggestionjComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SuggestionjComboBoxItemStateChanged

    String search = SearchjComboBox.getSelectedItem().toString();
    if (SuggestionjComboBox.getSelectedIndex() != -1) {
        selectedItem = (String) SuggestionjComboBox.getSelectedItem();
        artistInfo = new ArtistInfo();
        if (selectedItem.equalsIgnoreCase(AppContants.SUGGESTION_POPULAR)) {
            suggestSongs = artistInfo.getPopularSongs();
            //    addToSuggestionTable(suggestSongs);
            setTable(search, suggestSongs);
        } else if (selectedItem.equalsIgnoreCase(AppContants.SUGGESTION_FAV)) {
            suggestSongs = artistInfo.getUserFavSongs();
            // addToSuggestionTable(suggestSongs);
            setTable(search, suggestSongs);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select type of Suggestions");
    }


}//GEN-LAST:event_SuggestionjComboBoxItemStateChanged

private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_jScrollPane1MouseClicked

private void SuggestionjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuggestionjTableMouseClicked
// TODO add your handling code here:
    if (SearchjComboBox.getSelectedIndex() != - 1 && evt.getClickCount() == 2) {
        String artistName = (String) SuggestionjTable.getValueAt(SuggestionjTable.getSelectedRow(), 1);
        String trackName = (String) SuggestionjTable.getValueAt(SuggestionjTable.getSelectedRow(), 2);

        String selectedSearch = (String) SearchjComboBox.getSelectedItem();
        String googleUrl = "https://www.google.com/search?q=";
        String yahooUrl = "http://search.yahoo.com/search?";
        if (selectedSearch.equalsIgnoreCase("Google")) {

            //https://www.google.com/search?q=search+engine+optimisation;  // link for search query to google
            //  com.search();
            new Search().SearchInternet(googleUrl, selectedSearch, artistName, trackName);

        } else if (selectedSearch.equalsIgnoreCase("Yahoo")) {
            new Search().SearchInternet(yahooUrl, selectedSearch, artistName, trackName);
        }



    }



}//GEN-LAST:event_SuggestionjTableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuggestionJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuggestionJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuggestionJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuggestionJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new SuggestionJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox SearchjComboBox;
    private javax.swing.JComboBox SuggestionjComboBox;
    private javax.swing.JTable SuggestionjTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void setTable(String search, ArrayList<SuggestionInfoBean> suggestSongs) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) SuggestionjTable.getModel();
        defaultTableModel.getDataVector().removeAllElements();
        Object[] cols = new Object[4];

        if (suggestSongs != null) {
            for (int i = 0; i < suggestSongs.size(); i++) {
                cols[0] = i + 1;
                cols[1] = suggestSongs.get(i).getArtistName();
                cols[2] = suggestSongs.get(i).getTitleName();
                try {
                    cols[3] = new URL("http://" + search + ".com");
                } catch (MalformedURLException ex) {
                    Logger.getLogger(SuggestionJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                defaultTableModel.addRow(cols);
            }


        }
    }
}