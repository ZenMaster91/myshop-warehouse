package com.myshop2.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;

import com.myshop.warehouse.controllers.WorkingPlanItemController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.fonts.SystemFont;

public class TitleCellRendererWorkingPlanItem extends TitleCellRenderer {

	
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		@SuppressWarnings("unchecked")
		DefaultNonEditableTableModel<WorkingPlanItemController> model = (DefaultNonEditableTableModel<WorkingPlanItemController>) table.getModel();
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		

		if (row % 2 == 0) {
			c.setBackground(Color.WHITE);
			if (isSelected) {
				c.setBackground(Color.decode("#007AFF"));
			}
		} else {
			c.setBackground(Color.decode("#F7F7F7"));
			if (isSelected) {
				c.setBackground(Color.decode("#007AFF"));
			}
		}
		setHorizontalAlignment(JLabel.CENTER);
		setFont(SystemFont.headline);
		
		 WorkingPlanItemController wpi = model.getObjectAtRow(row);
		 if(wpi.getWPI().getCollected()) {
			 c.setBackground(Color.decode("#E0F8D8"));
	        	c.setForeground(Color.BLACK);
	        	if(isSelected) {
	            	c.setBackground(Color.decode("#007AFF"));
	            }
		 }
		return this;
	}

}
