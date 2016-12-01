package com.myshop.warehouse.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
			System.out.println("Order with: " + new OrderController(order).getNumberOfItems());
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
			if (workingPlan.getNumberOfItems() < 1 && workingPlan.canAdd(oi)) {
				workingPlan.addItem(oi);
				toDelete.add(oi);
				if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0)
					workingPlans.add(workingPlan);
				workingPlan = new WorkingPlanController(-1);
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
				if (workingPlan.canAdd(o.getProducts().get(j))) {
					workingPlan.addItem(o.getProducts().get(j));
				} else {
					if (!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0)
						workingPlans.add(workingPlan);
					workingPlan = new WorkingPlanController(-1);
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
		
		//WorkingPlanController parent = workingPlan;
		Random rnd = new Random();
		for (OrderItem oi : o.getProducts()) {
			
			if (workingPlan.canAdd(oi)) {
				System.out.println(">> Addind to the same workingPlan");
				
				if(oi!=null)
					workingPlan.addItem(oi);
			
			} else {
				System.out.println(">> Addind to diferent workingPlan");
				workingPlan = new WorkingPlanController(rnd.nextInt(Integer.MAX_VALUE));
				
				if(oi!=null)
					workingPlan.addItem(oi);
			
			}
			
			if(!workingPlan.isChild() && workingPlan.getNumberOfItems() > 0) {
				
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
			System.out.println("--> Voúmen: " + new OrderController(order).getVolume() + " || Peso: " + new OrderController().getWeight(order));
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
			new WorkingPlanController(-1).assign(wpc, wk);
		}
	}

}
