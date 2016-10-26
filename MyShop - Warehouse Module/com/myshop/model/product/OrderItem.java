package com.myshop.model.product;

public class OrderItem implements Comparable<OrderItem>{

	private int quantity;
	private Product product;
	private boolean collected = false;
	private Incidence incidence;
	private MailBox mailbox;
	
	/**
	 * Creates a new entry for a product,its id, its quantity and if its collected
	 * 
	 * @param id of the product to add.
	 * @param quantity to be added of the product.
	 * @param product to add.
	 **/
	
	public OrderItem(int quantity, Product product, boolean collected) {
		setQuantity(quantity);
		this.product = product;
		setCollected(collected);
	}
	/**
	 * Creates a new entry for a product,its id and its quantity
	 * 
	 * @param quantity to be added of the product.
	 * @param product to add.
	 **/
	public OrderItem (int quantity, Product product) {
		setQuantity(quantity);
		this.product = product;
	}

	/**
	 * 
	 * @return products quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 
	 * @return if its collected
	 */
	public boolean isCollected() {
		return collected;
	}

	/**
	 * 
	 * @param collected
	 */
	public void setCollected(boolean collected) {
	
	}
	@Override
	public int compareTo(OrderItem oi) {
		return this.product.getProductLocation().compareTo(oi.getProduct().getProductLocation());
	}
	
}
