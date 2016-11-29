package com.myshop.warehouse.validators;

import java.util.Date;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.myshop.model.workingPlan.WorkingPlan;
import com.myshop.model.workingPlan.WorkingPlanItem;
import com.myshop.warehouse.util.DefaultSql2o;

public class WorkingPlanCompletedValidator implements Validator {

	private WorkingPlan wp;

	public WorkingPlanCompletedValidator(WorkingPlan wp) {
		this.wp = wp;
	}

	@Override
	public boolean validate() {
		for (WorkingPlanItem wpi : wp.getItems()) {
			if (wpi.getCollected() == false) {
				return false;
			}
		}
		String sql = "UPDATE myshop.working_plan AS wp SET wp.date_completed = :date WHERE wp.wp_id = :id";
		
		try(Connection con = DefaultSql2o.SQL2O.open()) {
			Query query = con.createQuery(sql);
			query.addParameter("date", new Date())
					.addParameter("id", wp.getID())
					.addToBatch();
			query.executeBatch();
		}
		return true;
	}

}
