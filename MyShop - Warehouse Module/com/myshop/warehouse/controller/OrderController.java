package com.myshop.warehouse.controller;

import java.util.Collections;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;

public class OrderController{

	
	public List<Order> getAll() {
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.order";
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).executeAndFetch(Order.class);
		 }
	}
	
	public Order getOrderById(String id) {
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.order where order_id= "+id;
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).executeAndFetch(Order.class).get(0);
		 }
	}
	
	public List<Order> getOrderByStatus(String status){
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.order o, myshop.status s where o.status_id=s.status_id and s.name= "+status;
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).executeAndFetch(Order.class);
		 }
	}
	
	public List<OrderItem> getAllByOrderId(String id){
		Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
		String complexSql = "SELECT * FROM myshop.order_item o where o.order_id = "+id;
		 try (Connection con = sql2o.open()) {
			 //TODO
			 return Collections.sort(con.createQuery(complexSql).executeAndFetch(OrderItem.class));
		 }
	}
	
	
}
