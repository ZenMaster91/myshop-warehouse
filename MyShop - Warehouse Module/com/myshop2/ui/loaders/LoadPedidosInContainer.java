package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop2.ui.panels.DefaultListPanel;

public class LoadPedidosInContainer {

	private static int nOrders = 0;

	public static Container load(List<Order> orders, int width) {
		nOrders = 0;
		Container cont = new Container();

		for (Order o : orders) {
			DefaultListPanel pp = new DefaultListPanel();

			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMMM dd, HH:mm aa", new Locale("es", "ES"));
			pp.setTitle("Pedido: " + o.getID())
				.setDateInfo(df.format(o.getDateReceived()))
				.setDescLine1("Nº Objetos: " + new OrderController(o).getNumberOfItems())
				.setDescLine2("Peso: " + new OrderController().getWeight(o) + " Kg")
				.activeIndicator(false);
			
			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Mouse Clicked " + pp.getTitle().getText());
				}
			});

			// Adding the panel to the container.
			cont.add(pp);
			nOrders++;
		}

		for (int i = cont.getComponentCount(); i < 7; i++) {
			DefaultListPanel pp = new DefaultListPanel();

			// Set values according with the order.
			pp.getTitle().setVisible(false);
			pp.getDateMoreInfo().setVisible(false);
			pp.getDescLine1().setVisible(false);
			pp.getDescLine2().setVisible(false);
			pp.getIndicator().setVisible(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));

			// Adding the panel to the container.
			cont.add(pp);
		}

		int size = Math.max(7, cont.getComponentCount());
		cont.setLayout(new GridLayout(size, 0));
		return cont;
	}

	public static int numberOfOrders() {
		return nOrders;
	}

}
