package com.myshop2.ui.loaders;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterOrderListPanel;
import com.myshop2.ui.panels.DefaultListPanelSmall;
import com.myshop2.ui.panels.FullSimpleMessagePanel;
import com.myshop2.ui.renderers.BodyCellRenderer;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadPedidosInContainer {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<OrderController> model;
	private static Component component;
	JTable table = new JTable();

	public Component loadAsTable(List<Order> orders, WarehouseAPP app) {
		
		String[] columNames = { "ID Pedido", "Nº Artículos", "Fecha Pedido" };
		model = new DefaultNonEditableTableModel<>(columNames, 3);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Pedido").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Nº Artículos").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Fecha Pedido").setCellRenderer(new BodyCellRenderer());
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(MouseAdapterOrderListPanel.getFromTable(app.getContentPane(), "vistaPedidoIndividual", table, app.getScPedidoIndividual(), app));

		updateTable(orders, app);
		return component;
	}

	public void updateTable(List<Order> orders, WarehouseAPP app) {
		nOrders = 0;
		
		if(model == null || orders.size() == 0) {
			component = new FullSimpleMessagePanel("No hay pedidos");
		} else {
			component = table;
		}
		
		model.removeAll();
		for (Order o : orders) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("MMM dd, HH:mm aa", new Locale("es", "ES"));
				model.addRow(new OrderController(o), o.getID(), new OrderController(o).getNumberOfItems(),
						df.format(o.getDateReceived()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nOrders++;
		}
		model.fireTableDataChanged();
		updateReferences(app);
		app.getScPaneListaPedidos().getViewport().setView(component);
	}

	public static Container load(List<Order> orders, int width, Container detail, String detailView,
			Container nextContainer, WarehouseAPP app) { // JLabel id, JLabel
															// fecha, JLabel
															// nArticulos,
															// JLabel peso
		nOrders = 0;
		Container cont = new Container();
		if (orders.size() == 0) {
			return new FullSimpleMessagePanel("No hay pedidos");
		}

		for (Order o : orders) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMM dd, HH:mm aa", new Locale("es", "ES"));
			String aux = "";
			if (new OrderController(o).getNumberOfItems() > 1)
				aux = " objetos.";
			else
				aux = " objeto.";
			pp.setTitle("PE." + o.getID()).setDateInfo(df.format(o.getDateReceived()))
					.setDescLine1(new OrderController(o).getNumberOfItems() + aux).activeIndicator(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));
			pp.setMaximumSize(new Dimension(width - 5, 44));
			pp.addMouseListener(MouseAdapterOrderListPanel.get(detail, detailView, o, nextContainer, app));

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

		updateReferences(app);
		return cont;
	}

	private static void updateReferences(WarehouseAPP app) {
		if (numberOfOrders() == 0) {
			app.getPedidosTitleCounter().setText("Pedidos");
			app.getLlblPedidosLink().setText("Pedidos");
		} else {
			app.getPedidosTitleCounter().setText("Pedidos (" + numberOfOrders() + ")");
			app.getLlblPedidosLink().setText("Pedidos (" + LoadPedidosInContainer.numberOfOrders() + ")");
		}
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
