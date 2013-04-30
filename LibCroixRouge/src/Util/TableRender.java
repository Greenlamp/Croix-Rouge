/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableRender extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Object o = table.getValueAt(row, column);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        if(o != null && component instanceof JLabel){
            JLabel label = (JLabel) component;
            label.setHorizontalAlignment(CENTER);
            Font f = label.getFont();
            f = new Font(f.getName(), Font.BOLD, (f.getSize()));
            label.setBackground(Color.BLACK);
            label.setForeground(Color.BLACK);
            label.setFont(f);
        }

        if(row == 0){
            Color clr = Color.YELLOW;
            component.setBackground(clr);
        }else{
            Color clr = Color.WHITE;
            component.setBackground(clr);
        }

        if(column == 0){
            Color clr = Color.LIGHT_GRAY;
            component.setBackground(clr);
        }

        if(row == 1 && column == 0){
            Color clr = Color.YELLOW;
            component.setBackground(clr);
        }
        if(row == 2 || row == 6 || row == 10 || row == 14){
            Color clr = Color.GRAY;
            component.setBackground(clr);
        }
        if((row == 3 || row == 4)  && column >0){
            Color clr = Color.ORANGE;
            component.setBackground(clr);
        }
        if((row == 7 || row == 8)  && column >0){
            Color clr = Color.ORANGE;
            component.setBackground(clr);
        }
        if((row == 11 || row == 12)  && column >0){
            Color clr = Color.ORANGE;
            component.setBackground(clr);
        }
        if((row == 15 || row == 16)  && column >0){
            Color clr = Color.ORANGE;
            component.setBackground(clr);
        }

        if(o != null && component instanceof JLabel){
            if(row != 2 && row != 6 && row != 10 && row != 14 && column > 0){
                JLabel label = (JLabel) component;
                if(!label.getText().isEmpty()){
                    Color clr = Color.WHITE;
                    component.setBackground(clr);
                    setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    label.setHorizontalAlignment(CENTER);
                    Font f = label.getFont();
                    f = new Font(f.getName(), Font.BOLD, (f.getSize()));
                    label.setForeground(Color.BLACK);
                    label.setFont(f);
                }
            }
        }


        return component;
    }
}
