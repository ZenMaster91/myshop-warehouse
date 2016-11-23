package com.myshop2.maths;

import com.myshop.warehouse.generators.WorkingPlanGenerator;

public class FactorEstiba {
	
	private double vol, weight;
	
	public FactorEstiba(double vol, double weight) {
		this.vol = vol;
		this.weight = weight;
	}
	
	public boolean validate() {
		if(this.weight < WorkingPlanGenerator.MAX_WP_LOAD && this.vol/1000000 < WorkingPlanGenerator.MAX_WP_VOL)
			return true;
		return false;
	}
	
	public double calculate() {
		return ((vol)/weight);
	}

}
