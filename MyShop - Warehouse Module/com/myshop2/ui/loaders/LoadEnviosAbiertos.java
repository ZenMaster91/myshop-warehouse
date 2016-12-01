package com.myshop2.ui.loaders;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.warehouse.controllers.ShipmentController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.ui.WarehouseAPP;
import com.myshop2.ui.mouse.MouseAdapterLoadEnvios;
import com.myshop2.ui.panels.FullSimpleMessagePanel;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;

public class LoadEnviosAbiertos {

	private static int nOrders = 0;
	private static DefaultNonEditableTableModel<ShipmentController> model;
	private static Component component;
	static JTable table = new JTable();

	public static Component loadAsTable(List<ShipmentController> shipments, WarehouseAPP app) {
		String[] columNames = { "ID Envio"};
		model = new DefaultNonEditableTableModel<>(columNames, 1);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Envio").setCellRenderer(new TitleCellRenderer());
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.setEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.addMouseListener(MouseAdapterLoadEnvios.getFromTable(table, app));
		
		updateTable(shipments, app);
		return component;
	}

	public static void updateTable(List<ShipmentController> shipments, WarehouseAPP app) {
		nOrders = 0;
		if(model == null || shipments.size() == 0) {
			component = new FullSimpleMessagePanel("No hay envios abiertos");
		} else {
			component = table;
		}
		
		model.removeAll();
		for (ShipmentController shipment : shipments) {
			try {
				model.addRow(shipment, shipment.shipment.getID());
				nOrders++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.fireTableDataChanged();
		updateReferences(app);
		app.getScPaneEnvios().getViewport().setView(component);
	}

	public static int numberOfOrders() {
		return nOrders;
	}
	
	public static void updateReferences(WarehouseAPP app) {
		if(nOrders > 0) {
			app.getLblTitleEnvios().setText("Envios (" + nOrders + ")");
		} else {
			app.getLblTitleEnvios().setText("Envios");
		}
	}

}
