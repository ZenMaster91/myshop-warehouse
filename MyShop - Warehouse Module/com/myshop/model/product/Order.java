package com.myshop.model.product;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order implements Comparable<Order>{

	private int orderID;
	private List<OrderItem> products;
	private Status status;
	private Date dateReceived;
	
	/**
	 * Creates an empty order where we will be able to add and delete products.
	 * 
	 * @param orderID is the id of the order.
	 * @param date where the order is taking place.
	 */
	public Order(int orderID, Status status, Date dateReceived) {
		super();
		setOrderID(orderID);
		this.status = status;
		this.dateReceived = dateReceived;
	}
	
	/**
	 * 
	 * @return orders ID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * 
	 * @param orderID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	/**
	 * 
	 * @return orders status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return orders date
	 */
	public Date getDateReceived() {
		return dateReceived;
	}

	/**
	 * 
	 * @param dateReceived
	 */
	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	/**
	 * Adds a product to the order with its quantity.
	 * 
	 * @param product to be added to the order.
	 * @param quantity to be added of the given product.
	 */
	public void addProduct(Product product, int quantity) {
		products.add(new OrderItem(quantity, product));
	}
	
	/**
	 * Adds a product to the order with its quantity.
	 * 
	 * @param product to be added to the order.
	 * @param quantity to be added of the given product.
	 */
	public void addProduct(Product product, int quantity, boolean collected) {
		products.add(new OrderItem(quantity, product, collected));
	}

	/**
	 * Changes the quantity of a given product.
	 * 
	 * @param product to change the quantity.
	 * @param newQuantity for the given product.
	 */
	public void modifyQuantity(Product product, int newQuantity) {
		for (OrderItem p : products) {
			if (p.getProduct().equals(product))
				p.setQuantity(newQuantity);
		}
	}

	/**
	 * Deletes a product from the order.
	 * 
	 * @param product to be be deleted.
	 */
	public void removeProduct(Product product) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProduct().getProductID() == product
					.getProductID()) {
				products.remove(i);
				break;
			}
		}
	}

	/**
	 * Gives a list with all the products in the order.
	 * 
	 * @return a list with all the products in the order.
	 */
	public List<OrderItem> getProducs() {
		Collections.sort(products);
		return this.products;
	}
	
	/**
	 * Gets the total price for the order.
	 * 
	 * @return a double containing the total price of the order.
	 */
	public double getTotalPrice() {
		double res = 0.0;
		for (OrderItem dp : products)
			res += dp.getProduct().getPrice() * dp.getQuantity();
		return res;
	}
	
	/**
	 * Returns true if all the products have been collected.
	 * 
	 * @return true if all the products have been collected.
	 */
	public boolean allCollected() {
		for(OrderItem oi : this.products) {
			if(oi.isCollected()==false)
				return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Order o) {
		return this.getDateReceived().compareTo(o.getDateReceived());
	}
	
	
}
