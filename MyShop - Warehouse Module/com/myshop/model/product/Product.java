package com.myshop.model.product;

public class Product {

	private int productID, stock;
	private String name,description;
	private double weight, price;
	private Category category, subcategory;
	private ProductLocation productLocation;
	
	/**
	 * @return the productID
	 */
	public int getProductID() { return productID; }
	
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) { this.productID = productID; }
	
	/**
	 * @return the stock
	 */
	public int getStock() { return stock; }
	
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) { this.stock = stock; }
	
	/**
	 * @return the name
	 */
	public String getName() { return name; }
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) { this.name = name; }
	
	/**
	 * @return the description
	 */
	public String getDescription() { return description; }
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * @return the weight
	 */
	public double getWeight() { return weight; }
	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) { this.weight = weight; }
	
	/**
	 * @return the price
	 */
	public double getPrice() { return price; }
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) { this.price = price; }

	/**
	 * @return the category
	 */
	public String getCategory() { return category.getCategoryName(); }

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) { this.category = new Category(category); }

	/**
	 * @return the subcategory
	 */
	public String getSubcategory() { return subcategory.getCategoryName(); }

	/**
	 * @param subcategory the subcategory to set
	 */
	public void setSubcategory(String subcategory) { this.subcategory = new Category(subcategory); }

	/**
	 * @return the productLocation
	 */
	public ProductLocation getProductLocation() { return productLocation; }

	/**
	 * @param productLocation the productLocation to set
	 */
	public void setProductLocation(ProductLocation productLocation) { this.productLocation = productLocation; }
	
	/**
	 * @return the corridor
	 */
	public int getCorridor() { return productLocation.getCorridor(); }
	
	/**
	 * @param corridor corridor the be set for this product.
	 */
	public void setCorridor(int corridor) {
		if(productLocation==null) {
			productLocation = new ProductLocation();
		}
		productLocation.setCorridor(corridor);
	}
	
	/**
	 * @return the side
	 */
	public String getSide() { return productLocation.getSide().toString(); }
	
	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
		if(productLocation==null) {
			productLocation = new ProductLocation();
		}
		
		if(side.equals(Side.LEFT.toString()))
			productLocation.setSide(Side.LEFT);
		else
			productLocation.setSide(Side.RIGHT);
	}
	
	/**
	 * @return the position
	 */
	public int getPosition() { return productLocation.getPosition(); }
	
	/**
	 * @param pos position of the product.
	 */
	public void setPosition(int pos) {
		if(productLocation==null) {
			productLocation = new ProductLocation();
		}
		productLocation.setPosition(pos);
	}
	
	/**
	 * @return the height
	 */
	public int getHeigth() { return productLocation.getHeight(); }
	
	/**
	 * @param h height where this product is placed
	 */
	public void setHeight(int h) {
		if(productLocation==null) {
			productLocation = new ProductLocation();
		}
		productLocation.setHeight(h);
	}
}
