package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.ShipmentController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.session.Session;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.loaders.LoadBoxesInShipment;
import com.myshop2.ui.loaders.LoadCajasDisponibles;
import com.myshop2.ui.loaders.LoadDetallesWorkingPlan;

public class MouseAdapterLoadEnvios {

	public static MouseAdapter get(WorkingPlan wp, WarehouseAPP app) {

		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((JScrollPane) app.getScPaneOTIndiv())
						.setViewportView(LoadDetallesWorkingPlan.load(wp, app.getScPaneOTIndiv().getWidth()));
				app.getIdPedidoOTIndv().setText(Integer.toString(wp.getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				app.getFechaOTIndv().setText(sf.format(new Date()));
				app.getArticulosOTIdnv().setText(Integer.toString(new WorkingPlanController(wp).getNumberOfItems()));
				app.getPesoOTIndv().setText(Double.toString(new WorkingPlanController(wp).getTotalWeight()) + " Kg");
				((CardLayout) app.getContentPane().getLayout()).show(app.getContentPane(), "vistaOTIndividual");
			}
		};
	}

	public static MouseAdapter getFromTable(JTable table, WarehouseAPP app) {

		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				@SuppressWarnings("unchecked")
				ShipmentController wp = ((DefaultNonEditableTableModel<ShipmentController>) table.getModel())
						.getObjectAtRow(table.getSelectedRow());
				Session.shipment = wp.shipment;
				((JScrollPane) app.getScPaneOTIndiv())
						.setViewportView(LoadCajasDisponibles.loadAsTable(wp.getAsController()));
				app.getScrollPane_2()
				.setViewportView(LoadCajasDisponibles.loadAsTable(Session.getAvaliableBoxes()));
				app.getScrollPane_1()
				.setViewportView(LoadBoxesInShipment.loadAsTable(new ShipmentController(Session.shipment).getAsController()));	
				app.getLblIdenvio().setText(Integer.toString(wp.shipment.getID()));
				((CardLayout) app.getContentPane().getLayout()).show(app.getContentPane(), "vistaEnvioIndividual");
			}
		};
	}

}
