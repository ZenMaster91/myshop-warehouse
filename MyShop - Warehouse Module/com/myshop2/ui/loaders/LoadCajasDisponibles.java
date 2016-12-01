package com.myshop2.ui.loaders;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.warehouse.controllers.MailBoxController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadCajasDisponibles {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<MailBoxController> model;

	public static JTable loadAsTable(List<MailBoxController> boxes) {
		JTable table = new JTable();
		String[] columNames = { "ID Caja"};
		model = new DefaultNonEditableTableModel<>(columNames, 1);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Caja").setCellRenderer(new TitleCellRenderer());
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		updateTable(boxes);
		return table;
	}

	public static void updateTable(List<MailBoxController> boxes) {
		model.removeAll();
		nOrders = 0;
		for (MailBoxController box : boxes) {
			try {
				model.addRow(box, box.getMailBox().getID());
				nOrders++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.fireTableDataChanged();
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
