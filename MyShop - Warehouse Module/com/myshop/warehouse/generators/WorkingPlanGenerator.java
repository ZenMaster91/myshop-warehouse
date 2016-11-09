package com.myshop.warehouse.generators;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.WorkingPlanController;

/**
 * WorkingPlanGenerator generates working plans acording to a max load.
 * 
 * @version 31102238
 * @author Guillermo Facundo Colunga
 *
 */
public class WorkingPlanGenerator implements Generator {

	public final static double MAX_WP_LOAD = 10.0;

	private List<WorkingPlanController> workingPlans;

	public WorkingPlanGenerator() {
		this.workingPlans = new ArrayList<WorkingPlanController>();
	}

	public WorkingPlanGenerator(List<WorkingPlanController> workingPlans) {
		this.workingPlans = workingPlans;
	}

	/**
	 * Gets all the working plans generated.
	 * 
	 * @return all the working plans generated.
	 */
	public List<WorkingPlanController> getAll() {
		return this.workingPlans;
	}

	/**
	 * From the unassigned order_items it generates the necessary working plans
	 * that fits a maximum load. Then automatically saves it on to the database.
	 * 
	 * @return the necessary working plans that fits a maximum load.
	 */
	@Override
	public WorkingPlanGenerator generate() {
		// We create a default wpc pointer to manage different objects inside
		// the loop.
		WorkingPlanController wpc = new WorkingPlanController();

		// We will work over this auxiliary pointer that ponits to all the not
		// assigned items.
		List<Order> aux = null;
		try {
			aux = new OrderController().getNotAssigned();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Order o : aux) {
			System.out.println("Order received: " + o.getID() + " W: " + new OrderController().getWeight(o) + " WPC-W: " + wpc.getTotalWeight() + " C: " + (wpc.getTotalWeight() + new OrderController().getWeight(o) <= MAX_WP_LOAD));
			if (wpc.getTotalWeight() + new OrderController().getWeight(o) <= MAX_WP_LOAD) {
				System.out.println("Order fits simple condition: " + o.getID());
				wpc.addAll(o.getProducts());
			} else {
				if(!wpc.isChild() && wpc.getNumberOfItems()>0)
					workingPlans.add(wpc);
				wpc = new WorkingPlanController();
				WorkingPlanController parent = wpc;
				List<OrderItem> toDelete = new ArrayList<OrderItem>();
				for (OrderItem oi : o.getProducts()) {
					if (wpc.getNumberOfItems() < 1 && oi.getProductWeight() > MAX_WP_LOAD) {
						wpc.addItem(oi);
						toDelete.add(oi);
						if(!wpc.isChild() && wpc.getNumberOfItems()>0)
							workingPlans.add(wpc);
						wpc = new WorkingPlanController().setParent(parent);
					}
				}
				o.getProducts().removeAll(toDelete);
				toDelete.clear();
				if(wpc.getNumberOfItems() > 0)
					wpc = new WorkingPlanController().setParent(parent);
				int i = 0, j = 1;
				while(i < o.getProducts().size()) {
					wpc.addItem(o.getProducts().get(i));
					while(j < o.getProducts().size()) {
						if(wpc.getTotalWeight() + o.getProducts().get(j).getProductWeight() <= MAX_WP_LOAD) {
							wpc.addItem(o.getProducts().get(j));
						} else {
							if(!wpc.isChild() && wpc.getNumberOfItems()>0)
								workingPlans.add(wpc);
							wpc = new WorkingPlanController().setParent(parent);
						}
						j++;
					}
					i = j;
				}
				if(!wpc.isChild() && wpc.getNumberOfItems()>0) {
					workingPlans.add(wpc);
					wpc = new WorkingPlanController();
				}
			}
		}
		if(!wpc.isChild() && wpc.getNumberOfItems()>0)
			workingPlans.add(wpc);
		// save();
		return this;
	}
}
