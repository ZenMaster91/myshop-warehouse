package com.myshop.warehouse.controllers;

import com.myshop.model.order.OrderItem;

public class OrderItemController implements Comparable<OrderItemController> {

	private OrderItem oi;
	
	public OrderItemController(OrderItem oi) {
		this.oi = oi;
	}
	
	public OrderItem getItem() {
		return this.oi;
	}
	
	@Override
	public int compareTo(OrderItemController o) {
		return ((Integer)oi.getID()).compareTo(o.oi.getID());
	}

}
