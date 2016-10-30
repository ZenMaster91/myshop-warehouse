package com.myshop.warehouse.controller;

import java.util.List;

import com.myshop.model.order.OrderItem;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;

public class WorkingPlanController {
	
	private WorkingPlan wp;
	
	public WorkingPlanController() {
		wp = new WorkingPlan(-1, null, null);
	}
	
	public WorkingPlanController(WorkingPlan wp) {
		this.wp = wp;
	}
	
	/**
	 * @param orderItem item to add to the wpc.
	 */
	public WorkingPlanController addItem(OrderItem orderItem) { 
		this.wp.getItems().add(new WorkingPlanItem(orderItem, false)); 
		return this;
	}
	
	/**
	 * @param items items to be added.
	 */
	public WorkingPlanController addAll(List<OrderItem> items) {
		for(OrderItem oi : items) {
			addItem(oi);
		}
		return this;
	}
	
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
	
	public List<WorkingPlanItem> getItems() {
		return this.wp.getItems();
	}

}
