package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterOrderListPanel;
import com.myshop2.ui.panels.DefaultListPanel;
import com.myshop2.ui.panels.DefaultListPanelSmall;
import com.myshop2.ui.panels.FullSimpleMessagePanel;

public class LoadPedidosInContainer {

	private static int nOrders = 0;

	public static Container load(List<Order> orders, int width, Container detail, String detailView, Container nextContainer,
									WarehouseAPP app) { //JLabel id, JLabel fecha, JLabel nArticulos, JLabel peso
		nOrders = 0;
		Container cont = new Container();
		if(orders.size()==0) {
			return new FullSimpleMessagePanel("No hay pedidos");
		}

		for (Order o : orders) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMM dd, HH:mm aa", new Locale("es", "ES"));
			String aux = "";
			if(new OrderController(o).getNumberOfItems() > 1)
				aux = " objetos.";
			else 
				aux = " objeto.";
			pp.setTitle("PE." + o.getID())
				.setDateInfo(df.format(o.getDateReceived()))
				.setDescLine1(new OrderController(o).getNumberOfItems() + aux)
				.activeIndicator(false);

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
		app.getPedidosTitleCounter().setText("Pedidos ("+numberOfOrders()+")");
		app.getLlblPedidosLink().setText("Pedidos ("+LoadPedidosInContainer.numberOfOrders()+")");
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
