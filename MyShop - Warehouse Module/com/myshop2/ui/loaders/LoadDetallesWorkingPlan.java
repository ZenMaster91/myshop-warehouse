package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.myshop.model.order.OrderItem;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.controllers.WorkingPlanItemController;
import com.myshop.warehouse.igu.DefaultNonEditableTableModel;
import com.myshop2.session.Session;
import com.myshop2.ui.panels.DefaultListPanel;
import com.myshop2.ui.renderers.BodyCellRenderer;
import com.myshop2.ui.renderers.Caption2CellRenderer;
import com.myshop2.ui.renderers.TitleCellRenderer;
import com.myshop2.ui.renderers.TitleCellRendererWorkingPlanItem;

public class LoadDetallesWorkingPlan {

	private static int nOrders = 0;
	public static DefaultNonEditableTableModel<WorkingPlanItemController> model;

	public static JTable loadFromTable(WorkingPlanController workingPlanController, int width) {
		Session.workingPlan = workingPlanController.getWp();
		Session.workingPlanController = workingPlanController;
		JTable table = new JTable();
		String[] columNames = { "ID Prod.", "Cant.", "Pasillo", "Lado", "Posicion", "Altura", "Recog." };
		model = new DefaultNonEditableTableModel<>(columNames, 6);
		table.setModel(model);
		table.setRowHeight(30);
		table.getColumn("ID Prod.").setCellRenderer(new TitleCellRendererWorkingPlanItem());
		table.getColumn("Cant.").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Recog.").setCellRenderer(new TitleCellRenderer());
		table.getColumn("Pasillo").setCellRenderer(new BodyCellRenderer());
		table.getColumn("Lado").setCellRenderer(new BodyCellRenderer());
		table.getColumn("Posicion").setCellRenderer(new BodyCellRenderer());
		table.getColumn("Altura").setCellRenderer(new BodyCellRenderer());
		table.getColumn("ID Prod.").setMinWidth(50);
		table.getColumn("Lado").setMinWidth(65);
		table.getTableHeader().setDefaultRenderer(new Caption2CellRenderer());
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		updateTable(workingPlanController);
		return table;

	}

	public static void updateTable(WorkingPlanController workingPlanController) {
		Session.workingPlan = workingPlanController.getWp();
		Session.workingPlanController = workingPlanController;
		model.removeAll();
		for (WorkingPlanItem wpi : workingPlanController.getWp().getItems()) {
			try {
				model.addRow(new WorkingPlanItemController(wpi), wpi.getOrderItem().getProduct().getID(),
						wpi.getOrderItem().getQuantity(), wpi.getOrderItem().getProductProductLocationCorridor(),
						wpi.getOrderItem().getProductProductLocationSide(), wpi.getOrderItem().getProductProductLocationPosition(),
						wpi.getOrderItem().getProductProductLocationHeight(), wpi.getItemsCollected());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.fireTableDataChanged();
	}

	public static Container load(WorkingPlan workingPlan, int width) {
		nOrders = 0;
		Container cont = new Container();

		for (WorkingPlanItem wpi : workingPlan.getItems()) {
			DefaultListPanel pp = new DefaultListPanel();
			OrderItem oi = wpi.getOrderItem();
			// Set values according with the order.
			pp.setTitle("P." + oi.getID() + "   Cant.: " + oi.getQuantity())
					.setDescLine1("Pasillo: " + oi.getProductProductLocationCorridor() + "  Lado: "
							+ oi.getProductProductLocationSide() + "  Posición: "
							+ oi.getProductProductLocationPosition() + "  Altura: "
							+ oi.getProductProductLocationHeight())
					.setDescLine2("Peso: " + oi.getProductWeight() + " Kg  Dimensiones: ")
					.activeIndicator(oi.getIncidence() != null && oi.getIncidence().isSolve() != true);

			// Setting the size of the panel inside the list.
			pp.setPreferredSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.setMaximumSize(new Dimension(width - 5, 80));
			pp.getDateMoreInfo().setVisible(false);

			// Adding the panel to the container.
			cont.add(pp);
			nOrders++;
		}

		for (int i = cont.getComponentCount(); i < 5; i++) {
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
