package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop2.ui.loaders.LoadDetallesPedido;

public class MouseAdapterOrderEmpaquetadoListPanel {

	public static MouseAdapter get(Container panel, String view, Order o, Container nextContainer,
			JLabel id, JLabel fecha, JLabel nArticulos, JLabel peso) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((JScrollPane) nextContainer).setViewportView(LoadDetallesPedido.load(o, nextContainer.getWidth()));
				id.setText(Integer.toString(o.getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				fecha.setText(sf.format(o.getDateReceived()));
				nArticulos.setText(Integer.toString(new OrderController(o).getNumberOfItems()));
				peso.setText(Double.toString(new OrderController().getWeight(o)) + " Kg");
				((CardLayout) panel.getLayout()).show(panel, view);
			}
		};
	}

}
