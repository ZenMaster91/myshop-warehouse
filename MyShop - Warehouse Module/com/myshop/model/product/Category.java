package com.myshop.model.product;


public class Category {
	
	private String name;
	
	public Category(String name) {
		this.name = name;
	}
	
	/**
	 * @return the category name.
	 */
	public String getCategoryName() { return this.name; }
	
	/**
	 * @param category the new category name.
	 */
	public void setCategoryName(String category) {this.name = category;}

}
