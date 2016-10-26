package com.myshop.model.workingPlan;

import com.myshop.model.product.OrderItem;

public class WorkingPlanItem {
	
	private OrderItem orderItem;
	private int wpID;
	
	/**
	 * @return the orderItem
	 */
	public OrderItem getOrderItem() { return orderItem; }
	
	/**
	 * @param orderItem the orderItem to set
	 */
	public void setOrderItem(OrderItem orderItem) { this.orderItem = orderItem; }
	
	/**
	 * @return the wpID
	 */
	public int getWpID() { return wpID; }
	
	/**
	 * @param wpID the wpID to set
	 */
	public void setWpID(int wpID) { this.wpID = wpID; }
	
	

}
