package com.myshop.warehouse.controllers;

import java.util.ArrayList;
import java.util.List;

import com.myshop.model.order.OrderItem;
import com.myshop.model.warehouseKeeper.WarehouseKeeper;
import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;

public class WorkingPlanController {
	
	private WorkingPlan wp;
	private boolean splitted;
	
	public WorkingPlanController() {
		wp = new WorkingPlan(-1, null, new ArrayList<WorkingPlanItem>());
	}
	
	public WorkingPlanController(WorkingPlan wp) {
		this.wp = wp;
	}
	
	/**
	 * @return all the items in the working plan.
	 */
	public List<WorkingPlanItem> getItems() {
		return this.wp.getItems();
	}
	
	/**
	 * @return the number of items in the list.
	 */
	public int getNumberOfItems() { return this.wp.getItems().size(); }
	
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
	 * @param wk warehouse keeper.
	 */
	public WorkingPlanController assignWareHouseKeeper(WarehouseKeeper wk) {
		wp.setWarehouseKeeper(wk);
		return this;
	}
	
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

	/**
	 * @return the splitted
	 */
	public boolean isSplitted() {
		return splitted;
	}

	/**
	 * @param splitted the splitted to set
	 */
	public void setSplitted(boolean splitted) {
		this.splitted = splitted;
	}

}
