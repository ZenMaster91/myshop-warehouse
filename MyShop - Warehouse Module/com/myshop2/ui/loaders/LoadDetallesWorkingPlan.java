package com.myshop2.ui.loaders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import com.myshop.model.order.OrderItem;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop2.ui.panels.DefaultListPanel;

public class LoadDetallesWorkingPlan {

	private static int nOrders = 0;

	public static Container load(WorkingPlan workingPlan, int width) {
		nOrders = 0;
		Container cont = new Container();

		for (WorkingPlanItem wpi : workingPlan.getItems()) {
			DefaultListPanel pp = new DefaultListPanel();
			OrderItem oi = wpi.getOrderItem();
			// Set values according with the order.
			pp.setTitle("P." + oi.getID() + "   Cant.: " + oi.getQuantity())
					.setDescLine1("Pasillo: " + oi.getProductProductLocationCorridor()
								+ "  Lado: " + oi.getProductProductLocationSide()
								+ "  Posición: " + oi.getProductProductLocationPosition()
								+ "  Altura: " + oi.getProductProductLocationHeight())
					.setDescLine2("Peso: " + oi.getProductWeight() + " Kg  Dimensiones: ")
					.activeIndicator(oi.getIncidence()!=null && oi.getIncidence().isSolve()!=true);

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
