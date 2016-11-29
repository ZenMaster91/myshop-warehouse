package com.myshop.warehouse.validators;

import java.util.Date;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.controllers.WorkingPlanController;
import com.myshop.warehouse.controllers.WorkingPlanItemController;
import com.myshop.warehouse.util.DefaultSql2o;

public class ReferenciasValidator implements Validator {

	private List<WorkingPlanItemController> productos;
	private WorkingPlan wpc;
	private String id;
	private int quantity;

	public ReferenciasValidator(String id, int quantity, WorkingPlan wpc) {
		this.wpc = wpc;
		this.id = id;
		this.quantity = quantity;
	}

	@Override
	public boolean validate() {
		return validate(Integer.parseInt(id), quantity);
	}

	public boolean validate(int id, int quantity) {
		for (WorkingPlanItem wpi : wpc.getItems()) {
			if (((Integer)(wpi.getOrderItem().getProduct().getID())).equals(id) && (quantity + wpi.getItemsCollected() <= wpi.getOrderItem().getQuantity())) {
				
				wpi.setItemsCollected(wpi.getItemsCollected() + quantity);
				
				if(wpi.getItemsCollected()==wpi.getOrderItem().getQuantity()) {
					wpi.setCollected(true);
				}
				
				String sql = "UPDATE myshop.order_item as oi SET oi.items_collected = :items, oi.collected = :collected WHERE oi.product_id = :id";
				
				try(Connection con = DefaultSql2o.SQL2O.open()) {
					Query query = con.createQuery(sql);
					query.addParameter("items", wpi.getItemsCollected())
							.addParameter("collected", wpi.getCollected())
							.addParameter("id", wpi.getOrderItem().getProductID()).addToBatch();
					query.executeBatch();
				}
				
				return true;
			}
		}
		return false;
	}

}
