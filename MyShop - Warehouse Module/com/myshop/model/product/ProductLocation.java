package com.myshop.model.product;

public class ProductLocation implements Comparable<ProductLocation> {
	
	private int corridor, position, height;
	private Side side;
	
	public ProductLocation() { }

	/**
	 * @return the corridor
	 */
	public int getCorridor() { return corridor; }

	/**
	 * @param corridor the corridor to set
	 */
	public void setCorridor(int corridor) { this.corridor = corridor; }

	/**
	 * @return the position
	 */
	public int getPosition() { return position; }

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) { this.position = position; }

	/**
	 * @return the height
	 */
	public int getHeight() { return height; }

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) { this.height = height; }

	/**
	 * @return the side
	 */
	public Side getSide() { return side; }

	/**
	 * @param side the side to set
	 */
	public void setSide(Side side) { this.side = side; }
	
	public String toString() {
		return "PASILLO: " + corridor + ". LADO: " + side + ". POSICIÓN: " + position + ". ALTURA: " + height + ".";
	}
	
	@Override
	public int compareTo(ProductLocation o) {
		return this.toString().compareTo(o.toString());
	}

}
