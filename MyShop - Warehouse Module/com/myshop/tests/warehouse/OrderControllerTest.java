package com.myshop.tests.warehouse;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.junit.Test;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.warehouse.controllers.OrderController;

public class OrderControllerTest {

	@Test
	public void test() {
		try {
			for(Order o : new OrderController().getNotAssigned()) {
				System.out.println(o.getID() + " --> " + new OrderController().getWeight(o));
				for(OrderItem oi : o.getProducts()) {
					System.out.println("    --> " + oi.getProduct().getName() + " " + oi.getProduct().getPrice() + " " + new DecimalFormat("#0.000").format(oi.getProduct().getCompanyPrice()) + " - " + oi.getProductWeight());
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
