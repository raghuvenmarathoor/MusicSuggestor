/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.echo.render;

import java.awt.Component;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Staff
 */
public class PlayerListTableCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        if (value instanceof File) {
            File file = (File) value;
            if (column == 0) {
                label.setText(file.getName());
            }

        }
        return label;
    }
}
