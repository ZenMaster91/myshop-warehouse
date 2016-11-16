package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterWorkingPlanListPanel;
import com.myshop2.ui.panels.DefaultListPanel;
import com.myshop2.ui.panels.FullSimpleMessagePanel;

public class LoadOTsInContainer {

	private static int nItems = 0;

	public static Container load(List<WorkingPlanController> workingPlans, WarehouseAPP app) {
		System.out.println("WPLoad_1");
		nItems = 0;
		Container cont = new Container();
		if(workingPlans.size()==0) {
			return new FullSimpleMessagePanel("No hay OTs pendientes");
		}
		System.out.println("WPLoad_2");
		for(WorkingPlanController wpc : workingPlans) {
			System.out.println("WPLoad_2.1");
			WorkingPlan wp = wpc.getWp();
			System.out.println("WPLoad_2.2");
			DefaultListPanel pp = new DefaultListPanel();
			System.out.println("WPLoad_2.3");
			System.out.println("OT " + wp.getID() + " Loaded in panel." + wp.getItems().size() + " " + new WorkingPlanController(wp).getTotalWeight() + " " +wp.getItems().size());

			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMMM dd, HH:mm aa", new Locale("es", "ES"));
			System.out.println("WPLoad_2.4");
			pp.setTitle("ID: " + wp.getID())
				.setDateInfo(df.format(new Date()))
				.setDescLine1("Nº Objetos: " + wp.getItems().size() + " Peso: " + new WorkingPlanController(wp).getTotalWeight() + " Kg.")
				.setDescLine2("Nº Pedidos: " + wp.getItems().size())
				.activeIndicator(true);
			System.out.println("WPLoad_2.5");

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMaximumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMaximumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			System.out.println("WPLoad_2.6");
			
			pp.addMouseListener(MouseAdapterWorkingPlanListPanel.get(wp, app));
			System.out.println("WPLoad_2.7");

			// Adding the panel to the container.
			cont.add(pp);
			System.out.println("WPLoad_2.8");
			nItems++;
			System.out.println("WPLoad_2.9");
		}
		System.out.println("WPLoad_3");
		for (int i = cont.getComponentCount(); i < 7; i++) {
			DefaultListPanel pp = new DefaultListPanel();

			// Set values according with the order.
			pp.getTitle().setVisible(false);
			pp.getDateMoreInfo().setVisible(false);
			pp.getDescLine1().setVisible(false);
			pp.getDescLine2().setVisible(false);
			pp.getIndicator().setVisible(false);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMaximumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMinimumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));

			// Adding the panel to the container.
			cont.add(pp);
		}
		System.out.println("WPLoad_4");
		int size = Math.max(7, cont.getComponentCount());
		cont.setLayout(new GridLayout(size, 0));
		System.out.println("WPLoad_5");
		updateReferences(app);
		System.out.println("WPLoad_6");
		return cont;
	}
	
	private static void updateReferences(WarehouseAPP app) {
		app.getLblOrdenes().setText("Órdenes de Trabajo ("+nItems()+")");
		app.getLblOts().setText("OTs ("+nItems()+")");
	}

	public static int nItems() {
		return nItems;
	}

}
