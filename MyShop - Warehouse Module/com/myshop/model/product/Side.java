package com.myshop.model.product;

public enum Side {

	RIGHT ("derecha"), LEFT ("izquierda");
	
	private String lado;
	
	Side(String lado) {
		this.lado = lado;
	}
	
	public String toString() { return this.lado; }
	
}
