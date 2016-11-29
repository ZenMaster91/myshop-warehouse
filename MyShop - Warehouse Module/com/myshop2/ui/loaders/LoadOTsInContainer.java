package com.myshop2.ui.loaders;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterWorkingPlanListPanel;
import com.myshop2.ui.panels.DefaultListPanel;
import com.myshop2.ui.panels.FullSimpleMessagePanel;
import com.myshop2.ui.renderers.BodyCellRenderer;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadOTsInContainer {

	private static int nItems = 0;
	private static DefaultNonEditableTableModel<WorkingPlanController> model;
	private static Component component;
	JTable table = new JTable();

	public Component loadAsTable(List<WorkingPlanController> workingPlans, WarehouseAPP app) {
		
		String[] columNames = { "ID", "Nº Objetos", "Fecha Creacion" };
		model = new DefaultNonEditableTableModel<>(columNames, 3);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Nº Objetos").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Fecha Creacion").setCellRenderer(new BodyCellRenderer());
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.addMouseListener(MouseAdapterWorkingPlanListPanel.getFromTable(table, app));
		
		updateTable(workingPlans, app);
		return component;
	}
	
	public void updateTable(List<WorkingPlanController> workingPlans, WarehouseAPP app) {
		nItems = 0;
		
		if(model == null || workingPlans.size() == 0) {
			component = new FullSimpleMessagePanel("No hay OT's pendientes");
		} else {
			component = table;
		}
		
		model.removeAll();
		
		for(WorkingPlanController wpc : workingPlans) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("MMM dd, HH:mm aa", new Locale("es", "ES"));
				model.addRow(wpc, wpc.getWp().getID(), wpc.getNumberOfItems(), df.format(wpc.getWp().getGenerated()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nItems++;
		}
		model.fireTableDataChanged();
		updateReferences(app);
		app.getScPaneOTs().getViewport().setView(component);
	}

	public static Container load(List<WorkingPlanController> workingPlans, WarehouseAPP app) {
		nItems = 0;
		Container cont = new Container();
		if(workingPlans.size()==0) {
			updateReferences(app);
			return new FullSimpleMessagePanel("No hay OTs pendientes");
		}
		for(WorkingPlanController wpc : workingPlans) {
			WorkingPlan wp = wpc.getWp();
			DefaultListPanel pp = new DefaultListPanel();
			System.out.println("OT " + wp.getID() + " Loaded in panel." + wp.getItems().size() + " " + new WorkingPlanController(wp).getTotalWeight() + " " +wp.getItems().size());

			// Set values according with the order.
			SimpleDateFormat df = new SimpleDateFormat("MMMM dd, HH:mm aa", new Locale("es", "ES"));
			pp.setTitle("ID: " + wp.getID())
				.setDateInfo(df.format(new Date())) //REMEMBER TO CHANGE THE DATE.
				.setDescLine1("Nº Objetos: " + wp.getItems().size() + " Peso: " + new WorkingPlanController(wp).getTotalWeight() + " Kg.")
				.setDescLine2("Nº Pedidos: " + wp.getItems().size() + "(" + wp.getItems().get(0).getOrderItem().getParent().getID() + ")")
				.activeIndicator(true);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMaximumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			pp.setMaximumSize(new Dimension(app.getScPaneOTs().getWidth() - 5, 80));
			
			pp.addMouseListener(MouseAdapterWorkingPlanListPanel.get(wp, app));
			// Adding the panel to the container.
			cont.add(pp);
			nItems++;
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
		
		int size = Math.max(7, cont.getComponentCount());
		cont.setLayout(new GridLayout(size, 0));

		updateReferences(app);
		return cont;
	}
	
	private static void updateReferences(WarehouseAPP app) {
		if(nItems > 0) {
			app.getLblOrdenes().setText("Órdenes de Trabajo ("+nItems()+")");
			app.getLblOts().setText("OTs ("+nItems()+")");
			app.getLblGenerarOt().setEnabled(false);
		} else {
			app.getLblOrdenes().setText("Órdenes de Trabajo");
			app.getLblOts().setText("OTs");
			app.getLblGenerarOt().setEnabled(true);
		}
	}

	public static int nItems() {
		return nItems;
	}

}
