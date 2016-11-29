package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controllers.OrderItemController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.panels.DefaultListPanelSmall;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadDetallesPedido {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<OrderItemController> model;

	public static JTable loadAsTable(Order order, int width) {
		JTable table = new JTable();
		String[] columNames = { "ID Producto", "Cantidad", "Pasillo", "Lado", "Posicion", "Altura" };
		model = new DefaultNonEditableTableModel<>(columNames, 6);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Producto").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Cantidad").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Pasillo").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Lado").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Posicion").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Altura").setCellRenderer(new TitleCellRenderer());
		table.getColumn("ID Producto").setMinWidth(50);
		table.getColumn("Lado").setMinWidth(70);
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		updateTable(order);
		return table;
	}

	public static void updateTable(Order o) {
		model.removeAll();
		for (OrderItem oi : o.getProducts()) {
			try {
				model.addRow(new OrderItemController(oi), oi.getID(), oi.getQuantity(),
						oi.getProductProductLocationCorridor(), oi.getProductProductLocationSide(),
						oi.getProductProductLocationPosition(), oi.getProductProductLocationHeight());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.fireTableDataChanged();
	}

	public static Container load(Order order, int width) {
		nOrders = 0;
		Container cont = new Container();

		for (OrderItem oi : order.getProducts()) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			pp.setTitle(oi.getQuantity() + " x PR." + oi.getProduct().getID())
					.setDescLine1("PA: " + oi.getProductProductLocationCorridor() + "  LA: "
							+ oi.getProductProductLocationSide() + "  PO: " + oi.getProductProductLocationPosition()
							+ "  AL: " + oi.getProductProductLocationHeight())
					.activeIndicator(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));
			pp.getDateMoreInfo().setVisible(false);

			// Adding the panel to the container.
			cont.add(pp);
			nOrders++;
		}

		for (int i = cont.getComponentCount(); i < 9; i++) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			pp.getTitle().setVisible(false);
			pp.getDateMoreInfo().setVisible(false);
			pp.getDescLine1().setVisible(false);
			pp.getIndicator().setVisible(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));

			// Adding the panel to the container.
			cont.add(pp);
		}

		int size = Math.max(9, cont.getComponentCount());
		cont.setLayout(new GridLayout(size, 0));
		return cont;
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
