package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.myshop.model.order.Order;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.session.Session;
import com.myshop2.ui.WarehouseAPP;
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
	
	public static MouseAdapter getFromTable(JTable table, WarehouseAPP app) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				@SuppressWarnings("unchecked")
				OrderController oc = ((DefaultNonEditableTableModel<OrderController>) table.getModel()).getObjectAtRow(table.getSelectedRow());
				Session.order = oc.getOrder();
				((JScrollPane) app.getScPaneEmpaquetadoIndv()).setViewportView(LoadDetallesPedido.loadAsTable(oc.getOrder(), 0));
				app.getIdPedidoEmpInd().setText(Integer.toString(oc.getOrder().getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				app.getFechaEmpInd().setText(sf.format(oc.getOrder().getDateReceived()));
				app.getNarticulosEmpInd().setText(Integer.toString(new OrderController(oc.getOrder()).getNumberOfItems()));
				app.getPesoEmpInd().setText(Double.toString(new OrderController().getWeight(oc.getOrder())) + " Kg");
				((CardLayout) app.getContentPane().getLayout()).show( app.getContentPane(), "vistaEmpaquetadoIndividual");
				((CardLayout)app.getPanel_15().getLayout()).show(app.getPanel_15(), "scanner");
			}
		};
	}

}