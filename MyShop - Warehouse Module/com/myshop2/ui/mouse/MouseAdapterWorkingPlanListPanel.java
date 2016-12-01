package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.ShipmentController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.loaders.LoadCajasDisponibles;
import com.myshop2.ui.loaders.LoadDetallesWorkingPlan;

public class MouseAdapterWorkingPlanListPanel {

	public static MouseAdapter get(WorkingPlan wp, WarehouseAPP app) { 
		
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((JScrollPane) app.getScPaneOTIndiv()).setViewportView(LoadDetallesWorkingPlan.load(wp, app.getScPaneOTIndiv().getWidth()));
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
				WorkingPlanController wpc = ((DefaultNonEditableTableModel<WorkingPlanController>) table.getModel()).getObjectAtRow(table.getSelectedRow());
				((JScrollPane) app.getScPaneOTIndiv()).setViewportView(LoadDetallesWorkingPlan.loadFromTable(wpc, app.getScPaneOTIndiv().getWidth()));
				LoadDetallesWorkingPlan.updateTable(wpc);
				app.updateIncidenceIcon(wpc.getWp().incidence);
				app.getIdPedidoOTIndv().setText(Integer.toString(wpc.getWp().getID()));
				SimpleDateFormat sf = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");
				app.getFechaOTIndv().setText(sf.format(new Date()));
				app.getArticulosOTIdnv().setText(Integer.toString(wpc.getNumberOfItems()));
				app.getPesoOTIndv().setText(Double.toString(wpc.getTotalWeight()) + " Kg");
				((CardLayout) app.getContentPane().getLayout()).show(app.getContentPane(), "vistaOTIndividual");
			}
		};
	}

}
