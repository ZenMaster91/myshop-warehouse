package com.myshop.warehouse.controller;

import java.util.List;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;

public class OrderController {

	public double getWeight(Order o) {
		double aux = 0.0;
		for (OrderItem oi : o.getProducts()) {
			aux += oi.getProduct().getWeight();
		}
		return aux;
	}

	public List<Order> getNotAssigned() {
		return null;
	}

}
