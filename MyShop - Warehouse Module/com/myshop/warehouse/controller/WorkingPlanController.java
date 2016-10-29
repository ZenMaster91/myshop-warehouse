package com.myshop.warehouse.controller;

import com.myshop.model.order.OrderItem;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;

public class WorkingPlanController {
	
	private WorkingPlan wp;
	
	public WorkingPlanController() {
		wp = new WorkingPlan();
	}
	
	public WorkingPlanController(WorkingPlan wp) {
		this.wp = wp;
	}
	
	/**
	 * @param orderItem item to add to the wpc.
	 */
	public void addItem(OrderItem orderItem) { this.wp.getItems().add(orderItem); }
	
	/**
	 * @return the number of items in the list.
	 */
	public int getNumberOfItems() { return this.wp.getItems().size(); }
	
	/**
	 * @return the total weight of the order.
	 */
	public double getTotalWeight() {
		double weight = 0.0;
		for(WorkingPlanItem wpi : this.wp.getItems()) {
			OrderItem oi = wpi.getOrderItem();
			weight += oi.getProduct().getWeight();
		}
		return weight;
	}
	
	public void assignWareHouseKeeper(WarehouseKeeper wk) {
		wk.setID(wk.getID());
		wk.setName(wk.getName());
		wk.setSurname(wk.getSurname());
	}

}
