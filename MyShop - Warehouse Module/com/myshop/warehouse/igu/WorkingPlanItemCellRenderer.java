package com.myshop.warehouse.igu;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.myshop.warehouse.controllers.WorkingPlanItemController;

public class WorkingPlanItemCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		@SuppressWarnings("unchecked")
		DefaultNonEditableTableModel<WorkingPlanItemController> model = (DefaultNonEditableTableModel<WorkingPlanItemController>) table.getModel();
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(row %2 == 0) {
            c.setBackground(Color.WHITE);
            if(isSelected) {
            	c.setBackground(Color.decode("#007AFF"));
            }
        } else {
        	c.setBackground(Color.decode("#F7F7F7"));
        	if(isSelected) {
            	c.setBackground(Color.decode("#007AFF"));
            }
        }
        WorkingPlanItemController wpi = model.getObjectAtRow(row);
     
        if(wpi.getWPI().getOrderItem().getIncidence()!=null && wpi.getWPI().getOrderItem().getIncidence().isSolve()!=true) {
        	c.setBackground(Color.decode("#FFDB4C"));
        	c.setForeground(Color.BLACK);
        	if(isSelected) {
            	c.setBackground(Color.decode("#007AFF"));
            }
        }
        
        return c;
    }

}
