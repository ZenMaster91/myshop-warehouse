package com.myshop.warehouse.generators;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.OrderController;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.util.GetLastIndexCreated;

/**
 * WorkingPlanGenerator generates working plans acording to a max load.
 * 
 * @version 31102238
 * @author Guillermo Facundo Colunga
 *
 */
public class WorkingPlanGenerator implements Generator {

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
	@Override
	public WorkingPlanGenerator generate() {
		// We create a default wpc pointer to manage different objects inside
		// the loop.
		WorkingPlanController wpc;

		// We will work over this auxiliary pointer that ponits to all the not
		// assigned items.
		List<Order> aux = new OrderController().getNotAssigned();
		for (Order o : aux) {
			if (new OrderController().getWeight(o) <= MAX_WP_LOAD) {
				wpc = new WorkingPlanController().addAll(o.getProducts());
			} else {
				wpc = new WorkingPlanController();

				for (OrderItem oi : o.getProducts()) {
					// Special case if there's no items in the list and we find
					// one
					// that is heavier than the max_load per working plan.
					if (wpc.getNumberOfItems() < 1 && oi.getProduct().getWeight() > MAX_WP_LOAD) {
						wpc.addItem(oi); // Add it to the working plan.
						workingPlans.add(wpc);
						wpc = new WorkingPlanController();
					} else {
						// Then usual case that is try to add the item to the
						// working plan if the current total weight plus the
						// item
						// weight is no grater than the max load.
						if (wpc.getTotalWeight() + oi.getProduct().getWeight() <= MAX_WP_LOAD) {
							wpc.addItem(oi); // Add it to the working plan.
						} else {
							wpc = new WorkingPlanController().addItem(oi);
						}
					}
				}
			}
		}
		save();
		return this;
	}

	/**
	 * Sends the current status to the database.
	 */
	private void save() {
		final String insertWorkingPlan = "INSERT INTO myshop.working_pan() VALUES ()";
		final String insertWorkingPlanItem = "INSERT INTO myshop.working_plan_item (order_item_id, wp_id) VALUES (:item_id, :wp_id)";
		Sql2o sql2o = new Sql2o("urlToDatbase");
		Query query = null;
		Connection con = null;

		for (WorkingPlanController wpc : workingPlans) {

			con = sql2o.open();
			con.createQuery(insertWorkingPlan).executeUpdate();

			int index = new GetLastIndexCreated().get();

			con = sql2o.beginTransaction();
			query = con.createQuery(insertWorkingPlanItem);

			for (WorkingPlanItem wpi : wpc.getItems()) {
				OrderItem oi = wpi.getOrderItem();
				query.addParameter("item_id", oi.getProductID()).addParameter("wp_id", index).addToBatch();
			}

		}
		// Executes entire batch. Speeds up the performance.
		query.executeBatch();
		// By committing all the work before we avoid a roll back.
		con.commit();
	}
}
