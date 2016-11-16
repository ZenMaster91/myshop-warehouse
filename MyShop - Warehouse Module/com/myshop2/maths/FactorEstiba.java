package com.myshop2.maths;

public class FactorEstiba {
	
	private double vol, weight;
	
	public FactorEstiba(double vol, double weight) {
		this.vol = vol;
		this.weight = weight;
	}
	
	public double calculate() {
		return (vol/weight);
	}

}
