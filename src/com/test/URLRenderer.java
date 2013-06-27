/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Staff
 */
public class URLRenderer extends DefaultTableCellRenderer implements MouseListener, MouseMotionListener {

    private int row = -1;
    private int col = -1;
    private boolean isRollover = false;

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
        if (!table.isEditing() && this.row == row && this.col == column && this.isRollover) {
            setText("< html > < u > < font color='blue' >" + value.toString());
        } else if (hasFocus) {
            setText("< html > < font color='blue' >" + value.toString());
        } else {
            setText(value.toString());
        }
        return this;
    }

    private static boolean isURLColumn(JTable table, int column) {
        return column >= 0 && table.getColumnClass(column).equals(URL.class);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point pt = e.getPoint();
        int prev_row = row;
        int prev_col = col;
        boolean prev_ro = isRollover;
        row = table.rowAtPoint(pt);
        col = table.columnAtPoint(pt);
        isRollover = isURLColumn(table, col); // && pointInsidePrefSize(table, pt);
        if ((row == prev_row && col == prev_col && Boolean.valueOf(isRollover).equals(prev_ro))
                || (!isRollover && !prev_ro)) {
            return;
        }
//*/ @see SwingSet3: HyperlinkCellRenderer.java
        Rectangle repaintRect;
        if (isRollover) {
            Rectangle r = table.getCellRect(row, col, false);
            repaintRect = prev_ro ? r.union(table.getCellRect(prev_row, prev_col, false)) : r;
        } else { //if(prev_ro) {
            repaintRect = table.getCellRect(prev_row, prev_col, false);
        }
        table.repaint(repaintRect);
        /*/
        //TODO: asks JIDE to show benchmark results.
        table.repaint();
        //*/
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        if (isURLColumn(table, col)) {
            table.repaint(table.getCellRect(row, col, false));
            row = -1;
            col = -1;
            isRollover = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point pt = e.getPoint();
        int ccol = table.columnAtPoint(pt);
        if (isURLColumn(table, ccol)) { // && pointInsidePrefSize(table, pt)) {
            int crow = table.rowAtPoint(pt);
            URL url = (URL) table.getValueAt(crow, ccol);
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
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
