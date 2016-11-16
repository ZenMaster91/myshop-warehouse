package com.myshop2.ui.mouse;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop2.ui.WarehouseAPP;
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

}
