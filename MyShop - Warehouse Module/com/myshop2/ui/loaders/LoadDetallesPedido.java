package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop2.ui.panels.DefaultListPanel;
import com.myshop2.ui.panels.DefaultListPanelSmall;

public class LoadDetallesPedido {

	private static int nOrders = 0;

	public static Container load(Order order, int width) {
		nOrders = 0;
		Container cont = new Container();

		for (OrderItem oi : order.getProducts()) {
			DefaultListPanelSmall pp = new DefaultListPanelSmall();

			// Set values according with the order.
			pp.setTitle(oi.getQuantity() + " x PR." + oi.getProduct().getID())
					.setDescLine1("PA: " + oi.getProductProductLocationCorridor()
								+ "  LA: " + oi.getProductProductLocationSide()
								+ "  PO: " + oi.getProductProductLocationPosition()
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
