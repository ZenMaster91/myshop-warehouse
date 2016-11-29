package com.myshop2.ui.mouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyMouseAdapter extends MouseMotionAdapter {

	int itsRow = 0;

	public void mouseMoved(MouseEvent e) {
		JTable aTable = (JTable) e.getSource();
		itsRow = aTable.rowAtPoint(e.getPoint());
		aTable.repaint();
	}
	
	public class AttributiveCellRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public AttributiveCellRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (row == itsRow) {
				this.setBackground(Color.red);
				this.setForeground(Color.blue);
			} else {
				this.setBackground(Color.cyan);
				this.setForeground(Color.darkGray);
			}
			String aStr = "Row " + row + "Column" + column;
			this.setText(aStr);
			return this;
		}
	}
}
