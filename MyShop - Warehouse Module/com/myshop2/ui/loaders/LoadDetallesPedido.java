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
import com.myshop2.maths.DimensionsInterpreter;
import com.myshop2.ui.panels.DefaultListPanelSmall;
import com.myshop2.ui.renderers.BodyCellRenderer;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadDetallesPedido {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<OrderItemController> model;

	public static JTable loadAsTable(Order order, int width) {
		JTable table = new JTable();
		String[] columNames = { "ID Producto", "Cantidad", "Peso", "Dimensiones",  "U. Empa." };
		model = new DefaultNonEditableTableModel<>(columNames, 5);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Producto").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Cantidad").setCellRenderer(new BodyCellRenderer());
		table.getColumn("Peso").setCellRenderer(new BodyCellRenderer());
		table.getColumn("Dimensiones").setCellRenderer(new BodyCellRenderer());
		table.getColumn("U. Empa.").setCellRenderer(new BodyCellRenderer());
		table.getColumn("ID Producto").setMinWidth(50);
		table.getColumn("Cantidad").setMaxWidth(50);
		table.getColumn("Peso").setMaxWidth(50);
		table.getColumn("Dimensiones").setMinWidth(100);
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
				model.addRow(new OrderItemController(oi), oi.getProduct().getID(), oi.getQuantity(),
						oi.getProduct().getWeight(), new DimensionsInterpreter(oi.getProduct().getDimensions()).toString(),
						oi.itemsPackaged);
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
