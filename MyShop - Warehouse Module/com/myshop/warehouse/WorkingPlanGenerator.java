package com.myshop.warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controller.WorkingPlanController;

/**
 * WorkingPlanGenerator generates working plans acording to a max load.
 * 
 * @version 2610161700
 * @author Guillermo Facundo Colunga
 *
 */
public class WorkingPlanGenerator {

	public final static int MAX_WP_LOAD = 10;

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
	public WorkingPlanGenerator generate() {
		// We create a default wpc pointer to manage different objects inside
		// the loop.
		WorkingPlanController wpc;

		// We will work over this auxiliary pointer that ponits to all the not
		// assigned items.
		List<OrderItem> aux = new GetNotAssignedOrderItems().getAll();
		// Meanwhile there are still elements unassigned...
		while (aux.size() > 0) {
			// Each iteration represents a new WorkingPlanController.
			wpc = new WorkingPlanController();
			// The for each item not assigned yet we try to.
			for (OrderItem oi : aux) {
				// Special case if there's no items in the list and we find one
				// that is heavier than the max_load per working plan.
				if (wpc.getNumberOfItems() < 1 && oi.getProduct().getWeight() > MAX_WP_LOAD) {
					wpc.addItem(oi); // Add it to the working plan.
					aux.remove(oi); // Remove it from the remaining items list.
					workingPlans.add(wpc);
					break;
				} else {
					// Then usual case that is try to add the item to the
					// working plan if the current total weight plus the item
					// weight is no grater than the max load.
					if (wpc.getTotalWeight() + oi.getWeight() <= MAX_WP_LOAD) {
						wpc.addItem(oi); // Add it to the working plan.
						aux.remove(oi); // Remove it from the remaining items
										// list.
					}
				}
			}
			// At the end of each iteration we add the new generated working
			// plan to the list of working plans.
			workingPlans.add(wpc);
		}
		save();
		return this;
	}

	/**
	 * Sends the current status to the database.
	 */
	public void save() {
		final String insertWorkingPlan = "INSERT INTO myshop.working_pan() VALUES ()";
		final String insertWorkingPlanItem = "INSERT INTO myshop.working_plan_item (order_item_id, wp_id) VALUES (:item_id, :wp_id)";
		Sql2o sql2o = new Sql2o("urlToDatbase");
		Query query;
		Connection con;
		
		for (WorkingPlanController wpc : workingPlans) {

			try (con = sql2o.open()) {
				con.createQuery(insertWorkingPlan).executeUpdate();
			}
			int index = new GetLastIndexCreated().get();

			try (con = sql2o.beginTransaction()) {
				query = con.createQuery(insertWorkingPlanItem);

				for (OrderItem oi : wpc.getItems()) {
					query.addParameter("item_id", oi.getProductID()).addParameter("wp_id", index).addToBatch();
				}
			}
		}
		// Executes entire batch. Speeds up the performance.
		query.executeBatch();
		// By committing all the work before we avoid a roll back.
		con.commit();
	}
}
