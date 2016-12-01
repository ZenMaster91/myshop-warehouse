package com.myshop2.ui.loaders;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterOrderEmpaquetadoListPanel;
import com.myshop2.ui.panels.DefaultListPanelSmall;
import com.myshop2.ui.panels.FullSimpleMessagePanel;
import com.myshop2.ui.renderers.BodyCellRenderer;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadPedidosInEmpaquetado {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<OrderController> model;
	private static Component component;
	JTable table = new JTable();

	public Component loadAsTable(List<Order> orders, WarehouseAPP app) {

		String[] columNames = { "ID Pedido", "Nº Objetos", "Fecha Pedido" };
		model = new DefaultNonEditableTableModel<>(columNames, 3);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Pedido").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Nº Objetos").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Fecha Pedido").setCellRenderer(new BodyCellRenderer());
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(MouseAdapterOrderEmpaquetadoListPanel.getFromTable(table, app));

		updateTable(orders, app);
		return component;
	}

	private void updateTable(List<Order> orders, WarehouseAPP app) {
		nOrders = 0;
		
		if (model == null || orders.size() == 0) {
			component = new FullSimpleMessagePanel("No hay pedidos para empaquetar");
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
		app.getScPaneEmpaquetado().getViewport().setView(component);
	}

	public static Container load(List<Order> orders, int width, Container detail, String detailView,
			Container nextContainer, JLabel id, JLabel fecha, JLabel nArticulos, JLabel peso) {
		System.out.println("L" + 1);
		nOrders = 0;
		Container cont = new Container();
		if (orders.size() == 0) {
			return new FullSimpleMessagePanel("Nada por empaquetar");
		}
		System.out.println("L" + 2);

		for (Order o : orders) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			System.out.println("L" + 3);
			System.out.println(
					"Order id: " + o.getID() + " n items: " + o.getProducts().size() + " date" + o.getDateReceived());
			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMMM dd, HH:mm aa", new Locale("es", "ES"));
			pp.setTitle("Pedido: " + o.getID()).setDateInfo(df.format(o.getDateReceived()))
					.setDescLine1("Nº Objetos: " + new OrderController(o).getNumberOfItems())
					.setDescLine2("Peso: " + new OrderController().getWeight(o) + " Kg").activeIndicator(false);

			System.out.println("L" + 4);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.addMouseListener(MouseAdapterOrderEmpaquetadoListPanel.get(detail, detailView, o, nextContainer, id,
					fecha, nArticulos, peso));

			System.out.println("L" + 5);

			// Adding the panel to the container.
			cont.add(pp);
			nOrders++;
		}

		for (int i = cont.getComponentCount(); i < 14; i++) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			pp.getTitle().setVisible(false);
			pp.getDateMoreInfo().setVisible(false);
			pp.getDescLine1().setVisible(false);
			pp.getIndicator().setVisible(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));

			// Adding the panel to the container.
			cont.add(pp);
		}

		int size = Math.max(14, cont.getComponentCount());
		cont.setLayout(new GridLayout(size, 0));
		return cont;
	}

	private static void updateReferences(WarehouseAPP app) {
		if (numberOfOrders() == 0) {
			app.getLblEmpaquetar_1().setText("Empaquetado");
			app.getLabel_1_1().setText("Empaquetado");
		} else {
			app.getLblEmpaquetar_1().setText("Empaquetado (" + numberOfOrders() + ")");
			app.getLabel_1_1().setText("Empaquetado (" + numberOfOrders() + ")");
		}
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
