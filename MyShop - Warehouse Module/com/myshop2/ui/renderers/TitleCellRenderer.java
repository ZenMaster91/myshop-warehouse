package com.myshop2.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.myshop2.ui.fonts.SystemFont;

public class TitleCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
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
		
		return this;
	}

}
