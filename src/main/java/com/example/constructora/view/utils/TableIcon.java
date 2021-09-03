package com.example.constructora.view.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableIcon extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean bln,
                                                   boolean bln1, int i, int i1) {
        if(o instanceof JButton) {
            return (JButton) o;
        }
        return super.getTableCellRendererComponent(jTable, o, bln, bln1, i, i1);
    }
}
