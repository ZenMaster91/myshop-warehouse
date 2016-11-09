package com.myshop.warehouse.validators;

import java.util.List;

import com.myshop.warehouse.controllers.WorkingPlanItemController;

public class ReferenciasValidator implements Validator {

	private List<WorkingPlanItemController> productos;
	private String id;

	public ReferenciasValidator(String id, List<WorkingPlanItemController> productos) {
		this.productos = productos;
		this.id = id;
	}

	@Override
	public boolean validate() {
		return validate(Integer.parseInt(id));
	}

	public boolean validate(int id) {
		for (WorkingPlanItemController wpi : productos) {
			if (((Integer)(wpi.getWPI().getOrderItem().getProduct().getID())).equals(id) && (wpi.getWPI().getOrderItem().getIncidence()==null || wpi.getWPI().getOrderItem().getIncidence().isSolve())) {
				return true;
			}
		}
		return false;
	}

}
