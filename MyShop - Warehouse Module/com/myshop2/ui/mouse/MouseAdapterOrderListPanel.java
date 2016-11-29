package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.session.Session;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.loaders.LoadDetallesPedido;

public class MouseAdapterOrderListPanel {

	public static MouseAdapter get(Container panel, String view, Order o, Container nextContainer,
			WarehouseAPP app) { //JLabel id, JLabel fecha, JLabel nArticulos, JLabel peso
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((JScrollPane) nextContainer).setViewportView(LoadDetallesPedido.load(o, nextContainer.getWidth()));
				app.getLblIdpedido().setText(Integer.toString(o.getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				app.getLblFecha_1().setText(sf.format(o.getDateReceived()));
				app.getLblNarticulos().setText(Integer.toString(new OrderController(o).getNumberOfItems()));
				app.getLblPeso_1().setText(Double.toString(new OrderController().getWeight(o)) + " Kg");
				if(Session.isAlmaceneroOccupied)
					app.getLblGenerarOt().setEnabled(false);
				else 
					app.getLblGenerarOt().setEnabled(true);
				((CardLayout) panel.getLayout()).show(panel, view);
			}
		};
	}

	public static MouseAdapter getFromTable(Container panel, String view, JTable table, Container nextContainer,
			WarehouseAPP app) { //JLabel id, JLabel fecha, JLabel nArticulos, JLabel peso
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				@SuppressWarnings("unchecked")
				Order o = ((DefaultNonEditableTableModel<OrderController>) table.getModel()).getObjectAtRow(table.getSelectedRow()).getOrder();
				Session.order = o;
				((JScrollPane) nextContainer).setViewportView(LoadDetallesPedido.loadAsTable(o, nextContainer.getWidth()));
				app.getLblIdpedido().setText(Integer.toString(o.getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				app.getLblFecha_1().setText(sf.format(o.getDateReceived()));
				app.getLblNarticulos().setText(Integer.toString(new OrderController(o).getNumberOfItems()));
				app.getLblPeso_1().setText(Double.toString(new OrderController().getWeight(o)) + " Kg");
				if(Session.isAlmaceneroOccupied)
					app.getLblGenerarOt().setEnabled(false);
				((CardLayout) panel.getLayout()).show(panel, view);
			}
		};
	}
}
