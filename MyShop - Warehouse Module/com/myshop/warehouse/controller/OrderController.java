package com.myshop.warehouse.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.myshop.model.customer.Customer;
import com.myshop.model.order.Order;
import com.myshop.model.order.OrderItem;
import com.myshop.model.product.Product;

public class OrderController{

	private Sql2o sql2o = new Sql2o("jdbc:mysql://myshop.cvgrlnux4cbv.eu-west-1.rds.amazonaws.com:3306/myshop", "myshop-app", "'m:9AU7n");
	
	public List<Order> getAll() {
		String complexSql = "SELECT * FROM myshop.order";
		 try (Connection con = sql2o.open()) {
			 return con.createQuery(complexSql).executeAndFetch(Order.class);
		 }
	}
	
	public Order getOrderById(String id) {
		String complexSql = "SELECT o.*,s.name FROM myshop.order o , myshop.status s where s.status_id=o.status_id and o.order_id= "+id;
		List<Map<String,Object>> map;
		 try (Connection con = sql2o.open()) {
			 map= con.createQuery(complexSql).executeAndFetchTable().asList();
		 }
		 Order o = new Order();
		 for(Map<String,Object> m : map){
			 o.setID((int)m.get("order_id"));
			 o.setCustomer(new Customer((int)m.get("customer_id")));
			 o.setStatus((String)m.get("name"));
			 o.setProducts(getAllByOrderId(o.getID()+""));
			 o.setDateReceived((Date) m.get("date_received"));
		 }
		 return o;
	}

	
	public List<Order> getOrderByStatus(String status){
		String complexSql = "SELECT o.*FROM myshop.order o, myshop.status s where o.status_id=s.status_id and s.name= :status";
		List<Map<String,Object>> map;
		List<Order> orders = new ArrayList<Order>();
		List<OrderItem> lista;
		try (Connection con = sql2o.open()) {
			 map = con.createQuery(complexSql).addParameter("status", status).executeAndFetchTable().asList();
		 }
		 for(Map<String,Object> m : map){
			 Order o = new Order();
			 o.setID((int)m.get("order_id"));
			 o.setProducts(getAllByOrderId(o.getID()+""));
			 o.setDateReceived((Date) m.get("date_received"));
			 orders.add(o);
		 }
		 return orders;
	}
	
	public List<OrderItem> getAllByOrderId(String id){
		String complexSql = "SELECT * FROM myshop.order_item o, myshop.product_location l, myshop.side s , myshop.product p where s.side_id=l.side_id and p.product_id=o.product_id and l.product_id=o.product_id and o.order_id = "+id + " order by l.corridor";
		List<Map<String,Object>> map;
		try (Connection con = sql2o.open()) {
			 map = con.createQuery(complexSql).executeAndFetchTable().asList();
		 }
		
		List<OrderItem> orders = new ArrayList<OrderItem>();
		for(Map<String,Object> m : map){
			OrderItem o = new OrderItem();
			Product p = new Product();
			o.setQuantity((int) m.get("quantity"));
			o.setID((int) m.get("order_item_id"));
			p.setCorridor((int) m.get("corridor"));
			p.setHeight((int) m.get("heigth"));
			p.setID((int) m.get("product_id"));
			p.setPosition((int) m.get("position"));
			p.setPrice((double) m.get("price"));
			p.setSide((String) m.get("name"));
			o.setProduct(p);
			orders.add(o);
		}
		return orders;
	}
	
	
}
