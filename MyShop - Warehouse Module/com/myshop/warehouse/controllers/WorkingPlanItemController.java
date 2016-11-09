package com.myshop.warehouse.controllers;

import org.sql2o.Connection;

import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.util.DefaultSql2o;

public class WorkingPlanItemController implements Comparable<WorkingPlanItemController> {

	private WorkingPlanItem wpi;

	public WorkingPlanItemController(WorkingPlanItem wpi) {
		this.wpi = wpi;
	}

	public WorkingPlanItem getWPI() {
		return this.wpi;
	}

	@Override
	public int compareTo(WorkingPlanItemController o) {
		return ((Integer) wpi.getOrderItem().getID()).compareTo(o.getWPI().getOrderItem().getID());
	}

	public static void collect(String orderItemID) {
		collect(Integer.parseInt(orderItemID));
	}
	
	public static void collect(int orderItemID) {
		String sql = "UPDATE myshop.working_plan_item SET collected=1 where order_item_id=:oiID";

		try (Connection con = DefaultSql2o.SQL2O.open()) {
			con.createQuery(sql)
					.addParameter("oiID", orderItemID)
					.executeUpdate();
		}
	}

}
