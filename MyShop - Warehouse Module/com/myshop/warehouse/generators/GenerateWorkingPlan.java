package com.myshop.warehouse.generators;

import java.util.ArrayList;
import java.util.List;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop2.maths.FactorEstiba;

public class GenerateWorkingPlan {

	private static WorkingPlanController generateFromList(List<Order> orders) {
		WorkingPlanController workingPlan = new WorkingPlanController(-1);

		// Teniendo en cuenta que la lista está ordenada mostrando en primer
		// lugar la orden seleccionada.
		for (Order order : orders) {
			System.out.println("Order with: " + order.getProducts().size());
			// Si es más pequeña que el añadido al valor de estiva se añade al
			// working plan.
			System.out.println("V: " + new OrderController(order).getVolume() + workingPlan.getTotalVolume());
			System.out.println("W: " + new OrderController().getWeight(order) + workingPlan.getTotalWeight());
			if (new FactorEstiba(new OrderController(order).getVolume() + workingPlan.getTotalVolume(),
					new OrderController().getWeight(order) + workingPlan.getTotalWeight()).validate()) {
				// Se añade directo a la OT.
				workingPlan.addAll(order.getProducts());
			}
		}

		return workingPlan;
	}

	private static List<WorkingPlanController> generateFromSingle(Order o) {
		List<WorkingPlanController> workingPlans = new ArrayList<WorkingPlanController>();
		WorkingPlanController workingPlan = new WorkingPlanController(-1);

		if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0)
			workingPlans.add(workingPlan);
		workingPlan = new WorkingPlanController(-1);
		WorkingPlanController parent = workingPlan;
		List<OrderItem> toDelete = new ArrayList<OrderItem>();
		for (OrderItem oi : o.getProducts()) {
			if (workingPlan.getNumberOfItems() < 1 && (oi.getProductWeight() > WorkingPlanGenerator.MAX_WP_LOAD
					&& oi.getProduct().getDimensions().calculateVolume() > WorkingPlanGenerator.MAX_WP_VOL)) {
				workingPlan.addItem(oi);
				toDelete.add(oi);
				if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0)
					workingPlans.add(workingPlan);
				workingPlan = new WorkingPlanController(-1).setParent(parent);
			}
		}
		o.getProducts().removeAll(toDelete);
		toDelete.clear();
		if (workingPlan.getNumberOfItems() > 0)
			workingPlan = new WorkingPlanController(-1).setParent(parent);
		int i = 0, j = 1;
		while (i < o.getProducts().size()) {
			workingPlan.addItem(o.getProducts().get(i));
			while (j < o.getProducts().size()) {
				if ((workingPlan.getTotalWeight()
						+ o.getProducts().get(j).getProductWeight() <= WorkingPlanGenerator.MAX_WP_LOAD)
						&& (workingPlan.getTotalVolume() + o.getProducts().get(j).getProduct().getDimensions()
								.calculateVolume() <= WorkingPlanGenerator.MAX_WP_VOL)) {
					workingPlan.addItem(o.getProducts().get(j));
				} else {
					if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0)
						workingPlans.add(workingPlan);
					workingPlan = new WorkingPlanController(-1).setParent(parent);
				}
				j++;
			}
			i = j;
		}
		if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0) {
			workingPlans.add(workingPlan);
			workingPlan = new WorkingPlanController(-1);
		}

		return workingPlans;
	}

	private static List<WorkingPlanController> generateFromSingleSimple(Order o) {
		List<WorkingPlanController> workingPlans = new ArrayList<WorkingPlanController>();
		WorkingPlanController workingPlan = new WorkingPlanController(-1);
		WorkingPlanController parent = workingPlan;
		for (OrderItem oi : o.getProducts()) {
			if (new FactorEstiba(workingPlan.getTotalVolume() + oi.getProduct().getDimensions().calculateVolume(),
					workingPlan.getTotalWeight() + oi.getProduct().getWeight()).validate()) {
				if(oi!=null)
					workingPlan.addItem(oi);
			} else {
				workingPlan = new WorkingPlanController(-1).setParent(parent);
				if(oi!=null)
					workingPlan.addItem(oi);
			}
			
			if(!workingPlan.isChild() && workingPlan.getItems().size() > 0) {
				System.out.println("Generating OT -> WP with: " + workingPlan.getNumberOfItems() + " items");
				workingPlans.add(workingPlan);
			}

		}
		return workingPlans;
	}

	/**
	 * Generates a list of workingPlanItems from a given order.
	 * 
	 * @param order
	 *            to start computing.
	 * @param orders
	 *            orders to compute
	 * @return
	 */
	private static List<WorkingPlanController> generate(Order order, List<Order> orders) {
		if (false/* orders.contains(order) */) {
			throw new IllegalArgumentException("The order " + order.getID() + " is not in the list of orders.");
		} else {
			if (new FactorEstiba(new OrderController(order).getVolume(), new OrderController().getWeight(order))
					.validate()) {
				// La orden se puede meter en la oja de trabajo de forma directa
				// e incluso se pueden añadir más.
				System.out.println("Generating a simple OT");
				return generateFromList(orders).toList();
			} else {
				// La orden se tiene que particionar.
				System.out.println("Generating a complex OT");
				return generateFromSingleSimple(order);
			}
		}
	}

	/**
	 * Generates and assigns the generated working plans to the given warehouse
	 * keeper.
	 * 
	 * @param wk
	 *            the warehouse keeper
	 * @param order
	 *            order to start the generation of orders.
	 * @param orders
	 *            to generate from.
	 */
	public static void generateAndAssign(WarehouseKeeper wk, Order order, List<Order> orders) {
		for (WorkingPlanController wpc : generate(order, orders)) {
			System.out.println(wpc.getWp().getID() + ": " + wpc.getNumberOfItems() + " : " + wpc.getTotalVolume()
					+ "cm3 : " + wpc.getTotalWeight() + "kg");
			WorkingPlanController.assign(wpc, wk);
		}
	}

}
